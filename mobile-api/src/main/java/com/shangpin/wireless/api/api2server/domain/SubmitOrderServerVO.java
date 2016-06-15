package com.shangpin.wireless.api.api2server.domain;


import com.google.gson.reflect.TypeToken;
import com.shangpin.biz.bo.SupplierSkuNoInfo;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.api2client.domain.SubmitOrderVO;
import com.shangpin.wireless.api.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 提交订单服务端数据对象
 * 
 * @Author: wangfeng
 * @CreatDate: 2014-04-24
 * @Return
 */
public class SubmitOrderServerVO {

    private String code;
    private String msg;
    private SubmitOrderVO submitOrderVO;

    /**
     * 返给客户端的json数据
     * 
     * @Author: wangfeng
     * @CreatDate: 2014-04-24
     * @Return
     */
    public SubmitOrderServerVO jsonObj(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        JSONObject content = null;
        String msg = obj.getString("msg");
        this.setCode(obj.getString("code"));
        this.setMsg(msg == null ? "" : msg);
        if ("0".equals(code)) {
            submitOrderVO = new SubmitOrderVO();
            content = JSONObject.fromObject(obj.getString("content"));
            submitOrderVO.setOrderid(content.getString("orderid"));
            submitOrderVO.setDate(content.getString("date"));
            String amount = content.getString("amount");
            String carrageAmount=content.getString("carriage");
            Double payAmount=Double.parseDouble(carrageAmount)+Double.parseDouble(amount);
            submitOrderVO.setAmount(StringUtil.cutOffDecimal(String.valueOf(payAmount)));
            
//            String onlineAmount = content.getString("onlineamount");
//            submitOrderVO.setOnlineamount(StringUtil.cutOffDecimal(onlineAmount == null ? "0" : onlineAmount));
            submitOrderVO.setCod(content.getString("cod"));
            submitOrderVO.setCodmsg(content.getString("codmsg"));
            submitOrderVO.setSkucounts(content.getString("skucounts"));
            submitOrderVO.setObjectcounts(content.getString("objectcounts"));
            submitOrderVO.setGiftcard(content.getString("giftcard"));
            submitOrderVO.setCarriage(content.getString("carriage"));
            submitOrderVO.setSystime(content.getString("systime")==null?"":content.getString("systime"));
            submitOrderVO.setExpiretime(content.getString("expiretime")==null?"":content.getString("expiretime"));
            String giftcardbalance = content.getString("giftcardbalance");
            submitOrderVO.setGiftcardbalance(StringUtil.cutOffDecimal(giftcardbalance));
            submitOrderVO.setOrderno(content.get("orderno")==null?"":content.getString("orderno"));
            submitOrderVO.setPostarea(content.get("postarea")==null?"":content.getString("postarea"));
            String supplierskuno=content.get("supplierskuno")==null?"":content.getString("supplierskuno");

            //2.9.12新增参数开始
            if(content.get("payCategory") != null)
            submitOrderVO.setPayCategory(content.getString("payCategory"));
            if(content.get("internalAmount")!=null)
            submitOrderVO.setInternalAmount(content.getString("internalAmount"));
            //2.9.12新增参数结束

            if(!StringUtils.isEmpty(supplierskuno)){
            	List<SupplierSkuNoInfo> result = JsonUtil.fromJson(supplierskuno, new TypeToken<List<SupplierSkuNoInfo>>() {
	            }.getType());
            	submitOrderVO.setSupplierskuno(result);
            }
           
           
        }
        this.setSubmitOrderVO(submitOrderVO);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SubmitOrderVO getSubmitOrderVO() {
        return submitOrderVO;
    }

    public void setSubmitOrderVO(SubmitOrderVO submitOrderVO) {
        this.submitOrderVO = submitOrderVO;
    }
}
