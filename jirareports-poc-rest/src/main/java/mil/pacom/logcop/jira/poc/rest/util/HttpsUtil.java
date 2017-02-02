package mil.pacom.logcop.jira.poc.rest.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsUtil {
	
	private static final X509TrustManager TRUST_MANAGER = new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] x509Certificates, String s)  throws CertificateException {}
		@Override
		public void checkServerTrusted(X509Certificate[] x509Certificates, String s)  throws CertificateException {}
		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};
	
	public static String getHttpsUrlContents(String urlPath, SSLSocketFactory socketFactory, Map<String, String> requestProperties) {
		byte[] urlBytes = getHttpsUrlBytes(urlPath, socketFactory, requestProperties);
		
		return urlBytes != null ? new String(urlBytes, 0, urlBytes.length) : null;
	}
	
	
	public static byte[] getHttpsUrlBytes(String urlPath, SSLSocketFactory socketFactory, Map<String, String> requestProperties) {
		InputStream inputStream = null;
		
		try {
			// Add a cookie manager to help handle any redirects that the server asks us to do.
			CookieManager cm = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
			CookieHandler.setDefault(cm);
			
			URL url = new URL(urlPath);
			
			URLConnection urlConnection = url.openConnection();
			
			if (urlConnection instanceof HttpsURLConnection) {
				HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) url.openConnection();
				httpsUrlConnection.setSSLSocketFactory(socketFactory);
				
				// Add any request properties.
				if (requestProperties != null && !requestProperties.isEmpty()) {
					Set<Entry<String, String>> entrySet = requestProperties.entrySet();
					for (Entry<String, String> entry : entrySet) {
						httpsUrlConnection.addRequestProperty(entry.getKey(), entry.getValue());
					}
				}
				
				inputStream = httpsUrlConnection.getInputStream();
			}
			else {
				System.out.println("Unrecognized URL Connection type: " + urlConnection);
				return null;
			}
			
			return readInputStream(inputStream);
			
		} 
		catch (IOException e) {
			System.out.println("Failed to get URL contents for: " + urlPath);
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					System.out.println("Encountered error while closing connection stream.");
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
	public static byte[] readInputStream(InputStream inputStream) {
		if (inputStream == null) {
			return null;
		}
		
		BufferedInputStream bis = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			bis = new BufferedInputStream(inputStream);
			byte[] buf = new byte[1024];
			
			int len;
			while ((len = bis.read(buf)) > 0) {
				baos.write(buf, 0, len);
			}
			
			bis.close();
			return baos.toByteArray();
		}
		catch (IOException e) {
			System.out.println("Failed to read input stream.");
			e.printStackTrace();
		}
		finally {
			if (bis != null) {
				try {
					bis.close();
				}
				catch (IOException e) {
					System.out.println("Encountered error while closing connection stream.");
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
	public static SSLSocketFactory createSocketFactoryFromResource(String certificateResourceName, String certificatePassword) {
		return createSocketFactoryFromResource(certificateResourceName, certificatePassword, "TLS");
	}
	
	public static SSLSocketFactory createSocketFactoryFromResource(String certificateResourceName, String certificatePassword, String encryptionAlgorithm) {
		InputStream inputStream = null;
		
		try {
			inputStream = HttpsUtil.class.getClassLoader().getResourceAsStream(certificateResourceName);
			if (inputStream != null) {
				return createSocketFactory(inputStream, certificatePassword, encryptionAlgorithm);
			}
			else {
				return null;
			}
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					System.out.println("Failed to close certificate input stream.");
				}
			}
		}
	}
	
	
	public static SSLSocketFactory createSocketFactory(InputStream certificateFile, String certificatePassword, String encryptionAlgorithm) {
		try {
			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			clientStore.load(certificateFile, certificatePassword.toCharArray());
			
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, certificatePassword.toCharArray());
			KeyManager[] keyManagers = kmf.getKeyManagers();
			
			TrustManager[] trustAllCerts = new TrustManager[] {
					TRUST_MANAGER
			};
			
			SSLContext context = SSLContext.getInstance(encryptionAlgorithm);
			context.init(keyManagers, trustAllCerts, new SecureRandom());
			
			return context.getSocketFactory();
		}
		catch (Exception e) {
			System.out.println("Encountered error while creating socket factory.");
		}
		
		return null;
	}
	
	
}
