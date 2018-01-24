var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.ordersQueryUrl+"?type=SUCCESS_APPLY",
			datatype: "json",
			height:'auto',
			colNames:['受理时间','姓名','产品','课程','来源','操作'],
			colModel:[
				{name:'handleTime',index:'handleTime',width:80,sortable:false,formatter:formatDate},
				{name:'userExtendInfo.userName',index:'userExtendInfo.userName', width:150,sortable:false},
				{name:'productName',index:'productName', width:150,sortable:false},
				{name:'courseName',index:'courseName', width:150,sortable:false},
				{name:'userExtendInfo.sourceValueName',index:'sourceValueName', width:150,sortable:false},
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
			caption: "已报名列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.ordersFormUrl+"?type=SUCCESS_APPLY&id="+id;
	}
	,
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/orders/export/01");
		$("#queryForm").submit();
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
			userName:encodeURIComponent($("#userName").val()),
			sourceType:GetSelectValue("#sourceType"),
			sourceTypeSec:GetSelectValue("#sourceTypeSec"),
			packageId:GetSelectValue("#packageId"),
			courseId:GetSelectValue("#courseId"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.ordersQueryUrl+"?type=SUCCESS_APPLY",postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});