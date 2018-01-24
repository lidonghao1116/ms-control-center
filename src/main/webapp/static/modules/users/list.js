var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.userQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['注册时间','邀请码','用户昵称','用户手机号','用户姓名','状态','是否被邀请','操作'],
			colModel:[
				{name:'registerTime',index:'registerTime',width:150,sortable:false,formatter:formatDate},
				{name:'inviteCode',index:'inviteCode', width:150,sortable:false},
				{name:'wechatNick',index:'wechatNick', width:150,sortable:false},
				{name:'mobile',index:'mobile', width:150,sortable:false},
				{name:'userExtend.userName',index:'userExtend.userName', width:150,sortable:false},
				{name:'authStatusName',index:'authStatusName', width:150,sortable:false},
				{name:'isInviteName',index:'isInviteName', width:150,sortable:false},
				{name:'',index:'', width:150, fixed:true, sortable:false, resize:false,
					formatter:actFormatter
				}
			], 
			rownumbers: true,
			hidegrid: false,
			viewrecords : true,
			rowNum:10,
			pager : pager_selector,
	        loadComplete : function() {
				var table = this;
				setTimeout(function(){
					updatePagerIcons(table);
				}, 0);
			},
			caption: "用户列表",
			autowidth: true
		});
	}
	,
	viewObj:function(id){
		window.location.href=config.userInfoUrl+"?id="+id;
	}
	,
	
	init:function(){
		obj.query();
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
		});
		
		$("#exportBtn").bind("click",function(){
			obj.exportExcel();
		});
		
		//初始化日期
		$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		});
	}
	,
	refreshTable:function(){
		var params={
			inviteCode:encodeURIComponent($("#inviteCode").val()),
			mobile:encodeURIComponent($("#mobile").val()),
			userName:encodeURIComponent($("#userName").val()),
			authStatus:GetSelectValue("#authStatus"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.userQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var view ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.viewObj('+ rawObject.id+')"><span class="ui-icon fa-eye"></span></div>';
	return div+view;
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});