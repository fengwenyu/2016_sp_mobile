<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>

<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/giftcard/giftcardDetail.css${ver}" rel="stylesheet" />
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js${ver}"  type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}"  type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}"  type="text/javascript" charset="utf-8"></script>
		<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/giftcard/list.js${ver}"  type="text/javascript" charset="utf-8"></script>
</rapid:override>
<%-- 页标题 --%>
<rapid:override name="page_title">
	礼品卡
</rapid:override>
<rapid:override name="content">
	<div class="product_info">
        	<ul class="tab_info" style="font-size:15px;">
              <li class="curr"><span>电子卡</span></li>
              <li><span>实物卡</span></li>
            </ul>        
     </div>
    <div class="content_info gift-card">
          <div class="content_detail content_list">
          	 <div class="prod_list clr">
          		<c:import url="/giftCard/productListCell"></c:import>
          		</div>
          </div>
          
          <div class="content_size content_list" id="tabs_txt0" style="display:none">
          	 <div class="prod_list clr">
          		<c:import url="/giftCard/productListCell?type=2"></c:import>
          		</div>
          </div>
          
        </div>
</rapid:override>

<%@ include file="/WEB-INF/pages/common/another_common_mall_banner.jsp"%>

