<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-题库列表</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">题库列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">课程列表</a></li>
		<li><a href="${ctx}/question/form?id=">新增题目</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">题目名称</label>
	           <div class="col-md-2">
	            	<input type="text" id="question" maxlength="50" class="form-control" >
	            </div>
	            <label class="col-md-2 control-label">状态</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="status" >
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
           	</div>
           	 <div class="form-group">
            	<label class="col-md-2 control-label">题目类型</label>
	           <div class="col-md-2">
	            	<select class="chosen-select form-control" id="questionType" >
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(26,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}">${obj.text}</option>
	            		</c:forEach>
					</select>
	            </div>
	            	<label class="col-md-2 control-label">所属课程</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control" id="courseId" name="courseId"></select>
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
	<script src="${ctxStatic}/modules/question/list.js"></script>
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