var obj={
	add:function(){
		
		
		var params={
			authorityName:$("#authorityName").val(),
			status:GetSelectValue("#status"),
			remark:$("#remark").val()
			
		};
		
		//提交问题
		layer.confirm('确认添加此发证机构吗？',function(){
	        $.ajax({
	          url :config.authorityAddUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.authorityListUrl;
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
			authorityId:$("#authorityId").val(),
			status:GetSelectValue("#status")
			};
			
			//提交问题
			layer.confirm('确认修改此发证机构吗？',function(){
		        $.ajax({
		          url :config.authorityModifyUrl,
		          type : "POST",
		          data: params,
		          success: function(data){
		            if(data.success){
		            	layer.alert(data.msg, {icon:1});
		            	window.location.href=config.authorityListUrl;
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