package com.finovera.ebillapi.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.platformServices.request.ebill.RegisterUserRequest;
import com.finovera.platformServices.response.ebill.RegisterUserResponse;
import com.finovera.platformServices.response.ebill.UserResponse;

public class RegisterUserService extends BaseService {

	public RegisterUserService(final AuthInfo authInfo) {
		super(authInfo);
	}

	public RegisterUserResponse registerUser(final String userId) {

		final RegisterUserRequest req = new RegisterUserRequest();
		RegisterUserResponse response = null;
		final HttpEntity<RegisterUserRequest> httpRequest = new HttpEntity<RegisterUserRequest>(req, headers);

		response = template.postForObject(url + USER + "/", httpRequest, RegisterUserResponse.class, userId);

		authInfo.cacheData.put("userId", response.getUserId());

		return response;
	}

	public UserResponse getUserInfo(final String userId) {

		final HttpEntity<String> httpRequest = new HttpEntity<String>("", headers);
		final ResponseEntity<UserResponse> responseEntity = template.exchange(url + USER, HttpMethod.GET, httpRequest, UserResponse.class, userId);
		final UserResponse response = responseEntity.getBody();

		return response;

	}

}
