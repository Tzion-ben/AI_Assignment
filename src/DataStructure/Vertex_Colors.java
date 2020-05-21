package DataStructure;

/**
 * This interface represent the set of getters to return a ArrayList of digits
 * that a block with that color contains 
 * @author tzion
 *
 */
public interface Vertex_Colors {

	/**
	 * @return Array with all the numbers that their color is RED
	 */
	public int [] getRedBlocks();

	/**
	 * @return Array with all the numbers that their color is BLACK
	 */
	public int [] getBlacBlocks();

	/**
	 * @return Array with all the numbers that their color is GREES
	 */
	public int [] getGreenBlocks();

}