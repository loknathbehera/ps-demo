package com.finovera.ebillapi.auth;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.finovera.ebillApi.ui.AddBillerFrame;

public class AuthInfo {

	RestTemplate template;

	HttpHeaders headers;

	String apiVer = "1.0";

	String url;

	public AuthInfo(final String url) {
		template = new RestTemplate();
		this.url = url;
	}

	public void login(final MultiValueMap<String, String> credMap) {
		try {

			final CustomerLoginData loginData = template.postForObject(url + "/login/ps", credMap, CustomerLoginData.class);
			if (loginData.status.equals("SUCCESS")) {

				headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.add("X-FINOVERA-TOKEN", loginData.token);

				final AddBillerFrame addBiller = new AddBillerFrame();
				addBiller.addBillerPage();
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
