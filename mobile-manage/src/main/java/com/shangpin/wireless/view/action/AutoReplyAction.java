package com.shangpin.wireless.view.action;

import com.shangpin.wechat.service.MaterialService;
import com.shangpin.wechat.utils.common.CacheUtil;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.AutoReply;
import com.shangpin.wireless.domain.Reply;
import com.shangpin.wireless.util.HqlHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by cuibinqiang on 2015/12/3.
 */
@Controller
@Scope("prototype")
public class AutoReplyAction extends BaseAction<AutoReply> {

    protected final Log log = LogFactory.getLog(AutoReplyAction.class);

    @Autowired
    private MaterialService materialService;

    private List<AutoReply> autoReplyList;
    private AutoReply autoReplyBeadd;


    private static String BEADD_KEYWORD = "[{\"keyword\":\"**关注**\",\"mode\":\"1\"}]";
    private static String BEADD_TYPE = "1";
    private static String BEADD_PREFIX = "beadd";
    private static String COMMON_KEYWORD = "[{\"keyword\":\"**default**\",\"mode\":\"1\"}]";
    private static String COMMON_TYPE = "2";
    private static String COMMON_PREFIX = "common";
    private static String KEYWORDS_TYPE = "3";
    private static String KEYWORDS_PREFIX = "keywords";
    private static String CACHE_KEY = "weChatAutoReplyB70E3B9B0F77F97F2306EBB19DADC40B";

    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型

    private int page;
    private int count;

    /**
     * 删除缓存
     */
    private void removeCache(){
        CacheUtil.deleteKey(CACHE_KEY);
    }



    /**
     * 查询被添加回复
     * @return
     */
    public String beadd(){
        get(BEADD_TYPE);
        return BEADD_PREFIX;
    }
    /**
     * 查询自动回复
     * @return
     */
    public String common(){
        get(COMMON_TYPE);
        return COMMON_PREFIX;
    }

