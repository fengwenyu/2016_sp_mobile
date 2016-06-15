package com.shangpin.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * https请求
 * 
 * @author ZhuRuiSong
 *
 */
public class HttpsUtil {

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static byte[] post(String url, String content, String charset)
			throws NoSuchAlgorithmException, KeyManagementException,
			IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.setConnectTimeout(3000);
		conn.setReadTimeout(5000);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes(charset));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return outStream.toByteArray();
		}
		return null;
	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param certPath 证书路径
	 * @param password 证书密码
	 * @param url
	 * @param content 
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String certPath, String password, String url,
			String content) throws Exception {
		FileInputStream instream = new FileInputStream(new File(certPath));
		return doPost(instream, password, url, content);
	}
	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param instream 证书路径
	 * @param password 证书密码
	 * @param url
	 * @param content 
	 * @return
	 * @throws Exception
	 */
	public static String doPost(InputStream instream, String password, String url,
			String content) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		FileInputStream instream = new FileInputStream(new File(certPath));
		try {
			keyStore.load(instream, password.toCharArray());
		} finally {
			instream.close();
		}
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, password.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				  .setSocketTimeout(5000)
				  .setConnectTimeout(5000)
				  .setConnectionRequestTimeout(5000)
				  .setStaleConnectionCheckEnabled(true)
				  .build();
		
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.setDefaultRequestConfig(defaultRequestConfig)
				.build();		

		StringBuilder builder = new StringBuilder();
		try {
			HttpPost post = new HttpPost(url);
			StringEntity strEntry = new StringEntity(content, "UTF-8");
			post.setEntity(strEntry);
			post.setHeader("Content-Type", "text/xml;charset=UTF-8");
	
			CloseableHttpResponse response = httpclient.execute(post);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println(response.getStatusLine());
				if (entity != null) {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent()));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						builder.append(text);
					}
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return builder.toString();
	}

	public static void main(String[] args) {

		String httpsUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		try {
			byte[] result = post(httpsUrl, "", "UTF-8");

			System.out.println(new String(result));
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}