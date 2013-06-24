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

    private Character symbol;
    private State targetState;
    private int startIndex;
    private Map<Object,Match> last;

    Transition(Character symbol,State targetState,int startIndex) {
        last = new HashMap<Object,Match>();
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

    void setLasts(Map<Object,Match> lasts) {
        Set<Entry<Object,Match>> entries = lasts.entrySet();
        Iterator<Entry<Object,Match>> ite;
        Entry<Object,Match> e;
        for (ite = entries.iterator();ite.hasNext();) {
            e = ite.next();
            last.put(e.getKey(),e.getValue());
        }
    }

    public Map<Object, Match> getLasts() {
        return last;
    }

    void setLast(Object type,Match match) {
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

}
