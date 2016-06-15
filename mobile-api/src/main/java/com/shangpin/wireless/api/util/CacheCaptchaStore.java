package com.shangpin.wireless.api.util;

import java.util.Collection;
import java.util.Locale;

import com.octo.captcha.Captcha;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.CaptchaAndLocale;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.shangpin.biz.utils.ApiBizData;

public class CacheCaptchaStore implements CaptchaStore {

	@Override
	public void cleanAndShutdown() {

	}

	@Override
	public void empty() {

	}

	@Override
	public Captcha getCaptcha(String id) throws CaptchaServiceException {
		CaptchaAndLocale captchaAndLocale;
		try {
			captchaAndLocale = ApiBizData.findCaptcha(id);
		} catch (Exception e) {
			captchaAndLocale = null;
			e.printStackTrace();
		}
		return captchaAndLocale != null ? (captchaAndLocale.getCaptcha()) : null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Collection getKeys() {
		return null;
	}

	@Override
	public Locale getLocale(String id) throws CaptchaServiceException {
		CaptchaAndLocale captchaAndLocale;
		try {
			captchaAndLocale = ApiBizData.findCaptcha(id);
		} catch (Exception e) {
			captchaAndLocale = null;
			e.printStackTrace();
		}
		return captchaAndLocale != null ? (captchaAndLocale.getLocale()) : null;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public boolean hasCaptcha(String id) {
		CaptchaAndLocale captcha;
		try {
			captcha = ApiBizData.findCaptcha(id);
		} catch (Exception e) {
			captcha = null;
			e.printStackTrace();
		}
		return captcha == null ? false : true;
	}

	@Override
	public void initAndStart() {

	}

	@Override
	public boolean removeCaptcha(String id) {
		return false;
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha) throws CaptchaServiceException {
		ApiBizData.cacheCaptcha(id, new CaptchaAndLocale(captcha));
	}

	@Override
	public void storeCaptcha(String id, Captcha captcha, Locale locale) throws CaptchaServiceException {
		ApiBizData.cacheCaptcha(id, new CaptchaAndLocale(captcha, locale));
	}

}
