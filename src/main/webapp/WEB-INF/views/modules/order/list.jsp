<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-订单列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">订单记录</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">订单记录列表</a></li>
		
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
	            <label class="col-md-2 control-label">用户昵称</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="wechatNick" name="wechatNick" value="" />
	            </div>
	             <label class="col-md-2 control-label">用户手机号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="mobile" name="mobile" value="" />
	            </div>
           	</div>
           	<div class="form-group">
           		  <label class="col-md-2 control-label">订单编号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="ordersNo" name="ordersNo" value="" />
	            </div>
	            
	            <label class="col-md-2 control-label">购买课程</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control" id="courseId" name="courseId"></select>
	            	</div>
           	</div>
           	<div class="form-group">
           		  <label class="col-md-2 control-label">是否被邀请</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="isInvited" >
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(24,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
           	</div>
          <div class="form-group">
            <label class="col-md-2 control-label">购买时间</label>
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
	        	<span class="col-md-2" style="float: right;paddind-top: 3px">下载学习记录：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></span>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<script src="${ctxStatic}/js/dateFormat.js"></script> 
	<script src="${ctxStatic}/modules/order/list.js"></script>
	<script type="text/javascript">
	
	var defaultOption = '<option value="">---请选择---</option>';
	function initCourses(){
 		$.post(ctx+'/params/courses?status=all', {}, function(data){
	  		createCoursesData(data);
 		});
	};

	function createCoursesData(data){
		var selectGist = $("#courseId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.courseId+'">'+obj.courseName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	$(function(){
		initCourses();
		
		
	});
	</script>
</body>
</html>