<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <title>微信公众平台</title>
    <%@ include file="/pages/public/common.jspf" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/base2968da.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/advanced_reply_common27d7ed.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/dialog_img_pick29f4d5.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/emotion_editor23b187.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/msg_tab25df2d.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/msg_sender2968da.css">

</head>
<body>

<div id="body" class="body page_advanced_reply">
    <div id="js_container_box" class="container_box cell_layout side_l">

        <div class="col_main">


            <div class="main_hd">
                <h2>自动回复</h2>
            </div>
            <div class="main_bd">

                <div class="content_wrap" id="div_replyContent" style="">
                    <div class="global_mod mt_layout reply_tab_wrp">
                        <div class="section_tab">
                            <ul class="tab_navs">
                                <li class="tab_nav  ">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_beadd.action">被添加自动回复</a>
                                </li>
                                <li class="tab_nav selected">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_common.action">消息自动回复</a>
                                </li>
                                <li class="tab_nav no_extra ">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_keywords.action">关键词自动回复</a>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <div class="msg_sender" id="js_msgSender">
                        <div class="msg_tab">
                            <div class="tab_navs_panel">
                                <span class="tab_navs_switch_wrp switch_prev js_switch_prev">
                                    <span class="tab_navs_switch"></span>
                                </span>
                                <span class="tab_navs_switch_wrp switch_next js_switch_next">
                                    <span class="tab_navs_switch"></span>
                                </span>
                                <div class="tab_navs_wrp">
                                    <ul class="tab_navs js_tab_navs" style="margin-left:0;">

                                        <li class="tab_nav tab_text width4 <s:if test="autoReplyBeadd.replyList[0].type == 1">selected</s:if>"
                                            data-type="1" data-tab=".js_textArea" data-tooltip="文字">
                                            <a href="javascript:void(0);" onclick="return false;">&nbsp;<i
                                                    class="icon_msg_sender"></i><span
                                                    class="msg_tab_title">文字</span></a>
                                        </li>

                                        <li class="tab_nav tab_img width4 <s:if test="autoReplyBeadd.replyList[0].type == 2">selected</s:if>"
                                            data-type="2" data-tab=".js_imgArea" data-tooltip="图片">
                                            <a href="javascript:void(0);" onclick="return false;">&nbsp;<i
                                                    class="icon_msg_sender"></i><span
                                                    class="msg_tab_title">图片</span></a>
                                        </li>

                                    </ul>
                                </div>
                            </div>
                            <div class="tab_panel">

                                <div class="tab_content" style="display: <s:if test="autoReplyBeadd.replyList[0].type == 1"> block</s:if><s:else> none</s:else>;">
                                    <div class="js_textArea inner no_extra"><div class="emotion_editor">
                                        <div class="edit_area js_editorArea" style="display: none;"></div>
                                        <div id="edit_area_content" class="edit_area js_editorArea" contenteditable="true" style="overflow-y: auto; overflow-x: hidden;"><s:if test="autoReplyBeadd.replyList[0].type == 1">${autoReplyBeadd.replyList[0].content}</s:if></div>
                                        <div class="editor_toolbar">

                                            <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch">表情</a>
                                            <p class="editor_tip opr_tips">，按下Shift+Enter键换行</p>
                                            <p class="editor_tip js_editorTip">还可以输入<em>595</em>字</p>
                                            <div class="emotion_wrp js_emotionArea" style="display: none;">
                                        <span class="hook">
                                            <span class="hook_dec hook_top"></span>
                                            <span class="hook_dec hook_btm"></span>
                                        </span>
                                                <ul class="emotions" onselectstart="return false;">

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/0.gif" data-title="微笑" style="background-position:0px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/1.gif" data-title="撇嘴" style="background-position:-24px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/2.gif" data-title="色" style="background-position:-48px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/3.gif" data-title="发呆" style="background-position:-72px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/4.gif" data-title="得意" style="background-position:-96px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/5.gif" data-title="流泪" style="background-position:-120px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/6.gif" data-title="害羞" style="background-position:-144px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/7.gif" data-title="闭嘴" style="background-position:-168px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/8.gif" data-title="睡" style="background-position:-192px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/9.gif" data-title="大哭" style="background-position:-216px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/10.gif" data-title="尴尬" style="background-position:-240px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/11.gif" data-title="发怒" style="background-position:-264px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/12.gif" data-title="调皮" style="background-position:-288px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/13.gif" data-title="呲牙" style="background-position:-312px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/14.gif" data-title="惊讶" style="background-position:-336px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/15.gif" data-title="难过" style="background-position:-360px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/16.gif" data-title="酷" style="background-position:-384px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/17.gif" data-title="冷汗" style="background-position:-408px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/18.gif" data-title="抓狂" style="background-position:-432px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/19.gif" data-title="吐" style="background-position:-456px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/20.gif" data-title="偷笑" style="background-position:-480px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/21.gif" data-title="可爱" style="background-position:-504px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/22.gif" data-title="白眼" style="background-position:-528px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/23.gif" data-title="傲慢" style="background-position:-552px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/24.gif" data-title="饥饿" style="background-position:-576px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/25.gif" data-title="困" style="background-position:-600px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/26.gif" data-title="惊恐" style="background-position:-624px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/27.gif" data-title="流汗" style="background-position:-648px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/28.gif" data-title="憨笑" style="background-position:-672px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/29.gif" data-title="大兵" style="background-position:-696px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/30.gif" data-title="奋斗" style="background-position:-720px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/31.gif" data-title="咒骂" style="background-position:-744px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/32.gif" data-title="疑问" style="background-position:-768px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/33.gif" data-title="嘘" style="background-position:-792px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/34.gif" data-title="晕" style="background-position:-816px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/35.gif" data-title="折磨" style="background-position:-840px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/36.gif" data-title="衰" style="background-position:-864px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/37.gif" data-title="骷髅" style="background-position:-888px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/38.gif" data-title="敲打" style="background-position:-912px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/39.gif" data-title="再见" style="background-position:-936px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/40.gif" data-title="擦汗" style="background-position:-960px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/41.gif" data-title="抠鼻" style="background-position:-984px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/42.gif" data-title="鼓掌" style="background-position:-1008px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/43.gif" data-title="糗大了" style="background-position:-1032px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/44.gif" data-title="坏笑" style="background-position:-1056px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/45.gif" data-title="左哼哼" style="background-position:-1080px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/46.gif" data-title="右哼哼" style="background-position:-1104px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/47.gif" data-title="哈欠" style="background-position:-1128px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/48.gif" data-title="鄙视" style="background-position:-1152px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/49.gif" data-title="委屈" style="background-position:-1176px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/50.gif" data-title="快哭了" style="background-position:-1200px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/51.gif" data-title="阴险" style="background-position:-1224px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/52.gif" data-title="亲亲" style="background-position:-1248px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/53.gif" data-title="吓" style="background-position:-1272px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/54.gif" data-title="可怜" style="background-position:-1296px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/55.gif" data-title="菜刀" style="background-position:-1320px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/56.gif" data-title="西瓜" style="background-position:-1344px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/57.gif" data-title="啤酒" style="background-position:-1368px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/58.gif" data-title="篮球" style="background-position:-1392px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/59.gif" data-title="乒乓" style="background-position:-1416px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/60.gif" data-title="咖啡" style="background-position:-1440px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/61.gif" data-title="饭" style="background-position:-1464px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/62.gif" data-title="猪头" style="background-position:-1488px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/63.gif" data-title="玫瑰" style="background-position:-1512px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/64.gif" data-title="凋谢" style="background-position:-1536px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/65.gif" data-title="示爱" style="background-position:-1560px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/66.gif" data-title="爱心" style="background-position:-1584px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/67.gif" data-title="心碎" style="background-position:-1608px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/68.gif" data-title="蛋糕" style="background-position:-1632px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/69.gif" data-title="闪电" style="background-position:-1656px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/70.gif" data-title="炸弹" style="background-position:-1680px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/71.gif" data-title="刀" style="background-position:-1704px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/72.gif" data-title="足球" style="background-position:-1728px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/73.gif" data-title="瓢虫" style="background-position:-1752px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/74.gif" data-title="便便" style="background-position:-1776px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/75.gif" data-title="月亮" style="background-position:-1800px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/76.gif" data-title="太阳" style="background-position:-1824px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/77.gif" data-title="礼物" style="background-position:-1848px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/78.gif" data-title="拥抱" style="background-position:-1872px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/79.gif" data-title="强" style="background-position:-1896px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/80.gif" data-title="弱" style="background-position:-1920px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/81.gif" data-title="握手" style="background-position:-1944px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/82.gif" data-title="胜利" style="background-position:-1968px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/83.gif" data-title="抱拳" style="background-position:-1992px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/84.gif" data-title="勾引" style="background-position:-2016px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/85.gif" data-title="拳头" style="background-position:-2040px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/86.gif" data-title="差劲" style="background-position:-2064px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/87.gif" data-title="爱你" style="background-position:-2088px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/88.gif" data-title="NO" style="background-position:-2112px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/89.gif" data-title="OK" style="background-position:-2136px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/90.gif" data-title="爱情" style="background-position:-2160px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/91.gif" data-title="飞吻" style="background-position:-2184px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/92.gif" data-title="跳跳" style="background-position:-2208px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/93.gif" data-title="发抖" style="background-position:-2232px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/94.gif" data-title="怄火" style="background-position:-2256px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/95.gif" data-title="转圈" style="background-position:-2280px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/96.gif" data-title="磕头" style="background-position:-2304px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/97.gif" data-title="回头" style="background-position:-2328px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/98.gif" data-title="跳绳" style="background-position:-2352px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/99.gif" data-title="挥手" style="background-position:-2376px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/100.gif" data-title="激动" style="background-position:-2400px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/101.gif" data-title="街舞" style="background-position:-2424px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/102.gif" data-title="献吻" style="background-position:-2448px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/103.gif" data-title="左太极" style="background-position:-2472px 0;">
                                                        </i>
                                                    </li>

                                                    <li class="emotions_item">
                                                        <i class="js_emotion_i" data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/104.gif" data-title="右太极" style="background-position:-2496px 0;">
                                                        </i>
                                                    </li>

                                                </ul>
                                                <span class="emotions_preview js_emotionPreviewArea"><img src="" alt=""></span>
                                            </div>
                                        </div>
                                    </div>

                                    </div>
                                </div>

                                <div class="tab_content" style="display: <s:if test='autoReplyBeadd.replyList[0].type == 2'>block</s:if><s:else> none</s:else>;">
                                    <div class="js_imgArea inner ">
                                        <!--type 10图文 2图片  3语音 15视频 11商品消息-->
                                        <div class="tab_cont_cover jsMsgSendTab" data-index="1" data-type="2" style="display: <s:if test='autoReplyBeadd.replyList[0].type == "2"'>none</s:if><s:else> block</s:else>;">
                                            <div class="media_cover">
                                            <span class="create_access">
                                                <a class="add_gray_wrp jsMsgSenderPopBt" href="javascript:;" data-type="2" data-index="1">
                                                    <i class="icon36_common add_gray"></i>
                                                    <strong>从素材库中选择</strong>
                                                </a>
                                            </span>
                                            </div>
                                            <div class="media_cover">
                                            <span class="create_access webuploader-container">
                                                <a class="add_gray_wrp webuploader-pick" id="msgSendImgUploadBt" data-type="2"
                                                   href="javascript:;">
                                                    <input type="file" accept="image/*" class="upload_file_btn">
                                                    <i class="icon36_common add_gray"></i>
                                                    <strong>上传图片</strong>
                                                </a>
                                            </span>
                                            </div>
                                        </div>

                                        <div id="msgSender_media_1_2" data-id='<s:if test="autoReplyBeadd.replyList[0].type == 2">${autoReplyBeadd.replyList[0].content}</s:if>'
                                             class="msgSender_media_classFixImg" style="display: <s:if test='autoReplyBeadd.replyList[0].type == 2'>block</s:if><s:else> none</s:else>;" >
                                            <div class="appmsgSendedItem simple_img">
                                                <a class="title_wrp" href="<s:if test='autoReplyBeadd.replyList[0].type == 2'>${autoReplyBeadd.replyList[0].content}</s:if>" target="_blank">
                                                    <img class="icon" src="<s:if test='autoReplyBeadd.replyList[0].type == 2'>${autoReplyBeadd.replyList[0].content}</s:if>">
                                                    <span class="title">[图片]</span>
                                                </a>
                                            </div>
                                            <a href="javascript:;" class="jsmsgSenderDelBt link_dele" data-type="2" onclick="return false;">删除</a>
                                        </div>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="tool_bar">
                        <span id="js_save" class="btn btn_primary btn_input"><button>保存</button></span>
                        <span id="js_del" class="btn btn_default btn_input"><button>删除回复</button></span>
                    </div>
                </div>

            </div>


        </div>
    </div>

