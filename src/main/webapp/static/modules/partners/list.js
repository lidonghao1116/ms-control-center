var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.partnersQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['合作商名称','合作商归属地','联系人','联系电话','操作'],
			colModel:[
				{name:'partnerName',index:'partnerName',width:80,sortable:false},
				{name:'',index:'', width:150,sortable:false,formatter:areasFormatter},
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
			caption: "合作商列表",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
		window.location.href=config.partnersFormUrl+"?id="+id;
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
			partnerName:encodeURIComponent($("#partnerName").val()),
			partnerType:GetSelectValue("#partnerType"),
			contactPhone:$("#contactPhone").val(),
			province:GetSelectValue("#province"),
			city:GetSelectValue("#city"),
			county:GetSelectValue("#county")
		};
		$(grid_selector).setGridParam({url:config.partnersQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var edit ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+ rawObject.id+')"><span class="ui-icon ui-icon-pencil"></span></div>';
	return div+ edit;
};

function areasFormatter(cellvalue, options, rawObject){
	return rawObject.proviceName+"-"+ rawObject.cityName+"-"+ rawObject.countyName;
}

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});