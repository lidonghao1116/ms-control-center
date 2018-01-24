<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-红包列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">红包列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">红包列表</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
            <div class="form-group">
            	<label class="col-md-2 control-label">邀请人昵称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="inviterName" name="inviterName" value="" />
	            </div>
	            <label class="col-md-2 control-label">邀请人Openid</label>
	            	<div class="col-md-2">
	            		<input type="text" class="form-control" id="inviterOpenid" name="inviterOpenid" value="" />
	            	</div> 
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">清算状态</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="status" >
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(28,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
	            <label class="col-md-2 control-label">订单编号</label>
	            	<div class="col-md-2">
	            		<input type="text" class="form-control" id="orderNo" name="orderNo" value="" />
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
	<script src="${ctxStatic}/modules/redPacket/list.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>