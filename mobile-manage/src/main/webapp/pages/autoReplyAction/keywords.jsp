<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <title>微信公众平台</title>
    <%@ include file="/pages/public/common.jspf" %>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/css/base2968da.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/advanced_reply_common27d7ed.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/css/advanced_reply_keywords21d851.css">
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
                                <li class="tab_nav ">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_beadd.action">被添加自动回复</a>
                                </li>
                                <li class="tab_nav ">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_common.action">消息自动回复</a>
                                </li>
                                <li class="tab_nav no_extra selected">
                                    <a href="${pageContext.request.contextPath}/autoReplyAction_keywords.action">关键词自动回复</a>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <div class="btn_wrp">
                        <a href="javascript:void(0);" class="btn btn_add btn_primary" id="Js_rule_add"
                           data-status="not"><i class="icon14_common add_white"></i>添加规则</a>
                    </div>
                    <ul id="Js_ruleList" class="keywords_rule_list">

                        <li class="keywords_rule_item open" id="Js_ruleItem_0">

                            <div class="keywords_rule_hd no_extra dropdown_area Js_detail_switch dropdown_opened" data-id="0" data-reply="loaded" style="-webkit-transition: all, 1s;">
                                <div class="info">
                                    <em class="keywords_rule_num">新规则</em>
                                    <strong class="keywords_rule_name"></strong>
                                </div>
                                <div class="opr">
                                    &nbsp;
                                    <a href="javascript:void(0);" class="icon_dropdown_switch"><i class="arrow arrow_up"></i><i class="arrow arrow_down"></i></a>
                                </div>
                            </div>


                            <div class="keywords_rule_bd keywords_rule_overview">
                                <div class="keywords_info keywords">
                                    <strong class="keywords_info_title">关键词</strong>
                                    <div class="keywords_info_detail">
                                        <ul class="overview_keywords_list" id="Js_keywordsOverview_0">

                                        </ul>
                                    </div>
                                </div>
                                <div class="keywords_info reply">
                                    <strong class="keywords_info_title">回复</strong>
                                    <div class="keywords_info_detail">
                                        <p class="reply_info">
                                            <em class="num">0</em>条（
                                            <em data-type="1" class="num Js_reply_cnt2">0</em>条文字，
                                            <em data-type="2" class="num Js_reply_cnt2">0</em>条图片）

                                        </p>
                                    </div>
                                </div>
                                <div id="Js_replyAllOverview_0" class="dn">发送全部回复</div>
                            </div>


                            <div class="keywords_rule_bd keywords_rule_detail">

                                <div class="rule_name_area">
                                    <div class="frm_control_group">
                                        <label for="" class="frm_label">规则名</label>
                                        <div class="frm_controls">
                                            <span class="frm_input_box"><input class="frm_input" id="Js_ruleName_0" value='' type="text"></span>
                                            <p class="frm_tips">规则名最多60个字</p>
                                        </div>
                                    </div>
                                </div>


                                <div class="keywords_tap keywords no_data">
                                    <div class="keywords_tap_hd">
                                        <div class="info">
                                            <h4>关键字</h4>
                                        </div>
                                        <div class="opr">
                                            <a href="javascript:;" data-id="0" class="Js_keyword_add">添加关键字</a>
                                        </div>
                                    </div>
                                    <div class="keywords_tap_bd">
                                        <ul style="display: block;" class="keywords_list" id="Js_keywordList_0">


                                        </ul>
                                    </div>
                                </div>


                                <div class="keywords_tap reply no_data">
                                    <div class="keywords_tap_hd">
                                        <div class="info">
                                            <h4>回复</h4>
                                        </div>
                                        <div class="opr">
                                            <label for="Js_replyAll_0" class="frm_checkbox_label">
                                                <i class="icon_checkbox"></i>
                                                <input id="Js_replyAll_0" class="frm_checkbox Js_reply_all" type="checkbox">回复全部
                                            </label>
                                        </div>
                                    </div>
                                    <div class="keywords_tap_bd">

                                        <ul class="media_type_list">
                                            <li class="tab_text" data-tooltip="文字"><a href="javascript:;" data-type="1" data-id="0" class="Js_reply_add">&nbsp;<i class="icon_msg_sender"></i></a></li>
                                            <li class="tab_img" data-tooltip="图片"><a href="javascript:;" data-type="2" data-id="0" class="Js_reply_add">&nbsp;<i class="icon_msg_sender"></i></a></li>

                                        </ul>
                                        <ul style="display: block;" class="keywords_list" id="Js_replyList_0">

                                        </ul>
                                    </div>
                                </div>
                            </div>


                            <div class="keywords_rule_ft">

                                <p class="media_stat info">
                                    文字(<em data-type="1" class="num Js_reply_cnt">0</em>)、
                                    图片(<em data-type="2" class="num Js_reply_cnt">0</em>)

                                </p>

                                <div class="opr">
                                    <a href="javascript:;" data-id="0" class="btn btn_primary Js_rule_save">保存</a>
                                    <a href="javascript:;" data-id="0" class="btn btn_default Js_rule_del">删除</a>
                                </div>
                            </div>
                        </li>


                        <s:iterator id="autoReply" value="autoReplyList" status="st">

                            <li class="keywords_rule_item" id="Js_ruleItem_${autoReply.id}" style="display:block;">
                                <div class="keywords_rule_hd no_extra dropdown_area Js_detail_switch dropdown_closed"
                                     data-id="${autoReply.id}" data-reply="loaded" style="-webkit-transition: all, 1s;">
                                    <div class="info">
                                        <em class="keywords_rule_num">规则<s:property value="autoReplyList.size()-#st.index"/>:</em>
                                        <strong class="keywords_rule_name">${autoReply.name}</strong>
                                    </div>
                                    <div class="opr">
                                        &nbsp;
                                        <a href="javascript:void(0);" class="icon_dropdown_switch"><i
                                                class="arrow arrow_up"></i><i class="arrow arrow_down"></i></a>
                                    </div>
                                </div>
                                <div class="keywords_rule_bd keywords_rule_overview">
                                    <div class="keywords_info keywords">
                                        <strong class="keywords_info_title">关键词</strong>

                                        <div class="keywords_info_detail">
                                            <ul class="overview_keywords_list" id="Js_keywordsOverview_${autoReply.id}">
                                                <s:iterator value="#autoReply.keywordsList">
                                                    <li><em class="keywords_name">${keyword}</em></li>
                                                </s:iterator>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="keywords_info reply">
                                        <strong class="keywords_info_title">回复</strong>
                                        <div class="keywords_info_detail">
                                            <p class="reply_info">
                                                <em class="num">${autoReply.textNum + autoReply.picNum}</em>
                                                条（
                                                <em data-type="1" class="num Js_reply_cnt2">${autoReply.textNum}</em>
                                                条文字，
                                                <em data-type="2" class="num Js_reply_cnt2">${autoReply.picNum}</em>
                                                条图片）

                                            </p>
                                        </div>
                                    </div>
                                    <div id="Js_replyAllOverview_${autoReply.id}" class="dn">发送全部回复</div>
                                </div>


                                <div class="keywords_rule_bd keywords_rule_detail">

                                    <div class="rule_name_area">
                                        <div class="frm_control_group">
                                            <label for="" class="frm_label">规则名</label>
                                            <div class="frm_controls">
                                                <span class="frm_input_box">
                                                    <input type="text" class="frm_input" id="Js_ruleName_${autoReply.id}" value='${autoReply.name}'>
                                                </span>
                                                <p class="frm_tips">规则名最多60个字</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="keywords_tap keywords no_data">
                                        <div class="keywords_tap_hd">
                                            <div class="info">
                                                <h4>关键字</h4>
                                            </div>
                                            <div class="opr">
                                                <a href="javascript:;" data-id="${autoReply.id}" class="Js_keyword_add">添加关键字</a>
                                            </div>
                                        </div>
                                        <div class="keywords_tap_bd">
                                            <ul class="keywords_list" id="Js_keywordList_${autoReply.id}" style="display: block;">
                                                <s:iterator value="#autoReply.keywordsList" id="keywords">
                                                    <li data-index="0" data-id="${autoReply.id}">
                                                        <div class="desc">
                                                            <strong class="title Js_keyword_val" data-content='${keyword}'>${keyword}</strong>
                                                        </div>
                                                        <div class="opr">
                                                            <a href="javascript:;" class="keywords_mode_switch Js_keyword_mode" data-mode="${mode}">
                                                                <s:if test="#keywords.mode eq 0 ">
                                                                    完全相同
                                                                </s:if>
                                                                <s:if test="#keywords.mode eq 1 ">
                                                                    包含
                                                                </s:if>
                                                            </a>
                                                            <a href="javascript:;" class="icon14_common edit_gray Js_keyword_edit">编辑</a>
                                                            <a href="javascript:;" data-id="${autoReply.id}" class="icon14_common del_gray Js_keyword_del">删除</a>
                                                        </div>
                                                    </li>
                                                </s:iterator>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="keywords_tap reply no_data">
                                        <div class="keywords_tap_hd">
                                            <div class="info">
                                                <h4>回复</h4>
                                            </div>
                                            <div class="opr">
                                                <label for="Js_replyAll_${autoReply.id}" class="frm_checkbox_label <s:if test='#autoReply.replyType == "1"'>selected</s:if>">
                                                    <i class="icon_checkbox"></i>
                                                    <input id="Js_replyAll_${autoReply.id}" type="checkbox" class="frm_checkbox Js_reply_all">回复全部
                                                </label>
                                            </div>
                                        </div>
                                        <div class="keywords_tap_bd">
                                            <ul class="media_type_list">
                                                <li class="tab_text" data-tooltip="文字">
                                                    <a href="javascript:;" data-type="1" data-id="${autoReply.id}" class="Js_reply_add"> &nbsp;<i class="icon_msg_sender"></i></a>
                                                </li>
                                                <li class="tab_img" data-tooltip="图片">
                                                    <a href="javascript:;" data-type="2"  data-id="${autoReply.id}" class="Js_reply_add">&nbsp;<i class="icon_msg_sender"></i></a>
                                                </li>
                                            </ul>
                                            <ul class="keywords_list" id="Js_replyList_${autoReply.id}" style="display: block;">

                                                <s:iterator value="#autoReply.replyList">

                                                    <s:if test="type == 1">
                                                        <li data-index="0" data-id="${autoReply.id}" data-type="${type}" data-appid="" data-fileid="" data-content='${content}' style="z-index: 0">
                                                            <div class="desc">
                                                                <div class="media_content Js_media_content" id="Js_mediaContent_${autoReply.id}_${type}" data-index="0" data-type="${type}">${content}</div>
                                                            </div>
                                                            <div class="opr">
                                                                <a href="javascript:;" class="icon14_common edit_gray  Js_reply_edit">编辑</a>
                                                                <a href="javascript:;" data-id="${autoReply.id}" class="icon14_common del_gray Js_reply_del">删除</a>
                                                            </div>
                                                        </li>
                                                    </s:if>
                                                    <s:if test="type == 2">
                                                        <li data-index="1" data-id="${autoReply.id}" data-type="${type}" data-appid="" data-fileid="" data-content='${content}'>
                                                            <div class="desc">
                                                                <div class="media_content Js_media_content" id="Js_mediaContent_${autoReply.id}_${type}" data-index="1" data-type="${type}"><div class="appmsgSendedItem simple_img">
                                                                    <a class="title_wrp" href="${content}" target="_blank">
                                                                        <img class="icon" src="${content}">
                                                                        <span class="title">[图片]</span>
                                                                    </a>
                                                                </div></div>
                                                            </div>
                                                            <div class="opr">

                                                                <a href="javascript:;" data-id="" class="icon14_common del_gray Js_reply_del">删除</a>
                                                            </div>
                                                        </li>
                                                    </s:if>

                                                </s:iterator>
                                            </ul>
                                        </div>
                                    </div>
                                </div>


                                <div class="keywords_rule_ft">
                                    <p class="media_stat info">
                                        文字(
                                        <em data-type="1" class="num Js_reply_cnt">${autoReply.textNum}</em>
                                        )、 图片(
                                        <em data-type="2" class="num Js_reply_cnt">${autoReply.picNum}</em>
                                        )
                                    </p>
                                    <div class="opr">
                                        <a href="javascript:;" data-id="${autoReply.id}" class="btn btn_primary Js_rule_save">保存</a>
                                        <a href="javascript:;" data-id="${autoReply.id}" class="btn btn_default Js_rule_del">删除</a>
                                    </div>
                                </div>
                            </li>
                        </s:iterator>
                        <p class="empty_tips">暂无创建规则</p>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 弹出层内容 -->

