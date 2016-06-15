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
<link rel="shortcut icon" href="http://pic11.shangpin.com/shangpin/images/logo/favicon.ico" />

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
     <a href="#" class="cur">上装</a><a href="#">下装</a><a href="#">内衣</a>
   </nav>
   <ul class="tab_content" id="tabContent">
     <li>
     <table>
        <tr>
            <td colspan="8"><strong>男装（MENSWEAR）</strong> 外衣、衬衫、上装、套装</td>
        </tr>
        <tr align="center">
            <td><strong>国际码</strong></td>
            <td>XS</td>
            <td>S</td>
            <td>M</td>
            <td>L</td>
            <td>XL</td>
            <td>XXL</td>
            <td>3XL</td>
        </tr>
        <tr align="center">
            <td><strong>中国码CN</strong></td>
            <td>160/100A</td>
            <td>165/105A</td>
            <td>170/110A</td>
            <td>175/115A</td>
            <td>180/120A</td>
            <td>185/125A</td>
            <td></td>
        </tr>
        <tr align="center">
            <td></td>
            <td>37</td>
            <td>38</td>
            <td>39</td>
            <td>40</td>
            <td>41</td>
            <td>42</td>
            <td>43</td>
        </tr>
        <tr align="center">
            <td></td>
            <td>86-91</td>
            <td>91-96</td>
            <td>96-101</td>
            <td>101-106</td>
            <td>106-111</td>
            <td>111-116</td>
            <td>116-121</td>
        </tr>
        <tr align="center">
            <td><strong>英国码UK</strong></td>
            <td>34</td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>46</td>
            <td>48</td>
        </tr>
        <tr align="center">
            <td><strong>意大利码IT</strong></td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
            <td>50</td>
            <td>52</td>
            <td>54</td>
            <td>56</td>
        </tr>
        <tr align="center">
            <td><strong>法国码FR</strong></td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
            <td>50</td>
            <td>52</td>
            <td>54</td>
            <td>56</td>
        </tr>
    </table>
    
    <table style="display:none;">
        <tr>
            <td colspan="8"><strong>男装（MENSWEAR）</strong> 下装、牛仔裤</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>国际码</strong></td>
            <td>XS</td>
            <td>S</td>
            <td>M</td>
            <td>L</td>
            <td>XL</td>
            <td>XXL</td>
            <td>3XL</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>中国码CN</strong></td>
            <td>160</td>
            <td>165</td>
            <td>170</td>
            <td>175</td>
            <td>180</td>
            <td>185</td>
            <td>190</td>
        </tr>
        <tr align="center">
            <td width="90"></td>
            <td>28</td>
            <td>29</td>
            <td>30</td>
            <td>31</td>
            <td>32</td>
            <td>33</td>
            <td>34</td>
        </tr>
        <tr align="center">
            <td width="90"></td>
            <td>2尺1</td>
            <td>2尺2</td>
            <td>2尺3</td>
            <td>2尺4</td>
            <td>2尺5</td>
            <td>2尺6</td>
            <td>2尺7</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>美国码US</strong></td>
            <td>28</td>
            <td>30</td>
            <td>32</td>
            <td>34</td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>英国码UK</strong></td>
            <td>28</td>
            <td>30</td>
            <td>32</td>
            <td>34</td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>意大利码IT</strong></td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
            <td>50</td>
            <td>52</td>
            <td>54</td>
            <td>56</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>法国码FR</strong></td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
        </tr>
    </table>
    
    <table style="display:none;">
        <tr>
            <td colspan="6"><strong>内裤Briefs</strong></td>
        </tr>
        <tr align="center">
            <td width="90"><strong>国际码</strong></td>
            <td>S</td>
            <td>M</td>
            <td>L</td>
            <td>XL</td>
            <td>XXL</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>中国码CN</strong></td>
            <td>72-76</td>
            <td>76-81</td>
            <td>81-87</td>
            <td>87-93</td>
            <td>93-98</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>美国码US</strong></td>
            <td>28-30</td>
            <td>30-32</td>
            <td>32-34</td>
            <td>34-38</td>
            <td>38-42</td>
        </tr>
    </table>
     </li>
   </ul>
 </div>
 
</body>
</html>
