package mil.pacom.logcop.jira.poc.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;


public class RestClient2Poc {
	
	private static final String USER_NAME = "charles.e.emmons.ctr@navy.mil";
	
	private static final String PASSWORD = "logcop@2016!";
	
	//private static final String URL = "https://logcop.atlassian.net/rest/api/2/search?jql=key=PACAF-563&fields=id,key,worklog";
		
	
	// configure the proxy we need to connect through
	static {
		//System.getProperties().put("https.proxyHost", "nmciproxyb1");
       // System.getProperties().put("https.proxyPort", "8080");
        System.getProperties().put("https.proxyHost", "nmciproxyb1secure");
        System.getProperties().put("https.proxyPort", "8443");
        System.getProperties().put("https.proxySet", "true");
	}
	
	
	public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException {
		//performRequestForUrl(URL);
		performQuerySingleIssue();
		
	}	
	
	public static void performQuerySingleIssue()  {
		AsynchronousJiraRestClientFactory clientFactory = new AsynchronousJiraRestClientFactory();
		
		try {
	        
			URI uri = new URI("https://logcop.atlassian.net");
	        JiraRestClient jiraClient = clientFactory.createWithBasicHttpAuthentication(uri, USER_NAME, PASSWORD);
	        
	        Set<String> issueFields = new HashSet<>();
	        issueFields.add("*all");
	        //Promise<SearchResult> r = jc.getSearchClient().searchJql("Sprint=\"PACAF Sprint 22\"", null, null, set);
	        //Promise<SearchResult> r = jc.getSearchClient().searchJql("Sprint=\"PACAF Sprint 22\"", null, null, set);
	        Promise<SearchResult> searchResultPromise = jiraClient.getSearchClient().searchJql("Key=\"PACAF-563\"", null, null, issueFields);
	        //Promise<SearchResult> r = jc.getSearchClient().
	        
	        Iterator<Issue> it = searchResultPromise.get().getIssues().iterator();
	        while (it.hasNext()) {
	             
	            //Promise<Issue> issue = jc.getIssueClient().getIssue((it.next()).getKey(), null);
	        	Promise<Issue> issuePromise = jiraClient.getIssueClient().getIssue(it.next().getKey());
	        	Issue issue = issuePromise.get();
	            System.out.println(issue); 
	            System.out.println("Epic Key: " + issue.getFieldByName("Epic Link").getValue());
	            //System.out.println("Epic: " + issuePromise.get().getKey() + " " + issuePromise.get().getSummary());
	             
	            Iterator<IssueLink> itLink = issuePromise.get().getIssueLinks().iterator();
	            while (itLink.hasNext()) {
	                 
	                IssueLink issueLink = itLink.next();
	                //Issue issueL = jc.getIssueClient().getIssue((issueLink).getTargetIssueKey(), null);
	                Promise<Issue> issueL = jiraClient.getIssueClient().getIssue(issueLink.getTargetIssueKey());	                 
	            }
	             
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