<div class="dialog_wrp keywords_edit ui-draggable"  style="width: 960px; margin-left: -480px; margin-top: -292.5px; display:none;" id="txt_draggable">
    <div class="dialog">
        <div class="dialog_hd">
            <h3>添加关键字</h3>
            <!--#0001#-->
            <a href="javascript:;" onclick="return false" class="icon16_opr closed pop_closed">关闭</a>
            <!--%0001%-->
        </div>
        <div class="dialog_bd">
            <div class="emotion_editor_wrp" id="Js_textEditor">
                <div class="emotion_editor">
                    <div class="edit_area js_editorArea" style="display: none;"></div>
                    <div class="edit_area js_editorArea" contenteditable="true"
                         style="overflow-y: auto; overflow-x: hidden;"></div>
                    <div class="editor_toolbar">

                        <a href="javascript:void(0);" class="icon_emotion emotion_switch js_switch">表情</a>


                        <p class="editor_tip opr_tips">，按下Shift+Enter键换行</p>

                        <p class="editor_tip js_editorTip">还可以输入<em>30</em>字</p>

                        <div class="emotion_wrp js_emotionArea">
                            <span class="hook">
                                <span class="hook_dec hook_top"></span>
                                <span class="hook_dec hook_btm"></span>
                            </span>
                            <ul class="emotions" onselectstart="return false;">

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/0.gif"
                                       data-title="微笑" style="background-position:0px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/1.gif"
                                       data-title="撇嘴" style="background-position:-24px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/2.gif"
                                       data-title="色" style="background-position:-48px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/3.gif"
                                       data-title="发呆" style="background-position:-72px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/4.gif"
                                       data-title="得意" style="background-position:-96px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/5.gif"
                                       data-title="流泪" style="background-position:-120px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/6.gif"
                                       data-title="害羞" style="background-position:-144px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/7.gif"
                                       data-title="闭嘴" style="background-position:-168px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/8.gif"
                                       data-title="睡" style="background-position:-192px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/9.gif"
                                       data-title="大哭" style="background-position:-216px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/10.gif"
                                       data-title="尴尬" style="background-position:-240px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/11.gif"
                                       data-title="发怒" style="background-position:-264px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/12.gif"
                                       data-title="调皮" style="background-position:-288px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/13.gif"
                                       data-title="呲牙" style="background-position:-312px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/14.gif"
                                       data-title="惊讶" style="background-position:-336px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/15.gif"
                                       data-title="难过" style="background-position:-360px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/16.gif"
                                       data-title="酷" style="background-position:-384px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/17.gif"
                                       data-title="冷汗" style="background-position:-408px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/18.gif"
                                       data-title="抓狂" style="background-position:-432px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/19.gif"
                                       data-title="吐" style="background-position:-456px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/20.gif"
                                       data-title="偷笑" style="background-position:-480px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/21.gif"
                                       data-title="可爱" style="background-position:-504px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/22.gif"
                                       data-title="白眼" style="background-position:-528px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/23.gif"
                                       data-title="傲慢" style="background-position:-552px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/24.gif"
                                       data-title="饥饿" style="background-position:-576px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/25.gif"
                                       data-title="困" style="background-position:-600px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/26.gif"
                                       data-title="惊恐" style="background-position:-624px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/27.gif"
                                       data-title="流汗" style="background-position:-648px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/28.gif"
                                       data-title="憨笑" style="background-position:-672px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/29.gif"
                                       data-title="大兵" style="background-position:-696px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/30.gif"
                                       data-title="奋斗" style="background-position:-720px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/31.gif"
                                       data-title="咒骂" style="background-position:-744px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/32.gif"
                                       data-title="疑问" style="background-position:-768px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/33.gif"
                                       data-title="嘘" style="background-position:-792px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/34.gif"
                                       data-title="晕" style="background-position:-816px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/35.gif"
                                       data-title="折磨" style="background-position:-840px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/36.gif"
                                       data-title="衰" style="background-position:-864px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/37.gif"
                                       data-title="骷髅" style="background-position:-888px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/38.gif"
                                       data-title="敲打" style="background-position:-912px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/39.gif"
                                       data-title="再见" style="background-position:-936px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/40.gif"
                                       data-title="擦汗" style="background-position:-960px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/41.gif"
                                       data-title="抠鼻" style="background-position:-984px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/42.gif"
                                       data-title="鼓掌" style="background-position:-1008px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/43.gif"
                                       data-title="糗大了" style="background-position:-1032px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/44.gif"
                                       data-title="坏笑" style="background-position:-1056px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/45.gif"
                                       data-title="左哼哼" style="background-position:-1080px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/46.gif"
                                       data-title="右哼哼" style="background-position:-1104px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/47.gif"
                                       data-title="哈欠" style="background-position:-1128px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/48.gif"
                                       data-title="鄙视" style="background-position:-1152px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/49.gif"
                                       data-title="委屈" style="background-position:-1176px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/50.gif"
                                       data-title="快哭了" style="background-position:-1200px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/51.gif"
                                       data-title="阴险" style="background-position:-1224px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/52.gif"
                                       data-title="亲亲" style="background-position:-1248px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/53.gif"
                                       data-title="吓" style="background-position:-1272px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/54.gif"
                                       data-title="可怜" style="background-position:-1296px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/55.gif"
                                       data-title="菜刀" style="background-position:-1320px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/56.gif"
                                       data-title="西瓜" style="background-position:-1344px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/57.gif"
                                       data-title="啤酒" style="background-position:-1368px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/58.gif"
                                       data-title="篮球" style="background-position:-1392px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/59.gif"
                                       data-title="乒乓" style="background-position:-1416px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/60.gif"
                                       data-title="咖啡" style="background-position:-1440px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/61.gif"
                                       data-title="饭" style="background-position:-1464px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/62.gif"
                                       data-title="猪头" style="background-position:-1488px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/63.gif"
                                       data-title="玫瑰" style="background-position:-1512px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/64.gif"
                                       data-title="凋谢" style="background-position:-1536px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/65.gif"
                                       data-title="示爱" style="background-position:-1560px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/66.gif"
                                       data-title="爱心" style="background-position:-1584px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/67.gif"
                                       data-title="心碎" style="background-position:-1608px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/68.gif"
                                       data-title="蛋糕" style="background-position:-1632px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/69.gif"
                                       data-title="闪电" style="background-position:-1656px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/70.gif"
                                       data-title="炸弹" style="background-position:-1680px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/71.gif"
                                       data-title="刀" style="background-position:-1704px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/72.gif"
                                       data-title="足球" style="background-position:-1728px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/73.gif"
                                       data-title="瓢虫" style="background-position:-1752px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/74.gif"
                                       data-title="便便" style="background-position:-1776px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/75.gif"
                                       data-title="月亮" style="background-position:-1800px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/76.gif"
                                       data-title="太阳" style="background-position:-1824px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/77.gif"
                                       data-title="礼物" style="background-position:-1848px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/78.gif"
                                       data-title="拥抱" style="background-position:-1872px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/79.gif"
                                       data-title="强" style="background-position:-1896px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/80.gif"
                                       data-title="弱" style="background-position:-1920px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/81.gif"
                                       data-title="握手" style="background-position:-1944px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/82.gif"
                                       data-title="胜利" style="background-position:-1968px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/83.gif"
                                       data-title="抱拳" style="background-position:-1992px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/84.gif"
                                       data-title="勾引" style="background-position:-2016px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/85.gif"
                                       data-title="拳头" style="background-position:-2040px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/86.gif"
                                       data-title="差劲" style="background-position:-2064px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/87.gif"
                                       data-title="爱你" style="background-position:-2088px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/88.gif"
                                       data-title="NO" style="background-position:-2112px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/89.gif"
                                       data-title="OK" style="background-position:-2136px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/90.gif"
                                       data-title="爱情" style="background-position:-2160px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/91.gif"
                                       data-title="飞吻" style="background-position:-2184px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/92.gif"
                                       data-title="跳跳" style="background-position:-2208px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/93.gif"
                                       data-title="发抖" style="background-position:-2232px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/94.gif"
                                       data-title="怄火" style="background-position:-2256px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/95.gif"
                                       data-title="转圈" style="background-position:-2280px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/96.gif"
                                       data-title="磕头" style="background-position:-2304px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/97.gif"
                                       data-title="回头" style="background-position:-2328px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/98.gif"
                                       data-title="跳绳" style="background-position:-2352px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/99.gif"
                                       data-title="挥手" style="background-position:-2376px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/100.gif"
                                       data-title="激动" style="background-position:-2400px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/101.gif"
                                       data-title="街舞" style="background-position:-2424px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/102.gif"
                                       data-title="献吻" style="background-position:-2448px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/103.gif"
                                       data-title="左太极" style="background-position:-2472px 0;">
                                    </i>
                                </li>

                                <li class="emotions_item">
                                    <i class="js_emotion_i"
                                       data-gifurl="https://res.wx.qq.com/mpres/htmledition/images/icon/emotion/104.gif"
                                       data-title="右太极" style="background-position:-2496px 0;">
                                    </i>
                                </li>

                            </ul>
                            <span class="emotions_preview js_emotionPreviewArea"><img src="" alt=""></span>
                        </div>
                    </div>
                </div>

                <div class="tool_area"><p class="tips">输入回车可添加多个关键字，每个关键字少于30个字符</p></div>
                <ul class="overview_keywords_list" id="Js_editorKeywordList"></ul>
            </div>
        </div>

        <div class="dialog_ft">

            <span class="btn btn_primary btn_input js_btn_p"><button type="button" class="js_btn" data-index="0">确定
            </button></span>

            <span class="btn btn_default btn_input js_btn_p"><button type="button" class="js_btn" data-index="1">取消
            </button></span>

        </div>

    </div>
