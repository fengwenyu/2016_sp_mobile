package com.shangpin.wireless.api.api2client.domain;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.api.domain.AppPictures;
import com.shangpin.wireless.api.domain.OS;
import com.shangpin.wireless.api.service.AppPicturesService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.StringUtil;

public class AppPicturesAPIResponse extends CommonAPIResponse {

	private AppPicturesService appPicturesService;
	private String os;
	private String wh;
	private List<AppPictures> list;
	private String code = "0";
	private String msg = "查询成功";
	private String w;
	private String h;

	public AppPicturesAPIResponse() {
		ApplicationContext ac = ApplicationContextUtil.get("ac");
		appPicturesService = (AppPicturesService) ac.getBean("appPicturesService");
	}

	@Override
	public String obj2Json(String producType) {
		JSONObject clientData = new JSONObject();
		try {
			if (producType.equals("shangpin")) {
				getAppPictures();
			} else {
				getAolaiAppPictures();
			}

			JSONObject content = new JSONObject();
			if (list != null && list.size() > 0) {
				AppPictures appPictures = list.get(0);
				String imgUrl = appPictures.getImgUrl();
				if(!StringUtil.isNotEmpty(w, h)){
				    w="{w}";
				    h="{h}";
				}
				if (StringUtil.isNotEmpty(w, imgUrl)) {
					imgUrl = imgUrl.replace("{w}", w).replace("{h}", h);
					content.put("picurl", imgUrl);
				} else {
					content.put("picurl", "");
				}

			} else {
				msg = "没有数据";
				content.put("picurl", "");
			}
			clientData.put("code", code);
			clientData.put("msg", msg);
			clientData.put("content", content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientData.toString();
	}

	private void getAppPictures() {
		try {
			if (os != null) {
				if (os.equals("android")) {
					list = appPicturesService.findOne(OS.ANDRIODSTART.toString());
				} else if (os.equals("ios")) {
					if (wh != null) {
						if (wh.equals("640x960") || wh.equals("320x480")) {
							w = wh.substring(0, wh.indexOf("x"));
							h = wh.substring(wh.indexOf("x") + 1);
							list = appPicturesService.findOne(OS.IPHONE4START.toString());
						} else {// iphone5,6,plus都取这个图
							w = wh.substring(0, wh.indexOf("x"));
							h = wh.substring(wh.indexOf("x") + 1);
							list = appPicturesService.findOne(OS.IPHONE5START.toString());
						}
					} else {
						code = "-3";
						msg = "查询出错！";
					}
				} else {
					code = "-3";
					msg = "查询出错！";
				}
			} else {
				code = "-3";
				msg = "查询出错！";
			}
		} catch (Exception e) {
		}
	}

	private void getAolaiAppPictures() {
		try {
			if (os != null) {
				if (os.equals("android")) {
					list = appPicturesService.findAolaiOne(OS.ANDRIODSTART.toString());
				} else if (os.equals("ios")) {
					if (wh != null) {
						if (wh != null && (wh.equals("640x960") || wh.equals("320x480"))) {
							w = wh.substring(0, wh.indexOf("x"));
							h = wh.substring(wh.indexOf("x") + 1);
							list = appPicturesService.findAolaiOne(OS.IPHONE4START.toString());
						} else {
							w = wh.substring(0, wh.indexOf("x"));
							h = wh.substring(wh.indexOf("x") + 1);
							list = appPicturesService.findAolaiOne(OS.IPHONE5START.toString());
						}
					} else {
						code = "-3";
						msg = "查询出错！";
					}
				} else {
					code = "-3";
					msg = "查询出错！";
				}
			} else {
				code = "-3";
				msg = "查询出错！";
			}
		} catch (Exception e) {
		}
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

}
