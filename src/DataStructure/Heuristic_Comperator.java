package DataStructure;
/**
 * This comparator is for the Priority Queue that will be used at the Algorithms
 * It's will create a order of states based on their f(n) and creation 
 * @author Tzion
 */
import java.util.Comparator;

public class Heuristic_Comperator implements Comparator<State_Node> {
	public Heuristic_Comperator() {;}

	/**
	 * a. This comparator is ordering the nodes based on there f(n) in decreasing order for the PQ.
	 * 
	 * b. If the F(n) of both of the nodes is equal, so it will check there id that will say how created
	 * first, and because the creation is based on the following order of directions : --> L,U,R,D
	 * so L will have a id that is less then U or R and so on in every iteration.
	 * So they will ordering by the creation : if at the same iteration they will go out by the order: L,U,R,D,
	 * the id of L is less then U and so on, so they go out by the right order of directions,
	 * and if at different iteration so the previous state will go out first by the id only 
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