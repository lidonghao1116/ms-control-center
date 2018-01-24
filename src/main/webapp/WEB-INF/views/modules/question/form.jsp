<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-题库管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">题库管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/question/list">题库列表</a></li>
		<li class="active"><a href="${ctx}/question/form?id=">${update?'修改':'新增'}题目</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">所属课程</label>
							<div class="col-sm-4 padding-7">
								<select class="chosen-select form-control" id="courseId" name="courseId"></select>
							</div>
							<label class="col-sm-2 control-label">是否上架<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">题目类型</label>
					           <div class="col-md-2">
					            	<select class="chosen-select form-control" id="questionType" >
										<option value="">--请选择--</option>
										<c:forEach items="${fns:getParams(26,0)}" var="obj" varStatus="item">
					            			<option value="${obj.value}">${obj.text}</option>
					            		</c:forEach>
									</select>
					            </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">题目名称</label>
							<div class="col-sm-4 padding-7">
								<input id="question" maxlength="1000" class="form-control" />
							</div>
						</div>
					</div>
					<div class="questionSet">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">题目选项</label>
					</div>
						<div class="form-group">
							<label class="col-sm-1 control-label"></label>
							<label class="col-sm-2 control-label" >序号</label>
							<label class="col-sm-2 control-label" >设置答案</label>
							<label class="col-sm-2 control-label" >选项</label>
						</div>
						<div class="form-group answer_list">
						<label class="col-sm-1 control-label"></label>
							<label class="col-sm-2 control-label">A</label>
							<label class="col-sm-2 control-label" >
							  		<input type="checkbox" id="isAnswer1" />
							 </label>
							  <input type="text" id="answerDesc1"  class="form-control" style="margin-left: 13%;width: 40%;display: initial;" />
						</div>
						<div class="form-group answer_list">
						<label class="col-sm-1 control-label"></label>
							<label class="col-sm-2 control-label">B</label>
							<label class="col-sm-2 control-label" >
							  		<input type="checkbox" id="isAnswer2"  /> 
							 </label>
							  <input type="text" id="answerDesc2"  class="form-control" style="margin-left: 13%;width: 40%;display: initial;" />
						</div>
							
							<div class="form-group answer_list ans3">
							<label class="col-sm-1 control-label dele_bt but03">
          					<span class="glyphicon glyphicon-minus"></span> 
        					</label>
							<label class="col-sm-2 control-label">C</label>
							<label class="col-sm-2 control-label" >
							  		<input type="checkbox" id="isAnswer3"  /> 
							 </label>
							  <input type="text" id="answerDesc3"  class="form-control" style="margin-left: 13%;width: 40%;display: initial;" />
						</div>
						
						<div class="form-group answer_list ans4">
							<label class="col-sm-1 control-label dele_bt but04">
          					<span class="glyphicon glyphicon-minus"></span> 
        					</label>
							<label class="col-sm-2 control-label">D</label>
							<label class="col-sm-2 control-label">
							  		<input type="checkbox" id="isAnswer4"  /> 
							 </label>
							  <input type="text" id="answerDesc4"  class="form-control" style="margin-left: 13%;width: 40%;display: initial;" />
						</div>
						
						<div class="form-group answer_list ans5">
							<label class="col-sm-1 control-label dele_bt but05" >
          					<span class="glyphicon glyphicon-minus"></span> 
        					</label>
							<label class="col-sm-2 control-label" >E</label>
							<label class="col-sm-2 control-label">
							  		<input type="checkbox" id="isAnswer5"  /> 
							 </label>
							  <input type="text" id="answerDesc5"  class="form-control" style="margin-left: 13%;width: 40%;display: initial;" />
						</div>
						
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">所属课程</label>
							<div class="col-sm-4 padding-7">
								${model[1].courseName }
							</div>
							<label class="col-sm-2 control-label">是否上架<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value==model[1].status}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">题目类型</label>
							<div class="col-sm-4 padding-7">
								${model[1].questionTypeName }
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">题目名称</label>
							<div class="col-sm-4 padding-7">
								<textarea id="question" maxlength="1000" class="form-control">${model[1].question}</textarea>
							</div>
						</div>
					</div>
					<div class="questionSet">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">题目选项</label>
					</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="width:20%">序号</label>
							<label class="col-sm-2 control-label" style="width:20%">设置答案</label>
							<label class="col-sm-2 control-label" style="width:20%">选项</label>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="width:20%">A</label>
							<label class="col-sm-2 control-label" style="width:19%">
							  		<input type="checkbox" id="isAnswer1" <c:if test="${model[0][0].isAnswer=='1'}"> checked="checked" </c:if> /> 
							 </label>
							  <input type="text" id="answerDesc1"  value="${model[0][0].answerDesc}" class="form-control" style="margin-left: 18%;width: 41%;display: initial;" />
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" style="width:20%">B</label>
							<label class="col-sm-2 control-label" style="width:19%">
							  		<input type="checkbox" id="isAnswer2" <c:if test="${model[0][1].isAnswer=='1'}"> checked="checked" </c:if> /> 
							 </label>
							  <input type="text" id="answerDesc2"  value="${model[0][1].answerDesc}" class="form-control" style="margin-left: 18%;width: 41%;display: initial;" />
						</div>
						<c:if test="${model[0][2].answerDesc != null}">
                            <c:if test="${model[1].questionType == '01' || model[1].questionType == '03'}">
                                <div class="form-group">
                                <label class="col-sm-2 control-label" style="width:20%">C</label>
                                <label class="col-sm-2 control-label" style="width:19%">
                                        <input type="checkbox" id="isAnswer3" <c:if test="${model[0][2].isAnswer=='1'}"> checked="checked" </c:if> />
                                 </label>
                                  <input type="text" id="answerDesc3"  value="${model[0][2].answerDesc}" class="form-control" style="margin-left: 18%;width: 41%;display: initial;" />
                            </div>
						</c:if>
						<c:if test="${model[0][3].answerDesc != null}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="width:20%">D</label>
                                <label class="col-sm-2 control-label" style="width:19%">
                                        <input type="checkbox" id="isAnswer4" <c:if test="${model[0][3].isAnswer=='1'}"> checked="checked" </c:if> />
                                 </label>
                                  <input type="text" id="answerDesc4"  value="${model[0][3].answerDesc}" class="form-control" style="margin-left: 18%;width: 41%;display: initial;" />
                            </div>
						</c:if>
						<c:if test="${model[0][4].answerDesc != null}">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="width:20%">E</label>
                                <label class="col-sm-2 control-label" style="width:19%">
                                        <input type="checkbox" id="isAnswer5" <c:if test="${model[0][4].isAnswer=='1'}"> checked="checked" </c:if> />
                                 </label>
                                  <input type="text" id="answerDesc5"  value="${model[0][4].answerDesc}" class="form-control" style="margin-left: 18%;width: 41%;display: initial;" />
                            </div>
						</c:if>
						</c:if>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
					
			</form>
		</div>
	</div>
	<input id="questionId" value="${model[1].id}" type="hidden">
	<input id="answerId1" value="${model[0][0].id}" type="hidden">
	<input id="answerId2" value="${model[0][1].id}" type="hidden">
	<input id="answerId3" value="${model[0][2].id}" type="hidden">
	<input id="answerId4" value="${model[0][3].id}" type="hidden">
	<input id="answerId5" value="${model[0][4].id}" type="hidden">
	<input id="courseId" value="${model[1].courseId}" type="hidden">
	<input id="questionType" value="${model[1].questionType}" type="hidden">
	
	<script src="${ctxStatic}/modules/question/form.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
	
	$(function(){
		initCourses();
		//显示隐藏
		 $(".dele_bt").on("click",function(){
			$(this).parent(".answer_list").hide();
		 });
		$("#questionType").change(function(){
		    $("input:checkbox").attr("checked",null);
			if($(this).children("option:checked").val()=="02"){
				$(".answer_list").show();
				$(".answer_list:gt(1)").hide();
			}else if($(this).children("option:checked").val()=="01"){
				$(".answer_list").show();
				$(".answer_list:gt(3)").hide();
			}else if($(this).children("option:checked").val()=="03"){
				$(".answer_list").show();
			}
		})
		
	})	 
	
	
	var defaultOption = '<option value="">---请选择---</option>';
	function initCourses(){
 		$.post(ctx+'/params/courses', {}, function(data){
	  		createCoursesData(data);
 		});
	};

	function createCoursesData(data){
		var selectGist = $("#courseId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.courseId+'">'+obj.courseName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
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
	 $("input[type='text']").blur(function(){
			var self = $(this);
			var id = $(this).attr("id");
			switch (id){
			case "question":
				if (self.val() == "") {
					onError('*请填写题目名称',self);
				} else if(!name.test(self.val())){
					onError('*请输入格式正确的题目名称',self);
				}else{
					onSuccess(self);
				}
				break;
			
				default:
					break;
			}
		});
	})

	       $("input:checkbox").bind("click", function(){
	            var type = $("#questionType").val();
	            if(type == '01'){
	                console.log("单选题");
                    $("input:checkbox").attr("checked",null);
                    this.checked = true;
	            }else if(type == '02'){
	               console.log("判断题");
	               $("input:checkbox").attr("checked",null);
	               this.checked = true;
	            }else if(type == '03'){
	                console.log("多选题题");
	            }else{
                    console.log("other");
	            }
	       });
	</script>
</body>
</html>