package com.shangpin.wireless.api.api2server.domain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.Constants;

public class SPGoodsDetailServerResponse {
    private String code;
    private String msg;
    private String totalcount; // 剩余库存
    private String type; // 活动or专题
    // private String rebate; // 折扣
    private String buyer; // 买手推荐
    private String firstpropname; // 第一动态属性
    private String secondpropname; // 第二动态属性
    private String shareurl; // 分享链接(网站的商品详情页)
    private String goodscode; // 商品编号
    private String cod; // 是否支持货到付款
    private String cate; // 所属类别
    private String specialinfo; // 特殊说明
    private String aftersale; // 售后服务
    private String name;// 商品名称
    private String brand;// 品牌名称
    private String priceindex;// 商品价格索引
    private String issupportmember;// 是否支持会员
    private String exchange;// 是否支持退换货
    private String topicno;// 专题编号
    private String sizeinfo;// 尺码描述
    private String sizeinfoii;// 尺码描述
    private String status;// 商品属性
    private JSONArray prices;// 商品价格
    private JSONArray pics; // 图片地址
    private JSONArray info; // 商品基本信息
    private JSONArray firstprops; // 商品属性对应的sku数组
    private String pic;// 商品图片
    private String count;// 商品购买数量
    private String isMarked;//是否已经收藏
    private String favoriteProductid;//商品id
    private JSONArray specialprice;// 特殊价格
    private String hasSize;
    private String returnChangeRemind;//温馨提示

