package org.modelcc.matcher.fa;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.Matcher;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

public class EagerFAMatcher extends Matcher {

	@Override
    public List<Match> match(String input,List<Automaton> automata) {
        List<Match> matches = new ArrayList<Match>();
        int i;
        int j;
        for (i = 0;i < input.length();i++) {
            for (j = i;j < input.length();j++) {
                for (int k = 0;k < automata.size();k++) {
                    if (isMatch(input,i,j,automata.get(k))) {
                        matches.add(new Match(i,j,k));
                    }
                }
            }
        }
        return matches;
    }

    public static boolean isMatch(String input,int i,int j,Automaton autm) {
        State q0 = autm.getInitialState();
        while (i<=j) {
            char a = input.charAt(i);
            if (q0.getTransitions(a).size()>0) {
                q0 = q0.getTransitions(a).get(0);
            }
            else
                return false;
            if (a != '\0')
                i++;
        }
        return q0.isFinalState();
    }

}
