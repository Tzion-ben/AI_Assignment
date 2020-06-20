package Algorithms;
/**
 *This interface represents the set of operations applicable on a Search
 *Graph Algorithm
 * @author Tzion
 */
import DataStructure.State_Node;

public interface Algorithm {
	/**
	 * This function run's the Algorithm and return either the Path to the Goal
	 * or Null if there is no Path
	 * @return
	 */
	public State_Node Search_Goal_Algorithm();

	/**
	 * This function return the numbers of State_Nodes that the Algorithm created during
	 * it's running (OpenList and CloseList)
	 * @return
	 */
	public int getNodesNum();

}