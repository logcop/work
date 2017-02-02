package mil.pacom.logcop.jira.poc.rest;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import mil.pacom.logcop.jira.poc.rest.util.HttpsUtil;

/**
 * TODO: Not finished. I copied the methods from Jim's HttpsUtil that I thought would be useful. Not sure if we need the cert or not. Basic Authentication is not implemented yet.
 * @author charles.e.emmons
 *
 */
public class RestClient2Poc {

	private static final String JIRA_CERT = "jira-cert.cer";
	
	private static final String USER_NAME = "charles.e.emmons.ctr@navy.mil";
	
	private static final String PASSWORD = "logcop@2016!";
	
	private static final String URL = "https://logcop.atlassian.net/rest/api/2/search?jql=key=PACAF-563&fields=id,key,worklog";
	
	private static final Map<String, String> REQUEST_PROPERTIES = createRequestPropertiesMap();
	
	private static Map<String, String> createRequestPropertiesMap() {
		Map<String, String> map = new HashMap<>();
		map.put("Accept", "application/json");
		
		return map;
	}
	
	public static String performRequestForUrl(String requestUrl) {
		SSLSocketFactory socketFactory = HttpsUtil.createSocketFactoryFromResource(JIRA_CERT, PASSWORD); // not the cert password, need to do basic authentication....
		
		if (socketFactory == null) {
			System.out.println("Failed to create socket factory.");
			return null;
		}
		
		try {
			String contents = HttpsUtil.getHttpsUrlContents(requestUrl, socketFactory, REQUEST_PROPERTIES);
			if (contents == null) {
				System.out.println("Failed to retrieve data from: " + requestUrl);
				return null;
			}
			return contents;
		}
		catch (Exception e) {
			System.out.println("Error encountered when retrieving data from: " + requestUrl);
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {	
		// make call to performRequestForUrl here.......
		performRequestForUrl(URL);
	}
	
	
	
	/*public static void main(String[] args) {		
		try { 
			String https_url = "https://logcop.atlassian.net/rest/api/2/search?jql=key=PACAF-563&fields=id,key,worklog";
		      URL url;
		      try {

			     url = new URL(https_url);
			     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
			     con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			     con.setRequestProperty("Accept", "application/json");
			     con.setRequestMethod("GET");            

			     //dumpl all cert info
			     print_https_cert(con);

			     //dump all the content
			     print_content(con);

		      } catch (MalformedURLException e) {
			     e.printStackTrace();
		      } catch (IOException e) {
			     e.printStackTrace();
		      }
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private static void print_https_cert(HttpsURLConnection con){

	    if(con!=null){

	      try {

		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Cipher Suite : " + con.getCipherSuite());
		System.out.println("\n");

		Certificate[] certs = con.getServerCertificates();
		for(Certificate cert : certs){
		   System.out.println("Cert Type : " + cert.getType());
		   System.out.println("Cert Hash Code : " + cert.hashCode());
		   System.out.println("Cert Public Key Algorithm : "
	                                    + cert.getPublicKey().getAlgorithm());
		   System.out.println("Cert Public Key Format : "
	                                    + cert.getPublicKey().getFormat());
		   System.out.println("\n");
		}

		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	     }

	   }

	   private static void print_content(HttpsURLConnection con){
		if(con!=null){

		try {

		   System.out.println("****** Content of the URL ********");
		   BufferedReader br =
			new BufferedReader(
				new InputStreamReader(con.getInputStream()));

		   String input;

		   while ((input = br.readLine()) != null){
		      System.out.println(input);
		   }
		   br.close();

		} catch (IOException e) {
		   e.printStackTrace();
		}

	       }

	   }*/


}