</div>

<div class="dialog_wrp img_dialog_wrp ui-draggable" style="width: 846px; margin-left: -423px; margin-top: -289.5px; display:none;" id="img_draggable">
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
                                        <strong>未分组</strong><em class="num">(<span>1</span>)</em>
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
                                <span class="upload_area webuploader-container"><a id="js_imageupload879262300208211"
                                                                                   class="btn btn_primary js_imageupload webuploader-pick"
                                                                                   data-groupid="">本地上传<input
                                        type="file" accept="image/*" class="upload_file_btn"></a></span>
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
                <p class="dialog_ft_desc">已选<span class="js_selected">0</span>个，可选1个</p>
            </div>
        </div>

        <div class="dialog_ft">

            <span class="btn btn_disabled btn_input btn_primary js_btn_p"><button type="button" class="js_btn"
                                                                                  data-index="0">确定
            </button></span>

            <span class="btn btn_default btn_input js_btn_p"><button type="button" class="js_btn" data-index="1">取消
            </button></span>

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

        $("#Js_ruleItem_0").hide();
        //点击添加规则
        $("#Js_rule_add").click(function(){
            $("#Js_ruleItem_0").toggle();
        });
        //点击规则
        $(".keywords_rule_hd").click(function(){
            $(this).parent("li").toggleClass("open");
            $(this).toggleClass("dropdown_opened").toggleClass("dropdown_closed");
        })

        var _id;
        var _type;
        //点击添加关键字,添加文字回复
        $(".Js_keyword_add, .media_type_list a.Js_reply_add").click(function(){

            var dataType = $(this).attr("data-type");
            $(".dialog_wrp").hide();
            if(typeof (dataType) == 'undefined' || dataType == 1 ){
                $("#txt_draggable").show();
                $(".js_editorArea").text("");
            }
            if(dataType == 2){
                $("#img_draggable").show();
            }

            _id = $(this).attr("data-id");
            _type =  $(this).attr("class");
        })

        var _li;
        //关键字编辑 文字回复编辑
        $(".Js_keyword_edit, .Js_reply_edit").live("click",function(){
            $(".dialog_wrp").hide();
            $("#txt_draggable").show();
            _id = $(this).attr("data-id");
            _type =  $(this).attr("class");

            _li = $(this).parents("li[data-id]");
            var t = $(this).parent("div.opr").prev().children().text();
            $(".js_editorArea[contenteditable='true']").text(t);
        })


        //关闭弹窗
        $(".pop_closed, .js_btn").click(function(){
            $(".ui-draggable").hide();
        });

        //文字弹窗保存
        $("#txt_draggable .btn_primary .js_btn").click(function(){
            var keyword = $(".js_editorArea[contenteditable='true']").text();
            var id = _id;
            var type = _type;

            if(keyword == ''){
                alert("不可为空");
                return;
            }

            if(type == "Js_keyword_add"){
                $("#Js_keywordList_"+id).append( '<li data-index="1" data-id="'+id+'">'+
                        '<div class="desc">' +
                        "<strong class=\"title Js_keyword_val\" data-content='"+keyword+"'>"+keyword+"</strong>" +
                        '</div>'+
                        '<div class="opr">'+
                        '<a href="javascript:;" class="keywords_mode_switch Js_keyword_mode" data-mode="0">完全相同</a>'+
                        '<a href="javascript:;" class="icon14_common edit_gray Js_keyword_edit">编辑</a>'+
                        '<a href="javascript:;" class="icon14_common del_gray Js_keyword_del" data-id="'+id+'">删除</a>'+
                        '</div></li>');
            };
            if(type == "Js_reply_add"){
                $("#Js_replyList_"+id).append('<li data-index="0" data-id="'+id+'" data-type="1" data-appid="" data-fileid="" data-content=\''+keyword+'\' style="z-index: 0">'+
                        '<div class="desc">' +
                        '<div class="media_content Js_media_content" id="Js_mediaContent_'+id+'_0" data-index="0" data-type="1">'+keyword+'</div>' +
                        '</div>'+
                        '<div class="opr">'+
                        '<a href="javascript:;" class="icon14_common edit_gray  Js_reply_edit">编辑</a>'+
                        '<a href="javascript:;" data-id="'+id+'" class="icon14_common del_gray Js_reply_del">删除</a>'+
                        '</div></li>');
            };
            if(type == "icon14_common edit_gray Js_keyword_edit"){
                $(_li).children("div.desc").children(".Js_keyword_val").attr("data-content",keyword).text(keyword);
            };
            if(type == "icon14_common edit_gray  Js_reply_edit"){
                $(_li).attr("data-content",keyword).children("div.desc").children("div").text(keyword);
            };
        });

        //图片弹窗保存
        $("#img_draggable .btn_primary .js_btn").click(function(){

            var _li = $(".frm_checkbox_label.img_item_bd.selected").parent("li.js_imageitem");
            var data_id = $(_li).attr("data-id");
            var url = $(_li).attr("data-url");
            var id = _id;

            $("#Js_replyList_"+id).append('<li data-index="1" data-id="0" data-type="2" data-appid="" data-fileid="" data-content="'+data_id+'">'+
                '<div class="desc">'+
                '<div class="media_content Js_media_content" id="Js_mediaContent_'+data_id+'_2" data-index="1" data-type="2"><div class="appmsgSendedItem simple_img">'+
                '<a class="title_wrp" href="'+url+'" target="_blank">'+
                '<img class="icon" src="'+url+'">'+
                '<span class="title">[图片]</span></a></div></div></div>'+
                '<div class="opr"><a href="javascript:;" data-id="" class="icon14_common del_gray Js_reply_del">删除</a></div>'+
                '</li>'
            );

        });

        //?全匹配
        $("a.Js_keyword_mode").live("click",function(){
            var t = $.trim($(this).text());
               if(t =="包含"){
                   $(this).text('完全相同');
                   $(this).attr('data-mode',"0");
               }
               if(t =='完全相同'){
                   $(this).text('包含');
                   $(this).attr('data-mode',"1");
               }
        });
        //删除
        $(".del_gray").live("click",function(){
            if(confirm("确认删除?"))
                $(this).parents("li[data-id]").remove();
        });

        //全部回复
        $(":input.frm_checkbox").live("click",function(){
            $(this).parent("label.frm_checkbox_label").toggleClass("selected");
        });

        //保存规则
        $(".Js_rule_save").live("click",function(){
            var id = $(this).attr("data-id");
            //获取输入内容
            var name = $("#Js_ruleName_"+id).val();
                        
            if(typeof(name) == 'undefined' || $.trim(name)==""){
                alert("请输入规则名称!");
                return;
            }

            var keywords = "["
            var first = true;
            $("#Js_keywordList_"+id).children("li").each(function(){
                if(!first){
                    keywords +=",";
                }
                var keyword = $(this).children(".desc").children(".Js_keyword_val").text();
                var mode = $(this).children(".opr").children(".Js_keyword_mode").attr("data-mode");
                
              	//替换转义字符
                var keyword2 = keyword.replace(/"/g,"\\\"");                 
                
                keywords += '{"keyword":"'+keyword2+'","mode":"'+mode+'"}'
                first =false;

            })
            keywords += "]";

            if(keywords.length <10 ){
                alert("请输入关键字!");
                return;
            }

            var all = $("#Js_replyAll_"+id).parent("label").hasClass("selected");
            var replyType = true == all ? 1 : 0;

            var replys = "["
            first = true;
            $("#Js_replyList_"+id).children("li").each(function(){
                if(!first){
                    replys +=",";
                }
                var reply = $(this).attr("data-content");
                var type = $(this).attr("data-type");
                
              	//替换转义字符
                var reply2 = reply.replace(/"/g,"\\\""); 
                
                replys += '{"content":"'+reply2+'","type":"'+type+'"}'
                first =false;
            })
            replys += "]";

            if(replys.length <10){
                alert("请输入回复内容!");
                return;
            }

            //数据保存
            location.href = "${pageContext.request.contextPath}/autoReplyAction_keywordsSave.action?"+
                    "id="+id+"&name="+name + "&keywords="+keywords+"&replyType="+replyType+"&reply="+replys;
        });

        //删除规则
        $(".Js_rule_del").live("click",function(){        	

            var id = $(this).attr("data-id");

            if(!confirm("确认删除?")){
                return;
            }
            //数据删除
            location.href = "${pageContext.request.contextPath}/autoReplyAction_keywordsDel.action?id="+id;
        });

        //表情调用
        $(".js_switch").click(function(){
            $(".js_emotionArea").show();
        });
        $(".emotions .emotions_item").each(function(index) {
            $(this).click(function(){
                $(".js_emotionArea").hide();
            });

            $(this).find("i").hover(function(){
                var emotionPath = $(this).attr("data-gifurl");
                $(".js_emotionPreviewArea img").attr("src", emotionPath);
            }, function(){
                $(".js_emotionPreviewArea img").attr("src", "");
            });
        });
    });
</script>

</body>
</html>