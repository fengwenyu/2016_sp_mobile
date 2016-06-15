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
        <div class="topFix" id="order_head">
            <section>
                <div class="topBack" >
                    <a href="javascript:history.back(-1);" class="backBtn">&nbsp;</a>
                    <span class="top-title">提交订单</span>
                    <ul class="alUser_icon clr">
                        <li><a href="<c:url value='/'/>"><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/home_icon.png${ver}" width="25" height="25" alt="首页"></a></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="alContent" id="order_content" style="min-height:550px;">
    </c:when>
    <c:otherwise>
        <div class="alContent" id="order_content" style="margin-top: 0;">
    </c:otherwise>
</c:choose>
<!--头部 end-->
	<div class="paymet_block">
        <form name="login" id="J_Login">

            <fieldset>
            	<legend class="first_leg"></legend>
                <%--地址处理区域--%>
                <c:set var="lastReceived" value="${cartUnion.received.lastReceived}" />
                <c:choose>
                    <c:when test="${not empty lastReceived and not empty lastReceived.id}">
                        <p class="selected">
                            <a href="javascript:;">
                                <em>${lastReceived.name}</em>
                                <i class="phone">${lastReceived.tel}</i><br>
                                <c:if test="${not empty cardId}">
                                    <span>${cardId}</span></br>
                                </c:if>
                                <span>${lastReceived.provName}${lastReceived.cityName}
                                        ${lastReceived.areaName}${lastReceived.townName}${lastReceived.addr}</span>
                            </a>
                            <input type="hidden" name="addressId" value="${lastReceived.id}"/><%--收货地址--%>
                            <input type="hidden" id="forth_id" value="${lastReceived.town}"/>
                            <input type="hidden" id="third_id" value="${lastReceived.area}"/><%--兼容万一之前用户没有四级地址--%>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="select">
                            <a href="javascript:;">
                                <em>请填写配送地址</em>
                                <i class="phone"></i><br>
                                <span></span>
                            </a>
                           <%-- <a href="javascript:;">
                                请填写配送地址
                            </a>--%>
                            <input type="hidden" name="addressId" value=""/><%--收货地址id--%>
                            <input type="hidden" id="fourth_id" value="${lastReceived.townName}"/>
                        </p>
                    </c:otherwise>
                </c:choose>

                <%--收货时间区域--%>
                <c:set var="delivery" value="${cartUnion.received.delivery}" />
                <p class="select">
                    <a href="javascript:;" id="select_time" class="clr">
                        <span class="delivery_mode">${delivery.lastDelivery.desc}</span>
                    </a>
                    <input type="hidden"  name="deliveryFlag" value="${delivery.lastDelivery.id}"/><%--默认为1--%>
                </p>

                <%--国内商品区域--%>
                <c:set var="innerPro" value="${cartUnion.domesticProduct}"/>
                <c:if test="${not empty innerPro}">
                    <legend>${innerPro.title}</legend>
                    <c:forEach items="${innerPro.list}" var="pro" >
                        <p class="pord_info" sku="${pro.sku}">
                            <a href="javascript:;" class="clr">
                            <img src="${pro.pic}" width="56" height="67">
                            <ins>
                                <i>${pro.brandNameEN}<br />${pro.name}</i>
                                <c:forEach items="${pro.attribute}" var="attr">
                                    <em>${attr.name}：${attr.value}</em>
                                </c:forEach><br>
                                <c:set var="desc_temp" value="(${pro.priceDesc})"></c:set>
                                <em>&yen;<mark>${pro.price}</mark>${empty pro.priceDesc ? pro.priceDesc : desc_temp}&times;${pro.quantity}</em>
                            </ins>
                            </a>
                        </p>
                    </c:forEach>
                   <%-- <p class="pord_info">
                        <a href="#" class="clr">
                            <img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/image01.jpg${ver}"
                                 width="56" height="67">
                            <ins>
                                <i>POLO RALPH LAUREN<br/>拉夫劳伦男士红拉夫劳伦男士红拉夫劳伦男士</i>
                                <em>颜色：红色</em>
                                <em>尺码：S</em><br/>
                                <em>&yen;<mark>1190</mark>
                                    (含税费)&times;1</em>
                            </ins>
                        </a>
                    </p>--%>
                    <p class="freight"><a href="javascript:;">${innerPro.carriage.title}<i>${innerPro.carriage.desc}</i></a></p>
                    <%--<p class="select coupons cash"><a href="#">${innerPro.coupon.title} <i>${innerPro.coupon.desc} </i></a></p>--%>
                    <p class="select coupons cash" id="inner_coupon"><a href="javascript:;" count="${innerPro.coupon.count}">${innerPro.coupon.title}<i><em>${innerPro.coupon.desc}</em></i></a></p>
                    <p class="select coupons" id="inner_discount" ><a class="discount_btn">${innerPro.discount.title}<i>${innerPro.discount.desc}</i></a></p>
                </c:if>

                <%--全球购商品区域--%>
                <c:set var="outerPro" value="${cartUnion.abroadProduct}"/>
                <c:if test="${not empty outerPro}">
                    <legend>${outerPro.title}</legend>
                    <c:forEach items="${outerPro.list}" var="pro" >
                        <p class="abroad_info" sku="${pro.sku}">
                            <a href="javascript:;" class="clr">
                                <img src="${pro.pic}" width="56" height="67">
                                <ins>
                                    <i>${pro.brandNameEN}<br/>${pro.name}</i>
                                    <span>${pro.countryDesc}</span>
                                    <c:forEach items="${pro.attribute}" var="attr">
                                        <em>${attr.name}：${attr.value}</em>
                                    </c:forEach><br>
                                    <c:set var="desc_temp" value="${pro.priceDesc}"></c:set>
                                    <em>&yen;<mark>${pro.price}</mark>${empty pro.priceDesc ? pro.priceDesc : desc_temp}&times;${pro.quantity}</em>
                                    <em class="freight_info">${pro.carriage.title}<b>${pro.carriage.amount}</b><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/freight.png${ver}" width="14" height="14"></em>
                                </ins>
                            </a>
                        </p>
                    </c:forEach>

                    <%--<p class="abroad_info">	
                        <a href="#" class="clr">
                            <img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/image01.jpg${ver}"
                                 width="56" height="67">
                            <ins>
                                <i>POLO RALPH LAUREN<br/>拉夫劳伦男士红拉夫劳伦男士红拉夫劳伦男士</i>
                                <em>颜色：红色</em>
                                <em>尺码：S</em><br/>
                                <em>&yen;<mark>1190</mark>(含税费)&times;1</em>
                            </ins>
                        </a>
                    </p>--%>
                    <p id="out_coupon" class="select coupons"><a href="javascript:;" count="${outerPro.coupon.count}">${outerPro.coupon.title} <i><em>${outerPro.coupon.desc} </em></i></a></p>
                    <%--<p id="out_coupon" class="select coupons cash"><a href="#" >优惠券/现金券 <i>已优惠 <em>5</em> 元</i></a></p>--%>
                </c:if>

                <%--<legend>全球购商品</legend>
                <p class="abroad_info">
                	<a href="#" class="clr">
                    <img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/image01.jpg${ver}" width="56" height="67">
                    <ins>
                        <i>POLO RALPH LAUREN<br />拉夫劳伦男士红拉夫劳伦男士红拉夫劳伦男士</i>
                        <span>欧洲直发</span>
                        <em>颜色：红色</em>
                        <em>尺码：S</em><br />
                        <em>&yen;<mark>1190</mark>(含税费)&times;1</em>
                        <em class="freight_info">运费：<b>2</b><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/freight.png${ver}" width="14" height="14"></em>
                    </ins>
                    
                    </a>
                </p>
                <p class="abroad_info">
                	<a href="#" class="clr">
                    <img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/image01.jpg${ver}" width="56" height="67">
                    <ins>
                        <i>POLO RALPH LAUREN<br />拉夫劳伦男士红拉夫劳伦男士红拉夫劳伦男士</i>
                        <span>欧洲直发</span>
                        <em>颜色：红色</em>
                        <em>尺码：S</em><br />
                        <em>&yen;<mark>1190</mark>(含税费)&times;1</em>
                        <em class="freight_info">运费：<b>2</b><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/order/freight.png${ver}" width="14" height="14"></em>
                    </ins>
                    
                    </a>
                </p>
                <p id="out_coupon" class="select coupons"><a href="#">优惠券/现金券 <i>已优惠 <em>5</em> 元</i></a></p>--%>

                <%--发票区域--%>
                <c:set var="invoice" value="${cartUnion.invoice}"></c:set>
                <legend class="invo" valid="${invoice.valid}">发票信息${empty outerPro ? "":"<em>全球购商品不提供发票</em>" }</legend>
                <p class="select-invoice" >
                    <a href="javascript:;">${invoice.title}</a>
                    <span class="select-option" id="yes">是</span>
                    <span class="select-option cur" id="no">否</span>
                </p>

                <p class="select invoice">
                    <a href="javascript:;">
                        发票类型：<em>电子发票</em><br/>
                        发票抬头：<em>个人</em><br />
                        发票内容：<em>商品全称</em>
                    </a>
                </p>

                <%--礼品卡区域--%>
                <c:set var="prompt" value="<em>${cartUnion.giftCard.prompt}</em>"></c:set>
                <legend class="gift">${cartUnion.giftCard.title} ${cartUnion.giftCard.valid==1 ? "" : prompt }</legend>
                <c:choose>
                   <%-- <c:when test="${not empty cartUnion.giftCard and empty outerPro}">--%>
                    <c:when test="${not empty cartUnion.giftCard}">
                        <p class="giftCard" valid="${cartUnion.giftCard.valid}">
                            <a href="javascript:;" class="">余额:&yen;${cartUnion.giftCard.balance}
                                 <i amount="${cartUnion.giftCard.canUse}"></i>
                            </a>
                        </p>
                    </c:when>
                </c:choose>

                <legend id="pay_legend">支付方式 </legend>
                <%--<p>您还需要支付剩余 &yen;${cartUnion.totalAmount}</p>--%>
                <div id="pay">
                <c:set var="payments" value="${payments}"></c:set>
                <c:forEach items="${payments}" var="pay">
                    <c:if test="${pay.isSelected==1}">
                        <p id="defaultPay" class="payment-method" >
                	        <span class=${pay.clazz} url="${pay.url}" mainId="${pay.id}" subId="${pay.subpayCode}" way="${pay.way}">
                                <a href="javascript:;" class="cur">
                                    <em>${pay.name}</em>
                                </a>
                             </span>
                        </p>
                    </c:if>
                </c:forEach>

                <c:if test="${fn:length(payments)>1}">
                    <p class="select other-payment"><a href="javascript:;">其他支付方式</a></p>
                    <div class="other-payment-box">
                        <c:forEach items="${payments}" var="pay">
                            <c:if test="${pay.isSelected==0}">
                                <p class="payment-method" >
                                    <span class=${pay.clazz} mainId="${pay.id}" subId="${pay.subpayCode}" url="${pay.url}" way="${pay.way}">
                                        <a href="javascript:;">
                                            <em>${pay.name}</em>
                                        </a>
                                     </span>
                                </p>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:if>
                </div>