    public void get(String type){
        HqlHelper hqlHelper = new HqlHelper(AutoReply.class);
        hqlHelper.addWhereCondition("type = '" + type + "'");
        hqlHelper.addWhereCondition("status = '1'");

        try {
            autoReplyBeadd = autoReplyService.getByCondition(hqlHelper);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if("2".equals(autoReplyBeadd.getReplyList().get(0).getType())){
//            String content = materialService.getMaterial(autoReplyBeadd.getReplyList().get(0).getContent());
//            log.info("查询多媒体接口返回:"+content);
//        }

    }
    /**
     * 保存/更新被添加回复
     * @return
     */
    public String beaddSave(){
        save(BEADD_TYPE,BEADD_KEYWORD);
        return BEADD_PREFIX;
    }
    /**
     * 保存/更新自动回复
     * @return
     */
    public String commonSave(){
        save(COMMON_TYPE,COMMON_KEYWORD);
        return COMMON_PREFIX;
    }

    public void save(String type,String keywords){
        String reply = model.getReply();
        try {

            HqlHelper hqlHelper = new HqlHelper(AutoReply.class);
            hqlHelper.addWhereCondition("type = '" + type + "'");
            hqlHelper.addWhereCondition("status = '1'");
            autoReplyBeadd = autoReplyService.getByCondition(hqlHelper);

            if(autoReplyBeadd !=null){
                model = autoReplyBeadd;
                model.setReply(reply);
                autoReplyService.update(model);
                autoReplyBeadd = model;
            }else{
                model.setKeywords(keywords);
                model.setReply(reply);
                model.setType(type);
                model.setStatus("1");
                model.setMatchType("1");
                model.setReplyType("1");
                autoReplyService.save(model);
                autoReplyBeadd = model;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        removeCache();
    }

    /**
     * 删除被添加回复
     * @return
     */
    public String beaddDel(){
        del(BEADD_TYPE);
        return BEADD_PREFIX;
    }
    /**
     * 删除自动回复
     * @return
     */
    public String commonDel(){
        del(COMMON_TYPE);
        return COMMON_PREFIX;
    }

    public void del(String type){
        try {
            HqlHelper hqlHelper = new HqlHelper(AutoReply.class);
            hqlHelper.addWhereCondition("type = '" + type + "'");
            hqlHelper.addWhereCondition("status = '1'");
            autoReplyBeadd = autoReplyService.getByCondition(hqlHelper);

            if(autoReplyBeadd !=null){
                model = autoReplyBeadd;
                model.setStatus("0");
                autoReplyService.update(model);
                autoReplyBeadd.setReply(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        removeCache();
    }

    /*********************************************关键字回复***********************************************************/
    public String keywords(){
        HqlHelper hqlHelper = new HqlHelper(AutoReply.class);
        hqlHelper.addWhereCondition("type = '" + KEYWORDS_TYPE + "'");
        hqlHelper.addWhereCondition("status = '1'");
        hqlHelper.addOrderByProperty("id", false);

        try {
            autoReplyList  = autoReplyService.getListByCondition(hqlHelper);

            //统计类型数量
            for(AutoReply autoReply : autoReplyList){
                int textNum = 0;
                int picNum = 0;
                for (Reply reply : autoReply.getReplyList()) {
                    if("1".equals(reply.getType())){
                        textNum ++;
                    }
                    if("2".equals(reply.getType())){
                        picNum ++;
                    }
                }
                autoReply.setTextNum(textNum);
                autoReply.setPicNum(picNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return KEYWORDS_PREFIX;
    }

    public String keywordsSave(){

        Long id = model.getId();//request.getParameter("id");
        String name = model.getName();//request.getParameter("name");
        String keywords = model.getKeywords();//request.getParameter("keywords");
        String replyType = model.getReplyType();//request.getParameter("replyType");
        String replys = model.getReply();//request.getParameter("replys");

        try {
            //保存
            if(id.longValue() == 0){
                model.setName(name);
                model.setKeywords(keywords);
                model.setReply(replys);
                model.setStatus("1");
                model.setType("3");
                model.setReplyType(replyType);
                autoReplyService.save(model);

            //更新
            }else{

                //确认数据存在
                AutoReply autoReply = autoReplyService.getById(Long.valueOf(id));
                if(autoReply == null){
                    log.error("不存在此id的规则,要保存的id="+id+",name="+name);
                    return null;
                }

                model.setId(Long.valueOf(id));
                model.setName(name);
                model.setKeywords(keywords);
                model.setReply(replys);
                model.setStatus("1");
                model.setType("3");
                model.setReplyType(replyType);
                autoReplyService.update(model);
                log.info("已保存关键字规则,id="+id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        removeCache();

        return keywords();
    }

    public String keywordsDel(){

        Long id = model.getId();//request.getParameter("id");

        try {
            //为0不做处理
            if(id.longValue() == 0){
            	return keywords();
            }
            
            //确认数据存在
            AutoReply autoReply = autoReplyService.getById(Long.valueOf(id));
            if(autoReply == null){
                log.error("不存在此id的规则,要删除的id="+id);
                return null;
            }
            autoReplyService.deletebyId(Long.valueOf(id));
            log.info("已删除关键字规则,id="+id);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        removeCache();

        return keywords();
    }

/*********** 批量获取多媒体   ********************************************************************/

    /**
     * 批量获取多媒体
     * @return
     */
    public String getMaterialList(){

        String type = model.getType();//request.getParameter("type");

        if (count > 20) {
            count = 20;
        }

        int offset = (page-1) * count;

        String result = materialService.getMaterialList(type,offset, count);
//        result = "{\"item\":[{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGqINe05XwoujYunnzrLVgI8\",\"name\":\"QQ截图20151215172742.jpg\",\"update_time\":1450172063,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqKrhJhqHDgY331Q25I7PYyEWmfxJjzlVe7rYBSGVNN8vca2CPzXPAbw\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGiIVZCA9SLFZ8t7uWekTQ5U\",\"name\":\"QQ截图20151215171429.jpg\",\"update_time\":1450172005,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqjJiaZKSibrotyuicOMcuheia2icz8pXcc9XNQIcAbT1DydbTJONunhUiayAg\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGhle0gzF7LqVJSP1pMXqa30\",\"name\":\"QQ截图20151215173145.jpg\",\"update_time\":1450171988,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqCWwvgCuRCE6iblia07mgicIFBj8WbxmA5AZefNWUwcQibVMsgGzicIRY9lg\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGnlP0TiKLPqlwcvZI94xCZI\",\"name\":\"QQ截图20151215173035.jpg\",\"update_time\":1450171918,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBq6MbwxsxQgXPooyLTjA46N85jUJYakolUy45cFvcUvGeBVib7QneSUmg\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGuJf_KOsNGTrEd49TDx0XW4\",\"name\":\"QQ截图20151215172848.jpg\",\"update_time\":1450171815,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqDGPicJficMIonFsE16r7mujyGbZicdxN5Z77Lmhr2ic57XFQ7TDJaxuvww\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGjNkwjtpkGJz1b6RwPKH_ss\",\"name\":\"QQ截图20151215172742.jpg\",\"update_time\":1450171811,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqKrhJhqHDgY331Q25I7PYyEWmfxJjzlVe7rYBSGVNN8vca2CPzXPAbw\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGqQLLsvOXRsv6Z6CxkXA9LY\",\"name\":\"QQ截图20151215172742.jpg\",\"update_time\":1450171741,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqKrhJhqHDgY331Q25I7PYyEWmfxJjzlVe7rYBSGVNN8vca2CPzXPAbw\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGriuOBGfL1Ra-1Mhz2HP4lo\",\"name\":\"QQ截图20151215171429.jpg\",\"update_time\":1450171186,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqjJiaZKSibrotyuicOMcuheia2icz8pXcc9XNQIcAbT1DydbTJONunhUiayAg\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGusTLi_9HohcjMFbMfUm6wc\",\"name\":\"QQ截图20151215171654.jpg\",\"update_time\":1450171139,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqUDgwIfJVrd27siccNJLAc26llwWBia00CoiakJdyACu3CjPRSEwacRg7Q\\/0?wx_fmt=jpeg\"},{\"media_id\":\"zM4xOg6oWRb6J2KoBt2HGpG0skLLvG9CpCcdQJOCoXk\",\"name\":\"QQ截图20151215171654.jpg\",\"update_time\":1450171094,\"url\":\"https:\\/\\/mmbiz.qlogo.cn\\/mmbiz\\/uSsXTbwzqVfDZnP3XyR9VDNU8BZBwmBqtAzWSrR0FDb1SKuwdpbmj17SKjwcVbl8TOwV0F8LFAYZZsgE4WxzMw\\/0?wx_fmt=jpeg\"}],\"total_count\":6740,\"item_count\":10}\n";
        OutPrint(result);
        System.out.println(result);
        return null;
    }

    /**
     * 多媒体上传
     * @return
     */
    public String uploadMaterial(){


        if(image == null){
            log.info("上传多媒体为空");
            return null;
        }

        String result  = materialService.addMaterial("image",image);

        log.info("上传多媒体接口返回:"+result);

        return null;
    }







    public List<AutoReply> getAutoReplyList() {
        return autoReplyList;
    }

    public void setAutoReplyList(List<AutoReply> autoReplyList) {
        this.autoReplyList = autoReplyList;
    }

    public AutoReply getAutoReplyBeadd() {
        return autoReplyBeadd;
    }

    public void setAutoReplyBeadd(AutoReply autoReplyBeadd) {
        this.autoReplyBeadd = autoReplyBeadd;
    }


    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
