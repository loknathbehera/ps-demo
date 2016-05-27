package com.finovera.ebillapi;

import java.util.Map;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.demo.DemoClass;
import com.finovera.ebillapi.input.InputData;

public class App {
	public static void main(final String[] args) {

		final Map<String, Object> inputData = initializedInputData();

		final AuthInfo loggedIn = new AuthInfo(inputData);

		DemoClass dc = new DemoClass(loggedIn);
		dc.registerUser("ebill_Api_test_user");

	}

	private static Map<String, Object> initializedInputData() {
		final InputData inputData = new InputData();

		return inputData.getInputData();
	}
}