<%--               <p class="select other-payment"><a href="javascript:;">其他支付方式</a></p>
                <div class="other-payment-box">

                    <p class="payment-method">
                        <span class="weixinPay">
                            <a href="javascript:;">微信支付</a>
                        </span>
                    </p>
                    <p class="payment-method">
                        <span class="unionPay">
                            <a href="javascript:;">
                                <em>银联支付</em><br/>
                                储蓄卡支付需开通网银
                            </a>
                        </span>
                    </p>
                    <p class="payment-method">
                        <span class="cashPay">
                            <a href="javascript:;">货到付款现金支付</a>
                        </span>
                    </p>

                    <p class="payment-method">
                        <span class="postPay">
                            <a href="javascript:;">货到付款POS机刷卡</a>
                        </span>
                    </p>
                    <p class="payment-method">
                        <span class="pufaPay">
                            <a href="javascript:;">浦发银行</a>
                        </span>
                    </p>
                    <p class="payment-method">
                        <span class="CMPay">
                            <a href="javascript:;">招商银行</a>
                        </span>
                    </p>
              </div>--%>

                <p class="total">
                    <c:forEach var="price" items="${cartUnion.priceShow}">
                        <c:if test="${price.amount>0 or price.type ==1 or price.type==7}">
                            ${price.title}:&nbsp;&nbsp;${price.type==2 or price.type==3 or price.type==4 or price.type==6 ? "&minus;":"&ensp;"}<i type="${price.type}">&yen;${price.amount}</i><br/>
                            <%--<c:choose>
                                <c:when test="${price.type==2 or price.type==3 or price.type==4 or price.type==6}">
                                    -
                                </c:when>
                                <c:otherwise>
                                    &nbsp;
                                </c:otherwise>
                            </c:choose>--%>
                               <%-- &yen;${price.amount}</i><br/>--%>
                        </c:if>
                    </c:forEach>
                </p>
