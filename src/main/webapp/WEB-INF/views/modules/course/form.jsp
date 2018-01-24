<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-课程管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">课程管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/course/list">课程列表</a></li>
		<li class="active"><a href="${ctx}/course/form?courseId=${model.courseId}">课程${update?'修改':'新增'}</a></li>
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
							<label class="col-sm-2 control-label">课程名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="courseName" maxlength="50" class="form-control" />
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
							<label class="col-sm-2 control-label">适合工种<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="fitService" maxlength="40" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程价格<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4" >
									<input type="text" id="price" maxlength="4" class="form-control"  style="width: 80%;display: initial;"/>元/永久
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">排序<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
		            			<input type="text" id="sortNo"  maxlength="3"/>
							</div>
							<label class="col-sm-2 control-label">推荐标志<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7" >
		            			<input type="checkbox" id="reSign"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程简介</label>
							<div class="col-sm-5">
								<textarea id="summary" maxlength="90" class="form-control"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程形式</label>
							<div class="col-sm-5">
								<select class="chosen-select form-control" id="courseType" style="width: 63%;display: initial;" >
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(23,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="questionSet hidden">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">答题设置</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">答题时间</label>
							<div class="col-sm-4">
								<input type="text" id="courseTime" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>分钟(1-120)
							</div>
						</div>
						<div class="form-group form-padding">
						<label class="col-sm-2 control-label">判断题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()" class="chosen-select form-control" id="tf" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden" id="tfTotalCount">
								<input type="text"  onkeyup="sumTotalScores()" id="subjectCountTF" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">单选题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()" class="chosen-select form-control" id="sc" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden" id="scTotalCount">
								<input type="text" onkeyup="sumTotalScores()" id="subjectCountSC" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">多选题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()" class="chosen-select form-control" id="mc" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden"  id="mcTotalCount">
								<input type="text" onkeyup="sumTotalScores()" id="subjectCountMC" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总分</label>
							<div class="col-sm-4 padding-7">
								<span id="totalScores"></span>分
							</div>
						</div>
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
						<label class="col-sm-2 control-label">创建时间</label>
						<div class="col-sm-4 padding-7">
							<fmt:formatDate value="${model.creatTime}" type="both"/>
						</div>
					</div>
						</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="courseName" value="${model.courseName}" maxlength="40" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">是否上架<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value==model.status}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">适合工种<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="fitService" value="${model.fitService}" maxlength="40" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程价格<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4" >
								<input type="text"  maxlength="4" id="price" value="${model.price}" class="form-control"  style="width: 80%;display: initial;"/>元/永久
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">排序<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
		            			<input type="text" id="sortNo" value="${model.sortNo}" />
							</div>
							<label class="col-sm-2 control-label">推荐标志<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7" >
		            			<input type="checkbox" id="reSign" <c:if test="${model.reSign=='1'}">checked="checked" </c:if> />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程简介</label>
							<div class="col-sm-5">
								<textarea id="summary" maxlength="90" class="form-control">${model.summary}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程形式</label>
							<div class="col-sm-5">
								<select class="chosen-select form-control" id="courseType" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(23,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value==model.courseType}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="questionSet hidden">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">答题设置</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">答题时间</label>
							<div class="col-sm-4">
								<input type="text" id="courseTime" value="${model.courseTime}" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>分钟(1-120)
							</div>
						</div>
						<div class="form-group">
						<label class="col-sm-2 control-label">判断题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()"  class="chosen-select form-control" id="tf" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value== model.templateInfoTf.subjectScore}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden" id="tfTotalCount">
								<input type="text" onkeyup="sumTotalScores()"  id="subjectCountTF" value="${model.templateInfoTf.subjectCount}" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>	
						<div class="form-group">
							<label class="col-sm-2 control-label">单选题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()"  class="chosen-select form-control" id="sc" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value== model.templateInfoSc.subjectScore}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden" id="scTotalCount">
								<input type="text" onkeyup="sumTotalScores()"  id="subjectCountSC" value="${model.templateInfoSc.subjectCount }" maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">多选题</label>
							<div class="col-sm-5">
								<select onchange="sumTotalScores()"  class="chosen-select form-control" id="mc" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(21,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value== model.templateInfoMc.subjectScore}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
								分/题
							</div>
							<div class="col-sm-3 hidden" id="mcTotalCount">
								<input type="text" onkeyup="sumTotalScores()"  id="subjectCountMC" value="${model.templateInfoMc.subjectCount }"  maxlength="3" class="form-control" style="width: 80%;display: initial;"/>题
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总分</label>
							<div class="col-sm-4 padding-7">
								<span id="totalScores"></span>分
							</div>
						</div>
					</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
			</form>
		</div>
	</div>
	<input id="courseId" value="${model.courseId}" type="hidden">
	<input id="courseTypeModify" value="${model.courseType}" type="hidden">
	<input id="flag3" value="${model.templateInfoMc.subjectCount}" type="hidden">
	<input id="flag4" value="${model.templateInfoTf.subjectCount}" type="hidden">
	<input id="flag2" value="${model.templateInfoSc.subjectCount}" type="hidden">
	<script src="${ctxStatic}/modules/courses/form.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var flag1 =$("#courseTypeModify").val();
		if(flag1 == "02" || flag1 =="03" ){
			$(".questionSet").removeClass("hidden");
		}
		var flag2 = $("#flag2").val();
		if( flag2!= "" &&  flag2 != null ){
			$("#scTotalCount").removeClass("hidden");
		}
		var flag3= $("#flag3").val();
		if(flag3 != "" && flag3!= null ){
			$("#mcTotalCount").removeClass("hidden");
		}  
		var flag4 = $("#flag4").val();
		if( flag4 != ""  &&  flag4 != null){
			$("#tfTotalCount").removeClass("hidden");
		}  
		$(function(){
			$("#courseType").change(function(){
				if($(this).children("option:checked").val()=="01"){
					$("#courseId").hide();
				}else if($(this).children("option:checked").val()=="02"){
					$("#courseId").show();
				}else if($(this).children("option:checked").val()=="03"){
					$("#courseId").show();
				}
			})
			//显示隐藏
			 $("#sc").on("change",function(){
				 var opa = $('#sc option:selected').val();
				 if(""!=opa){
					$("#scTotalCount").removeClass("hidden");
				 }
				 if(""==opa){
				 	$("#scTotalCount").addClass("hidden");
				 }
				 
			 });
			 $("#tf").on("change",function(){
				 var opa = $('#tf option:selected').val();
				 if(""!=opa){
				 $("#tfTotalCount").removeClass("hidden");
				 }
				 if(""==opa){
				 $("#tfTotalCount").addClass("hidden");
				 }
			 });
			 $("#mc").on("change",function(){
				 var opa = $('#mc option:selected').val();
				 if(""!=opa){
				 $("#mcTotalCount").removeClass("hidden");
				 }
				 if(""==opa){
				 $("#mcTotalCount").addClass("hidden");
				 }
			 });
			 $("#courseType").on("change",function(){
				 var opa = $('#courseType option:selected').val();
				  if("03" == opa|| "02" == opa){
				 	$(".questionSet").removeClass("hidden");
				 }
				 if("01" == opa){
				 	$(".questionSet").addClass("hidden");
				 } 
			 });
		})	 
		
		
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
					case "fitService":
						if (self.val() == "") {
							onError('*请填写适合工种',self);
						} else if(!name.test(self.val())){
							/* onError('*请输入格式正确的适合工种',self); */
						}else{
							onSuccess(self);
						}
						break;
					case "price":
						if (self.val() == "") {
							onError('*请填写课程价格',self);
						} else if(!number.test(self.val())){
							onError('*请输入格式正确的课程价格',self);
						}else{
							onSuccess(self);
						}
						break;
					case "summary":
						if (self.val() == "") {
							onError('*请填写课程简介',self);
						} else if(!name.test(self.val())){
							onError('*请输入格式正确的课程简介',self);
						}else{
							onSuccess(self);
						}
						break;
					case "courseTime":
					    if($("#courseType").val()=='01'){
					        onSuccess(self);
					    }
						if (self.val() == "") {
							onError('*请填写答题时间',self);
						} else if(!number.test(self.val())){
							onError('*请输入格式正确的答题时间',self);
						}else{
							onSuccess(self);
						}
						break;
						default:
							break;
					}
				});
			})
        sumTotalScores();
		function sumTotalScores(){
		   $("#totalScores").html($("#tf").val() * $("#subjectCountTF").val() + $("#sc").val() * $("#subjectCountSC").val() + $("#mc").val() * $("#subjectCountMC").val());
		}
	</script>
</body>
</html>