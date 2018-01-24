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
		<li class="active"><a href="${ctx}/course/form?id=${model.id}">课程${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">身份信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="typeName" maxlength="50" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">考试形式<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="examType">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总课时<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="totalHours" maxlength="50" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">上课时间<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7" >
								<c:forEach items="${fns:getParams(16,0)}" var="obj" varStatus="item">
									<input type="checkbox" name="classTimes" value="${obj.value}"/>${obj.text}
			            		</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否上海交金<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<input type="radio" name="isNeedHasPf" value="1">是
								<input type="radio" name="isNeedHasPf" value="0">否
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注</label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control"></textarea>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">费用信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">监考费</label>
							<div class="col-sm-4">
								<input type="text" id="examFee" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
							<label class="col-sm-2 control-label">其他费</label>
							<div class="col-sm-4">
								<input type="text" id="certificateFee" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证书费</label>
							<div class="col-sm-4">
								<input type="text" id="otherFee" maxlength="50" class="form-control" style="width: 80%;display: initial;"/>元
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
					  <label class="col-md-2 controls">身份信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<input type="text" id="typeName" value="${model.typeName}" maxlength="50" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">考试形式<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="examType" disabled="disabled">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(1,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${model.examType==obj.value}">selected="selected" </c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">总课时<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								${model.totalHours}
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">课程状态<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" >
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(2,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${model.status==obj.value}">selected="selected" </c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注<span class="dr-asterisk">*</span></label>
							<div class="col-sm-5">
								<textarea id="remarks" maxlength="1000" class="form-control">${model.remarks}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">上课时间<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<c:forEach items="${fns:getParams(16,0)}" var="obj" varStatus="item">
									<input type="checkbox" name="classTimes" value="${obj.value}"/>${obj.text}
			            		</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否上海交金<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<input type="radio" name="isNeedHasPf" value="1" <c:if test="${model.isNeedHasPf=='1'}">checked="checked" </c:if>>是
								<input type="radio" name="isNeedHasPf" value="0" <c:if test="${model.isNeedHasPf=='0'}">checked="checked" </c:if>>否
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">费用信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">监考费</label>
							<div class="col-sm-4">
								<input type="text" id="examFee" maxlength="50" value="${model.examFee}" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
							<label class="col-sm-2 control-label">其他费</label>
							<div class="col-sm-4">
								<input type="text" id="certificateFee" maxlength="50" value="${model.certificateFee}" class="form-control" style="width: 80%;display: initial;"/>元
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证书费</label>
							<div class="col-sm-4">
								<input type="text" id="otherFee" maxlength="50" value="${model.otherFee}" class="form-control" style="width: 80%;display: initial;"/>元
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
	<input id="id" value="${model.id}" type="hidden"/>
	<input id="par" value="${model.classTimes}" type="hidden"/>
	<script src="${ctxStatic}/modules/courses/form.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var flag=${update};
		if(flag){
			var boxs=$("#par").val();
			initCheckData(boxs,"classTimes");
		}
	</script>
</body>
</html>