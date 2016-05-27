package com.finovera.ebillapi;

import java.util.Map;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.input.InputDataClass;
import com.finovera.ebillapi.userbillersdemo.AddBillerDemo;
import com.finovera.ebillapi.userservicesdemo.DemoClass;

public class App {
	public static void main(final String[] args) throws Exception {

		final Map<String, Object> inputData = initializedInputData();

		final AuthInfo loggedIn = new AuthInfo(inputData);

		final DemoClass dc = new DemoClass(loggedIn);
		dc.registerUser();

		final AddBillerDemo a = new AddBillerDemo(loggedIn);
		a.addBiller();

	}

	private static Map<String, Object> initializedInputData() {
		final InputDataClass inputData = new InputDataClass();

		return inputData.getInputData();
	}
}
