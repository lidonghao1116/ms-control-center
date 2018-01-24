var obj={
	add:function(){
		
		
		var userBaseInfo={
			mobile:$("#mobile").val(),	
		}
		var sourceValue='';
		if(GetSelectValue("#sourceType")=='01'){
			sourceValue=GetSelectValue("#sourceValue");
		}else if(GetSelectValue("#sourceType")=='02'){
			sourceValue=$("#sourceTypeValue").val();
		}
		var userExtendInfo={
			userName:$("#userName").val(),	
			certNo:$("#certNo").val(),
			education:GetSelectValue("#education"),
			nation:GetSelectValue("#nation"),
			birthplace:GetSelectValue("#birthplace"),
			address:$("#address").val(),
			contacts:$("#contacts").val(),
			contactPhone:$("#contactPhone").val(),
			sourceType:GetSelectValue("#sourceType"),
			sourceTypeSec:GetSelectValue("#sourceTypeSec"),
			sourceValue:sourceValue,
			sourceRemarks:$("#sourceRemarks").val()
		}
		
		var params={
			userBaseInfo:userBaseInfo,
			userExtendInfo:userExtendInfo,
			packageId:GetSelectValue("#packageId"),
			courseId:GetSelectValue("#courseId"),
			classNumber:GetSelectValue("#classNumber"),
			classTime:GetSelectValue("#classTime"),
			isExam:GetSelectValue("#isExam"),
			isHasPf:GetSelectValue("#isHasPf"),
			schoolFee:$("#schoolFee").val(),
			deposit:$("#deposit").val(),
			bookFree:$("#bookFree").val(),
			isStaging:$("input[name='isStaging']:checked").val(),
			firstPay:$("#firstPay").val()
		};
		
		layer.confirm('确认添加此订单信息吗？',function(){
	        $.ajax({
	          url :config.ordersAddUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.ordersListUrl+"?type=SUCCESS_APPLY";
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	modify:function(){
		var params={
			id:$("#id").val(),
			classNumber:GetSelectValue("#classNumber"),
			isHasPf:GetSelectValue("#isHasPf"),
			bookFree:$("#bookFree").val(),
			isStaging:$("input[name='isStaging']:checked").val(),
			firstPay:$("#firstPay").val(),
			isClear:$("#isClear").is(":checked")?"1":"0",
			type:"SUCCESS_APPLY"
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
	            	window.location.href=config.ordersListUrl+"?type=SUCCESS_APPLY";
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	}
	,
	init:function(){
		//初始化绑定事件
		$("#addBtn").bind("click",function(){
			obj.add();
		});
		
		$("#modifyBtn").bind("click",function(){
			obj.modify();
		});
	}
}

$(function(){
	obj.init();
});