package com.finovera.ebillapi.userservicesdemo;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.RegisterUserService;

public class DemoClass {

	AuthInfo loggedIn;

	public DemoClass(final AuthInfo loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void registerUser() {
		final RegisterUserService rus = new RegisterUserService(loggedIn);

		rus.registerUser((String) loggedIn.inputData.get("userId"));

		System.out.println(loggedIn.cacheData.get("userId"));

		rus.getUserInfo((String) loggedIn.inputData.get("userId"));
	}
}
