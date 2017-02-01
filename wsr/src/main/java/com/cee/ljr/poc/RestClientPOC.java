package com.cee.ljr.poc;


public class RestClientPOC {

	public static void main(String[] args) {
		final JiraRestClientFactory factory = new JerseyJiraRestClientFactory();

        final URI jiraServerUri = new URI("http://localhost:8090/jira");

        final JiraRestClient restClient = JiraRestClientFactory.createWithBasicHttpAuthentication(jiraServerUri, "yourusername", "yourpassword");

        //final NullProgressMonitor pm = new NullProgressMonitor();

        final Issue issue = restClient.getIssueClient().getIssue("TST-1", pm);

 

        System.out.println(issue);
	  }
	
	

}
