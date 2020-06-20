package Puzzle_initFromFile;
/**
 * This class is represent a initialization of the Game details from .txt file
 * @author Tzion
 */
import java.util.Hashtable;
import DataStructure.Color;

public class initMatrix {

	/**
	 * This function manage all the operation of the initialization of the game details from .txt file
	 * @param matSIZE
	 * @param game_Matrix
	 * @param blackBlocks
	 * @param redBlocks
	 */
	private void initGame(String matSIZE, String game_Matrix, String blackBlocks, String redBlocks) {
		initSIZE(matSIZE);
		this.BlocksColors = new Hashtable<Integer, Color>();
		initBalckBlocks(blackBlocks);
		initRedBlocks(redBlocks);
		initGreenBlocks(blackBlocks, redBlocks);

		//final step: initialize the Integer matrix
		initGame_Matrix(game_Matrix);
	}

	/**
	 * This function initialize the Integer matrix of the Game from a String, put the Label 0 at the position 
	 * of the space: "_"
	 * @param game_Matrix String
	 */
	private void initGame_Matrix(String game_Matrix) {
		this.ThePuzzle=new int[this.n][this.m];
		String [] intArray=game_Matrix.split(",");

		int RunONstringMat_Integeres=0;
		for(int i=0;i<this.n;i++) {
			for(int j=0;j<this.m;j++) {
				if(intArray[RunONstringMat_Integeres].equals("_")) {this.ThePuzzle[i][j]=0;}
				else {this.ThePuzzle[i][j]=Integer.valueOf(intArray[RunONstringMat_Integeres]);}

				RunONstringMat_Integeres++;
			}
		}
	}

	/**
	 * This function initialize the Puzzle matrix SIZE from string
	 * @param matSIZE: n*m
	 */
	private void initSIZE(String matSIZE) {
		String [] cut_X=matSIZE.split("x");
		this.n=Integer.valueOf(cut_X[0]);
		this.m=Integer.valueOf(cut_X[1]);
	}

	/**
	 * This function initialize at the HashTable the blocks of numbers that their color is Black.
	 * Using hashMap to access the numbers in O(1)
	 * @param BalckBloacks
	 */
	private void initBalckBlocks(String BlackBloacks) {
		if(BlackBloacks!=null) {
			String [] tempBalck=BlackBloacks.split(",");
			for(int i=0;i<tempBalck.length ;i++) {
				this.BlocksColors.put(Integer.valueOf(tempBalck[i]), Color.BLACK);
			}
		}
	}

	/**
	 * This function initialize at the HashTable the blocks of numbers that their color is Red.
	 * Using hashMap to access the numbers in O(1)
	 * @param RedBloacks
	 */
	private void initRedBlocks(String RedBlocks) {
		if(RedBlocks!=null) {
			String [] tempRed=RedBlocks.split(",");
			for(int i=0;i<tempRed.length ;i++) {
				this.BlocksColors.put(Integer.valueOf(tempRed[i]), Color.RED);
			}
		}
	}

	/**
	 * This function initialize at the HashTable of the blocks the numbers that there color is Green.
	 * The "Green" it is all the numbers that not Black neither Red
	 * Using hashMap to access the numbers in O(1)
	 * 
	 * - If the number is not at the HashTable so far so it's a green number
	 * @param RedBloacks BlackBlocks
	 */
	private void initGreenBlocks(String BlackBlocks, String RedBlocks) {
		for(int i=1;i<=this.n*this.m ;i++) 
			if(!this.BlocksColors.containsKey(i))
				this.BlocksColors.put(i,Color.GREEN);
	}

	//*********************************** simple getter **********************************
	public int getN() {return n;}

	public int getM() {return m;}

	public int[][] getThePuzzle() {return ThePuzzle;}

	public Hashtable<Integer, Color> getBlocksColors() {return BlocksColors;}

	//*********************************** Private Data ***********************************
	private int n;//numbers of the rows
	private int m;//numbers of the columns
	private int [][] ThePuzzle;

	//For each number, to get the color of the block of the number at the Puzzle, it will be O(1)
	private Hashtable<Integer, Color> BlocksColors;

	//*********************************** Constructors ***********************************
	public initMatrix(String matSIZE, String Game_Matrix, String BlackBlocks, String RedBlocks) 
	{initGame(matSIZE,Game_Matrix,BlackBlocks,RedBlocks);}

}