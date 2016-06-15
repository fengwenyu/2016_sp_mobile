package com.shangpin.manager.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shangpin.utils.FileUtils;
import com.shangpin.utils.HttpClientUtil;

@Controller
public class BaseController {

    protected static String USER_EDIT_SUCCESS_MSG = "user.edit.success.msg";

    /**
     * 获取资源文件的value
     * 
     * @param locale
     *            本地的编码方式
     * @param key
     *            资源文件的key值
     * @return
     */
    protected String getMessage(Locale locale, String key) {
        if (locale == null || StringUtils.isEmpty(key))
            return null;
        ResourceBundle resources = ResourceBundle.getBundle("i18n/messages", locale);
        String message = resources.getString(key);
        return message;
    }

    /**
     * 上传图片到服务器上
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadPic(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();// 文件名字
        String ext = FileUtils.getFileExt(fileName);
        String fileSize = String.valueOf(file.getSize());
        InputStream stream = file.getInputStream();

        Map<String, Object> params = new HashMap<String, Object>();
        String url = "http://mobile20.shangpin.com/shangpin/SaveMobilePic/";
        params.put("extension", ext);
        params.put("picturetype", "system");
        params.put("contentlength", fileSize);
        params.put("filename", fileName);
        params.put("file", stream);
        String result = HttpClientUtil.doPostFile(url, params);

        return result;
    }
}
