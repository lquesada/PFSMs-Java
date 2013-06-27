package org.modelcc.matcher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.modelcc.matcher.automata.Automaton;
import org.modelcc.matcher.automata.State;
import org.modelcc.matcher.dfa.ExploratoryDFAMatcher;
import org.modelcc.matcher.dfa.ImprovedExploratoryDFAMatcher;
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

        args = new String[4];
        args[0] = "/home/elezeta/testfile";
        args[1] = "0";
        args[2] = "16384";
        args[3] = "1048576";

        String input = null;
		try {
			input = readFile(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		int minSize = Integer.parseInt(args[1]);
		int increment = Integer.parseInt(args[2]);
		int maxSize = Integer.parseInt(args[3]);
		
		if (maxSize>input.length()) {
			System.out.println("MAXSIZE IS GREATER THAN FILE LENGTH.");
			System.exit(-1);
		}
		int a1;
		int a2;
		int a3;
		int a4;
		//tryMatcher("SETUP","SETUP1",new ImprovedExploratoryDFAMatcher(),input.substring(0,1048576),automata);
		//tryMatcher("SETUP","SETUP2",new ImprovedExploratoryDFAMatcher(),input.substring(0,1048),automata);
		//tryMatcher("SETUP","SETUP3",new ImprovedExploratoryDFAMatcher(),input.substring(0,1048),automata);

		for (int i = minSize;i <= maxSize;i+=increment) {
			String process = input.substring(0,i);
			
			a1 = tryMatcher("EXDFA",""+i,new ExploratoryDFAMatcher(),process,automata);
			//a2 = tryMatcher("IEDFA",""+i,new ImprovedExploratoryDFAMatcher(),process,automata);
			a2 = tryMatcher("EPFSM",""+i,new ExploratoryPFSMMatcher(),process,automata);

	        if (a1 != a2) {
	        	System.out.println("ERROR");
	        	System.exit(-1);
	        }
		}
	}
	
	private static int tryMatcher(String id,String id2,Matcher matcher,String input,List<Automaton> automata) {
		List<Match> matches = null;
		long startTime = System.nanoTime();
    	matches = matcher.match(input, automata);
        long endTime = System.nanoTime();
        System.out.println(id+" "+id2+"\t"+(endTime-startTime));
        return matches.size();
	}

	private static String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader( new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    return stringBuilder.toString();
	}
	
}
