package mil.pacom.logcop.jira.poc.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.time.StopWatch;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Worklog;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;


public class RestClientApiPoc {
	
	private static final String USER_NAME = "charles.e.emmons.ctr@navy.mil";	
	private static final String PASSWORD = "logcop@2016!";
	private static final String JIRA_URL = "https://logcop.atlassian.net";
	
	private static final String JQL_SINGLE_ISSUE = "Key=\"PACAF-128\"";
	private static final String JQL_WEEKLY_ISSUES = "workLogDate >= '2017-02-13' and workLogDate <= '2017-02-17'";
	
	private static JiraRestClient jiraClient;
	//private static final String URL = "https://logcop.atlassian.net/rest/api/2/search?jql=key=PACAF-563&fields=id,key,worklog";
		
	
	// configure the proxy we need to connect through
	static {
		//System.getProperties().put("https.proxyHost", "nmciproxyb1");
       // System.getProperties().put("https.proxyPort", "8080");
        System.getProperties().put("https.proxyHost", "nmciproxyb1secure");
        System.getProperties().put("https.proxyPort", "8443");
        System.getProperties().put("https.proxySet", "true");
	}
	
	
	public static void main(String[] args) {
		System.out.println("RestClientApiPoc started....");
		try {
			initializeJiraClient();
			
			testSingleIssueAllFields();
			
			testSingleIssueRequiredFields();
			
			//testAllIssuesRequiredFields();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jiraClient.close();
			} catch (IOException ioe) {
				System.out.println("Unable to close JiraRestClient");
				System.exit(1);
			}
		}
		
