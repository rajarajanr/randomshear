package com.struts2.blog;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;

@SuppressWarnings("unchecked")
public class LoginAction implements SessionAware {
	private String username;
	private String password;
	private String button;
	private Map<String, Object> session;
	private static Map<String, String> loginInfo;

	static {
		try {
			FileInputStream fileIn = new FileInputStream("login.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loginInfo = (HashMap<String, String>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			System.out
					.println("login.ser not found, creating a new login hashmap...");
			loginInfo = new HashMap<String, String>();
			System.out.println("created.");
		} catch (ClassNotFoundException c) {
			System.out.println("unreacheable code reached!");
		}
	}

	public String execute() {
		System.out.println("entering execute");
		System.out.println(this.username);
		System.out.println(this.password);
		System.out.println(this.button);
		if ("login".equalsIgnoreCase(this.button)) {
			return login();
		}
		if ("register".equalsIgnoreCase(this.button)) {
			return register();
		}
		if ("logout".equalsIgnoreCase(this.button)) {
			return logout();
		}

		System.out.println("returning failure from execute");
		return Action.ERROR;
	}

	public String login() {
		System.out.println("entering login");
		if ((this.username != null) && (loginInfo.containsKey(this.username))
				&& (loginInfo.get(this.username).equals(this.password))) {
			System.out.println("returning success");
			session.put("username", username.toUpperCase());
			return Action.SUCCESS;
		}

		System.out.println("returning failure");
		return Action.ERROR;
	}

	public String register() {
		System.out.println("enterign register");
		if ((this.username != null) && (!this.username.isEmpty())
				&& (this.password != null) && (!this.password.isEmpty())) {
			if (loginInfo.containsKey(this.username)) {
				System.out.println("returning failure inside loop");
				return Action.ERROR;
			}
			loginInfo.put(this.username, this.password);
			try {
				FileOutputStream fileOut = new FileOutputStream("login.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(loginInfo);
				out.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
			session.put("username", username.toUpperCase());
			System.out.println("returning success");
			return Action.SUCCESS;
		}
		System.out.println("returning failure");
		return Action.ERROR;
	}

	public String logout() {
		System.out.println("entering logout");
		session.remove("username");
		return "logoutsuccess";
	}

	public String getButton() {
		return this.button;
	}

	public void setButton(String button) {
		this.button = button;
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

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;

	}
}