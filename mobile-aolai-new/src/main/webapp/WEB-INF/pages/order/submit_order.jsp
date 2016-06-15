<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<rapid:override name="custum">
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/order.css" rel="stylesheet" />
	<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/page/account.css" rel="stylesheet" />
	
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/order/pre_submit.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/order/submit_order.js" type="text/javascript" charset="utf-8"></script>
	<script src="${cdn:js(pageContext.request)}/styles/shangpin/js/order/invoice.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" charset="utf-8">
			loader = SP.core
				.install("${cdn:js(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/lazyload.js${ver}")
				.excute();
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/config.sim.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/comm.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/order.js${ver}")
				.using("${cdn:js(pageContext.request)}/styles/shangpin/js/account.js${ver}")
				.excute(function(){
					isHome(false);
				});
				
	</script>
	<script type="text/javascript" charset="utf-8">
		$(function(){
			//将应付金额放入隐藏域
			$("#famount").val(${orderVO.amount});
			 window.onhashchange = function(){
			    	var url = window.location.hash;
					var reg = /#app/ig;
					if(!reg.test(url)){
						//隐藏以下区域
						cancelEditAddr();//收货地址编辑
						hideCouponcode();//优惠码
						hideCoupon();//优惠劵
						cancelInvoice();//发票地址
					}
			 };
		});
	</script>
</rapid:override >
<rapid:override name="content">
	<c:import url="/nav?navId=15"></c:import>
	<%-- <nav class="alNav">
	  <ul>
	    <li><a href="${ctx}">首页</a></li>
	    <li><a href="/allcartaction!listCart">购物袋</a></li>
	    <li>订单</li>
	  </ul>
    </nav> --%>
