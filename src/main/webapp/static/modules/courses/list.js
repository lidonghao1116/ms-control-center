var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.coursesQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['课程ID','创建时间','课程名称','课程形式','价格','推荐标志','状态','操作'],
			colModel:[
			    {name:'courseId',index:'courseId',width:80,sortable:false},
				{name:'creatTime',index:'creatTime',width:100,sortable:false,formatter:formatDate},
				{name:'courseName',index:'courseName', width:150,sortable:false},
				{name:'courseTypeName',index:'courseTypeName', width:100,sortable:false},
				{name:'price',index:'price', width:100,sortable:false},
				{name:'reSignName',index:'reSignName', width:100,sortable:false},
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
	modifyObj:function(courseId){
		window.location.href=config.coursesFormUrl+"?courseId="+courseId;
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
			courseName:$("#courseName").val(),
			status:GetSelectValue("#status")
		};
		$(grid_selector).setGridParam({url:config.coursesQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.courseId+')"><span class="ui-icon ui-icon-pencil"></span></div>';
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