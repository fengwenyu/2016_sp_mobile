<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="container" id="invoice_address_add_container">

    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">新增发票地址</span>
                
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--收货地址 start-->
    <div class="paymet_block">
        
         <form name="invoice_address_add_form" id="invoice_address_add_form">
            <fieldset class="notBtm">
	                <legend>收货地址</legend>
	                <p class="select"><a href="javascript:;" id="invoice_address_add_select_area" onclick="selectPCAT('invoice_address_add_');">省市区</a>
		                <input type="hidden" id="invoice_address_add_province" name="province" />
		                <input type="hidden" id="invoice_address_add_city" name="city" />
		                 <input type="hidden" id="invoice_address_add_area" name="area" />
		                 <input type="hidden" id="invoice_address_add_town" name="town"/>
	                </p>
	                <p>
	                    <label for="addr">详细地址：</label>
	                    <input type="text" id="invoice_address_add_addr" name="addr" placeholder="请输入详细街道地址" required maxlength="50" />
	                </p>
	                <p>
	                    <label for="code">邮编信息：</label>
	                    <input type="text" id="invoice_address_add_postcode" name="postcode" placeholder="请输入您所在地区的邮编" required  maxlength="6" />
	                </p>
	
	                <legend>收货人信息</legend>
	                <p>
	                    <label for="userName">姓名：</label>
	                    <input type="text" id="invoice_address_add_name" name="name" placeholder="请填写个中文名称" required />
	                </p>
	                <p>
	                    <label for="mobileNum">联系电话：</label>
	                    <input type="text" id="invoice_address_add_tel" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
	                </p>
	                <div class="payment_submit">
	                    <a href="javascript:;" class="payment_btn" onclick="submitAddInvoiceAddr('invoice_address_add_')">保存</a>
	                </div>
            </fieldset>
        </form>
        </div>
    <!--收货地址 end-->
  </div>