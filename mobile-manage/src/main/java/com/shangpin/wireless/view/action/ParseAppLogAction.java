package com.shangpin.wireless.view.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.wireless.util.DateUtil;
import com.shangpin.wireless.util.ParseAppLogUtil;
import com.shangpin.wireless.util.StringUtils;
/**
 * 解析用户行为日志
 * 
 * @Author liling
 * @CreatDate 2013-12-06
 */
@Controller
public class ParseAppLogAction extends ActionSupport {
	
	private static final long serialVersionUID = -8183531988855036341L;
	private String date;
	
	public String parse() throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotEmpty(date)) {
				Date five = sdf.parse(date);
//				for (int i = 0; i < 31; i++) {
					String date = DateUtil.getAfterAmountDay(five, 0, "yyyy-MM-dd");
					ArrayList<String> directoryNamelist=new ArrayList<String>();
					String basePath="/home/parselog/app_user_logs/";
					File rootFile = new File(basePath+date);
					if(rootFile.exists()){
						directoryNamelist=ParseAppLogUtil.getDirectoryNamelist(rootFile);
						for (int n=0;n<directoryNamelist.size();n++){
							String path=basePath+date+"/"+directoryNamelist.get(n);
							ParseAppLogUtil.parseOnedayLog(path);
							
						}
						
					}
					
//					}
					ActionContext.getContext().getValueStack().set("msg", "您的日志文件已解析成功");
				}
//			}
		} catch (Exception e) {
			ActionContext.getContext().getValueStack().set("msg", "您的日志文件解析出错了");
		}
		return "parseAppLogSuccess";
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	
}
