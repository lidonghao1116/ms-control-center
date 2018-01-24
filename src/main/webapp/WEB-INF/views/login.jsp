<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-登录</title>
<meta name="decorator" content="default" />
<script src="${ctxStatic}/js/supersized.3.2.7.min.js"></script>
<script src="${ctxStatic}/js/supersized-init.js"></script>
</head>

<body>
	<div class="dr-login-page">
		<!-- <a href="#" class="logo" style="height: 50px;"></a> -->
		<h1 class="form-signin-heading">${fns:getConfig('productName')}-总控系统</h1>
		<form id="loginForm" action="${ctx}/login" method="post">
			<div class="form-group col-md-8" style="text-align: left;">
				<label for="userName" class="sr-only">用户名</label> <input type="text"
					id="userName" name="username" placeholder="用户名" value="${username}"
					class="form-control required" />
			</div>
			<div class="form-group col-md-8" style="text-align: left;">
				<label for="password" class="sr-only">密码</label> <input
					type="password" id="password" name="password" placeholder="密码"
					class="form-control required" />
			</div>
			<c:if test="${captcha==true}">
				<div class="form-group col-md-8" style="text-align: left;">
					<div class="row ">
						<div class="col-md-7">
							<input type="text" id="captchCode" name="captchCode"
								class="form-control col-md-2 required" />
						</div>
						<div class="col-md-5">
							<img id="captcha" src="${ctx}/common/getCaptcha"
								style="cursor: pointer;" onclick="changeCaptcha()">
						</div>
					</div>
				</div>
			</c:if>
			<div class="form-group col-md-8">
				<button class="btn btn-primary btn-lg btn-block" type="submit"><i class="icon-key"></i>登 录</button>
			</div>
			<c:if test="${not empty message}">
				<div class="form-group col-md-8">
					<div class="alert alert-danger alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">×</button>
						${message}
					</div>
				</div>
			</c:if>
		</form>
		<div class="footer">
			Copyright &copy; 2015-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a>
			- Powered By <a href="" target="_blank">hezp</a> ${fns:getConfig('version')}
		</div>
	</div>
	<script>
		function changeCaptcha() {
			$("#captcha").attr("src",
					ctx + "/common/getCaptcha?date=" + new Date());
		}
	</script>
</body>
</html>