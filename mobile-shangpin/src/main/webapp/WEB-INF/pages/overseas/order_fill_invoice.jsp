<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="container"  id="invoice_container">
    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">发票信息</span>
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--收货地址 start-->
    <div class="paymet_block">
        
        <form name="login" id="J_Login">
            <fieldset class="notBtm">
                <legend>发票详情：</legend>
                <p>
                    <label for="invoiceName">发票抬头：</label>
                    <input type="text" id="J_invoiceName" name="invoiceName" placeholder="个人、公司/名称" required value="个人" />
                </p>
                <p>
                    <label>发票内容：</label>
                    <span class="select-menu">
                    	<select id="J_invoiceContent" >
	                   		 <option value="商品明细"  selected = "selected">商品明细</option>
	                        <option value="箱包">箱包</option>
	                        <option value="饰品">饰品</option>
	                        <option value="化妆品">化妆品</option>
	                        <option value="钟表">钟表</option>
	                        <option value="服饰">服饰</option>
	                        <option value="鞋帽">鞋帽</option>
	                        <option value="眼镜">眼镜</option>
                        </select>
                    </span>
                </p>
                <legend>发票邮寄地址</legend>
                <p>
                    <a href="javascript:;" class="select-address cur">
                        <em>与收货地址相同</em>
                    </a>
                </p>
                <p class="select other-address">
                	<a href="javascript:;" id="to_invoice_address">其他地址：
                        <em></em>
                    </a>
                </p>
               
            </fieldset>
        </form>
        <p class="coupons_tips"><br><br><br>温馨提示:手表类商品只能开具为品牌和型号，其它内容无法开具。如您的商品由品牌方发货，发票则由尚品网单独发出。如需开增值类发票，请联系尚品客服4006-900-900</p>
    </div>
    </div>