package com.shangpin.api.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.service.ASPBizCommentService;

/**
 * 评论接口
 * 
 * @author Administrator
 *
 */
@Controller
public class CommentController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private ASPBizCommentService commentService;

    /**
     * 根据订单号查询评论初始化信息
     * 
     * @param request
     * @param mainOrderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commentInit", method = { RequestMethod.POST, RequestMethod.GET })
    public String commentInit(HttpServletRequest request, String orderNo, String productNo, String orderDetailNo, String skuNo, String categoryNo) {
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
        String data = commentService.findCommentInitInfo(orderNo, productNo, userId, orderDetailNo, skuNo, categoryNo);
        return data;
    }

    /**
     * 根据订单号查询评论列表信息
     * 
     * @param request
     * @param mainOrderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/commentList", method = { RequestMethod.POST, RequestMethod.GET })
    public String commentList(HttpServletRequest request, String subOrderNo) {
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
        String data = commentService.CommentListByOrderNo(subOrderNo, userId);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/submitComment", method = { RequestMethod.POST, RequestMethod.GET })
    public String submitComment(HttpServletRequest request, String commentItemList, String commentContent, String imageList, String orderNo,String orderDetailNo,String skuNo,String productNo,String categoryNo) {
    	String userId = request.getHeader("userid");
    	if(userId==null||"".equals(userId)){
    		userId = request.getParameter("userId");
    	}
    	String data = commentService.submitComment(commentItemList, commentContent, imageList, orderNo, orderDetailNo, skuNo,productNo,userId,categoryNo);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadCommentPic", method = { RequestMethod.POST })
    public ContentBuilder<Picture> uploadPic(HttpServletRequest request, HttpServletResponse response) {
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
            if (!ServletFileUpload.isMultipartContent(request)) {
                // 按照传统方式获取数据
                return builder.buildDefError();
            }
            // 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                // 如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    // 解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    // value = new String(value.getBytes("iso8859-1"),"UTF-8");
                   logger.info(name + "=" + value);
                } else {// 如果fileitem中封装的是上传文件
                        // 得到上传的文件名称，
                    String filename = item.getName();
                    logger.info(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    // 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
                    // c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    // 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    // 转换为字符串形式
                    Base64 base64 = new Base64();
                    item.getInputStream();
                    BufferedImage image = ImageIO.read(item.getInputStream());
        			ByteArrayOutputStream bs = new ByteArrayOutputStream();
        			ImageOutputStream imageOut = ImageIO.createImageOutputStream(bs);
//        			ImageIO.createImageOutputStream(bs);
        			ImageIO.write(image, "jpg", imageOut);
        			byte[] bt = bs.toByteArray(); 
        			String str = new String(base64.encode(bt));

//                    String str = new String(base64.item(item.getString().getBytes()));
                    Picture picture = commentService.uploadPic(filename.substring(filename.lastIndexOf(".")), "user", str);
                    // 删除处理文件上传时生成的临时文件

                    
                    item.delete();
                    message = "文件上传成功！";
                    logger.debug(message);
                    return null == picture ? builder.buildDefError() : builder.buildDefSuccess(picture);
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            logger.error(message, e);
        }
        return builder.buildDefError();
    }
    @ResponseBody
    @RequestMapping(value = "/commentProductList", method = { RequestMethod.POST, RequestMethod.GET })
    public String commentProductList(HttpServletRequest request, String commentItemList, String commentContent, String imageList, String orderNo,String orderDetailNo,String skuNo,String productNo,String categoryNo) {
        String userId = request.getHeader("userid");
        String pageIndex=request.getParameter("pageIndex");
        String pageSize=request.getParameter("pageSize");
        String data = commentService.commentProductList(userId, pageIndex, pageSize);
        return data;
    }
}
