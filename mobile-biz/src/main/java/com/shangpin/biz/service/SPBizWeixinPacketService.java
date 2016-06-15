package com.shangpin.biz.service;

import java.util.List;

import com.shangpin.biz.bo.Picture;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.entity.WeiXinPacketCash;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.core.entity.WeiXinPacketCoupon;

/**
 * @author qinyingchun
 *微信红包业务层处理逻辑方法
 */
public interface SPBizWeixinPacketService {
	/**
	 * 送出红包列表信息
	 * @param sendLoginName
	 * @return
	 */
	public List<WeiXinPacketRecord> send(String sendLoginName);
	
	public List<WeiXinPacketRecord> sendO(String sendOpenId);
	
	/**
	 * 根据登录账户查询用户信息
	 * @param loginName
	 * @return
	 */
	public WeiXinPacketAccount findByLoginName(String loginName);
	
	/**
	 * 保存微信用户信息
	 * @param account
	 * @return
	 */
	public WeiXinPacketAccount save(WeiXinPacketAccount account);
	
	/**
	 * 收到红包列表信息
	 * @param receiveLoginName
	 * @return
	 */
	public List<WeiXinPacketRecord> receive(String receiveLoginName);
	
	public List<WeiXinPacketRecord> receiveO(String receiveOpenId);
	/**
	 * 根据微信用户的openId查询用户信息
	 * @param openId
	 * @return
	 */
	public WeiXinPacketAccount findByOpenId(String openId);
	
	/**
	 * 判断用户是否存在
	 * @param openId
	 * @return
	 */
	public boolean existUser(String openId);
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	public WeiXinPacketAccount findById(long id);
	
	/**
	 * 保存红包接受记录信息
	 * @param record
	 * @return
	 */
	public WeiXinPacketRecord save(WeiXinPacketRecord record);
	
	/**
	 * 判断两个两个好友是否已经相互识别了二维码
	 * @param sendOpenId
	 * @param receiveOpenId
	 * @return
	 */
	public boolean isDiscern(String sendOpenId, String receiveOpenId);

	/**
	 * 根据微信账户查询现金卷
	 * @param loginName
	 * @return
	 */
	public List<WeiXinPacketCoupon> coupons(String loginName);

	/**
	 * 保存现金卷信息
	 * @param weiXinPacketCoupon
	 */
	public WeiXinPacketCoupon saveCoupon(WeiXinPacketCoupon weiXinPacketCoupon);

	/**
	 * 查询当日现金劵的兑换次数
	 * @param i
	 * @param format
	 * @return
	 */
	public List<WeiXinPacketCoupon> queryNumber(int i, String format);

	/**
	 * 查询页面显示的现金劵
	 * @return
	 */
	public List<WeiXinPacketCash> findCash();
	
	/**
	 * 上传微信红包分享图片
	 * @param extension
	 * @param picturetype
	 * @param picFileContent
	 * @return
	 */
	public Picture uploadPic(String extension, String picturetype, String picFileContent);

	/**
	 * 每天每个人的兑换情况
	 * @param openId
	 * @param todayStr
	 * @return
	 */
	public List<WeiXinPacketCoupon> queryCoupon(String openId, String todayStr);

}
