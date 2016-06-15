package com.shangpin.web.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.service.SPBizCouponService;
import com.shangpin.biz.service.SPBizWeixinPacketService;
import com.shangpin.core.entity.WeiXinPacketAccount;
import com.shangpin.core.entity.WeiXinPacketCash;
import com.shangpin.core.entity.WeiXinPacketCoupon;
import com.shangpin.core.entity.WeiXinPacketRecord;
import com.shangpin.utils.EmojiUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.web.utils.Constants;
import com.shangpin.wechat.bo.base.AccessToken;
import com.shangpin.wechat.bo.base.QRcodeCreateResult;
import com.shangpin.wechat.bo.base.UserInfo;
import com.shangpin.wechat.service.WeChatPublicService;

@Controller
@RequestMapping(value = "/packet")
public class WeixinPacketController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(WeixinPacketController.class);
	
	private Font font = new Font("微软雅黑", Font.PLAIN, 24);
	
	private Graphics2D g = null;
	
	private int fontSize = 0;
	
	private int x = 0;
	
	private int y = 0;

	private static final String SEND_FUNDS = "/packet/send_funds";
	private static final String RAISE_FUNDS = "/packet/raise_funds";
	private static final String EXCHANGE = "/packet/exchange";
	private static final String COUPONS = "/packet/coupons";
	private static final String SHARE_PIC = "/packet/share_pic";
	private static final String EXPLAIN = "/packet/explain";
	private static final String SHARE_PACKET = "/packet/share_packet";
	@Autowired
	private SPBizWeixinPacketService bizWeixinPacketService;
	@Autowired
	private SPBizCouponService bizCouponService;
	@Autowired
	private WeChatPublicService weChatPublicService;

	/**
	 * 送出红包记录
	 * 
	 * @param loginName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(String openId, Model model) {
		List<WeiXinPacketRecord> records = bizWeixinPacketService.sendO(openId);
		WeiXinPacketAccount account = bizWeixinPacketService.findByOpenId(openId);
		model.addAttribute("records", records);
		model.addAttribute("account", account);
		return SEND_FUNDS;
	}
	
	@RequestMapping(value = "/receivefirst", method = RequestMethod.GET)
	public String first(Model model, HttpServletRequest request){
		String code = request.getParameter("code");
		logger.debug("authroized code==============={}", code);
		AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
		logger.debug("access token==============={}", accessToken.toString());
		String openId = accessToken.getOpenid();
		//String openId = "oFHXijs8DAnA2OfSwOUH7rZtuv4U";
		return "redirect:/packet/receive?openId=" + openId;
	}

	/**
	 * 收到红包记录
	 * 
	 * @param openId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String receive(String openId, Model model, HttpServletRequest request) {
		logger.debug("openId===================={}", openId);
//		String code = request.getParameter("code");
//		logger.debug("authroized code==============={}", code);
//		AccessToken accessToken = weChatPublicService.getAccessTokenObj(code);// 网页授权获得的token和公共账号获取的token不同
//		logger.debug("access token==============={}", accessToken.toString());
		String token = weChatPublicService.getToken();
		logger.debug("token==============={}", token);
//		if (StringUtils.isEmpty(openId)) {
//			openId = accessToken.getOpenid();
//			//openId = (String)request.getSession().getAttribute("openId");
//			logger.debug("openId================={}", openId);
//		}
		WeiXinPacketAccount packetAccount = bizWeixinPacketService.findByOpenId(openId);
		logger.debug("packet account==============={}", packetAccount);
		if (null == packetAccount) {
			UserInfo user = weChatPublicService.baseUserObj(token, openId);
			logger.debug("user info==================={}", user.toString());
			WeiXinPacketAccount account = convert(user);
			logger.debug("account================={}", account.toString());
			account.setShangpinPacket(Constants.SHANGPIN_PACKET_MONEY);
			account.setBalance(Constants.SHANGPIN_PACKET_MONEY);
			account.setCreateTime(new Timestamp(new Date().getTime()));
			packetAccount = bizWeixinPacketService.save(account);
		}
		List<WeiXinPacketRecord> records = bizWeixinPacketService.receiveO(openId);
		double shangpinPacket = packetAccount.getShangpinPacket();
		double receiveMoney = packetAccount.getReceivePacketMoney();
		double totalMoney = shangpinPacket + receiveMoney;
		double amount = formateDouble(totalMoney);
		String shareTitle = Constants.SHARE_TITLE;
		String shareDesc = Constants.SHARE_DESC;
		model.addAttribute("shareTitle", shareTitle);
		model.addAttribute("shareDesc", shareDesc);
		model.addAttribute("records", records);
		model.addAttribute("account", packetAccount);
		model.addAttribute("amount", amount);
		return RAISE_FUNDS;
	}
	
	@RequestMapping(value = "/share/raise", method = RequestMethod.GET)
	public String sharePacket(String openId, Model model){
		WeiXinPacketAccount packetAccount = bizWeixinPacketService.findByOpenId(openId);
		List<WeiXinPacketRecord> records = bizWeixinPacketService.receiveO(openId);
		double shangpinPacket = packetAccount.getShangpinPacket();
		double receiveMoney = packetAccount.getReceivePacketMoney();
		double totalMoney = shangpinPacket + receiveMoney;
		double amount = formateDouble(totalMoney);
		model.addAttribute("records", records);
		model.addAttribute("account", packetAccount);
		model.addAttribute("amount", amount);
		return SHARE_PACKET;
	}

	/**
	 * 分享页面
	 * 
	 * @param openId
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String share(String openId, Model model, HttpServletRequest request) {
		WeiXinPacketAccount account = bizWeixinPacketService.findByOpenId(openId);
		String sharePic = account.getSharePicUrl();
		if(StringUtils.isEmpty(sharePic)){
			String personLogo = account.getHeadImgUrl();
			String nickName = account.getNickName();
			String token = weChatPublicService.getToken();
			long userId = account.getId();
			// 生成二维码
			String code_json = "{\"action_name\": \"QR_LIMIT_SCENE\",\"action_info\": {\"scene\": {\"scene_id\": \""
					+ userId + "\"}}}";
			String qrcode_json = weChatPublicService.qrcodeCreate(token, code_json);
			logger.debug("qrcode ticket======================{}", qrcode_json);
			QRcodeCreateResult qrcodeResult = JsonUtil.fromJson(qrcode_json, QRcodeCreateResult.class);
			String ticket = qrcodeResult.getTicket();
			logger.debug("qrcode ticket===================={}", ticket);
			// 获取二维码URL
			String showQrcodeURL = weChatPublicService.showQRCode(ticket);
			logger.debug("qrcode show url=================={}", showQrcodeURL);
			String realPath = request.getRealPath("");
			String sharebg = realPath + File.separator + "styles" + File.separator + "shangpin" + File.separator + "images" + File.separator + "wechatRed" + File.separator + "sharebg.jpg";
			logger.debug("share background image==============={}", sharebg);
			BufferedImage bufferedImgb = this.loadImageUrl(showQrcodeURL);
			BufferedImage bufferedImgd = this.loadImageLocal(sharebg);
			BufferedImage togeterQrcodeImage = this.modifyImagetogeter(bufferedImgb, bufferedImgd, 180, 650, 170, 170);
			BufferedImage bufferedImgHead = this.loadImageUrl(personLogo);
			BufferedImage togeterHeadImage = this.modifyHeaderImage(bufferedImgHead, togeterQrcodeImage, 240, 160, 494, 494);
			BufferedImage resultImage = this.modifyImage(togeterHeadImage, nickName, 268, 340);
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imageOut;
			try {
				imageOut = ImageIO.createImageOutputStream(bs);
				this.writeImageOutput(imageOut, resultImage);
				byte[] bt = bs.toByteArray();
				Base64 base64=new Base64();  
				String str = new String(base64.encode(bt));
				Picture picture = bizWeixinPacketService.uploadPic(".jpg", "system", str);
				sharePic = picture.getPicUrl();
				logger.debug("share pic ==================={}", sharePic);
				sharePic = sharePic.replace("{w}", "640").replace("{h}", "1136");
				account.setSharePicUrl(sharePic);
				bizWeixinPacketService.save(account);
				logger.debug("shar pic save end====================");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("account", account);
		model.addAttribute("showPic", sharePic);
		return SHARE_PIC;
	}

	/**
	 * 微信红包规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/explain", method = RequestMethod.GET)
	public String explain() {
		return EXPLAIN;
	}

	@RequestMapping(value = "/exchange", method = RequestMethod.GET)
	public String exchange(String openId, Model model) {
		int totalcoupon = 0;
		List<WeiXinPacketRecord> records = bizWeixinPacketService
				.receiveO(openId);
		WeiXinPacketAccount account = bizWeixinPacketService
				.findByOpenId(openId);
		List<WeiXinPacketCoupon> coupons = bizWeixinPacketService
				.coupons(openId);
		String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 现金劵的信息
		List<WeiXinPacketCash> cashs = bizWeixinPacketService.findCash();
		for (WeiXinPacketCash weiXinPacketCash : cashs) {
			if (weiXinPacketCash.getId() == 1) {
				model.addAttribute("cashone", weiXinPacketCash);
			}
			if (weiXinPacketCash.getId() == 2) {
				model.addAttribute("cashtwo", weiXinPacketCash);
			}
			if (weiXinPacketCash.getId() == 3) {
				model.addAttribute("cashthree", weiXinPacketCash);
			}
		}
		// 已兑换红包总额
		for (WeiXinPacketCoupon weiXinPacketCoupon : coupons) {
			totalcoupon += weiXinPacketCoupon.getCouponValue();
		}
		// 每天兑换红包的总数
		List<WeiXinPacketCoupon> tennumber = bizWeixinPacketService
				.queryNumber(10,todayStr);
		List<WeiXinPacketCoupon> thirtynumber = bizWeixinPacketService
				.queryNumber(30,todayStr);
		List<WeiXinPacketCoupon> fiftynumber = bizWeixinPacketService
				.queryNumber(50,todayStr);
		//每个人每天兑换的红包
		List<WeiXinPacketCoupon>  todayCoupon=bizWeixinPacketService.queryCoupon(openId,todayStr);
		// 判断哪个红包已兑换
		for (WeiXinPacketCoupon weiXinCoupon : todayCoupon) {
			if (weiXinCoupon.getCouponValue() == 10) {
				model.addAttribute("ten", weiXinCoupon.getCouponValue());
			}
			if (weiXinCoupon.getCouponValue() == 30) {
				model.addAttribute("thirty", weiXinCoupon.getCouponValue());
			}
			if (weiXinCoupon.getCouponValue() == 50) {
				model.addAttribute("fifty", weiXinCoupon.getCouponValue());
			}
		}
		double shangpinPacket = account.getShangpinPacket();
		double receiveMoney = account.getReceivePacketMoney();
		double totalMoney = shangpinPacket + receiveMoney;
		double amount = formateDouble(totalMoney);
		model.addAttribute("tennumber", tennumber.size());
		model.addAttribute("thirtynumber", thirtynumber.size());
		model.addAttribute("fiftynumber", fiftynumber.size());

		model.addAttribute("coupons", coupons);
		model.addAttribute("totalCoupon", totalcoupon);

		model.addAttribute("records", records);
		model.addAttribute("account", account);
		model.addAttribute("amount", amount);
		return EXCHANGE;
	}

	@ResponseBody
	@RequestMapping(value = "/saveCoupon")
	public String savecoupon(WeiXinPacketCoupon weiXinPacketCoupon, String activCode, String openId, HttpServletRequest request) {
		logger.debug("acive code============={}", activCode);
		String resultCode = "0";
		String keycode=null;
		String codeone="";
		String codetwo="";
		String codethree="";
		// 现金劵的信息
		List<WeiXinPacketCash> cashs = bizWeixinPacketService.findCash();
		for (WeiXinPacketCash weiXinPacketCash : cashs) {
			if (weiXinPacketCash.getId() == 1) {
				codeone=weiXinPacketCash.getActiCode();
			}
			if (weiXinPacketCash.getId() == 2) {
				 codetwo=weiXinPacketCash.getActiCode();
			}
			if (weiXinPacketCash.getId() == 3) {
				codethree=weiXinPacketCash.getActiCode();
			}
		}
		if(activCode.equals("10")){
			keycode=codeone;
		}
		if(activCode.equals("30")){
			keycode=codetwo;
		}
		if(activCode.equals("50")){
			keycode=codethree;
		}
		String userid = getUserId(request);
		logger.debug("user id=================={}", userid);
		Map<String, Object> resultBase = bizCouponService.sendCoupon(userid, "1", "coupon:" + keycode, "8");
		logger.debug("active code return result============================={}", resultBase);
		if (Constants.SUCCESS.equals(resultBase.get("code"))) {
			String sd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			weiXinPacketCoupon.setCreateTime(sd);
			WeiXinPacketCoupon coupon = bizWeixinPacketService.saveCoupon(weiXinPacketCoupon);
			WeiXinPacketAccount account = bizWeixinPacketService.findByOpenId(openId);
			double lastBalance = account.getBalance();
			double couponValue = weiXinPacketCoupon.getCouponValue();
			double balance = formateDouble(lastBalance - couponValue);
			account.setBalance(balance);
			bizWeixinPacketService.save(account);
			logger.debug("balance end==================");
			if (null == coupon) {
				resultCode = "1";
			}
		} else {
			resultCode = "1";
		}
		return resultCode;
	}
	
	@RequestMapping(value = "/send/msg", method = RequestMethod.GET)
	@ResponseBody
	public void sendMsg(String openId, String amount, HttpServletRequest request){
		String currentDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
		String context = request.getContextPath();
		String couponInfo = Constants.COUPON_INFO;
		logger.debug("orgin coupon info============{}", couponInfo);
		String sendMsg = couponInfo.replace("${user.openId}", openId).replace("${coupon.amount}", amount).replace("${current.date}", currentDate);
		logger.debug("result coupon info================={}", sendMsg);
		String token = weChatPublicService.getToken();
		weChatPublicService.sendMsg(token, sendMsg);
		String subjectList = Constants.SUBJECT_LIST;
		logger.debug("orgin subject list================={}", subjectList);
		String resultSubject = subjectList.replace("${user.openId}", openId).replace("${ctx}", context);
		logger.debug("result subject list===================={}", resultSubject);
		weChatPublicService.sendMsg(token, resultSubject);
	}

	@RequestMapping(value = "/coupons", method = RequestMethod.GET)
	public String coupons(String loginName, Model model) {

		return COUPONS;
	}
	
	private double formateDouble(double d){
		DecimalFormat format = new DecimalFormat("#.00");
		String str = format.format(d);
		return Double.parseDouble(str);
	}


	private WeiXinPacketAccount convert(UserInfo user) {
		WeiXinPacketAccount account = new WeiXinPacketAccount();
		account.setOpenId(user.getOpenid());
		account.setNickName(EmojiUtil.filterEmoji(user.getNickname()));
		account.setHeadImgUrl(user.getHeadimgurl());
		account.setSex(user.getSex());
		account.setCountry(user.getCountry());
		account.setProvince(user.getProvince());
		account.setCity(user.getCity());
		account.setUnionid(user.getUnionid());
		account.setLanguage(user.getLanguage());
		return account;
	}

	private BufferedImage loadImageLocal(String imgName) {  
        try {  
            return ImageIO.read(new File(imgName));  
        } catch (IOException e) {  
        }  
        return null;  
    } 
	
	private BufferedImage loadImageUrl(String imageName) {
		try {
			URL url = new URL(imageName);
			return ImageIO.read(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void writeImageLocal(String newImage, BufferedImage img) {
		File outFile = new File(newImage);
		try {
			ImageIO.write(img, "jpg", outFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void writeImageLocal(String newImage, String imageName) {
		BufferedImage bufferedImage = this.loadImageUrl(imageName);
		this.writeImageLocal(newImage, bufferedImage);
	}
	
	private void writeImageOutput(ImageOutputStream out, BufferedImage img){
		try {
			ImageIO.write(img, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void setFont(String fontStyle, int fontSize){
		this.fontSize = fontSize;
		this.font = new Font(fontStyle, Font.PLAIN, fontSize);
	}
	
	private BufferedImage modifyImage(BufferedImage img, Object cont, int x, int y){
		int w = img.getWidth();
		int h = img.getHeight();
		int len = cont.toString().length();
		g = img.createGraphics();
		g.setBackground(Color.WHITE);
		g.setColor(new Color(206, 63, 63));
		if(this.font != null){
			g.setFont(font);
		}
		if(x >= h || y >= w){
			this.x = h - this.fontSize + 2;
		}else{
		    if (len > 4) {
                this.x = x - (len - 3) * 10;
            } else if (len < 4) {
                this.x = x + len * 6;
            } else {
                this.x = x;
            }
            this.y = y;
		}
		if(cont != null){
			g.drawString(cont.toString(), this.x, this.y);
		}
		g.dispose();
		
		return img;
	}
	
	@SuppressWarnings("unused")
	private BufferedImage modifyImage(BufferedImage img, Object[] contentArr, int x, int y, boolean xory){
		int w = img.getWidth();
		int h = img.getHeight();
		g = img.createGraphics();
		g.setBackground(Color.WHITE);
		g.setColor(Color.RED);
		if(this.font != null){
			g.setFont(font);
		}
		if(x >= h || y >= w){
			this.x = h - this.fontSize + 2;
		}else{
			this.x = x;
			this.y = y;
		}
		if(contentArr != null){
			int arrlen = contentArr.length;
			if(xory){
				for(int i = 0; i < arrlen; i ++){
					g.drawString(contentArr[i].toString(), x, y);
					this.x += contentArr[i].toString().length() * this.fontSize / 2 + 5;
				}
			}else{
				for(int i = 0; i < arrlen; i++){
					g.drawString(contentArr[i].toString(), x, y);
					this.y += this.fontSize + 2;
				}
			}
		}
		return img;
	}
	
	@SuppressWarnings("unused")
	private BufferedImage modifyImageYe(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		g = img.createGraphics();
		g.setBackground(Color.WHITE);
		g.setColor(Color.blue);
		if(this.font != null){
			g.setFont(this.font);
			g.drawString("www.hi.baidu.com", w - 85, h-5);
			g.dispose();
		}
		return img;
	}
	
	private BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d, int x, int y, int subw, int subh){
		int w = b.getWidth() - subw;
		int h = b.getHeight() - subh;
		g = d.createGraphics();
		g.drawImage(b, x, y, w, h, null);
		return d;
	}
	
	/**
     * 头像高度固定
     * @param b
     * @param d
     * @param x
     * @param y
     * @param subw
     * @param subh
     * @return
     */
    public BufferedImage modifyHeaderImage(BufferedImage b, BufferedImage d, int x, int y, int subw, int subh){
        int w = 150;
        int h = 150;
        g = d.createGraphics();
        g.drawImage(b, x, y, w, h, null);
        return d;
    }
}
