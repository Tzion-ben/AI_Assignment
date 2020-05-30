package Algorithms;
/**
 * 
 * @author Tzion
 */
import java.util.Comparator;
import DataStructure.State_Node;

public class Huristic_Comperator implements Comparator<State_Node> {
	public Huristic_Comperator() {;}
	@Override
	public int compare(State_Node o1, State_Node o2) {
		int pr=o2.getH()-o1.getH();
		return pr;
	}
}