$(function(){
	grid =$('#newProduct').datagrid({
		id : 'getPager',
		name : 'getPager',
		idField : 'categoryId',
		
		url:'${pageContext.request.contextPath }/newProductBrandReAction_datagridList.action',
		singleSelect :true,
		frozenColumns:[[
						{field:'select',checkbox:true}
					]],	
		singleSelect :true,
		frozenColumns:[[
			{field:'select',checkbox:true,editor:'checkbox'},
			{field:'categoryId',title:'分类',width:80,align:'center',formatter : function(value) {
			if(value=='A01B02'){
				return '女士服装';
			}else if(value=='A01B03'){
				return '女士鞋靴';
			}else if(value=='A01B01'){
				return '女士箱包';
			}else if(value=='A01B04'){
				return '女士配饰';
			}else if(value=='A02B02'){
				return '男士服装';
			}else if(value=='A02B03'){
				return '男士鞋靴';
			}else if(value=='A02B01'){
				return '男士箱包';
			}else if(value=='A02B04'){
				return '男士配饰';
			}
	}},
		]],	
		columns:[[
			{field:'firstNewProductId',title:'新品推荐一',width:100,align:'center',editor:'text'},
			{field:'secondNewProductId',title:'新品推荐二',width:100,align:'center',editor:'text'},	
			{field:'firstBrandId',title:'品牌推荐一id',width:100,align:'center',editor:'text'},	
			{field:'firstBrandName',title:'品牌推荐一名称',width:100,align:'center',editor:'text'},	
			{field:'firstBrandProductId',title:'品牌推荐一商品',width:100,align:'center',editor:'text'},	
			{field:'secondBrandId',title:'品牌推荐二ID',width:100,align:'center',editor:'text'},	
			{field:'secondBrandName',title:'品牌推荐二名称',width:100,align:'center',editor:'text'},	
			
			{field:'threeBrandId',title:'品牌推荐三ID',width:100,align:'center',editor:'text'},
			{field:'threeBrandName',title:'品牌推荐三名称',width:100,align:'center',editor:'text'},	
			{field:'fourthBrandId',title:'品牌推荐四ID',width:100,align:'center',editor:'text'},	
			{field:'fourthBrandName',title:'品牌推荐四名称',width:100,align:'center',editor:'text'},	
			{field:'fifthBrandId',title:'品牌推荐五ID',width:100,align:'center',editor:'text'},	
			{field:'fifthBrandName',title:'品牌推荐五名称',width:100,align:'center',editor:'text'},	
			{field:'sixthBrandId',title:'品牌推荐六ID',width:100,align:'center',editor:'text'},
			{field:'sixthBrandName',title:'品牌推荐六名称',width:100,align:'center',editor:'text'},	
			{field:'seventhBrandId',title:'品牌推荐七ID',width:100,align:'center',editor:'text'},	
			{field:'seventhBrandName',title:'品牌推荐七名称',width:100,align:'center',editor:'text'}	
			
		]],	
		title : '列表',
		width: '100%',
		height :340,
		
		
		pageList :
		[ 10, 15, 20, 25, 30, 40, 50 ],
		pageSize : 10,
	
		pagination:true,
	
		toolbar : [ {
			
			text : '添加',
			iconCls : 'icon-add',
			handler : add
			
		}, 
		{
			
			id:'btnsave',
			text : '修改',
			iconCls : 'icon-edit',
			handler : update
			}
	
	]

	
		
});

add_window = $('#add-window').window(
	{
		closed : true,modal:true,minimizable:false,maximizable:false,resizable:false
	});
		
form = add_window.find('form');

$('#firstNewProductId').focus(function(){
	if(this.value == '输入8位商品编号')
	{
		$(this).val('');
	}
});
$('#secondNewProductId').focus(function(){
	if(this.value == '输入8位商品编号')
	{
		$(this).val('');
	}
});
$('#firstNewProductId').blur(function(){
	if(!this.value)
	{
		this.value='输入8位商品编号';
		}
});
$('#secondNewProductId').blur(function(){
	if(!this.value)
	{
		this.value='输入8位商品编号';
		}
});

$('#firstBrandProductId').focus(function(){
	if(this.value == '输入8位商品编号')
	{
		$(this).val('');
	}
});
$('#firstBrandProductId').blur(function(){
	if(!this.value)
	{
		this.value='输入8位商品编号';
		}
});


$('#firstBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#firstBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});

$('#secondBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#secondBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});

$('#threeBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#threeBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});

$('#fourthBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#fourthBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});
$('#fifthBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#fifthBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});
$('#sixthBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#sixthBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});
$('#seventhBrandId').focus(function(){
	if(this.value == '输入5位品牌编号')
	{
		$(this).val('');
	}
});
$('#seventhBrandId').blur(function(){
	if(!this.value)
	{
		this.value='输入5位品牌编号';
		}
});



$('#firstBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#firstBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});

$('#secondBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#secondBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});

$('#threeBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#threeBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});

$('#fourthBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#fourthBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});
$('#fifthBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#fifthBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});
$('#sixthBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#sixthBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});
$('#seventhBrandName').focus(function(){
	if(this.value == '输入品牌名称')
	{
		$(this).val('');
	}
});
$('#seventhBrandName').blur(function(){
	if(!this.value)
	{
		this.value='输入品牌名称';
		}
});




});

var add_window;
//查询
function reloadgrid(){
	//执行查询操作
	var pager = $('#newProduct').datagrid('getPager');//得到DataGrid页面
		pager.pagination({
			pageNumber:1
		});

	$('#newProduct').datagrid('options').queryParams = {categoryId:$("#categoryIdQuery").val()};
	$("#newProduct").datagrid('reload');
	$("#newProduct").datagrid('resize'); 
}


function add(){
	$("#desc").text('');
	document.getElementById('categoryId').value='-1';
	document.getElementById('firstNewProductId').value='输入8位商品编号';
	document.getElementById('secondNewProductId').value='输入8位商品编号';
	document.getElementById('firstBrandId').value='输入5位品牌编号';
	document.getElementById('firstBrandName').value='输入品牌名称';
	
	document.getElementById('firstBrandProductId').value='输入8位商品编号';
	document.getElementById('secondBrandId').value='输入5位品牌编号';
	document.getElementById('secondBrandName').value='输入品牌名称';
	document.getElementById('threeBrandId').value='输入5位品牌编号';
	document.getElementById('threeBrandName').value='输入品牌名称';
	document.getElementById('fourthBrandId').value='输入5位品牌编号';
	
	document.getElementById('fourthBrandName').value='输入品牌名称';
	document.getElementById('fifthBrandId').value='输入5位品牌编号';
	document.getElementById('fifthBrandName').value='输入品牌名称';
	document.getElementById('sixthBrandId').value='输入5位品牌编号';
	document.getElementById('sixthBrandName').value='输入品牌名称';
	
	document.getElementById('seventhBrandId').value='输入5位品牌编号';
	document.getElementById('seventhBrandName').value='输入品牌名称';
	
	add_window.window('open');
	$("#uporsave").show();
	form.url = 'newProductBrandReAction_editAll.action';
		
}
function saveEntity() {

	var categoryId = $('#categoryId').val();
	var firstNewProductId = $('#firstNewProductId').val();
	var secondNewProductId = $('#secondNewProductId').val();
	
	var firstBrandId = $('#firstBrandId').val();
	var firstBrandName = $('#firstBrandName').val();
	var firstBrandProductId = $('#firstBrandProductId').val();
	
	var secondBrandId = $('#secondBrandId').val();
	var secondBrandName = $('#secondBrandName').val();
	var threeBrandId = $('#threeBrandId').val();
	
	var threeBrandName = $('#threeBrandName').val();
	var fourthBrandId = $('#fourthBrandId').val();
	var fourthBrandName = $('#fourthBrandName').val();
	var fifthBrandId = $('#fifthBrandId').val();
	var fifthBrandName = $('#fifthBrandName').val();
	var sixthBrandId = $('#sixthBrandId').val();
	var sixthBrandName = $('#sixthBrandName').val();
	var seventhBrandId = $('#seventhBrandId').val();
	var seventhBrandName = $('#seventhBrandName').val();
	if (Trim(categoryId) == ''||Trim(categoryId)=='-1') {
		alert('请选择品类！');
	
		return;
	}
	if (Trim(firstNewProductId)==''||Trim(firstNewProductId)=='输入8位商品编号') {
		alert('新品推荐位置一不能为空！');
		$('#firstNewProductId').focus();
		return;
	}
	if (Trim(secondNewProductId) == ''||Trim(secondNewProductId)=='输入8位商品编号') {
		alert('新品推荐位置二不能为空！');
		$('#secondNewProductId').focus();
		return;
	}
	if (Trim(firstBrandId)=='' ||Trim(firstBrandId)=='输入5位品牌编号') {
		alert('品牌ID不能为空！');
		$('#firstBrandId').focus();
		return;
	}
	if (Trim(firstBrandName) == ''||Trim(firstBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#firstBrandName').focus();
		return;
	}
	if (Trim(firstBrandProductId) == ''||Trim(firstBrandProductId)=='输入8位商品编号') {
		alert('推荐商品编号不能为空！');
		$('#firstBrandProductId').focus();
		return;
	}
	if (Trim(secondBrandId)=='' ||Trim(secondBrandId)=='输入5位品牌编号') {
		alert('品牌ID不能为空！');
		$('#secondBrandId').focus();
		return;
	}
	if (Trim(secondBrandName) == ''||Trim(secondBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#secondBrandName').focus();
		return;
	}
	if (Trim(threeBrandId)=='' ||Trim(threeBrandId)=='输入5位品牌编号') {
		alert('品牌ID不能为空！');
		$('#threeBrandId').focus();
		return;
	}
	if (Trim(threeBrandName) == ''||Trim(threeBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#threeBrandName').focus();
		return;
	}
	if (Trim(fourthBrandId)==''||Trim(fourthBrandId)=='输入5位品牌编号' ) {
		alert('品牌ID不能为空！');
		$('#fourthBrandId').focus();
		return;
	}
	if (Trim(fourthBrandName) == ''||Trim(fourthBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#fourthBrandName').focus();
		return;
	}
	if (Trim(fifthBrandId)=='' ||Trim(fifthBrandId)=='输入5位品牌编号' ) {
		alert('品牌ID不能为空！');
		$('#fifthBrandId').focus();
		return;
	}
	if (Trim(fifthBrandName) == ''||Trim(fifthBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#fifthBrandName').focus();
		return;
	}
	if (Trim(sixthBrandId)=='' ||Trim(sixthBrandId)=='输入5位品牌编号') {
		alert('品牌ID不能为空！');
		$('#sixthBrandId').focus();
		return;
	}
	if (Trim(sixthBrandName) == ''||Trim(sixthBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#sixthBrandName').focus();
		return;
	}
	if (Trim(seventhBrandId)==''||Trim(seventhBrandId)=='输入5位品牌编号' ) {
		alert('品牌ID不能为空！');
		$('#seventhBrandId').focus();
		return;
	}
	if (Trim(seventhBrandName) == ''||Trim(seventhBrandName)=='输入品牌名称') {
		alert('品牌名称不能为空！');
		$('#seventhBrandName').focus();
		return;
	}
	if (Trim(firstNewProductId).length !=8) {
		alert('请输入正确的编号！');
		$('#firstNewProductId').focus();
		return;
	}
	
	if (Trim(secondNewProductId).length !=8) {
		alert('请输入正确的编号！');
		$('#secondNewProductId').focus();
		return;
	}
	if (Trim(firstBrandProductId).length !=8) {
		alert('请输入正确的编号！');
		$('#firstBrandProductId').focus();
		return;
	}
	
	
	if (Trim(firstBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#firstBrandId').focus();
		return;
	}
	if (Trim(secondBrandId ).length !=5) {
		alert('请输入5位品牌编号！');
		$('#secondBrandId').focus();
		return;
	}
	
	if (Trim(threeBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#threeBrandId').focus();
		return;
	}
	
	if (Trim(fourthBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#fourthBrandId').focus();
		return;
	}
	if (Trim(fifthBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#fifthBrandId').focus();
		return;
	}
	if (Trim(sixthBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#sixthBrandId').focus();
		return;
	}
	if (Trim(seventhBrandId).length !=5) {
		alert('请输入5位品牌编号！');
		$('#seventhBrandId').focus();
		return;
	}
		form.form('submit', {
			url : form.url,
			cache : false,
		
			  success : function callback() {
				add_window.window('close');
				$.messager.alert('操作提示', '操作成功!');
				document.getElementById('categoryIdQuery').value='-1';
				reloadgrid();
			}
		});
		

	}
function closeWindows()
{
	add_window.window('close');
}

function update() {
	var row = grid.datagrid('getSelected');
	if (row) {
		if(grid.datagrid('getSelections').length>1){
			$.messager.alert('warning', '只能选择一条记录!', 'warning');
			return;
		}
		add_window.window('open');
		form.form('clear');
		$.ajax({
			type : "post",
			url : 'newProductBrandReAction_info.action',
			data : {categoryId:row.categoryId,x:Math.random()},
			success : function callback(data) {
				var obj=eval('('+data+')');
				form.form('load',obj);
				if(row.categoryId=='A01B02'){
					$("#desc").html('正在编辑的是<b>女士服装</b>频道');
				}else if(row.categoryId=='A01B03'){
					$("#desc").html('正在编辑的是<b>女士鞋靴</b>频道');
				}else if(row.categoryId=='A01B01'){
					$("#desc").html('正在编辑的是<b>女士箱包</b>频道');
				}else if(row.categoryId=='A01B04'){
					$("#desc").html('正在编辑的是<b>女士配饰</b>频道');
				}if(row.categoryId=='A02B02'){
					$("#desc").html('正在编辑的是<b>男士服装</b>频道');
				}else if(row.categoryId=='A02B03'){
					$("#desc").html('正在编辑的是<b>男士鞋靴</b>频道');
				}else if(row.categoryId=='A02B01'){
					$("#desc").html('正在编辑的是<b>男士箱包</b>频道');
				}else if(row.categoryId=='A02B04'){
					$("#desc").html('正在编辑的是<b>男士配饰</b>频道');
				}
				
				$("#uporsave").hide();
			
				
				form.url = 'newProductBrandReAction_editAll.action';
				}
			});
			
	} else {
		$.messager.alert('警告', '请选择一条数据!', 'warning');
	}
	
}

