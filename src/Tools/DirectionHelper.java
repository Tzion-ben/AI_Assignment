package Tools;

import java.util.Hashtable;

import DataStructure.Color;
import DataStructure.Direction;

public class DirectionHelper {

	public DirectionHelper() {;}
	//*******************************************************************************************//

	/**
	 * This function returns the reversed operation of a given operation
	 * @return
	 */
	public Direction getReversedOp (Direction d) {
		Direction rev = null;
		switch (d) {
		case R: 
			rev = Direction.L;
			break;
		case U:
			rev = Direction.D;
			break;
		case L: 
			rev = Direction.R;
			break;
		case D:
			rev = Direction.U;
			break;
		}
		return rev;
	}

	/**
	 * This function returns the number of the direction 0...3,
	 * based on the following order : L,U,R,D (0,1,2,3)
	 * @return
	 */
	public int getDirection_Id (Direction d) {
		switch (d) {
		case L: 
			return 0;
		case U:
			return 1;
		case R: 
			return 2;
		case D:
			return 3;
		}
		return -1;
	}

	/**
	 * This function gets i and j and State matrix and calculting if the number at this location is
	 * can to move or no (if the color is NOT black it's can move)
	 * @param mat
	 * @param numbersColors
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean locationToOperetors(int [][] mat, Hashtable<Integer, Color> numbersColors, int i, int j) {
		Color colorToCheck = colorValidator.getColor((mat[i][j]));
		return colorValidator.getAllowORnot(colorToCheck);
	}

	/**
	 * This function return a string with number and it's allow direction to move
	 * @param num
	 * @param d
	 * @return
	 */
	public String nextOperation(int [][] mat,int i,int j, Direction D) {return String.valueOf(mat[i][j])+"-"+D;}

	/**
	 * This function sets the colorValidator help Object
	 */
	private void setHelpColorValidator() {colorValidator = new ColorValidator(numbersColors);}
	//*********************Private Data**************************
	private ColorValidator colorValidator;
	private Hashtable<Integer, Color> numbersColors = new Hashtable<Integer, Color>();

	//****************** Private Constructor ******************
	public DirectionHelper (Hashtable<Integer, Color> numbersColors) 
	{
		this.numbersColors=numbersColors;
		setHelpColorValidator();
	}
}