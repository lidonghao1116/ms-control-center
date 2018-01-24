var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.learnQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['课程ID','姓名','手机号码','课程名称','答题次数','最高成绩'],
			colModel:[
			    {name:'courseId',index:'courseId', width:80,sortable:false},
				{name:'userExtend.userName',index:'userExtend.userName', width:150,sortable:false},
				{name:'userInfo.mobile',index:'userInfo.mobile', width:150,sortable:false},
				{name:'',index:'', width:150,sortable:false,formatter:courseNameFormatter },
				{name:'answersNum',index:'answersNum', width:150,sortable:false},
				{name:'scores',index:'scores', width:150,sortable:false}
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
			caption: "学习记录列表",
			autowidth: true
		});
	}
	,
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/learnRecords/export/00");
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
	}
	,
	refreshTable:function(){
		var params={
			userName:encodeURIComponent($("#userName").val()),
			courseId:GetSelectValue("#courseId")
		};
		$(grid_selector).setGridParam({url:config.learnQueryUrl,postData:params}).trigger('reloadGrid');
	}
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