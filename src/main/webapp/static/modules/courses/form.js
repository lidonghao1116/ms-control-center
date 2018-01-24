var obj={
	add:function(){
		
		var templateInfo = {
			tf:GetSelectValue("#tf"),
			sc:GetSelectValue("#sc"),
			mc:GetSelectValue("#mc"),
			subjectCountTF:$("#subjectCountTF").val(),
			subjectCountSC:$("#subjectCountSC").val(),
			subjectCountMC:$("#subjectCountMC").val()
		}
		
		var params={
			templateInfo:templateInfo,
			courseName:$("#courseName").val(),
			status:GetSelectValue("#status"),
			fitService:$("#fitService").val(),
			price:$("#price").val(),
			sortNo:$("#sortNo").val(),
			summary:$("#summary").val(),
			reSign:$("#reSign").is(":checked")?"1":"0",
			courseType:GetSelectValue("#courseType"),
			courseTime:$("#courseTime").val()
			
		};
		
		//提交问题
		layer.confirm('确认添加此课程吗？',function(){
	        $.ajax({
	          url :config.coursesAddUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.coursesListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });
		});
		
	
	}
	,
	modify:function(){
		
		var templateInfo = {
			tf:GetSelectValue("#tf"),
			sc:GetSelectValue("#sc"),
			mc:GetSelectValue("#mc"),
			subjectCountTF:$("#subjectCountTF").val(),
			subjectCountSC:$("#subjectCountSC").val(),
			subjectCountMC:$("#subjectCountMC").val()
			}
		var params={
			courseId:$("#courseId").val(),	
			courseName:$("#courseName").val(),
			status:GetSelectValue("#status"),
			fitService:$("#fitService").val(),
			price:$("#price").val(),
			sortNo:$("#sortNo").val(),
			summary:$("#summary").val(),
			reSign:$("#reSign").is(":checked")?"1":"0",
			courseType:GetSelectValue("#courseType"),
			courseTime:$("#courseTime").val(),
			templateInfo:templateInfo
			
		};
		
		//提交问题
		layer.confirm('确认修改课程吗？',function(){
	        $.ajax({
	          url :config.coursesModifyUrl,
	          type : "POST",
	          contentType:"application/json",
	          data: JSON.stringify(params),
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.coursesListUrl;
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