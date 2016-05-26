package com.finovera.ebillapi.input;

import java.util.ArrayList;
import java.util.List;

import com.finovera.platformServices.data.Biller;

public class CacheData extends DataManager {

	public CacheData() {
		// TODO Auto-generated constructor stub
	}

	List<Biller> billers = new ArrayList<Biller>();

	public Biller getBillerByImmuitalbeId(final String immutableId) {

		for (final Biller biller : billers) {
			if (biller.getImmutableId().equals(immutableId)) {
				return biller;
			}
		}

		return null;

	}
}
