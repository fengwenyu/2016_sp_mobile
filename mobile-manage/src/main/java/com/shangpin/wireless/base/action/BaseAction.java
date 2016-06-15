package com.shangpin.wireless.base.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shangpin.wireless.api.service.*;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.manage.service.*;
import com.shangpin.wireless.util.JsonUtil;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.service.AolaiActivityService;
import com.shangpin.wireless.api.service.AppNavigationService;
import com.shangpin.wireless.api.service.AppPicturesService;
import com.shangpin.wireless.api.service.FindManageService;
import com.shangpin.wireless.api.service.ErrorLogService;
import com.shangpin.wireless.api.service.FeedbackService;
import com.shangpin.wireless.api.service.HotBrandsService;
import com.shangpin.wireless.api.service.OnlineManageService;
import com.shangpin.wireless.api.service.PushManageAndroidService;
import com.shangpin.wireless.api.service.PushManageIosService;
import com.shangpin.wireless.api.service.WXFashionInformationService;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.manage.service.ChannelService;
import com.shangpin.wireless.manage.service.DeviceInfoService;
import com.shangpin.wireless.manage.service.MenuService;
import com.shangpin.wireless.manage.service.NewProductBrandReService;
import com.shangpin.wireless.manage.service.OperateLogService;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.manage.service.PrivilegeService;
import com.shangpin.wireless.manage.service.ProductService;
import com.shangpin.wireless.manage.service.PushDictionaryService;
import com.shangpin.wireless.manage.service.RoleService;
import com.shangpin.wireless.manage.service.UserService;

/**
 * @Author zhouyu
 * @CreateDate 2012-07-11
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	protected final Log log = LogFactory.getLog(BaseAction.class);
	@Resource(name = RoleService.SERVICE_NAME)
	protected RoleService roleService;
	@Resource(name = UserService.SERVICE_NAME)
	protected UserService userService;
	@Resource(name = PrivilegeService.SERVICE_NAME)
	protected PrivilegeService privilegeService;
	@Resource(name = OperateLogService.SERVICE_NAME)
	protected OperateLogService operateLogService;
	@Resource(name = ChannelService.SERVICE_NAME)
	protected ChannelService channelService;
	@Resource(name = ProductService.SERVICE_NAME)
	protected ProductService productService;
	@Resource(name = PushDictionaryService.SERVICE_NAME)
	protected PushDictionaryService pushDictionaryService;
	@Resource(name = ErrorLogService.SERVICE_NAME)
	protected ErrorLogService errorLogService;
	@Resource(name = OnlineManageService.SERVICE_NAME)
	protected OnlineManageService onlineManageService;
	@Resource(name = FeedbackService.SERVICE_NAME)
	protected FeedbackService feedbackService;
	@Resource(name = OrderStatisticsService.SERVICE_NAME)
	protected OrderStatisticsService orderStatisticsService;
	@Resource(name = PushManageIosService.SERVICE_NAME)
	protected PushManageIosService pushManageIosService;
	@Resource(name = PushManageAndroidService.SERVICE_NAME)
	protected PushManageAndroidService pushManageAndroidService;
	@Resource(name = AccountService.SERVICE_NAME)
	protected AccountService accountService;
	@Resource(name = DeviceInfoService.SERVICE_NAME)
	protected DeviceInfoService deviceInfoService;
	@Resource(name = AccountStatisticsService.SERVICE_NAME)
	protected AccountStatisticsService accountStatisticsService;
	@Resource(name = NewProductBrandReService.SERVICE_NAME)
	protected NewProductBrandReService newProductBrandReService;
	@Resource(name = HotBrandsService.SERVICE_NAME)
	protected HotBrandsService hotBrandsService;
	
	@Resource(name = FindManageService.SERVICE_NAME)
	protected FindManageService findManageService;
	
	@Resource(name = WXFashionInformationService.SERVICE_NAME)
	protected WXFashionInformationService wxFashionInformationService;
	
	@Resource(name = AppPicturesService.SERVICE_NAME)
	protected AppPicturesService appPicturesService;
	@Resource(name = AppNavigationService.SERVICE_NAME)
	protected AppNavigationService appNavigationService;
	@Resource(name = AolaiActivityService.SERVICE_NAME)
	protected AolaiActivityService aolaiActivityService;
	@Resource(name = AutoReplyService.SERVICE_NAME)
	protected AutoReplyService autoReplyService;
	@Resource(name = MenuService.SERVICE_NAME)
	protected MenuService menuService;	
	protected User userLogin = (User) ActionContext.getContext().getSession().get("user");
	
	protected T model;
	private Class<T> modelClass;

	protected ActionContext context= ActionContext.getContext();
	protected HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
	protected HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

	public void OutPrint(Object object){
		try {
			response.getWriter().print(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BaseAction() {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.modelClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public T getModel() {
		if (model == null) {
			try {
				model = (T) modelClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return model;
	}

	/**
	 * 获取当前登录的用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param
	 * @Return User
	 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 *保存上传的文件，并返回文件存储的路径
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-23
	 * @param inData
	 *            上传的字节
	 * @param path
	 *            文件保存路径
	 * @throws IOException
	 * @Return:String
	 */
	public String saveUploadFile(byte[] inData, String basePath, String fileName) {
		try {
			File dir = new File(basePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String path = basePath + File.separator + fileName;
			OutputStream out = new FileOutputStream(path);
			out.write(inData);
			out.close();
			return "success";
		} catch (Exception e) {
			log.error("BaseAction-saveUploadFile()" + e);
			return "error";
		}
	}

	/**
	 *下载文件
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-07-30
	 * @param path
	 *            文件保存路径
	 * @Return:
	 */
	public void downloadFile(String downloadPath, InputStream in, OutputStream out) {
		try {
			out = ServletActionContext.getResponse().getOutputStream();
			Streams.copy(in, out, true);
		} catch (Exception e) {
			log.error("UploadAndDownload-downloadFile()" + e);
		}
	}

	protected int pageNum = 1;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
