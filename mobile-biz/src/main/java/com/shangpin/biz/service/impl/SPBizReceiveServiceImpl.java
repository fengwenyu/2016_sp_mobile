package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.biz.bo.OrderReturn;
import com.shangpin.biz.bo.ReceiveRequest;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.biz.service.SPBiz520SellService;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.biz.service.SPBizSendInfoService;
import com.shangpin.core.entity.Receive;
import com.shangpin.core.service.IReceiveService;
import com.shangpin.utils.DateUtils;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JedisUtilFactory;
import com.shangpin.utils.JsonUtil;

@Service
@Transactional
public class SPBizReceiveServiceImpl implements SPBizReceiveService {
   
	@Autowired
	private FreebieService freebieService;
	
	@Autowired
	private IReceiveService iReceiveService;
	
	private static final Logger logger = LoggerFactory.getLogger(SPBizReceiveServiceImpl.class);
	
    private StringBuilder submitOrderURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/ConfirmOrderV3");
	
    @Autowired
	private SPBizSendInfoService bizSendInfoService;
    
    @Autowired
    private SPBiz520SellService sPBiz520SellService;
    @Autowired
	private ASPBizUserService aSPBizUserService;
	/**
	 * 领取服务 
	 * 返回1 参数有误  2 订单号无效   3 领取成功  4 提交订单失败 5已被领取
	 */
	public Map<String, Object> pickFreebie(ReceiveRequest request){

         Map<String, Object> map = new HashMap<String, Object>();
		 String fuserid=request.getFuserId()==null?"":request.getFuserId();
		 String forderid=request.getForderId()==null?"":request.getForderId();
		 String fspuid=request.getFspuId()==null?"":request.getFspuId();//发起skuno
		 String fspuno=request.getFspuNo()==null?"":request.getFspuNo();
		 String laddressId=request.getLaddressId()==null?"":request.getLaddressId();
		 String lskuId=request.getLskuId()==null?"":request.getLskuId();
		 String luserid=request.getLuserId()==null?"":request.getLuserId();
		 String mailAddress =request.getMailAddress()==null?"":request.getMailAddress();
		 //锁定商品并发key
		 String SKOno="shangpin"+fuserid+forderid+lskuId+fspuno;
		 OrderReturn isorder=new OrderReturn();
		 if(null!=JedisUtilFactory.getDefaultJedisUtil().get(SKOno)){
			 map.put("code", "4");
			 map.put("msg", "该商品正在被领取");
			 return map;
		 }
		 if (StringUtils.isEmpty(fuserid) || StringUtils.isEmpty(forderid)||StringUtils.isEmpty(fspuid)||StringUtils.isEmpty(fspuno)) {
				map.put("code", "1");
				map.put("msg", "参数有误!");
				return map;
			}
		//验证订单号是否有效
		boolean orderstatus=sPBiz520SellService.getOrderStatus(fuserid, forderid);
		 logger.info(forderid+"订单号状态为"+orderstatus);
		if(!orderstatus){
			map.put("code", "2");
            map.put("msg", "订单号无效!");
            return map;
		}
	   //已领取次数，值唯一。每个只能领取一次，为0可领取 1不可领取
	  Long getNumber=iReceiveService.countrecordAndSpuNo(fuserid, forderid,fspuid,fspuno);
	  
	  String key = rediskey(fuserid, forderid, fspuid, fspuno);
	  String phonekey=phoneRedisKey(fuserid, forderid, fspuid, fspuno);
	  long leftCnt=shengyu(fuserid,forderid,fspuid,fspuno);
	  
	  if(leftCnt==0){
		  map.put("code", "4");
          map.put("msg", "已领取完，不可领取");
          return map;
	  }
		// 只为0 可领取，1不能领取
	   if(getNumber==0 && leftCnt>0){
		   long value=JedisUtilFactory.getDefaultJedisUtil().new Strings().setnx(SKOno, "shifoulingqu");
		   if(1!=value){
			   map.put("code", "4");
			   map.put("msg", "该商品正在被领取");
			   return map;
		   }else{
			   JedisUtilFactory.getDefaultJedisUtil().expire(SKOno, 30);//30秒过期时间
		   }
		   //主站下单捕获异常
			   try{
				   isorder= confirmOrderV3(luserid, laddressId,lskuId);
			   }catch(Exception e){
				   logger.error("调用order/ConfirmOrderV3提交订单方法发生异常.......",e);
				   map.put("code", "4");
				   map.put("msg", "提交订单失败");
				   return map;
			   }
				   if(null!=isorder&&!StringUtils.isEmpty(isorder.getOrderNo())){
					   //下单成功
					   Receive receive =new Receive();
					   receive.setFuserid(fuserid);
					   receive.setForderId(forderid);
					   receive.setFspuId(fspuid);
					   receive.setAddressId(request.getLaddressId()==null?"":request.getLaddressId());
					   receive.setLuserId(request.getLuserId()==null?"":request.getLuserId());
					   receive.setCreateTime(new Date());
					   receive.setSkuId(request.getLskuId()==null?"":request.getLskuId());
					   receive.setPhone(request.getPhone()==null?"":request.getPhone());
					   receive.setFspuNo(request.getFspuNo()==null?"":request.getFspuNo());//spuno
					   receive.setLorderId(isorder.getOrderNo());
					   receive.setMailAddress(mailAddress);
					   //本地库记录一条
					   try{
			              Receive result1=iReceiveService.save(receive);
					       //redis
						  User user= aSPBizUserService.findUserByUserId(fuserid);
						  if (user!=null && !StringUtils.isEmpty(user.getUserid())) {
							  String phoneNum=user.getMobile();//手机号5月16日18:24
							  String phone1 =result1.getPhone();
							  String ltime=DateUtils.dateToStr(result1.getCreateTime(),"MM-dd HH:mm");
							  //有手机号才可以发送短信
							  if(!StringUtils.isEmpty(phoneNum)){
								  String msgTemplate = "您送出的TOPSHOP同款撞衫【"+result1.getFspuId()+"】，已被"+phone1+"于"+ltime+"成功领取";
								  try{
									  bizSendInfoService.sendPhoneCode(fuserid, phoneNum, msgTemplate);
								  }catch(Exception e){
									  logger.error("调用主站发送短信接口发生异常.......",e);
								  }
							  }
						 }else {
							  map.put("code", "4");
					          map.put("msg", "提交成功!没有发送短信!");
					          logger.info(forderid+"提交成功!但是没有发送短信");
					          return map;
						} 
						  //缓存及时性相隔毫米点击的时候验证信息返回
						   JedisUtilFactory.getDefaultJedisUtil().set(key,result1.getCreateTime().getTime()+"");
						   JedisUtilFactory.getDefaultJedisUtil().set(phonekey,result1.getPhone());
						   //谁知key过期时间
						   JedisUtilFactory.getDefaultJedisUtil().expire(key, 60*30);
						   JedisUtilFactory.getDefaultJedisUtil().expire(phonekey,60*30);
						  map.put("code", "3");
						  map.put("msg", "领取成功!");
						  return map;
					   }catch(Exception e){
						   logger.error(forderid+"保存领取记录失败",e);
						   map.put("code", "3");
						   map.put("msg", "领取失败!");
						   return map;
					   }
					  
				   }else{
					   if(null!=isorder&&isorder.getCode()!="0"){
						   map.put("code", "4");
	                       map.put("msg", isorder.getMsg());
	                       return map; 
					   }
					 
					 
				   } 
		   }else{
                Receive entity=iReceiveService.query(fuserid, forderid,fspuid,fspuno);
                if(null==entity){
                	 map.put("code", "4");
                     map.put("msg", "该商品不可领取");
                     return map;
    			}
                
                Long pictime=entity.getCreateTime().getTime();
                Long systime=new Date().getTime();
                //相差
                Long ms=systime-pictime;
                String phone=entity.getPhone();
                if(!StringUtils.isEmpty(phone)){
                    phone=phone.substring(0,3)+"****"+phone.substring(7);
                }
              String  message=checkitem(ms,phone);
               map.put("code", "5");
               map.put("msg", message);
               return map;
		   }
	   return map;
	   }
	private String rediskey(String fuserid, String forderid, String fspuid,
			String fspuno) {
		String key="receivetimess"+fuserid+forderid+fspuid+fspuno;
		return key;
	}
	/**
	 * 判断是否skuid是否领取   0 可领取 其他字符信息 
	 */
	public String isPicked(String fuserid, String forderid,String fspuid,String fspuno) {
		String message=""; 
		boolean is=true;
		String key = rediskey(fuserid, forderid, fspuid, fspuno);
		String phonekey=phoneRedisKey(fuserid, forderid, fspuid, fspuno);
		 String b=JedisUtilFactory.getDefaultJedisUtil().get(key);
		 String phone=JedisUtilFactory.getDefaultJedisUtil().get(phonekey);
			if(!StringUtils.isEmpty(b)&&!StringUtils.isEmpty(phone)){
				String time=b;
				String lphone=phone;
				Long ltime=Long.parseLong(time);
				Long system=new Date().getTime();
				Long xiangcha=system-ltime;
				String mobile=lphone.substring(0,3)+"****"+lphone.substring(7);
				message=checkitem(xiangcha, mobile);
				if(StringUtils.isEmpty(message)){
					String lp=lphone.substring(0,3)+"****"+lphone.substring(7);
					message="该同款撞衫已被"+lp+"于"+10+"秒前领走";
				}
				return message;
			} 
		//是否领取过次数
		Long getNumber=iReceiveService.countrecordAndSpuNo(fuserid, forderid,fspuid,fspuno);
		//剩余可领取次数
		long left_cnt=shengyu(fuserid,forderid,fspuid,fspuno);
		if(getNumber==0 && left_cnt>0){
			message="0";//可领取
			return message;
		}else{//告知谁领取了
			Receive entity=iReceiveService.query(fuserid, forderid,fspuid,fspuno);
			if(null==entity){
				message="该商品不可领取";
				return message; 
			}
			
			Long pictime=entity.getCreateTime().getTime();
			Long systime=new Date().getTime();
			//相差
			Long ms=systime-pictime;
			String phone1=entity.getPhone();
		    if(!StringUtils.isEmpty(phone1)){
		    	phone1=phone1.substring(0,3)+"****"+phone1.substring(7);
		    }
			message=checkitem(ms,phone1);
		    
		}
		logger.debug("验证是否领取方法返回值"+message);
		return message;
	}
	private String phoneRedisKey(String fuserid, String forderid,
			String fspuid, String fspuno) {
		return "phoness"+fuserid+forderid+fspuid+fspuno;
	}
	/**
	 * 判断某个用户订单，其中某个sku的剩余可领取次数
	 * @param fuserid
	 * @param forderid
	 * @param fspuid
	 * @return 小于等于0则没有可领取次数
	 */
    public int shengyu(String fuserid, String forderid,String fspuid,String fspuno){
    	String spunum1=mainshengyu(fuserid, forderid, fspuid,fspuno);
    	int mainsy=Integer.parseInt(spunum1);
    	if(mainsy>0){
    		long recenum=iReceiveService.countrecord(fuserid, forderid,fspuid);
    		logger.info(forderid+"本地库中记录数"+recenum+"sku"+fspuid);
    		return mainsy-Integer.parseInt(recenum+"");
    	}else{
    		return 0;//
    	}
    }
    
