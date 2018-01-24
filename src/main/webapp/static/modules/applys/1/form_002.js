var obj={
	modify:function(optType){
		var params={
			id:$("#id").val(),
			classNumber:GetSelectValue("#classNumber"),
			isHasPf:GetSelectValue("#isHasPf"),
			bookFree:$("#bookFree").val(),
			isStaging:$("input[name='isStaging']:checked").val(),
			firstPay:$("#firstPay").val(),
			isClear:$("#isClear").is(":checked")?"1":"0",
			makeupFee:$("#makeupFee").val(),
			optType:optType,
			type:"REP_APPLY"
		};
		
		//提交问题
		layer.confirm('确认修改订单信息吗？',function(){
	        $.ajax({
	          url :config.ordersModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.ordersListUrl+"?type=REP_APPLY";
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	init:function(){
		$("#passBtn").bind("click",function(){
			obj.modify("opt03");
		});
		$("#unpassBtn").bind("click",function(){
			obj.modify("opt04");
		});
	}
}

$(function(){
	obj.init();
});