<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>推送消息配置页面</title>
	<%@ include file="/pages/public/common.jspf"%>
	<script type="text/javascript">
		$(function(){
			// 填充渠道列表
			$('#channelList').datagrid({
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${pageContext.request.contextPath }/channelAction_datagridList.action',
				remoteSort: true,
				showFooter:true,
				width: 880,
				loadMsg:'加载数据...',
				singleSelect :true,
				onSelect : function(rowIndex, rowData){				
					$("#channelNumTemp").val(rowData.channelNum);
					$("#channelNum").val(rowData.channelNum);
				},
				frozenColumns:[[
					{field:'select',checkbox:true}
				]],	
				columns:[[
						{field:'channelNum',title:'渠&nbsp;道&nbsp;编&nbsp;号',width:200,align:'center'},
						{field:'channelName',title:'渠&nbsp;道&nbsp;名&nbsp;称',width:240,align:'center'},
						{field:'createTime',title:'创&nbsp;建&nbsp;时&nbsp;间',width:380,align:'center'}	
				]],				
				pagination:true,
				rownumbers:true
			});
		});
		//渠道查询
		function reloadgrid(){
			//执行查询操作
			var pager = $('#channelList').datagrid('getPager');//得到DataGrid页面
				pager.pagination({
					pageNumber:1
				});
			$('#channelList').datagrid('options').queryParams = {channelName:$("#channelName").val()};
			$("#channelList").datagrid('reload');
			$("#channelList").datagrid('resize'); 
    	}
		
		function changeAction(){
			var key=$("#action").val();
			var productNum = $("#productNum").val();
			if(key=="link"){
				$("#tr_url").show();
				//$("#url").val("");
				$("#tr_topicid").hide();
				$("#topicid").val("");
				$("#tr_actid").hide();
				$("#actid").val("");
				$("#tr_productid").hide();
				$("#productid").val("");
				$("#tr_goodsid").hide();
				$("#goodsid").val("");
				$("#tr_type").hide();
				$("#type").val("");
				$("#tr_categoryno").hide();
				$("#categoryno").val("");
				$("#tr_openappuri").hide();
				$("#openappuri").val("");
				$("#tr_appuri").hide();
				$("#appuri").val("");
				//$("#notice").val("");
			}else if(key=="topic"){
				$("#tr_url").hide();
				$("#url").val("");
				$("#tr_topicid").show();
				//$("#topicid").val("");
				$("#tr_actid").hide();
				$("#actid").val("");
				$("#tr_productid").hide();
				$("#productid").val("");
				$("#tr_goodsid").hide();
				$("#goodsid").val("");
				$("#tr_type").hide();
				$("#type").val("");
				$("#tr_categoryno").hide();
				$("#categoryno").val("");
				$("#tr_openappuri").hide();
				$("#openappuri").val("");
				$("#tr_appuri").hide();
				$("#appuri").val("");
				//$("#notice").val("");
			}else if(key=="activity"){
				$("#tr_url").hide();
				$("#url").val("");
				$("#tr_topicid").hide();
				$("#topicid").val("");
				$("#tr_actid").show();
				//$("#actid").val("");
				$("#tr_productid").hide();
				$("#productid").val("");
				$("#tr_goodsid").hide();
				$("#goodsid").val("");
				$("#tr_type").hide();
				$("#type").val("");
				$("#tr_categoryno").hide();
				$("#categoryno").val("");
				$("#tr_openappuri").hide();
				$("#openappuri").val("");
				$("#tr_appuri").hide();
				$("#appuri").val("");
				//$("#notice").val("");
			}else if(key=="detail"){
				if(productNum=="1"||productNum=="101"||productNum=="10000"){//奥莱客户端
					$("#tr_url").hide();
					$("#url").val("");
					$("#tr_topicid").hide();
					$("#topicid").val("");
					$("#tr_actid").hide();
					$("#actid").val("");
					$("#tr_productid").hide();
					$("#productid").val("");
					$("#tr_goodsid").show();
					//$("#goodsid").val("");
					$("#tr_type").show();
					//$("#type").val("");
					$("#tr_categoryno").show();
					//$("#categoryno").val("");
					$("#tr_openappuri").hide();
					$("#openappuri").val("");
					$("#tr_appuri").hide();
					$("#appuri").val("");
					//$("#notice").val("");
				}else if(productNum=="2"||productNum=="102"||productNum=="20000"){//尚品客户端
					$("#tr_url").hide();
					$("#url").val("");
					$("#tr_topicid").show();
					//$("#topicid").val("");
					$("#tr_actid").hide();
					$("#actid").val("");
					$("#tr_productid").show();
					//$("#productid").val("");
					$("#tr_goodsid").hide();
					$("#goodsid").val("");
					$("#tr_type").hide();
					$("#type").val("");
					$("#tr_categoryno").hide();
					$("#categoryno").val("");
					$("#tr_openappuri").hide();
					$("#openappuri").val("");
					$("#tr_appuri").hide();
					$("#appuri").val("");
					//$("#notice").val("");
				}
			}else if(key=="openapp"){
					$("#tr_url").hide();
					$("#url").val("");
					$("#tr_topicid").hide();
					$("#topicid").val("");
					$("#tr_actid").hide();
					$("#actid").val("");
					$("#tr_productid").hide();
					$("#productid").val("");
					$("#tr_goodsid").hide();
					$("#goodsid").val("");
					$("#tr_type").hide();
					$("#type").val("");
					$("#tr_categoryno").hide();
					$("#categoryno").val("");
					$("#tr_openappuri").show();
					//$("#openappuri").val("");
					$("#tr_appuri").show();
					//$("#appuri").val("");
					//$("#notice").val("");
			}else if(key==""){
					$("#tr_url").hide();
					$("#url").val("");
					$("#tr_topicid").hide();
					$("#topicid").val("");
					$("#tr_actid").hide();
					$("#actid").val("");
					$("#tr_productid").hide();
					$("#productid").val("");
					$("#tr_goodsid").hide();
					$("#goodsid").val("");
					$("#tr_type").hide();
					$("#type").val("");
					$("#tr_categoryno").hide();
					$("#categoryno").val("");
					$("#tr_openappuri").hide();
					$("#openappuri").val("");
					$("#tr_appuri").hide();
					$("#appuri").val("");
			}
		}
		function changeProduct(){
			
			var productid = $("#tr_productid").css("display");
			var topicid = $("#tr_topicid").css("display");
			
			var goodsid = $("#tr_goodsid").css("display");
			var type = $("#tr_type").css("display");
			var categoryno = $("#tr_categoryno").css("display");
			
			var productNum = $("#productNum").val();
			var action = $("#action").val();
			if(productNum == "101" || productNum == "102"){
				$("#tr_endTime").show();
				$("#f_showTime").show();
			}else{
				$("#tr_endTime").hide();
				$("#endTime").val("");
				$("#f_showTime").hide();
			}
			
			if(productNum == "1" || productNum == "2"){
			    $("#tr_title").show();
			}else{
				$("#tr_title").hide();
				$("#title").val("");
			}
			
			if(action=="detail"){
				if(productNum=="1"||productNum=="101"||productNum=="10000"){ // 奥莱
					$("#tr_goodsid").show();
					$("#tr_type").show();
					$("#tr_categoryno").show();
					$("#tr_productid").hide();
					$("#productid").val("");;
					$("#tr_topicid").hide();
					$("#topicid").val("");;
				}else if(productNum=="2"||productNum=="102"||productNum=="20000"){//尚品
					$("#tr_goodsid").hide();
					$("#goodsid").val("");;
					$("#tr_type").hide();
					$("#type").val("");;
					$("#tr_categoryno").hide();
					$("#categoryno").val("");;
					$("#tr_productid").show();
					$("#tr_topicid").show();
				}
			}
		}
		
		//取字符串字节数
		function getStringLength(s) {
	        var totalLength = 0;
	        var i;
	        var charCode;
	        for (i = 0; i < s.length; i++) {
	          charCode = s.charCodeAt(i);
	          if (charCode < 0x007f) {
	            totalLength = totalLength + 1;
	          } else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
	            totalLength += 2;
	          } else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
	            totalLength += 3;
	          }
	        }
	        return totalLength;
      	} 
		
		//表单验证
		function checkForm(){
			var url = $("#tr_url").css("display");		
			var topicid = $("#tr_topicid").css("display");		
			var actid = $("#tr_actid").css("display");		
			var productid = $("#tr_productid").css("display");		
			var goodsid = $("#tr_goodsid").css("display");		
			var type = $("#tr_type").css("display");		
			var categoryno = $("#tr_categoryno").css("display");		
			var openappuri = $("#tr_openappuri").css("display");		
			var appuri = $("#tr_appuri").css("display");		
			var action = $("#action").val();
			var channelNum = $("#channelNum").val();
			var productNum = $("#productNum").val();
			var notice = $("#notice").val();
			var title = $("#title").val();
			var dictId=$("#dictId").val();
			if(dictId == ""){
				alert("请选择分类！");
				return false;
			}
			if(productNum == "101" || productNum == "102"){
				var showTime = $("#showTime").val();
				var endTime = $("#endTime").val();
				if(showTime==""){
					$.messager.alert('错误','显示时间不能为空!','error',function(r){
						$("#showTime").focus();
					});	
					return false;
				}
				if(endTime==""){
					$.messager.alert('错误','结束时间不能为空!','error',function(r){
						$("#endTime").focus();
					});	
					return false;
				}
				
				 var a = new Date(showTime.replace("-", "/"));
                 var b = new Date(endTime.replace("-", "/"));
                 if(a >= b){
                 	$.messager.alert('错误','结束时间必须大于显示时间!','error',function(r){
						$("#endTime").focus();
					});	
					return false;
                 }
			}
			
			//content 最大256byte 预留 108byte 最多148byte  {"id":"9999","title":"","aps":{"sound":"default","alert":"","badge":1},"action":""}
			if(notice=="" && action==""){
				$.messager.alert('错误','公告内容和事件不能同时为空!','error',function(r){
					$("#notice").focus();
				});	
				return false;
			}
			else if(notice!="" && getStringLength(notice)>148){
				$.messager.alert('错误','显示文字不能超过148个字节!(' + getStringLength(notice) + ')','error',function(r){
					$("#notice").focus();
				});	
				return false;
			} 
			if(productNum=="1"||productNum=="2") {
				if(dictId == ""){
					$.message.alert('错误','请选择分类!','error',function(r){
						$("#title").focus();
					});
					return false;
				}
					if(title == ""){
						$.messager.alert('错误','标题文字不能为空!','error',function(r){
							$("#title").focus();
						});	
						return false;
					}else if(title != ""){
						var titleLength = getStringLength(title);
						var noticeLength = getStringLength(notice);
						var tlength = noticeLength + titleLength;
				    	if(getStringLength(title)>30){
				    		$.messager.alert('错误','标题文字不能超过30个字节!(' + titleLength + ')','error',function(r){
								$("#title").focus();
							});	
							return false
				    	}else if(tlength > 148){
				    		$.messager.alert('错误','内容和标题文字总数不能超过148个字符!(' + tlength + ')','error',function(r){
								$("#notice").focus();
							});	
							return false;
				    	}
				    }
				    
				}
			
			if(url!="none"&&action=="link"&&$("#url").val()==""){
				$.messager.alert('错误','URL地址不能为空!','error',function(r){
					$("#url").focus();
				});	
				return false;
			}else if(topicid!="none"&&action=="topic"&&$("#topicid").val()==""){
				$.messager.alert('错误','专题编号不能为空!','error',function(r){
					$("#topicid").focus();
				});	
				return false;
			}
			else if(actid!="none"&&action=="activity"&&$("#actid").val()==""){
				$.messager.alert('错误','活动编号不能为空!','error',function(r){
					$("#actid").focus();
				});	
				return false;
			}else if(productid!="none"&&topicid!="none"&&action=="detail"){
				if(productNum=="2"||productNum=="102"||productNum=="20000"){
					if($("#productid").val()==""){
						$.messager.alert('错误','产品编号不能为空!','error',function(r){
							$("#topicid").focus();
						});	
						return false;
					}/*else if($("#topicid").val()==""){
						$.messager.alert('错误','专题编号不能为空!','error',function(r){
							$("#topicid").focus();
						});	
						return false;
					}*/
				}
			}else if(goodsid!="none"&&type!="none"&&categoryno!="none"&&action=="detail"){
				if(productNum=="1"||productNum=="101"||productNum=="10000"){
					if($("#goodsid").val()==""){
						$.messager.alert('错误','产品编号不能为空!','error',function(r){
							$("#goodsid").focus();
						});	
						return false;
					}else if($("#type").val()==""){
						$.messager.alert('错误','商品来源不能为空!','error',function(r){
							$("#type").focus();
						});	
						return false;
					}else if($("#categoryno").val()==""){
						$.messager.alert('错误','子类编号不能为空!','error',function(r){
							$("#categoryno").focus();
						});	
						return false;
					}
				}
			}else if(openappuri!="none"&&appuri!="none"&&action=="openapp"){
				if($("#openappuri").val()==""){
					$.messager.alert('错误','应用打开地址不能为空!','error',function(r){
						$("#openappuri").focus();
					});	
					return false;
				}else if($("#appuri").val()==""){
					$.messager.alert('错误','其他应用下载地址不能为空!','error',function(r){
						$("#appuri").focus();
					});	
					return false;
				}
			}/*else if($("#showTime").val()==""){
				$.messager.alert('错误','显示时间不能为空!','error',function(r){
					$("#showTime").focus();
				});	
				return false;
			}*/
			
		}
		//jQuery的ajax验证用户名是否存在
		function checkUserName(){
			if($("#username").val()==""){
				return;
			}
			$.ajax({
		        type: "POST",
		        url: "${pageContext.request.contextPath }/pushManageAction_checkUserName.action",
		        data:"userName="+$("#username").val()+"&timeStamp="+new Date().getTime(),
		        success: function(data){
					data = eval("("+data+")");
					$("#checkUserName").text("");
					$("#checkUserName").text(data.returnInfo);
		        }
		    });	
		}
	</script>
