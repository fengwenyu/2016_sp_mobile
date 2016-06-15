package com.shangpin.biz.service.abstraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.shangpin.base.service.GalleryService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.Gallery;
import com.shangpin.biz.utils.Constants;
import com.shangpin.biz.utils.PicCdnHash;
import com.shangpin.product.model.cbwfs.MobileAlterPic;
import com.shangpin.product.model.common.ContentBuilder;

public abstract class AbstractBizGalleryService extends AbstractBizCommonService {

	public static Logger logger = LoggerFactory.getLogger(AbstractBizGalleryService.class);

	@Autowired
	ShangPinService shangPinService;
	@Autowired
	private GalleryService galleryService;

	/**
	 * 轮播图
	 * 
	 * @param type
	 *            类型 1：新品轮播；2：商城轮播；3：首页轮播
	 * @return
	 * @author wangfeng
	 */
	public String queryGalleryToResult(String type, String frames) {
		try {
			String json = shangPinService.queryGalleryList(type, frames);
			logger.debug("调用base接口返回数据:" + json);
			if (!StringUtils.isEmpty(json)) {
				return json;
			}
		} catch (Exception e) {
			logger.error("调用base接口返回数据发生错误！");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 轮播图，移动端可以共用
	 * @param type 轮播图的位置
	 * @return
	 */
	public List<Gallery> galleries(String type){
		long start = System.currentTimeMillis();
		List<Gallery> galleries = new ArrayList<Gallery>();
		ContentBuilder<Map<String, List<MobileAlterPic>>> contentBuilder = galleryService.gallery(type);
		if(null != contentBuilder && Constants.SUCCESS.equals(contentBuilder.getCode())){
			List<MobileAlterPic> mobileAlterPics = contentBuilder.getContent().get("list");
			if(null != mobileAlterPics && mobileAlterPics.size() > 0){
				for(MobileAlterPic mobileAlterPic : mobileAlterPics){
					if(null != mobileAlterPic){
						Gallery gallery = new Gallery();
						gallery.setName(mobileAlterPic.getName());
						gallery.setType(convertType(mobileAlterPic.getRefType()));
						gallery.setRefContent(mobileAlterPic.getRefContent());
						gallery.setPic(PicCdnHash.getPicUrl(mobileAlterPic.getPicNo(), "2"));
						galleries.add(gallery);
					}
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("gallery==================" + (end - start) + "ms");
		return galleries;
	}
	
	/**
	 * 后台服务查询出来的通用规则需要转换
	 * @param type 0活动，1h5链接，2品牌，3品类，4详情
	 * @return 1活动，2品类，3品牌，4详情，5h5页面
	 */
	private String convertType(Short type){
		switch (type) {
		case 0:
			return "1";
		case 1:
			return "5";
		case 2:
			return "3";
		case 3:
			return "2";
		case 4:
			return "4";
		default:
			return "";
		}
	}

}
