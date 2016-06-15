<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
	<c:set var="ua" value="${header['User-Agent']}" />
	<c:set var="micromessenger" value="micromessenger" />
	<c:set var="aolaiIOSApp" value="AolaiIOSApp" />
	<c:set var="shangpinAndroidApp" value="ShangpinAndroidApp" />
	<c:set var="aolaiAndroidApp" value="AolaiAndroidApp" />
	<c:set var="shangpinIOSApp" value="ShangpinIOSApp" />
	<c:choose>
		<c:when test="${!fn:containsIgnoreCase(ua, micromessenger)&&!fn:containsIgnoreCase(ua, shangpinIOSApp)&&!fn:containsIgnoreCase(ua, aolaiAndroidApp)&&!fn:containsIgnoreCase(ua, aolaiIOSApp)&&!fn:containsIgnoreCase(ua, shangpinAndroidApp)}">    
			<div class="topFix" id="order_address_add_header" style="display: none">
			   <section>
			       <div class="topBack" >
			       <a href="javascript:showPage('order_address_list','order_address_list_header');" class="backBtn">&nbsp;</a>
			       		添加收货地址
			       <ul class="alUser_icon clr">
			           <li><a href="<c:url value='/index'/>"><img src="${cdn:pic(pageContext.request)}/styles/shangpin/images/home_icon.png" width="25" height="25" alt="首页"></a></li>
			       </ul>
			       </div>
			   </section>
			</div>
			<div class="order_address_add alContent" style="display: none;min-height:550px;">
		</c:when>
		<c:otherwise>
			<div class="order_address_add alContent" style="display: none;margin-top: 0;">
		</c:otherwise>
	</c:choose>
	<div class="paymet_block">
		<form name="login" id="order_address_form" method="post">
	    	<input type="hidden" id="province" name="province" value=""/>
	    	<input type="hidden" id="provincename" name="provname" value=""/>
	    	<input type="hidden" id="city" name="city" value=""/>
	    	<input type="hidden" id="cityname" name="cityname" value=""/>
	    	<input type="hidden" id="area" name="area" value=""/>
	    	<input type="hidden" id="areaname" name="areaname" value=""/>
	    	<input type="hidden" id="town" name="town" value=""/>
	    	<input type="hidden" id="townname" name="townname" value=""/>
	        <fieldset class="notBtm">
	            <legend>收货人信息</legend>
	            <p>
	                <label for="userName">姓名：</label>
	                <input type="text" id="J_userName" name="name" placeholder="请填写个中文名称" required />
	            </p>
	            <p>
	                <label for="mobileNum">联系电话：</label>
	                <input type="text" id="J_mobileNum" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
	            </p>
	            <legend>收货地址</legend>
	            <p class="select"><a href="#" id="select_area">省市区</a></p>
	            <p>
	                <label for="addr">详细地址：</label>
	                <input type="text" id="J_addr" name="addr" placeholder="请输入详细街道地址" required maxlength="50" />
	            </p>
	            <p>
	                <label for="code">邮编信息：</label>
	                <input type="text" id="J_code" name="postcode" placeholder="请输入您所在地区的邮编" required maxlength="6" />
	            </p>
<!-- 	            <p> -->
<!-- 	                <label for="default">默认地址：</label> -->
<!-- 	                <input type="checkbox" id="J_isd" name="isd" placeholder="请输入您所在地区的邮编"/> -->
<!-- 	            </p> -->
	            
	            <div class="payment_submit">
	                <a href="javascript:showAddressList('order_address_list','order_address_list_header');" class="payment_btn">保存</a>
	<!--                 <input type="submit" class="payment_btn" value="保存" /> -->
	            </div>
	        </fieldset>
	    </form>
	    <div id="address_area_overlay"></div>
		<div id="address_area_layer">
			<a href="javascript:;" class="prev_btn">返回</a>
        	<a href="javascript:;" class="close_btn">关闭</a>
			<h3>省份</h3>
		    <dl id="area_province" title="省份">
		    	<c:forEach var="provice" items="${provinces}">
		  		<dd><a href="#" id="${provice.id}">${provice.name}</a></dd>
		  	</c:forEach>
		    </dl>
		    <dl id="area_city" title="城市">
		       
		    </dl>
		    <dl id="area_county" title="地区">
		        
		    </dl>
		    <dl id="area_street" title="街道">
		       
		    </dl>
		</div>
	</div>
</div>
