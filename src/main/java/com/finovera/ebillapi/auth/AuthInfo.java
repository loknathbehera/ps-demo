package com.finovera.ebillapi.auth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.finovera.ebillapi.input.DataManager;

public class AuthInfo extends DataManager {

	public AuthInfo(final Map<String, Object> inputDatas) {
		template = new RestTemplate();
		url = (String) inputDatas.get("URL");
		this.inputDatas = inputDatas;

		login();
	}

	public void login() {
		try {
			final MultiValueMap<String, String> map = (MultiValueMap<String, String>) inputDatas.get("credMap");

			cacheDatas = new HashMap<String, Object>();

			final CustomerLoginData loginData = template.postForObject(url + "/login/ps", map, CustomerLoginData.class);
			if (loginData.status.equals("SUCCESS")) {

				headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("X-FINOVERA-TOKEN", loginData.token);

			} else {
				throw new Exception("Login to finovera Api failed");
			}

		} catch (final Exception e) {
			System.out.println(e);
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