<%--                <p class="total">
                    商品金额：<i>&nbsp;&nbsp;&yen;1199</i><br />
                    运费：<i>&nbsp;&nbsp;&yen;50</i><br />
                    优惠：<i>- &yen;50</i><br />
                    礼品卡：<i>- &yen;50</i><br />
                    需支付金额：<i>- &yen;1150</i>
                </p>--%>
                <p class="attention">${cartUnion.prompt}</p>
                <div class="payment_submit_all">
                    <p>需支付金额：<i total="${cartUnion.totalAmount}"> &yen;${cartUnion.totalAmount}</i></p>
<%--                	<p>需支付金额：<i> &yen;1150</i></p>--%>
                    <c:set var="showNum" value="${cartUnion.totalSettlement}"></c:set>
                    <a href="javascript:;" class="payment_btn">提交订单(${showNum})</a>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<div class="select-overlay"></div>
<div class="select-layer" id="rece_time_layer">
    <a href="javascript:;" class="close_btn">关闭</a>
    <h3>收货时间</h3>
    <c:set var="deliId" value="${cartUnion.received.delivery.lastDelivery.id}"></c:set>
    <dl class="select-opt">
        <c:forEach var="item" items="${delivery.list}" >
            <dd><a href="javascript:;" ${item.id == deliId ? "class=\"cur\"":""} id="${item.id}">${item.desc}</a></dd>
        </c:forEach>
    </dl>
