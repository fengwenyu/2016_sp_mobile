package com.shangpin.mobileAolai.platform.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileAolai.base.model.Coupon;
import com.shangpin.mobileAolai.base.model.Lottery;
import com.shangpin.mobileAolai.common.annotation.AppAuthAnnotation;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.PropertiesUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.CouponService;
import com.shangpin.mobileAolai.platform.service.LotteryCouponService;
import com.shangpin.mobileAolai.platform.service.LotteryService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.LotteryConfig;
/**
 * 抽奖Action
 * 
 * @Author huangxiaoliang
 * 
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/actlotaction"), results = {
		@Result(name = "index", location = "/WEB-INF/pages/lottery/lottery.jsp"),
		@Result(name = "getLottery", type = "json", params = { "root", "entityJson" }) }) })
public class ActLotAction extends ActionSupport {
	private static final long serialVersionUID = 6241749617103279391L;
	@SuppressWarnings("unused")
	private final Log log = LogFactory.getLog(ActLotAction.class);	
	@Autowired
	private LotteryService lotteryService;
	
	@Autowired
	private LotteryCouponService lotteryCouponService;
	
	@Autowired
	CouponService couponService;
	private static final int LOTTERY_KEEP_TIME	= 30 * 60 * 1000;	//	30分钟
	private static LotteryConfig LotteryCache;
	private static long LotteryTime;
	private static long LotteryListTime;
	/** 实体数据转json */
	private JSONObject entityJson;
