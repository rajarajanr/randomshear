package com.struts2.blog;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class DashboardAction implements SessionAware {
	String username;
	private Map<String, Object> session;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;
	}

	public String dashboard() {
		this.username = (String) session.get("username");
		return "success";
	}
}
