package com.shangpin.wireless.view.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.JSONUtils;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;
import com.shangpin.wechat.bo.base.QueryBatchCardResult;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.cfg.Configuration;
import com.shangpin.wireless.domain.PageBean;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.domain.WechatCreateCard;
import com.shangpin.wireless.domain.WechatCreateCardBaseInfoDateInfo;

/**
 * 微信管理Action
 * 
 * @Author zrs
 * @CreatDate 
 */
@Controller
@Scope("prototype")
public class WechatAction extends BaseAction<WechatCreateCard> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(WechatAction.class);
	private int rows = Configuration.getPageSize();
	private String cardType;
	private String cardId;
	private List<String> satusList;
	private String cardDetail;

	@Autowired
	private WeChatPublicService weChatPublicService;

	/** 添加页面 */
	public String addUI() {
		return "addUI";
	}

	/**
	 * 添加卡券
	 * @return
	 */
	public String add() {
		
		if(StringUtil.isBlank(cardType)){
			return null;
		}
		try {
			//将时间转换成秒数
			if("DATE_TYPE_FIX_TIME_RANGE".equals(model.getBase_info().getDate_info().getType())){
				WechatCreateCardBaseInfoDateInfo dateInfo = model.getBase_info().getDate_info();
				dateInfo.setBegin_timestamp(DateUtils.strToDate(dateInfo.getBegin_timestamp()+" 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime()/1000+"");
				dateInfo.setEnd_timestamp(DateUtils.strToDate(dateInfo.getEnd_timestamp()+" 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime()/1000+"");
			}			
					
			String baseInfoJson = JSONUtils.obj2json(model);
			String cardTypeLowerCase = cardType.toLowerCase();
			
			String postJson = "{\"card\":{\"card_type\": \"" + cardType + "\",\"" + cardTypeLowerCase + "\":" + baseInfoJson + "}}";

			log.info("WechatAction-add():postJson=" + postJson);
			
			String accessToken = weChatPublicService.getToken();
			cardId = weChatPublicService.createCard(accessToken, postJson);

			log.info("WechatAction-add():cardId=" + cardId);
		} catch (Exception e) {
			log.error("WechatAction-add():e=" + e);
		}
		return "add";
	}
	
	/**
	 * 获取门店列表
	 * @return
	 */
	public String getPoiList() {

		try {
			log.info("WechatAction-getPoiList():pageNum=" + pageNum + ",rows=" + rows);
			ReturnObject returnObject = new ReturnObject();			
			String accessToken = weChatPublicService.getToken();
			String poiJson = weChatPublicService.queryPoiListWithJson(accessToken, pageNum, rows);
			log.info("WechatAction-getPoiList():poiJson=" + poiJson);
			returnObject.setReturnInfo(poiJson);			
			ServletActionContext.getResponse().getWriter().print(JsonUtil.toJson(returnObject));
		} catch (Exception e) {
			log.error("WechatAction-getPoiList() " + e);
		}
		return null;
	}
	/**
	 * 获取卡券列表
	 * @return
	 */
	public String list(){
		try {
			log.info("WechatAction-list():pageNum=" + pageNum + ",rows=" + rows + ",satusList=" + satusList);
			String accessToken = weChatPublicService.getToken();
			QueryBatchCardResult queryBatchCardResult = weChatPublicService.queryBatchCard(accessToken, pageNum, rows, satusList);
			 
			PageBean pageBean = new PageBean(pageNum,rows,Integer.valueOf(queryBatchCardResult.getTotal_num()),queryBatchCardResult.getCard_id_list());
			ActionContext.getContext().getValueStack().push(pageBean);						
		} catch (Exception e) {
			log.error("WechatAction-list() " + e);
		}
		return "list";
	}
	
	/**
	 * 获取卡券详情
	 * @return
	 */
	public String detail(){
		try {
			log.info("WechatAction-detail():cardId=" + cardId);
			String accessToken = weChatPublicService.getToken();
			cardDetail = weChatPublicService.queryCardDetailByCardIdWithJson(accessToken, cardId);		
			log.info("WechatAction-detail():cardDetail=" + cardDetail);
		} catch (Exception e) {
			log.error("WechatAction-detail() " + e);
		}
		return "detail";
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public List<String> getSatusList() {
		return satusList;
	}

	public void setSatusList(List<String> satusList) {
		this.satusList = satusList;
	}

	public String getCardDetail() {
		return cardDetail;
	}

	public void setCardDetail(String cardDetail) {
		this.cardDetail = cardDetail;
	}

	
}
