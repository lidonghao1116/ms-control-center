var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.schoolCourseQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['课程名称','证书名称','鉴定等级','发证机构','课程状态','操作'],
			colModel:[
			    {name:'courseName',index:'courseName', width:150,sortable:false},
				{name:'certName',index:'certName', width:150,sortable:false},
				{name:'orgName',index:'orgName', width:150,sortable:false},
				{name:'authorityName',index:'authorityName', width:150,sortable:false},
				{name:'statusName',index:'statusName', width:150,sortable:false},
				{name:'',index:'', width:150, fixed:true, sortable:false, resize:false,
					formatter:actFormatter
				}
			], 
			rownumbers: true,
			rownumWidth:80,
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
			caption: "课程管理列表",
			autowidth: true
		});
	}
	,
	
	modifyObj:function(courseId){
		window.location.href=config.schoolCourseGetCourseBaseUrl+"?courseId="+courseId;
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
			courseName:$("#courseId option:selected").text(),
			authenticateGrade:GetSelectValue("#authenticateGrade"),
			authorityId:GetSelectValue("#authorityId"),
			status:GetSelectValue("#status"),
		};
		$(grid_selector).setGridParam({url:config.schoolCourseQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var view ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+rawObject.courseId+')"><span class="glyphicon glyphicon-edit"></span></div>';
	return div+ view;
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
	$("#jqgh_grid-table_rn span.s-ico").before("<span>序号</span>");
});