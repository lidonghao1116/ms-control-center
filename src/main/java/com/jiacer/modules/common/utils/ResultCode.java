package com.jiacer.modules.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description: 响应返回码
 * @author hzp
 * @date 2016-5-11
 *
 */
public class ResultCode {

	public static final Integer SUCCESS = 0;
	public static final Integer ERROR = 999999;
	public static final Integer SESSION_TIMEOUT = 999010;
	public static final Integer PARAMS_ERROR = 999011;
	public static final Integer DATA_ERROR = 999012;
	public static final Integer ILLEGAL_REQUEST = 999013;
	public static final Integer AUTHORIZE_ERROR = 999014;
	public static final Integer DATA_IS_NULL = 999015;
	public static final Integer SMSCODE_TIMEOUT = 999016;
	

	

	

	
	public static class Function {
		public static final Integer LOGIN_DISABLED = 500001;
	}

	public static class schools {
		public static final Integer LOGIN_ACCOUNT_EXIT = 600001;
	}

	
	
	
	
	public static final String getMsg(Integer code) {
		return resultMsg.get(code);
	}

	private static Map<Integer, String> resultMsg;

	static {
		resultMsg = new HashMap<Integer, String>();
		resultMsg.put(SUCCESS, "成功");
		resultMsg.put(ERROR, "系统错误");
		resultMsg.put(SESSION_TIMEOUT, "登陆超时");
		resultMsg.put(PARAMS_ERROR, "参数错误");
		resultMsg.put(DATA_ERROR, "数据异常");
		resultMsg.put(ILLEGAL_REQUEST, "非法请求");
		resultMsg.put(AUTHORIZE_ERROR, "授权失败");
		resultMsg.put(DATA_IS_NULL, "获取数据为空");
		resultMsg.put(Function.LOGIN_DISABLED, "无法登陆");

		resultMsg.put(schools.LOGIN_ACCOUNT_EXIT, "该管理员账号已存在");
		
	}
}
