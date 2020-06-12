package Tools;
/**
 * This class contains functions that helps the State_Node class with all the functions that
 * associated with the direction of the operation that brings the state matrix to the specific state.
 * @author Tzion
 */
import java.util.Hashtable;
import DataStructure.Color;
import DataStructure.Direction;

public class DirectionHelper {

	/************************************************************************************************
	 * This function returns the reversed operation of a given operation
	 * @return
	 ************************************************************************************************/
	public Direction getReversedOp (Direction D) {
		switch (D) {
		case R: 
			return Direction.L;
		case U:
			return Direction.D;
		case L: 
			return Direction.R;
		case D:
			return Direction.U;
		}

		return null;
	}

	/************************************************************************************************
	 * This function returns the number of the direction 0...3,
	 * based on the following order : L,U,R,D --> (0,1,2,3)
	 * @return
	 ************************************************************************************************/
	public int getDirection_Id (Direction D) {
		switch (D) {
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

	/************************************************************************************************
	 * This function gets i and j and State matrix and calculating if the number at this [i][j] location is
	 * can to move or no (if the color is NOT black it's can move)
	 * @param mat
	 * @param numbersColors
	 * @param i
	 * @param j
	 * @return
	 ************************************************************************************************/
	public boolean locationToOperetors(int [][] mat, Hashtable<Integer, Color> numbersColors, int i, int j) {
		Color colorToCheck = colorValidator.getColor((mat[i][j]));
		return colorValidator.getAllowORnot(colorToCheck);
	}

	/************************************************************************************************
	 * This function return a string with number and direction to move (only the allow directions) 
	 * @param num
	 * @param d
	 * @return
	 ************************************************************************************************/
	public String nextOperation(int [][] mat,int i,int j, Direction D) {return String.valueOf(mat[i][j])+"-"+D;}

	/************************************************************************************************
	 * This function sets the colorValidator help Object
	 ************************************************************************************************/
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