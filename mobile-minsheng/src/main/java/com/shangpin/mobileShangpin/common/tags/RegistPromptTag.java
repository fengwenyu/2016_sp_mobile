package com.shangpin.mobileShangpin.common.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.shangpin.mobileShangpin.businessbean.TopicConfig;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;

/**
 * 通过不同活动，显示相应的注册提示内容
 * 
 * @author Administrator
 * 
 */
public class RegistPromptTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8979986208660481910L;
	private String type;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doStartTag() {
		// 获取渠道号
		String channelNo = pageContext.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		try {
			StringBuffer buffer = new StringBuffer();
			String registDesc = null;
			// 在缓存中获取注册提示信息
			if (DataContainerPool.topicConfigContainer.getQueueSize() > 0) {
				// 通过渠道号在缓存中获取活动
				Object obj = DataContainerPool.topicConfigContainer.get(channelNo);
				if (null != obj) {
					TopicConfig topicConfig = (TopicConfig) obj;
					registDesc = topicConfig.getRegistDesc();
				}
			}
			// 登录、注册页面显示的注册提示信息
			if ("regist".equals(type)) {
				if (StringUtils.isEmpty(registDesc)) {
					buffer.append("<legend>");
					buffer.append("如您在电脑上已注册可以直接登录，尚品网账号可直接登录。");
				} else {
					buffer.append("<legend style=\"color:red;\">");
					buffer.append(registDesc);
				}
				buffer.append("</legend>");
			} else if ("login".equals(type)) {
				if (StringUtils.isEmpty(registDesc)) {
					registDesc = "免费注册>";
				}
				buffer.append(registDesc);
			}
			pageContext.getOut().write(buffer.toString());// 标签的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
