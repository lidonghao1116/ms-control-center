<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-课程列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">课程列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">课程列表</a></li>
		<li><a href="${ctx}/schoolCourse/addCourse">新增课程</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
           	<div class="form-group">
           		  <label class="col-md-1 control-label">课程名称</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control" id="courseId" name="courseId"></select>
	            	</div>
	              <label class="col-md-1 control-label">鉴定等级</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control"  id="authenticateGrade" name="authenticateGrade"></select>
	            	</div>
           	</div>
           	<div class="form-group">
           		  <label class="col-md-1 control-label">发证机构</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control" id="authorityId" name="authorityId"></select>
	            	</div>
	              <label class="col-md-1 control-label">课程状态</label>
	            	<div class="col-md-2">
	            		<select class="chosen-select form-control"  id="status" name="status"></select>
	            	</div>
	    			<div class="col-md-3 col-md-offset-3">
	        			<button class="btn btn-sm btn-primary" type="button" id="queryBtn"><span class="fa fa-search"></span></button>
	    			</div>
           	</div>
        </form>
    </div>
    <div class="page-content">
    	<div class="page-heading mb5">
        	<div class="text-left small">
	        	<button class="btn btn-sm btn-primary" type="button" onclick="addCourse()">新增课程</button>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<script src="${ctxStatic}/js/dateFormat.js"></script> 
	<script src="${ctxStatic}/modules/schoolCourse/list.js"></script>
	<script type="text/javascript">
	
	var defaultOption = '<option value="">---请选择---</option>';
	function addCourse(){
		window.location.href=ctx+"/schoolCourse/addCourse";
	};
	/*课程名称信息  */
	function initCourseBaseInfo(){
 		$.post(ctx+'/params/coursesBaseInfo', {}, function(data){
	  		createCourseBaseInfoData(data);
 		});
	};
	/*鉴定等级  */
	function initAuthenticateGrade(){
 		$.post(ctx+'/params/cfgParamsOfGrade', {}, function(data){
 			createAuthenticateGradeData(data);
 		});
	};
	/* 机构名称信息 */
	function initAuthorityName(){
 		$.post(ctx+'/params/authorityName', {}, function(data){
	  		createAuthorityNameData(data);
 		});
	};
	/*课程状态信息  */
	function initCourseStatus(){
 		$.post(ctx+'/params/cfgParamOfStatus', {}, function(data){
	  		createCourseStatusData(data);
 		});
	};
	
	/*课程名称信息 */
	function createCourseBaseInfoData(data){
		var selectGist = $("#courseId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.courseId+'">'+obj.courseName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	/*鉴定等级信息  */
	function createAuthenticateGradeData(data){
		var selectGist = $("#authenticateGrade");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	/* 发证机构信息 */
	function createAuthorityNameData(data){
		var selectGist = $("#authorityId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.authorityId+'">'+obj.authorityName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	/*课程状态信息  */
	function createCourseStatusData(data){
		var selectGist = $("#status");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	
	$(function(){
		initCourseBaseInfo();
		initAuthenticateGrade();
		initAuthorityName();
		initCourseStatus(); 
	});
	
	
	</script>
</body>
</html>