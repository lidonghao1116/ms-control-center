package com.jiacer.modules.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	
	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}

}