</div>

<div id="overlay"></div>
<div id="popup_box_id" >
    <a href="javascript:;" class="title_closeBtn">×</a>
    <h1 id="popup_title_id" style="display: block;">填写收货人身份证号码</h1>
    <div id="popup_content_id" class="share">
        <div id="popup_message_id">
        	<p>根据海关的要求，海外商品需要您填写收货人的身份证号码进行个人物品入境申报。</p>
            <div class="identification-box">
                <label for="identificationNum">身份证号码：</label>
                <input type="text" id="J_identificationNum" name="identificationNum" placeholder="请输入身份证号码" required maxlength="18">
            </div>
        </div>
        <div id="popup_panel_id">
          <button type="button" id="popup_cancel_id">&nbsp;取消&nbsp;</button>
          <button type="button" id="popup_ok_id">&nbsp;保存&nbsp;</button>
        </div>
    </div>
</div>
<div class="discount">
	<div class="dis_box">
        <input placeholder="请输入折扣码" value=""/>
        <p>温馨提示：折扣码与优惠券只能使用一种</p>
        <a class="dis_false">取消</a>
        <a class="dis_true">确定</a>
    </div>
</div>
<div class="freight_bg">
	<div class="freight_box">
    	<h3>关税运费说明</h3>
        <p>海外商品由于清关需求，需要单独收取关税和运费，商品展示价格中已包含税费，购买多件商品需要多次收取运费，请知晓，感谢~</p>
        <a>知道了</a>
    </div>
</div>

<!--地址不全时候显示-->
<div id="select-area-overlay" style="height: 667px; display: none;"></div>
<div class="select-layer" id="select_street_layer" style="display: none;">
    <a href="javascript:;" class="close_btn">关闭</a>
    <h3 style="text-align:center">请选择街道</h3>
    <dl class="select-opt" id="orgin_area_street" title="街道"></dl>
</div>
