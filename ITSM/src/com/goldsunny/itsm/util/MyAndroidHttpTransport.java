package com.goldsunny.itsm.util;

import java.io.IOException;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;

public class MyAndroidHttpTransport extends HttpTransportSE {
	private int timeout = 20000; // Ĭ�ϳ�ʱʱ��Ϊ20s

	public MyAndroidHttpTransport(String url) {
		super(url);
	}

	public MyAndroidHttpTransport(String url, int timeout) {
		super(url);
		this.timeout = timeout;
	}

	// ����ע��˷���
	protected ServiceConnection getServiceConnection() throws IOException {
		ServiceConnectionSE serviceConnection = new  ServiceConnectionSE(this.url,
				timeout);
		return serviceConnection;
	}

	
}