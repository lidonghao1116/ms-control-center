var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.authorityQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['创建时间','机构名称','备注','状态','操作'],
			colModel:[
				{name:'addTime',index:'addTime',width:80,sortable:false,formatter:formatDate},
				{name:'authorityName',index:'authorityName', width:150,sortable:false},
				{name:'remark',index:'remark', width:150,sortable:false},
				{name:'statusName',index:'statusName', width:100,sortable:false},
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
			caption: "发证机构列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(authorityId){
		window.location.href=config.authorityFormUrl+"?authorityId="+authorityId;
	}
	,
	init:function(){
		obj.query();
		
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
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
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val(),
			status:GetSelectValue("#status")
		};
		$(grid_selector).setGridParam({url:config.authorityQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	 
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.authorityId+')"><span class="ui-icon ui-icon-pencil"></span> </div>';
	return div+ edit;
};


jQuery(function($) {	
	jQuery(window).on('resize.jqGrid', function () {
		jQuery(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
		
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
	
});