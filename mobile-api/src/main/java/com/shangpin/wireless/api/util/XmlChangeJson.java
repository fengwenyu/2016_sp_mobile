package com.shangpin.wireless.api.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/** 
 * 转换一个xml格式的字符串到json格式 
 * @param xml xml格式的字符串 
 * @return 成功返回json 格式的字符串;失败反回null 
 */ 
public class XmlChangeJson {

	private static final Log log = LogFactory.getLog(XmlChangeJson.class.getSimpleName());
	
	public String xml2JSON(String xml){ 
		  JSONObject obj = new JSONObject(); 
		  try { 
			
		   InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8")); 
		   SAXBuilder sb = new SAXBuilder(); 
		   Document doc = sb.build(is); 
		   Element root = doc.getRootElement(); 
		   obj.put(root.getName(), iterateElement(root)); 
		   return obj.toString(); 
		  } catch (Exception e){ 
		   e.printStackTrace(); 
		   log.error("XmlChangeJson :"+e);
		   return null; 
		  } 
		}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map iterateElement(Element element){ 
		  List jiedian = element.getChildren(); 
		  Element et = null; 
		  Map obj = new HashMap(); 
		  List list = null; 
		  for (int i = 0; i < jiedian.size(); i++) { 
		   list = new LinkedList(); 
		   et = (Element) jiedian.get(i); 
		   if(et.getTextTrim().equals("")){ 
		    if(et.getChildren().size()==0) 
		     continue; 
		    if(obj.containsKey(et.getName())){ 
		     list = (List) obj.get(et.getName()); 
		    } 
		    list.add(iterateElement(et)); 
		    obj.put(et.getName(),list); 
		   }else{ 
		    if(obj.containsKey(et.getName())){ 
		     list = (List) obj.get(et.getName()); 
		    } 
		    list.add(et.getTextTrim()); 
		    obj.put(et.getName() ,list); 
		   } 
		  } 
		  return obj; 
		} 
	
	public static void main(String[] args) 
	{
		String arg1 ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
	  	"<PayPlatRequestParameter>"+
		"<CTRL-INFO WEBSVRNAME=\"订单生成\" WEBSVRCODE=\"001001\" APPFROM=\"001440060510005-TISSON001-1|121.33.197.198\" KEEP=\"20110513015738\" />"+
		"<PARAMETERS>"+
		"<PARTNERID>231232</PARTNERID>"+
		"<PRODUCTNO>13315953167</PRODUCTNO>"+
		"<PARTNERORDERID>213123</PARTNERORDERID>"+
		"<TXNAMOUNT>000000000100</TXNAMOUNT>"+
		"<BUSINESSTYPE></BUSINESSTYPE>"+
		"<GOODSID></GOODSID>"+
		"<GOODSNAME>箱包</GOODSNAME>"+
		"<GOODSCOUNT>1</GOODSCOUNT>"+
		"<SIG></SIG>"+
		"</PARAMETERS>"+
		"</PayPlatRequestParameter>";
		
		new XmlChangeJson().xml2JSON(arg1);
	
	}
	
}
