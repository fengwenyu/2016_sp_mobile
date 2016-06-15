<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>产品信息</title>
   <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
		$(document).ready(function(){
			
			//校验
			function verify(e){
				var val = $.trim($(e).val());
				var prompt = $(e).next("label").text();
				if(prompt.indexOf("*") >=0 && val ==''){
					var title = $(e).parent().prev("td").text();
					alert(title+"不能为空");return false;
				}
				if(prompt.indexOf("字数上限为") >=0){
					var _s = prompt.indexOf("字数上限为");
					var _e = prompt.indexOf("个汉字");
					var len = val.length;					
					var _size = prompt.substring((_s+5),_e);					
					if(len > _size){
						alert(prompt);
						return false;
					}
				}
				return true;
			}
			
			//input失去焦点
			$(":text:enabled:not(:hidden)").blur(function(){
				verify(this);
			})			
			//提交
			$("#wechatInfo").submit(function(){
				var _re = true;
		 		$(":text:enabled:not(:hidden)").each(function(){
					_re = verify(this);
					if(!_re){
						return _re;
					}
				}) 
			
				if( $("#date_type_fix_time_range").is(":checked") && ($("#begin_timestamp").val() == '' || $("#end_timestamp").val() == '')){
						alert("使用时间的类型:不能为空");return false;
				}
				return _re;
			})
			
			//根据单选框显示相应项
			function changeShow(clazz,showId){				
				var _clazz = "."+clazz;
				
				$(_clazz+":not(#tr_"+showId+")").hide();
				
				$("#"+clazz+" :radio").change(function(){
					var cardType = $("#"+clazz+" :radio:checked").val();
					$(_clazz).hide();
					$(_clazz+" :input").val("");
					$("#tr_"+cardType + _clazz).show();
				})
			}
			
			//卡类型单选框
			changeShow("tr_card_type","GROUPON");
			//时间限制单选框
			changeShow("tr_date_type","DATE_TYPE_FIX_TIME_RANGE");
			
			var pageNum = 1;
			var rows = 5;
			
			//查门店
			$("#get_poi_list").click(function(){
				
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath }/wechatAction_getPoiList.action?pageNum="+pageNum+"&rows="+rows,
				
					dateType:"html",
					success: function(data){
						if(typeof(data)=='undefined'){
							alert("系统错误");
							return;
						}
						var jsonData = eval('(' + data + ')');
						var returnInfo = eval('(' + jsonData.returnInfo + ')');					
						if(returnInfo.total_count <= 0){
							alert("还没没有门店");
							return;
						}
						var businessList =returnInfo.business_list;					
					
						if(businessList.length <=0){
							alert("门店已加载完毕");
							return;
						}
						var poiList = "";
						for(var i = 0; i<businessList.length; i++ ){
							var baseInfo = businessList[i];
							poiList +=  "<tr>"+
											"<td><input type='checkbox' name='base_info.location_id_list' value='"+baseInfo.base_info.poi_id+"'/></td>"+
									        "<td>"+baseInfo.base_info.business_name+"</td>"+							        
									        "<td>"+baseInfo.base_info.address+"</td>"+
								        "</tr>";
								      				
						}						
						$("#poi_list_table tbody").append(poiList);						
						pageNum++;
					}
				})
			})
			
		})
	</script>
