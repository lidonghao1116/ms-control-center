package com.jiacer.modules.system.controller;

import com.jiacer.modules.common.config.Global;
import com.jiacer.modules.common.controller.BaseController;
import com.jiacer.modules.common.utils.EntryptUtils;
import com.jiacer.modules.common.utils.StringUtils;
import com.jiacer.modules.mybatis.entity.SysUsersEntity;
import com.jiacer.modules.system.config.MappingURL;
import com.jiacer.modules.system.config.SysConstants;
import com.jiacer.modules.system.security.FormAuthenticationFilter;
import com.jiacer.modules.system.security.SystemAuthorizingRealm.Principal;
import com.jiacer.modules.system.service.SystemService;
import com.jiacer.modules.system.utils.UserUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginControlller extends BaseController{

	@Autowired
	SystemService systemService;

	/**
	 * 管理登录
	 */
	@RequestMapping(value = MappingURL.LOGIN_URL, method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}
		model.addAttribute("captcha", Global.getConfig(SysConstants.CAPTCHA));
		return "login";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = MappingURL.LOGIN_URL, method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		// 如果已经登录，则跳转到管理首页
		if(principal != null){
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		String exception = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
		
		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);
		model.addAttribute("captcha", Global.getConfig(SysConstants.CAPTCHA));
		
		return "login";
	}


	@RequestMapping(value = MappingURL.MODIFY_PWD_URL, method = RequestMethod.POST)
	@ResponseBody
	public String modifyPwd(SysUserForm form) throws Exception {
		Principal principal = UserUtils.getPrincipal();
		// 如果已经登录，则跳转到管理首页
		if(principal == null){
			return "用户登陆信息失效，请重新登陆";
		}
		if(form == null || StringUtils.isBlank(form.getNewPassword()) || !form.getNewPassword().equals(form.getRepeatPassword())){
			return "两次密码输入不一致";
		}
		SysUsersEntity user = systemService.getUseById(principal.getId());
		if(!EntryptUtils.validatePassword(form.getOldPassword(), user.getPassword())){
				return "原始密码输入错误";
		}
		systemService.modifyPasswordById(principal.getId(), form.getNewPassword());
		return "修改成功";

	}
}
