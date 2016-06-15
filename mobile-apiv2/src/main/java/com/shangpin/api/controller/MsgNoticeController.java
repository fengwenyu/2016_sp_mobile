package com.shangpin.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.NoticeMenu;
import com.shangpin.biz.bo.PushInfo;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.bo.message.MessageCount;
import com.shangpin.biz.bo.message.ShowMultiMessage;
import com.shangpin.biz.service.ASPBizBrandService;
import com.shangpin.biz.service.ASPBizMsgNoticeService;
import com.shangpin.biz.service.ASPBizPushService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;

/**
 * 消息通知接口
 * 
 * @author Administrator
 *
 */
@Controller
public class MsgNoticeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MsgNoticeController.class);
    @Autowired
    private ASPBizMsgNoticeService msgNoticeService;

    @Autowired
    private ASPBizBrandService aspBizBrandService;
    @Autowired
    private ASPBizPushService bizPushService;

    /**
     * 根据用户ID查询消息列表
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
	@ResponseBody
    @RequestMapping(value = "/messageNotice", method = { RequestMethod.POST, RequestMethod.GET })
    public String messageNotice(HttpServletRequest request) {
    	String useragent = request.getHeader("User-Agent");
    	String plateForm = ClientUtil.isIOS(useragent) ? "IOS" : "Android";
    	String version = request.getHeader("ver");
        String type = request.getParameter("type");

        if("1".equals(type)){

            final String imei = request.getHeader("imei");
            final String userId = request.getHeader("userid");
            if (!StringUtil.isNotEmpty(imei)) {
                return returnParamError();
            }

            try {
                return aspBizBrandService.doNewGoods(userId);
            } catch (Exception e) {
                logger.error("error:", e);
                return returnSystemError();
            }
        }



    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String pageIndex = request.getParameter("pageIndex")==null?"":request.getParameter("pageIndex");
    	String pageSize = request.getParameter("pageSize")==null?"":request.getParameter("pageSize");
    	//消息类型
    	String messageType = request.getParameter("messageType")==null?"":request.getParameter("messageType");
    	//消息状态
    	String readStatus = request.getParameter("readStatus")==null?"":request.getParameter("readStatus");
        String data = msgNoticeService.findMsgList(userId,pageIndex,pageSize, Constants.NOTICE_TYPE_ORDER, readStatus, version, plateForm);
        msgNoticeService.updateBatchMessageStatus(userId, Constants.NOTICE_TYPE_ORDER, Constants.NOTICE_MESSAGE_READ);
        return data;
    }

    /**
     * 更新消息状态接口
     * @param request
     * @param subOrderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/messageRead", method = { RequestMethod.POST, RequestMethod.GET })
    public String messageRead(HttpServletRequest request, String msgId, String readFlag) {
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
        String data = msgNoticeService.updateMsgStatus(userId, msgId, readFlag);
        return data;
    }
    
    /**
     * 物流消息和系统消息菜单
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notice/menu/list", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ContentBuilder menuList(HttpServletRequest request){
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	List<NoticeMenu> noticeMenus = msgNoticeService.getMenuListObj();
    	for(NoticeMenu noticeMenu : noticeMenus){
    		String type = noticeMenu.getType();
    		if("4".equals(type)){//物流通知
    			MessageCount count = msgNoticeService.findMsgCountObj(userId, Constants.NOTICE_TYPE_ORDER, Constants.NOTICE_MESSAGE_UN_READ);
    			noticeMenu.setUnRead(null == count ? "0" : count.getTotal());
    		}else if("3".equals(type)) {//通知
    			MessageCount count = msgNoticeService.findMsgCountObj(userId, Constants.NOTICE_TYPE_ORDER_RETURN, Constants.NOTICE_MESSAGE_UN_READ);
    			noticeMenu.setUnRead(null == count ? "0" : count.getTotal());
			}else if("1".equals(type)){//尚品活动
				noticeMenu.setUnRead("0");
			}
    	}
    	ContentBuilder builder = new ContentBuilder();
    	Map<String, List<NoticeMenu>> map = new HashMap<String, List<NoticeMenu>>();
    	map.put("list", null != noticeMenus ? noticeMenus : new ArrayList<NoticeMenu>());
    	return builder.buildDefSuccess(map);
    }
    
    /**
     * push消息通知
     * @param pageIndex
     * @param pageSize
     * @param type
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/notice/push/list", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String pushList(String pageIndex, String pageSize, String type, HttpServletRequest  request){
    	String useragent = request.getHeader("User-Agent");
    	String plateForm = ClientUtil.isIOS(useragent) ? "IOS" : "Android";
    	String userId = request.getHeader("userid");
    	List<PushInfo> pushInfos = bizPushService.pushInfo(userId, pageIndex, pageSize, plateForm);
    	ContentBuilder builder = new ContentBuilder();
    	Map<String, List<PushInfo>> map = new HashMap<String, List<PushInfo>>();
    	map.put("list", null != pushInfos ? pushInfos : new ArrayList<PushInfo>());
    	return JsonUtil.toJson(builder.buildDefSuccess(map));
    }
    
    /**
     * 系统通知（目前只有退货的消息）
     * @param pageIndex
     * @param pageSize
     * @param type
     * @param request
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @RequestMapping(value = "/notice/multi/list", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String multi(String pageIndex, String pageSize, String readStatus, String type, HttpServletRequest request){
    	String useragent = request.getHeader("User-Agent");
    	String plateForm = ClientUtil.isIOS(useragent) ? "IOS" : "Android";
    	String userId = request.getHeader("userid");
    	List<ShowMultiMessage> messages = msgNoticeService.findMessageByPage(userId, pageIndex, pageSize, Constants.NOTICE_TYPE_ORDER_RETURN, readStatus, plateForm);
    	ContentBuilder builder = new ContentBuilder();
    	Map<String, List<ShowMultiMessage>> map = new HashMap<String, List<ShowMultiMessage>>();
    	map.put("list", null != messages ? messages : new ArrayList<ShowMultiMessage>());
    	String result = JsonUtil.toJson(builder.buildDefSuccess(map));
    	msgNoticeService.updateBatchMessageStatusObj(userId, Constants.NOTICE_TYPE_ORDER_RETURN, Constants.NOTICE_MESSAGE_READ);
    	return result;
    }
}