</head>
<body>
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 创建卡券
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="wechatInfo" action="wechatAction_add">
    	
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 创建卡券 </div> 
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
	            	<tr id="tr_card_type">
	            		<td>卡券类型:</td>
	            		<td>
	            			<%-- <s:radio name="cardType" list="%{#{'GROUPON':'团购券','CASH':'代金券','DISCOUNT':'折扣券','GIFT':'礼品券','GENERAL_COUPON':'优惠券'}}" value="GROUPON" theme="simple"></s:radio> 
	            			 --%>
			            	<input type="radio" name="cardType" checked value="GROUPON" >团购券
			            	<input type="radio" name="cardType"  value="CASH" >代金券
			            	<input type="radio" name="cardType"  value="DISCOUNT" >折扣券
			            	<input type="radio" name="cardType"  value="GIFT" >礼品券
			            	<input type="radio" name="cardType"  value="GENERAL_COUPON" >优惠券
		            	</td>
		            </tr>		            
	
                    <tr id="tr_GROUPON" class="tr_card_type">
                      <td>团购详情:</td>
                      <td>
                         <s:textfield name="deal_detail" id="deal_detail" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="deal_detail">*</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_CASH" class="tr_card_type">
                      <td>起用金额（单位为分）:</td>
                      <td>
                        <s:textfield name="least_cost" id="least_cost" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="least_cost">*如果无起用门槛则填0。</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_CASH" class="tr_card_type">
                      <td>减免金额（单位为分）:</td>
                      <td>
                        <s:textfield name="reduce_cost" id="reduce_cost" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="reduce_cost">*</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_DISCOUNT" class="tr_card_type">
                      <td>打折额度（百分比） :</td>
                      <td>
                        <s:textfield name="discount" id="discount" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="discount">*填30就是七折</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_GIFT" class="tr_card_type">
                      <td>礼品的名称:</td>
                      <td>
                        <s:textfield name="gift" id="gift" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="gift">*</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_GENERAL_COUPON" class="tr_card_type">
                      <td>优惠详情:</td>
                      <td>
                        <s:textfield name="default_detail" id="default_detail" cssClass="InputStyle" />                        
                         <label><font color="red"><span id ="default_detail">*</span></font></label>
                      </td>
                    </tr>
                  
		            <tr>
                      <td>基础属性 </td>
                      <td>
						<hr/>
                      </td>
                    </tr> 
                    <tr>
                    	<td>卡券的商户logo:</td>
                        <td>
                        	<c:set var="logo_url_set" value="http://mmbiz.qpic.cn/mmbiz/uSsXTbwzqVdHRKRo7ogapqqMvZccVGW7GLYlia45pLLUaz6ulpPia3Ow9bOV8UXgsm9GE0TNUAnEGjzZdm45BXlw/0"/>                       	
                        	<img alt="" height="5%" width="5%" src="${logo_url_set}">
                        	<input type="hidden" name="base_info.logo_url" id="logo_url" cssClass="InputStyle" value="${logo_url_set}" />                        
	                        <label><font color="green"><span id ="logo_url"></span>(商户logo,如需修改请登录商户后台操作)</font></label>                        	
						</td>
                    </tr>
                    <tr>
                    	<td>Code展示类型:</td>
					    <td>
					    	<select name="base_info.code_type" class="SelectStyle" id="code_type">
	   							<option value="CODE_TYPE_TEXT">文本</option>
	   							<option value="CODE_TYPE_BARCODE">一维码 </option>
	   							<option value="CODE_TYPE_ONLY_BARCODE">一维码无code显示</option>
	   							<option value="CODE_TYPE_QRCODE">二维码</option>
	   							<option value="CODE_TYPE_ONLY_QRCODE">二维码无code显示</option>
	   						</select>
					    </td>
                    </tr>
                    <tr>
                    	<td>商户名字</td>
                        <td>
                        	<s:textfield name="base_info.brand_name" id="brand_name" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="brand_name"></span>*字数上限为12个汉字</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>卡券名</td>
                        <td>
                        	<s:textfield name="base_info.title" id="title2" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="title2"></span>*字数上限为9个汉字</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>券名</td>
                        <td>
                        	<s:textfield name="base_info.sub_title" id="sub_title" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="sub_title">字数上限为18个汉字</span></font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>券颜色:</td>
					    <td>
					    	<select name="base_info.color" class="SelectStyle" id="color">
	   							<option value="Color010" style="background-color:#55bd47">#Color010</option>
								<option value="Color020" style="background-color:#10ad61">#Color020</option>
								<option value="Color030" style="background-color:#35a4de">#Color030</option>
								<option value="Color040" style="background-color:#3d78da">#Color040</option>
								<option value="Color050" style="background-color:#9058cb">#Color050</option>
								<option value="Color060" style="background-color:#de9c33">#Color060</option>
								<option value="Color070" style="background-color:#ebac16">#Color070</option>
								<option value="Color080" style="background-color:#f9861f">#Color080</option>
								<option value="Color081" style="background-color:#f08500">#Color081</option>
								<option value="Color082" style="background-color:#a9d92d">#Color082</option> 
								<option value="Color090" style="background-color:#e75735">#Color090</option>
								<option value="Color100" style="background-color:#d54036">#Color100</option>
								<option value="Color101" style="background-color:#cf3e36">#Color101</option>
								<option value="Color102" style="background-color:#5E6671">#Color102</option>
	   						</select>
					    </td>
                    </tr>
                    <tr>
                    	<td>卡券使用提醒</td>
                        <td>
                        	<s:textfield name="base_info.notice" id="notice" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="notice"></span>*字数上限为16个汉字</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>卡券使用说明</td>
                        <td>
                        	<s:textfield name="base_info.description" id="description2" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="description2"></span>*</font></label>
						</td>
                    </tr>
                    <tr>
                    	<td>卡券库存的数量</td>
                        <td>
                        	<s:textfield name="base_info.sku.quantity" id="quantity" cssClass="InputStyle" />                        
	                        <label><font color="red"><span id ="quantity"></span>*上限为100000000</font></label>
						</td>
                    </tr>
                    <tr>
                      <td>每人可领券的数量限制,默认为50</td>
                      <td>
                          <s:textfield name="base_info.get_limit" id="get_limit" cssClass="InputStyle" value="1"/>                        
                          <label><font color="red"><span id ="get_limit">*</span></font></label>
                      </td>
                    </tr>
                    <tr id="tr_date_type">
                    	<td>使用时间的类型:</td>
                    	<td>
                    		<input id="date_type_fix_time_range" type="radio" name="base_info.date_info.type" checked value="DATE_TYPE_FIX_TIME_RANGE" >固定日期区间
	            			<input type="radio" name="base_info.date_info.type"  value="DATE_TYPE_FIX_TERM" >固定时长(自领取后按天算)
	            			<label><font color="red"><span id ="get_limit">*</span></font></label>
                    	</td>					   
                    </tr>
                    <tr id="tr_DATE_TYPE_FIX_TIME_RANGE" class="tr_date_type">
                    	<td></td>
					    <td>					    
					    	开始时间<input readonly="readonly" id="begin_timestamp" name="base_info.date_info.begin_timestamp" type="text" onclick="WdatePicker()"/>
					    	<label><font color="red"><span id ="get_limit"></span></font></label>
					    	结束时间<input readonly="readonly" id="end_timestamp" name="base_info.date_info.end_timestamp" type="text" onclick="WdatePicker()"/>
					    	<label><font color="red"><span id ="get_limit"></span></font></label>					    	
					    </td>
                    </tr>
                    <tr id="tr_DATE_TYPE_FIX_TERM" class="tr_date_type">
                    	<td></td>
					    <td>					    	
					    	领取后<input id="fixed_begin_term" name="base_info.date_info.fixed_begin_term" type="text" />天生效,
					    	<label><font color="red"><span id ="get_limit">*</span></font></label>
					    	<input id="fixed_term" name="base_info.date_info.fixed_term" type="text"/>天有效
					    	<label><font color="red"><span id ="get_limit">*</span></font></label>
					    </td>
                    </tr>
                   
                    <tr>
                      <td>以下为非必填项</td>
                      <td>
						<hr/>
                      </td>
                    </tr>                                
                    <tr>
                      <td>是否自定义Code码:</td>
                      <td>
                        <input type="radio" name="base_info.use_custom_code" value="true" >是
                        <input type="radio" name="base_info.use_custom_code" checked value="false" >否
                        <label><font color="green"><span id ="service_phone">(如果选择是请确认已导入code到微信服务器)</span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>是否指定用户领取:</td>
                      <td>
                        <input type="radio" name="base_info.bind_openid"  value="true" >是
                        <input type="radio" name="base_info.bind_openid" checked value="false" >否
                      </td>
                    </tr>
                    <tr>
                      <td>客服电话</td>
                      <td>                        
                          <s:textfield name="base_info.service_phone" id="service_phone" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="service_phone"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>门店位置poiid</td>
                      <td>                      
                          <s:a href="javascript:void(0)" id="get_poi_list">点击选择门店</s:a>		
                          <table id="poi_list_table"><tbody></tbody></table>						
                      </td>
                    </tr>
                    <tr>
                      <td>第三方来源名</td>
                      <td>                        
                          <s:textfield name="base_info.source" id="source" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="source"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>自定义跳转外链的入口名字</td>
                      <td>                        
                          <s:textfield name="base_info.custom_url_name" id="custom_url_name" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="custom_url_name"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>自定义跳转的URL</td>
                      <td>                        
                          <s:textfield name="base_info.custom_url" id="custom_url" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="custom_url"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>显示在入口右侧的提示语</td>
                      <td>                        
                          <s:textfield name="base_info.custom_url_sub_title" id="custom_url_sub_title" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="custom_url_sub_title"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>产品介绍</td>
                      <td>                        
                          <s:textfield name="base_info.promotion_url_name" id="promotion_url_name" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="promotion_url_name"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>入口跳转外链的地址链接</td>
                      <td>
                        
                          <s:textfield name="base_info.promotion_url" id="promotion_url" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="promotion_url"></span></font></label>
                      </td>
                    </tr>
                    <tr>
                      <td>显示在营销入口右侧的提示语</td>
                      <td>                        
                          <s:textfield name="base_info.promotion_url_sub_title" id="promotion_url_sub_title" cssClass="InputStyle" />                        
                          <label><font color="red"><span id ="promotion_url_sub_title"></span></font></label>
                      </td>
                    </tr>

                    <tr>
                      <td>卡券领取页面是否可分享:</td>
                      <td>
                        <input type="radio" name="base_info.can_share" checked value="true" >是
                        <input type="radio" name="base_info.can_share"  value="false" >否
                      </td>
                    </tr>
                    <tr>
                      <td>卡券是否可转赠:</td>
                      <td>
                        <input type="radio" name="base_info.can_give_friend" checked value="true" >是
                        <input type="radio" name="base_info.can_give_friend"  value="false" >否
                      </td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>
</body>
</html>
