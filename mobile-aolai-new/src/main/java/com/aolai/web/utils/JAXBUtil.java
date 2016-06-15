package com.aolai.web.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.bo.CouponPeriod;
import com.shangpin.biz.bo.Navigation;
import com.shangpin.biz.bo.Navigations;

/**
 * 主要用来实现对象和XML之间的序列化和反序列化操作
 * 
 * @author zghw
 *
 */
public class JAXBUtil {
    public final static Logger logger = LoggerFactory.getLogger(JAXBUtil.class);

    /**
     * 对象转化为xml字符串
     * 
     * @param obj
     * @return
     */
    public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Constants.DEFAULT_ENCODE);// //编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            logger.error("Object to xml ERROR:" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 转化xml中的数据为对象
     * 
     * @param xml
     * @param valueType
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(JAXBUtil.class.getClassLoader().getResourceAsStream(xml));
        } catch (Exception e) {
            logger.error("Object from xml ERROR:" + e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String args[]) {
        Navigations nav = JAXBUtil.fromXML("nav.xml", Navigations.class);
        Navigation navigation = nav.getNavigation().get(2);
        System.out.println(JAXBUtil.toXML(navigation));
        CouponPeriod cp = JAXBUtil.fromXML("couponPeriod.xml", CouponPeriod.class);
        System.out.println(JAXBUtil.toXML(cp));
    }
}
