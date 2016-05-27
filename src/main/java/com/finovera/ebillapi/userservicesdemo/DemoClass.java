package com.finovera.ebillapi.userservicesdemo;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.RegisterUserService;

public class DemoClass {

	AuthInfo loggedIn;
	String userId;

	public DemoClass(final AuthInfo loggedIn) {
		this.loggedIn = loggedIn;
		userId = (String) loggedIn.inputData.get("userId");
	}

	public void registerUser() {
		final RegisterUserService rus = new RegisterUserService(loggedIn);

		rus.registerUser(userId);

		System.out.println(loggedIn.cacheData.get("userId"));

		rus.getUserInfo(userId);
	}
}
