var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.questionQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['题目ID','所属课程','题目类型','题目名称','状态','操作'],
			colModel:[
			    {name:'id',index:'id', width:80,sortable:false},
				{name:'',index:'', width:150,sortable:false, formatter:courseNameFormatter },
				{name:'questionTypeName',index:'questionTypeName', width:150,sortable:false},
				{name:'question',index:'question', width:150,sortable:false},
				{name:'statusName',index:'statusName', width:100,sortable:false},
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
			caption: "课程列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.questionFormUrl+"?id="+id;
	}
	,
	init:function(){
		obj.query();
		//初始化绑定事件
		$("#queryBtn").bind("click",function(){
			obj.refreshTable();
		});
	}
	,
	refreshTable:function(){
		var params={
			question:encodeURIComponent($("#question").val()),
			status:GetSelectValue("#status"),
			questionType:GetSelectValue("#questionType"),
			courseId:GetSelectTextByKey("#courseId",41)
		};
		$(grid_selector).setGridParam({url:config.questionQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};
function courseNameFormatter(cellvalue, options, rawObject) {
	return GetSelectTextByKey("#courseId",rawObject.courseId);
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});