var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.ordersQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['订单编号','购买时间','购买课程','用户昵称','用户手机号','支付方式','是否被邀请','操作'],
			colModel:[
			    {name:'orderNo',index:'orderNo',width:130,sortable:false},
				{name:'orderTime',index:'orderTime',width:100,sortable:false,formatter:formatDate},
				{name:'',index:'', width:80,sortable:false,formatter:courseNameFormatter },
				{name:'wechatNick',index:'wechatNick', width:80,sortable:false},
				{name:'mobile',index:'mobile', width:80,sortable:false},
				{name:'tradeChannelName',index:'tradeChannelName', width:80,sortable:false},
				{name:'isInvitedName',index:'isInvitedName', width:60,sortable:false},
				{name:'',index:'', width:60, fixed:true, sortable:false, resize:false,
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
			caption: "订单记录",
			autowidth: true
		});
	}
	,
	exportExcel:function(){
		$("#queryForm").attr("action",ctx+"/order/export/00");
		$("#queryForm").submit();
	}
	,
	modifyObj:function(orderNo){
		window.location.href=config.ordersInfoUrl+"?orderNo="+orderNo;
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
			wechatNick:encodeURIComponent($("#wechatNick").val()),
			mobile:encodeURIComponent($("#mobile").val()),
			ordersNo:encodeURIComponent($("#ordersNo").val()),
			courseId:GetSelectValue("#courseId"),
			isInvited:GetSelectValue("#isInvited"),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.ordersQueryUrl,postData:params}).trigger('reloadGrid');
	}
};

function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';
	var view ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit"'
				+'onclick="obj.modifyObj('+"'"+ rawObject.orderNo+"'"+')"><span class="ui-icon fa-eye"></span></div>';
	return div+ view;
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