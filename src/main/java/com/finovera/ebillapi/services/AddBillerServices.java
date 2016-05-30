package com.finovera.ebillapi.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.finovera.ebillapi.auth.AuthInfo;
import com.finovera.ebillapi.ui.AddBillerUI;
import com.finovera.platformServices.data.Biller;
import com.finovera.platformServices.request.ebill.AddBillerLoginRequest;
import com.finovera.platformServices.response.ebill.BillerListResponse;
import com.finovera.platformServices.response.ebill.BillerLoginStatusResponse;
import com.finovera.platformServices.response.ebill.FinoveraResponseCode;

public class AddBillerServices extends BaseService {

	public AddBillerServices(final AuthInfo authInfo) {
		super(authInfo);
		// TODO Auto-generated constructor stub
	}

	public Biller getBillerInfo(final String providerImmutableId) {

		final HttpEntity<String> httpRequest = new HttpEntity<String>("", headers);
		final ResponseEntity<BillerListResponse> responseEntity = template.exchange(url + BILLER, HttpMethod.GET, httpRequest, BillerListResponse.class);
		final BillerListResponse response = responseEntity.getBody();

		if (response.getStatusCode().compareTo(FinoveraResponseCode.OK) != 0) {
			return null;
		} else {
			for (final Biller biller : response.getBillers()) {
				if (biller.getImmutableId().equals(providerImmutableId)) {
					authInfo.inputData.put("biller", biller);
					return biller;
				}
			}
		}
		return null;
	}

	public void addBillerLogin(final String providerImmutableId) throws Exception {
		final Biller biller = getBillerInfo(providerImmutableId);

		final AddBillerUI addBillerFrame = new AddBillerUI(authInfo);
		addBillerFrame.setVisible(true);

	}

	public BillerLoginStatusResponse postRequestToAddBillerLogin(final AddBillerLoginRequest req, final String userId) {
		final HttpEntity<AddBillerLoginRequest> httpRequest = new HttpEntity<AddBillerLoginRequest>(req, headers);
		final BillerLoginStatusResponse response = template.postForObject(url + ADD_BILLER_LOGIN, httpRequest, BillerLoginStatusResponse.class, userId);

		try {
			poll(response.getBillerLoginId(), response.getBillerLoginStatusTrackingToken(), userId);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			// log.error("Error", e);

		}

		return response;
	}
}
