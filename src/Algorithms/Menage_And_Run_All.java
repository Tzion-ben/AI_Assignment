package Algorithms;


import DataStructure.Direction;
import DataStructure.State_Node;
import Puzzle_initFromFile.initMatrix;
import Puzzle_initFromFile.initTXT;

public class Menage_And_Run_All {

	public static void main(String[] args) {
		initTXT tt =new initTXT("input.txt");
		initMatrix start=new initMatrix(tt.getMatSIZE(), tt.getGame_Matrix(), tt.getBlackBlocks(), tt.getRedBloacks());

		int [][] vv=start.getThePuzzle();
		for(int i=0;i<vv.length;i++) {
			for(int j=0;j<vv[0].length;j++) {
				System.out.print(vv[i][j]+"|");
			}
			System.out.println();
		}

		String op = String.valueOf(0)+"-N";
		State_Node startState=new State_Node(start.getThePuzzle(),start.getBlocksColors(),op, 0,0, null ,-1, -1,null,0);

		int k=1;
		int [][] goalState = new int[start.getThePuzzle().length][start.getThePuzzle()[0].length];
		for(int i=0 ; i<goalState.length ; i++) {
			for(int j=0 ; j<goalState[0].length ; j++) {
				goalState[i][j]=k;
				k++;
			}
		}
		goalState[goalState.length-1][goalState[0].length-1]=0;

		State_Node goal = new State_Node(goalState,start.getBlocksColors(), op, 0,0, null,goalState.length-1,
				goalState[0].length-1,null,0);

		long startTime=0 ,endTime = 0;
		double finalTime=0;

		BFS BFS = new BFS(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansBFS = BFS.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		if(ansBFS!=null) {
			System.out.println("BFS :"+BFS.getNodesNum());
			System.out.println("cost:"+ansBFS.getF());
			String ansPrint  = ansBFS.getPathSoFar();
			System.out.println(ansPrint);
		}
		else {
			System.out.println("BFS :"+BFS.getNodesNum());
			System.out.println("No path at BFS");
		}


		DFID DFID = new DFID(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansDFID = DFID.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		System.out.println("DFID :"+DFID.getNodesNum());
		System.out.println("cost:"+ansDFID.getF());
		String ansPrintDFID  = ansDFID.getPathSoFar();
		System.out.println(ansPrintDFID);

		A_Star A_Star = new A_Star(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansAStar = A_Star.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		System.out.println("A_Star :"+A_Star.getNodesNum());
		System.out.println("cost:"+ansAStar.getF());
		String ansPrintA_Star  = ansAStar.getPathSoFar();
		System.out.println(ansPrintA_Star);

		IDA_Star IDA_Star = new IDA_Star(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansIDA_Star = IDA_Star.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		System.out.println("IDA_Star :"+IDA_Star.getNodesNum());
		System.out.println("cost:"+ansIDA_Star.getF());
		String ansPrintIDA_Star  = ansIDA_Star.getPathSoFar();
		System.out.println(ansPrintIDA_Star);
	}

}
