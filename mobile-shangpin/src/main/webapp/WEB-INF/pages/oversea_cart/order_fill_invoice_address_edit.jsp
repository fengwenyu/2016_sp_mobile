<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
  <div class="container" id="invoice_address_edit_container">
    
    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">修改发票地址</span>
                
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--发票地址 start-->
     <div class="paymet_block">
          
         <form name="invoice_address_edit_form" id="invoice_address_edit_form">
            <fieldset class="notBtm">
	                <legend>收货地址</legend>
	                <p class="select"><a href="javascript:;" id="invoice_address_edit_select_area" onclick="selectPCAT('invoice_address_edit_');">省市区</a>
		                <input type="hidden" id="invoice_address_edit_province" name="province" />
		                <input type="hidden" id="invoice_address_edit_city" name="city" />
		                 <input type="hidden" id="invoice_address_edit_area" name="area" />
		                 <input type="hidden" id="invoice_address_edit_town" name="town"/>
		                 <input type="hidden" id="invoice_address_edit_id" name="id"/>
	                </p>
	                <p>
	                    <label for="addr">详细地址：</label>
	                    <input type="text" id="invoice_address_edit_addr" name="addr" placeholder="请输入详细街道地址" required maxlength="50" />
	                </p>
	                <p>
	                    <label for="code">邮编信息：</label>
	                    <input type="text" id="invoice_address_edit_postcode" name="postcode" placeholder="请输入您所在地区的邮编" required  maxlength="6" />
	                </p>
	
	                <legend>收货人信息</legend>
	                <p>
	                    <label for="userName">姓名：</label>
	                    <input type="text" id="invoice_address_edit_name" name="name" placeholder="请填写个中文名称" required />
	                </p>
	                <p>
	                    <label for="mobileNum">联系电话：</label>
	                    <input type="text" id="invoice_address_edit_tel" name="tel" placeholder="请输入11位手机号码" required maxlength="11" />
	                </p>
	                <div class="payment_submit">
	                    <a href="javascript:;" class="payment_btn" onclick="submitUpdateInvoiceAddr('invoice_address_edit_')">保存</a>
	                </div>
            </fieldset>
            <div class="explain">
            </div>
        </form>
    </div>
    <!--收货地址 end-->

  </div>