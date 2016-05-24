package com.finovera.ebillapi.auth;

import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class AuthInfo {

	RestTemplate template;

	HttpHeaders headers;

	String apiVer = "1.0";

	public AuthInfo() {
		template = new RestTemplate();
	}

	public void login(final Map<String, Object> inputData) {
		try {
			final MultiValueMap<String, String> map = (MultiValueMap<String, String>) inputData.get("credMap");
			final String url = (String) inputData.get("URL");

			final CustomerLoginData loginData = template.postForObject(url + "/login/ps", map, CustomerLoginData.class);
			if (loginData.status.equals("SUCCESS")) {

				headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("X-FINOVERA-TOKEN", loginData.token);

			} else {

			}

		} catch (final Exception e) {

		}
	}

	public static class CustomerLoginData {

		public String status;
		public String token;
		public long vaidUntil;
	}

	static class UserLoginData {
		public String token;
		public String userId;
		public String name;
		public String zip;
		public String email;
	}

}
