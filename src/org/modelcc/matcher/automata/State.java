package org.modelcc.matcher.automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class State {

    private Object id;
    
    private boolean finalState;
    
    private Map<Character,ArrayList<State>> transitions;
    
    private boolean isIgnorePattern;
    
    private Integer priority;

    public State() {
    	this(null,false,false,null);
    }

    public State(Object id,boolean finalState) {
    	this(id,finalState,false,null);
    }

    public State(Object id,boolean finalState,boolean isIgnorePattern,Integer priority) {
        transitions = new HashMap<Character,ArrayList<State>>();
        this.id = id;
        this.finalState = finalState;
        this.setIgnorePattern(isIgnorePattern);
        this.setPriority(priority);
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
    
    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState=finalState;
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
        ArrayList<State> aux = transitions.get(symbol);
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

	public boolean isIgnorePattern() {
		return isIgnorePattern;
	}

	public void setIgnorePattern(boolean isIgnorePattern) {
		this.isIgnorePattern = isIgnorePattern;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
