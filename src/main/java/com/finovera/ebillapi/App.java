package com.finovera.ebillapi;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.finovera.ebillapi.auth.AuthInfo;

public class App {
	public static void main(final String[] args) {
		final AuthInfo auth = new AuthInfo("http://localhost:8085/spring");
		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("customerCode", "");
		map.add("password", "");

		auth.login(map);
	}
}
