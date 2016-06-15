package com.shangpin.wireless.api.api2client.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.shangpin.base.utils.BrandUtil;
import com.shangpin.wireless.api.util.AppUrlRedirectUtil;

/**
 * 商城
 * @author wangfeng
 *
 */
public class MallResponse{
		
    public String obj2Json(JSONObject galleryObj, JSONObject categoryObj, JSONObject brandObj, JSONObject promotionObj) {
        JSONObject apiRefObj=new JSONObject();
        JSONArray array=null;
        apiRefObj.put("code", "0");
        apiRefObj.put("msg", "");
        JSONObject contentObj=new JSONObject();
        JSONObject gContent=galleryObj.getJSONObject("content");
        JSONArray galleryNewArry=new JSONArray();
        if(gContent!=null&&!"{}".equals(gContent.toString())){
            array=gContent.getJSONArray("gallery");
            JSONObject galleryNewObj=null;
            JSONObject galleryOldObj=null;
            if (array!=null&&array.size()>0) {
                for (int i = 0; i < array.size(); i++) {
                    galleryOldObj=array.getJSONObject(i);
                    galleryNewObj=new JSONObject();
                    galleryNewObj.put("name", galleryOldObj.getString("name"));
                    galleryNewObj.put("pic", galleryOldObj.getString("pic"));
                    galleryNewObj.put("refContent", galleryOldObj.getString("refContent"));
                    galleryNewObj.put("type", galleryOldObj.getString("type"));
                    galleryNewArry.add(galleryNewObj);
                }
            }        
        }
        
        contentObj.put("gallery", galleryNewArry);
        
        JSONObject cObj=new JSONObject();
        JSONObject cContent=categoryObj.getJSONObject("content");
        if(cContent!=null&&!"{}".equals(cContent.toString())){
            cObj.put("categoryImage", cContent.getString("categoryImage"));
            array=cContent.getJSONArray("categoryList");
            JSONObject cateOldObj=null;
            JSONObject cateNewObj=null;
            JSONArray cateNewArray=new JSONArray();
            String id=null;
            for (int i = 0; i < array.size(); i++) {
                cateOldObj=array.getJSONObject(i);
                cateNewObj=new JSONObject();
                cateNewObj.put("name", cateOldObj.getString("name"));
                id=cateOldObj.getString("id");
                /*if(i==1){//用于客户端测试，暂时写死一个品类
                    cateNewObj.put("type", "8");
                    id="A01B03|A02B03";
                    cateNewObj.put("name", "鞋靴"); 
                }else{    */            
                cateNewObj.put("type", "2");
                cateNewObj.put("refContent", id);
                cateNewArray.add(cateNewObj);
            }
            cObj.put("categoryList", cateNewArray);
        }                
        contentObj.put("category", cObj);
        JSONObject brandOldObj=null;
        JSONObject brandNewObj=new JSONObject();
        JSONObject bContent=brandObj.getJSONObject("content");
        JSONArray brandNewArray=new JSONArray();
        if(bContent!=null&&!"{}".equals(bContent.toString())){
            array= bContent.getJSONArray("brandList");
            JSONArray listArray=null;
            for (int i = 0; i < array.size(); i++) {
                brandOldObj=array.getJSONObject(i);
                brandNewObj.put("name", brandOldObj.getString("name"));
                JSONArray oldListArry=brandOldObj.getJSONArray("list");                
                JSONObject singleOld=null;
                JSONObject singleNew=null;
                listArray=new JSONArray();
                for (int j = 0; j < oldListArry.size(); j++) {
                    singleNew=new JSONObject();
                    singleOld=oldListArry.getJSONObject(j);
                    singleNew=JSONObject.fromObject(BrandUtil.getBrandJson(singleOld.toString()));
                    listArray.add(singleNew);
                }
                brandNewObj.put("list", listArray);
                brandNewArray.add(brandNewObj);
            }
        }
       
        contentObj.put("brandList", brandNewArray);
        
        JSONObject promotionOld=null;
        JSONObject promotionNew=null;
        JSONArray pArray=new JSONArray();
        JSONObject pContent=promotionObj.getJSONObject("content");
        if(pContent!=null&&!"{}".equals(pContent.toString())){
            array=pContent.getJSONArray("promotion");
            for (int i = 0; i < array.size(); i++) {
                promotionNew=new JSONObject();
                promotionOld=array.getJSONObject(i);
                promotionNew.put("name", promotionOld.getString("name"));
                promotionNew.put("pic", promotionOld.getString("pic"));
                promotionNew.put("type", AppUrlRedirectUtil.appType(promotionOld.getString("link")));               
                promotionNew.put("refContent", AppUrlRedirectUtil.appUrl(promotionOld.getString("link")));
                pArray.add(promotionNew);
            }
        }
       
        contentObj.put("promotion", pArray);
        apiRefObj.put("content", contentObj);
        return apiRefObj.toString();
    }
}
