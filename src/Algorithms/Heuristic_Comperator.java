package Algorithms;
/**
 * This comparator is for the Priority Queue that will be used at the Algorithms
 * It's will create a order of states based on their Heuristic Function   
 * @author Tzion
 */
import java.util.Comparator;
import DataStructure.State_Node;

public class Heuristic_Comperator implements Comparator<State_Node> {
	public Heuristic_Comperator() {;}
	@Override
	public int compare(State_Node o1, State_Node o2) {
		int F=o1.getF()-o2.getF();
		return F;
	}
}