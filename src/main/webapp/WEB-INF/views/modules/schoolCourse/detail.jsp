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
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">课程列表页面</a></li>
		<li class="active">课程详情</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/applyOrder/list">课程列表</a></li>
		<li class="active"><a href="${ctx}/applyOrder/info?id=${model.id}">课程详情</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls text-info">基本信息</label>
				</div>
				<div class="form-group">
				<div class="form-group">
					<div class="form-group form-padding">
						<label class="col-md-2 control-label text-danger">创建时间</label>
						<div class="col-md-2 padding-7">
							<fmt:formatDate value="${model.addTime}" type="both"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group ">
						<label class="col-md-2 control-label text-danger">学校名称</label>
						<div class="col-md-2 padding-7">
							<c:if test="${not empty model.schools}">
							${model.schools.schoolName}
							</c:if>
						</div>
						
						<label class="col-md-2 control-label text-danger">课程名称</label>
						<div class="col-md-2 padding-7">
							${model.typeName}
						</div>
						<label class="col-md-2 control-label text-danger">鉴定等级</label>
						<div class="col-md-2 padding-7">
							${fns:getText(25,0,model.authenticateGrade)}
						</div>
					</div>
					
					<div class="form-group  ">
						<label class="col-md-2 control-label text-danger">考试形式</label>
							<div class="col-md-2 padding-7">
								${fns:getText(1,0,model.examType)}
							</div>
						<label class="col-md-2 control-label text-danger">总课时</label>
						<div class="col-md-2 padding-7">
							${model.totalHours}
						</div>	
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">发证机构</label>
						<div class="col-md-2 padding-7">
							<c:if test="${not empty model.certAuthority}">
							${model.certAuthority.authorityName}
							</c:if>
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">课程状态</label>
						<div class="col-md-2 padding-7">
							${fns:getText(2,0,model.status)}
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">备注</label>
						<div class="col-md-2 padding-7">
							${model.remarks}
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">上课时间</label>
						<div class="col-md-10 padding-7">
							<c:forEach var="ct" items="${model.classTimesList}">
                                <div>${ct.templateName}: 学习周期（${ct.openCycle}） 上课时间（${ct.beginTime}-${ct.endTime}） </div>
							</c:forEach>
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">交社保要求</label>
						<div class="col-md-2 padding-7">
							${obj.isHasPf=='1'?"是":"否"}
						</div>
						
					</div>
				</div>
					<div class="form-group" style="margin-bottom: 5px;">
				  		<label class="col-md-2 controls text-info">费用信息</label>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">监考费</label>
						<div class="col-md-2 padding-7">
							${model.examFee}
						</div>
						<label class="col-md-2 control-label text-danger">其他费</label>
						<div class="col-md-2 padding-7">
							${model.otherFee}
						</div>
						<label class="col-md-2 control-label text-danger">证书费</label>
						<div class="col-md-2 padding-7">
							${model.certificateFee}
						</div>
						
					</div>
					
				<div class="col-sm-12 text-center">
					<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>