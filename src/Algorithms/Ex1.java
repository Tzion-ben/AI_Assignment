package Algorithms;
/**
 * This class runs all the Puzzle Game and write it to a .txt File
 * @author tzion
 */
import DataStructure.State_Node;
import Puzzle_initFromFile.initMatrix;
import Puzzle_initFromFile.initTXT;
import Puzzle_initFromFile.outputTXT;

public class Ex1 {

	/**
	 * This main function runs all the Puzzle Game
	 * @param args
	 */
	public static void main(String[] args) {
		//reading the .txt file
		initTXT inputFIle =new initTXT("input.txt");

		//create the all matrix Game details
		initMatrix init_Puzzle=new initMatrix(inputFIle.getMatSIZE(), inputFIle.getGame_Matrix(),
				inputFIle.getBlackBlocks(), inputFIle.getRedBloacks());

		//create a NONE operatin for the "_" Label at the Game Matrix 
		String opToStart_AND_GoalState = String.valueOf(0)+"-N";

		//create the start Node
		State_Node startState=new State_Node(init_Puzzle.getThePuzzle(),init_Puzzle.getBlocksColors()
				,opToStart_AND_GoalState, 0,0, null ,-1, -1,0);

		//create the matrix for the Goal state
		int k=1;
		int [][] goalState = new int[init_Puzzle.getThePuzzle().length][init_Puzzle.getThePuzzle()[0].length];
		for(int i=0 ; i<goalState.length ; i++) {
			for(int j=0 ; j<goalState[0].length ; j++) {
				goalState[i][j]=k;
				k++;
			}
		}
		//puts the Label 0 that represent the space block "_" at last location in the matrix
		goalState[goalState.length-1][goalState[0].length-1]=0;

		//create the Goal state
		State_Node goal = new State_Node(goalState,init_Puzzle.getBlocksColors(), opToStart_AND_GoalState, 0,0, null,goalState.length-1,
				goalState[0].length-1,0);

		//RUNS ALL THE GAME !!
		RUN_The_GAME(inputFIle.getAlgo(), startState, goal, inputFIle.getShowTimeORnot(), inputFIle.getShowOpenListORnot());

	}

	/**
	 * This function will decide what Algorithm to run depend on the String algo that came from 
	 * the .txt file, and in the end create the output .txt file 
	 * @param algo
	 * @param startState
	 * @param goal
	 * @param printTime
	 */
	public static void RUN_The_GAME(String algo, State_Node startState ,State_Node goal, boolean printTime, boolean withOpen) {

		long startTime=0 ,endTime = 0;
		double finalTime=0;
		Algorithm searchPathToGoal = null;

		switch (algo) {
		case "BFS": 
			searchPathToGoal =new BFS(startState, goal, withOpen);
			break;

		case "DFID": 
			searchPathToGoal =new DFID(startState, goal, withOpen);
			break;

		case "A*": 
			searchPathToGoal =new A_Star(startState, goal, withOpen);
			break;

		case "IDA*": 
			searchPathToGoal =new IDA_Star(startState, goal, withOpen);
			break;

		case "DFBnB": 
			searchPathToGoal =new DFBnB(startState, goal, withOpen);
			break;
		}

		startTime = System.currentTimeMillis();
		State_Node result = searchPathToGoal.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		String path = path(result);

		/**
		 * create the output file with the result path , time of running , number on states created and the cost
		 * to the goal
		 */
		int costToGoal = 0 ;
		if(result != null)
			costToGoal = result.getF();
		outputTXT create_OutputTXT = new outputTXT(path, searchPathToGoal.getNodesNum(), costToGoal, finalTime, printTime);
	}



	/**
	 * This method ordering the path string in a specific way, for example: 5U-6D,
	 * if there is no path re turn "no path" 
	 * @param result Path before ordering it
	 * @return
	 */
	private static String path (State_Node result) {
		if(result!=null) {
			String Path ="";
			while(result!=null) {
				Path=result.getOperation()+" "+Path;
				result=result.getFatherPointer();
			}
			Path=Path.replaceAll("-", "");
			Path=Path.replaceAll(" ", "-");
			Path = Path.substring(0, Path.length()-1);
			return Path.substring(3);
		}else {
			return "no path";
		}
	}

}