		System.out.println("RestClietApiPoc completed...");
	}	
	
	public static void testSingleIssueRequiredFields() throws ExecutionException, InterruptedException {
		System.out.println("\nrunning test for single issue with worklog field...");
		
		Set<String> issueFields = new HashSet<String>();
		issueFields.addAll(Field.getAllRequired());
		issueFields.add(Field.WORKLOG);
		issueFields.add(CustomField.STORY_POINTS);
		issueFields.add(CustomField.ASSIGNED_DEVELOPER);
		issueFields.add(CustomField.PARENT_LINK);
		issueFields.add(CustomField.EPIC_LINK);
		issueFields.add(CustomField.SPRINT);
		
		List<Issue> issues = timedQuery(issueFields, JQL_SINGLE_ISSUE);
		printIssues(issues);
	}
	
	public static void testSingleIssueAllFields() throws ExecutionException, InterruptedException {
		System.out.println("\nrunning test for single issue with all fields...");
		
		Set<String> issueFields = new HashSet<>();
		issueFields.add(Field.ALL);
		
		List<Issue> issues = timedQuery(issueFields, JQL_SINGLE_ISSUE);
		printIssues(issues);
	}
	
	public static void testAllIssuesRequiredFields() throws ExecutionException, InterruptedException {
		System.out.println("\nrunning test for all issues with required fields...");
		
		Set<String> issueFields = new HashSet<>();
		issueFields.addAll(Field.getAllRequired());
		
		timedQuery(issueFields, null);
	}
	
	
	public static List<Issue> timedQuery(Set<String> issueFields, String jql) throws ExecutionException, InterruptedException {
		List<Issue> issues = new ArrayList<Issue>();
		
		StopWatch sw = new StopWatch();
		sw.start();
		try {
			issues = queryJira(issueFields, jql);
		} finally {
			sw.stop();
		}
		System.out.println("Query: " + jql + " returning fields: " + issueFields + " completed in " + sw.getTime() + " milliseconds....");
		System.out.println(issues.size() + " issues returned.");
		
		return issues;
	}
	
	
	public static List<Issue> queryJira(Set<String> issueFields, String jql) throws ExecutionException, InterruptedException {
        List<Issue> issuesList = new ArrayList<Issue>();
		
		        
        SearchResult searchResult = jiraClient.getSearchClient().searchJql(jql, 1000, null, issueFields).get();
        
        Iterable<Issue> iterableIssues = searchResult.getIssues();
        
        issuesList = convertToList(iterableIssues);
        
        int lastIndex = searchResult.getTotal() - 1;
        
        if (lastIndex > searchResult.getMaxResults()) {
        	
        	int startAt = issuesList.size() - 1;
        	while (startAt <= lastIndex) {
        		searchResult = jiraClient.getSearchClient().searchJql(jql, 1000, startAt, issueFields).get();
                
                iterableIssues = searchResult.getIssues();
                
                issuesList.addAll(convertToList(iterableIssues));
                startAt = issuesList.size() - 1;
                System.out.println("startAt = " + startAt);
        	}
        }
        
        return issuesList;
        /*try {
	        Iterator<Issue> it = searchResultPromise.get().getIssues().iterator();
	        while (it.hasNext()) {
	        	Issue issue = it.next();
	        	Promise<Issue> issuePromise = jiraClient.getIssueClient().getIssue(it.next().getKey());
	        	Issue issue = issuePromise.get();
	            System.out.println(issue);       	
	             
	            Iterator<IssueLink> itLink = issuePromise.get().getIssueLinks().iterator();
	            while (itLink.hasNext()) {	                 
	                IssueLink issueLink = itLink.next();
	                Promise<Issue> issueLinkPromise = jiraClient.getIssueClient().getIssue(issueLink.getTargetIssueKey());
	                System.out.println("issue link: " + issueLinkPromise.get());
	            }
	        }
        } catch (ExecutionException ee) {
        	throw new RuntimeException("Unable to retrieve data...", ee);
        } catch (InterruptedException ie) {
        	throw new RuntimeException("Unable to retrieve data...", ie);        	
        }*/
		
	}
	
	private static void printIssues(List<Issue> issues) {
		for (Issue issue : issues) {
			//System.out.println(issue);
        	
        	System.out.println("Issue");
        	System.out.println("-----");
        	System.out.println("key: " + issue.getKey());
        	System.out.println("summary: " + issue.getSummary());
        	System.out.println("type: " + issue.getIssueType().getName());
        	System.out.println("project key: " + issue.getProject().getKey());
        	System.out.println("status: " + issue.getStatus().getName());
        	for (Worklog workLog : issue.getWorklogs()) {
        		System.out.println("worklog:");
        		System.out.println("\tminutes: " + workLog.getMinutesSpent());
	        	System.out.println("\tstartDate: " + workLog.getStartDate());
				
			}
            System.out.println("epic key: " + getEpicLink(issue));
            System.out.println("story points: " + getStoryPoints(issue));
            System.out.println("parent link: " + issue.getField(CustomField.PARENT_LINK));
            System.out.println("assigned developer: " + getAssignedDeveloper(issue));            
            System.out.println("sprints: " + getSprints(issue));
		}
	}
	
	private static List<Sprint> getSprints(Issue issue) {
		List<Sprint> sprintList = new ArrayList<Sprint>();
		
		try {
			JSONArray jsonArray = getJsonArrayValue(issue, CustomField.SPRINT);
			for (int i = 0; i < jsonArray.length(); i++) {
				Object obj = jsonArray.get(i);
				if (obj != null) {
					Sprint sprint = mapSprintObject(obj);
					sprintList.add(sprint);
				}
			}
		} catch (JSONException je) {
			System.err.println("unable to get sprints from issue: " + issue.getKey());
			je.printStackTrace();
		}
		
		return sprintList;
	}
	
	private static Sprint mapSprintObject(Object sprintObj) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		String str = sprintObj.toString();
		String keyValue = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
		StringTokenizer tok = new StringTokenizer(keyValue, ",");
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		while (tok.hasMoreTokens()) {
		    String entString = tok.nextToken();
		    String[] propertyValuePair = entString.split("=");
		    String property = propertyValuePair[0];
		    String value = (propertyValuePair.length == 2) ? propertyValuePair[1] : ""; 
		    map.put(property, value);
		}
		
		Sprint sprint = null;
		
		try {		
			sprint = 
				new Sprint(map.get("id"), 
						map.get("rapidViewId"),
						map.get("state"),
						map.get("name"), 
						map.get("goal"), 
						df.parse(map.get("startDate")), 
						df.parse(map.get("endDate")), 
						df.parse(map.get("completeDate")), 
						map.get("sequence"));
		} catch(ParseException pe) {
			System.out.println("Unable to parse date field...");
			pe.printStackTrace();
		}
		return sprint;
	}
	
	private static String getAssignedDeveloper(Issue issue) {		
		String assignedDeveloper = "";
		
		try {		
			JSONArray jsonArray = getJsonArrayValue(issue, CustomField.ASSIGNED_DEVELOPER);
			JSONObject jsonObject = (jsonArray == null) ? null : jsonArray.getJSONObject(0);
			
			assignedDeveloper = (jsonObject == null) ? "" : jsonObject.getString("value");
		} catch (JSONException je) {
			System.err.println("unable to get assigned developer from issue: " + issue.getKey());
			je.printStackTrace();
		}
		
		return assignedDeveloper;
	}
	
	private static String getEpicLink(Issue issue) {
		return getStringValue(issue, CustomField.EPIC_LINK);
	}
	
	private static Double getStoryPoints(Issue issue) {
		return getDoubleValue(issue, CustomField.STORY_POINTS);
	}
	
	private static JSONArray getJsonArrayValue(Issue issue, String customField) {
		if (issue == null) {
			return null;
		}
		
		IssueField issueField = issue.getField(customField);
		if (issueField == null || issueField.getValue() == null) {
			return null;
		}
		
		return (JSONArray) issueField.getValue();
	}
	
	private static String getStringValue(Issue issue, String customField) {
		if (issue == null) {
			return null;
		}
		
		IssueField issueField = issue.getField(customField);
		if (issueField == null || issueField.getValue() == null) {
			return "";
		}
		
		return (String) issueField.getValue();
	}
	
	private static Double getDoubleValue(Issue issue, String customField) {
		if (issue == null) {
			return null;
		}
		
		IssueField issueField = issue.getField(customField);
		if (issueField == null || issueField.getValue() == null) {
			return null;
		}
		
		return (Double) issueField.getValue();
	}
	
	private static List<Issue> convertToList(Iterable<Issue> iterableIssues) {
		List<Issue> issueList = new ArrayList<Issue>();
		
		for (Issue issue : iterableIssues) {
			issueList.add(issue);
		}
		
		return issueList;
	}
	
	private static void initializeJiraClient() throws URISyntaxException {
		System.out.println("initializing Jira Rest Client API...");
		
		StopWatch sw = new StopWatch();
		sw.start();
		AsynchronousJiraRestClientFactory clientFactory = new AsynchronousJiraRestClientFactory();
		
		URI uri = new URI(JIRA_URL);
		jiraClient = clientFactory.createWithBasicHttpAuthentication(uri, USER_NAME, PASSWORD);
		sw.stop();
		
		System.out.println("Jira Rest Client API initialization completed in " + sw.getTime() + " milliseconds...");
	}

}
