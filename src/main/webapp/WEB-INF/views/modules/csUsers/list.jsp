<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-用户列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">用户列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">用户列表</a></li>
		
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
	            <label class="col-md-2 control-label">邀请码</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="inviteCode" name="inviteCode" value="" />
	            </div>
	             <label class="col-md-2 control-label">用户手机号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="mobile" name="mobile" value="" />
	            </div>
           	</div>
           	<div class="form-group">
           		  <label class="col-md-2 control-label">用户姓名</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="userName" name="userName" value="" />
	            </div>
	            
	             <label class="col-md-2 control-label">状态</label>
	             <!--状态：已注册、已绑定、已认证  -->
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="authStatus">
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(30,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
           	</div>
           	  	<div class="form-group">
            	<label class="col-md-2 control-label">注册时间</label>
	           <div class="col-md-4">
	            	<div class="input-group">
						<input type="text" class="form-control date-picker" id="startDate" name="startDate" value="" data-date-format="yyyy-mm-dd"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
				</div>
				<div class="col-md-4">
					<div class="input-group">
						<input type="text" class="form-control date-picker" id="endDate" name="endDate" value="" data-date-format="yyyy-mm-dd"/>
						<span class="input-group-addon">
							<i class="fa fa-calendar bigger-110"></i>
						</span>
					</div>
	            </div>
           	</div>
        </form>
    </div>
    <div class="page-content">
    	<div class="page-heading mb5">
        	<div class="text-left small">
	        	<button class="btn btn-sm btn-primary" type="button" id="queryBtn"><span class="fa fa-search"></span> 查询</button>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<script src="${ctxStatic}/js/dateFormat.js"></script> 
	<script src="${ctxStatic}/modules/users/list.js"></script>
</body>
</html>