    /**
     * 解析主站返回的json数据
     * 
     * @Author: wangwenguan
     * @CreatDate: 2012-11-2
     * @param
     * @Return
     */
    public SPGoodsDetailServerResponse json2Obj(String jsonStr) {
        JSONObject obj = JSONObject.fromObject(jsonStr);
        this.setCode(obj.getString("code"));
        this.setMsg(obj.getString("msg"));
        if (Constants.SUCCESS.equals(code)) {
            obj = JSONObject.fromObject(obj.getJSONObject("content"));
            setReturnChangeRemind(obj.getString("returnChangeRemind"));
            setIsMarked(obj.getString("ismarked"));
            setFavoriteProductid(obj.getString("favoriteProductid"));
            setHasSize(obj.getString("hassize"));
            setTotalcount(obj.getString("totalcount"));
            setType(obj.getString("type"));
            // setRebate(obj.getString("rebate"));
            setBuyer(obj.getString("buyer"));
            setFirstpropname(obj.getString("firstpropname"));
            setSecondpropname(obj.getString("secondpropname"));
            String shareUrl = obj.getString("shareurl")== null ? "" : obj.getString("shareurl");
             if(obj.getString("cate") != null){
                if(obj.getString("cate").startsWith("A01")){
                    shareUrl = shareUrl.replace("/men/product", "/women/product");
                }else if(obj.getString("cate").startsWith("A02")){
                    shareUrl = shareUrl.replace("/women/product", "/men/product");
                }
            }
            setShareurl(shareUrl);
            setGoodscode(obj.getString("goodscode"));
            setCod(obj.getString("cod"));
            setCate(obj.getString("cate"));
            setSpecialinfo(obj.getString("specialinfo"));
            setAftersale(obj.getString("aftersale"));
            setName(obj.getString("name"));
            setBrand(obj.getString("brand"));
            setPriceindex(obj.getString("priceindex"));
            setIssupportmember(obj.getString("issupportmember"));
            setExchange(obj.getString("exchange"));
            setTopicno(obj.getString("topicno"));
            setSizeinfo(obj.getString("sizeinfo"));
            JSONObject sizeinfoiiObj = obj.getJSONObject("sizeinfoii");//尺码
            StringBuffer sizeBuffer=new StringBuffer("");
            if(sizeinfoiiObj!=null){                
                JSONArray productindexArr=sizeinfoiiObj.getJSONArray("productindex");
                JSONObject productdetailsizeObj=sizeinfoiiObj.getJSONObject("productdetailsize");
                if(productindexArr.size()>0||productdetailsizeObj.getJSONArray("table").size()>0||(!"".equals(productdetailsizeObj.getString("pic")))||productdetailsizeObj.getJSONArray("sizepiclist").size()>0){
                    setHasSize("1");//兼容箱包尺码为空
                    sizeBuffer.append("<div style=\"margin-top:0px\" class=\"alContent\">");
                    if(productindexArr.size()>0){   
                        sizeBuffer.append("<section class=\"spSizeInfo\">");
                        sizeBuffer.append("<h2>商品指数</h2>");
                        for(int i=0;i<productindexArr.size();i++){
                            sizeBuffer.append("<dl>");
                            sizeBuffer.append("<dt>");
                            sizeBuffer.append(""+productindexArr.getJSONObject(i).getString("title"));
                            sizeBuffer.append("</dt>");
                            sizeBuffer.append("<dd>");
                            JSONArray valuesArr=productindexArr.getJSONObject(i).getJSONArray("values");
                            int current=Integer.parseInt(productindexArr.getJSONObject(i).getString("current"));
                            if(valuesArr.size()>0){
                                for(int j=0;j<valuesArr.size();j++){
                                    if(j==current){
                                        sizeBuffer.append("<em>");
                                        sizeBuffer.append(""+valuesArr.getJSONObject(j).getString("name"));
                                        sizeBuffer.append("</em>");
                                    }else{
                                        sizeBuffer.append("<span>");
                                        sizeBuffer.append(""+valuesArr.getJSONObject(j).getString("name"));
                                        sizeBuffer.append("</span>");
                                    }
                                }
                                sizeBuffer.append("</dd>");
                            }
                            sizeBuffer.append("</dl>");             
                        }
                        sizeBuffer.append(" </section>");
                    }
                    String pic="";
                    Boolean tableflag=false;
                    if(productdetailsizeObj!=null){
                        pic=productdetailsizeObj.getString("pic");
                        JSONArray tablesArr=productdetailsizeObj.getJSONArray("table");
                        if(!"".equals(tablesArr)&&tablesArr.size()>0){
                            tableflag=true;
                            int tablessize=tablesArr.size();
                            int tsize=tablesArr.getJSONArray(0).size();
                            int boxW = (tablessize-1)*65+95;
                            //int boxW=355;
                            String boxWs=boxW+"px";
                            sizeBuffer.append("<section class=\"spSizeInfo\">");
                            if(!"".equals(pic)){
                                sizeBuffer.append("<h2>商品详细尺码（测量单位：CM）　<a href=\"#size\">测量指南</a></h2>");
                            }else{
                                sizeBuffer.append("<h2>商品详细尺码（测量单位：CM）</h2>");
                            }
                            sizeBuffer.append("<ul class=\"tab_content\" id=\"tabContent\">");
                            if(boxW>320){
                                sizeBuffer.append("<li id=\"sizeli\" style=\"width:"+boxWs+"\" >");
                                sizeBuffer.append("<table id=\"tab\" style=\"width:"+boxWs+"\" >"); 
                            }else{
                                sizeBuffer.append("<li id=\"sizeli\">");
                                sizeBuffer.append("<table id=\"tab\">");    
                            }
                            for(int i=0;i<tsize;i++){
                                sizeBuffer.append("<tr align=\"center\">");
                                for(int j=0;j<tablessize;j++){
                                    if(j==0){
                                        sizeBuffer.append("<td>");
                                        sizeBuffer.append(""+tablesArr.getJSONArray(j).get(i));
                                        sizeBuffer.append("</td>");
                                    }else{
                                        if(boxW>320){
                                            sizeBuffer.append("<td>");
                                            sizeBuffer.append(""+tablesArr.getJSONArray(j).get(i));
                                            sizeBuffer.append("</td>");
                                        }else{
                                            sizeBuffer.append("<td style=\"width:auto\">");
                                            sizeBuffer.append(""+tablesArr.getJSONArray(j).get(i));
                                            sizeBuffer.append("</td>");
                                        }
                                    }
                                    
                                }
                                sizeBuffer.append("</tr>");
                            }
                            sizeBuffer.append("</table>");
                            sizeBuffer.append("</li>");
                            sizeBuffer.append("</ul>");
                            sizeBuffer.append("</section>");
                        }                       
                        if(!tableflag){
                            JSONArray sizepiclistArr=productdetailsizeObj.getJSONArray("sizepiclist");
                            if(!"".equals(sizepiclistArr)&&sizepiclistArr.size()>0){
                                sizeBuffer.append("<section class=\"spSizeInfo\">");
                                sizeBuffer.append("<h2>商品详细尺码（测量单位：CM)</h2>");
                                for(int i=0;i<sizepiclistArr.size();i++){
                                    sizeBuffer.append(" <img  src=\""+Constants.BASE_M_SHANGPIN_URL +"styles/shangpin/images/e.gif\" lazy=\""+sizepiclistArr.get(i).toString()+"\" width=\"320\">");
                                }
                                sizeBuffer.append("</section>");
                            }
                            
                        }
                        
                    }
                    if(!"".equals(pic)){
                        sizeBuffer.append(" <section id=\"pic\" class=\"spSizeInfo\">");
                        sizeBuffer.append("<a id=\"size\" name=\"size\"></a>");
                        sizeBuffer.append("<h2>测量指南</h2>");
                        sizeBuffer.append("<img  src=\""+pic+"\" width=\"320\">");
                        sizeBuffer.append(" </section>");
                    }
                    sizeBuffer.append("</div>");
                }
            }
            setSizeinfoii(sizeBuffer.toString());
            setStatus(obj.getString("status"));
            setPrices(obj.getJSONArray("prices"));
            setPics(obj.getJSONArray("pics"));
            setInfo(obj.getJSONArray("info"));
            setFirstprops(obj.getJSONArray("firstprops"));
            setSpecialprice(obj.getJSONArray("specialprice"));
        }
        return this;
    }

