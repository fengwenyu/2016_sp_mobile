package com.shangpin.mobileShangpin.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;

/**
 * 活动商品列表页中活动的宣传图片
 * 
 * @author Administrator
 * 
 */
public class ActivityNameTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4042062931490405758L;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() {
		// 获取渠道号
		String channelNo = pageContext.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		try {
			String prop = null;
			// 在缓存中获取注册提示信息
			if (DataContainerPool.topicConfigContainer.getQueueSize() > 0) {
				Object obj = DataContainerPool.topicConfigContainer.get(channelNo);
				if (null != obj) {
					TopicConfig bean = (TopicConfig)obj;
					prop = bean.getName();
				}
			}

			if(StringUtils.isEmpty(prop)){
				prop = "尚品活动";
			}
			pageContext.getOut().write(prop);// 标签的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
