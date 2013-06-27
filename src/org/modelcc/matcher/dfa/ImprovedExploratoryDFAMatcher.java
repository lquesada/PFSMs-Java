package org.modelcc.matcher.dfa;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.Matcher;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

public class ImprovedExploratoryDFAMatcher extends Matcher {

	@Override
    public List<Match> match(String input,List<Automaton> automata) {
        List<Match> matches = new ArrayList<Match>();
        for (int i = 0;i < input.length();i++) {
            for (int k = 0;k < automata.size();k++) {
                matchFront(input,i,automata.get(k),matches);
            }
        }
        return matches;
    }

    private void matchFront(String input, int i, Automaton automaton,List<Match> matches) {
        int initial = i;
    	State q0 = automaton.getInitialState();
        while (i < input.length()) {
	        char a = input.charAt(i);
	        if (q0.getTransitions(a).size()>0) {
	            q0 = q0.getTransitions(a).get(0);
	            if (q0.isFinalState())
	            	matches.add(new Match(initial,i,q0.getMatchId()));
	        }
	        else
	            return;
	        if (a != '\0')
	            i++;
        }
	}

}
