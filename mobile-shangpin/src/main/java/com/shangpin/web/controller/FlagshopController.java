package com.shangpin.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.Flagshop;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizFlagshopService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.web.utils.FlagshopEntrance;

/**
 * @ClassName: FlagshipController
 * @Description: 旗舰店
 * @author liling
 * @date 2014年12月18日
 * @version 1.0
 */
@Controller
@RequestMapping("/flagshop")
public class FlagshopController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FlagshopController.class);
	private static final String INDEX = "flagshop/index";
	private static final String DETAIL = "flagshop/detail";
	@Autowired
	SPBizFlagshopService flagshopService;

	/**
	 * 
	 * @Title: index
	 * @Description: 直接跳转到style搭配的首页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {

		// 获取旗舰店内容
		List<Flagshop> flagshopList = FlagshopEntrance.getFlagshopContent();
		model.addAttribute("flagshopList", flagshopList);
		return INDEX;
	}

	/**
	 * 
	 * @Title: detail
	 * @Description:  旗舰店详细页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月17日
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model, String brandNo, String gender, String imgUrl, String title) {
		try {
			Flagshop flagshop = flagshopService.getFlagshopDetail(brandNo);
			User user = getSessionUser(request);
			
			if (null == gender) {				
				if (user != null && "1".equals(user.getGender())) {
					gender = "1";
				} else {
					gender = "0";
				}
			}	
			String html = changeHtml(request, flagshop.getHtml(), gender, imgUrl, brandNo, title);
			model.addAttribute("html", html);
			model.addAttribute("title", title);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("flagshop detail e={}",e);
		}

		return DETAIL;
	}

	/**
	 * 拼接页面html的href方法
	 * 
	 * @return
	 */
	public String changeHtml(HttpServletRequest request, String htmlString, String gender, String imgUrl, String brandNO, String title) {
		String[] arrayString = htmlString.split("<a ");
		String hrefString = "";
		String resultString = "";
		String startString = "";
		String secondString = "";
		String thirdString = "";
		// 判断app版本信息
		String ua = request.getHeader("User-Agent").toLowerCase();
		String[] appArray = { "ShangpinIOSApp", "1.1.1", "AolaiIOSApp", "3.3.6", "ShangpinAndroidApp", "1.0.5", "AolaiAndroidApp", "1.0.4" };
		boolean flag = false;
		for (int i = 0; i < appArray.length; i = i + 2) {
			if (ua.indexOf(appArray[i].toLowerCase()) > -1) {
				String str = ua.substring(ua.indexOf(appArray[i].toLowerCase()), ua.length());
				String ver = str.substring(appArray[i].length() + 1, str.indexOf(";"));
				if (StringUtil.compareVer(ver, appArray[i + 1]) == 1) {
					flag = true;
				}
			}
		}
		if (flag) {
			for (int i = 1; i < arrayString.length; i++) {
				// 获取链接类型
				String byteString = arrayString[i].substring(arrayString[i].indexOf("data-type=\"") + 11, arrayString[i].indexOf("data-type=\"") + 11 + arrayString[i].substring(arrayString[i].indexOf("data-type=\"") + 11, arrayString[i].length()).indexOf("\""));
				try {
					if (arrayString[i].indexOf("<span>") > -1) {

						title = URLEncoder.encode(arrayString[i].substring(arrayString[i].indexOf("<span>") + 6, arrayString[i].indexOf("</span>")), "UTF-8");
						title = title.replaceAll("\\+", "%20");
					}
				} catch (UnsupportedEncodingException e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
				if (byteString.equals("link")) {// 链接
					startString = arrayString[i].substring(arrayString[i].indexOf("data-url=\"") + 10, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-url=\"") + 10, arrayString[i].indexOf("data-url=\"") + 10 + startString.indexOf("\""));
					if (hrefString.startsWith("http://")) {
						arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"" + hrefString + "\"").toString();
					} else {
						arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"http://" + hrefString + "\"").toString();
					}
				} else if (byteString.equals("navigation")) {// 导航
					String brandno = "";
					startString = arrayString[i].substring(arrayString[i].indexOf("data-brandno=\"") + 14, arrayString[i].length());
					String navno = "";
					String catenavname = "";
					secondString = arrayString[i].substring(arrayString[i].indexOf("data-navno=\"") + 12, arrayString[i].length());
					thirdString = arrayString[i].substring(arrayString[i].indexOf("data-navname=\"") + 14, arrayString[i].length());
					brandno = arrayString[i].substring(arrayString[i].indexOf("data-brandno=\"") + 14, arrayString[i].indexOf("data-brandno=\"") + 14 + startString.indexOf("\""));
					navno = arrayString[i].substring(arrayString[i].indexOf("data-navno=\"") + 12, arrayString[i].indexOf("data-navno=\"") + 12 + secondString.indexOf("\""));
					try {
						catenavname = URLEncoder.encode(arrayString[i].substring(arrayString[i].indexOf("data-navname=\"") + 14, arrayString[i].indexOf("data-navname=\"") + 14 + thirdString.indexOf("\"")), "UTF-8");
						catenavname = catenavname.replaceAll("\\+", "%20");

					} catch (UnsupportedEncodingException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					catenavname = arrayString[i].substring(arrayString[i].indexOf("data-navname=\"") + 14, arrayString[i].indexOf("data-navname=\"") + 14 + thirdString.indexOf("\""));

					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), "href=\"shangpinapp://phone.shangpin/actiongobrandlist?catenavname=" + catenavname + "&title=" + catenavname + "&brandid=" + brandno + "&navid=" + navno + "\"").toString();

					// arrayString[i]=new
					// StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"),
					// "href=\"shangpinapp://phone.shangpin/actiongocatelist?catename="+catenavname+"&categoryid=A01B01&title="+title+"\"").toString();
				} else if (byteString.equals("detail")) {// 详细
					startString = arrayString[i].substring(arrayString[i].indexOf("data-productid=\"") + 16, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-productid=\"") + 16, arrayString[i].indexOf("data-productid=\"") + 16 + startString.indexOf("\""));
					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"shangpinapp://phone.shangpin/actiongodetail?title=" + title + "&productid=" + hrefString + "\"").toString();
				} else if (byteString.equals("subject")) {// 活动
					String subjectName = "";
					startString = arrayString[i].substring(arrayString[i].indexOf("data-subjectno=\"") + 16, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-subjectno=\"") + 16, arrayString[i].indexOf("data-subjectno=\"") + 16 + startString.indexOf("\""));
					secondString = arrayString[i].substring(arrayString[i].indexOf("data-subjectname=\"") + 18, arrayString[i].length());
					try {
						subjectName = URLEncoder.encode(arrayString[i].substring(arrayString[i].indexOf("data-subjectname=\"") + 18, arrayString[i].indexOf("data-subjectname=\"") + 18 + secondString.indexOf("\"")), "UTF-8");
						subjectName = subjectName.replaceAll("\\+", "%20");

					} catch (UnsupportedEncodingException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"shangpinapp://phone.shangpin/actiongoactivitylist?title=" + subjectName + "&activityid=" + hrefString + "\"").toString();
				}
			}
		} else {
			for (int i = 1; i < arrayString.length; i++) {
				// 获取链接类型
				String byteString = arrayString[i].substring(arrayString[i].indexOf("data-type=\"") + 11, arrayString[i].indexOf("data-type=\"") + 11 + arrayString[i].substring(arrayString[i].indexOf("data-type=\"") + 11, arrayString[i].length()).indexOf("\""));
				if (byteString.equals("link")) {// 链接
					startString = arrayString[i].substring(arrayString[i].indexOf("data-url=\"") + 10, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-url=\"") + 10, arrayString[i].indexOf("data-url=\"") + 10 + startString.indexOf("\""));
					if (hrefString.startsWith("http://")) {
						arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"" + hrefString + "\"").toString();
					} else {
						arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"http://" + hrefString + "\"").toString();
					}
				} else if (byteString.equals("navigation")) {// 导航
					String brandno = "";
					startString = arrayString[i].substring(arrayString[i].indexOf("data-brandno=\"") + 14, arrayString[i].length());
					String navno = "";
					secondString = arrayString[i].substring(arrayString[i].indexOf("data-navno=\"") + 12, arrayString[i].length());
					brandno = arrayString[i].substring(arrayString[i].indexOf("data-brandno=\"") + 14, arrayString[i].indexOf("data-brandno=\"") + 14 + startString.indexOf("\""));
					//navno此参数不知何意，甄士隐上个版本源代码中也有，但没有实际意义，暂时保留
					navno = arrayString[i].substring(arrayString[i].indexOf("data-navno=\"") + 12, arrayString[i].indexOf("data-navno=\"") + 12 + secondString.indexOf("\""));
					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"brand/flagshopProduct/list?brandNo=" + brandno + "&navno=" + navno + "&imgUrl=" + imgUrl + "&title=" + title + "\"").toString();
				} else if (byteString.equals("detail")) {// 详细
					startString = arrayString[i].substring(arrayString[i].indexOf("data-productid=\"") + 16, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-productid=\"") + 16, arrayString[i].indexOf("data-productid=\"") + 16 + startString.indexOf("\""));
					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"/product/detail?productNo=" + hrefString + "\"").toString();
				} else if (byteString.equals("subject")) {// 活动
					String subjectName = "";
					startString = arrayString[i].substring(arrayString[i].indexOf("data-subjectno=\"") + 16, arrayString[i].length());
					hrefString = arrayString[i].substring(arrayString[i].indexOf("data-subjectno=\"") + 16, arrayString[i].indexOf("data-subjectno=\"") + 16 + startString.indexOf("\""));
					secondString = arrayString[i].substring(arrayString[i].indexOf("data-subjectname=\"") + 18, arrayString[i].length());
					try {
						subjectName = URLEncoder.encode(arrayString[i].substring(arrayString[i].indexOf("data-subjectname=\"") + 18, arrayString[i].indexOf("data-subjectname=\"") + 18 + secondString.indexOf("\"")), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
					arrayString[i] = new StringBuffer(arrayString[i]).insert(arrayString[i].indexOf(">"), " href=\"subject/flagshopProduct/list?topicId=" + hrefString + "&gender=" + gender + "&istop=1&imgUrl=" + imgUrl + "&title=" + title  +"&subjectName=" + subjectName  + "\"").toString();
				}
			}
		}

		for (int i = 0; i < arrayString.length; i++) {
			if (i < arrayString.length - 1) {
				resultString = resultString + arrayString[i] + "<a ";
			} else {
				resultString = resultString + arrayString[i];
			}

		}

		return resultString;

	}
}
