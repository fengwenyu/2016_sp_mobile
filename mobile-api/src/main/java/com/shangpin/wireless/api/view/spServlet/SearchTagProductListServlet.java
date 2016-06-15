package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.biz.service.ASPBizSerchService;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

public class SearchTagProductListServlet extends BaseServlet {

    private static final long serialVersionUID = 3479475926001047012L;

    protected final Log log = LogFactory.getLog(SearchTagProductListServlet.class);

    ASPBizSerchService aspBizSearchService;

    @Override
    public void init() throws ServletException {
        aspBizSearchService = (ASPBizSerchService) getBean(ASPBizSerchService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String pageIndex = request.getParameter("pageIndex");
        final String pageSize = request.getParameter("pageSize");
        final String userLv = request.getParameter("userLv");
        final String price = request.getParameter("price");
        final String size = request.getParameter("size");
        final String color = request.getParameter("color");
        final String tagId = request.getParameter("tagId");
        final String categoryId = request.getParameter("categoryId");
        final String postArea = request.getParameter("postArea");
        final String brandId = request.getParameter("brandId");
        final String order = request.getParameter("order");
        String filters = request.getParameter("filters");
        String version=request.getHeader("ver");
        String data=null;
        try {
            if (StringUtil.compareVer(version, "2.9.0") >= 0) {
                data = aspBizSearchService.queryTagProductListNew(pageIndex, pageSize, userLv, tagId, order,filters);
                
            }else{
                data = aspBizSearchService.queryTagProductList(pageIndex, pageSize, userLv, price, size, color, tagId, categoryId, postArea, brandId, order);
                
            }
            log.info("SearchTagProductListData:" + data);
            sendMessage(response, data);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SearchTagProductListServlet:" + e);
            responseError(response, "api");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