    public String mainshengyu(String fuserid, String forderid,String fspuid,String fspuno){
    	String spunum=sPBiz520SellService.checkSpuNum(fuserid, forderid, fspuid);
    	logger.info(forderid+"主站剩余次数>>>"+spunum+"sku"+fspuid);
    	 spunum=StringUtils.isEmpty(spunum)?"0":spunum;
    	return spunum;
    }
    
	
   /**
    * 提交主站订单接口,并验证是否调用成功
    * ConfirmOrderV3:<br/>
    * (TODO 描述这个方法的作用). <br/>
    * @param userid
    * @param receivedid
    * @param skuid
    * @return
    */
	public OrderReturn confirmOrderV3(String userid,String laddressId,String skuid){
		Map<String, String> map = new HashMap<>();
		 OrderReturn order=new OrderReturn();
		map.put("userId", userid);
		map.put("receivedId", laddressId);
		map.put("skuId", skuid);
	        String result = "";
	        try {
	            result = HttpClientUtil.doGet(submitOrderURL.toString(), map);
	            if(StringUtils.isEmpty(result)){
	            	return order;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       
	        ResultObjOne<OrderReturn> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjOne<OrderReturn>>(){});
	        if(null!=obj){
	           	if("0".equals(obj.getCode())){
	           		order=obj.getContent();
	           		order.setCode("0");
	       	     return order;
	           	}else{
	           		order.setCode(obj.getCode());
	           		order.setMsg(obj.getMsg());
	           		return order;
	           	}
	       }
	   return order;     
}
	/**
	 * /**
	 *  根据订单号查询订单是否有撞衫节赠送订单
	 */
	public List<Receive> isNeworder(String forderid,String lorderid){
		List<Receive> receive=iReceiveService.querynewOrderId(forderid,lorderid);
		System.out.println(1111);
		  return receive;
	}
	/**
	 * 根据撞衫订单查询订单详细
	 */
	
	public List <Receive> isorderdetail(String lorderid){
		List <Receive> receive=iReceiveService.querydetailOrderId(lorderid);
		  return receive;
	}
	
	
	public String checkitem(long ms,String phone){
		String message="";
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数
		try {
		//获得两个时间的毫秒时间差异
		long day = ms/nd;//计算差多少天
		long hour = ms%nd/nh;//计算差多少小时
		long min = ms%nd%nh/nm;//计算差多少分钟
		//输出结果
		//System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟");
		

        if(day!=0){
        	message="该同款撞衫已被"+phone+"于"+day+"天前领走";
          	return message;
        }
        if(hour!=0){
        	message="该同款撞衫已被"+phone+"于"+hour+"小时前领走";
        	return message;
        }
        
        if(min!=0){
        	message="该同款撞衫已被"+phone+"于"+min+"分钟前领走";
        	return message;
        }
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return message;
		
		
	}
	
	public List<String> receivedSkuSeq(String fuserid, String forderid,String fskuid){
		List<Receive> recevies=iReceiveService.queryReceivedSku(fuserid,forderid,fskuid);
		List<String> skuSeq = null;
		if(CollectionUtils.isNotEmpty(recevies)){			
			skuSeq = new ArrayList<>();
			for (Receive receive : recevies) {
				skuSeq.add(receive.getFspuNo());
			}
			return skuSeq;
		}
		return new ArrayList<String>();
	}
	
}



