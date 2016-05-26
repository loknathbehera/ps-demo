package com.finovera.ebillapi.services;

import org.springframework.http.HttpEntity;

import com.finovera.platformServices.request.ebill.RegisterUserRequest;
import com.finovera.platformServices.response.ebill.RegisterUserResponse;

public class RegisterUserService extends BaseService {

	public RegisterUserResponse registerUser(final String userId) {
		final RegisterUserRequest req = new RegisterUserRequest();
		RegisterUserResponse response = null;
		final HttpEntity<RegisterUserRequest> httpRequest = new HttpEntity<RegisterUserRequest>(req, headers);

		response = template.getForObject(userId, RegisterUserResponse.class, url + USER + "/");

		if (response == null) {

			response = template.postForObject(url + USER + "/", httpRequest, RegisterUserResponse.class, userId);
		}

		return response;
	}
}
