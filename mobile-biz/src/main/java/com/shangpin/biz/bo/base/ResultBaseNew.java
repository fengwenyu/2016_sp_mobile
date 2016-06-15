package com.shangpin.biz.bo.base;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ResultBaseNew implements Serializable{
	private static final long serialVersionUID = 696519328694195819L;
	// 成功标准
    public static final String CODE = "0";
    public static final String SUCCESS = "success";
    
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private String code;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object content;
    
    /**
     * 构造方法
     * @param code
     * @param msg
     * @param content
     */
    public ResultBaseNew() {
    }
    
    public ResultBaseNew(Object content) {
        this.code = CODE;
        this.msg = SUCCESS;
        this.content = content;
    }

    public ResultBaseNew(String code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    /**
     * 
     * @Title: build自己构建一个自定义状态码的对象
     * @Description: TODO
     * @param code
     * @param msg
     * @param content
     * @return ReturnBaseNew
     * @author fengwenyu 
     * @date 2015年12月1日
     */
    public static ResultBaseNew build(String code, String msg) {
        return new ResultBaseNew(code, msg, null);
    }
    public static ResultBaseNew build(String code, String msg, Object content) {
        return new ResultBaseNew(code, msg, content);
    }

    public static ResultBaseNew success(Object content) {
        return new ResultBaseNew(content);
    }
    //只返回两个参数code和message
    public static ResultBaseNew emptyContent() {
        return new ResultBaseNew(null);
    }

    /**
     * 将json结果集转化为ReturnBaseNew对象
     * 
     * @param jsoncontent json数据
     * @param clazz ReturnBaseNew中的object类型
     * @return
     */
    public static ResultBaseNew formatToPojo(String jsoncontent, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsoncontent, ResultBaseNew.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsoncontent);
            JsonNode content = jsonNode.get("content");
            Object obj = null;
            if (clazz != null) {
                if (content.isObject()) {
                    obj = MAPPER.readValue(content.traverse(), clazz);
                } else if (content.isTextual()) {
                    obj = MAPPER.readValue(content.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").textValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @Title: get\set方法
     * @Description: TODO
     * @throws 
     * @author fengwenyu 
     * @date 2015年12月1日
     */
    
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static ResultBaseNew format(String json) {
        try {
            return MAPPER.readValue(json, ResultBaseNew.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsoncontent json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResultBaseNew formatToList(String jsoncontent, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsoncontent);
            JsonNode content = jsonNode.get("content");
            Object obj = null;
            if (content.isArray() && content.size() > 0) {
                obj = MAPPER.readValue(content.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").textValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    } 
    
    /**
     * 
     * @Title:返回结果是否成功
     * @Description: 
     * @author By fengwenyu
     * @Create Date 2015年12月1日
     */
    public boolean isSuccess() {
        if (this.getCode() == null || !CODE.equals(this.getCode())) {
            return false;
        }
        return true;
    }
}