</head>
<body onload="changeProduct();">
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>&nbsp;推送消息
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<!--显示表单内容-->
<div id=MainArea>
    <form action="<%=path%>/pushManageAction_add.do" id="pushManageForm" method="post" onsubmit="return checkForm();">
    	<input type="hidden" name="userLoginName" value="${user.loginName}"/>
    	<input type="hidden" name="channelNum" id="channelNum"/>
        <div class="ItemBlock_Title1">
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                	<!-- 
                 	<tr>
                    	<td>手机平台</td>
                        <td>
                           <select name="platform" class="SelectStyle" id="platform">
	   							<option value="">请选择平台</option>
	   							<option value="ios">IOS</option>
	   							<option value="android">Android</option>
	   						</select>
	      				   <font color="red"> *</font>
                        </td>
                    </tr>
                	 -->
                    <tr>
                    	<td>产品号</td>
                        <td>
                           <s:select name="productNum" id="productNum" cssClass="SelectStyle" 
                           	list="#productList" listKey="productNum" listValue="productName" onchange="changeProduct();"> 
                           </s:select>
	      				   <font color="red"> *</font>
                        </td>
                    </tr>
                    <tr>
						<td>渠道编号</td>
                        <td>
                       	 	<s:textfield cssClass="InputStyle" id="channelNumTemp" disabled="true"/>
		      			</td>
                    </tr>
                    <tr>
						<td>性别</td>
                        <td>
                       	 	<input type="radio" name="msgType" value="1" />男<input type="radio" name="msgType" value="0" />女 <input type="radio" name="msgType" value="2" checked="checked" />默认
		      			</td>
                     </tr> 
                    <tr>
						<td>分组</td>
                        <td>
                       	 	<s:select name="dictId" id="dictId" cssClass="SelectStyle" 
                           	list="#pushDataList" listKey="id" listValue="dictName"  headerKey="" headerValue="请选择分类" onchange=""> 
                           </s:select>
                            <font color="red"> *</font>
	   					</td>
                    </tr>
                    <tr  id="tr_title">
                    	<td>标题</td>
                    	 <td><s:textfield name="title" cssClass="InputStyle" id="title"/><font color="red"> *</font></td>
                  </tr>
                    <tr>
                    	<td>公告内容</td>
                    	<td><s:textarea name="notice" cssClass="InputStyle" id="notice" cssStyle="height:70px;"/></td>
                    </tr>
                    <tr>
                    	<td>用户名</td>
                        <td><s:textfield name="username" cssClass="InputStyle" id="username" onblur="checkUserName();"/>&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><span id ="checkUserName">${error}</span></font></td>
                    </tr>
                    <tr>
                    	<td>显示时间</td>
					    <td><s:textfield name="showTime" id="showTime" cssClass="InputStyle" onclick="WdatePicker({minDate:'%y-%M-{%d}',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:00'});" />
					    	<font id="f_showTime" color="red" style="display: none"> *</font>
					    </td>
					</tr>
					<tr style="display:none " id="tr_endTime">
                    	<td>结束时间</td>
					    <td><s:textfield name="endTime" id="endTime" cssClass="InputStyle" onclick="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'});" /><font color="red"> *</font></td>
					</tr>
                    <tr>
                    	<td>处理事件</td>
                        <td>
                           <select name="action" class="SelectStyle" id="action" onchange="changeAction();">
	   							<option value="">无特殊事件</option>
	   							<option value=link>用浏览器打开链接</option>
	   							<%--<option value="topic">直接进入专题</option>--%>
	   							<option value="activity">直接进入活动</option>
	   							<option value="detail">直接进入详情页</option>
	   							<%--<option value="openapp">打开其他应用</option>--%>
	   						</select>
                        </td>
                    </tr>
                    <!-- 
                    <tr>
                    	<td>事件参数</td>
                        <td><s:textfield name="actionarg" cssClass="InputStyle" id="actionarg"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr>
                    	<td>详细参数</td>
                        <td><s:textfield name="actionobj" cssClass="InputStyle" id="actionobj"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                     -->
                    <tr style="display:none " id="tr_url">
                    	<td>URL超链接</td>
                        <td><s:textfield name="url" cssClass="InputStyle" id="url"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_topicid">
                    	<td>专题编号（topicid）</td>
                        <td><s:textfield name="topicid" cssClass="InputStyle" id="topicid"/>
                        <!-- 
                        <font color="red"> *&nbsp;</font>
                         -->
                        </td>
                    </tr>
                    <tr style="display:none " id="tr_actid">
                    	<td>活动编号</td>
                        <td><s:textfield name="actid" cssClass="InputStyle" id="actid"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_productid">
                    	<td>产品编号（productid）</td>
                        <td><s:textfield name="productid" cssClass="InputStyle" id="productid"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_goodsid">
                    	<td>产品编号（goodsid）</td>
                        <td><s:textfield name="goodsid" cssClass="InputStyle" id="goodsid"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_type">
                    	<td>商品来源（type）</td>
                        <td><s:textfield name="type" cssClass="InputStyle" id="type"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_categoryno">
                    	<td>子类编号（categoryno）</td>
                        <td><s:textfield name="categoryno" cssClass="InputStyle" id="categoryno"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_openappuri">
                    	<td>应用打开地址</td>
                        <td><s:textfield name="openappuri" cssClass="InputStyle" id="openappuri"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                    <tr style="display:none " id="tr_appuri">
                    	<td>其他应用下载地址</td>
                        <td><s:textfield name="appuri" cssClass="InputStyle" id="appuri"/><font color="red"> *&nbsp;</font></td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
        </div>
    </form>
</div>
<div class="Description" style="margin-left: 50px;">
	<div style="height: 30px;margin: 0 0 10px 0">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>渠道名称：<s:textfield name="channelName" cssClass="InputStyle" id="channelName"/></td>
				<td><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG" onclick="reloadgrid();"/></td>
			</tr>
		</table>
	</div>
	<div id="channelListDiv">
		<table id="channelList"></table>
	</div>
</div>
</body>
</html>
	