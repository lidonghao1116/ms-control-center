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
		<li class="active"><a href="#">编辑课程</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">创建时间：</label>
								<div class="col-md-2 padding-7">
								<fmt:formatDate value="${course.addTime}" type="both"/>
								</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">修改时间：</label>
								<div class="col-md-2 padding-7">
									<fmt:formatDate value="${course.modifyTime}" type="both"/>
								</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程名称：</label>
								<div class="col-md-2 padding-7">
							          ${course.courseName}
							    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证书名称：</label>
								<div class="col-md-2 padding-7">
							          ${course.certName}
							    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">鉴定等级：</label>
							
								<div class="col-md-2 padding-7">
									${course.orgName}
								</div>							
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总课时：</label>
							<div class="col-sm-4">
		            			<input type="text" value="${course.totalHours }" id="totalHour"  maxlength="3" class="form-control"/>
							</div>							
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">考试形式：</label>
							<div class="col-sm-4" >
								<select class="chosen-select form-control" id="examType" style="width: 63%;display: initial;" >
								  <option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value==course.examType}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>							
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">发证机构：</label>
								<div class="col-md-8 padding-7">
							          ${course.authorityName}
							    </div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注:</label>
							<div class="col-sm-5">
								<textarea id="remarks"  maxlength="90" class="form-control">${course.remarks }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">课程状态：</label>
         					<div class="radio radio-info radio-inline" id="status" >
		                        <input type="radio" id="inlineRadio1" value="01" name="radioInline" <c:if test="${course.status eq '01'}"> checked="checked"</c:if>/>
		                        <label for="inlineRadio1">上架 </label>
		                    </div>
		                    <div class="radio radio-inline" id="status">
		                        <input type="radio" id="inlineRadio2" value="02" name="radioInline" <c:if test="${course.status eq '02'}"> checked="checked"</c:if>/>
		                        <label for="inlineRadio2">下架</label>
		                    </div>
					   	</div>
					  </div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>取消</button>
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保存</button>
					</div>
			</form>
		</div>
	</div>
<script src="${ctxStatic}/modules/schoolCourse/course.js"></script>
<input id="courseId" value="${course.courseId}" type="hidden"/>
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