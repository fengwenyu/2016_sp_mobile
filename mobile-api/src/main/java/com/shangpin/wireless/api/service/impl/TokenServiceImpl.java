package com.shangpin.wireless.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.dao.TokenDAO;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.Token;
import com.shangpin.wireless.api.service.TokenService;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.util.WeixinAccountConfig;
import com.shangpin.wireless.api.vo.WeixinTokenVO;

@Service(TokenService.SERVICE_NAME)
public class TokenServiceImpl implements TokenService {
	protected final Log log = LogFactory.getLog(TokenServiceImpl.class);
	@Resource(name = TokenDAO.SERVICE_NAME)
	private TokenDAO tokenDAO;

	@Override
	public void update(Token token) throws Exception{
		tokenDAO.update(token,"");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List find(String queySql) throws Exception {
		return tokenDAO.findByCondition(queySql);
	}

	@Override
	public Token save(Token token) throws Exception {
		return tokenDAO.save(token);
	}

	@Override
	public Token findToken() throws Exception {
		Token token = new Token();
		List list = find("from Token where status ='normal'");
		if(list != null && list.size() > 0){
			token = (Token) list.get(0);
		}
    	return token;
	}

	@Override
	public boolean findValidaeToken(String tokenCode) throws Exception{
		List list = find("from Token where code ='"+tokenCode+"' and unix_timestamp(efftime) > unix_timestamp(now())");
		if(list != null && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public synchronized void resetTokenStatus() {

    	try{
    		Token token = new Token();
    		List list = find("from Token");
    		if(list != null && list.size() > 0){
    			token = (Token) list.get(0);
    			token.setStatus("normal");
    			token.setLastUpdated(new Date());
    			update(token);
    			//保存日志
    			StringBuffer sb = new StringBuffer();
    			sb.append("服务器:")
    			  .append(Constants.SERVER_IP)
    			  .append(" 于")
    			  .append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
    			  .append("重置了Token的争抢！");
    			log.debug(sb.toString());
    		}
    	}catch (Exception e) {
    		log.error("resetTokenStatus :", e);
		}
    
		
	}

	@Override
	public synchronized Token updateToken() {
    	try{
    		//1. query status
    		List list = find("from Token");
    		if(list != null && list.size() > 0){
    			Token token = (Token) list.get(0);
    			if ("normal".equals(token.getStatus())){
    				String serverId = Constants.SERVER_IP;
    				//2. try get this task
    				token.setStatus("renew");
    				token.setServerId(serverId);
    				update(token);//保存serverId(服务器ID)是为了记录哪台服务器抢到了资源，如果出现异常，需要记录日志并报警
    				WeixinTokenVO code = getAccessToken();
    				if(code.getAccess_token() != null && !"".equals(code.getAccess_token())){
    					List updatList = find("from Token where status='renew' and serverId='" + serverId + "'");
    					if(updatList != null && updatList.size() > 0){
    						Token updateToken = (Token) updatList.get(0);
    						updateToken.setCode(code.getAccess_token());
    						
    						Date now = new Date();
    						Calendar calendar = Calendar.getInstance();
    						Long expires = new Long(code.getExpires_in()) * 1000;
    						long millis = now.getTime() + expires;
							calendar.setTimeInMillis(millis );
							
							updateToken.setLastUpdated(now);
							updateToken.setEfftime(calendar.getTime());
    						updateToken.setStatus("normal");
    						update(updateToken);
    						return updateToken;
    					}
    				}
    			}
    		}else{
    			WeixinTokenVO code = getAccessToken();
    			if(code.getAccess_token() != null && !"".equals(code.getAccess_token())){
    				Token token =new Token();
    				token.setCode(code.getAccess_token());
    				Date now = new Date();
    				Calendar calendar = Calendar.getInstance();
    				Long expires = new Long(code.getExpires_in()) * 1000;
    				long millis = now.getTime() + expires;
    				calendar.setTimeInMillis(millis );
    				token.setLastUpdated(now);
    				token.setEfftime(calendar.getTime());
    				token.setStatus("normal");
    				return save(token);
				}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		//记录错误日志
    		log.error("An exception  " + e + " has been thrown in " + TokenServiceImpl.class+ " updateToken()");
    	}
    	
    	return null;
    }
	
	 /**
     * 获取微信Token
     */
    private synchronized WeixinTokenVO getAccessToken() {
    	WeixinTokenVO weixinToken = null;
    	String accUrl = WeixinAccountConfig.getAccValue("acc_url");
    	String appid = WeixinAccountConfig.getAccValue("appid");
    	String secret = WeixinAccountConfig.getAccValue("secret");
    	String grant_type = WeixinAccountConfig.getAccValue("grant_type");
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("appid", appid);
    	map.put("secret", secret);
    	map.put("grant_type", grant_type);
    	String data = null;
    	try {
			// 获取微信Token数据
			data =  WebUtil.readContentFromGet(accUrl, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			String access_token = jsonObj.getString("access_token");
			weixinToken = new WeixinTokenVO();
			if(access_token != null && !"".equals(access_token)){
				weixinToken.setAccess_token(access_token);
				String expires = jsonObj.getString("expires_in");
				weixinToken.setExpires_in(expires);
			}else{
				weixinToken.setErrmsg(jsonObj.getString("errmsg"));
				weixinToken.setErrcode(jsonObj.getString("errcode"));
			}
		}
        return weixinToken;
    }
}
