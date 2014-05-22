package com.goldsunny.itsm.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;

public class NFCHelper {
	private NfcAdapter mAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private Activity mActivity;
	private String[][] mTechLists;

	public NFCHelper(Activity activity) {

		try {
			mActivity = activity;
			mAdapter = NfcAdapter.getDefaultAdapter(activity);
			mPendingIntent = PendingIntent.getActivity(activity, 0,
					new Intent(mActivity.getBaseContext(), mActivity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

			// Setup an intent filter for all MIME based dispatches
			IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
			try {
				ndef.addDataType("*/*");
			} catch (MalformedMimeTypeException e) {
				// throw new RuntimeException("fail", e);
			}
			mFilters = new IntentFilter[] { ndef, };
			mTechLists = new String[][] { new String[] { MifareClassic.class.getName() } };
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void enableForegroundDispatch() {
		if (mAdapter == null)
			return;
		mAdapter.enableForegroundDispatch(mActivity, mPendingIntent, mFilters, mTechLists);
	}

	public void disableForegroundDispatch() {
		if (mAdapter == null)
			return;
		mAdapter.disableForegroundDispatch(mActivity);
	}
}
