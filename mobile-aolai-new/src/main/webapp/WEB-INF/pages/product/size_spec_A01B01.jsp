<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/include.inc.jsp"%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<title>尺码对照</title>
<link href="${cdn:css(pageContext.request)}/styles/shangpin/css/size.css${ver}" rel="stylesheet" />
<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/zepto.min.js${ver}"></script>
<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/iscroll.js${ver}"></script>
<script src="${cdn:css(pageContext.request)}/styles/shangpin/js/comm_size.js${ver}"></script>
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
            <td colspan="7"><strong>女装（Womens）</strong> T恤、上衣、夹克、大衣、连衣裙</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>中国码CN</strong></td>
            <td>155</td>
            <td>160</td>
            <td>165</td>
            <td>170</td>
            <td>175</td>
            <td>180</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>国际码</strong></td>
            <td>XS</td>
            <td>S</td>
            <td>M</td>
            <td>L</td>
            <td>XL</td>
            <td>XXL</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>美国码US</strong></td>
            <td>0</td>
            <td>2</td>
            <td>4</td>
            <td>6</td>
            <td>8</td>
            <td>10</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>英国码UK</strong></td>
            <td>6</td>
            <td>8</td>
            <td>10</td>
            <td>12</td>
            <td>14</td>
            <td>16</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>意大利码IT</strong></td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>法国码FR</strong></td>
            <td>34</td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>44</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>法国码FR(D)</strong></td>
            <td>T0</td>
            <td>T1</td>
            <td>T2</td>
            <td>T3</td>
            <td>T4</td>
            <td>T5</td>
        </tr>
    </table>
    
    <table style="display:none;">
        <tr>
            <td colspan="7"><strong>女装（Womens）</strong> 裙装、裤装</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>中国码CN</strong></td>
            <td>155</td>
            <td>160</td>
            <td>165</td>
            <td>170</td>
            <td>175</td>
            <td>180</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>国际码</strong></td>
            <td>XS</td>
            <td>S</td>
            <td>M</td>
            <td>L</td>
            <td>XL</td>
            <td>XXL</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>美国码US</strong></td>
            <td>0</td>
            <td>2</td>
            <td>4</td>
            <td>6</td>
            <td>8</td>
            <td>10</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>英国码UK</strong></td>
            <td>6</td>
            <td>8</td>
            <td>10</td>
            <td>12</td>
            <td>14</td>
            <td>16</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>意大利码IT</strong></td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>44</td>
            <td>46</td>
            <td>48</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>法国码FR</strong></td>
            <td>34</td>
            <td>36</td>
            <td>38</td>
            <td>40</td>
            <td>42</td>
            <td>44</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>通用码</strong></td>
            <td>25</td>
            <td>26</td>
            <td>27</td>
            <td>28</td>
            <td>29</td>
            <td>30</td>
        </tr>
        <tr align="center">
            <td width="90"><strong>腰围（参考）</strong></td>
            <td>1尺8-1尺9</td>
            <td>1尺9-2尺</td>
            <td>2尺-2尺1</td>
            <td>2尺1-2尺2</td>
            <td>2尺2-2尺3</td>
            <td>2尺3-2尺4</td>
        </tr>
    </table>
    
    <table style="display:none;">
        <tr>
            <td colspan="17"><strong>内衣Bras</strong></td>
        </tr>
        <tr align="center">
            <td><strong>国际码</strong></td>
            <td>XXS</td>
            <td>XS</td>
            <td>S</td>
            <td>S</td>
            <td>XS,S</td>
            <td>S</td>
            <td>S,M</td>
            <td>M</td>
            <td>M</td>
            <td>M</td>
            <td>M,L</td>
            <td>L</td>
            <td>L</td>
            <td>L,XL</td>
            <td>L,XL</td>
            <td>XL</td>
        </tr>
        <tr align="center">
            <td><strong>美国码US/英国码UK</strong></td>
            <td>32A</td>
            <td>32B</td>
            <td>32C</td>
            <td>32D</td>
            <td>34A</td>
            <td>34B</td>
            <td>34C</td>
            <td>34D</td>
            <td>36A</td>
            <td>36B</td>
            <td>36C</td>
            <td>36D</td>
            <td>38A</td>
            <td>38B</td>
            <td>38C</td>
            <td>38D</td>
        </tr>
        <tr align="center">
            <td><strong>欧洲码EU</strong></td>
            <td>70A</td>
            <td>70B</td>
            <td>70C</td>
            <td>70D</td>
            <td>75A</td>
            <td>75B</td>
            <td>75C</td>
            <td>75D</td>
            <td>80A</td>
            <td>80B</td>
            <td>80C</td>
            <td>80D</td>
            <td>85A</td>
            <td>85B</td>
            <td>85C</td>
            <td>85D</td>
        </tr>
        <tr align="center">
            <td><strong>意大利码IT</strong></td>
            <td>1A</td>
            <td>1B</td>
            <td>1C</td>
            <td>1D</td>
            <td>2A</td>
            <td>2B</td>
            <td>2C</td>
            <td>2D</td>
            <td>3A</td>
            <td>3B</td>
            <td>3C</td>
            <td>3D</td>
            <td>4A</td>
            <td>4B</td>
            <td>4C</td>
            <td>4D</td>
        </tr>
        <tr align="center">
            <td><strong>法国码FR</strong></td>
            <td>85A</td>
            <td>85B</td>
            <td>85C</td>
            <td>85D</td>
            <td>90A</td>
            <td>90B</td>
            <td>90C</td>
            <td>90D</td>
            <td>95A</td>
            <td>95B</td>
            <td>95C</td>
            <td>95D</td>
            <td>100A</td>
            <td>100B</td>
            <td>100C</td>
            <td>100D</td>
        </tr>
    </table>
     </li>
   </ul>
 </div>
</body>
</html>
