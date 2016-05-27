package com.finovera.ebillapi.userbillersdemo;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.platformServices.data.Biller;

public class AddBillerDemo {

	AuthInfo loggedIn;

	Biller biller;

	public AddBillerDemo(final AuthInfo loggedIn) {
		this.loggedIn = loggedIn;
	}

}
