package org.modelcc.automata;

public class Automaton {
    private State initialState;

    public Automaton(State initialState) {
        this.initialState = initialState;
    }

    public State getInitialState() {
        return initialState;
    }

}