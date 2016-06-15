<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<title>尺码对照</title>
<link href="<%=path %>/css/size.css" rel="stylesheet" />
<script src="<%=path %>/js/zepto.min.js"></script>
<script src="<%=path %>/js/iscroll.js"></script>
<script src="<%=path %>/js/comm_size.js"></script>

</head>

<body>
 <span class="goBack"><a href="javascript:history.go(-1);">返回上一级</a></span>
 <div class="content">
   <nav class="tab_nav">
     <a href="javascript:;" class="cur">男鞋</a>
   </nav>
   <ul class="tab_content" id="tabContent">
     <li>
     <table>
        <tr align="center">
            <td><strong>中国码CN</strong></td>
            <td>39</td>
            <td>39.5</td>
            <td>40</td>
            <td>40.5</td>
            <td>41</td>
            <td>41.5</td>
            <td>42</td>
            <td>42.5</td>
            <td>43</td>
            <td>43.5</td>
            <td>44</td>
            <td>44.5</td>
            <td>45</td>
            <td>45.5</td>
            <td>46</td>
            <td>46.5</td>
        </tr>
        <tr align="center">
            <td><strong>欧洲码EU</strong></td>
            <td>39</td>
            <td>39.5</td>
            <td>40</td>
            <td>40.5</td>
            <td>41</td>
            <td>41.5</td>
            <td>42</td>
            <td>42.5</td>
            <td>43</td>
            <td>43.5</td>
            <td>44</td>
            <td>44.5</td>
            <td>45</td>
            <td>45.5</td>
            <td>46</td>
            <td>46.5</td>
        </tr>
        <tr align="center">
            <td><strong>意大利码IT</strong></td>
            <td>39</td>
            <td>39.5</td>
            <td>40</td>
            <td>40.5</td>
            <td>41</td>
            <td>41.5</td>
            <td>42</td>
            <td>42.5</td>
            <td>43</td>
            <td>43.5</td>
            <td>44</td>
            <td>44.5</td>
            <td>45</td>
            <td>45.5</td>
            <td>46</td>
            <td>46.5</td>
        </tr>
        <tr align="center">
            <td><strong>法国码FR</strong></td>
            <td>39</td>
            <td>39.5</td>
            <td>40</td>
            <td>40.5</td>
            <td>41</td>
            <td>41.5</td>
            <td>42</td>
            <td>42.5</td>
            <td>43</td>
            <td>43.5</td>
            <td>44</td>
            <td>44.5</td>
            <td>45</td>
            <td>45.5</td>
            <td>46</td>
            <td>46.5</td>
        </tr>
        <tr align="center">
            <td><strong>美国码US</strong></td>
            <td>7</td>
            <td></td>
            <td>7.5</td>
            <td></td>
            <td>8</td>
            <td>8.5</td>
            <td>9</td>
            <td>9.5</td>
            <td>10</td>
            <td></td>
            <td>10.5</td>
            <td>11</td>
            <td>11.5</td>
            <td>12</td>
            <td></td>
            <td></td>
        </tr>
        <tr align="center">
            <td><strong>英国码UK</strong></td>
            <td>6</td>
            <td></td>
            <td>6.5</td>
            <td></td>
            <td>7</td>
            <td>7.5</td>
            <td>8</td>
            <td>8.5</td>
            <td>9</td>
            <td></td>
            <td>9.5</td>
            <td>10</td>
            <td>10.5</td>
            <td>11</td>
            <td></td>
            <td></td>
        </tr>
    </table>
     </li>
   </ul>
 </div>
 
</body>
</html>
