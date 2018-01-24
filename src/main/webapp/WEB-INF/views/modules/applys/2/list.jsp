<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>${fns:getConfig('productName')}-已报名列表</title>
<%@ include file="/WEB-INF/views/include/datepicker.jsp"%>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">主页</a></li>
		<li class="active">已报名列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li class="active"><a href="#">已报名列表</a></li>
		<li><a href="${ctx}/orders/form?id=&type=SUCCESS_APPLY">新增学员</a></li>
	</ul>
	<div class="searchbar"> 
        <form class="form-horizontal" action="" id="queryForm" method="post" enctype="multipart/form-data" target="uploadTarget">
            <div class="form-group">
            	<label class="col-md-2 control-label">姓名</label>
	            <div class="col-md-2">
	            	<input type="text" class="form-control" id="userName" name="userName" value="" />
	            </div>
	            <label class="col-md-2 control-label">来源</label>
	           	<div class="col-sm-2">
					<select class="chosen-select form-control"  name="sourceType"  id="sourceType">
						<option value="">--请选择--</option>
						<c:forEach items="${fns:getParams(5,0)}" var="obj" varStatus="item">
	            			<option value="${obj.value}" id="${obj.id}">${obj.text}</option>
	            		</c:forEach>
					</select>
				</div>
				<div class="col-sm-2">
					<select class="chosen-select form-control" name="sourceTypeSec"  id="sourceTypeSec">
						<option value="">--请选择--</option>
					</select>
				</div>
           	</div>
           	<div class="form-group">
           		<label class="col-md-2 control-label">产品</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="packageId" name="packageId"></select>
	            </div>
            	<label class="col-md-2 control-label">课程</label>
	            <div class="col-md-2">
	            	<select class="chosen-select form-control" id="courseId" name="courseId"></select>
	            </div>
           	</div>
           	<div class="form-group">
            	<label class="col-md-2 control-label">受理时间</label>
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
	        	<span class="col-md-2" style="float: right;paddind-top: 3px">下载学员信息：<a style="cursor: pointer;" id="exportBtn">EXCEL下载</a></span>
	    	</div>
	    </div>
		<div class="page-body">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<iframe id="uploadTarget" name="uploadTarget" style='display:none;'></iframe>
	<script src="${ctxStatic}/modules/applys/2/list.js"></script>
	<script type="text/javascript">
	var defaultOption = '<option value="">---请选择---</option>';
	function initCourses(){
 		$.post(ctx+'/params/courses', {}, function(data){
	  		createCoursesData(data);
 		});
	};
	
	function initProducts(){
 		$.post(ctx+'/params/coursesPackage', {}, function(data){
	  		createProductsData(data);
 		});
	};
	
	function initSourceSec(id){
 		$.post(ctx+'/system/params', {type:5,pid:id}, function(data){
 			createSourceSecData(data);
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
	
	function createProductsData(data){
		var selectGist = $("#packageId");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
   				$('<option value="'+obj.id+'">'+obj.packageName+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	
	function createSourceSecData(data){
		var selectGist = $("#sourceTypeSec");
		selectGist.html('');
		selectGist.append(defaultOption);
		if(data){
      		$.each(data,function(i,obj){
   				$('<option value="'+obj.value+'">'+obj.text+'</option>').appendTo(selectGist);
	      	});
	    }
	}
	
	$(function(){
		initCourses();
		initProducts();
		
		$("#sourceType").bind('change',function(){
			var id=$(this).find("option:selected").attr("id");
			initSourceSec(id);
		});
	});
	</script>
</body>
</html>