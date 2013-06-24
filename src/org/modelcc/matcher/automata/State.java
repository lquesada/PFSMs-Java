package org.modelcc.matcher.automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class State {

    private Object id;
    
    private Map<Character,List<State>> transitions;

    private Object matchId;
    
    public State() {
    	this(null,null);
    }

    public State(Object id) {
    	this(id,null);
    }

    public State(Object id,Object matchId) {
    	this.id = id;
    	this.matchId = matchId;
    	this.transitions = new HashMap<Character,List<State>>();
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
    
    public boolean isFinalState() {
        return (matchId!=null);
    }

    public void setMatchId(Object matchId) {
        this.matchId=matchId;
    }

    public Object getMatchId() {
    	return matchId;
    }

    public List<State> getTransitions(Character symbol) {
        List<State> aux = transitions.get(symbol);
        if (aux == null) {
            aux = new ArrayList<State>();
        }
        return Collections.unmodifiableList(aux);
    }

    public Set<Character> getTransitionSymbols() {
        return Collections.unmodifiableSet(transitions.keySet());
    }

    public void addTransition(Character symbol,State target) {
        List<State> aux = transitions.get(symbol);
        if (aux == null) {
            aux = new ArrayList<State>();
            transitions.put(symbol,aux);
        }
        aux.add(target);
    }

    public void removeTransition(Character symbol,State target) {
    	if (transitions.get(symbol) != null)
    		transitions.get(symbol).remove(target);
    }

}
