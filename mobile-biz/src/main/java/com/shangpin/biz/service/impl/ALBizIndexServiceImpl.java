package com.shangpin.biz.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.biz.bo.Activity;
import com.shangpin.biz.bo.PreSell;
import com.shangpin.biz.service.ALBizIndexService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.biz.utils.ParseJsonUtil;
import com.shangpin.biz.utils.StringUtil;

@Service
public class ALBizIndexServiceImpl implements ALBizIndexService {

	private static final Logger logger = LoggerFactory.getLogger(ALBizIndexServiceImpl.class);

	@Autowired
	private AoLaiService aoLaiService;

	/**
	 * 最新热卖列表，即当日最新活动列表
	 * 
	 */
	@Override
	public List<Activity> getNewHotProduct(String gender, String startTime, String endTime, String picw, String pich) {
		// 测试数据
		// startTime = "2014-10-23";
		// endTime = "2014-11-13";
		try {
			// 测试数据
			// String json =
			// "{\"code\":\"0\",\"msg\":\" \",\"content\":{\"list\":[{\"brand\": \"GUCCI\",\"productname\": \"男士羊毛西服套装\",\"now\": \"15999.00\",\"past\": \"16302.00\", \"goodsid\": \"01223304\",\"categoryno\": \"20120810713\",\"picurl\": \"http://pic.shangpincdn.com/getProductPicture.aspx?PictureFileNo=20111012165410005177&width=252&height=336\", \"count\": \"0\"},{\"brand\": \"GUCCI\",\"productname\": \"男士羊毛西服套装\",\"now\": \"15999.00\",\"past\": \"16302.00\", \"goodsid\": \"01223304\",\"categoryno\": \"20120810713\",\"picurl\": \"http://pic.shangpincdn.com/getProductPicture.aspx?PictureFileNo=20111012165410005177&width=252&height=336\", \"count\": \"0\"}]}}";
			logger.debug("***To newHot's params:gender,startTime,endTime,pW,pH***" + gender + ":" + startTime + ":" + endTime + ":" + picw + ":" + pich);
			List<Activity> hotProductList = getActivityList(gender, startTime, endTime, picw, pich);
			// 今日活动未满6个时，需要补充6个活动
			if (hotProductList == null) {
				hotProductList = new ArrayList<Activity>();
			}
			if (hotProductList==null || hotProductList.size() < 6) {
				String startTime1 = DateTimeUtil.shortFmt(DateTimeUtil.getAppointDayFromToday(DateTimeUtil.getShortDate(startTime), -2));
				List<Activity> moreList = getActivityList(gender, startTime1, startTime, picw, pich);
				if(moreList!=null){
					for (Activity activity : moreList) {
						if (hotProductList.size() < 6) {
							hotProductList.add(activity);
						}
					}
				}
				
			}
			return hotProductList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 最新热卖/预售日历_通用处理方法
	 * 
	 * @param gender
	 * @param startTime
	 * @param endTime
	 * @param picw
	 * @param pich
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Activity> getActivityList(String gender, String startTime, String endTime, String picw, String pich) {
		List<Activity> list = null;
		String json = aoLaiService.findSubjectListByPeriod(gender, startTime, endTime, picw, pich);
		logger.debug("ActivityList's json is:" + json);
		if (!StringUtils.isEmpty(json)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(json);
				String code = rootNode.path("code").asText();
				String content = rootNode.path("content").toString();

				// 主站处理成功且数据不为空
				if (Constants.SUCCESS.equals(code)) {
					List contentList = mapper.readValue(content, List.class);
					if (contentList != null && contentList.size() > 0) {
						String jsonStr = mapper.writeValueAsString(contentList.get(0));
						if (StringUtils.isEmpty(jsonStr)) {
							return null;
						}
						// 将json映射成List集合对象
						JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, Activity.class);
						list = mapper.readValue(jsonStr, javaType);

						return list;
					}

				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 限时特卖列表
	 */
	@Override
	public List<Activity> getLimitedProduct(String gender, String picw, String pich) {
		String json = aoLaiService.findEndingSubjectList(gender, picw, pich);
		logger.debug("LimitedProduct's json is:" + json);
		// if(StringUtils.isEmpty(json)){
		// //测试数据json
		// json =
		// "{ \"code\": \"0\", \"msg\": \"\", \"content\": { \"systime\": 1414998056184, \"list\": [ { \"pic\": \"http://pic15.shangpin.com/e/s/14/10/31/20141031180009026356-290-216.jpg\", \"activityid\": \"41031133\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"大牌拍照样品特卖\", \"t0\": \"全场\", \"t1\": \"0.90\", \"t2\": \"折起\", \"brandno\": \"B0002\", \"brandcnname\": \"普拉达\", \"brandenname\": \"PRADA\" }, { \"pic\": \"http://pic13.shangpin.com/e/s/14/10/30/20141030144359272666-290-216.jpg\", \"activityid\": \"41030851\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"意大利高质感裤袜\", \"t0\": \"全场\", \"t1\": \"69\", \"t2\": \"元起\", \"brandno\": \"B0835\", \"brandcnname\": \"FOGAL\", \"brandenname\": \"FOGAL\" }, { \"pic\": \"http://pic11.shangpin.com/e/s/14/10/29/20141029140504473702-290-216.jpg\", \"activityid\": \"41029720\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"和总统一起签名\", \"t0\": \"全场\", \"t1\": \"140\", \"t2\": \"元起\", \"brandno\": \"B1668\", \"brandcnname\": \"犀飞利\", \"brandenname\": \"SHEAFFER\" }, { \"pic\": \"http://pic13.shangpin.com/e/s/14/10/29/20141029140021520864-290-216.jpg\", \"activityid\": \"41029719\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"自然风格珠宝\", \"t0\": \"全场\", \"t1\": \"2.00\", \"t2\": \"折起\", \"brandno\": \"B0836\", \"brandcnname\": \"自然主义\", \"brandenname\": \"NATURALISM\" }, { \"pic\": \"http://pic12.shangpin.com/e/s/14/10/29/20141029135600832862-290-216.jpg\", \"activityid\": \"41029718\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"新品牌入驻\", \"t0\": \"全场\", \"t1\": \"1.30\", \"t2\": \"折起\", \"brandno\": \"B2042\", \"brandcnname\": \"\", \"brandenname\": \"KIWA AWIK\" }, { \"pic\": \"http://pic14.shangpin.com/e/s/14/10/29/20141029134927504232-290-216.jpg\", \"activityid\": \"41029714\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"新品牌入驻\", \"t0\": \"全场\", \"t1\": \"6.70\", \"t2\": \"折起\", \"brandno\": \"B2066\", \"brandcnname\": \"蜚比\", \"brandenname\": \"FITBIT\" }, { \"pic\": \"http://pic14.shangpin.com/e/s/14/10/29/20141029113330770762-290-216.jpg\", \"activityid\": \"41029711\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"18K镀金镶彩宝 清新上新\", \"t0\": \"全场\", \"t1\": \"109\", \"t2\": \"元起\", \"brandno\": \"B1424\", \"brandcnname\": \"蔻斯琦\", \"brandenname\": \"AGOVSKI\" }, { \"pic\": \"http://pic12.shangpin.com/e/s/14/10/25/20141025155105754980-290-216.jpg\", \"activityid\": \"41025557\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"让精致的你永远是注目的焦点\", \"t0\": \"全场\", \"t1\": \"222\", \"t2\": \"元起\", \"brandno\": \"B0884\", \"brandcnname\": \"黄蕙玲\", \"brandenname\": \"PESARO\" }, { \"pic\": \"http://pic16.shangpin.com/e/s/14/10/25/20141025154514801199-290-216.jpg\", \"activityid\": \"41025556\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"优雅时尚新风尚\", \"t0\": \"全场\", \"t1\": \"439\", \"t2\": \"元起\", \"brandno\": \"B2067\", \"brandcnname\": \"\", \"brandenname\": \"ATSURO TAYAMA\" }, { \"pic\": \"http://pic11.shangpin.com/e/s/14/10/30/20141030180620518473-290-216.jpg\", \"activityid\": \"41024514\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"时尚个性太阳镜镜架\", \"t0\": \"全场\", \"t1\": \"3.6\", \"t2\": \"折起\", \"brandno\": \"B0084\", \"brandcnname\": \"菲拉格慕\", \"brandenname\": \"SALVATORE FERRAGAMO\" }, { \"pic\": \"http://pic13.shangpin.com/e/s/14/10/30/20141030180539690084-290-216.jpg\", \"activityid\": \"41024513\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"费雷女士太阳镜镜架综合场\", \"t0\": \"全场\", \"t1\": \"299\", \"t2\": \"元起\", \"brandno\": \"B0062\", \"brandcnname\": \"费雷\", \"brandenname\": \"GIANFRANCO FERRE\" }, { \"pic\": \"http://pic15.shangpin.com/e/s/14/10/21/20141021211224848331-290-216.jpg\", \"activityid\": \"41021057\", \"starttime\": \"1414803600000\", \"endtime\": \"1415062800000\", \"activityname\": \"终极清仓\", \"t0\": \"全场\", \"t1\": \"99\", \"t2\": \"元起\", \"brandno\": \"B0613\", \"brandcnname\": \"\", \"brandenname\": \"BEVERLY FELDMAN\" }, { \"pic\": \"http://pic11.shangpin.com/e/s/14/10/24/20141024171130223592-290-216.jpg\", \"activityid\": \"41022219\", \"starttime\": \"1414425600000\", \"endtime\": \"1415030400000\", \"activityname\": \"意大利高质感裤袜\", \"t0\": \"全场\", \"t1\": \"1.3\", \"t2\": \"折起\", \"brandno\": \"B0835\", \"brandcnname\": \"FOGAL\", \"brandenname\": \"FOGAL\" }, { \"pic\": \"http://pic16.shangpin.com/e/s/14/10/27/20141027233045582035-290-216.jpg\", \"activityid\": \"41017518\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"优雅品味女装，新品牌入驻\", \"t0\": \"全场\", \"t1\": \"2.8\", \"t2\": \"折起\", \"brandno\": \"B2131\", \"brandcnname\": \"布凡诗 \", \"brandenname\": \"BUFANSHI\" }, { \"pic\": \"http://pic15.shangpin.com/e/s/14/10/20/20141020154320379112-290-216.jpg\", \"activityid\": \"41015120\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"冬季鞋品促销专场\", \"t0\": \"全场\", \"t1\": \"159\", \"t2\": \"元起\", \"brandno\": \"B0922\", \"brandcnname\": \"耐克\", \"brandenname\": \"NIKE\" }, { \"pic\": \"http://pic14.shangpin.com/e/s/14/10/20/20141020153341457196-290-216.jpg\", \"activityid\": \"41015119\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"让女人有万千风情的魅惑，维多利亚秘密专场特惠\", \"t0\": \"全场\", \"t1\": \"99\", \"t2\": \"元起\", \"brandno\": \"B0209\", \"brandcnname\": \"维多利亚的秘密\", \"brandenname\": \"VICTORIA'S SECRET\" }, { \"pic\": \"http://pic12.shangpin.com/e/s/14/10/20/20141020151543582393-290-216.jpg\", \"activityid\": \"41015117\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"年轻无极限运动综合场\", \"t0\": \"全场\", \"t1\": \"129\", \"t2\": \"元起\", \"brandno\": \"B1301\", \"brandcnname\": \"博特斯\", \"brandenname\": \"PROTEST\" }, { \"pic\": \"http://pic16.shangpin.com/e/s/14/10/28/20141028204905473218-290-216.jpg\", \"activityid\": \"41014038\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"靓丽名媛装\", \"t0\": \"全场\", \"t1\": \"249\", \"t2\": \"元起\", \"brandno\": \"B0535\", \"brandcnname\": \"\", \"brandenname\": \"SINEQUANONE\" }, { \"pic\": \"http://pic14.shangpin.com/e/s/14/10/28/20141028204654770358-290-216.jpg\", \"activityid\": \"41014037\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"简单优雅不跟风\", \"t0\": \"全场\", \"t1\": \"89\", \"t2\": \"元起\", \"brandno\": \"B0797\", \"brandcnname\": \"摩珂\", \"brandenname\": \"MO&CO.\" }, { \"pic\": \"http://pic13.shangpin.com/e/s/14/10/28/20141028204827535633-290-216.jpg\", \"activityid\": \"41014036\", \"starttime\": \"1414418400000\", \"endtime\": \"1415030400000\", \"activityname\": \"英伦经典热潮\", \"t0\": \"全场\", \"t1\": \"1.3\", \"t2\": \"折起\", \"brandno\": \"B0005\", \"brandcnname\": \"博柏利\", \"brandenname\": \"BURBERRY\" } ] } }";
		// }
		if (!StringUtils.isEmpty(json)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(json);
				rootNode = mapper.readTree(json);
				String jsonList = rootNode.path("content").path("list").toString();
				if (StringUtils.isEmpty(jsonList)) {
					return null;
				}
				JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, Activity.class);
				List<Activity> limitedProductList = mapper.readValue(jsonList, javaType);
				return limitedProductList;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 全部最新热卖（女士首页）
	 */
	@Override
	public List<Activity> getAllNewHotProduct(String gender, String picw, String pich) {
		logger.debug("To AllNewHotProduct's param:gender,pW,pH" + gender + ":" + picw + ":" + pich);
		String json = aoLaiService.findTodaySubjectList(gender, picw, pich);
		logger.debug("AllNewHotProduct's json is:" + json);
		if (!StringUtils.isEmpty(json)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode rootNode = mapper.readTree(json);
				rootNode = mapper.readTree(json);
				String jsonList = rootNode.path("content").path("list").toString();
				if (StringUtils.isEmpty(jsonList)) {
					return null;
				}
				JavaType javaType = ParseJsonUtil.getCollectionType(ArrayList.class, Activity.class);
				List<Activity> allNewHotProductList = mapper.readValue(jsonList, javaType);
				return allNewHotProductList;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 预售产品列表
	 */
	@Override
	public List<Activity> getPreSellProduct(String startTime, String endTime, String picw, String pich) {
		// 测试数据
		// startTime = "2014-10-23";
		// endTime = "2014-11-13";
		logger.debug("***To PreSell's params:startTime,endTime,pW,pH***" + startTime + ":" + endTime + ":" + picw + ":" + pich);
		try {
			List<Activity> preSellProductList = getActivityList("", startTime, endTime, picw, pich);
			return preSellProductList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 预售日期列表
	 */
	@Override
	public List<PreSell> getPreDateList(Date date, String id, String startTime, String endTime) {
		List<PreSell> preSellList = new ArrayList<PreSell>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		int length = 4;
		try {
			for (int i = 1; i < length; i++) {
				PreSell preSell = new PreSell();
				if (StringUtil.isNotEmpty(id, startTime, endTime)) {
					if ((String.valueOf(i).equals(id))) {
						preSell.setCssFlag(1);// 控制CSS样式：选中状态
					} else {
						preSell.setCssFlag(0);// 控制CSS样式：未选中状态
					}
				} else {
					// 默认选中明天
					if (i == 1)
						preSell.setCssFlag(1);
					else
						preSell.setCssFlag(0);
				}
				preSell.setId(i + "");
				preSell.setDate(sdf.format(DateTimeUtil.getAfterAmountDay(date, i)));
				preSell.setStartTime(sdf1.format(DateTimeUtil.getAfterAmountDay(date, i)));
				preSell.setEndTime(sdf1.format(DateTimeUtil.getAfterAmountDay(date, i + 1)));
				preSell.setWeekDay(DateTimeUtil.dateToWeek(DateTimeUtil.getAfterAmountDay(date, i)));
				preSellList.add(preSell);
			}
			return preSellList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
