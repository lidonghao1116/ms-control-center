<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-课程管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页面</a></li>
		<li class="active">课程管理</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/schoolCourse/list">课程列表</a></li>
		<li class="active"><a href="#">新增课程</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程名称<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4">
								<input type="text" placeholder="请输入课程名称" id="courseName" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证书名称<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4">
								<input type="text" placeholder="请输入证书名称" id="certName" maxlength="40" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">鉴定等级<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4" >
								<select class="chosen-select form-control" id="authenticateGrade" style="width: 63%;display: initial;" >
									<option value="">--请选择--</option>
								</select>							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总课时<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4">
		            			<input type="text" placeholder="请输入总课时" id="totalHour"  maxlength="3" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">考试形式<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4" >
								<select class="chosen-select form-control" id="examType" style="width: 63%;display: initial;" >
									<option value="">--请选择--</option>
								</select>							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">发证机构<span class="dr-asterisk">*</span>：</label>
							<div class="col-sm-4" >
								<select class="chosen-select form-control" id="authorityId" style="width: 63%;display: initial;" >
									<option value="">--请选择--</option>
								</select>							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注:</label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="90" class="form-control"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程状态<span class="dr-asterisk">*</span>：</label>
         					<div class="radio radio-info radio-inline" id="status">
		                        <input type="radio" id="inlineRadio1" value="01" name="radioInline" checked>
		                        <label for="inlineRadio1">上架 </label>
		                    </div>
		                    <div class="radio radio-inline">
		                        <input type="radio" id="inlineRadio2" value="02" name="radioInline">
		                        <label for="inlineRadio2">下架</label>
		                    </div>
					   	</div>
					  </div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>取消</button>
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>确认</button>
					</div>
			</form>
		</div>
	</div>
<script src="${ctxStatic}/modules/schoolCourse/course.js"></script>
<script type="text/javascript">
	
	var defaultOption = '<option value="">---请选择---</option>';
	/*鉴定等级  */
	function initAuthenticateGrade(){
 		$.post(ctx+'/params/cfgParamsOfGrade', {}, function(data){
 			createAuthenticateGradeData(data);
 		});
	};
	/*考试形式信息  */
	function initExamType(){
 		$.post(ctx+'/params/cfgParamOfExamType', {}, function(data){
	  		createExamTypeData(data);
 		});
	};
	/* 机构名称信息 */
	function initAuthorityName(){
 		$.post(ctx+'/params/authorityName', {}, function(data){
	  		createAuthorityNameData(data);
 		});
	};
	

	/*鉴定等级信息  */
	function createAuthenticateGradeData(data){
		var selectGist = $("#authenticateGrade");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	/*考试形式信息 */
	function createExamTypeData(data){
		var selectGist = $("#examType");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	/* 发证机构信息 */
	function createAuthorityNameData(data){
		var selectGist = $("#authorityId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.authorityId+'">'+obj.authorityName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	
	$(function(){
		initAuthenticateGrade();
		initExamType();
		initAuthorityName();
		
	});
	
	</script>
	
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
	//输入check
	$(function(){
		 function onError (msg,self) {
				if(self.nextAll().hasClass('prompt_sp')){
					self.nextAll('.prompt_sp').text(msg);
					return;
				}
				self.parent().append("<span class='prompt_sp' style='color:red;margin-left:10px'>"+msg+"</span>");
			};

		function onSuccess(self) {
			self.nextAll('.prompt_sp').remove();	
		}; 
	
		var name = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;//文字、数字、字母
		var number = /^[0-9]+$/;//数字
		 $("input[type='text'],textarea").blur(function(){
				var self = $(this);
				var id = $(this).attr("id");
				switch (id){
				case "courseName":
					if (self.val() == "") {
						onError('*请填写课程名称',self);
					} else if(!name.test(self.val())){
						onError('*请输入格式正确的课程名称',self);
					}else{
						onSuccess(self);
					}
					break;
				case "certName":
					if (self.val() == "") {
						onError('*请填写证书名称',self);
					} else if(!name.test(self.val())){
						onError('*请输入格式正确的证书名称',self);
					}else{
						onSuccess(self);
					}
					break;
				case "totalHour":
					if (self.val() == "") {
						onError('*请填写总课时',self);
					} else if(!number.test(self.val())){
						onError('*请输入格式正确的总课时',self);
					}else{
						onSuccess(self);
					}
					break;
				}
			});
		})
	</script>
	
</body>
</html>