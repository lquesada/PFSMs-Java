package org.modelcc.matcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;
import org.modelcc.matcher.fa.ExploratoryFAMatcher;
import org.modelcc.matcher.pfsm.ExploratoryPFSMMatcher;
import org.modelcc.matcher.pfsm.GreedyPFSMMatcher;

public class MatcherTest {

	private static void addBulkTransitions(State s0,State s1,String characters) {
		Set<Character> chars = new HashSet<Character>();
		for (int i = 0;i < characters.length();i++)
			chars.add(characters.charAt(i));
		for (int i = 0;i < 255;i++) {
			if (chars.contains(new Character((char)i))) {
				s0.addTransition((char)i,s1);
			}
		}
	}

	private static void addBulkTransitionsExcept(State s0,State s1,String characters) {
		Set<Character> chars = new HashSet<Character>();
		for (int i = 0;i < characters.length();i++)
			chars.add(characters.charAt(i));
		for (int i = 0;i < 255;i++) {
			if (!chars.contains(new Character((char)i))) {
				s0.addTransition((char)i,s1);
			}
		}
	}
	public static void main(String[] args) {
		String az = "abcdefghijklmnopqrstuvwxyz";
		String AZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String n09 = "0123456789";
		String alphanum = az+AZ+n09;
/*		Automaton emailaddress;
		{
			State s0 = new State(0);
			State s1 = new State(1);
			State s2 = new State(2);
			State s3 = new State(3,"email");
			addBulkTransitions(s0,s1,alphanum+".-_+");
			addBulkTransitions(s1,s1,alphanum+".-_+");
			addBulkTransitions(s1,s2,"@");
			addBulkTransitions(s2,s3,alphanum+".-");
			addBulkTransitions(s3,s3,alphanum+".-");
			emailaddress = new Automaton(s0);
		}
		Automaton phonenumber;
		{
			State s0 = new State(0);
			State s1 = new State(1);
			State s2 = new State(2);
			State s3 = new State(3);
			State s4 = new State(4);
			State s5 = new State(5);
			State s6 = new State(6,"phone number");
			addBulkTransitions(s0,s1,"(");
			addBulkTransitions(s1,s2,n09);
			addBulkTransitions(s2,s2,n09);
			addBulkTransitions(s2,s3,")");
			addBulkTransitions(s3,s4," ");
			addBulkTransitions(s4,s5,n09);
			addBulkTransitions(s0,s5,n09);
			addBulkTransitions(s5,s5,n09+"-");
			addBulkTransitions(s5,s6,n09);
			phonenumber = new Automaton(s0);
		}
		Automaton ip;
		{
			State sa0 = new State("a0");
			State sa1 = new State("a1");
			State sa2 = new State("a2");
			State sa3 = new State("a3");

			State sb0 = new State("b0");
			State sb1 = new State("b1");
			State sb2 = new State("b2");
			State sb3 = new State("b3");

			State sc0 = new State("c0");
			State sc1 = new State("c1");
			State sc2 = new State("c2");
			State sc3 = new State("c3");

			State sd0 = new State("d0");
			State sd1 = new State("d1","ip");
			State sd2 = new State("d2","ip");
			State sd3 = new State("d3","ip");

			addBulkTransitions(sa0,sa1,n09);
			addBulkTransitions(sa1,sb0,".");
			addBulkTransitions(sa1,sa2,n09);
			addBulkTransitions(sa2,sb0,".");
			addBulkTransitions(sa2,sa3,n09);
			addBulkTransitions(sa3,sb0,".");

			addBulkTransitions(sb0,sb1,n09);
			addBulkTransitions(sb1,sc0,".");
			addBulkTransitions(sb1,sb2,n09);
			addBulkTransitions(sb2,sc0,".");
			addBulkTransitions(sb2,sb3,n09);
			addBulkTransitions(sb3,sc0,".");

			addBulkTransitions(sc0,sc1,n09);
			addBulkTransitions(sc1,sd0,".");
			addBulkTransitions(sc1,sc2,n09);
			addBulkTransitions(sc2,sd0,".");
			addBulkTransitions(sc2,sc3,n09);
			addBulkTransitions(sc3,sd0,".");

			addBulkTransitions(sd0,sd1,n09);
			addBulkTransitions(sd1,sd2,n09);
			addBulkTransitions(sd2,sd3,n09);

			ip = new Automaton(sa0);
		}
		*/
		Automaton alpha;
		{
			State s0 = new State(0);
			State s1 = new State(1,"alpha");
			addBulkTransitions(s0,s1,az+AZ);
			addBulkTransitions(s1,s1,az+AZ);
			alpha = new Automaton(s0);
		}
		Automaton numeric;
		{
			State s0 = new State(0);
			State s1 = new State(1,"numeric");
			addBulkTransitions(s0,s1,n09);
			addBulkTransitions(s1,s1,n09);
			numeric = new Automaton(s0);
		}
		Automaton alphanumeric;
		{
			State s0 = new State(0);
			State s1 = new State(1,"alphanumeric");
			addBulkTransitions(s0,s1,az+AZ+n09);
			addBulkTransitions(s1,s1,az+AZ+n09);
			alphanumeric = new Automaton(s0);
		}

		List<Automaton> automata = new ArrayList<Automaton>();
        automata.add(alpha);
        automata.add(numeric);
        automata.add(alphanumeric);
        String input = "aaaaa atest@elezeta.comaa a9994141 382.48.82.841.412 aa";

        {
	        Matcher matcher = new ExploratoryFAMatcher();
	        List<Match> matches = matcher.match(input, automata);
	        for (int i = 0;i < matches.size();i++) {
	        	Match match = matches.get(i);
	        	System.out.println("Match in "+match.getStartIndex()+"-"+match.getEndIndex()+" of type "+match.getId()+"  "+input.substring(match.getStartIndex(),match.getEndIndex()+1));
	        }
	
	        System.out.println("Total FA "+matches.size());
        }

        {
	        Matcher matcher = new ExploratoryPFSMMatcher();
	        List<Match> matches = matcher.match(input, automata);
	        for (int i = 0;i < matches.size();i++) {
	        	Match match = matches.get(i);
	        	System.out.println("Match in "+match.getStartIndex()+"-"+match.getEndIndex()+" of type "+match.getId()+"  "+input.substring(match.getStartIndex(),match.getEndIndex()+1));
	        }
	
	        System.out.println("Total PFSM "+matches.size());
        }
	}
	
}
