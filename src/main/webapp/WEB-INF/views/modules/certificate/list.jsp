<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-证书列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">证书列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">证书列表</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="GET">
            <div class="form-group">
            	<label class="col-md-2 control-label">学员姓名</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="userName" value="" />
	            </div>
	            <label class="col-md-2 control-label">学员手机号</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="mobile"value="" />
	            </div>
           	</div>
          <div class="form-group">
              <label class="col-md-2 control-label">学校名称</label>
              <div class="col-md-2">
                    <select class="chosen-select form-control" id="schoolId" name="schoolId"></select>
                </div>
              <label class="col-md-2 control-label">报名课程</label>
                <div class="col-md-2">
                    <select class="chosen-select form-control" id="courseId" name="courseId"></select>
                </div>
        </div>
          <div class="form-group">
             <label class="col-md-2 control-label">报名时间</label>
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
	<script src="${ctxStatic}/modules/certificate/list.js"></script>
	<script type="text/javascript">
	var defaultOption = '<option value="">---请选择---</option>';
	function initSchoolCourses(schooolId,id){
		//alert(id);
		//alert(schooolId);
 		$.post(ctx+'/params/learnType', {id:schooolId,values:id}, function(data){
	  		createCoursesData(data);
 		});
	};
	function initSchools(){
 		$.post(ctx+'/params/schools', {}, function(data){
	  		createSchoolsData(data);
 		});
	};

	function createCoursesData(data){
		var selectGist = $("#courseId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.id+'">'+obj.typeName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	function createSchoolsData(data){
		var selectGist = $("#schoolId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
      			$('<option value="'+obj.id+'" ck="'+obj.applyCourses+'">'+obj.schoolName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	$(function(){

		initSchools();
		$("#schoolId").bind('change',function(){
			var id=$("#schoolId").find("option:selected").attr("ck");
			var schooolId=$("#schoolId").find("option:selected").val();
			//alert(id);
			//alert(schooolId);
			initSchoolCourses(schooolId,id);

		});
	});
	</script>
</body>
</html>