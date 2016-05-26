package com.finovera.evbillapi.input;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class DataManager {

	public Map<String, Object> inputDatas = null;
	public Map<String, Object> cacheDatas = null;

	public RestTemplate template;

	public HttpHeaders headers;

	public final String apiVer = "1.0";

	public String url;

}
