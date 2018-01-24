var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.schoolsQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['学校名称','学校地址','固定电话','联系人','联系手机','操作'],
			colModel:[
				{name:'schoolName',index:'schoolName',width:80,sortable:false},
				{name:'',index:'', width:250,sortable:false,formatter:areasFormatter},
				{name:'schoolPhone',index:'schoolPhone', width:150,sortable:false},
				{name:'contacts',index:'contacts', width:150,sortable:false},
				{name:'contactPhone',index:'contactPhone', width:150,sortable:false},
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
			caption: "学校列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		//alert(id);
		window.location.href=config.schoolsFormUrl+"?id="+id;
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
			schoolName:encodeURIComponent($("#schoolName").val()),
			privince:GetSelectValue("#province"),
			city:GetSelectValue("#city"),
			area:GetSelectValue("#county")
		};
		$(grid_selector).setGridParam({url:config.schoolsQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};
function areasFormatter(cellvalue, options, rawObject){
	if(rawObject.privinceName!=null&&rawObject.cityName!=null&&rawObject.areaName!=null&&rawObject.schoolAddress!=null){
		return rawObject.privinceName+"-"+ rawObject.cityName+"-"+ rawObject.areaName+"-"+rawObject.schoolAddress;
	}else if(rawObject.privinceName!=null&&rawObject.cityName!=null&&rawObject.areaName!=null){
		return rawObject.privinceName+"-"+ rawObject.cityName+"-"+ rawObject.areaName;
	}else if(rawObject.privinceName!=null&&rawObject.cityName!=null){
		return rawObject.privinceName+"-"+ rawObject.cityName;
	}else if(rawObject.privinceName!=null){
		return rawObject.privinceName;
	}else if(rawObject.schoolAddress!=null){
		return rawObject.schoolAddress;
	}else {
		return null;
	}
}
jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});