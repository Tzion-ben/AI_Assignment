package DataStructure;
/**
* This interface represents the set of operations applicable on a 
 * Operator(edge) in the solution search graph.
 * @author tzion
 */
public interface Operator_Data {
	/**
	 * The id of the source State (node) of this edge.
	 * @return
	 */
	public int getSrcId();
	
	/**
	 * The id of the destination State(node) of this edge
	 * @return
	 */
	public int getDestId();
	
	/**
	 * @return the weight of this edge (positive value).
	 */
	public double getWeight();
	
	/**
	 * return the remark (meta data) associated with this edge.
	 * @return
	 */
	public String getInfo();
	
	/**
	 * Allows changing the remark (meta data) associated with this edge.
	 * @param s
	 */
	public void setInfo(String s);
	
}