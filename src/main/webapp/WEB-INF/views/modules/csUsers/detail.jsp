<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-用户管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">用户详情</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/user/list">用户列表</a></li>
		<li class="active"><a href="${ctx}/user/info?id=${model.userInfo.id}">用户详情</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls">用户信息</label>
				</div>
				<div class="form-group">
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">注册时间</label>
						<div class="col-sm-4 padding-7">
							<fmt:formatDate value="${model.userInfo.registerTime}" type="both"/>
						</div>
					</div>
				</div>
				<div class="form-group">
						<div class="form-group">
						<label class="col-sm-2 control-label">用户昵称</label>
						<div class="col-sm-4 padding-7">
							${model.userInfo.wechatNick}
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">用户手机号</label>
						<div class="col-sm-4 padding-7">
							${model.userInfo.mobile}
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">用户姓名</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.userExtend}">
							${model.userExtend.userName}
							</c:if>
						</div>
					</div>
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">邀请码</label>
						<div class="col-sm-4 padding-7">
							${model.userInfo.inviteCode}
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邀请人OpenId</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.inviterInfo}">
							${model.inviterInfo.openId}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邀请人昵称</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.inviterInfo}">
							${model.inviterInfo.wechatNick}
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邀请人手机号</label>
						<div class="col-sm-4 padding-7">
							<c:if test="${not empty model.inviterInfo}">
							${model.inviterInfo.mobile}
							</c:if>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="form-group form-padding">
						<label class="col-sm-2 control-label">认证时间</label>
						<div class="col-sm-4 padding-7">
							<fmt:formatDate value="${model.userInfo.registerTime}" type="both"/>
						</div>
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