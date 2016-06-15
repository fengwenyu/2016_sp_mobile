package com.shangpin.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Area;
import com.shangpin.biz.bo.City;
import com.shangpin.biz.bo.FashionRun;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.Town;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.core.entity.FashionOrder;
import com.shangpin.core.entity.FashionPack;
import com.shangpin.core.entity.FashionPackExt;
import com.shangpin.core.service.IFashionOrderService;
import com.shangpin.core.service.IFashionPackExtService;
import com.shangpin.core.service.IFashionPackService;
import com.shangpin.pay.bo.AlipayBo;
import com.shangpin.pay.bo.CommonBackBo;
import com.shangpin.pay.bo.UnionPayBo;
import com.shangpin.pay.bo.WXPayBo;
import com.shangpin.pay.bo.WXPayDataBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.pay.service.AlipayService;
import com.shangpin.pay.service.UnionpayService;
import com.shangpin.pay.service.WeixinpayService;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.pay.utils.weixin.WeixinpayUtil;
import com.shangpin.utils.IDCard;
import com.shangpin.web.utils.Constants;
import com.shangpin.web.utils.PropertyUtil;
import com.shangpin.web.utils.RunInviteCodePropertyUtil;

@Controller
@RequestMapping("/fashionrun")
public class FashionRunController {
	public static final Logger logger = LoggerFactory.getLogger(FashionRunController.class);
	@Autowired
	private IFashionOrderService fashionOrderService;
	@Autowired
	private IFashionPackService fashionPackService;
	@Autowired
	private IFashionPackExtService fashionPackExtService;
	@Autowired
	private SPBizUserService userService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private UnionpayService unionpayService;
	@Autowired
	private WeixinpayService weixinpayService;
	@Autowired
	private SPBizSendInfoService bizSendInfoService;
	@Autowired
	private SPBizAddressService bizAddressService;
	
