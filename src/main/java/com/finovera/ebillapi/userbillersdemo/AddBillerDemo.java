package com.finovera.ebillapi.userbillersdemo;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.services.AddBillerServices;
import com.finovera.ebillapi.ui.AddBillerUI;
import com.finovera.platformServices.data.Biller;

public class AddBillerDemo {

	AuthInfo loggedIn;

	Biller biller;

	public AddBillerDemo(final AuthInfo loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void addBiller() throws Exception {
		final AddBillerServices addBillerServices = new AddBillerServices(loggedIn);

		addBillerServices.addBillerLogin("11c4a259-8b03-484f-b01f-75b80fd90039");
	}

	public void addBillerThrowUI() throws Exception {
		final AddBillerUI addBillerServices = new AddBillerUI(loggedIn);

		addBillerServices.setVisible(true);
	}

}
