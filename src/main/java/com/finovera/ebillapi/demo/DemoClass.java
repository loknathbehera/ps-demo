package com.finovera.ebillapi.demo;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.RegisterUserService;

public class DemoClass {

	AuthInfo loggedIn;

	public DemoClass(AuthInfo loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void registerUser(String userId) {
		RegisterUserService rus = new RegisterUserService(loggedIn);
		rus.registerUser(userId);
	}

}
