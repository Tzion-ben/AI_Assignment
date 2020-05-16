package DataStructure;
/**
 * This interface represents the set of operations applicable on a 
 * State (vertex/node) in the solution search graph.
 * @author Tzion
 */
public interface State_Data extends Vertex_Colors{
	/**
	 * Return the id associated with this node.
	 * The id is an integer number, for every state it is a different id
	 * @return
	 */
	public int getStateId();

	/**
	 * 
	 * @return The matrix associated with the State 
	 */
	public int [][] getStateMatrix ();

	/**
	 * @return The Heuristic functions of the State(node)
	 */
	public int HeuristicFunctions();
	
	/**
	 * @return a reference to the father of this State,
	 * so that at the goal we can go back to find all the road. 
	 */
	public State_Data fatherPointer();
	
	/**
	 * @return The weight of the road so far.
	 */
	public double getWeightUntilNow();

	/**
	 * @return the remark (meta data) associated with this State(node).
	 */
	public String getInfo();

	/**
	 * Allows changing the remark (meta data) associated with this State(node).
	 * @param s
	 */
	public void setInfo(String s);
	
}