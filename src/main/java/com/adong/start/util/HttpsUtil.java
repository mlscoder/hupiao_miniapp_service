package com.adong.start.util;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsUtil {
	/**
	 * 以https方式发送请求并将请求响应内容以String方式返回
	 * 
	 * @param path
	 *            请求路径
	 * @param method
	 *            请求方法
	 * @param body
	 *            请求数据体
	 * @return 请求响应内容转换成字符串信息
	 */
	public static String httpsRequestToString(String path, String method, String body) {
		if (path == null || method == null) {
			return null;
		}
		String response = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection conn = null;
		try {
			// 创建SSLConrext对象，并使用我们指定的信任管理器初始化
			// 我感觉没吊用，但是又不敢删除
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			TrustManager[] tm = { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			} };
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上面对象中得到SSLSocketFactory
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			System.out.println("请求网址：" + path);

			URL url = new URL(path);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			// 设置请求方式（get|post）
			conn.setRequestMethod(method);

			// 有数据提交时
			if (null != body) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(body.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			response = buffer.toString();
		} catch (Exception e) {

			System.out.println(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} catch (IOException execption) {

			}
		}
		return response;
	}

}
