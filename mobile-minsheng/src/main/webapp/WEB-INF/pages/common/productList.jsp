<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<ul>
    <s:iterator value="merchandiseList">
      <li>
        <a title="${brandname }${productname}${status}" href="<%=path %>/merchandiseaction!spdetail?ch=${ch}&brandid=${bid}&gender=${gender}&istop=${istop}&categoryid=${categoryid}&categoryname=${categoryname}&childCategoryid=${childcategoryid}&childCategoryname=${childCategoryname}&productid=${productid}&topicid=${topicid}&activityName=${activityName}">
          <s:if test="%{count==0}">
		    <i class="saletip saleoutTip">售罄</i>
		  </s:if>
		  <img width="159" height="211" alt="" src="<%=path %>/images/e.gif" lazy="${pic}">
          <div class="alBrand_list_info">
            <p>
              <em>${brandname }</em>
              <em>${productname}</em>
            </p>
            <span><i style="font-weight:normal;">&yen;
              <s:if test="%{status==\"000100\"}">
			    ${specialprice[0] }
		      </s:if>
		      <s:elseif test="%{status==\"0001\"}">
			      ${specialprice[0] }
		        </s:elseif>
		      <s:else>
		        ${prices[priceindex] }
		      </s:else>
            </i></span>           
        </div>
	   </a>
      </li>
    </s:iterator>
</ul>

