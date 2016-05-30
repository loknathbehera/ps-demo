package com.finovera.ebillapi.input;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class InputDataClass extends DataManager {

	public InputDataClass() {
		this.inputData = new HashMap<String, Object>();

		initData();
	}

	public Map<String, Object> getInputData() {
		return inputData;
	}

	public void setInputDatas(final String key, final Object value) {
		inputData.put(key, value);
	}

	private void initData() {
		inputData.put("URL", "http://localhost:8085/spring");

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("customerCode", "fin");
		map.add("password", "Tera kya go hoga Kaliya?");

		inputData.put("credMap", map);

		inputData.put("userId", "ebillApi_testUser432784326");

	}

}