//	private  static List<Lottery> lotterysList;//缓存数据
	private List<Lottery> lotteryList;
	private List<Lottery> lotterys;
	private Lottery lottery;
	private List<String>  list;
	private String cmsTitle;
	private String activeId;
	private AccountVO user;
	
	/**
	 * 获得图片展示
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@AppAuthAnnotation
	public String lotteryIndex(){
		user = WebUtil.getSessionUser();
		if (user!=null) {
			lotteryList=getLotteryList(user.getUserid(), activeId);//获取某人某活动中奖信息
			lotterys = getDayLotteryList(user.getUserid(), activeId);//获取当天的某人某活动中奖信息
			list = creatList(lotterys);
			
		}
		
		return "index";
	}
	
	/**
	 * 抽奖
	 * 
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public synchronized String getLottery() {
		String channelNo = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(channelNo)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME) != null) {
				channelNo = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME).getValue();
			}else{
				channelNo=StringUtils.isEmpty(channelNo)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:channelNo;
			}
			
		}
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user = WebUtil.getSessionUser();
		List<Lottery> lotteryList=new ArrayList<Lottery>();
		entityJson=new JSONObject();
		if(user!=null){    
			LotteryConfig config = getLotteryConfig();
			Date date = new Date();
			if (config != null
					&& config.getStartTime().before(date)
					&& config.getEndTime().after(date)) {
				final String activeid = config.getId();
				lotteryList=lotteryService.findLotteryByUidAndAid(user.getUserid(), activeid);
				boolean winflagtoday = false;
				int prizeEntitylen=config.getEntity().length-1;//取出未中奖的等级编号，如果当前用户产生中奖纪录flag为true
				LotteryConfig.PrizeEntity lastprizeEntity=config.getEntity()[prizeEntitylen];
				for(Lottery lottery:lotteryList){					
					if(!lottery.getWinningLevel().equals(lastprizeEntity.getLevel())){
						winflagtoday = true;
					}
				}
				String leveltimes=config.getViptimesmap().get(user.getLv());
				int times=0;
				if(leveltimes!=null){
					times=Integer.parseInt(config.getViptimesmap().get(user.getLv()));
				}else{
					times=Integer.parseInt(config.getViptimesmap().get("0"));
				}
				int remain=times-lotteryList.size()-1;//剩余抽奖次数，包括本次抽奖
				if (remain < 0) {
					remain = 0;
				}
				if(lotteryList.size()<times){
					synchronized (this) {
						Random ran = new Random();
						int probability = ran.nextInt(100000);
						for (int i=0;i<config.getEntity().length;i++) {
							LotteryConfig.PrizeEntity prizeEntity = config.getEntity()[i];
							/*if(winflagtoday){//如果用户今天有除未中奖的中奖纪录，直接给予未中奖
								i = prizeEntitylen;
								probability=config.getEntity()[prizeEntitylen].getRangeStart();
								prizeEntity = config.getEntity()[prizeEntitylen];
							}*/
							if (probability >= prizeEntity.getRangeStart() && probability < prizeEntity.getRangeEnd()) {
								final String level = String.valueOf((i + 1));
								lotteryList = lotteryService.findLotteryByLevelAndAcid(level, activeid);								
								if(prizeEntity.getMaxcount() <= 0 || lotteryList.size() < prizeEntity.getMaxcount()){
									List<Lottery> lotteryUserList=new ArrayList<Lottery>();
									lotteryUserList = lotteryService.findLotteryByUidAndAidAndLevel(level, user.getUserid(),activeid);	
									if(prizeEntity.getUsermaxcount() < 0 || lotteryUserList.size() < prizeEntity.getUsermaxcount()){
										List<Lottery> lotteryDayList=new ArrayList<Lottery>();
										lotteryDayList = lotteryService.findLotteryByLevelAndAcidToday(level, activeid);
										if (prizeEntity.getDaycount() <= 0 || lotteryDayList.size() < prizeEntity.getDaycount()) {
											Lottery lottery =new Lottery();
									        lottery.setActiveId(activeid);
									        lottery.setActiveName(config.getDesc());
									        lottery.setUserId(user.getUserid());
									        lottery.setUserName(user.getEmail());
									        lottery.setCreateTime(new Date());
									        lottery.setWinningLevel(String.valueOf((i + 1)));
									        lottery.setWinningName(prizeEntity.getLevelname());
											String activateCode = null;
											if (prizeEntity.getActivatecode() == null) {
												//	不配置激活码，使用其他方式派奖，奖品不是优惠券
												if ("1".equals(level)) {
													lottery.setReserve1("A");
												} else if ("2".equals(level)) {
													lottery.setReserve2("B");
												} else if ("3".equals(level)) {
													lottery.setReserve3("C");
												} else if ("4".equals(level)) {
													lottery.setReserve4("D");
												}
												
											} else {
										        if (prizeEntity.getActivatecode() instanceof String) {
										        	activateCode = (String)prizeEntity.getActivatecode();
										        	if ("".equals(activateCode)) {
										        		//数据库查询优惠码
														String couponType = null;
														if ("5".equals(level)) {
															couponType = "2";
														} else {
															couponType = "1";
														}
														List<Coupon> coupons = lotteryCouponService.findLotteryCoupon(couponType);
														for (int j=0;j<coupons.size();j++) {
															Coupon coupon = coupons.get(0);
															coupon.setDel("1");
															lotteryCouponService.upate(coupon);
															activateCode = coupon.getCouponNum();
														}
														
										        	}
												}else if (prizeEntity.getActivatecode() instanceof JSONArray) {
													final int index = lotteryList.size();
													activateCode = ((JSONArray)prizeEntity.getActivatecode()).getString(index);
												}
												lottery.setReserve0(activateCode);//记录激活码
											}
											try {
												lotteryService.addLottery(lottery);
												if(activateCode!=null && !"".equals(activateCode)){	
													couponService.sendCoupon(user.getUserid(), "1", "coupon:"+activateCode,"1");
												}	
												// 记录访问日志
												FileUtil.addLog(SysContent.getRequest(), "actlotaction!getLottery",
														channelNo,
														"activeid", lottery.getActiveId(),
														"activeName",lottery.getActiveName(),
														"userId", user.getUserid(),
														"createTime", sdf.format(lottery.getCreateTime()),
														"winningLevel", lottery.getWinningLevel(),
														"winningName", lottery.getWinningName(),
														"activatecode", activateCode);
											} catch (Exception e) {
												entityJson.put("num", prizeEntitylen+1);

											    entityJson.put("times", remain);
												e.printStackTrace();
											}
											entityJson.put("num", String.valueOf((i + 1)));
										    entityJson.put("message", prizeEntity.getLevelname());
										    entityJson.put("times", remain);
										    if (prizeEntity.getImageSplit() != null && !"".equals(prizeEntity.getImageSplit())) {
										    	entityJson.put("image", prizeEntity.getImageSplit());
										    }
										    if (!"".equals(entityJson.get("image")) && entityJson.get("image") != null) {
										    	list = creatList(lotterys);
										    	if (list.size()==4){
										    		entityJson.put("one", "100");
										    	}
										    }
										    break;
										} else {
											probability = prizeEntity.getRangeEnd();
										}
									}else{
										probability = prizeEntity.getRangeEnd();
									}
																			
								} else {
									probability = prizeEntity.getRangeEnd();
								}
							}
						}
					}
				}else{
					entityJson.put("num", "-1");
				    entityJson.put("message", "今天已经没有机会了！");
				}				
			} else {
				entityJson.put("num", "-2");
			    entityJson.put("message", "抽奖活动已经结束！");
			}
		}else{
			entityJson.put("num", "-3");
		    entityJson.put("message", "你还没有登录！");
		    entityJson.put("loginFrom", "7");
		}
		
		return "getLottery";
	}
	@SuppressWarnings({ "rawtypes" })
	private static LotteryConfig getLotteryConfig () {
		long now = System.currentTimeMillis();
		try {
		if (now > LotteryTime + LOTTERY_KEEP_TIME) {
			Properties props = PropertiesUtil.getInstance("/resources/config/lottery/config.properties");
			
			for (Enumeration e = props.keys(); e.hasMoreElements();) {
				String propkey = (String) e.nextElement();
				final String propvalue = new String(props.getProperty(propkey).getBytes("ISO-8859-1"), "UTF-8");
				LotteryCache = LotteryConfig.parse(propvalue);				
			}
			LotteryTime = now;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LotteryCache;
	}
	public List<Lottery> getLotteryList(String userId, String activeid){
		Date date = new Date();
		LotteryConfig config = getLotteryConfig();
		try {
			if (config != null && config.getStartTime().before(date) && config.getEndTime().after(date)) {
				lotteryList=lotteryService.findLotteryListByUserId(userId, activeid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lotteryList;
	}

	public List<Lottery> getDayLotteryList(String userId, String activeid){
		Date date = new Date();
		LotteryConfig config = getLotteryConfig();
		try {
			if (config != null && config.getStartTime().before(date) && config.getEndTime().after(date)) {
				lotterys=lotteryService.findDayLotteryListByUserId(userId, activeid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lotterys;
	}
	
	@SuppressWarnings("rawtypes")
	public List creatList(List<?> lists){
		
		try {
			list = new ArrayList<String>();
			if (lotterys != null) {
				for (int s=0;s<lotterys.size();s++) {
					lottery = lotterys.get(s);
					if (lottery.getReserve1()!=null && !"".equals(lottery.getReserve1())){
						list.add(lottery.getReserve1());
					} 
					if (lottery.getReserve2()!=null && !"".equals(lottery.getReserve2())){
						list.add(lottery.getReserve2());
					} 
					if (lottery.getReserve3()!=null && !"".equals(lottery.getReserve3())){
						list.add(lottery.getReserve3());
					} 
					if (lottery.getReserve4()!=null && !"".equals(lottery.getReserve4())){
						list.add(lottery.getReserve4());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static LotteryConfig getLotteryCache() {
		return LotteryCache;
	}
	public static void setLotteryCache(LotteryConfig lotteryCache) {
		LotteryCache = lotteryCache;
	}
	public static long getLotteryTime() {
		return LotteryTime;
	}
	public static void setLotteryTime(long lotteryTime) {
		LotteryTime = lotteryTime;
	}
	public JSONObject getEntityJson() {
		return entityJson;
	}
	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public LotteryService getlotteryService() {
		return lotteryService;
	}

	public void setlotteryService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}
	
	public static long getLotteryListTime() {
		return LotteryListTime;
	}
	public static void setLotteryListTime(long lotteryListTime) {
		LotteryListTime = lotteryListTime;
	}
	
	public String getActiveId() {
		return activeId;
	}
	public void setActiveId(String activeId) {
		this.activeId = activeId;
	}
//	public static List<Lottery> getLotterysList() {
//		return lotterysList;
//	}
//	public static void setLotterysList(List<Lottery> lotterysList) {
//		ActLotAction.lotterysList = lotterysList;
//	}
	public List<Lottery> getLotteryList() {
		return lotteryList;
	}
	public void setLotteryList(List<Lottery> lotteryList) {
		this.lotteryList = lotteryList;
	}
	public List<Lottery> getLotterys() {
		return lotterys;
	}
	public void setLotterys(List<Lottery> lotterys) {
		this.lotterys = lotterys;
	}
	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	
	public String getCmsTitle() {
		return cmsTitle;
	}
	public void setCmsTitle(String cmsTitle) {
		this.cmsTitle = cmsTitle;
	}

	public AccountVO getUser() {
		return user;
	}

	public void setUser(AccountVO user) {
		this.user = user;
	}
}
