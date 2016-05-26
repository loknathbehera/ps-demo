package com.finovera.ebillapi.services;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.finovera.evbillapi.input.DataManager;

public class BaseService extends DataManager {

	public static final String USER = "/ps/user/{userId}"; // POST = add user; GET = getuserInfo; DELETE = removeUser
	public static final String PROVIDER = "/ps/provider/{id}";// GET

	public static final String ADD_BILLER_LOGIN = "/ps/user/{userId}/billerlogin/";// POST
	public static final String BILLER_LOGIN_STATUS = "/ps/user/{userId}/billerlogin/{billerloginId}/status";// GET
	public static final String MFA_RESPONSE = "/ps/user/{userId}/billerlogin/{billerloginId}/mfaresponse";// POST
	public static final String CREDENTIALS = "/ps/user/{userId}/billerlogin/{billerloginId}/credentials";// POST
	public static final String BILLER_LOGIN = "/ps/user/{userId}/billerlogin/{billerloginId}"; // POST = refresh; GET = getBillerLogin;
	// DELETE = deleteBillerLogin
	public static final String GET_BILLER_LOGINS = "/ps/user/{userId}/billerlogin/";// GET
	public static final String GET_EBILLS = "/ps/user/{userId}/ebills";// GET *
	public static final String GET_EBILL_ACCOUNT_INFO = "/ps/user/{userId}/billerlogin/{billerloginId}/account";// GET
	public static final String GET_DOCUMENT = "/ps/user/{userId}/document/{documentId}";// GET *

	protected RestTemplate template;
	protected HttpHeaders headers;
	protected String url;

}
