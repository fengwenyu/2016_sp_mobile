package com.shangpin.api.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.shangpin.api.utils.Constants;
import com.shangpin.api.utils.FTPUtil;
import com.shangpin.api.utils.MD5Util;
import com.shangpin.api.utils.ValidateUtil;
import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.QuickUser;
import com.shangpin.biz.bo.SourceEnum;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.service.ASPBizCommentService;
import com.shangpin.biz.service.ASPBizUserService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.FtpUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;

/**
 * 用户控制层
 * 
 * @author zghw
 */
@Controller
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);
    // 分众方约定的key
    private static final String PRESHAREKEY = "7AAEECF50664798ACDCFB422D70BABF7";

    @Autowired
    private ASPBizUserService userService;
 
    @Autowired
    private ASPBizCommentService commentService;

    /**
     * 发送手机验证码接口
     * 
     * @author zghw
     */
    @ResponseBody
    @RequestMapping(value = "/sendPhoneCode", method = { RequestMethod.POST,
            RequestMethod.GET })
    public ContentBuilder<Map<String, String>> sendPhoneCode( HttpServletRequest request) {
        ContentBuilder<Map<String, String>> builder = new ContentBuilder<Map<String, String>>();
        String imei = request.getHeader("imei");
        String productNum = request.getHeader("p");
        final String version = request.getHeader("ver");
        Map<String, String> content = new HashMap<String, String>();
        
        String phone = "";
        String source = "";
        if (StringUtil.compareVersion("2.9.9", version) == 1) {
        	Map<String, String> params = new ConcurrentHashMap<String, String>(8);

    		try {
    			params = AESUtil.base64ZipAES(request.getParameterMap().toString());
    		} catch (Exception e2) {
    			e2.printStackTrace();
    		}
    		
    		phone = params.get("phone");
    		source = params.get("source");
        }else{
        	phone = request.getParameter("phone");
    		source = request.getParameter("source");
        }
        
        
        // 非空判断
        if (!StringUtil.isNotEmpty(phone, source, imei, productNum)) {
            return builder.buildContent(Constants.ERROR_PARAM_CODE,
                    Constants.ERROR_PARAM_MSG, content);
        }
        // 验证手机号码是否正确
        if (!ValidateUtil.isMobileNumber(phone)) {
            return builder.buildDefError("手机号码格式不正确!", content);
        }
        try {
            // 来自手机号码+验证码登录
            if (source.equals("1")) {
                String msgTemplate = "短信验证码：{$verifyCode$}，请勿向任何人提供您收到的短信验证码。";
                // 发送手机号码
                ResultBase obj = userService.beSendVerifyCode(phone, phone,
                        msgTemplate);
                if (obj != null && obj.isSuccess()) {
                    // 发送成功
                    // 存储该手机发送次数信息
                    userService.saveSendPhoneInfo(phone, imei, SourceEnum.Ql);
                    // 取得是否打开验证码
                    String isOpenCheck = userService.isOpenCheck(phone, imei,
                            SourceEnum.Ql) ? "0" : "1";
                    content.put("isOpenCheck", isOpenCheck);
                    return builder.buildDefSuccess(content);
                }
                return builder.buildDefError("发送短信失败。", content);
            }
            return builder.buildDefError("发送来源不支持。", content);
        } catch (Exception e) {
            logger.error("error:", e);
            return builder.buildContent(Constants.ERROR_SYSTEM_CODE,
                    Constants.ERROR_SYSTEM_MSG, content);
        }
    }

    /**
     * 手机加验证码登录
     * 
     * @author zghw
     */
    @ResponseBody
    @RequestMapping(value = "/smscodeLogin", method = { RequestMethod.POST,
            RequestMethod.GET })
    public ContentBuilder<User> smscodeLogin(String phone, String smscode,
            String imgcode, HttpServletRequest request) {
        ContentBuilder<User> builder = new ContentBuilder<User>();
        String imei = request.getHeader("imei");
        String productNum = request.getHeader("p");
        String channelNo = request.getHeader("ch");// 渠道号
        User content = new User();
        logger.info("imei:"+imei+"=>"+"productNum:"+productNum+"=>channelNo:"+channelNo);
        // 非空判断
        if (!StringUtil.isNotEmpty(phone, smscode, imei, productNum)) {
            return builder.buildContent(Constants.ERROR_PARAM_CODE,
                    Constants.ERROR_PARAM_MSG, content);
        }

        // 验证手机号码是否正确
        if (!ValidateUtil.isMobileNumber(phone)) {
            return builder.buildDefError("手机号码格式不正确。", content);
        }
        try {
            // 是否发送过验证码给该手机
            boolean isSended = userService.getSendCount(phone, imei,
                    SourceEnum.Ql) > 0 ? true : false;
            if (!isSended) {
                return builder.buildDefError("未给该手机发送短信验证码！", content);
            }
            // 验证码是否在打开状态
            boolean isOpenCheck = userService.isOpenCheck(phone, imei,
                    SourceEnum.Ql);
            if (isOpenCheck) { // 判断验证码
                if (StringUtils.isBlank(imgcode)) {
                    return builder.buildContent("3", "请输入图片验证码!", content);
                }
                ImageCaptchaService imageCaptchaService = getImageCaptchaService(request);
                boolean isEqual = imageCaptchaService.validateResponseForID(
                        imei, imgcode.toUpperCase());
                if (!isEqual) {
                    return builder.buildDefError("验证码输入有误，请重新输入。", content);
                }
            }
            // 验证手机验证码
            ResultBase obj = userService.beVerifyPhoneCode(phone, phone,
                    smscode);
            if (obj == null || (!obj.isSuccess())) {
                return builder.buildDefError("手机验证码不正确。", content);
            }
            // 通过手机号判断是否是新用户
            QuickUser quickUser = userService.checkPhoneUser(phone, channelNo);
            logger.info("quickUser:", JsonUtil.toJson(quickUser));
            if (quickUser != null) {
                if ("1".equals(quickUser.getIsNewUser())) {
                    // 如果是新用户则下发短信。
                    String msgTemplate = "您好，您已注册成为尚品网用户，将享受尚品网会员权益。您可使用本手机号登录，默认密码为手机号后6位，请及时修改密码。";
                    userService.beSendVerifyCode(phone, phone, msgTemplate);
                }
                String userId = quickUser.getUserId();
                // 查询用户信息对象
                content = userService.findUserByUserId(userId);
                
                //设置默认头像开始
                Map<String, String> iconMap = new HashMap<String, String>();
    			iconMap.put("普通会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrKAE-1MAAATatXinY0856.png");
    			iconMap.put("黄金会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrCAR-m-AAAZs-ncDX8318.png");
    			iconMap.put("白金会员", "http://pic5.shangpin.com/group1/M00/D7/05/rBQKaFaKCrOAZOYiAAAaEcJFeUk549.png");
    			iconMap.put("钻石会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrGAHGORAAAbrQ3CN4c900.png");
    			String icon = content.getIcon();
    			String keyLv = content.getLevel().trim();
    			if(StringUtils.isBlank(icon)){
    				icon = iconMap.get(keyLv);
    				if(icon==null){
    					icon="";
    				}
    				content.setIcon(icon);
    			}
    			//设置默认头像结束
    			
                String sessionId = generateSessionId();
                content.setSessionid(sessionId);
                logger.info("user:", JsonUtil.toJson(content));
                if (content != null) {
                    // 更新用户表
                    // userService.synchronizationAccount(content, channelNo,
                    // productNum, request);
                }
                logger.info(JsonUtil.toJson(builder.buildDefSuccess("success", content)));
                return builder.buildDefSuccess("success", content);
            }
            return builder.buildDefError("登录失败!", content);
        } catch (Exception e) {
            logger.error("error:", e);
            return builder.buildContent(Constants.ERROR_SYSTEM_CODE,
                    Constants.ERROR_SYSTEM_MSG, content);
        }
    }

    /**
     * 用户行为上传
     * 
     * @param request
     * @return
     * @author zghw
     */
    @ResponseBody
    @RequestMapping(value = "/behaviorUpload", method = { RequestMethod.POST })
    public ContentBuilder<Map<String, String>> behaviorUpload(
            HttpServletRequest request) {
        String imei = request.getHeader("imei");
        String productNum = request.getHeader("p");
        String channelNo = request.getHeader("ch");// 渠道号
        ContentBuilder<Map<String, String>> builder = new ContentBuilder<Map<String, String>>();
        Map<String, String> content = new HashMap<String, String>();
        // 非空判断
        if (!StringUtil.isNotEmpty(imei, productNum, channelNo)) {
            return builder.buildContent(Constants.ERROR_PARAM_CODE,
                    Constants.ERROR_PARAM_MSG, content);
        }
        FTPClient ftp = new FTPClient();
        try {
            // 打开FTP链接
            boolean isCon = FTPUtil.connectFtp(ftp);
            if (!isCon) {
                return builder.buildDefError("程序异常,上传失败！", content);
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                return builder.buildDefError("上传的文件不支持!", content);
            }
            if (isMultipart) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload sfUpload = new ServletFileUpload(factory);
                sfUpload.setHeaderEncoding("UTF-8");
                List<FileItem> fileItems = sfUpload.parseRequest(request); // 这里取到的fileItems为空
                Iterator<FileItem> iter = fileItems.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        String fileName = item.getName().trim();
                        // 验证传入的格式是否正确
                        boolean isValidFileName = FTPUtil.isValidFileName(imei,
                                channelNo, fileName);
                        if (!isValidFileName) {
                            return builder.buildDefError(
                                    "上传的文件不支持!" + fileName, content);
                        }

                        if (fileName.trim() != "") {
                            // 重命名上传后的文件名
                            // 定义上传路径
                            String newFileName = imei + "_"
                                    + System.currentTimeMillis() + "_"
                                    + fileName;
                            String dir = "/home/behavior/";
                            File file = new File(dir);
                            if (!file.exists() && !file.isDirectory()) {
                                // 文件夹不存在则创建
                                file.mkdir();
                            }
                            String path = dir + newFileName;
                            File localFile = new File(path);
                            // 下载到本地
                            item.write(localFile);
                            // FTP服务器上传
                            FtpUtil.upload(localFile, ftp);
                            // 删除文件
                            localFile.delete();
                            logger.info("upload file: " + newFileName
                                    + " success!");
                            return builder.buildDefSuccess("上传成功！", content);
                        }
                    }
                }
            }
            /*
             * // 创建一个通用的多部分解析器 CommonsMultipartResolver multipartResolver = new
             * CommonsMultipartResolver
             * (request.getSession().getServletContext()); // 判断 request
             * 是否有文件上传,即多部分请求 if (multipartResolver.isMultipart(request)) { //
             * 转换成多部分request MultipartHttpServletRequest multiRequest =
             * (MultipartHttpServletRequest) request; // 取得request中的所有文件名
             * Iterator<String> iter = multiRequest.getFileNames(); // 打开FTP链接
             * boolean isCon = FTPUtil.connectFtp(); if (!isCon) { return
             * builder.buildDefError("程序异常,上传失败！", content); } while
             * (iter.hasNext()) { // 取得上传文件 MultipartFile file =
             * multiRequest.getFile(iter.next()); // 上传文件不能为空 if
             * (file.isEmpty()) { return builder.buildDefError("上传文件不能为空!",
             * content); } String fileName = file.getOriginalFilename(); //
             * 验证传入的格式是否正确 boolean isValidFileName =
             * FTPUtil.isValidFileName(imei,channelNo, fileName); if
             * (!isValidFileName) { return builder.buildDefError("上传的文件不支持!" +
             * fileName, content); } if (fileName.trim() != "") { // 重命名上传后的文件名
             * // 定义上传路径 String
             * newFileName=imei+"_"+System.currentTimeMillis()+"_"+ fileName;
             * String path = "/home/behavior/" +newFileName; File localFile =
             * new File(path); // 下载到本地 file.transferTo(localFile); // FTP服务器上传
             * FtpUtil.upload(localFile); //删除文件 localFile.delete();
             * logger.info("upload file: " + newFileName + " success!"); } }
             * return builder.buildDefSuccess("上传成功！", content); }
             */
        } catch (Exception e) {
            e.printStackTrace();
            return builder.buildDefError("程序异常,上传失败！", content);
        } finally {
            FtpUtil.closeFtp(ftp);
        }
        return builder.buildDefError("上传失败！", content);
    }

    /**
     * 取得验证码
     * 
     * @param request
     * @param response
     * @author zghw
     */
    @RequestMapping(value = "/getCaptcha")
    public void getCaptcha(HttpServletRequest request,
            HttpServletResponse response) {
        String captchaId = request.getHeader("imei");
        ServletOutputStream out = null;
        try {
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            if (!ApiBizData.invalidKey(captchaId)) {
                ApiBizData.deleteKey(captchaId);
            }
            ImageCaptchaService imageCaptchaService = getImageCaptchaService(request);
            BufferedImage challenge = imageCaptchaService
                    .getImageChallengeForID(captchaId, request.getLocale());
            ImageIO.write(challenge, "jpg", out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("GetCaptchaServlet:" + e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 取得图片验证码接口实例
     * 
     * @param request
     * @return
     * @author zghw
     */
    private ImageCaptchaService getImageCaptchaService(
            HttpServletRequest request) {
        WebApplicationContext ctx = getCtx(request);
        ImageCaptchaService ics = (ImageCaptchaService) ctx
                .getBean("imageCaptchaService");
        return ics;
    }

    private static final String generateSessionId() {
        Random ran = new Random(System.currentTimeMillis());

        final int bits = 24;
        StringBuilder strBuff = new StringBuilder();
        for (int i = bits - 1; i >= 0; i--) {
            final int value = ran.nextInt(10 + 26);
            if (value < 10) {
                strBuff.append(value);
            } else if (value < 10 + 26) {
                strBuff.append((char) ('A' + (value - 10)));
            }
        }

        return strBuff.toString();
    }

    /**
     * 分众掉APP接口
     * 
     * @param accounts
     * @param nonce
     * @param sign
     * @param request
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String queryIsRegisterApp(@RequestParam String accounts,
            @RequestParam String nonce, @RequestParam String sign,
            HttpServletRequest request) {
        try {
            // 非空判断
            if (!StringUtil.isNotEmpty(accounts, nonce, sign)) {
                logger.info("参数不可以为空");
                return returnExternalError();
            }
            String proSign = MD5Util.md5PackParameter(nonce, accounts,
                    PRESHAREKEY);
            // 通过sign值验证请求后
            if (StringUtils.equals(sign, proSign)) {
                // 获得用户信息状态
                String jsons = userService.checkUserList(accounts);
                logger.info("接口返回的:" + jsons);
                JSONObject obj = JSONObject.fromObject(jsons);
                if (StringUtil.isNotEmpty(jsons)
                        && (StringUtils.equals(
                                com.shangpin.biz.utils.Constants.SUCCESS,
                                obj.getString("code")))) {
                    JSONObject contentObj = JSONObject.fromObject(obj
                            .getString("content"));
                    return returnData(contentObj.toString().equals("{}") ? null
                            : contentObj.getString("list"));
                }
                return returnExternalError();
            }
            logger.info("sign:" + sign + "生成的sign:" + proSign);
            return returnExternalError();
        } catch (Exception e) {
            logger.error("error:程序异常！", e);
            return returnExternalError();
        }
    }

    /**
     * 返回封装后得json
     * 
     * @param str
     * @return
     * @throws Exception
     */
    private static String returnData(String str) throws Exception {
        String data = null;
        if (StringUtil.isNotEmpty(str)) {
            StringBuffer strBuffer = new StringBuffer();
            // String strs = JSONUtils.obj2json(str);
            strBuffer.append("{\"result\":\"1\",\"data\":");
            strBuffer.append(str);
            strBuffer.append("}");
            data = strBuffer.toString();
        } else {
            data = "{\"result\":\"" + Constants.ERROR_DATA + "\",\"data\":[]}";
        }
        logger.info("接口封装后返回的:" + data);
        return data;
    }

    /**
     * 
     * @Title: uploadUserIcon
     * @Description: 上传用户头像
     * @return User
     * @throws
     * @author fengwenyu
     * @date 2015年12月15日
     */
    @RequestMapping("/uploadUserIcon")
    @ResponseBody
    public String uploadUserIcon(HttpServletRequest request,
            HttpServletResponse response) {
        String userId = request.getHeader("userid");
        if (StringUtils.isBlank(userId)) {
            userId = request.getParameter("userId");
        }
        ContentBuilder<Picture> builder = new ContentBuilder<Picture>();
        // 消息提示
        String message = "";
        try {
            // 使用Apache文件上传组件处理文件上传步骤：
            // 1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            // 3、判断提交上来的数据是否是上传表单的数据
            // 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                // 如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    if(StringUtils.isBlank(userId)&&"userId".equals(name)){
                    	userId = value;
                    }
                    // value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    logger.info(name + "=" + value);
                    continue;
                } else {// 如果fileitem中封装的是上传文件
                        // 得到上传的文件名称，
                    String filename = item.getName();
                    logger.info(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename
                            .substring(filename.lastIndexOf("\\") + 1);
                    // 转换为字符串形式
                    Base64 base64 = new Base64();
                    item.getInputStream();
                    BufferedImage image = ImageIO.read(item.getInputStream());
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    ImageOutputStream imageOut = ImageIO
                            .createImageOutputStream(bs);
                    ImageIO.write(image, "jpg", imageOut);
                    byte[] bt = bs.toByteArray();
                    String str = new String(base64.encode(bt));

                    Picture picture = commentService.uploadPic(
                            filename.substring(filename.lastIndexOf(".")),
                            "user", str);
                    logger.info("文件上传状态："+picture.getSuccess());
                    // 删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                    logger.debug(message);

                    if (StringUtils.isNotBlank(picture.getPicUrl())) {
                    	String iconUrl = picture.getPicUrl();
                    	String picno = picture.getPicno();
                    	//判断userid
                    	if (StringUtils.isBlank(userId)) {
                          return JsonUtil.toJson(ResultBaseNew.build("1", "用户未登陆",
                                  new User()));
                    	}
                        String result = userService.modifyUserInfoIcon(userId, picno);
                        ResultBaseNew resultBaseNew = ResultBaseNew.format(result);
                        if(resultBaseNew==null||!resultBaseNew.getCode().equals("0")){
                        	return JsonUtil.toJson(ResultBaseNew.build("1", "文件保存失败",new User()));
                        }else{
                        	Map<String, String> map = new HashMap<String, String>();
                        	map.put("icon", iconUrl);
                        	return JsonUtil.toJson(ResultBaseNew.success(map));
                        }
                    }
                    return JsonUtil.toJson(ResultBaseNew.build("1", "文件上传失败",
                            new User()));
                }
            }
            return JsonUtil
                    .toJson(ResultBaseNew.build("1", "参数有误", new User()));
        } catch (Exception e) {
            message = "文件上传失败！";
            logger.error(message, e);
            return JsonUtil
                    .toJson(ResultBaseNew.build("1", "系统异常", new User()));
        }
    }

    /**
     * 获取用户信息
     * 
     * @return String
     * @throws
     * @author fengwenyu
     * @date 2015年12月16日
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) {
        String userId = request.getHeader("userid");
        if (StringUtils.isBlank(userId)) {
            userId = request.getParameter("userId");
            if (StringUtils.isBlank(userId)) {
                return JsonUtil.toJson(ResultBaseNew.build("1", "用户未登陆",
                        new User()));
            }
        }
        String result = userService.getUserInfo(userId);
        return addSessionidAndIcon(result);
    }

    /**
     * 修改用户信息
     * 
     * @return String
     * @throws
     * @author fengwenyu
     * @date 2015年12月16日
     */
    @RequestMapping("/modifyUserInfo")
    @ResponseBody
    public String modifyUserInfo(User user, HttpServletRequest request) {
    	String userId = request.getHeader("userid");
        if (StringUtils.isBlank(userId)) {
            userId = request.getParameter("userId");
            if (StringUtils.isBlank(userId)) {
                return JsonUtil.toJson(ResultBaseNew.build("1", "用户未登陆",
                        new User()));
            }
        }
        Map<String, String> params = new HashMap<String, String>();

        String nickName = user.getNickName();
        String gender = user.getGender();
        String birthday = user.getBirthday();
        if(nickName==null ||gender==null ||birthday==null){
        	return JsonUtil.toJson(ResultBaseNew.build("1", "参数不能为空",new User()));
        }
        if(!"".equals(nickName)){
        	//校验昵称 昵称规则是中文 数字 英文 _ 其他不符合要求
            final String regEX = "^[\u4e00-\u9fa5_a-zA-Z0-9]+$";
            Pattern p = Pattern.compile(regEX);
            Matcher m = p.matcher(nickName);
            if(!m.matches()){
            	return JsonUtil.toJson(ResultBaseNew.build("1", "昵称只支持中英文、数字、下划线",new User()));
            }
            //校验昵称结束 
        }
        
        // 获取原始用户信息
        User userOld=null;
        userOld = userService.getUserInfoPojo(userId);
        if(userOld == null){
        	params.put("nickName", nickName);
        	params.put("gender", gender);
        	params.put("birthday", birthday);
        	return userService.modifyUserInfo(userId, params);
        }
        boolean flag = nickName.equals(userOld.getNickName()) && gender.equals(userOld.getGender()) && birthday.equals(userOld.getBirthday());
        if(flag){
        	return JsonUtil.toJson(ResultBaseNew.build("0", "信息未经修改", userOld));
        }else{
        	params.put("nickName", nickName);
        	params.put("gender", gender);
        	params.put("birthday", birthday);
        	String result = userService.modifyUserInfo(userId, params);
        	return addSessionidAndIcon(result);
        }

    }

    /**
     * 登录后在个人中心修改密码或修改礼品卡支付密码
     * 
     * @param type
     * @param nowPassword
     * @param newPassword
     * @param confirmPassword
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPassword", method = { RequestMethod.POST,
            RequestMethod.GET })
    public String modifyPassword(@RequestParam String type,
            @RequestParam String nowPassword, @RequestParam String newPassword,
            @RequestParam String confirmPassword, HttpServletRequest request) {
        String userId = request.getHeader("userid");
        if (userId == null || "".equals(userId)) {
            userId = request.getParameter("userId");
        }
        nowPassword = nowPassword.replaceAll(" ", "+");
        newPassword = newPassword.replaceAll(" ", "+");
        confirmPassword = confirmPassword.replaceAll(" ", "+");

        try {
        	nowPassword = AESUtil.decrypt(nowPassword, AESUtil.IDCARD_KEY);
        	newPassword = AESUtil.decrypt(newPassword, AESUtil.IDCARD_KEY);
        	confirmPassword = AESUtil.decrypt(confirmPassword, AESUtil.IDCARD_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String data = userService.modifyPassword(userId, type, nowPassword,
                newPassword, confirmPassword);
        return data;
    }

    /**
     * 验证验证码接口
     * 
     * @param phoneNum
     * @param verifyCode
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/verifyPhoneCode", method = { RequestMethod.POST, RequestMethod.GET })
    public String verifyPhoneCode(@RequestParam String phoneNum, @RequestParam String verifyCode, HttpServletRequest request) {
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	
    	String data = userService.aspVerifyPhoneCode(userId, phoneNum, verifyCode);
        return data;
    }
    
	 /**
	  * 获取我的尺码
	  * @param userId 用户id
	  * @return
	  */
    @ResponseBody
    @RequestMapping(value = "/getMyTaglia", method = { RequestMethod.POST, RequestMethod.GET })
    public String getMyTaglia(HttpServletRequest request) {
        try {
        	
        	String userId = request.getHeader("userid");
        	if(userId==null||"".equals(userId)){
        		userId = request.getParameter("userId");
        	}
        	
        	String os = request.getHeader("os");
        	
            // 非空判断
            if (!StringUtil.isNotEmpty(userId)) {
                logger.info("参数不可以为空");
                return returnExternalError();
            }
        
            // 获得用户尺码信息
            String json = userService.getMyTaglia(userId,os);
           
            return json;
            
        } catch (Exception e) {
            logger.error("error:程序异常！", e);
            return returnExternalError();
        }
    }
    
    /**
     * 获取我的尺码
     */
    @ResponseBody
    @RequestMapping(value = "/modifyMyTaglia", method = { RequestMethod.POST, RequestMethod.GET })
    public String modifyMyTaglia(@RequestParam String modifyData, HttpServletRequest request) {
    	try {
    		
    		String userId = request.getHeader("userid");
        	if(userId==null||"".equals(userId)){
        		userId = request.getParameter("userId");
        	}
    		
    		String os = request.getHeader("os");
    		
    		// 非空判断
    		if (!StringUtil.isNotEmpty(userId)) {
    			logger.info("参数不可以为空");
    			return returnExternalError();
    		}
    		
    		// 获得用户信息状态
    		String json = userService.modifyMyTaglia(userId, modifyData, os);
    		
    		return json;
    	} catch (Exception e) {
    		logger.error("error:程序异常！", e);
    		return returnExternalError();
    	}
    }
    
    private String addSessionidAndIcon(String result){
    	 ResultBaseNew resultBaseNew = ResultBaseNew.formatToPojo(result, User.class);
         if(resultBaseNew !=null && "0".equals(resultBaseNew.getCode())){
         	User user = (User) resultBaseNew.getContent();
         	String sessionId = generateSessionId();
         	user.setSessionid(sessionId);
         	//设置默认头像开始
            Map<String, String> iconMap = new HashMap<String, String>();
 			iconMap.put("普通会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrKAE-1MAAATatXinY0856.png");
 			iconMap.put("黄金会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrCAR-m-AAAZs-ncDX8318.png");
 			iconMap.put("白金会员", "http://pic5.shangpin.com/group1/M00/D7/05/rBQKaFaKCrOAZOYiAAAaEcJFeUk549.png");
 			iconMap.put("钻石会员", "http://pic6.shangpin.com/group1/M00/D7/05/rBQKaFaKCrGAHGORAAAbrQ3CN4c900.png");
 			String icon = user.getIcon();
 			String keyLv = user.getLevel().trim();
 			if(StringUtils.isBlank(icon)){
 				icon = iconMap.get(keyLv);
 				if(icon==null){
 					icon="";
 				}
 				user.setIcon(icon);
 			}
 			//设置默认头像结束
 			
         	return JsonUtil.toJson(ResultBaseNew.success(user));
         }else{
         	 return JsonUtil.toJson(ResultBaseNew.build("1", "参数缺少错误",new User()));
         }
    }
}
