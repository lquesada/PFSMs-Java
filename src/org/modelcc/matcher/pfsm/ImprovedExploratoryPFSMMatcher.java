package org.modelcc.matcher.pfsm;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.Matcher;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImprovedExploratoryPFSMMatcher extends Matcher {

    @Override
    public List<Match> match(String input,List<Automaton> automata) {

        List<Match> matches = new ArrayList<Match>();
        for (int i = 0;i < input.length();i++)
        	for (int j = 0;j < automata.size();j++)
        		apply(matches,automata.get(j).getInitialState(),i,i,'\0',input.charAt(i));
        return matches;
     }


    private void apply(List<Match> matches,State state,int start,int index,Character current,Character currentInput) {
    	if (state.isFinalState())
    		matches.add(new Match(start,index,state.getMatchId()));
    	for (Iterator<State> i = state.getTransitions('\0').iterator();i.hasNext();)
    		apply(matches,i.next(),start,index,current,currentInput);
    	for (Iterator<State> i = state.getTransitions(currentInput).iterator();i.hasNext();)
    		apply(matches,i.next(),start,index+1,currentInput,currentInput);
    }

}