</div>


<!-- 弹出层内容 -->

<div class="dialog_wrp img_dialog_wrp ui-draggable" style="width: 846px; margin-left: -423px; margin-top: -289.5px; display:none;">
    <div class="dialog">
        <div class="dialog_hd">
            <h3>选择图片</h3>
            <!--#0001#-->
            <a href="javascript:;" onclick="return false" class="icon16_opr closed pop_closed">关闭</a>
            <!--%0001%-->
        </div>
        <div class="dialog_bd">
            <div class="img_pick_panel inner_container_box side_l cell_layout">
                <div class="inner_side">
                    <div class="group_list">
                        <div class="inner_menu_box">
                            <dl class="inner_menu js_group">
                                <dd id="js_group1" class="inner_menu_item js_groupitem selected" data-groupid="1">
                                    <a href="javascript:;" class="inner_menu_link" title="未分组" onclick="return false">
                                        <%--<strong>未分组</strong><em class="num">(<span>1</span>)</em>--%>
                                    </a>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="inner_main">
                    <div class="img_pick_area">
                        <div class="sub_title_bar in_dialog">
                            <div class="upload_box r align_right">
                                <span class="upload_area webuploader-container">
                                    <a id="js_imageupload879262300208211" class="btn btn_primary js_imageupload webuploader-pick" data-groupid="">本地上传
                                        <input type="file" accept="image/*" class="upload_file_btn"></a>
                                </span>
                            </div>
                            <div class="img_water_tips mini_tips icon_after r weak_text">
                                大小不超过5M<span class="js_water">，已开启图片水印</span>
                                <i class="js_water_tips icon_msg_mini ask"></i>
                            </div>
                        </div>
                        <div>
                            <div class="img_pick">
                                <i class="icon_loading_small white js_loading" style="display: none;"></i>
                                <ul class="group js_list img_list">


                                </ul>
                            </div>
                            <div class="js_pagebar">
                                <div id="wxPagebar_000" class="pagination">
                                    <span class="page_nav_area">
                                        <a style="display: none;" href="javascript:void(0);" class="btn page_first"></a>
                                        <a style="display: inline-block;" href="javascript:void(0);" class="btn page_prev"><i class="arrow"></i></a>

                                            <span class="page_num">
                                                <label id="wxPagebar_currPage">1</label>
                                                <span class="num_gap">/</span>
                                                <label id="wxPagebar_totalPage">2</label>
                                            </span>

                                        <a href="javascript:void(0);" class="btn page_next"><i class="arrow"></i></a>
                                        <a style="display: none;" href="javascript:void(0);" class="btn page_last"></a>
                                    </span>

                                    <span class="goto_area">
                                        <input id="wxPagebar_pageGo" type="text">
                                        <a href="javascript:void(0);" class="btn page_go">跳转</a>
                                    </span>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <p class="dialog_ft_desc"><%--已选<span class="js_selected">0</span>个，--%>可选1个</p>
            </div>
        </div>

        <div class="dialog_ft">
            <span class="btn btn_disabled btn_input btn_primary js_btn_p"><button type="button" class="js_btn" data-index="0">确定</button></span>
            <span class="btn btn_default btn_input js_btn_p"><button type="button" class="js_btn" data-index="1">取消</button></span>
        </div>
    </div>