<div class="alContent">

  	<%--收货人地址编辑区域（默认隐藏） --%>
	<div class="address_con" style="display:none;">
		<%--已有收获地址选择 --%>
		<div id="addrList">
		<c:forEach items="${consigneeAddrList}" var="consigneeAddr" varStatus="st">
			<ul class="address_list">
				<li id="${consigneeAddr.id }" cod="${consigneeAddr.cod }" isd="${consigneeAddr.isd }" class="li_addrList" uname="${consigneeAddr.name }" addr="${consigneeAddr.addr }" tel="${consigneeAddr.tel }">
					<section class="alOrder_addr addr_list" style="background:none">
						<a href="javascript:void(0);" id="${consigneeAddr.id }" cod="${consigneeAddr.cod }" uname="${consigneeAddr.name }" addr="${consigneeAddr.provName}${consigneeAddr.cityName }${consigneeAddr.areaName}${consigneeAddr.townName}${consigneeAddr.addr }" tel="${consigneeAddr.tel }" onclick="setAddress(this);window.history.go(-1);">
		                  <dl>
		                    <dt>收货人：</dt><dd>${consigneeAddr.name }</dd>
		                  </dl>
		                  <dl>
		                    <dt>地　址：</dt><dd>${consigneeAddr.provName}${consigneeAddr.cityName }${consigneeAddr.areaName}${consigneeAddr.townName}${consigneeAddr.addr }</dd>
		                  </dl>
		                  <dl>
		                    <dt>手机号：</dt><dd>${consigneeAddr.tel }</dd> 
		                  </dl>
		                </a>
					</section>
				</li>
			</ul>
			</c:forEach>
		</div>
		
		<%--新增收获地址 --%>
		<div id="addMyAddr">
		<h4 class="add_new">新增收货地址</h4>
		<ul class="add_cont">
			<li id="msg" style="padding:0; color:#b51111; text-align:center; line-height:25px;">${msg}</li>
			<li>
              <span>收货人：</span>
              <input type="text" class="ac_txt" id="J_AddrName" maxlength="20">
            </li>
            <li>
              <span>省份：</span>
              <select id="J_AddrProvince">
                  <option value="-1">请选择省份</option>
                  <c:forEach items="${provinceList}" var="province">
                  	<option value="${province.id}" >${province.name}</option>
                  </c:forEach>
              </select>
            </li>
            <li>
              <span>城市：</span>
              <select id="J_AddrCity">
                  <option value="-1">请选择城市</option>
              </select>
          	</li>
          	<li>
               <span>地区：</span>
               <select id="J_AddrArea">
                  <option value="-1">请选择地区</option>
              </select>
            </li>
            <li>
               <span>街道：</span>
               <select id="J_AddrTown">
                  <option value="-1">请选择街道</option>
                  <option value="" >天通苑</option>
              </select>
            </li>
            <li>
              <span>手机号码：</span>
              <input type="tel" class="ac_txt" id="J_AddrMobile" maxlength="11">
            </li>
            <li>
              <span>邮政编码：</span>
              <input type="tel" class="ac_txt" id="J_AddrCode" maxlength="6">
            </li>
            <li>
              <span>详细地址：</span>
              <input type="text" class="ac_txt long_txt" id="J_AddrText">
            </li>
            
            <li>
              <span>设为默认：</span>
              <input type="checkbox" id="J_AddrDefault" >
            </li>
            <li class="addr_errorMsg"></li>
            <li>
              <p class="txt_center">
                <input type="button" class="alList_submitBtn" value="确认" onclick="addEditAddr();$('#msg').hide();"/>
                <!--a href="#" class="alList_submitBtn">确认</a-->
                <a href="javascript:cancelEditAddr()" class="alList_moreBtn">取消</a>
              </p>
            </li>
          	
		</ul>
		</div>
	</div>
	
	<%--收货人地址显示区域 --%>
	<div class="myCom">
	<section class="alOrder_addr"  id="alOrder_addr"> 
		<%--显示默认地址 --%>
			<a href="#app" onclick="javascript:showAddress();">
				<c:if test="${consigneeAddrList != null }">
				<input type="hidden" id="cId" value="${consigneeAddrList[0].id}">
			      <dl>
			        <dt>收货人：</dt><dd id="caname">${consigneeAddrList[0].name}</dd>
			      </dl>
			      <dl>
			        <dt>地　址：</dt><dd id="caaddress">${consigneeAddrList[0].provName}${consigneeAddrList[0].cityName }${consigneeAddrList[0].areaName}${consigneeAddrList[0].townName}${consigneeAddrList[0].addr}</dd>
			      </dl>                 
			      <dl>
			        <dt>手机号：</dt><dd id="catel">${consigneeAddrList[0].tel}</dd>
			      </dl>
		      	</c:if>
		      <c:if test="${consigneeAddrList == null || consigneeAddressList.size <= 0}">
		        <div class="noneAddr">点击添加收货人地址</div>
		      </c:if>
	       </a>
	</section>
	</div>
	<%--配送方式 --%>
	<div class="myCom">
	<h2 class="alOrder_title">配送方式</h2>
	<ul class="alOrder_select" id="alOrder_date">
	<c:forEach items="${deliveryList}" var="delivery" varStatus="st">
	    <li class="deliverytag <c:if test='${st.index==0}'>cur</c:if>" data-title="${delivery.id}" onclick="changeDelivery(this)"><a href="#">${delivery.name}<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="${ctx}/styles/shangpin/images/cur_icon.png" width="12" height="12"></i></a></li>
	</c:forEach>
	</ul>
	</div>
	
	<%--====================================发票信息start================================= --%>
	<div class="myCom">
	<section class="alOrder_addr alOrder_invoice">
	    <a href="#app" onclick="javascript:showInvoice();">
	      <dl>
	        <dt>发票信息</dt><dd id="isInvoice">不开发票</dd>
	      </dl>
	    </a>
    </section>
    </div>
    <div id="invoice" style="display:none;">
    	<ul class="invoice_select" id="invoice_option">
    		<li>
		      <dl>
		        <dt>发票信息：</dt>
		        <dd>
			         <a href="javascript:invoiceWant('N');" class="cur" id="invoice_close_a" data-title="0" >
			          <em>不要发票<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em>
			         </a>
			         <a href="javascript:invoiceWant('Y');" id="invoice_open_a" data-title="1" >
			           <em>要发票<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em>
			         </a>
		         </dd>
		      </dl>
		    </li>
    	</ul>
    	
    	<div class="invoice_content" style="display:none;">
    		<ul class="invoice_select" id="invoice_title">
    			<li>
			        <dl>
			          <dt>发票抬头：</dt>
			          <dd><a href="javascript:invoiceKind('0')" id="invoice_org_a" class="cur" data-title="0"><em>单位<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a><a href="javascript:invoiceKind('1')" id="invoice_person_a" data-title="1"><em>个人<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a></dd>
			        </dl>
			    </li>
			    <span>
			        <input type="text" id="invoice_org" name="org" value="" placeholder="请填写单位或个人名称,如不填写默认显示‘个人’" />
			        <input type="text" id="invoice_person" name="person" value="" placeholder="请填写个人姓名,如不填写默认显示‘个人’" />
		       </span>
    		</ul>
    		
    		<ul class="invoice_select" id="invoice_class">
		      <li>
		        <dl>
		          <dt>发票内容：</dt>
		          <dd id="invoiceCon">
		            <a href="javascript:invoiceContent('qc')" id="qc" class="cur" data-title="商品全称" id="商品全称"><em>商品全称<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('xb')" id="xb" data-title="箱包" id="箱包"><em>箱包<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('sp')" id="sp" data-title="饰品" id="饰品"><em>饰品<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('mz')" id="mz" data-title="化妆品" id="化妆品"><em>化妆品<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('zb')" id="zb" data-title="钟表" id="钟表"><em>钟表<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy"/images/cur_icon.png" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('fz')" id="fz" data-title="服装" id="服装"><em>服装<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('xm')" id="xm" data-title="鞋帽" id="鞋帽"><em>鞋帽<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		            <a href="javascript:invoiceContent('yj')" id="yj" data-title="眼镜" id="眼镜"><em>眼镜<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		          </dd>
		        </dl>
		      </li>
		      <p><em>温馨提示:</em>手表类商品只能开具为品牌和型号，其他内容无法开具。</p>
		    </ul>
		    
		    <ul class="invoice_select" id="invoice_addr">
		      <li>
		        <dl>
		          <dt>发票邮至：</dt>
		          <dd>
		          <a id="destY" href="javascript:invoiceDest('0')" class="big cur" data-title="0" ><em>与收货地址相同<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		          <a id="destN" href="javascript:invoiceDest('1')" data-title="1" id="other_addr" ><em>其他地址<i><img src="${ctx}/styles/shangpin/images/cur_icon.png" lazy="" width="12" height="12"></i></em></a>
		          </dd>
		        </dl>
		        <input type="hidden" id="addrVal" value="0">
		      </li>
		    </ul>
		    
		    <ul class="invoice_addrForm add_cont" >
		    	<div id="invaddrList" style="background:none;">
		    	<c:forEach items="${invoiceAddrList}" var="invoiceAddr">
		    		<ul class="invaddr_list">
		    			<li id="${invoiceAddr.id}" class="invaddr_list_li">
		    				<section class="alOrder_invaddr" style="background:none;">
		    					<a href="javascript:void(0);" onclick="setInvAddress(${invoiceAddr.id});">
				                  <dl>
				                    <dt>收货人：</dt><dd>${invoiceAddr.name}</dd>
				                  </dl>
				                  <dl>
				                    <dt>地　址：</dt><dd>${invoiceAddr.provName}${invoiceAddr.cityName}${invoiceAddr.areaName}${invoiceAddr.townName}${invoiceAddr.addr}</dd>
				                  </dl>
				                  <dl>
				                    <dt>手机号：</dt><dd>${invoiceAddr.tel}</dd>
				                  </dl>
				                </a>
		    				</section>
		    			</li>
		    		</ul>
		    	</c:forEach>
		    	</div>
		    	
		    	<!-- 新增发票区域按钮 -->
		        <div class="alList_moreBtn" onclick="showAddInv()" style="width:90%" id="addInvBut">点击添加发票地址</div>
		    	
		    	<div id="otherAddr" style="background:none;">
		    		<li>
			          <label for="userName">姓　 名：</label>
			          <input type="text" id="INV_AddrName" name="userName" value="" maxlength="20" onfocus=""/>
			        </li>
			        <li>
				        <label>省　 份：</label>
				        <select id="INV_AddrProvince" >
				        	<option value="-1" selected="selected">请选择省份</option>
				            <c:forEach items="${provinceList}" var="province">
			                  	<option value="${province.id}" >${province.name}</option>
			                </c:forEach>
				        </select>
				     </li>
				     <li>
				        <label>城　 市：</label>
				        <select id="INV_AddrCity">
				            <option value="-1">请选择城市</option>
				        </select>
				      </li>
				      <li>
				        <label>地　 区：</label>
				        <select id="INV_AddrArea">
				            <option value="-1">请选择地区</option>
				        </select>
				      </li>
				      <li>
			               <span>街道：</span>
			               <select id="INV_AddrTown" >
			                  <option value="-1">请选择街道</option>
			              </select>
			          </li>
			          <li>
				        <label for="userPhone" >手　 机：</label>
				        <input type="tel" id="INV_AddrMobile" name="userPhone" value="" maxlength="11" onfocus="changeState();"/>
				      </li>
				      <li>
				        <label for="userCode">邮　 编：</label>
				        <input type="tel" id="INV_AddrCode" name="userCode" value="" maxlength="6" onfocus="changeState();"/>
				      </li>
				      <li>
				        <label for="userAddr">地　 址：</label>
				        <input type="text" id="INV_AddrText" name="userAddr" value="" onfocus="changeState();"/>
				      </li>
		    	</div>
		    </ul>
    	</div>
    	
    	<div class="inv_addr_errorMsg addr_errorMsg" style="padding: 0;color: #B51111;text-align: center;line-height: 25px;" ></div>

       <!-- 发票地址 -->
       <div style="display: none;" id="addInvButton">
		<p class="txt_center" >
	     <input type="button" class="alList_submitBtn invoice_submitBtn" value="新增" onclick="addNewInv();$('#msg').hide();"/>
	     <a href="javascript:void(0)"onclick="cancelAdd();" class="alList_moreBtn">撤销</a>
	    </p>
		</div>
		
		<!-- 选择现有的发票地址列表 -->
	   <p class="txt_center" id="chooseInvButton">
	     <input type="button" class="alList_submitBtn invoice_submitBtn" value="确认" onclick="addInvoice();$('#msg').hide();"/>
	     <a href="#"onclick="cancelInvoice();" class="alList_moreBtn">取消</a>
	   </p>
	   
    </div>
	<%--====================================发票信息end================================= --%>
	
	<%--====================================优惠劵列表start==============================--%>
	
	<c:if test="${couponList != null && fn:length(couponList)>0 }">
	<div class="myCom">
		<section class="alOrder_addr alOrder_invoice">
		    <a href="#app" onclick="javascript:showCoupon();">
		      <dl>
		        <dt>优惠券</dt>
		        <dd id="iscoupon">未使用 </dd>
		      </dl>
		    </a>
	   </section>
	</div> 
	   <div class="alOrder_couponList" style="display:none;">
	   		<c:forEach items="${couponList}" var="coupon">
	   			<dl>
			    	<dd>
			        	<p>${coupon.desc}</p>
			            <ul>
			            	<li>优惠券面值<br />${coupon.amount }</li>
			                <li>有效期<br />${coupon.expiredate }</li>
			                <li><a href="#" onclick="javascript:selectCoupon(this);" couponNo="${coupon.couponno}" couponRule="${coupon.desc}" couponAmount="${coupon.amount }" class="select_coupon" id ="usecode" isUse="0">使用</a></li>
			            </ul>
			        </dd>
		        </dl>
	   		</c:forEach>
	   </div>
	</c:if>

	<%--====================================优惠劵列表end================================--%>
	
	<%--优惠码 开始 --%>
	<div class="myCom">
	<section class="alOrder_addr alOrder_couponcode">
	    <a href="#app" onclick="javascript:showCouponcode();">
	      <dl>
	        <dt>使用优惠码</dt><dd id="isCouponcode">未使用</dd>
	      </dl>
	    </a>
	</section>
	</div>
	
	<div class="_couponcode" style="display:none;">
		<div class="prompt" id="prompt">  温馨提示：优惠码和优惠券不能同时使用。</div>
         <c:if test="${sessionScope.maolai_user != null && sessionScope.maolai_user.level != '正式会员' }">
	        <div class="prompt" id="promptlevel">您是${sessionScope.maolai_user.level}，如使用优惠码，该商品不能同时享受会员价。</div>
         </c:if>
          <div>   
          	<label >使用优惠码：</label>
            <input type="text" id="couponNum" name="" value="" />
       	  </div>
       		
         <div align="center"><input type="button" onclick="javascript:getCouponCodeInfo()" id="select_couponcode" useflag="0" value="使用" /></div>
	</div>
	
	<%--商品清单 --%>
	<div class="myCom">
	<h2 class="alOrder_title">商品清单</h2>
	<section class="alOrder_list">
		<c:forEach items="${productList}" var="productCart">
			<c:forEach items="${productCart.cartItemList}" var="product" varStatus="st">
				<input type="hidden" id="shopid" name="shopid" class="shopIds" value="${product.shoppingCartDetailId}"/>
				<dl class="alOrder_listBlock">
					<dt>
						<span style="width:70px; height:90px;"><img src="${product.mobileImg}" lazy="" width="70" height="90"></span>
						<p>
						${product.productName}<br />
						<c:forEach var="skuAttr" items="${fn:split(product.mobileSkuAttrText,' ')}">
							${skuAttr}<br/>
						</c:forEach>
						数量：${product.quantity}件<br />
                        <em>&yen;${fn:substringBefore(product.price,".")}</em>
						</p>
					</dt>
				</dl>
			</c:forEach>
		</c:forEach>
		
		<p class="totalPricewrap"  >  
	     	<span>总金额：</span><em>&yen;${orderVO.amount }</em></br>
	   		<span id="discount" style="display: none;"><span >-优惠金额：</span><em id="discountamount"></em></br></span>
	    	<span id="s_carriage" style="display: none;text-align:right;"><span>+运费：</span><em id="carriage"></em></br></span>
	   		<span id="totalPrice">应付金额：</span><em id="factPay">&yen;${orderVO.amount }</em>
        <p>
		<a href='javascript:submitOrder();' class="alOrder_buyBtn alOrder_submitBtn" id="subOrder">提交订单</a>
	</section>
	</div>
	
	<form>
		<input type="hidden" id="carrFee" value="${orderVO.carriage }"/>
		<input type="hidden" id="CartOrder" value="${orderVO.amount }">
	    <input type="hidden" id="INV_Addr_size" value="${fn:length(invoiceAddrList)}" />
	    <input type="hidden" id="CON_Addr_size" value="${fn:length(consigneeAddrList)}" />
	    
	    <!-- 将省市信息放入隐藏域 -->
	    <input type="hidden" id="myProId" value="">
	    <input type="hidden" id="myProName" value="">
	    <input type="hidden" id="myCityId" value="">
	    <input type="hidden" id="myCityName" value="">
	    <input type="hidden" id="myAreaId" value="">
	    <input type="hidden" id="myAreaName" value="">
	    <input type="hidden" id="myTownId" value="">
	    <input type="hidden" id="myTownName" value="">
   <%--======================= 提交订单所需参数 ==============================--%>
	    <input type="hidden" id="addrid" value="">
	    <input type="hidden" id="invoiceaddrid" value="">
	    <!-- 是否开发票：默认不开 0 -->
	    <input type="hidden" id="invoiceflag" value="0">
	    <!-- 发票类型：默认单位 1 -->
	    <input type="hidden" id="invoicetype" value="1">
	    <input type="hidden" id="invoicetitle" value="个人">
	    <input type="hidden" id="invoicecontent" value="商品全称">
	    <input type="hidden" id="couponflag" value="">
	    <input type="hidden" id="coupon" value="">
	    <!-- 配送方式 ：默认工作日收货-->
	    <input type="hidden" id="express" value="1">
	    <input type="hidden" id="buysIds" value="">
	    <input type="hidden" id="famount" value="">
	    <!-- 订单提交成功与否标识 -->
	    <input type="hidden" id="isSubmit" value="0" >
	    
	    <!-- 发票的标示区域，不作提交 -->
	 	 <input type="hidden" id="invFlag" value="0">
	 	 <input type="hidden" id="invTit" value="1">
	 	 <input type="hidden" id="invCon" value="">
	 	 <input type="hidden" id="invDesFlag" value="0">
	 	 <input type="hidden" id="invAddressId" value="">
	 	 <!-- 标记发票地址是否添加:0新增，1选择现有的 -->
	 	 <input type="hidden" id="invSel" value="0">
   </form>
</div>
</rapid:override>


<%@ include file="/WEB-INF/pages/common/base.jsp" %> 