	private static final String INDEX = "fashion_run/index";
	private static final String APPLY = "fashion_run/sign";
	private static final String AGREE = "fashion_run/agree";
	private static final String GIFT = "fashion_run/gift";
	private static final String SHARE="fashion_run/share";
	private static final String FILLFORM="fashion_run/fill_form";
	/** 微信支付页 */
	private static final String WEIXIN = "fashion_run/wechat_pay";
	private static final String PAY_SUCCESS = "fashion_run/pay_success";
	private static final String PAY_FAIL = "fashion_run/pay_fail";
	private static final String TOPAY = "fashion_run/topay";
	private static final String GIFT_SUCCESS = "fashion_run/gift_success";
	private static final String DOWNLOAD = "fashion_run/download";
	private static final String FINISH = "fashion_run/finish";
	private static final String MODIFY = "fashion_run/modify";
	private static final String FACE = "fashion_run/face";
	private static final String GRIL = "fashion_run/girl";
	private static final String LOGIN = "fashion_run/login";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return INDEX;

	}
	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public String apply(Model model) {
		long count = fashionOrderService.countNotStatus(Constants.FASHION_RUN_WAIT_PAY);
		
		if (count > Long.valueOf(Constants.RUN_COUNT)) {
			return FINISH;
		}
		return APPLY;

	}
	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String fillform(Model model) {
		return FILLFORM;

	}
	
	@RequestMapping(value = "/agree", method = RequestMethod.GET)
	public String agree(Model model) {
	
		return AGREE;

	}
	@RequestMapping(value = "/gift", method = RequestMethod.GET)
	public String gift(Model model) {
		List<Province> provinces = bizAddressService.findProvinceListObj();
		List<FashionPackExt> packExtList=fashionPackExtService.findAll();
	    model.addAttribute("provinces", provinces);
	    model.addAttribute("packExtList", packExtList);
		return GIFT;

	}
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(Model model) {
	
		return DOWNLOAD;

	}
	@RequestMapping(value="/share",method=RequestMethod.GET)
	public String share(Model model){
		
		return SHARE;
		
	}
	@RequestMapping(value = "/gift/success", method = RequestMethod.GET)
	public String giftSucess(Model model,String orderId) {
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		model.addAttribute("fashionOrder", fashionOrder);
		return GIFT_SUCCESS;

	}
	@RequestMapping(value = "/finish", method = RequestMethod.GET)
	public String finish(Model model) {
	
		return FINISH;

	}
	@RequestMapping(value = "/face", method = RequestMethod.GET)
	public String face(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (StringUtils.isEmpty(user)) {
            return LOGIN;
        }
        List<Object[]> list=fashionOrderService.findOrderSuccessCount();
        List<Object[]> sellList=fashionPackExtService.findPackExtSellCount();
        if(list==null||list.size()==0){
        	return FACE;
        }
		long totalCount=0;
		long paidCount=0;
		long applyCount=0;
		long deliverCount=0;
        for(int i=0;i<list.size();i++){
        	Object[] object=list.get(i);
        	long count=(Long) object[1];
        	totalCount=totalCount+count;
        	if(object[0].equals(Constants.FASHION_RUN_PAID)){
        		paidCount=count;
        	}
        	if(object[0].equals(Constants.FASHION_RUN_WAIT_CONFIRM)){
        		applyCount=count;
        	}
        	if(object[0].equals(Constants.FASHION_RUN_DELIVERED)){
        		deliverCount=count;
        	}
      
        }
        model.addAttribute("paidCount", paidCount);
        model.addAttribute("applyCount", applyCount);
    	model.addAttribute("deliverCount", deliverCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("sellList", sellList);
		return FACE;

	}
	
    /**
     * 
     * @Title: login
     * @Description: 用户登录校验
     * @Create By liling
     * @Create Date 2015年7月12日
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "userName",required = true) String userName, @RequestParam(value="password",required = true) String password,
           HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
            throws Exception, IOException {
     
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (!StringUtils.isEmpty(user)) {
            session.removeAttribute(Constants.SESSION_USER);
        }
        String msg = "";
        // 调用主站接口校验登录
        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            try {
                if (userName.equals(Constants.FASHION_RUN_NAME)&&password.equals(Constants.FASHION_RUN_PWD)) {
                	User fashionUser=new User();
            		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            		fashionUser.setUserid(dateFormat.format(new Date()));
            		fashionUser.setName(userName);
                    // 将user放入session中
                    session.setAttribute(Constants.SESSION_USER, fashionUser);
                    // 将userId存入session，便于日志查看
                    session.setAttribute(Constants.SESSION_USERID, fashionUser.getUserid());
                    return  "redirect:/fashionrun/face";
                } else {
                    msg = "用户名或密码错误，请重新输入...";
                    map.put("msg", msg);
                    return LOGIN;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception:" + e);
            }

        }
        return LOGIN;
    }
	@RequestMapping(value = "/girl", method = RequestMethod.GET)
    public String girl(Model model) {
    
        return GRIL;

    }
	/**
	 * 
	 * @Title: city
	 * @Description: 获取省下面的市
	 * @param
	 * @return List<City>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/city",  method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<City> getCity(String proviceId,Model model) {
		List<City> cities = bizAddressService.findCityListObj(proviceId);
		model.addAttribute("cities", cities);
		return cities;
	}
	/**
	 * 
	 * @Title: area
	 * @Description:获取市下面的区
	 * @param
	 * @return List<Area>
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	@ResponseBody
	public List<Area> area(String cityId,Model model) {
		List<Area> areas = bizAddressService.findAreaListObj(cityId);
		model.addAttribute("areas", areas);
		return areas;
	}

	@RequestMapping(value = "/town", method = RequestMethod.POST)
	@ResponseBody
	public List<Town> town(String areaId,Model model) {
		List<Town> towns = bizAddressService.findTownList(areaId);
		model.addAttribute("towns", towns);
		return towns;
	}



	/**
	 * 
	 * @Title: add
	 * @Description:fashion run报名操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月7日
	 */
	@RequestMapping(value = "/add",method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> add(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		long count = fashionOrderService.countNotStatus(Constants.FASHION_RUN_WAIT_PAY);
	
		if (count > Long.valueOf(Constants.RUN_COUNT)) {
			map.put("code", "-1");
			return map;
		}
		
		Map<String, Object> validationMap= validation(fashionRun);
		if(!validationMap.get("code").equals(Constants.SUCCESS)){
			return validationMap;
		}
		FashionPack fashionPack = fashionPackService.findByPackType("1");
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap = check(fashionRun);
		if (!checkMap.get("code").equals(Constants.SUCCESS)) {
			return checkMap;
		}
		FashionOrder fashionOrder = getFashionOrder(fashionRun);
		fashionOrder.setCreateTime(new Date());
		fashionOrder.setFashionType("1");
		fashionOrder.setStatus(Constants.FASHION_RUN_WAIT_PAY);
		fashionOrder.setOrderId(getOrderId(new Date()));
		BigDecimal packAmount = fashionPack.getPrice();
		// 装备金额
		fashionOrder.setPackAmount(packAmount);
		// 应付金额
		fashionOrder.setPayAmount(packAmount);
		fashionOrder.setPackId(fashionPack.getId());
		String phone=fashionRun.getPhone();
		String pid=fashionRun.getPid();
		logger.info("fashionrun user phone={},pid={}",phone,pid);
		fashionOrderService.saveOrUpdate(fashionOrder);
		String orderId=fashionOrder.getOrderId();
		logger.info("fashionrun user  phone={},pid={},orderId={}",phone,pid,orderId);
		map.put("code", "0");
		map.put("orderId", orderId);
		map.put("packId", fashionOrder.getPackId());
		map.put("id", fashionOrder.getId());
		return map;
	}
	/**
	 * 
	 * @Title: teamAdd
	 * @Description:fashion run团体报名操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月7日
	 */
	@RequestMapping(value = "/team/add", method = { RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> teamAdd(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		long count = fashionOrderService.countNotStatus(Constants.FASHION_RUN_WAIT_PAY);
	
		if (count > Long.valueOf(Constants.RUN_COUNT)) {
			map.put("code", "-1");
			return map;
		}
		
		Map<String, Object> validationMap= validation(fashionRun);
		if(!validationMap.get("code").equals(Constants.SUCCESS)){
			return validationMap;
		}
		String runInviteCode=RunInviteCodePropertyUtil.getString(fashionRun.getTeamFlag());
		if(!fashionRun.getInviteCode().equals(runInviteCode)){
			map.put("code", "5");
			return map;
		}
		FashionPack fashionPack = fashionPackService.findByPackType("2");
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap = check(fashionRun);
		if (!checkMap.get("code").equals(Constants.SUCCESS)) {
			return checkMap;
		}
		FashionOrder fashionOrder = getFashionOrder(fashionRun);
		fashionOrder.setCreateTime(new Date());
		fashionOrder.setFashionType("2");
		fashionOrder.setStatus(Constants.FASHION_RUN_WAIT_PAY);
		fashionOrder.setOrderId(getOrderId(new Date()));
		BigDecimal packAmount = fashionPack.getPrice();
		// 装备金额
		fashionOrder.setPackAmount(packAmount);
		// 应付金额
		fashionOrder.setPayAmount(packAmount);
		fashionOrder.setPackId(fashionPack.getId());
		String phone=fashionRun.getPhone();
		String pid=fashionRun.getPid();
		logger.info("fashionrun user phone={},pid={}",phone,pid);
		fashionOrderService.saveOrUpdate(fashionOrder);
		String orderId=fashionOrder.getOrderId();
		logger.info("fashionrun user  phone={},pid={},orderId={}",phone,pid,orderId);
		map.put("code", "0");
		map.put("orderId", orderId);
		map.put("packId", fashionOrder.getPackId());
		map.put("id", fashionOrder.getId());
		return map;
	}

	/**
	 * 
	 * @Title: apply
	 * @Description:fashion run领取礼包操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> apply(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
        FashionOrder fashionOrder = null;
		Specification<FashionOrder> specification = null;
		FashionRun fashionRunPhone = new FashionRun();
		fashionRunPhone.setPhone(fashionRun.getPhone());
		specification = getWhereClause(fashionRunPhone);
		List<FashionOrder> list = fashionOrderService.findByConditions(specification,new Sort(new Order(Direction.DESC, "createTime")));
		if(list==null||list.isEmpty()){
			map.put("code", "-1");
			return map;
		}
        fashionOrder = list.get(0);
		// TODO空判断
		String status = fashionOrder.getStatus();
		if (Constants.FASHION_RUN_PAID.equals(status)) {
			
			fashionOrder = updateFashionOrder(fashionRun, fashionOrder);
			String size = fashionRun.getSize();
			FashionPackExt fashionPackExt = this.fashionOrderService.findFashionPackExtBySize(size);
			int stock = fashionPackExt.getStock();
			if(stock<=0){
				map.put("code", "2");
				return map;
			}
			fashionOrder.setApplyTime(new Date());
			fashionOrder.setStatus(Constants.FASHION_RUN_WAIT_CONFIRM);
			fashionOrder=fashionOrderService.updateFashionOrder(fashionOrder);
			fashionOrderService.updateFashionPackExt(fashionPackExt);
			
			applyGiftByPhoneAndSendMsg(fashionOrder.getPhone());
			map.put("orderId", fashionOrder.getOrderId());
			map.put("code", "0");
			return map;
		}
		if (status.equals(Constants.FASHION_RUN_HAS_CONFIRM)||status.equals(Constants.FASHION_RUN_DELIVERED) || status.equals(Constants.FASHION_RUN_WAIT_CONFIRM)) {
			map.put("code", "1");
			return map;
		}
		
		return map;
	}
	/**
	 * 
	 * @Title: modify
	 * @Description:fashion run 修改
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model,String orderId) {
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		model.addAttribute("fashionOrder", fashionOrder);
		return MODIFY;

	}
	/**
	 * 
	 * @Title: add
	 * @Description:fashion run报名操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月7日
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		long count = fashionOrderService.countNotStatus(Constants.FASHION_RUN_WAIT_PAY);
	
		if (count > Long.valueOf(Constants.RUN_COUNT)) {
			map.put("code", "-1");
			return map;
		}
		Map<String, Object> validationMap= validation(fashionRun);
		if(!validationMap.get("code").equals(Constants.SUCCESS)){
			return validationMap;
		}
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap = checkById(fashionRun);
		if (!checkMap.get("code").equals(Constants.SUCCESS)) {
			return checkMap;
		}
		FashionOrder fashionOrder = fashionOrderService.findById(Long.valueOf(fashionRun.getId()));
		fashionOrder.setPhone(fashionRun.getPhone());
		fashionOrder.setName(fashionRun.getName());
		fashionOrder.setPid(fashionRun.getPid());
		fashionOrderService.saveOrUpdate(fashionOrder);
		map.put("orderId", fashionOrder.getOrderId());
		map.put("packId", fashionOrder.getPackId());
		map.put("id", fashionOrder.getId());
		if(!fashionOrder.getStatus().equals(Constants.FASHION_RUN_WAIT_PAY)){
			map.put("code", "3");
		}
		map.put("code", "0");
	
		return map;
	}

	/**
	 * 
	 * @Title: topay
	 * @Description:fashion run 去支付页
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/topay", method = RequestMethod.GET)
	public String toPay(Model model,String orderId) {
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		model.addAttribute("fashionOrder", fashionOrder);
		return TOPAY;

	}
	/**
	 * 
	 * @Title: apply
	 * @Description:fashion run领取礼包操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/findByPhone", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPhone(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Specification<FashionOrder> specification = null;
		FashionRun fashionRunPhone = new FashionRun();
		fashionRunPhone.setPhone(fashionRun.getPhone());
		specification = getWhereClause(fashionRunPhone);
		List<FashionOrder> list = fashionOrderService.findByConditions(specification,new Sort(new Order(Direction.DESC, "createTime")));
		if(list==null||list.size()<=0){
			map.put("code", "-1");
			return map;
		}
	
		map.put("fashionOrder", list.get(0));
		map.put("code", "0");
		// TODO空判断
		return map;
	}
	/**
	 * 
	 * @Title: apply
	 * @Description:fashion run领取礼包操作
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年2月7日
	 */
	@RequestMapping(value = "/findByPid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByPid(FashionRun fashionRun, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Specification<FashionOrder> specification = null;
		FashionRun fashionRunPhone = new FashionRun();
		fashionRunPhone.setPid(fashionRun.getPid());
		specification = getWhereClause(fashionRunPhone);
		List<FashionOrder> list = fashionOrderService.findByConditions(specification,new Sort(new Order(Direction.DESC, "createTime")));
		if(list==null||list.size()<=0){
			map.put("code", "-1");
			return map;
		}
	
		map.put("fashionOrder", list.get(0));
		map.put("code", "0");
		// TODO空判断
		return map;
	}
	/**
	 * 
	 * @Title: pay
	 * @Description: 支付宝支付
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/alipay", method = RequestMethod.GET)
	public String pay(String orderId, HttpServletRequest request, HttpServletResponse response) {

		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);

		AlipayBo alipayBo = new AlipayBo();
		alipayBo.setOrderId(orderId);
		alipayBo.setTotalFee(String.valueOf(fashionOrder.getPackAmount()));
		alipayBo.setOrderTimeout("600");// 超时时间600分钟
		alipayBo.setCallbackUrl(PropertyUtil
				.getString("fashion.run.alipay.callback.url"));
		alipayBo.setNotifyUrl(PropertyUtil
				.getString("fashion.run.alipay.notify.url"));
		alipayBo.setMerchantUrl(PropertyUtil
				.getString("fashion.run.alipay.merchant.url"));
		try {
			String url = alipayService.wapPay(alipayBo);
			if (!StringUtils.isEmpty(url)) {
				response.sendRedirect(url);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/deliver", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deliver(String orderId, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		if(fashionOrder==null){
			map.put("code", "-1");
		}
		String status = fashionOrder.getStatus();
		if (status.equals(Constants.FASHION_RUN_PAID) || status.equals(Constants.FASHION_RUN_WAIT_CONFIRM)) {
			fashionOrder.setApplyTime(new Date());
			fashionOrder.setStatus(Constants.FASHION_RUN_DELIVERED);
		/*	fashionOrder = updateFashionOrder(fashionRun, fashionOrder);*/
			fashionOrderService.saveOrUpdate(fashionOrder);
			map.put("code", "0");
		}
		if (status.equals(Constants.FASHION_RUN_WAIT_PAY)) {
			map.put("code", "1");
		}
		if (status.equals(Constants.FASHION_RUN_DELIVERED)) {
			map.put("code", "2");
		}
		return map;
		
	}
	/**
	 * 
	 * @Title: ylpay
	 * @Description:银联支付
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/yinlianpay", method = RequestMethod.GET)
	public String ylpay(String orderId, HttpServletResponse response,
			HttpServletRequest request) {
		logger.debug("fashion run yinlianpay start....................");
		logger.debug("orderId:" + orderId);
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		BigDecimal totalFee4 = new BigDecimal(100);
		String totalFee = String.valueOf(fashionOrder.getPayAmount()
				.multiply(totalFee4).toString().split("[.]")[0]);
		UnionPayBo unionPayBo = new UnionPayBo();
		SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyyMMddHHmmss");
		// 订单时间
		Date orderDate = fashionOrder.getCreateTime();
		long endtime = orderDate.getTime() + 60 * 1000 * 60 * 10;
		// 订单超时时间
		String timeExpire = formatDate1.format(new Date(endtime));
		String timeStart = formatDate1.format(new Date(orderDate.getTime()));
		unionPayBo.setOrderTimeStart(timeStart);
		unionPayBo.setOrderTimeout(timeExpire);
		unionPayBo.setOrderNumber(orderId);
		unionPayBo.setTotalFee(totalFee);
		unionPayBo.setOrderDescription(Constants.YL_PAY_DESCRIPTION);// 订单描述：用于第三方银联支付后显示
		unionPayBo.setCallbackUrl(PropertyUtil
				.getString("fashion.run.unionpay.callback.url"));
		unionPayBo.setNotifyUrl(PropertyUtil
				.getString("fashion.run.unionpay.notify.url"));
		try {
			String url = unionpayService.wapPay(unionPayBo);
			logger.debug("fashion run yinlianpay to url:" + url);
			if (!StringUtils.isEmpty(url)) {
				response.sendRedirect(url);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("yinlianpay end....................");
		return null;
	}

	@RequestMapping(value = "/wxpay", method = RequestMethod.GET)
	public String weixinPay(String orderId, String id,
			HttpServletRequest request, Model model) {
		try {
			logger.debug("fashion run weixinpay start....................");
			logger.debug("orderId:" + orderId);

			WXPayBo wxPayBo = new WXPayBo();
			FashionOrder fashionOrder = fashionOrderService
					.findByOrderId(orderId);
		/*	FashionPack fashionPack = fashionPackService.findById(Long
					.valueOf(fashionOrder.getPackId()));*/
			BigDecimal totalFee4 = new BigDecimal(100);
			String totalFee = String.valueOf(fashionOrder.getPackAmount()
					.multiply(totalFee4).toString().split("[.]")[0]);
			String ip = WeixinpayUtil.getIpAddr(request);
			if (ip.indexOf(",") > -1) {
				ip = ip.split(",")[0].trim();
			}
			// 获取订单详细信息
			SimpleDateFormat formatDate1 = new SimpleDateFormat(
					"yyyyMMddHHmmss");
			// 订单时间
			Date orderDate = fashionOrder.getCreateTime();
			long endtime = orderDate.getTime() + 60 * 1000 * 60 * 10;
			// 订单超时时间
			String timeExpire = formatDate1.format(new Date(endtime));
			String timeStart = formatDate1
					.format(new Date(orderDate.getTime()));
			wxPayBo.setOrderTimeStart(timeStart);
			wxPayBo.setOrderTimeout(timeExpire);
			wxPayBo.setOrderId(orderId);
			wxPayBo.setIp(ip);
			wxPayBo.setTotalFee(totalFee);
			wxPayBo.setProductName(Constants.FASHION_RUN_PAY_DESC);
			wxPayBo.setNotifyUrl(PropertyUtil
					.getString("fashion.run.wxpay.notify.url"));
			WXPayDataBo wxPayDataBo = weixinpayService.jsApiPay(wxPayBo);
			model.addAttribute("wxPayData", wxPayDataBo);
			model.addAttribute("orderId", orderId);
		} catch (ServiceException e) {
			logger.info("weixinpay e code={}", e.getErrType().getErrorCode());
			e.printStackTrace();
		}
		logger.debug("weixinpay end....................");
		return WEIXIN;
	}

	/**
	 * 
	 * @Title: alipayNotify
	 * @Description:支付宝回调函数异步调取的url更新订单状态
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月21日
	 */
	@RequestMapping(value = "/alipay/notify", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String alipayNotify(HttpServletRequest request,
			HttpServletResponse response) {
		// String userid = getUserId(request);
		logger.debug("fashion run alipay notify update order status start");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = alipayService.notify(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("fashion run alipay verifySign:" + flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
				fashionOrder.setStatus(Constants.FASHION_RUN_PAID);
				fashionOrder.setPayType("1");
				fashionOrder.setPayTime(new Date());
				String tradeNo = cBo.getTradeNo();
				if (StringUtil.isNotEmpty(tradeNo)) {
					fashionOrder.setTradeNo(tradeNo);
				}
				fashionOrderService.saveOrUpdate(fashionOrder);
				registerByPhoneAndSendMsg(fashionOrder.getPhone());
				
				logger.info("fashion run alipay order update success :orderId={}",orderId);
				out.print("success");

			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("fashion run alipay notify update order status end");
		return null;
	}

	/**
	 * 
	 * @Title: ylnotify
	 * @Description:银联回调函数异步调取的url更新订单状态
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/ylnotify", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String unionPayNotify(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("yinlian pay update order status start...................");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = unionpayService.notify(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("fashion run yinlian pay verifySign:" + flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
				fashionOrder.setStatus(Constants.FASHION_RUN_PAID);
				fashionOrder.setPayType("3");

				fashionOrder.setPayTime(new Date());
				fashionOrderService.saveOrUpdate(fashionOrder);
				registerByPhoneAndSendMsg(fashionOrder.getPhone());
				logger.debug("fashion run status update success");
				out.print("success");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("fashion run yinlian pay update order status end...................");
		return null;
	}

	/**
	 * 
	 * @Title: weixinPayNotify
	 * @Description:微信支付异步调取的url更新订单状态
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年11月21日
	 */
	@RequestMapping(value = "/wxpay/notify", method = { RequestMethod.GET,RequestMethod.POST })
	public String weixinPayNotify(HttpServletRequest request,HttpServletResponse response) {
		logger.debug("fashion run  weixin pay update order status start...................");
		try {
			PrintWriter out = response.getWriter();
			CommonBackBo cBo = weixinpayService.notify(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("fashion run  weixin pay verifySign:" + flag);
			if (flag) {
				String orderId = cBo.getOrderId();
				FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
				fashionOrder.setStatus(Constants.FASHION_RUN_PAID);
				fashionOrder.setPayType("2");
				String tradeNo = cBo.getTradeNo();
				if (StringUtil.isNotEmpty(tradeNo)) {
					fashionOrder.setTradeNo(tradeNo);
				}

				fashionOrder.setPayTime(new Date());
				fashionOrderService.saveOrUpdate(fashionOrder);
				registerByPhoneAndSendMsg(fashionOrder.getPhone());
				logger.info("fashion run wxpay order update success :orderId={}",orderId);
				out.print("success");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("fashion run  weixin pay update order status end...................");
		return null;
	}

	/**
	 * 
	 * @Title: success
	 * @Description:跳转到支付成功页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/alipay/success", method = RequestMethod.GET)
	public String success(Model model, HttpServletRequest request) {
		logger.debug("alipay success page start");

		try {
			CommonBackBo cBo = alipayService.callBack(request);
			boolean flag = cBo.isVerifySign();
			logger.debug("fashion run alipay verifySign:" + flag);
			String orderId = cBo.getOrderId();
			model.addAttribute("orderId", orderId);
			if (flag) {
				logger.debug("fashion run alipay verifySign success");
				return PAY_SUCCESS;
			} else {
				FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
				model.addAttribute("fashionOrder", fashionOrder);
				logger.debug("fashion run alipay verifySign fail to fail page");
				return PAY_FAIL;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.debug("fashion run alipay success page end");
		return PAY_FAIL;
	
	}

	/**
	 * 
	 * @Title: success
	 * @Description:跳转到支付成功页面
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/pay/ylsuccess", method = RequestMethod.GET)
	public String ylsuccess(Model model, HttpServletRequest request) {
		logger.debug("fashion run yinlianpay success page start");
		try {
			CommonBackBo cBo = unionpayService.callBack(request);
			boolean flag = cBo.isVerifySign();
			String orderId = cBo.getOrderId();
			if (flag) {
				logger.debug("fashion run yinlianpay verifySign success");
				return PAY_SUCCESS;
			} else {
				model.addAttribute("orderId", orderId);
				logger.debug("fashion run  yinlianpay verifySign fail to fail page");
				return PAY_FAIL;
			}

		} catch (ServiceException e) {
			e.printStackTrace();
		}
		logger.debug("fashion run  yinlianpay success page end");
		return PAY_FAIL;
	}

	/**
	 * 
	 * @Title: weixinPaySuccess
	 * @Description:微信支付成功跳转
	 * @param
	 * @return String
	 * @throws
	 * @Create By lilig
	 * @Create Date 2015年7月2日
	 */
	@RequestMapping(value = "/wxpay/success", method = RequestMethod.GET)
	public String weixinPaySuccess(String orderId, String type, Model model,
			HttpServletRequest request) {
		model.addAttribute("orderId", orderId);
		if (type.equals(Constants.SUCCESS)) {
			return PAY_SUCCESS;
		}
		FashionOrder fashionOrder = fashionOrderService.findByOrderId(orderId);
		model.addAttribute("fashionOrder", fashionOrder);
		return PAY_FAIL;
	}
	   /**
     * @Title: sendPhoneCode
     * @Description:发送手机验证码
     * @param mobi
     *            手机号
     * @param captcha
     *            验证码
     * @Create By zhongchao
     * @Create Date 2014年10月22日
     */
	@ResponseBody
    @RequestMapping(value = "/sendPhoneCode", method = RequestMethod.POST)
    public  Map<String, Object> sendPhoneCode(@RequestParam("phone") String phone, HttpServletRequest request) {
		 String msgTemplate = "尊敬的顾客，您正在申请领取Fashion Run装备，短信验证码为：{$verifyCode$}，请在页面内填写。如非本人操作，请联系客服4006-900-900。【尚品网】";
		 Map<String, Object> sendMap = bizSendInfoService.sendPhoneCode(phone, phone, msgTemplate);
		if (sendMap.get("code").equals(Constants.SUCCESS)) {
				logger.info("fashion run send msg sucess");
			} else {
				logger.info("fashion run send msg fail");
			}
		return sendMap;
     
    }
/*	private String getProductName(String name) {

		if (name.length() > 40) {
			return name.toString().substring(0, 40) + "...";
		} else {
			return name;
		}

	}*/
	private Map<String, Object> validation(final FashionRun fashionRun){
		Map<String, Object> map = new HashMap<String, Object>();
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		if (p.matcher(fashionRun.getName()).find()){
			map.put("code", "4");
			map.put("msg", "姓名格式不对");
			return map;
		}
		if (!IDCard.checkIDCard(fashionRun.getPid())){
			map.put("code", "4");
			map.put("msg", "请输入正确的身份证号码");
			return map;
		}
		map.put("code", "0");
		return map;
	 }
	private void registerByPhoneAndSendMsg(String phone) {
		QuickUser user = userService.checkUser(phone, Constants.CREATE_NEW_USER,Constants.FASHION_REGISTE_SOURCE);
		String isNewUser = user.getIsNewUser();
		String msgTemplate = "";
		logger.debug("start send phone info");
		msgTemplate = "美女，恭喜您报名成功！Fashion Run北京站将于8月1日上午8点，在奥林匹克森林公园南门正式开跑，我们不见不散！下载尚品网APP，领取FashionRun运动大礼包。";
		if (Constants.IS_NEW_USER.equals(isNewUser)) {
			String msgTemplateRe = "您好，恭喜您成功报名Fashion Run北京站活动，并将同时尊享尚品网会员权益。您的尚品网账户即报名手机号，初始密码为：手机号后6位，请尽快修改。 ";
			boolean registerflag = bizSendInfoService.sendInfo(phone, phone, msgTemplateRe);
			if (registerflag) {
				logger.info("fashion run register send msg sucess");
			} else {
				logger.info("fashion run register send msg fail");
			}
		} 
		boolean flag = bizSendInfoService.sendInfo(phone, phone, msgTemplate);
		if (flag) {
			logger.info("fashion run send msg sucess");
		} else {
			logger.info("fashion run send msg fail");
		}
	}
	private void applyGiftByPhoneAndSendMsg(String phone) {
		String msgTemplate = "美女，恭喜您成功领取FashionRun时尚装备！首席赞助商尚品网于7月29日前开始为您配送，预计1-2天送达，感谢您的参与!";
		boolean flag = bizSendInfoService.sendInfo(phone, phone, msgTemplate);
		if (flag) {
			logger.info("fashion run send msg sucess");
		} else {
			logger.info("fashion run send msg fail");
		}
	}

	private String getOrderId(Date createDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
		out.append(getRandomNum());
		return out.toString();
	}

	private String getRandomNum() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	private FashionOrder getFashionOrder(final FashionRun fashionRun) {
		FashionOrder fashionOrder = new FashionOrder();
		if(StringUtil.isNotEmpty(fashionRun.getAddr())){
			fashionOrder.setAddr(fashionRun.getAddr());
		}
		if(StringUtil.isNotEmpty(fashionRun.getCity())){
			fashionOrder.setCity(fashionRun.getCity());
		}
		/* fashionOrder.setBirthday(fashionRun.getBirthday()); */
		if(StringUtil.isNotEmpty(fashionRun.getContacts())){
			fashionOrder.setContacts(fashionRun.getContacts());
		}
		if(StringUtil.isNotEmpty(fashionRun.getContactsPhone())){
			fashionOrder.setContactsPhone(fashionRun.getContactsPhone());
		}
		if(StringUtil.isNotEmpty(fashionRun.getEmail())){
			fashionOrder.setEmail(fashionRun.getEmail());
		}
		if(StringUtil.isNotEmpty(fashionRun.getLogisticsName())){
			fashionOrder.setLogisticsName(fashionRun.getLogisticsName());
		}
		if(StringUtil.isNotEmpty(fashionRun.getLogisticsNo())){
			fashionOrder.setLogisticsNo(fashionRun.getLogisticsNo());
		}
		if(StringUtil.isNotEmpty(fashionRun.getPackId())){
			fashionOrder.setPackId(Long.valueOf(fashionRun.getPackId()));
		}
		if(StringUtil.isNotEmpty(fashionRun.getName())){
			fashionOrder.setName(fashionRun.getName());
		}
		if(StringUtil.isNotEmpty(fashionRun.getPhone())){
			fashionOrder.setPhone(fashionRun.getPhone());
		}
		// TODO
		/* fashionOrder.setPayAmount(fashionRun.getPayAmount()); */
		if(StringUtil.isNotEmpty(fashionRun.getPid())){
			fashionOrder.setPid(fashionRun.getPid());
		}
		if(StringUtil.isNotEmpty(fashionRun.getProvince())){
			fashionOrder.setProvince(fashionRun.getProvince());
		}
		if(StringUtil.isNotEmpty(fashionRun.getSex())){
			fashionOrder.setSex(fashionRun.getSex());
		}
		if(StringUtil.isNotEmpty(fashionRun.getZipCode())){
			fashionOrder.setZipCode(fashionRun.getZipCode());
		}
		if(StringUtil.isNotEmpty(fashionRun.getTeamFlag())){
			fashionOrder.setTeamFlag(fashionRun.getTeamFlag());
		}
		if(StringUtil.isNotEmpty(fashionRun.getInviteCode())){
			fashionOrder.setInviteCode(fashionRun.getInviteCode());
		}
		return fashionOrder;

	}

	private FashionOrder updateFashionOrder(final FashionRun fashionRun,FashionOrder fashionOrder) {
		
		
		if(StringUtil.isNotEmpty(fashionRun.getAddr())){
			fashionOrder.setAddr(fashionRun.getAddr());
		}
		if(StringUtil.isNotEmpty(fashionRun.getCity())){
			fashionOrder.setCity(fashionRun.getCity());
		}
		/* fashionOrder.setBirthday(fashionRun.getBirthday()); */
		if(StringUtil.isNotEmpty(fashionRun.getContacts())){
			fashionOrder.setContacts(fashionRun.getContacts());
		}
		if(StringUtil.isNotEmpty(fashionRun.getContactsPhone())){
			fashionOrder.setContactsPhone(fashionRun.getContactsPhone());
		}
		if(StringUtil.isNotEmpty(fashionRun.getEmail())){
			fashionOrder.setEmail(fashionRun.getEmail());
		}
		if(StringUtil.isNotEmpty(fashionRun.getLogisticsName())){
			fashionOrder.setLogisticsName(fashionRun.getLogisticsName());
		}
		if(StringUtil.isNotEmpty(fashionRun.getLogisticsNo())){
			fashionOrder.setLogisticsNo(fashionRun.getLogisticsNo());
		}
		if(StringUtil.isNotEmpty(fashionRun.getPackId())){
			fashionOrder.setPackId(Long.valueOf(fashionRun.getPackId()));
		}
		if(StringUtil.isNotEmpty(fashionRun.getName())){
			fashionOrder.setName(fashionRun.getName());
		}
		if(StringUtil.isNotEmpty(fashionRun.getPhone())){
			fashionOrder.setPhone(fashionRun.getPhone());
		}
		// TODO
		/* fashionOrder.setPayAmount(fashionRun.getPayAmount()); */
		if(StringUtil.isNotEmpty(fashionRun.getPid())){
			fashionOrder.setPid(fashionRun.getPid());
		}
		if(StringUtil.isNotEmpty(fashionRun.getProvince())){
			fashionOrder.setProvince(fashionRun.getProvince());
		}
		if(StringUtil.isNotEmpty(fashionRun.getArea())){
			fashionOrder.setArea(fashionRun.getArea());
		}
		if(StringUtil.isNotEmpty(fashionRun.getTown())){
			fashionOrder.setTown(fashionRun.getTown());
		}
		if(StringUtil.isNotEmpty(fashionRun.getSex())){
			fashionOrder.setSex(fashionRun.getSex());
		}
		if(StringUtil.isNotEmpty(fashionRun.getZipCode())){
			fashionOrder.setZipCode(fashionRun.getZipCode());
		}
		
		if(StringUtil.isNotEmpty(fashionRun.getSize())){
			fashionOrder.setSize(fashionRun.getSize());
		}
		
		
		return fashionOrder;

	}

	private Map<String, Object> check(final FashionRun fashionRun) {
		Map<String, Object> map = new HashMap<String, Object>();
		Specification<FashionOrder> specification = null;

		FashionRun fashionRunPhone = new FashionRun();
		fashionRunPhone.setPhone(fashionRun.getPhone());
		specification = getWhereClause(fashionRunPhone);
		List<FashionOrder> list = fashionOrderService.findByConditions(specification,new Sort(new Order(Direction.DESC, "createTime")));
		if (list != null && list.size() > 0) {
			map.put("code", "1");
			return map;
		}
		FashionRun fashionRunPid = new FashionRun();
		fashionRunPid.setPid(fashionRun.getPid());
		specification = getWhereClause(fashionRunPid);
		List<FashionOrder> listByPid = fashionOrderService.findByConditions(
				specification,
				new Sort(new Order(Direction.DESC, "createTime")));
		if (listByPid != null && listByPid.size() > 0) {
			map.put("code", "2");
			return map;
		}

	/*	FashionRun fashionRunEmail = new FashionRun();
		fashionRunEmail.setPhone(fashionRun.getEmail());
		specification = getWhereClause(fashionRunEmail);
		List<FashionOrder> listByEmail = fashionOrderService.findByConditions(
				specification,
				new Sort(new Order(Direction.DESC, "createTime")));
		if (listByEmail != null && listByEmail.size() > 0) {
			map.put("code", "3");
			return map;
		}*/
		map.put("code", "0");
		return map;
	}
	private Map<String, Object> checkById(final FashionRun fashionRun) {
		Map<String, Object> map = new HashMap<String, Object>();
		Specification<FashionOrder> specification = null;

		FashionRun fashionRunPhone = new FashionRun();
		fashionRunPhone.setPhone(fashionRun.getPhone());
		fashionRunPhone.setId(fashionRun.getId());
		specification = getWhereClauseById(fashionRunPhone);
		List<FashionOrder> list = fashionOrderService.findByConditions(specification,new Sort(new Order(Direction.DESC, "createTime")));
		if (list != null && list.size() > 0) {
			map.put("code", "1");
			return map;
		}
		FashionRun fashionRunPid = new FashionRun();
		fashionRunPid.setPid(fashionRun.getPid());
		specification = getWhereClauseById(fashionRunPid);
		List<FashionOrder> listByPid = fashionOrderService.findByConditions(
				specification,
				new Sort(new Order(Direction.DESC, "createTime")));
		if (listByPid != null && listByPid.size() > 0) {
			map.put("code", "2");
			return map;
		}

	/*	FashionRun fashionRunEmail = new FashionRun();
		fashionRunEmail.setPhone(fashionRun.getEmail());
		specification = getWhereClause(fashionRunEmail);
		List<FashionOrder> listByEmail = fashionOrderService.findByConditions(
				specification,
				new Sort(new Order(Direction.DESC, "createTime")));
		if (listByEmail != null && listByEmail.size() > 0) {
			map.put("code", "3");
			return map;
		}*/
		map.put("code", "0");
		return map;
	}
	private Specification<FashionOrder> getWhereClauseById(
			final FashionRun fashionRun) {
		return new Specification<FashionOrder>() {
			@Override
			public Predicate toPredicate(Root<FashionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if (fashionRun.getName() != null) {
					predicate.add(cb.like(root.get("name").as(String.class),
							"%" + fashionRun.getName() + "%"));

				}
				if (fashionRun.getPhone() != null) {
					predicate.add(cb.equal(root.get("phone").as(String.class),
							fashionRun.getPhone()));
				}
				if (fashionRun.getPid() != null) {
					predicate.add(cb.equal(root.get("pid").as(String.class),
							fashionRun.getPid()));
				}
				if (fashionRun.getEmail() != null) {
					predicate.add(cb.equal(root.get("email").as(String.class),
							fashionRun.getEmail()));
				}
				predicate.add(cb.notEqual(root.get("status").as(String.class),
						Constants.FASHION_RUN_WAIT_PAY));
				predicate.add(cb.notEqual(root.get("id").as(Long.class),
						fashionRun.getId()));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
	/**
	 * 动态生成where语句
	 * 
	 * @param fashionRun
	 * @return
	 */
	private Specification<FashionOrder> getWhereClause(
			final FashionRun fashionRun) {
		return new Specification<FashionOrder>() {
			@Override
			public Predicate toPredicate(Root<FashionOrder> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
				if (fashionRun.getName() != null) {
					predicate.add(cb.like(root.get("name").as(String.class),
							"%" + fashionRun.getName() + "%"));

				}
				if (fashionRun.getPhone() != null) {
					predicate.add(cb.equal(root.get("phone").as(String.class),
							fashionRun.getPhone()));
				}
				if (fashionRun.getPid() != null) {
					predicate.add(cb.equal(root.get("pid").as(String.class),
							fashionRun.getPid()));
				}
				if (fashionRun.getEmail() != null) {
					predicate.add(cb.equal(root.get("email").as(String.class),
							fashionRun.getEmail()));
				}
				predicate.add(cb.notEqual(root.get("status").as(String.class),
						Constants.FASHION_RUN_WAIT_PAY));
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
public static void main(String[] args) {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Random random = new Random();
	
	for(int i=0;i<162;i++){
		Date  createDate=new Date();
		
		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
		String result = "";
		for (int x = 0; x < 6; x++) {
			result += random.nextInt(10);
		}
		
		out.append(result);
		System.out.println(out.toString()+",");
	}
	

}
}
