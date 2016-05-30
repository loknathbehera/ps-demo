package com.finovera.ebillapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.platformServices.data.CredentialFieldData;
import com.finovera.platformServices.data.MFA;
import com.finovera.platformServices.data.MFAList;
import com.finovera.platformServices.data.SiteLoginConnectionStatus;
import com.finovera.platformServices.response.FinoveraResponse;
import com.finovera.platformServices.response.ebill.FinoveraResponseCode;
import com.finovera.platformServices.response.ebill.MFAResponse;
import com.finovera.platformServices.response.ebill.MFAResponseList;
import com.finovera.platformServices.response.generic.AddSiteLoginStatusResponse;

public class BaseService {

	AuthInfo authInfo;

	protected RestTemplate template;
	protected HttpHeaders headers;
	protected String url;

	public BaseService(final AuthInfo authInfo) {
		this.authInfo = authInfo;

		template = authInfo.template;
		headers = authInfo.headers;
		url = authInfo.url;
	}

	public static final String USER = "/ps/user/{userId}"; // POST = add user;
															// GET =
															// getuserInfo;
															// DELETE =
															// removeUser
	public static final String PROVIDER = "/ps/provider/{id}";// GET

	public static final String ADD_BILLER_LOGIN = "/ps/user/{userId}/billerlogin/";// POST
	public static final String BILLER_LOGIN_STATUS = "/ps/user/{userId}/billerlogin/{billerloginId}/status";// GET
	public static final String MFA_RESPONSE = "/ps/user/{userId}/billerlogin/{billerloginId}/mfaresponse";// POST
	public static final String CREDENTIALS = "/ps/user/{userId}/billerlogin/{billerloginId}/credentials";// POST
	public static final String BILLER_LOGIN = "/ps/user/{userId}/billerlogin/{billerloginId}"; // POST
																								// =
																								// refresh;
																								// GET
																								// =
																								// getBillerLogin;
	public static final String BILLER = "/ps/biller/";// GET

	// DELETE = deleteBillerLogin
	public static final String GET_BILLER_LOGINS = "/ps/user/{userId}/billerlogin/";// GET
	public static final String GET_EBILLS = "/ps/user/{userId}/ebills";// GET *
	public static final String GET_EBILL_ACCOUNT_INFO = "/ps/user/{userId}/billerlogin/{billerloginId}/account";// GET
	public static final String GET_DOCUMENT = "/ps/user/{userId}/document/{documentId}";// GET
																						// *

	public void poll(final Long siteId, final String token, final String userId) throws Exception {
		final long timeToWait = System.currentTimeMillis() + 5 * 60 * 1000; // 5 minutes
		do {
			final HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);

			final ResponseEntity<AddSiteLoginStatusResponse> respEntity = template.exchange(url
					+ "/ps/user/{userId}/sitelogin/{siteloginId}/{trackingToken}/status", HttpMethod.GET, requestEntity, AddSiteLoginStatusResponse.class,
					userId, siteId, token);
			final AddSiteLoginStatusResponse resp = respEntity.getBody();
			if (resp.getConnectionStatus() == SiteLoginConnectionStatus.COMPLETED) {
				if (resp.getStatusCode() == FinoveraResponseCode.OK) {
					if (resp.getFailures().size() > 0) {
						// log.error("Server reported errors");
						for (final String s : resp.getFailures()) {
							// log.error("Error {}", ExternalErrorCode.getCodeForValue(Integer.parseInt(s)));
						}
						return;
					}

					return;
				}
				if (resp.isMFARequired()) {
					handleMFA(resp.getMfaList(), userId, siteId, resp.getTrackingToken());
				}
			}
			try {
				Thread.sleep(3 * 1000);
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				// log.error("Error", e);

			}
		} while (System.currentTimeMillis() < timeToWait);
		// log.warn("Job did not finish within 5 minutes");
		throw new Exception("Job did not finish within 5 minutes");
	}

	private void handleMFA(final MFAList mfaList, final String userId, final Long siteId, final String trackingToken) throws Exception {

		final MFAResponseList mfaResponseList = new MFAResponseList();
		// mfaResponseList.setApiVersion(this.argsData.apiVersion);
		mfaResponseList.setUserId(userId);
		mfaResponseList.setTrackingToken(trackingToken);
		mfaResponseList.setMfaId(mfaList.getMfaId());
		MFAResponse mfaResponse;
		final List<MFAResponse> mfaResponses = new ArrayList<MFAResponse>();

		for (final MFA mfa : mfaList.getMfas()) {
			switch (mfa.getSecurityChallengeType()) {
			case question:
				mfaResponse = handleQuestion(userId, trackingToken, mfa);
				mfaResponses.add(mfaResponse);
				break;
			case otp:
				mfaResponse = handleOtp(mfa);
				mfaResponses.add(mfaResponse);
				break;
			default:
				throw new Exception("Exceptional case found " + mfa.getSecurityChallengeType());
			}
		}

		// log.info("sending MFA answers with tracking token {} MFA {}", trackingToken, mfaResponseList.getMfaId());
		mfaResponseList.setMfaResponses(mfaResponses.toArray(new MFAResponse[mfaResponses.size()]));
		final HttpEntity<MFAResponseList> mfaResponseListHttpRequest = new HttpEntity<MFAResponseList>(mfaResponseList, headers);
		template.postForEntity(url + "/ps/user/{userId}/sitelogin/{siteloginId}/mfaresponse", mfaResponseListHttpRequest, FinoveraResponse.class, userId,
				siteId);
	}

	private MFAResponse handleOtp(final MFA mfa) throws Exception {
		// // log.info("Received OTP challenge");
		// for (final Entry<String, String> entry : mfa.getDeliveryOptions().entrySet()) {
		// // log.info("{} ({})", entry.getKey(), entry.getValue());
		// System.out.printf("%s: (value:'%s')%n", entry.getKey(), entry.getValue());
		// }
		// final String answer = stdin.readLine();
		final MFAResponse mfaResponse = new MFAResponse();
		// mfaResponse.setMessage(answer);
		// // log.info("Responding with answer {}", answer);
		return mfaResponse;
	}

	private MFAResponse handleQuestion(final String userId, final String trackingToken, final MFA mfa) throws Exception {
		// // log.info("Asking question {}", mfa.getChallenge());
		// System.out.println(mfa.getChallenge());
		// final String answer = stdin.readLine();
		final MFAResponse mfaResponse = new MFAResponse();
		// mfaResponse.setMessage(answer);
		// // log.info("Responding with answer {}", answer);
		return mfaResponse;
	}

	private CredentialFieldData getField(final String name, final String string) {
		final CredentialFieldData cfd = new CredentialFieldData();
		cfd.setName(name);
		cfd.setStringValue(string);
		return cfd;
	}
}
