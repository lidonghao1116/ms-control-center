var obj={
	add:function(){
		 $("div>input[type='text']").trigger("blur");
		 if($(".prompt_sp").length>0){
			 return;
		 }
		var loginAccount = $(".form-group input[name='loginAccount']").val();
		 $.ajax({
		        url: config.schoolsCheckUrl,
		        type: "post",
		        data: {
		        	loginAccount:loginAccount
		        },
		        dataType: "json",
		        async:false,
		        success: function success(data) {
		            if(data.success){
				        	$("#check_m").submit();
		                }else if(data.code=='600001'){
		                	alert("该管理员账号已存在");
			            	return false;
		                }
		            
		            
		        }
		    });  
		
	}
	,
	modify:function(){
		//提交问题
		layer.confirm('确认修改学校信息吗？',function(){
			//修改提交
			$("#checks_s").submit();
	        /*$.ajax({
	          url :config.schoolsModifyUrl,
	          type : "POST",
	          data: params,
	          success: function(data){
	            if(data.success){
	            	layer.alert(data.msg, {icon:1});
	            	window.location.href=config.schoolsListUrl;
	            }else{
	              	layer.msg(data.msg);
	            }
	          }
	        });*/
		});
		
	},
	resetPwd:function(){
    	var params={
            id:$("#id").val()
        };
        $.ajax({
          url :config.schoolsResetPwdUrl,
          type : "POST",
          data: params,
          success: function(data){
            if(data.success){
                $("#resetPwdTips").html("重置密码成功，初始密码：000000");
            }else{
                layer.msg(data.msg);
            }
          }
        });

    },
	init:function(){
		//初始化绑定事件
		$("#addBtn").bind("click",function(){
			obj.add();
		});
		
		$("#modifyBtn").bind("click",function(){
			//alert(1122);
			obj.modify();
		});

		$("#btnResetPwd").bind("click",function(){
            //alert(1122);
            obj.resetPwd();
        });
	}
}


$(function(){
	obj.init();
});