package com.finovera.ebillapi.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class AuthInfo {

	RestTemplate template;

	HttpHeaders headers;

	String apiVer = "1.0";

	public void init(final RestTemplate template, final HttpHeaders headers) {
		this.template = template;
		this.headers = headers;
		headers.add("X-FINOVERA-VERSION", apiVer);
	}

}
