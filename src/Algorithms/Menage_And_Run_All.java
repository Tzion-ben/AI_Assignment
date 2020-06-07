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
		State_Node startState=new State_Node(start.getThePuzzle(),start.getBlocksColors(),op, 0,0, null ,-1, -1,null);

		int k=1;
		int [][] goalState = new int[start.getThePuzzle().length][start.getThePuzzle()[0].length];
		for(int i=0 ; i<goalState.length ; i++) {
			for(int j=0 ; j<goalState[0].length ; j++) {
				goalState[i][j]=k;
				k++;
			}
		}
		goalState[goalState.length-1][goalState[0].length-1]=0;

		State_Node goal = new State_Node(goalState,start.getBlocksColors(), op, 0,0, null,goalState.length-1,goalState[0].length-1,null);



		BFS BFS = new BFS(startState, goal);
		System.out.println();
		long startTime = System.currentTimeMillis();
		State_Node ansBFS = BFS.BFS();
		long endTime = System.currentTimeMillis();
		double finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		System.out.println("BFS :"+BFS.getNodesNum());
		System.out.println("cost:"+ansBFS.getF());
		State_Node pos = ansBFS;
		String ansPrint  = ansBFS.getPathSoFar();
//		while(pos!= null) {
//			ansPrint = pos.getNumDirection()+""+pos.getDirection()+"-"+ansPrint;
//			pos = pos.getFatherPointer();
//		}
		ansPrint.lastIndexOf("-");
		
		System.out.println(ansPrint);
		

	}

}
