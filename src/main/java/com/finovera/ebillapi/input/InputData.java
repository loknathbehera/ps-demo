package com.finovera.ebillapi.input;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class InputData extends DataManager {

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
		inputDatas.put("URL", "https://sandbox.finovera.com/cabinet");

		final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("customerCode", "fin");
		map.add("password", "Tera kya go hoga Kaliya?");

		inputDatas.put("credMap", map);

		inputDatas.put("userId", "ebillApi_testUser2131312321");

	}

}
