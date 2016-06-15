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
			<div class="topFix" id="address_add_head" style="display: none">
			   <section>
			       <div class="topBack" >
			       <a href="javascript:;" class="backBtn">&nbsp;</a>
			       		添加收货地址
			       <ul class="alUser_icon clr">
					   <li><a href="<c:url value='/'/>"><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/home_icon.png${ver}" width="25" height="25" alt="首页"></a></li>
			       </ul>
			       </div>
			   </section>
			</div>
			<div class="order_address_add alContent" id="address_add_content" style="display: none;min-height:550px;">
		</c:when>
		<c:otherwise>
			<div class="order_address_add alContent" id="address_add_content" style="display: none;margin-top: 0;">
		</c:otherwise>
	</c:choose>
	<div class="paymet_block" id="address_">
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

				<legend>收货地址</legend>
				<p class="select"><a href="javascript:;" id="select_area">省市区</a></p>
				<p>
					<label for="addr">详细地址：</label>
					<input type="text" id="J_addr" name="addr" placeholder="请输入详细街道地址" required maxlength="50" />
				</p>
				<p>
					<label for="code">邮编信息：</label>
					<input type="text" id="J_code" name="postcode" placeholder="请输入您所在地区的邮编" required maxlength="6" />
				</p>

	            <legend>收货人信息</legend>
	            <p>
	                <label for="userName">姓名：</label>
	                <input type="text" id="J_userName" name="name" placeholder="请输入中文名称" required />
	            </p>
	            <p>
	                <label for="mobileNum">联系电话：</label>
	                <input type="text" id="J_mobileNum" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
	            </p>

				<p>
					<label for="identificationNum"  style="width:75px;">身份证号码：</label>
					<input type="text" id="address_add_cardID" name="cardID" placeholder="请输入身份证号码" maxlength="18" />
				</p>

				<ul>
					<li>身份证号码仅在您购买海外商品时使用，如您购买国内商品可不填</li>
				</ul>

			<%--	<div class="payment_submit">
					<a href="javascript:;" class="payment_btn" onclick="submitAddAddr('address_add_')">保存</a>
				</div>
--%>
	            <div class="payment_submit">
	                <a href="javascript:;" class="payment_btn">保存</a>
	<!--                 <input type="submit" class="payment_btn" value="保存" /> -->
	            </div>
	        </fieldset>
			<div class="explain">
				<h3>关于身份证填写的声明</h3>
				<p>1. 身份证号码仅在您购买海外商品时使用，根据海关的要求，购买海外商品时需要您填写收货人的身份证号码进行个人物品入境申报。</p>
				<p>2. 因海关会对您填写的身份证是否会收货人的身份证信息进行验证，请确保填写正确，否则商品可能无法正常通关。</p>
				<p>3. 尚品网承诺，您填写的身份证信息仅用于清关，绝不做他用，请放心填写。</p>
			</div>
	    </form>
	    <div id="address_area_overlay"></div>
		<div id="address_area_layer">
			<a href="javascript:;" class="prev_btn">返回</a>
        	<a href="javascript:;" class="close_btn">关闭</a>
			<h3>省份</h3>
		    <dl id="area_province" title="省份">
		    	<c:forEach var="provice" items="${provinces}">
		  		<dd><a href="javascript:;" id="${provice.id}">${provice.name}</a></dd>
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

<%--<div class="paymet_block">

	<form name="address_add_form" id="address_add_form">
		<fieldset class="notBtm">
			<legend>收货地址</legend>
			<p class="select"><a href="javascript:;" id="address_add_select_area" onclick="selectPCAT('address_add_');">省市区</a>
				<input type="hidden" id="address_add_province" name="province" />
				<input type="hidden" id="address_add_city" name="city" />
				<input type="hidden" id="address_add_area" name="area" />
				<input type="hidden" id="address_add_town" name="town"/>
			</p>
			<p>
				<label for="addr">详细地址：</label>
				<input type="text" id="address_add_addr" name="addr" placeholder="请输入详细街道地址" required maxlength="50" />
			</p>
			<p>
				<label for="code">邮编信息：</label>
				<input type="text" id="address_add_postcode" name="postcode" placeholder="请输入您所在地区的邮编" required  maxlength="6" />
			</p>

			<legend>收货人信息</legend>
			<p>
				<label for="userName">姓名：</label>
				<input type="text" id="address_add_name" name="name" placeholder="请填写个中文名称" required />
			</p>
			<p>
				<label for="mobileNum">联系电话：</label>
				<input type="text" id="address_add_tel" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
			</p>

			<p>
				<label for="identificationNum"  style="width:75px;">身份证号码：</label>
				<input type="text" id="address_add_cardID" name="cardID" placeholder="请输入身份证号码" maxlength="18" />
			</p>

			<ul>
				<li>身份证号码仅在您购买海外商品时使用，如您购买国内商品可不填</li>
			</ul>

			<div class="payment_submit">
				<a href="javascript:;" class="payment_btn" onclick="submitAddAddr('address_add_')">保存</a>
			</div>
		</fieldset>
		<div class="explain">
			<h3>关于身份证填写的声明</h3>
			<p>1. 身份证号码仅在您购买海外商品时使用，根据海关的要求，购买海外商品时需要您填写收货人的身份证号码进行个人物品入境申报。</p>
			<p>2. 因海关会对您填写的身份证是否会收货人的身份证信息进行验证，请确保填写正确，否则商品可能无法正常通关。</p>
			<p>3. 尚品网承诺，您填写的身份证信息仅用于清关，绝不做他用，请放心填写。</p>
		</div>
	</form>
</div>--%>
