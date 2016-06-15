/**
 * create on 2011-12-7
 */
package com.shangpin.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 根据该对象可以构造Xml字符串
 * 
 * @author sunweiwei
 * @version v1.0
 * 
 */
public class XmlUtil {

	private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	private static String HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	private String name;
	private Object value;
	private Map<String, Object> attributes;
	private List<XmlUtil> subXmlObjects;

	/**
	 * 根据name构造XmlObject
	 * 
	 * @param name
	 *            生成xml时标签名，如name="html"，则生成xml为<html/>
	 */
	public XmlUtil(String name) {
		this.name = name;
	}

	/**
	 * 根据name,value构造XmlObject
	 * 
	 * @param name
	 *            生成xml时标签名，如name="html"，则生成xml为<html/>
	 * @param value
	 *            生成xml时标签值，
	 */
	public XmlUtil(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * 获得当前对象的名称
	 * 
	 * @return 返回当前对象的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置当前对象的名称
	 * 
	 * @param name
	 *            给定名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得当前对象的值
	 * 
	 * @return 返回当前对象的值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置当前对象的值
	 * 
	 * @param value
	 *            给定值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 为当前XmlObject添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void addAttribute(String name, Object value) {
		if (attributes == null) {
			attributes = new LinkedHashMap<String, Object>();
		}
		if (name != null && !name.trim().equals("") && !name.equals(this.name)) {
			attributes.put(name, value);
		}
	}

	/**
	 * 为当前XmlObject添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void setAttribute(String name, Object value) {
		addAttribute(name, value);
	}

	/**
	 * 根据属性名称获得当前XmlObject对象的属性值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性值
	 */
	public Object getAttributeValue(String name) {
		return getAttributeValue(name, null);
	}

	/**
	 * 根据属性名称获得当前XmlObject对象的属性值
	 * 
	 * @param name
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return 若属性存在，则返回属性值，否则返回默认值
	 */
	public Object getAttributeValue(String name, Object defaultValue) {
		Object value = attributes.get(name);
		return value == null ? defaultValue : value;
	}

	/**
	 * 为当前XmlObject对象添加子XmlObject对象
	 * 
	 * @param xmlObject
	 *            给定XmlObject对象
	 */
	public void addSubXmlObject(XmlUtil xmlObject) {
		if (subXmlObjects == null) {
			subXmlObjects = new ArrayList<XmlUtil>();
		}
		if (xmlObject != null) {
			subXmlObjects.add(xmlObject);
		}
	}

	public List<XmlUtil> getSubXmlObjectsByName(String name) {
		List<XmlUtil> xmlObjects = new ArrayList<XmlUtil>();
		for (XmlUtil temp : subXmlObjects) {
			if (temp.getName().equals(name)) {
				xmlObjects.add(temp);
			}
		}
		return xmlObjects;
	}

	public XmlUtil getUniqueSubXmlObjectByName(String name) {
		for (XmlUtil temp : subXmlObjects) {
			if (temp.getName().equals(name)) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * 构造当前对象的压缩XML字符串
	 * 
	 * @return XML字符串
	 */
	public String toCompactXml() {
		return HEAD + getNoHeadXml("", "");
	}

	/**
	 * 根据格式化留白("\t")和默认的行分隔符("\t")构造当前对象的XML字符串
	 * 
	 * @return XML字符串
	 */
	public String toFormatXml() {
		return HEAD + getNoHeadXml("\t", "\n");
	}

	/**
	 * 根据格式化留白和默认的行分隔符构("\n")造当前对象的XML字符串
	 * 
	 * @param blank
	 *            格式化留白
	 * @return XML字符串
	 */
	public String toFormatXml(String blank) {
		return HEAD + getNoHeadXml(blank, "\n");
	}

	/**
	 * 根据格式化留白和行分隔符构造当前对象的无头的XML字符串
	 * 
	 * @param blank
	 *            格式化留白
	 * @param split
	 *            行分隔符
	 * @return 无头的XML字符串
	 */
	private String getNoHeadXml(String blank, String split) {
		return getNoHeadXml(blank, split, 0);
	}

	private String getNoHeadXml(String blank, String split, int count) {
		String blanks = "";
		for (int i = 0; i < count; i++) {
			blanks += blank;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(count == 0 ? split : "");
		sb.append(blanks + "<" + name);
		if (attributes != null) {
			Set<Entry<String, Object>> set = attributes.entrySet();
			for (Entry<String, Object> entry : set) {
				String tempName = entry.getKey();
				Object tempValue = entry.getValue();
				if (tempName != null && tempValue != null) {
					sb.append(" " + tempName + "=\"" + tempValue + "\"");
				}
			}
		}
		if (subXmlObjects == null) {
			if (value == null) {
				sb.append("/>" + split);
			} else {
				sb.append(">");
				sb.append(value);
				sb.append("</" + name + ">" + split);
			}
		} else {
			sb.append(">" + split);
			Iterator<XmlUtil> iterator = subXmlObjects.iterator();
			count += 1;
			while (iterator.hasNext()) {
				XmlUtil xmlObject = iterator.next();
				sb.append(xmlObject.getNoHeadXml(blank, split, count));
			}
			sb.append(blanks + "</" + name + ">" + split);
		}
		return sb.toString();
	}

	/**
	 * 解析返回的结果xml,得到指定节点的值
	 * 
	 * @param xml
	 * @param element
	 *            节点名称
	 * @return
	 */
	public static String parseXml(String xml, String element) {
		String value = null;
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(in);
			Element rootElm = document.getRootElement();
			value = rootElm.elementText(element);
			return value;
		} catch (DocumentException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return value;
	}
	
	////2015-10-15 17:05:30 zrs 添加
	
	/** 
     * xml字符串转换成bean对象 
     *  
     * @param xmlStr xml字符串 
     * @param clazz 待转换的class 
     * @return 转换后的对象 
     */  
    @SuppressWarnings("rawtypes")
    public static Object xmlStrToBean(String xmlStr, Class clazz) {  
        Object obj = null;  
        try {  
            // 将xml格式的数据转换成Map对象  
            Map<String, Object> map = xmlStrToMap(xmlStr);  
            //将map对象的数据转换成Bean对象  
            obj = mapToBean(map, clazz);  
        } catch(Exception e) {  
            e.printStackTrace();  
        }  
        return obj;  
    }  
      
    /** 
     * 将xml格式的字符串转换成Map对象 
     *  
     * @param xmlStr xml格式的字符串 
     * @return Map对象 
     * @throws Exception 异常 
     */  
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> xmlStrToMap(String xmlStr) throws Exception {  
        if(StringUtil.isEmpty(xmlStr)) {  
            return null;  
        }  
        Map<String, Object> map = new HashMap<String, Object>();  
        //将xml格式的字符串转换成Document对象  
        Document doc = DocumentHelper.parseText(xmlStr);  
        //获取根节点  
        Element root = doc.getRootElement();  
        //获取根节点下的所有元素  
        List children = root.elements();  
        //循环所有子元素  
        if(children != null && children.size() > 0) {  
            for(int i = 0; i < children.size(); i++) {  
                Element child = (Element)children.get(i);  
                map.put(child.getName(), child.getTextTrim());  
            }  
        }  
        return map;  
    }  
      
    /** 
     * 将Map对象通过反射机制转换成Bean对象 
     *  
     * @param map 存放数据的map对象 
     * @param clazz 待转换的class 
     * @return 转换后的Bean对象 
     * @throws Exception 异常 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object mapToBean(Map<String, Object> map, Class clazz) throws Exception {  
        Object obj = clazz.newInstance();  
        if(map != null && map.size() > 0) {  
            for(Map.Entry<String, Object> entry : map.entrySet()) {  
                String propertyName = entry.getKey();  
                Object value = entry.getValue();  
                String setMethodName = "set"  
                        + propertyName.substring(0, 1).toUpperCase()  
                        + propertyName.substring(1);  
                Field field = getClassField(clazz, propertyName);  
                
                if(field == null){
                	continue;
                }
                
                Class fieldTypeClass = field.getType();  
                value = convertValType(value, fieldTypeClass);  
                clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);  
            }  
        }  
        return obj;  
    }  
      
    /** 
     * 将Object类型的值，转换成bean对象属性里对应的类型值 
     *  
     * @param value Object对象值 
     * @param fieldTypeClass 属性的类型 
     * @return 转换后的值 
     */  
    @SuppressWarnings("rawtypes")
    private static Object convertValType(Object value, Class fieldTypeClass) {  
        Object retVal = null;  
        if(Long.class.getName().equals(fieldTypeClass.getName())  
                || long.class.getName().equals(fieldTypeClass.getName())) {  
            retVal = Long.parseLong(value.toString());  
        } else if(Integer.class.getName().equals(fieldTypeClass.getName())  
                || int.class.getName().equals(fieldTypeClass.getName())) {  
            retVal = Integer.parseInt(value.toString());  
        } else if(Float.class.getName().equals(fieldTypeClass.getName())  
                || float.class.getName().equals(fieldTypeClass.getName())) {  
            retVal = Float.parseFloat(value.toString());  
        } else if(Double.class.getName().equals(fieldTypeClass.getName())  
                || double.class.getName().equals(fieldTypeClass.getName())) {  
            retVal = Double.parseDouble(value.toString());  
        } else {  
            retVal = value;  
        }  
        return retVal;  
    }  
  
    /** 
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类) 
     *  
     * @param clazz 指定的class 
     * @param fieldName 字段名称 
     * @return Field对象 
     */  
    @SuppressWarnings("rawtypes")
    private static Field getClassField(Class clazz, String fieldName) {  
        if( Object.class.getName().equals(clazz.getName())) {  
            return null;  
        }  
        Field []declaredFields = clazz.getDeclaredFields();  
        for (Field field : declaredFields) {  
            if (field.getName().equals(fieldName)) {  
                return field;  
            }  
        }  
  
        Class superClass = clazz.getSuperclass();  
        if(superClass != null) {// 简单的递归一下  
            return getClassField(superClass, fieldName);  
        }  
        return null;  
    }   
	

	public static void main(String[] args) {
		XmlUtil obj = new XmlUtil("response");
		obj.addSubXmlObject(new XmlUtil("resultCode", "452"));
		obj.addSubXmlObject(new XmlUtil("resultMsg", "3351321"));
		obj.addSubXmlObject(new XmlUtil("orderid", "C00152"));
		System.out.println(obj.toFormatXml());
	}
}