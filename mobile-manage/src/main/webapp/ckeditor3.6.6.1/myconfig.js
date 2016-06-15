/*
Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
    config.language = 'zh-cn';

  // 设置宽高
   

  // 编辑器样式，有三种：'kama'（默认）、'office2003'、'v2'
      config.skin = 'office2003';

  // 背景颜色
      config.uiColor = '#FFF';

      config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
   

  // 在 CKEditor 中集成 CKFinder，注意 ckfinder的路径选择要正确。
  //CKFinder.SetupCKEditor(null, '/ckfinder/');
};
