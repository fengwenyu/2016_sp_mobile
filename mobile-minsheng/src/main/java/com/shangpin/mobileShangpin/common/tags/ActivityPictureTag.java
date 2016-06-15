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
public class ActivityPictureTag extends TagSupport {
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
		String topicid  = pageContext.getRequest().getParameter("topicid");
		try {
			String pic = null;
			// 在缓存中获取注册提示信息
			if (DataContainerPool.topicConfigContainer.getQueueSize() > 0) {
				// 通过渠道号在缓存中获取活动
				Object obj = DataContainerPool.topicConfigContainer.get(channelNo);
				if (null != obj) {
					TopicConfig topicConfig = (TopicConfig) obj;
					if(topicConfig.getId().equals(topicid)){
						
						pic = topicConfig.getImg();
					}
				}
			}
			// 登录、注册页面显示的注册提示信息
			if (StringUtils.isNotEmpty(pic) && !pic.startsWith("http://")) {
				pic = pageContext.getSession().getServletContext().getContextPath() + pic;
			}
			if("20130724600".equals(topicid)){
				pic=pageContext.getSession().getServletContext().getContextPath()+"/images/weixin.jpg";
			}
			pageContext.getOut().write(pic);// 标签的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
