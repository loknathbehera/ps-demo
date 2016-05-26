package com.finovera.ebillapi;

import java.util.Map;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.evbillapi.input.InputData;

public class App {
	public static void main(final String[] args) {

		final Map<String, Object> inputData = initializedInputData();

		final AuthInfo loggedIn = new AuthInfo(inputData);

	}

	private static Map<String, Object> initializedInputData() {
		final InputData inputData = new InputData();

		return inputData.getInputData();
	}
}
