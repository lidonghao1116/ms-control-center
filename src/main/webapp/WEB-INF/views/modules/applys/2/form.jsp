<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-报名管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">已报名列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/orders/list?type=SUCCESS_APPLY">已报名列表</a></li>
		<li class="active"><a href="${ctx}/orders/form?id=${model.id}&type=SUCCESS_APPLY">新增${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<%@ include file="addForm.jsp"%>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<%@ include file="modifyForm.jsp"%>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				
				</c:if>
			</form>
		</div>
	</div>
	<input id="id" value="${model.id}" type="hidden"/>
	<script src="${ctxStatic}/modules/applys/2/form.js"></script>
	<script src="${ctxStatic}/modules/help.js"></script>
		
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
	var flag=${update};
	$(function(){
		initExamClass();
		if(!flag){
			initProducts();
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
			
			$("#packageId").bind('change',function(){
				var values=$("#packageId").find("option:selected").attr("ck");
				initCourses(values,'');
			});
			
			$("#courseId").bind('change',function(){
				var values=$("#courseId").find("option:selected").attr("ct");
				initClassTimes(values,'');
			});
		}
	});
	</script>
</body>
</html>