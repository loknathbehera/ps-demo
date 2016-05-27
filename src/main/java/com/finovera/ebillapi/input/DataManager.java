package com.finovera.ebillapi.input;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class DataManager {

	public Map<String, Object> inputData = null;
	public Map<String, Object> cacheData = null;

	public RestTemplate template;

	public HttpHeaders headers;

	public final String apiVer = "1.0";

	public String url;

}
