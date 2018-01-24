var grid_selector = "#grid-table";
var pager_selector = "#grid-pager";
var obj={
	query:function(){
		$(grid_selector).jqGrid({
			url: config.redPacketQueryUrl,
			datatype: "json",
			height:'auto',
			colNames:['订单编号','发放时间','邀请人','红包编号','金额','红包状态','清算（时间）'],
			colModel:[
			    {name:'',index:'', width:80,sortable:false, formatter:orderFormatter},
				{name:'sendTime',index:'sendTime', width:70,sortable:false,formatter:formatDate},
				{name:'userExtendInfo.userName',index:'userExtendInfo.userName', width:50,sortable:false},
				{name:'redpackWxNo',index:'redpackWxNo', width:130,sortable:false},
				{name:'amount',index:'amount', width:40,sortable:false},
				{name:'failReason',index:'failReason', width:80,sortable:false},
				{name:'',index:'', width:130, fixed:true, sortable:false, resize:false,
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
			caption: "红包发放记录",
			autowidth: true
		});
	}
	,
	modifyObj:function(id){
	//alert(id);
	//提交问题
	layer.confirm('确认对此红包进行清算吗？',function(){
        $.ajax({
          url :config.redPacketModifyUrl,
          type : "POST",
          data: {id:id},
          success: function(data){
            if(data.success){
            	layer.alert(data.msg, {icon:1});
            	window.location.href=config.redPacketListUrl;
            }else{
              	layer.msg(data.msg);
            }
          }
        });
	});
	
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
			inviterName:encodeURIComponent($("#inviterName").val()),
			inviterOpenid:encodeURIComponent($("#inviterOpenid").val()),
			status:GetSelectValue("#status"),
			orderNo:encodeURIComponent($("#orderNo").val()),
			startDate:$("#startDate").val(),
			endDate:$("#endDate").val()
		};
		$(grid_selector).setGridParam({url:config.redPacketQueryUrl,postData:params}).trigger('reloadGrid');
	}
};
function orderFormatter(cellvalue, options, rawObject) {
    var html = "<a href='"+config.ordersInfoUrl+"?orderNo="+rawObject.orderNo+"'>"+rawObject.orderNo+"</a>"
    return html;
}
function actFormatter(cellvalue, options, rawObject) {
	var div='<div style="margin-left:8px;">';

    var model = "";

    if(rawObject.status == 01){
	    var model ='<div style="float:left;cursor:pointer;color:red" class="ui-pg-div ui-inline-edit"'
		    		+'onclick="obj.modifyObj('+"'"+ rawObject.id+"'"+')">强制清算</span></div>';
	}else{
	    var model ='<div style="float:left;cursor:pointer;" class="ui-pg-div ui-inline-edit">'+rawObject.modifyTime+'</span></div>';
	}
	var viewlast =  '</div>';
	return div+model+viewlast;
};

jQuery(function($) {	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width());
    });
	//初始化面包屑
	initBreadcrumb();
	obj.init();
});