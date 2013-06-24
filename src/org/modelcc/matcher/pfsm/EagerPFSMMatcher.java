package org.modelcc.matcher.pfsm;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.Matcher;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EagerPFSMMatcher extends Matcher {

    @Override
    public List<Match> match(String input,List<Automaton> automata) {

        Map<Object,HashSet<Transition>> transitions;
        transitions = new HashMap<Object,HashSet<Transition>>();
        List<Match> matches = new ArrayList<Match>();
        Character current;
        HashSet<Transition> cur;
        HashSet<Transition> eps;
        Iterator<Transition> ite;
        Transition t;
        int i;

        for (i = 0;i < input.length();i++) {
        	for (int j = 0;j < automata.size();j++)
        		add(transitions,new Transition('\0',automata.get(j).getInitialState(),i));
            eps = get(transitions,'\0');
            while (!eps.isEmpty()) {
                ite = eps.iterator();
                t = ite.next();
                ite.remove();
                apply(matches,transitions,t,i);
            }
            current = input.charAt(i);
            cur = get(transitions,current);
            transitions.clear();
            for (ite = cur.iterator();ite.hasNext();)
                apply(matches,transitions,ite.next(),i);

        }
        return matches;
     }

    private HashSet<Transition> get(Map<Object,HashSet<Transition>> map,Character s) {
        HashSet<Transition> aux = map.get(s);
        if (aux == null) {
            aux = new HashSet<Transition>();
            map.put(s, aux);
        }
        return aux;
    }

    private void add(Map<Object,HashSet<Transition>> map,Transition t) {
        HashSet<Transition> aux = map.get(t.getSymbol());
        if (aux == null) {
            aux = new HashSet<Transition>();
            map.put(t.getSymbol(), aux);
        }
        aux.add(t);
    }

    private void apply(List<Match> matches,Map<Object, HashSet<Transition>> transitions, Transition t,int index) {
        int i;
        Iterator<Character> ite;
        List<State> trs;
        Character symbol;
        State s = t.getTargetState();
        if (s.isFinalState())
            matches.add(new Match(t.getStartIndex(),index,s.getMatchId()));
        Set<Character> symbols = s.getTransitionSymbols();
        for (ite = symbols.iterator();ite.hasNext();) {
            symbol = ite.next();
            trs = s.getTransitions(symbol);
            for (i = 0;i < trs.size();i++)
                add(transitions,new Transition(symbol,trs.get(i),t.getStartIndex()));
        }
    }
}
