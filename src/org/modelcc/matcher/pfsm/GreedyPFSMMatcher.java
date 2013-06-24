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

public class GreedyPFSMMatcher extends Matcher {

	@Override
    public List<Match> match(String input,List<Automaton> automata) {

        Map<Character,ArrayList<Transition>> transitions;
        transitions = new HashMap<Character,ArrayList<Transition>>();
        Set<Object> transdone;
        List<Match> matches = new ArrayList<Match>();
        Character current;
        ArrayList<Transition> cur;
        ArrayList<Transition> eps;
        Iterator<Transition> ite;
        Transition t;
        Match m;
        State s;
        int i;
        boolean finalstate = false;

        for (i = 0;i < input.length();i++) {
            if (finalstate || transitions.isEmpty()) {
            	for (int j = 0;j < automata.size();j++)
            		add(transitions,new Transition('\0',automata.get(j).getInitialState(),i));
            }
            eps = get(transitions,'\0');
            while (!eps.isEmpty()) {
                ite = eps.iterator();
                t = ite.next();
                ite.remove();
                s = apply(matches,transitions,t,i);
                finalstate |= s!=null;
            }
            current = input.charAt(i);
            cur = get(transitions,current);
            transitions.clear();
            transdone = new HashSet<Object>();
            for (ite = cur.iterator();ite.hasNext();) {
                t = ite.next();
                if (!transdone.contains(t.getTargetState().getMatchId())) {
                    s = apply(matches,transitions,t,i);
                    if (s != null) {
                        finalstate = true;
                        transdone.add(s.getMatchId());
                    }
                }
            }

        }

        List<Match> filtered = new ArrayList<Match>();
        for (i = 0;i < matches.size();i++) {
            m = matches.get(i);
            if (!m.isShadowed())
                filtered.add(m);
        }
        return filtered;
     }

    private ArrayList<Transition> get(Map<Character,ArrayList<Transition>> map,Character s) {
        ArrayList<Transition> aux = map.get(s);
        if (aux == null) {
            aux = new ArrayList<Transition>();
            map.put(s, aux);
        }
        return aux;
    }

    private void add(Map<Character,ArrayList<Transition>> map,Transition t) {
        ArrayList<Transition> aux = map.get(t.getSymbol());
        if (aux == null) {
            aux = new ArrayList<Transition>();
            map.put(t.getSymbol(), aux);
        }
        aux.add(t);
    }

    private State apply(List<Match> matches,Map<Character, ArrayList<Transition>> transitions, Transition t,int index) {
        int i;
        Iterator<Character> ite;
        List<State> trs;
        Character symbol;
        Transition tn;
        Match m = null;
        State s = t.getTargetState();
        if (s.isFinalState()) {
            m = new Match(t.getStartIndex(),index,s.getMatchId());
            matches.add(m);
        }
        Set<Character> symbols = s.getTransitionSymbols();
        for (ite = symbols.iterator();ite.hasNext();) {
            symbol = ite.next();
            trs = s.getTransitions(symbol);
            for (i = 0;i < trs.size();i++) {
                tn = new Transition(symbol,trs.get(i),t.getStartIndex());
                tn.setLasts(t.getLasts());
                if (s.isFinalState())
                    tn.setLast(s.getMatchId(),m);
                add(transitions,tn);
            }
        }
        if (s.isFinalState())
            return s;
        else
            return null;
    }
}
