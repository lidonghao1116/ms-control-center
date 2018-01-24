<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-发证机构管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">发证机构管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/certAuthority/list">发证机构列表</a></li>
		<li class="active"><a href="${ctx}/certAuthority/form?authorityId=${model.authorityId}">${update?'修改':'新增'}机构</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">发证机构<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text"  placeholder="请输入发证机构" id="authorityName" maxlength="50" class="form-control" />
							</div>
							
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">备注<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<textarea id="remark"  placeholder="备注" maxlength="1000" class="form-control"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否上架<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}">${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				<!-- 修改 -->
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">发证机构<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								${model.authorityName}
							</div>
							
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">备注<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								${model.remark}
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否上架<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<select class="chosen-select form-control" id="status" style="width: 63%;display: initial;">
									<option value="">--请选择--</option>
									<c:forEach items="${fns:getParams(22,0)}" var="obj" varStatus="item">
				            			<option value="${obj.value}" <c:if test="${obj.value==model.status}">selected="selected"</c:if>>${obj.text}</option>
				            		</c:forEach>
								</select>
							</div>
						</div>
					</div>
					
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="modifyBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
			</form>
		</div>
	</div>
	<script src="${ctxStatic}/modules/authority/form.js"></script>
	<input id="authorityId" value="${model.authorityId}" type="hidden"/>
	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
	//输入check
	$(function(){
		 function onError (msg,self) {
				if(self.nextAll().hasClass('prompt_sp')){
					self.nextAll('.prompt_sp').text(msg);
					return;
				}
				self.parent().append("<span class='prompt_sp' style='color:red;margin-left:10px'>"+msg+"</span>");
			};

		function onSuccess(self) {
			self.nextAll('.prompt_sp').remove();	
		}; 
	
		var name = /^[a-zA-Z0-9\u4e00-\u9fa5]+$/;//文字、数字、字母
		var number = /^[0-9]+$/;//数字
		 $("input[type='text'],textarea").blur(function(){
				var self = $(this);
				var id = $(this).attr("id");
				switch (id){
				case "authorityName":
					if (self.val() == "") {
						onError('*请填写发证机构',self);
					} else if(!name.test(self.val())){
						onError('*请输入格式正确的发证机构',self);
					}else{
						onSuccess(self);
					}
					break;
				case "remark":
					if (self.val() == "") {
						onError('*请填写发证机构',self);
					} else if(!name.test(self.val())){
						onError('*请输入格式正确的发证机构',self);
					}else{
						onSuccess(self);
					}
					break;
					default:
						break;
				}
			});
		})
	</script>
</body>
</html>