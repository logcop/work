package mil.pacom.logcop.jira.poc.rest;

import java.net.URI;

public class RestClientApiPoc {

	private static final String LET439 = "LET-439";
	
	public static void main(String[] args) {
		try {
		URI uri = new URI("https://logcop.atlassian.net");
		
		String user = "charles.e.emmons.ctr@navy.mil";
		String password = "logcop@2016!";

		/*JiraRestClientFactory restClientFactory = new AsynchronousJiraRestClientFactory();

		JiraRestClient restClient = restClientFactory.createWithBasicHttpAuthentication(uri, user, password);*/
		
		//final NullProgressMonitor pm = new NullProgressMonitor();
		
        //final Promise<Issue> issue = restClient.getIssueClient().getIssue(LET439);
 

        //System.out.println(issue.claim()); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        // now let's vote for it
        /*restClient.getIssueClient().vote(issue.getVotesUri(), pm);
        // now let's watch it
        restClient.getIssueClient().watch(issue.getWatchers().getSelf(), pm);
        // now let's start progress on this issue
        final Iterable<Transition> transitions = restClient.getIssueClient().getTransitions(issue.getTransitionsUri(), pm);
        final Transition startProgressTransition = getTransitionByName(transitions, "Start Progress");
        restClient.getIssueClient().transition(issue.getTransitionsUri(), new TransitionInput(startProgressTransition.getId()), pm);
        // and now let's resolve it as Incomplete
        final Transition resolveIssueTransition = getTransitionByName(transitions, "Resolve Issue");
        Collection<FieldInput> fieldInputs = Arrays.asList(new FieldInput("resolution", "Incomplete"));
        final TransitionInput transitionInput = new TransitionInput(resolveIssueTransition.getId(), fieldInputs, Comment.valueOf("My comment"));
        restClient.getIssueClient().transition(issue.getTransitionsUri(), transitionInput, pm);*/
	}
	
	/*private static Transition getTransitionByName(Iterable<Transition> transitions, String transitionName) {

        for (Transition transition : transitions) {
            if (transition.getName().equals(transitionName)) {
                return transition;
            }

        }

        return null;

    }*/

}
