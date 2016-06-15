package com.shangpin.wireless.api.util;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.common.xmap.XMap;

import com.shangpin.wireless.api.businessbean.Brand;
import com.shangpin.wireless.api.businessbean.Brands;

/**
 * 读取XML文件，将更多品牌组装为json格式。
 * 
 * @author yumeng
 */
public class MoreBrandUtil {
	/**
	 * 读取XML文件，并转换为Object数据
	 * 
	 * @param filename
	 *            XML文件
	 * @return 返回Brands类型的数组
	 * 
	 * @throws Exception
	 */
	private static Object[] xml2Bean(String fileName) throws Exception {
		XMap xmap = new XMap();
		xmap.register(Brands.class);
		InputStream in = MoreBrandUtil.class.getClassLoader().getResourceAsStream(fileName);
		return xmap.loadAll(in);
	}

	/**
	 * 将Brands类型的数组，转换为json格式的字符串
	 * 
	 * @param objs
	 *            Brands类型的数组
	 * 
	 * @return json格式的字符串
	 */
	private static String array2Json(Object[] objs) {
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("[");
		if (null != objs && objs.length > 0) {
			for (int i = 0; i < objs.length; i++) {
				Brands brands = (Brands) objs[i];
				String capital = brands.getCapital();
				sbuffer.append("{\"capital\":\"").append(capital).append("\",\"brands\":[");
				List<Brand> list = brands.getBrand();
				for (int j = 0; j < list.size(); j++) {
					Brand brand = list.get(j);
					sbuffer.append("{\"id\":\"").append(brand.getId());
					sbuffer.append("\",\"name\":\"").append(brand.getName());
					if (j == list.size() - 1) {
						sbuffer.append("\"}");
					} else {
						sbuffer.append("\"},");
					}
				}
				if (i == objs.length - 1) {
					sbuffer.append("]}");
				} else {
					sbuffer.append("]},");
				}
			}
		}
		sbuffer.append("]");
		return sbuffer.toString();
	}

	private static String successHeader() {
		return "{\"code\": \"0\",\"msg\": \"\",\"content\": {\"list\":";
	}

	public static String faildata() {
		return "{\"code\": \"-11\",\"msg\": \"系统数据有误\",\"content\": {}}";
	}

	public static String failCateid() {
		return "{\"code\": \"-12\",\"msg\": \"品类数据有误\",\"content\": {}}";
	}

	/**
	 * 读取XML文件，将更多品牌组装为json格式
	 * 
	 * @param fileName
	 *            XML文件
	 * 
	 * @return json格式的字符串
	 */
	public static String moreBrands(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return failCateid();
		}
		Object[] objs = null;
		try {
			objs = xml2Bean(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return faildata();
		}
		return successHeader() + array2Json(objs) + "}}";
	}

	/**
	 * 通过读取品类（箱包、服饰、鞋靴、配饰）xml，初始化更多品类，并放入缓存池中。
	 */
	public static void initMoreBrand() {
		// 男箱包A02B01
		DataContainerPool.brandContainer.putOrReplace("A02B01", moreBrands("config/morebrand/bags-men.xml"));
		// 男服饰A02B02
		DataContainerPool.brandContainer.putOrReplace("A02B02", moreBrands("config/morebrand/clothing-men.xml"));
		// 男鞋靴A02B03
		DataContainerPool.brandContainer.putOrReplace("A02B03", moreBrands("config/morebrand/shoes-men.xml"));
		// 男配饰A02B04
		DataContainerPool.brandContainer.putOrReplace("A02B04", moreBrands("config/morebrand/accessories-men.xml"));
		// 女箱包A01B01
		DataContainerPool.brandContainer.putOrReplace("A01B01", moreBrands("config/morebrand/bags-women.xml"));
		// 女服饰A01B02
		DataContainerPool.brandContainer.putOrReplace("A01B02", moreBrands("config/morebrand/clothing-women.xml"));
		// 女鞋靴A01B03
		DataContainerPool.brandContainer.putOrReplace("A01B03", moreBrands("config/morebrand/shoes-women.xml"));
		// 女配饰A01B04
		DataContainerPool.brandContainer.putOrReplace("A01B04", moreBrands("config/morebrand/accessories-women.xml"));
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.out.println(moreBrands("config/morebrand/bags-men.xml"));
//		System.out.println(xml2Bean("config/morebrand/bags-men.xml"));
	}
}
