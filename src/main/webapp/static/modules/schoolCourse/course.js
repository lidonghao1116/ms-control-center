var obj={
	add:function(){
		
		
		var params={
			courseName:$("#courseName").val(),
			certName:$("#certName").val(),
			authenticateGrade:GetSelectValue("#authenticateGrade"),
			totalHours:$("#totalHour").val(),
			examType:GetSelectValue("#examType"),
			authorityId:GetSelectValue("#authorityId"),
			remark:$("#remarks").val(),
			status:$("input[name='radioInline']:checked").val()
		};
		
		//提交问题
		layer.confirm('确认添加此课程吗？',function(){
	        $.ajax({
	          url :config.schoolCourseAddCourseUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.msg(data.msg, {icon:1});
	            	window.location.href=config.schoolCourseList;
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
			courseId:$("#courseId").val(),	
			totalHours:$("#totalHour").val(),
			examType:GetSelectValue("#examType"),
			remark:$("#remarks").val(),
			status:$("input[name='radioInline']:checked").val()
		};
		
		//提交问题
		layer.confirm('确认修改课程吗？',function(){
	        $.ajax({
	          url :config.schoolCourseUpdateCourseUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.schoolCourseList;
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

