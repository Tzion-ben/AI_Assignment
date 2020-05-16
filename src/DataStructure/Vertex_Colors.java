package DataStructure;
import java.util.ArrayList;

/**
 * This interface represent the set of getters to return a ArrayList of digits
 * that a block with that color contains 
 * @author tzion
 *
 */
public interface Vertex_Colors {

	/**
	 * @return ArrayList with all the numbers that their color is RED
	 */
	public ArrayList<Integer> getRedBlocks();

	/**
	 * @return ArrayList with all the numbers that their color is GREES
	 */
	public ArrayList<Integer> getGreenBlocks();

	/**
	 * @return ArrayList with all the numbers that their color is BLACK
	 */
	public ArrayList<Integer> getBlacBlocks();

}