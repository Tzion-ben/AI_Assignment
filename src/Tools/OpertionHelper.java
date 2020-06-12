package Tools;
/**
 * This class contains functions that helps the State_Node class with all the functions that
 * associated with the operation that brings the state matrix to the specific state.
 * @author Tzion
 */
import java.util.Hashtable;
import DataStructure.Color;
import DataStructure.Direction;

public class OpertionHelper {

	/************************************************************************************************
	 * This function create a String from a int matrix and convert it to a String
	 * that represent the state matrix
	 * @param mat
	 * @return String result
	 ************************************************************************************************/
	public String key (int [][] mat) {
		String result = "";
		String matState="";
		for(int i=0 ; i<mat.length ; i++) 
			for(int j=0 ; j<mat[0].length ; j++) {
				matState=matState+String.valueOf(mat[i][j]);
			}
		result = String.valueOf(matState);

		return result;
	}

	/************************************************************************************************
	 * This function searching for the position of the Label 0 that represent the empty block :"_" 
	 * @return array in size 2, with the i and j (location) of the 0
	 ************************************************************************************************/
	public int [] setMinus_I_J(int[][] mat) {
		boolean stop=false;	
		int [] space_pos = new int [2];
		for(int i=0 ; i<mat.length && !stop ; i++) {
			for(int j=0 ; j<mat[0].length ; j++) {
				if (mat[i][j] == 0) {
					space_pos[0]=i;
					space_pos[1]=j;
					stop=true;
				}
			}
		}
		
		return space_pos;
	}

	/************************************************************************************************
	 * This function is calculating the Heuristic function for the Puzzle.
	 * The function is based on the Manhattan distance heuristic/
	 * Finds the "goal" location of every k | 1<=k<n*m, and calculating how many steps this k have to 
	 * take to frim his position now to his "goal" position
	 * @param mat
	 * @param D
	 * @return
	 ************************************************************************************************/
	public int setH(int [][] mat ,Direction D) {

		int h=0;
		int goal_i=0, goal_j=0, now_i=0, now_j=0;
		int stepsForI=0, stepsForJ=0;
		int SIZE = mat.length*mat[0].length;

		/**
		 * at first i will find the "Goal" location of k
		 */
		int colum = mat[0].length;
		for(int k=1 ; k<=SIZE ; k++ ) {
			goal_j = now_j;
			goal_i = now_i;
			now_j++;//the columns runs

			//if k divided be now_i that it's the number of the row so it mean's it's
			//the last location of the row 
			if(k/(now_i+1) == colum) {  //a new line at the goal matrix so i++ and j=0
				now_i++;
				now_j=0;
			}

			for(int i=0;i<mat.length;i++) {
				for(int j=0; j<mat[0].length;j++) {

					//if it's not the 0 Label that represent "_" : a empty block
					if(mat[i][j]!=0) {
						if(mat[i][j] == k) {

							//how many steps have to do to put the number at his "Goal" position 
							//for the h function
							stepsForI=goal_i-i;
							stepsForJ=goal_j-j;

							//returns the cost of the block to move
							Color colorToCosr = colorValidator.getColor(mat[i][j]);
							int costColor= colorValidator.getCost(colorToCosr);

							//calculate steps for rows+columns
							int cost=(Math.abs(stepsForI)+Math.abs(stepsForJ))*costColor;

							//added it to the h function until now
							h+=cost;

							//to stop loop, and move to the next k
							j=mat[0].length;
							i=mat.length;
						}
					}
					//returns the moveCounters to start position
					stepsForI=0;
					stepsForJ=0;
				}
			}
		}

		return h;
	}

	/************************************************************************************************
	 * This function sets the colorValidator help Object
	 ************************************************************************************************/
	private void setHelpColorValidator() {colorValidator = new ColorValidator(numbersColors);}

	//********************* Private Data **************************
	private  static ColorValidator colorValidator;
	private Hashtable<Integer, Color> numbersColors = new Hashtable<Integer, Color>();

	//****************** Private Constructor ******************
	public OpertionHelper (Hashtable<Integer, Color> numbersColors) 
	{
		this.numbersColors=numbersColors;
		setHelpColorValidator();
	}

}