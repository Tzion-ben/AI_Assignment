package Puzzle_initFromFile;

import java.util.Hashtable;

import Colored_Matrix_Management.Direction;
import Colored_Matrix_Management.Matrix_Variable;
import DataStructure.Vertex_Colors;

/**
 * 
 * @author Tzion
 *
 */
public class initMatrix {

	/************************************************************************************************
	 * This function is manage all the details of the Puzzle Game that came from the constructor 
	 * @param matSIZE
	 * @param Game_Matrix
	 * @param BlackBlocks
	 * @param RedBloacks
	 * @param GreenBloacks
	 ************************************************************************************************/
	private void initGame(String matSIZE, String Game_Matrix, String BlackBlocks, String RedBlocks, String GreenBlocks) {
		initSIZE(matSIZE);
		initGame_Matrix(Game_Matrix);
		initBalckBlocks(BlackBlocks);
	}

	/************************************************************************************************
	 * This function initialize the Puzzle matrix SIZE 
	 * @param matSIZE
	 ************************************************************************************************/
	private void initSIZE(String matSIZE) {
		String [] cut_X=matSIZE.split("x");
		this.n=Integer.valueOf(cut_X[0]);
		this.n=Integer.valueOf(cut_X[1]);
	}

	/************************************************************************************************
	 * 
	 * @param BalckBloacks
	 ************************************************************************************************/
	private void initBalckBlocks(String BlackBloacks) {
		if(BlackBloacks!=null) {
			String [] tempBalck=BlackBloacks.split(",");
			for(int i=0;i<tempBalck.length ;i++) {
				
			}
		}
	}

	/************************************************************************************************
	 * 
	 * @param RedBloacks
	 ************************************************************************************************/
	private void initRedBlocks(String RedBloacks) {

	}

	/************************************************************************************************
	 * 
	 * @param Game_Matrix
	 ************************************************************************************************/
	private void initGame_Matrix(String Game_Matrix) {
		this.ThePuzzle=new Matrix_Variable[this.n][this.m];
		String [] intArray=Game_Matrix.split(",");
		int RunONstringMatIntegeres=0;
		for(int i=0;i<this.n;i++) {
			for(int j=0;j<this.m;j++) {
				this.ThePuzzle[i][j]=new Matrix_Variable(intArray[RunONstringMatIntegeres], color, Direction.N);
				RunONstringMatIntegeres++;
			}
		}

	}



	//****************** Private Methods and Data *****************
	private int n;//numbers of the rows
	private int m;//numbers of the columns
	private Matrix_Variable [][] ThePuzzle;

	//For each number, to get the color of the number at the Puzzle, it will be O(1)
	private Hashtable<Integer, Integer> RedBlocks;
	private int [] GreenBlocksssss;
	private int [] BlackBlockssssss;

	//****************** Constructors *****************
	public initMatrix(String matSIZE, String Game_Matrix, String BlackBlocks, String RedBlocks, String GreenBlocks) {
		initGame(matSIZE,Game_Matrix,BlackBlocks,RedBlocks,GreenBlocks);
	}
}