</div>

<div class="mask ui-draggable" style="display:none;">
    <iframe frameborder="0"
            style="filter:progid:DXImageTransform.Microsoft.Alpha(opacity:0);position:absolute;top:0px;left:0px;width:100%;height:100%;"
            src="about:blank"></iframe>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-1.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/style/js/autoReply/common.js"></script>

<script type="text/javascript">
    $(function () {

        //tab切换事件
        var tabs = function (nav, content) {
            $(nav).find("li").bind("click", function () {
                var index = $(this).index();
                $(this).addClass("selected").siblings().removeClass("selected");
                $(content).eq(index).show().siblings(content).hide();
                $(".js_emotionArea").hide();
            });
        }

        tabs(".js_tab_navs", ".tab_content");



        //点击图片选中确认
        $(".dialog_ft .btn_primary").click(function(){
            var _li = $(".frm_checkbox_label.img_item_bd.selected").parent("li.js_imageitem");
            var id = $(_li).attr("data-id");
            var url = $(_li).attr("data-url");

            //添加图片 显示
            $("#msgSender_media_1_2 a").attr("href",url);
            $("#msgSender_media_1_2 a img").attr("src",url);
            $("#msgSender_media_1_2").attr("data-id",id).show();

            //隐藏选图按钮
            $(".tab_cont_cover.jsMsgSendTab").hide();
        })

        //图片删除
        $(".jsmsgSenderDelBt").live("click",function(){
            $("#msgSender_media_1_2").hide();
            $(".tab_cont_cover.jsMsgSendTab").show();
        })



        $("#js_del").click(function(){
            location.href = "${pageContext.request.contextPath}/autoReplyAction_commonDel.action"
        })

        $("#js_save").click(function(){

            var type = $(".js_tab_navs li.selected").attr("data-type");

            var reply;
            if("1" == type){
                reply = $("#edit_area_content").text();
                if(reply == ''){
                    alert("必须输入回复语");return;
                }
            }
            if("2" == type){
                /*可见代表有效*/
                if($("#msgSender_media_1_2").is(":hidden")){
                    alert("请选择一个图片");return;
                }

                reply = $("#msgSender_media_1_2").attr("data-id");
            }            

            //替换转义字符
            reply = reply.replace(/"/g,"\\\""); 
            
            reply = '[{"content":"'+reply+'","type":"'+type+'"}]'
            location.href = "${pageContext.request.contextPath}/autoReplyAction_commonSave.action?reply="+reply;
        });

    });
</script>

</body>
</html>