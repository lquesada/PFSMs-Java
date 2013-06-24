package org.modelcc.matcher;

import java.util.List;

import org.modelcc.matcher.automata.Automaton;

public abstract class Matcher {

	public abstract List<Match> match(String input,List<Automaton> automaton);
	
}
