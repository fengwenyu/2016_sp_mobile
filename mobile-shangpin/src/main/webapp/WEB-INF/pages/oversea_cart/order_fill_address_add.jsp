<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<div class="container" id="address_add_container">

    <!--头部 start-->
    <div class="topFix">
        <section>
            <div class="topBack">
                <a href="javascript:;" class="backBtn">&nbsp;</a>
                <span class="top-title">新增收货地址</span>
                
            </div>
        </section>
    </div>
    <!--头部 end-->

    <!--收货地址 start-->
    <div class="paymet_block">
        
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
    </div>
    <!--收货地址 end-->
  </div>