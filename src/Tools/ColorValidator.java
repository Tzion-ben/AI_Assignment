package Tools;
/**
 * This class contains functions that helps the State_Node class with all the functions that
 * associated with the "color" of a block at specific state Integer matrix.
 * @author Tzion
 */
import java.util.Hashtable;
import DataStructure.Color;

public class ColorValidator {

	/**
	 * This function return a number associated with a "color" at the Hash Table
	 * @param Integer
	 * @return Color
	 */
	public Color getColor(int num) {
		if(num==0) {return Color.E;}
		while(!this.numbersColors.isEmpty()) 
			if(this.numbersColors.containsKey(num)) 
				return this.numbersColors.get(num);

		return null;
	}

	/**
	 * this method return the cost to move a block depend on it's color, RED->30
	 * GREEN->1, BLACK->can't move so 0 , E-> for the start state, is 0 also
	 * @return the cost depend on the color
	 */
	public int getCost (Color c){
		switch (c) {
		case RED:
			return 30;
		case GREEN:
			return 1;
		}

		//BLACK && E cases: --> cost = 0
		return 0;
	}

	/**
	 * This method checks the color of the block and says if this block can move or 
	 * not: RED and GRENN - can move, BLACK - can't move 
	 * @param color c
	 * @return true(can move) or false(can't move)
	 */
	public boolean getAllowORnot(Color c) {
		switch (c) {
		case RED:
		case GREEN:
			return true;
		}

		//BLACK case : --> false
		return false;
	}

	/**
	 * This method counting the size of the matrix without the BLACK blocks  
	 * @return size of the matrix without the BLACK blocks
	 */
	public int getNumWithOutBlackBlocks() {
		int count = 0;
		for (int i = 0; i < this.numbersColors.size(); i++) {
			if(!this.numbersColors.get(i+1).equals(Color.BLACK)) {count++;}
		}
		
		return count;
	}

	//*********************************** Private Data ***********************************
	private Hashtable<Integer, Color> numbersColors = new Hashtable<Integer, Color>();

	//************************************ Constructor ***********************************
	public ColorValidator(Hashtable<Integer, Color> numbersColors) {this.numbersColors=numbersColors;}

}