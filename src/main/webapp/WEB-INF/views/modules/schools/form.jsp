<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}-学校管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<ul id="breadcrumb" style="display: none">
		<li><i class="ace-icon fa fa-home home-icon"></i><a href="#">列表页面</a></li>
		<li class="active">学校管理列表</li>
	</ul>
	<ul class="nav nav-tabs padding-12">
		<li><a href="${ctx}/schools/list">学校列表</a></li>
		<li class="active"><a href="${ctx}/schools/form?id=${model.id}">学校${update?'修改':'新增'}</a></li>
	</ul>
	<div class="page-content mt10">
		<div class="page-body">
			<form class="form-horizontal" id="check_m" action="${ctx}/schools/add" enctype="multipart/form-data" method="post">
				 <input name="callbackUrl"   type="hidden" value="" /> 
				<!-- 新增 -->
				<c:if test="${update == 'false'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学校名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schoolName" name="schoolName" maxlength="40" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">所在区域<span class="dr-asterisk">*</span></label>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="province" name="privince">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="city" name="city">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="county" name="area">
								</select>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">详细地址<span class="dr-asterisk">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="schoolAddress" name="schoolAddress" maxlength="90" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">固定电话<span class="dr-asterisk">*</span></label>
							<div class="col-sm-8">
								<input type="text" id="schoolPhone" name="schoolPhone" maxlength="12" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" name="contacts" maxlength="40" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">联系人手机号<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" name="contactPhone" maxlength="11" class="form-control" />
							</div>
						</div>
					</div>	
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">账号信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">管理员账户</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="loginAccount" name="loginAccount"  maxlength="10" class="form-control" />
							</div>
							<label class=" control-label">(默认登陆密码:000000)</label>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">主体信息登记</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">工商名称</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="companyName" name="companyName" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">营业执照注册号</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="licenceNo" name="licenceNo" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">上传营业执照</label>
							<div class="col-sm-4 padding-7">
								<input type="file"  id="licenceImgFile" onchange="upload(this,'licenceImg', 'licenceImgView' )" name="licenceImgFile" value="" />(大小<i style="color: #FF411B;">5M</i>以内)
								<input type="hidden"  id="licenceImg"  name="licenceImg" />
								<span id="licenceImgView"></span>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人姓名</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="agentName" name="agentName" maxlength="18" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人身份证号</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="agentIdNumber" name="agentIdNumber" maxlength="18" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人身份证照片</label>
							<div class="col-sm-4 padding-7">
								<input type="file"  id="idcardFrontImgFile" onchange="upload(this,'idcardFrontImg','idcardFrontImgView')" name="idcardFrontImgFile" value="" />(正面,大小<i style="color: #FF411B;">5M</i>以内)
								<input type="hidden"  id="idcardFrontImg"  name="idcardFrontImg" />
                                <span id="idcardFrontImgView"></span>
							</div>
							<div class="col-sm-4 padding-7">
								<input type="file"  id="idcardBackImgFile" name="idcardBackImgFile" onchange="upload(this,'idcardBackImg','idcardBackImgView')" value="" />(反面,大小<i style="color: #FF411B;">5M</i>以内)
								<input type="hidden"  id="idcardBackImg"  name="idcardBackImg" />
                                <span id="idcardBackImgView"></span>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">销售信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">销售人姓名</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="saler" name="saler" maxlength="18" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-sm-12 text-center">
						<button class="btn btn-sm btn-primary" id="addBtn" type="button" onclick=""><i class="fa fa-floppy-o"></i>保 存</button>
						<button class="btn btn-sm btn-primary" type="button" onclick="history.go(-1)"><i class="fa fa-reply"></i>返回</button>
					</div>
				</c:if>
				</form>
				<!-- 修改 -->
				<form class="form-horizontal" id="checks_s" action="${ctx}/schools/modify" enctype="multipart/form-data" method="post">
					<input id="id" name="id" value="${model.id}" type="hidden"/>
					<input name="callbackUrl"   type="hidden" value="" /> 
				<c:if test="${update == 'true'}">
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">基本信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">创建日期</label>
							<div class="col-sm-4 padding-7">
								<fmt:formatDate value="${model.appltTime}" type="both"/>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">学校名称<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4 padding-7">
								<input type="text" id="schoolName" value="${model.schoolName }" name="schoolName" maxlength="40" class="form-control" />
							</div>
						</div>
						 <div class="form-group form-padding">
							<label class="col-sm-2 control-label">所在区域<span class="dr-asterisk">*</span></label>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="province"  name="privince">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="city"  name="city">
								</select>
							</div>
							<div class="col-sm-2">
								<select class="chosen-select form-control" id="county" name="area">
								</select>
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">详细地址<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schoolAddress" value="${model.schoolAddress }" name="schoolAddress" maxlength="90" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">固定电话<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="schoolPhone" value="${model.schoolPhone }" name="schoolPhone" maxlength="12" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contacts" name="contacts" value="${model.contacts}" maxlength="40" class="form-control" />
							</div>
							<label class="col-sm-2 control-label">联系人手机号<span class="dr-asterisk">*</span></label>
							<div class="col-sm-4">
								<input type="text" id="contactPhone" name="contactPhone" value="${model.contactPhone }" maxlength="11" class="form-control" />
							</div>
						</div>
					</div>	
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">账号信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">管理员账户</label>
							<div class="col-sm-4 padding-7">
			            		${model.sysUsers.loginAccount }
			            		<span><input type="button" value="重置密码" id="btnResetPwd"/></span>
			            		<span id="resetPwdTips"></span>
							</div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">主体信息登记</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">工商名称</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="companyName" name="companyName" value="${model.companyName }" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">营业执照注册号</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="licenceNo" name="licenceNo" value="${model.licenceNo }" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">上传营业执照</label>
							<div class="col-sm-4 padding-7">
                                <input type="file"  id="licenceImgFile" onchange="upload(this,'licenceImg', 'licenceImgView' )" name="licenceImgFile" value="" />(大小<i style="color: #FF411B;">5M</i>以内)
                                <input type="hidden"  id="licenceImg"  name="licenceImg" />
                                <span id="licenceImgView"><a target='_blank' href="${model.licenceImg}"><img alt="营业执照" src="${model.licenceImg}?x-oss-process=style/small"></a></span>
                            </div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人姓名</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="agentName" name="agentName" value="${model.agentName }" maxlength="18" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人身份证号</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="agentIdNumber" name="agentIdNumber" value="${model.agentIdNumber }" maxlength="18" class="form-control" />
							</div>
						</div>
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">代理人身份证照片</label>
							<div class="col-sm-4 padding-7">
                                <input type="file"  id="idcardFrontImgFile" onchange="upload(this,'idcardFrontImg','idcardFrontImgView')" name="idcardFrontImgFile" value="" />(正面,大小<i style="color: #FF411B;">5M</i>以内)
                                <input type="hidden"  id="idcardFrontImg"  name="idcardFrontImg" />
                                <span id="idcardFrontImgView"><a target='_blank' href="${model.idcardFrontImg}"><img alt="身份正面照片" src="${model.idcardFrontImg}?x-oss-process=style/small"></a></span>
                            </div>
                            <div class="col-sm-4 padding-7">
                                <input type="file"  id="idcardBackImgFile" name="idcardBackImgFile" onchange="upload(this,'idcardBackImg','idcardBackImgView')" value="" />(反面,大小<i style="color: #FF411B;">5M</i>以内)
                                <input type="hidden"  id="idcardBackImg"  name="idcardBackImg" />
                                <span id="idcardBackImgView"><a target='_blank' href="${model.idcardBackImg}"><img alt="身份反面照片" src="${model.idcardBackImg}?x-oss-process=style/small"></a></span>
                            </div>
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 5px;">
					  <label class="col-md-2 controls">销售信息</label>
					</div>
					<div class="form-group form-color">
						<div class="form-group form-padding">
							<label class="col-sm-2 control-label">销售人姓名</label>
							<div class="col-sm-4 padding-7">
			            		<input type="text" id="saler" name="saler" value="${model.saler }" maxlength="18" class="form-control" />
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
	<input id="o_province" value="${model.privince}" type="hidden"/>
	<input id="o_city" value="${model.city}" type="hidden"/>
	<input id="o_county" value="${model.area}" type="hidden"/>
	
	<script src="${ctxStatic}/modules/schools/form.js"></script>

	<!-- 初始化页面checkbox -->
	<script type="text/javascript">
		var flag=${update};
		if(flag){
			var boxs=$("#par").val();
			initCheckData(boxs,"applyCourses");
		}
	</script>
	<script type="text/javascript">
		var item=["#province","#city","#county"];
		var itemValue=[$("#o_province").val(),$("#o_city").val(),$("#o_county").val()];
		var defaultOption = '<option value="">---请选择---</option>';
		var flag=true;
		
		function getIndex($item){
			for(var i=0;i<item.length;i++){
				if(item[i]==$item){
					return i;
				}
			}
			return 0;
		}
		
		function createArearSelect(areaData){
			resetSelect(item[0]);
	    	initSelectData(areaData,item[0],itemValue[0]);
	    	var update=${update};
	    	if(update){
	    		$.each(areaData,function(i,param){
		            if(param.areaCode == itemValue[0]){
		                initSelectData(param.childList,$(item[1]),itemValue[1]);
		                bindChange(item[1],param.childList);
		                $.each(param.childList,function(i,obj){
				            if(obj.areaCode == itemValue[1]){
				                initSelectData(obj.childList,$(item[2]),itemValue[2]);
				            }
				        });
		            }
		        });
	    	}
	    	bindChange(item[0],areaData);
		};
	
		function bindChange($item,areaData){
			var index=getIndex($item)+1;
			if(index==item.length){
				flag=false;
		   	}else{
		   		flag=true;
		   	}
			
			if(!flag){
				return;
			}
			var selectGist1 = $(item[index-1]);
			selectGist1.bind('change',function(){
				var $this=$(this).val();
				resetSelect("#"+$(this).attr("id"));
				getDataInfo(areaData,$this,itemValue[index-1],index)
			});
		}
		//获取精确的数据根据arercode
		function getDataInfo(areaData,areaCode,selectValue,index){
			var selectGist2 = $(item[index]);
			$.each(areaData,function(i,param){
	            if(param.areaCode == areaCode){
	                initSelectData(param.childList,selectGist2,selectValue);
	                bindChange(item[index],param.childList);
	                return false;
	            }
	        });
		}
		
		function resetSelect($item){
			for(var i=0;i<item.length;i++){
				if($item==item[i]){
					for(var j=i+1;j<item.length;j++){
						$(item[j]).html("")
						$(item[j]).append(defaultOption);
					}
					 return false;
				}
			}
		};
		
		function initSelectData(areaData,$item,value){
			var selectGist = $($item);
			selectGist.html('');
			selectGist.append(defaultOption);
			if(areaData){
	      		$.each(areaData,function(i,obj){
	      			if(value==obj.areaCode){
	      				$('<option value="'+obj.areaCode+'" selected="selected">'+obj.areaName+'</option>').appendTo(selectGist);
	      			}else{
	      				$('<option value="'+obj.areaCode+'">'+obj.areaName+'</option>').appendTo(selectGist);
	      			}
		      	});
		    }
		}
	  
		function getArearSelect(pCode,item_1,item_2,item_next){
	 		$.post(ctx+'/system/areas/init', {}, function(data){
		  		createArearSelect(data);
	 		});
		};
		
		$(function(){
			getArearSelect();
			//上传图片check 限制大小
			 $("input[type='file']").on("change",function(){
					var fileSize = this.files[0].size;
					var filepath = $(this).val();
					var extStart = filepath.lastIndexOf(".");
					var ext = filepath.substring(extStart, filepath.length).toUpperCase();
					$(this).parents(".upload-sel").nextAll('.prompt_sp').remove();

				});
			//输入check
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
			 var tensPhone = /^[0-9\-]+$/;//固定电话  数字和横杠
			 $("input[type='text']").blur(function(){
					var self = $(this);
					var id = $(this).attr("id");
					switch (id){
					case "schoolName":
						if (self.val() == "") {
							onError('*请填写学校名称',self);
						} else if(!name.test(self.val())){
							onError('*请输入格式正确的学校名称',self);
						}else{
							onSuccess(self);
						}
						break;
					case "schoolAddress":
						if (self.val() == "") {
							onError('*请填写详细地址',self);
						} else if(!name.test(self.val())){
							//onError('*请输入格式正确的详细地址',self);
						}else{
							onSuccess(self);
						}
						break;
					case "schoolPhone":
						if (self.val() == "") {
							onError('*请填写固定电话',self);
						} else if(!tensPhone.test(self.val())){
							onError('*请输入格式正确的固定电话',self);
						}else{
							onSuccess(self);
						}
						break;
					case "contacts":
						if (self.val() == "") {
							onError('*请填写联系人',self);
						} else if(!name.test(self.val())){
							onError('*请输入格式正确的联系人',self);
						}else{
							onSuccess(self);
						}
						break;
					case "contactPhone":
						if (self.val() == "") {
							onError('*请填写联系人手机号',self);
						} else if(!number.test(self.val())){
							onError('*请输入格式正确的联系人手机号',self);
						}else{
							onSuccess(self);
						}
						break;
					case "loginAccount":
						if (self.val() == "") {
							onError('*请填写管理员账号',self);
						} else if(!name.test(self.val())){
							onError('*请输入格式正确的管理员账号',self);
						}else{
							onSuccess(self);
						}
						break;
					case "companyName":
						if (self.val() == "") {
							onError('*请填写工商名称',self);
						} else{
							onSuccess(self);
						}
						break;
					case "licenceNo":
						if (self.val() == "") {
							onError('*请填写营业执照注册号',self);
						} else{
							onSuccess(self);
						}
						break;
					case "agentName":
						if (self.val() == "") {
							onError('*请填写代理人姓名',self);
						} else if(!name.test(self.val())){
							onError('*请输入格式正确的代理人姓名',self);
						}else{
							onSuccess(self);
						}
						break;
					case "agentIdNumber":
						if (self.val() == "") {
							onError('*请填写代理人身份证号',self);
						} else if(!number.test(self.val())){
							//onError('*请输入格式正确的代理人身份证号',self);
						}else{
							onSuccess(self);
						}
						break;
					case "saler":
						 if(self.val()!=""&&!name.test(self.val())){
							onError('*请输入格式正确的销售人姓名',self);
						}else{
							onSuccess(self);
						}
						break;
						default:
							break;
					}
				});
		});

    function upload(fileInput, param, view){
        var data = $(fileInput)[0].files[0];
        var fd = new FormData();
        fd.append('file', data);
        jQuery.ajax({
            url: '${fns:getConfig("upload.url")}',
            data: fd,
            cache: false,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function(data){
                console.log(data.result.url);
                $("#"+param).val(data.result.url);
                $("#"+view).html("<a target='_blank' href='"+data.result.url+"'><img src='"+data.result.url+"?x-oss-process=style/small'></a>");
            }
        });
    }

    </script>

</body>
</html>