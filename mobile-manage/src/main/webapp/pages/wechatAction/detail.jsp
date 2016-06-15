<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>卡券详情</title>
    <%@ include file="/pages/public/common.jspf"%>
    <script type="text/javascript">
    $(document).ready(function(){
	   	function format(txt,compress/*是否为压缩模式*/){/* 格式化JSON源码(对象转换为JSON文本) */  
	       var indentChar = '    ';   
	       if(/^\s*$/.test(txt)){   
	           alert('数据为空,无法格式化! ');   
	           return;   
	       }   
	       try{var data=eval('('+txt+')');}   
	       catch(e){   
	           alert('数据源语法错误,格式化失败! 错误信息: '+e.description,'err');   
	           return;   
	       };   
	       var draw=[],last=false,This=this,line=compress?'':'\n',nodeCount=0,maxDepth=0;   
	          
	       var notify=function(name,value,isLast,indent/*缩进*/,formObj){   
	           nodeCount++;/*节点计数*/  
	           for (var i=0,tab='';i<indent;i++ )tab+=indentChar;/* 缩进HTML */  
	           tab=compress?'':tab;/*压缩模式忽略缩进*/  
	           maxDepth=++indent;/*缩进递增并记录*/  
	           if(value&&value.constructor==Array){/*处理数组*/  
	               draw.push(tab+(formObj?('"'+name+'":'):'')+'['+line);/*缩进'[' 然后换行*/  
	               for (var i=0;i<value.length;i++)   
	                   notify(i,value[i],i==value.length-1,indent,false);   
	               draw.push(tab+']'+(isLast?line:(','+line)));/*缩进']'换行,若非尾元素则添加逗号*/  
	           }else   if(value&&typeof value=='object'){/*处理对象*/  
	                   draw.push(tab+(formObj?('"'+name+'":'):'')+'{'+line);/*缩进'{' 然后换行*/  
	                   var len=0,i=0;   
	                   for(var key in value)len++;   
	                   for(var key in value)notify(key,value[key],++i==len,indent,true);   
	                   draw.push(tab+'}'+(isLast?line:(','+line)));/*缩进'}'换行,若非尾元素则添加逗号*/  
	               }else{   
	                       if(typeof value=='string')value='"'+value+'"';   
	                       draw.push(tab+(formObj?('"'+name+'":'):'')+value+(isLast?'':',')+line);   
	               };   
	       };   
	       var isLast=true,indent=0;   
	       notify('',data,isLast,indent,false);   
	       return draw.join('');   
	   }

		$("#ip_bt").click(function(){
			var v = $("#td_detail").text();
			var f = format(v);
			alert(f);	
			
		})
		
	})
</script>    	
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <!--页面标题-->
        <div id="Title">
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 卡券详情
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
		<thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="20">添加结果</td>          
                <td width="20">操作</td>          
            </tr>
        </thead>
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer">       
            <tr class="TableDetail1 template">
                <td id="td_detail">${cardDetail}</td>                
                <td><input type="button" id="ip_bt" value="格式化"></td>                
            </tr>        
        </tbody>
    </table>
</div>
    
</body>
</html>

