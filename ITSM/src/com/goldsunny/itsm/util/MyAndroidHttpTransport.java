package com.goldsunny.itsm.util;

import java.io.IOException;

import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.ServiceConnectionSE;

public class MyAndroidHttpTransport extends HttpTransportSE {
	private int timeout = 20000; // 默认超时时间为20s

	public MyAndroidHttpTransport(String url) {
		super(url);
	}

	public MyAndroidHttpTransport(String url, int timeout) {
		super(url);
		this.timeout = timeout;
	}

	// 尤其注意此方法
	protected ServiceConnection getServiceConnection() throws IOException {
		ServiceConnectionSE serviceConnection = new  ServiceConnectionSE(this.url,
				timeout);
		return serviceConnection;
	}

	
}