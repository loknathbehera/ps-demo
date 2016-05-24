package com.finovera.evbillapi.input;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class InputData extends DataManager {
	private final Map<String, Object> inputDatas;

	public InputData() {
		this.inputDatas = new HashMap<String, Object>();

		initData();
	}

	public Map<String, Object> getInputData() {
		return inputDatas;
	}

	public void setInputDatas(final String key, final Object value) {
		inputDatas.put(key, value);
	}

	private void initData() {
		inputDatas.put("URL", "http://localhost:8085/spring");

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("customerCode", "fin");
		map.add("password", "Tera kya go hoga Kaliya?");

		inputDatas.put("credMap", map);

	}

}
