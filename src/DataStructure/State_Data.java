package DataStructure;

import Colored_Matrix_Management.Matrix_Variable;

/**
 * This interface represents the set of operations applicable on a 
 * State (vertex/node) in the solution search graph.
 * @author Tzion
 */
public interface State_Data{

	/**
	 * @return The matrix associated with the State, after setting it
	 */
	public Matrix_Variable [][] getStateMatrix ();


	/**
	 *this function set the state matrix with the operator from the father state 
	 */
	public void setStateMatrix ();

	/**
	 * @return The weight of the road so far, after setting it
	 */
	public int getF();

	/**
	 * this function set the f(n)=g(n)+h(n), with the cost of the road so far
	 */
	public void setF();

	/**
	 *this function set Heuristic functions of the State(node) so far 
	 */
	public void setH();

	/**
	 * @return The Heuristic functions of the State(node) after setting it
	 */
	public int getH();

	/**
	 *this function set G functions (real cost so far) of the State(node) so far 
	 */
	public void setG();

	/**
	 * @return The G functions (real cost so far) of the State(node) after setting it
	 */
	public int getG();

	/**
	 * @return a reference to the father of this State,
	 * so that at the goal we can go back to find all the road, (The path).
	 */
	public State_Data getFatherPointer();

	/** 
	 * @return The operation that brings the state before to this state
	 */
	public Oprerator getOperation();

	/**
	 * @return all the operators that allows on this state
	 */
	public Oprerator [] setOprerators ();

}