package com.struts2.blog;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor, SessionAware {

	private Map<String, Object> session;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("intercepting...");
		if (session.get("username") != null) {

			invocation.invoke();
		}
		return "login";
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;

	}

}
