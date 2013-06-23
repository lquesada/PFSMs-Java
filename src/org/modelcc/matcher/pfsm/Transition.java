package org.modelcc.matcher.pfsm;

import org.modelcc.matcher.Match;
import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Transition {
	/* TODO

    private Character symbol;
    private State targetState;
    private int startIndex;
    private Map<Type,Match> last;

    Transition(Character symbol,State targetState,int startIndex) {
        last = new HashMap<Type,Match>();
        this.symbol = symbol;
        this.targetState = targetState;
        this.startIndex = startIndex;
    }

    Character getSymbol() {
        return symbol;
    }

    State getTargetState() {
        return targetState;
    }

    int getStartIndex() {
        return startIndex;
    }

    void setLasts(Map<Type,Match> lasts) {
        Set<Entry<Type,Match>> entries = lasts.entrySet();
        Iterator<Entry<Type,Match>> ite;
        Entry<Type,Match> e;
        for (ite = entries.iterator();ite.hasNext();) {
            e = ite.next();
            last.put(e.getKey(),e.getValue());
        }
    }

    public Map<Type, Match> getLasts() {
        return last;
    }

    void setLast(Type type,Match match) {
        Match m =  last.get(type);
        if (m != null)
            m.setShadowed(true);
        last.put(type, match);
    }
    void clearLast() {
        Iterator<Match> ite;
        for (ite = last.values().iterator();ite.hasNext();)
            ite.next().setShadowed(true);
        last.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass()!=getClass())
            return false;
        else
            return symbol==((Transition)o).getSymbol()
                &&
               targetState==((Transition)o).getTargetState()
                &&
               startIndex==((Transition)o).getStartIndex();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.symbol != null ? this.symbol.hashCode() : 0);
        hash = 89 * hash + (this.targetState != null ? this.targetState.hashCode() : 0);
        hash = 89 * hash + this.startIndex;
        return hash;
    }

*/    
}
