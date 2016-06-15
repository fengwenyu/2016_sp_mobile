package com.shangpin.biz.service.abstraction;

import java.util.Set;

import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JedisUtil;


/**
 * 通用的服务，和业务没什么关系
 * @author qinyingchun
 *
 */
public class AbstractBizCommonHanderService {
	
	public boolean IsUseCombinePay(String member){
		return JedisUtil.getInstance().new Sets().sismember(Constants.IS_USE_COMBINE_PAY, member);
	}
	
	public Set<String> useCombinePay(){
		return JedisUtil.getInstance().new Sets().smembers(Constants.IS_USE_COMBINE_PAY);
	}
	
	public void add(String member){
		JedisUtil.getInstance().new Sets().sadd(Constants.IS_USE_COMBINE_PAY, member);
	}
	
	public void remove(String member){
		JedisUtil.getInstance().new Sets().srem(Constants.IS_USE_COMBINE_PAY, member);
	}

}
