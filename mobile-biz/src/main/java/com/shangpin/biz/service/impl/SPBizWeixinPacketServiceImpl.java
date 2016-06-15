package com.shangpin.biz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.SPBizWeixinPacketService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.entity.WeiXinPacketCash;
import com.shangpin.core.entity.WeiXinPacketCoupon;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.core.service.IWeiXinPacketAccountService;
import com.shangpin.core.service.IWeiXinPacketCashService;
import com.shangpin.core.service.IWeiXinPacketCouponService;
import com.shangpin.core.service.IWeiXinPacketRecordService;
import com.shangpin.utils.JsonUtil;



/**
 * @author qinyingchun
 *微信红包业务层处理逻辑方法实现类
 */
@Service
public class SPBizWeixinPacketServiceImpl implements SPBizWeixinPacketService{
	
	@Autowired
	private IWeiXinPacketRecordService weiXinPacketRecordService;
	
	@Autowired
	private IWeiXinPacketAccountService weiXinPacketAccountService;
	
	@Autowired
	private IWeiXinPacketCouponService weiXinPacketCouponService;
	
	@Autowired
	private IWeiXinPacketCashService weiXinPacketCashService;
	
	@Autowired
	private ShangPinService shangPinService;

	@Override
	public List<WeiXinPacketRecord> send(String sendLoginName) {
		List<WeiXinPacketRecord> records = weiXinPacketRecordService.sendPacket(sendLoginName);
		return temp(records);
	}
	
	@Override
	public List<WeiXinPacketRecord> sendO(String sendOpenId) {
		List<WeiXinPacketRecord> records = weiXinPacketRecordService.sendPacketO(sendOpenId);
		return temp(records);
	}
	
	@Override
	public WeiXinPacketAccount findByLoginName(String loginName) {
		return weiXinPacketAccountService.findByLoginName(loginName);
	}

	@Override
	public List<WeiXinPacketRecord> receive(String receiveLoginName) {
		List<WeiXinPacketRecord> records = weiXinPacketRecordService.receivePacket(receiveLoginName);
		return temp(records); 
	}
	
	@Override
	public List<WeiXinPacketRecord> receiveO(String receiveOpenId) {
		List<WeiXinPacketRecord> records = weiXinPacketRecordService.findByReceiveOpenId(receiveOpenId);
		return temp(records); 
	}
	
	@Override
	public WeiXinPacketAccount save(WeiXinPacketAccount account) {
		return weiXinPacketAccountService.save(account);
	}
	
	private List<WeiXinPacketRecord> temp(List<WeiXinPacketRecord> records){
		if(null != records && records.size() > 0){
			for(WeiXinPacketRecord record : records){
				long time = record.getCreateTime().getTime();
				String strTime = new SimpleDateFormat("MM月dd日 HH:mm").format(new Date(time));
				record.setViewTime(strTime);
			}
		}
		return records;
	}

	@Override
	public WeiXinPacketAccount findByOpenId(String openId) {
		return weiXinPacketAccountService.findByOpenId(openId);
	}

	@Override
	public boolean existUser(String openId) {
		WeiXinPacketAccount account = this.findByOpenId(openId);
		if(null == account){
			return false;
		}
		return true;
	}

	@Override
	public List<WeiXinPacketCoupon> coupons(String loginName) {
		
		return weiXinPacketCouponService.findByAccountName(loginName);
	}

	@Override
	public WeiXinPacketCoupon saveCoupon(WeiXinPacketCoupon weiXinPacketCoupon) {
		return weiXinPacketCouponService.save(weiXinPacketCoupon);
	}

	@Override
	public List<WeiXinPacketCoupon> queryNumber(int number, String date) {
		return weiXinPacketCouponService.queryNumber( number, date);
	}
	public WeiXinPacketAccount findById(long id) {
		return weiXinPacketAccountService.findById(id);
	}

	@Override
	public WeiXinPacketRecord save(WeiXinPacketRecord record) {
		return weiXinPacketRecordService.save(record);
	}

	@Override
	public boolean isDiscern(String sendOpenId, String receiveOpenId) {
		return weiXinPacketRecordService.isDiscern(sendOpenId, receiveOpenId);
	}

	@Override
	public List<WeiXinPacketCash> findCash() {
		
		return weiXinPacketCashService.findCash();
	}

	@Override
	public Picture uploadPic(String extension, String picturetype, String picFileContent) {
		String json = shangPinService.uploadPic(extension, picturetype, picFileContent);
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ResultObjOne<Picture> picResultObjOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Picture>>() {});
		if(!Constants.SUCCESS.equals(picResultObjOne.getCode())){
			return null;
		}
		Picture picture = picResultObjOne.getContent();
		return picture;
	}

	@Override
	public List<WeiXinPacketCoupon> queryCoupon(String openId, String todayStr) {
		
		return weiXinPacketCouponService.queryCoupon(openId,todayStr);
	}


}