    /**
     * 解析主站返回的简要的商品json数据
     * 
     */
    public JSONObject json2SummaryObj(String jsonStr) {
        JSONObject obj = JSONObject.fromObject(jsonStr);
        this.setCode(obj.getString("code"));
        this.setMsg(obj.getString("msg"));
        JSONObject content = new JSONObject();
        if (Constants.SUCCESS.equals(code)) {
            obj = JSONObject.fromObject(obj.getJSONObject("content"));
            content.put("totalcount", obj.getString("totalcount"));
            content.put("goodscode", obj.getString("goodscode"));
            content.put("cod", obj.getString("cod"));
            // content.put("cate", cate);
            content.put("name", obj.getString("name"));
            content.put("brand", obj.getString("brand"));
            content.put("priceindex", obj.getString("priceindex"));
            content.put("topicno", obj.getString("topicno"));
            content.put("status", obj.getString("status"));
            content.put("prices", obj.getJSONArray("prices") == null ? "[]" : obj.getJSONArray("prices").toString());
            String picstr = "";
            if (null != obj.getJSONArray("pics") && obj.getJSONArray("pics").size() > 0) {
                picstr = obj.getJSONArray("pics").get(0).toString();
            }
            content.put("pic", picstr);
        }
        return content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoodscode() {
        return goodscode;
    }

    public void setGoodscode(String goodscode) {
        this.goodscode = goodscode;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public String getFirstpropname() {
        return firstpropname;
    }

    public void setFirstpropname(String firstpropname) {
        this.firstpropname = firstpropname;
    }

    public String getSecondpropname() {
        return secondpropname;
    }

    public void setSecondpropname(String secondpropname) {
        this.secondpropname = secondpropname;
    }

    public String getSpecialinfo() {
        return specialinfo;
    }

    public void setSpecialinfo(String specialinfo) {
        this.specialinfo = specialinfo;
    }

    public String getAftersale() {
        return aftersale;
    }

    public void setAftersale(String aftersale) {
        this.aftersale = aftersale;
    }

    public JSONArray getPics() {
        return pics;
    }

    public void setPics(JSONArray pics) {
        this.pics = pics;
    }

    public JSONArray getInfo() {
        return info;
    }

    public void setInfo(JSONArray info) {
        this.info = info;
    }

    public JSONArray getFirstprops() {
        return firstprops;
    }

    public void setFirstprops(JSONArray firstprops) {
        this.firstprops = firstprops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPriceindex() {
        return priceindex;
    }

    public void setPriceindex(String priceindex) {
        this.priceindex = priceindex;
    }

    public String getIssupportmember() {
        return issupportmember;
    }

    public void setIssupportmember(String issupportmember) {
        this.issupportmember = issupportmember;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTopicno() {
        return topicno;
    }

    public void setTopicno(String topicno) {
        this.topicno = topicno;
    }

    public String getSizeinfo() {
        return sizeinfo;
    }

    public void setSizeinfo(String sizeinfo) {
        this.sizeinfo = sizeinfo;
    }

    public String getSizeinfoii() {
        return sizeinfoii;
    }

    public void setSizeinfoii(String sizeinfoii) {
        this.sizeinfoii = sizeinfoii;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONArray getPrices() {
        return prices;
    }

    public void setPrices(JSONArray prices) {
        this.prices = prices;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public JSONArray getSpecialprice() {
        return specialprice;
    }

    public void setSpecialprice(JSONArray specialprice) {
        this.specialprice = specialprice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(String isMarked) {
        this.isMarked = isMarked;
    }

    public String getFavoriteProductid() {
        return favoriteProductid;
    }

    public void setFavoriteProductid(String favoriteProductid) {
        this.favoriteProductid = favoriteProductid;
    }

    public String getHasSize() {
        return hasSize;
    }

    public void setHasSize(String hasSize) {
        this.hasSize = hasSize;
    }

    public String getReturnChangeRemind() {
        return returnChangeRemind;
    }

    public void setReturnChangeRemind(String returnChangeRemind) {
        this.returnChangeRemind = returnChangeRemind;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
    
    
    
}
