package org.modelcc.matcher;

import java.util.ArrayList;
import java.util.List;

import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;
import org.modelcc.matcher.fa.EagerFAMatcher;
import org.modelcc.matcher.pfsm.EagerPFSMMatcher;
import org.modelcc.matcher.pfsm.GreedyPFSMMatcher;

public class MatcherTest {

	public static void main(String[] args) {
        State s0,s1,s2,s3,s4,s5,s6;

        // Matches a+
        s0 = new State(0);
        s1 = new State(1,"word");
        s0.addTransition('a',s1);
        s1.addTransition('a',s1);
        Automaton a = new Automaton(s0);

        List<Automaton> automata = new ArrayList<Automaton>();
        automata.add(a);
        String input = "aaaaa aaa a  aa";
        
        Matcher matcher = new GreedyPFSMMatcher();
        List<Match> matches = matcher.match(input, automata);
        for (int i = 0;i < matches.size();i++) {
        	Match match = matches.get(i);
        	System.out.println("Match in "+match.getStartIndex()+"-"+match.getEndIndex()+" of type "+match.getId());
        }

        System.out.println("Total "+matches.size());
	}
	
}
