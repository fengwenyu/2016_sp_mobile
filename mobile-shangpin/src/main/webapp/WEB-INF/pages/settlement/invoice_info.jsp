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
        <div class="topFix" id="invoice_head" style="display: none">
            <section>
                <div class="topBack" >
                    <a href="javascript:void(0);" class="backBtn">&nbsp;</a>
                    发票信息
                    <ul class="alUser_icon clr">
                        <li><a href="<c:url value='/'/>"><img src="${cdn:css(pageContext.request)}/styles/shangpin/images/home_icon.png${ver}" width="25" height="25" alt="首页"></a></li>
                    </ul>
                </div>
            </section>
        </div>
        <div class="alContent" id="invoice_content" style="display: none;min-height: 550px;" >
    </c:when>
    <c:otherwise>
        <div class="alContent" id="invoice_content" style="display: none;margin-top: 0;min-height: 100%;" >
    </c:otherwise>
</c:choose>
<!--头部 start-->
    <div class="invoice_block">
            <fieldset class="notBtm">
                <legend>发票信息</legend>
                <c:set var="invoice" value="${cartUnion.invoice}"></c:set>
                <c:set var="temp" value="${not empty cartUnion.invoice.lastInvoice}"></c:set>
                <div class="invoice_info">
                    <p>
                        <label>发票抬头：</label>
                        <em class="info_btn">
                            <a class="active_a personal">个人</a>
                            <a class="company">单位</a>
                        </em>
                    </p>
                    <div class="info_input">
                        <p class="org_name">
                            <label for="invoiceName">单位名称：</label>
                            <input type="text" id="J_invoiceName" name="invoiceName" value="" placeholder="请输入单位名称" />
                        </p>
                        <p class="bor_none">
                            <label>发票内容：</label>
                            <span class="select-menu">
                                <select id="J_invoiceContent">
                                    <c:forEach var="iterm" items="${invoice.lastInvoice.contentList}">
                                        <option value="${iterm}" ${iterm==invoice.lastInvoice.invoiceContent ? "selected=\"selected\"":""}>${iterm}</option>
                                    </c:forEach>
                                </select>
                            </span>
                        </p>
                    </div>
                </div>

                <legend>收票人信息</legend>
                <div class="invoice_address">
                    <p>
                        <label for="invoicePhone">收票人手机：</label>
                        <input type="text" id="J_invoicePhone" name="invoicePhone" value="${lastInvoice.invoiceTel}" placeholder="请输入手机号" required>
                        <%--<c:set var="phone" value="${lastReceived.tel}"></c:set>
                        <c:choose>
                            <c:when test="${temp}">
                                <input type="text" id="J_invoicePhone" name="invoicePhone" value="${lastInvoice.invoiceTel}" placeholder="请输入手机号" required>
                            </c:when>
                            <c:otherwise>&lt;%&ndash;取地址的手机号&ndash;%&gt;
                                <input type="text" id="J_invoicePhone" name="invoicePhone" value="${phone}" placeholder="请输入手机号" required>
                            </c:otherwise>
                        </c:choose>--%>
                    </p>
                    <p class="bor_none">
                        <label for="invoiceMail">收票人邮箱：</label>
                        <input type="text" id="J_invoiceMail" name="invoiceMail" value="" placeholder="可用该邮箱获取电子发票,非必填" />
                    </p>
                </div>

                <div class="payment_submit">
                    <a href="javascript:;" class="payment_btn" id="J_Login_invoice">保存</a>
                </div>
            </fieldset>
        <p class="coupons_tips">温馨提示：根据国家税务政策规定，将全面使用电子发票。电子发票具有纸质发票的所有法律效力、用途及使用规定，成功开票后您可到个人中心下载电子发票文档。原机打纸质发票停用，如有疑问可在线咨询或致电客服！</p>
    </div>
</div>

