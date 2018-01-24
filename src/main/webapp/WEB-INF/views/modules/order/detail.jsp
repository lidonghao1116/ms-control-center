<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-订单管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">订单详情</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/user/list">订单记录列表</a></li>
		<li class="active"><a href="${ctx}/order/info?orderNo=${model[0].orderNo}">订单详情</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls">用户信息</label>
				</div>
				<div class="form-group">
					<div class="form-group form-padding">
						<label class="col-md-2 control-label">手机号码</label>
						<div class="col-md-4 padding-7">
							${model[1].mobile}
						</div>
					</div>
				</div>
					<div class="form-group">
						<label class="col-md-2 control-label">用户昵称</label>
						<div class="col-md-2 padding-7">
							${model[1].wechatNick}
						</div>
						<label class="col-md-2 control-label">用户姓名</label>
						<div class="col-md-4 padding-7">
							${model[1].userName}
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">邀请人Openid</label>
						<div class="col-md-2 padding-7">
							${model[2].openId}
						</div>
						<label class="col-md-2 control-label">邀请人昵称</label>
						<div class="col-md-4 padding-7">
							${model[2].wechatNick}
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
				  		<label class="col-md-2 controls">购买信息</label>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">订单编号</label>
						<div class="col-md-2 padding-7">
							${model[0].orderNo}
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">购买时间</label>
						<div class="col-md-4 padding-7">
							<fmt:formatDate value="${model[0].orderTime}" type="both"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">购买课程</label>
						<div class="col-md-4 padding-7">
							${model[0].courseName}
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">支付价格</label>
						<div class="col-md-4 padding-7">
							${model[0].amount}
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">支付方式</label>
						<div class="col-md-4 padding-7">
							${model[0].tradeChannelName}
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