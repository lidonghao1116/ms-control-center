<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-证书管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">证书列表页面</a></li>
		<li class="active">证书详情</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="">证书列表</a></li>
		<li class="active"><a href="">证书详情</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal">
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls text-info">报名信息</label>
				</div>
				
				<div class="form-group">
					<div class="form-group form-padding">
						<label class="col-md-2 control-label text-danger">培训学校</label>
						<div class="col-md-2 padding-7">
							<c:if test="${not empty model.schools}">
							${model.schools.schoolName}
							</c:if>
						</div>
						<label class="col-md-2 control-label text-danger">培训课程</label>
						<div class="col-md-2 padding-7">
							<c:if test="${not empty model.learnTypes}">
							${model.learnTypes.typeName}
							</c:if>
						</div>
						<label class="col-md-2 control-label text-danger">鉴定等级</label>
						<div class="col-md-2 padding-7">
							${model.learnTypes.authenticateGradeName}
						</div>
					</div>
				</div>
				<div class="form-group" style="margin-bottom: 5px;">
				  <label class="col-md-2 controls text-info">身份信息</label>
				</div>
				<div class="form-group">
					<div class="form-group form-padding"">
						<label class="col-md-2 control-label text-danger">姓名</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.userName}
						</div>
						
						<label class="col-md-2 control-label text-danger">身份证号</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.certNo}
						</div>
					</div>
					
					<div class="form-group  ">
						<label class="col-md-2 control-label text-danger">手机号码</label>
							<div class="col-md-2 padding-7">
								${model.userInfo.mobile}
							</div>
						<label class="col-md-2 control-label text-danger">年龄</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.age}
						</div>	
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">学历</label>
						<div class="col-md-2 padding-7">
							${fns:getText(3,0,model.userExtend.education)}
						</div>
						<label class="col-md-2 control-label text-danger">性别</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.sex=='1'?"男":"女"}
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">民族</label>
						<div class="col-md-2 padding-7">
							${fns:getText(6,0,model.userExtend.nation)}
						</div>
						<label class="col-md-2 control-label text-danger">籍贯</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.birthplace}
						</div>
						
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label text-danger">户籍地址</label>
						<div class="col-md-2 padding-7">
							${model.userExtend.address}
						</div>
						
					</div>
				</div>
					<div class="form-group" style="margin-bottom: 5px;">
				  		<label class="col-md-2 controls text-info">考证信息</label>
					</div>
					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">考试状态</label>
                    						<div class="col-md-2 padding-7">
                    						    ${model.examClass.examStatusName}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">鉴定等级</label>
                    						<div class="col-md-2 padding-7">
                                                ${model.learnTypes.authenticateGradeName}
                    						</div>
                    						<label class="col-md-2 control-label text-danger">综合成绩</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.comprehensiveScores}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">理论成绩</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.theoryScores}
                    						</div>
                    						<label class="col-md-2 control-label text-danger">能力成绩</label>
                    						<div class="col-md-2 padding-7">
                    						    ${model.userCert.abilityScores}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">技能成绩</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.poScores}
                    						</div>
                    						<label class="col-md-2 control-label text-danger">评定结果</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.examResultText}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">发证日期</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.issuingDate}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">证书编号</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.certificateNo}
                    						</div>
                    					</div>
                    					<div class="form-group">
                    						<label class="col-md-2 control-label text-danger">发证机构</label>
                    						<div class="col-md-2 padding-7">
                    							${model.userCert.certOrgName}
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