var obj={
	add:function(){
		
		var answers = {
			isAnswer1:$("#isAnswer1").is(":checked")?"1":"0",	
			isAnswer2:$("#isAnswer2").is(":checked")?"1":"0",	
			isAnswer3:$("#isAnswer3").is(":checked")?"1":"0",	
			isAnswer4:$("#isAnswer4").is(":checked")?"1":"0",	
			isAnswer5:$("#isAnswer5").is(":checked")?"1":"0",	
			answerDesc1:$("#answerDesc1").val(),
			answerDesc2:$("#answerDesc2").val(),
			answerDesc3:$("#answerDesc3").val(),
			answerDesc4:$("#answerDesc4").val(),
			answerDesc5:$("#answerDesc5").val()
		}
		
		var params={
			answers:answers,
			question:$("#question").val(),
			questionType:GetSelectValue("#questionType"),
			status:GetSelectValue("#status"),
			courseId:GetSelectValue("#courseId")
			
		};
		
		//提交问题
		layer.confirm('确认添加此题目吗？',function(){
			//alert(params);
	        $.ajax({
	          url :config.questionAddUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.questionListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	
	}
	,
	modify:function(){
		
		var answers = {
				
			answerId1:$("#answerId1").val(),
			isAnswer1:$("#isAnswer1").is(":checked")?"1":"0",
			answerDesc1:$("#answerDesc1").val(),
			
			answerId2:$("#answerId2").val(),
			isAnswer2:$("#isAnswer2").is(":checked")?"1":"0",
			answerDesc2:$("#answerDesc2").val(),
			
			answerId3:$("#answerId3").val(),
			isAnswer3:$("#isAnswer3").is(":checked")?"1":"0",
			answerDesc3:$("#answerDesc3").val(),
			
			answerId4:$("#answerId4").val(),
			isAnswer4:$("#isAnswer4").is(":checked")?"1":"0",
			answerDesc4:$("#answerDesc4").val(),
			
			answerId5:$("#answerId5").val(),
			isAnswer5:$("#isAnswer5").is(":checked")?"1":"0",
			answerDesc5:$("#answerDesc5").val()
			}
		var params={
			answers:answers,
			id:$("#questionId").val(),
			status:GetSelectValue("#status"),
			question:$("#question").val(),
			questionType:$("#questionType").val()
		};
		
		//提交问题
		layer.confirm('确认修改此题目吗？',function(){
	        $.ajax({
	          url :config.questionModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.questionListUrl;
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