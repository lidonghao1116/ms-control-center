var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.certificateQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['证书编号','学员姓名','手机号','培训学校','报名课程','鉴定等级','发证日期','操作'],
			colModel:[
			    {name:'certificateNo',index:'certificateNo',width:100,sortable:false},
				{name:'userName',index:'userName',width:80,sortable:false},
				{name:'mobile',index:'mobile', width:150,sortable:false},
				{name:'schoolName',index:'schoolName', width:150,sortable:false},
				{name:'courseName',index:'courseName', width:150,sortable:false},
				{name:'authenticateGrade',index:'authenticateGrade', width:150,sortable:false},
				{name:'issuingDate',index:'', width:150,sortable:false},
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
			caption: "成绩列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(schoolId,classId,userId){
		window.location.href=config.certificateInfoUrl+"?schoolId="+schoolId+"&classId="+classId+"&userId="+userId;
		//window.location.href=config.certificateInfoUrl+"?schoolId="+schoolId+"&classId="+classId+"&userId="+userId;
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
			userName:encodeURIComponent($("#userName").val()),
			mobile:encodeURIComponent($("#mobile").val()),
			courseId:GetSelectValue("#courseId"),
			schoolId:GetSelectValue("#schoolId"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.certificateQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+rawObject.schoolId+","+rawObject.classId+","+rawObject.userId+')"><span class="ui-icon ui-icon-pencil"></span></div>';
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