<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-预报名受理管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">预报名受理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/orders/list?type=PEND_APPLY">预报名学员</a></li>
		<li><a href="${ctx}/orders/list?type=NOT_MATCH">不符合学员</a></li>
		<li><a href="${ctx}/orders/list?type=REP_APPLY">补考学员</a></li>
		<li class="active"><a href="${ctx}/orders/form?id=${model.id}&type=PEND_APPLY">预报名受理${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<c:if test="${update == 'true'}">
					<c:if test="${isExist == 'true'}">
						<%@ include file="form_info.jsp"%>
					</c:if>
					<c:if test="${isExist == 'false'}">
						<%@ include file="form_modify.jsp"%>
					</c:if>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">报考信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">产品</label>
							<div class="col-sm-4 padding-7">
								${model.productName}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程</label>
							<div class="col-sm-4 padding-7">
								${model.courseName}
								<select class="chosen-select form-control" id="courseId"></select>
							</div>
							<label class="col-sm-2 control-label">班级标号</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="classNumber">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(2,0)}" var="obj" varStatus="item">
					           			<option value="${obj.value}">${obj.text}</option>
					           		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">上课时间</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="classTime"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否考试</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="isExam">
									<option value="">--请选择--</option>
					           		<option value="1">是</option>
					           		<option value="0">否</option>
								</select>
							</div>
							<label class="col-sm-2 control-label">是否交金</label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="isHasPf">
									<option value="">--请选择--</option>
					           		<option value="1" <c:if test="${model.isHasPf=='1'}">selected="selected" </c:if>>是</option>
					           		<option value="0" <c:if test="${model.isHasPf=='0'}">selected="selected" </c:if>>否</option>
								</select>
							</div>
						</div>
					</div>
					
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">收费信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学费<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schoolFee" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
							<label class="col-sm-2 control-label">押金</label>
							<div class="col-sm-4">
								<input type="text" id="deposit" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">书费</label>
							<div class="col-sm-4">
								<input type="text" id="bookFree" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否分期</label>
							<div class="col-sm-4 padding-7">
								<input type="radio" name="isStaging" value="1">是
								<input type="radio" name="isStaging" value="0">否
							</div>
							<label class="col-sm-2 control-label">首付款</label>
							<div class="col-sm-4">
								<input type="text" id="firstPay" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="passBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>通过</button>
						<button class="btn btn-sm btn-primary" id="unpassBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>不通过</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				
				</c:if>
			</form>
		</div>
	</div>
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="isExist" value="${isExist}" type="hidden"/>
	<script src="${ctxStatic}/modules/applys/1/form.js"></script>
	<script src="${ctxStatic}/modules/help.js"></script>	
	<script type="text/javascript">
	var isExist=${isExist};
	$(function(){
		var classTime=${model.classTime!=null?model.classTime:'""'};
		var courseId=${model.courseId!=null?model.courseId:'""'};
		initExamClass();
		$("#sourceType").bind('change',function(){
			$("#sourceValueDiv").html("");
			var id=$(this).find("option:selected").attr("id");
			initSourceSec(id,'');
		});
		
		$("#sourceTypeSec").bind('change',function(){
			var pid=GetSelectValue("#sourceType");
			if(pid=='01'){
				var id=GetSelectValue("#sourceTypeSec");
				initSourceValue(id,'');
			}else if(pid=='02'){
				$("#sourceValueDiv").html("");
				$("#sourceValueDiv").append(sourceValueInput);
			}
		});
		initCourses(courseId,courseId);
		var values=$("#courseId").find("option:selected").attr("ct");
		initClassTimes(values,classTime);
		$("#courseId").hide()
	});
	</script>
</body>
</html>