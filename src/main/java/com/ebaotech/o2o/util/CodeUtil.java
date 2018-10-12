package com.ebaotech.o2o.util;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

/**
 * @author Administrator
 * 2018年9月13日
 */
public class CodeUtil {
	public static boolean getVerifyCode(HttpServletRequest request){
		String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
			return false;
		}
		return true;
	}
}
