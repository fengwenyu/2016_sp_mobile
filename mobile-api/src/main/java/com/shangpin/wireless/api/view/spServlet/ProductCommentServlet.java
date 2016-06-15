package com.shangpin.wireless.api.view.spServlet;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizProductCommentlService;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 商品详情页
 * 
 * @author wangfeng
 * 
 */
public class ProductCommentServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(ProductCommentServlet.class);

    ASPBizProductCommentlService aspBizProductCommentlService = null;

    @Override
    public void init() throws ServletException {
        aspBizProductCommentlService = (ASPBizProductCommentlService) getBean(ASPBizProductCommentlService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String productId=request.getParameter("productId");
        String pageIndex=request.getParameter("pageIndex");
        String pageSize=request.getParameter("pageSize");
        String channelNo = request.getHeader("ch");// 获取渠道号
        String tagId = request.getParameter("tagId")==null?"":request.getParameter("tagId");//评论的标签id，代表偏大，偏小，合适等
        PrintWriter writer = null;
        if (StringUtil.isNotEmpty(productId)) {
            try {
                writer = response.getWriter();
                String data = aspBizProductCommentlService.queryProductCommentToResult(productId, pageIndex, pageSize,tagId);
                writer.print(data);
                // 记录访问日志
                FileUtil.addLog(request, "productComment", channelNo, "productId", productId);
            } catch (Exception e) {
                log.error("ProductCommentServlet:" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("ProductCommentServlet:" + e);
                }
            }
        }

    }
}
