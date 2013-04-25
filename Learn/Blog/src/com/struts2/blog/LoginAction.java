package com.struts2.blog;

import java.util.HashMap;
import java.util.Map;

public class LoginAction {
	private String username;
	private String password;
	private String button;
	private static Map<String, String> loginInfo = new HashMap<String, String>();

	public String getButton() {
		return this.button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String execute() {
		System.out.println("entering execute");
		System.out.println(this.username);
		System.out.println(this.password);
		System.out.println(this.button);
		if ("login".equalsIgnoreCase(this.button))
			return login();
		if ("register".equalsIgnoreCase(this.button)) {
			return register();
		}
		System.out.println("returning failure from execute");
		return "failure";
	}

	public String login() {
		System.out.println("entering login");
		if ((this.username != null) && (loginInfo.containsKey(this.username))
				&& (loginInfo.get(this.username).equals(this.password))) {
			System.out.println("returning success");
			return "success";
		}

		System.out.println("returning failure");
		return "failure";
	}

	public String register() {
		System.out.println("enterign register");
		if ((this.username != null) && (!this.username.isEmpty())
				&& (this.password != null) && (!this.password.isEmpty())) {
			if (loginInfo.containsKey(this.username)) {
				System.out.println("returning failure inside loop");
				return "failure";
			}
			loginInfo.put(this.username, this.password);
			System.out.println("returning success");
			return "success";
		}
		System.out.println("returning failure");
		return "failure";
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}