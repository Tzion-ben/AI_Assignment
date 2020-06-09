package DataStructure;
/**
 * This comparator is for the Priority Queue that will be used at the Algorithms
 * It's will create a order of states based on their Heuristic Function   
 * @author Tzion
 */
import java.util.Comparator;

public class Heuristic_Comperator implements Comparator<State_Node> {
	public Heuristic_Comperator() {;}

	/**
	 * This 
	 */
	@Override
	public int compare(State_Node o1, State_Node o2) {
		if(o2.getF() == o1.getF()) {

			if(o1.getNodeID() < o2.getNodeID())
				return -1;
		}
		else {
			if(o1.getF() < o2.getF())
				return -1;
		}
		return 1;
	}
} 