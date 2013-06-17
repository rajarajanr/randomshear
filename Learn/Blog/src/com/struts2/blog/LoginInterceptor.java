package com.struts2.blog;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor, SessionAware {

	private static final long serialVersionUID = 4269907924617613299L;
	private Map<String, Object> session;
	private final boolean isTrue = true;

	@Override
	public void destroy() {
		System.out.println("Die Interceptor...");

	}

	@Override
	public void init() {
		System.out.println("Happy B'day...");
	}

	@Override
	public String intercept(final ActionInvocation invocation) throws Exception {
		System.out.println("intercepting...Action[" + invocation.getAction()
				+ "]");

		if (invocation.getAction() instanceof LoginAction) {
			System.out
					.println("login/register action detected aborting interceptor...");
		} else if (invocation.getInvocationContext().getSession()
				.get("username") == null) {
			System.out.println("Username ::"
					+ invocation.getInvocationContext().getSession()
							.get("username"));
			return "login";
		}

		// invocation.addPreResultListener(new PreResultListener() {
		//
		// @Override
		// public void beforeResult(ActionInvocation arg0, String arg1) {
		// System.out.println("TEST::"
		// + arg0.getInvocationContext().getSession());
		// if (!arg0.getInvocationContext().getSession().isEmpty()
		// || arg0.getInvocationContext().getSession()
		// .get("username") == null) {
		// System.out.println("Username ::"
		// + invocation.getInvocationContext().getSession()
		// .get("username"));
		// isTrue = false;
		// }
		//
		// }
		// });
		// if (!isTrue) {
		// return "login";
		// }

		String invoke = invocation.invoke();

		System.out.println("Executed??? " + invocation.isExecuted());

		return invoke;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = map;

	}

}
