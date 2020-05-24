package Puzzle_initFromFile;

import java.util.Hashtable;
import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;

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
	 ************************************************************************************************/
	private void initGame(String matSIZE, String Game_Matrix, String BlackBlocks, String RedBlocks) {
		initSIZE(matSIZE);
		initBalckBlocks(BlackBlocks);
		initRedBlocks(RedBlocks);
		initGreenBlocks(BlackBlocks, RedBlocks);

		//final step:
		initGame_Matrix(Game_Matrix);
	}

	/************************************************************************************************
	 * This function initialize the Puzzle matrix SIZE from string
	 * @param matSIZE
	 ************************************************************************************************/
	private void initSIZE(String matSIZE) {
		String [] cut_X=matSIZE.split("x");
		this.n=Integer.valueOf(cut_X[0]);
		this.m=Integer.valueOf(cut_X[1]);
	}

	/************************************************************************************************
	 * This function initialize the HashTable of the blocks of numbers that their color is Black.
	 * Using hashMap to access the numbers in O(1)
	 * @param BalckBloacks
	 ************************************************************************************************/
	private void initBalckBlocks(String BlackBloacks) {
		this.BlackBlocks=new Hashtable<Integer, Integer>();
		if(BlackBloacks!=null) {
			String [] tempBalck=BlackBloacks.split(",");
			for(int i=0;i<tempBalck.length ;i++) {
				this.BlackBlocks.put(Integer.valueOf(tempBalck[i]), Integer.valueOf(tempBalck[i]));
			}
		}
	}

	/************************************************************************************************
	 * This function initialize the HashTable of the blocks of numbers that their color is Red.
	 * Using hashMap to access the numbers in O(1)
	 * @param RedBloacks
	 ************************************************************************************************/
	private void initRedBlocks(String RedBlocks) {
		this.RedBlocks=new Hashtable<Integer, Integer>();
		if(RedBlocks!=null) {
			String [] tempRed=RedBlocks.split(",");
			for(int i=0;i<tempRed.length ;i++) {
				this.RedBlocks.put(Integer.valueOf(tempRed[i]), Integer.valueOf(tempRed[i]));
			}
		}
	}

	/************************************************************************************************
	 * This function initialize the HashTable of the blocks of numbers that their color is Green.
	 * Using hashMap to access the numbers in O(1)
	 * 
	 * -A:= If no have a red or black blocks (both ==null) so than all the numbers will be green 
	 * -B:= I
	 * @param RedBloacks
	 ************************************************************************************************/
	private void initGreenBlocks(String BlackBlocks, String RedBlocks) {
		this.GreenBlocks=new Hashtable<Integer, Integer>();
		if(BlackBlocks==null && BlackBlocks==null) {
			for(int i=1;i<=this.n*this.m ;i++) 
				this.GreenBlocks.put(i,i);
		}
		else {
			for(int i=1;i<=this.n*this.m ;i++) 
				if(!this.BlackBlocks.containsKey(i) && !this.RedBlocks.containsKey(i))
					this.GreenBlocks.put(i,i);
		}
	}

	/************************************************************************************************
	 * This function is initialize the Puzzle matrix/Board eith all numbers and there colors 
	 * @param Game_Matrix
	 ************************************************************************************************/
	private void initGame_Matrix(String Game_Matrix) {
		this.ThePuzzle=new Matrix_Variable[this.n][this.m];
		String [] intArray=Game_Matrix.split(",");

		int RunONstringMat_Integeres=0;
		for(int i=0;i<this.n;i++) {
			for(int j=0;j<this.m;j++) {
				if(intArray[RunONstringMat_Integeres].equals("_")) {
					this.ThePuzzle[i][j]=new Matrix_Variable(-1, Color.E);
				}
				else {
					//if the color of the block with that number is Black
					if(this.BlackBlocks.containsKey(Integer.valueOf(intArray[RunONstringMat_Integeres])))
						this.ThePuzzle[i][j]=new Matrix_Variable(Integer.valueOf(intArray[RunONstringMat_Integeres]), Color.BLACK);

					//if the color of the block with that number is Red
					else if(this.RedBlocks.containsKey(Integer.valueOf(intArray[RunONstringMat_Integeres]))) 
						this.ThePuzzle[i][j]=new Matrix_Variable(Integer.valueOf(intArray[RunONstringMat_Integeres]), Color.RED);

					//if the color of the block with that number is Green
					else if(this.GreenBlocks.containsKey(Integer.valueOf(intArray[RunONstringMat_Integeres]))) 
						this.ThePuzzle[i][j]=new Matrix_Variable(Integer.valueOf(intArray[RunONstringMat_Integeres]), Color.GREEN);

					RunONstringMat_Integeres++;
				}
			}
		}
	}


	//Only regular Getters
	public Matrix_Variable[][] getThePuzzle() {
		return this.ThePuzzle;
	}

	public Hashtable<Integer, Integer> getBlackBlocks() {
		return BlackBlocks;
	}

	public Hashtable<Integer, Integer> getRedBlocks() {
		return RedBlocks;
	}

	public Hashtable<Integer, Integer> getGreenBlocks() {
		return GreenBlocks;
	}


	//****************** Private Data *****************
	private int n;//numbers of the rows
	private int m;//numbers of the columns
	private Matrix_Variable [][] ThePuzzle;

	//For each number, to get the color of the number at the Puzzle, it will be O(1)
	private Hashtable<Integer, Integer> BlackBlocks;
	private Hashtable<Integer, Integer> RedBlocks;
	private Hashtable<Integer, Integer> GreenBlocks;


	//****************** Constructors *****************
	public initMatrix(String matSIZE, String Game_Matrix, String BlackBlocks, String RedBlocks) {
		initGame(matSIZE,Game_Matrix,BlackBlocks,RedBlocks);
	}
}