package org.modelcc.matcher.pfsm;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DirectTransition {

    private State targetState;
    private int startIndex;

    DirectTransition(State targetState,int startIndex) {
        this.targetState = targetState;
        this.startIndex = startIndex;
    }

    State getTargetState() {
        return targetState;
    }

    int getStartIndex() {
        return startIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass()!=getClass())
            return false;
        else
              return targetState==((DirectTransition)o).getTargetState()
                &&
               startIndex==((DirectTransition)o).getStartIndex();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.targetState != null ? this.targetState.hashCode() : 0);
        hash = 89 * hash + this.startIndex;
        return hash;
    }

}
