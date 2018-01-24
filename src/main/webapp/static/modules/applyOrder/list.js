var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.applyOrderQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['订单ID','学员姓名','手机号','学校名称','报名课程','鉴定等级','报名时间','操作'],
			colModel:[
			    {name:'id',index:'id',width:80,sortable:false},
				{name:'userExtendInfo.userName',index:'userExtendInfo.userName',width:80,sortable:false},
				{name:'userBaseInfo.mobile',index:'userBaseInfo.mobile', width:150,sortable:false},
				{name:'schools.schoolName',index:'schools.schoolName', width:150,sortable:false},
				{name:'learnTypes.typeName',index:'learnTypes.typeName', width:150,sortable:false},
				{name:'orgName',index:'orgName', width:150,sortable:false},
				{name:'orderTime',index:'orderTime', width:150,sortable:false,formatter:formatDate},
				{name:'',index:'', width:150, fixed:true, sortable:false, resize:false,
					formatter:actFormatter
				}
			], 
			rownumbers: false,
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
			caption: "培训订单列表",
			autowidth: true
		});
	}
	,
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/order/export/00");
		$("#queryForm").submit();
	}
	,
	modifyObj:function(id){
		window.location.href=config.applyOrderInfoUrl+"?id="+id;
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
		
		$("#exportBtn").bind("click",function(){
			obj.exportExcel();
		});
	}
	,
	refreshTable:function(){
		var params={
			userName:encodeURIComponent($("#userName").val()),
			mobile:encodeURIComponent($("#mobile").val()),
			
			courseId:GetSelectValue("#courseId"),
			schoolId:GetSelectValue("#schoolId"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.applyOrderQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var view ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon fa-eye"></span></div>';
	return div+ view;
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});