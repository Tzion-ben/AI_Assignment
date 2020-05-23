package DataStructure;

import java.util.Hashtable;

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
	public Hashtable<Integer, Integer> getRedBlocks();

	/**
	 * @return Array with all the numbers that their color is BLACK
	 */
	public Hashtable<Integer, Integer> getBlacBlocks();

	/**
	 * @return Array with all the numbers that their color is GREES
	 */
	public Hashtable<Integer, Integer> getGreenBlocks();

}