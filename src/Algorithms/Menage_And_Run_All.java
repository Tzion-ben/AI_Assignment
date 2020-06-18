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
		State_Node startState=new State_Node(start.getThePuzzle(),start.getBlocksColors(),op, 0,0, null ,-1, -1,0);

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
				goalState[0].length-1,0);

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
			String Path ="";
			while(ansBFS!=null) {
				Path=ansBFS.getOperation()+" "+Path;
				ansBFS=ansBFS.getFatherPointer();
			}
			System.out.println(Path.substring(3));
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
		if(ansDFID!=null) {
			System.out.println("DFID :"+DFID.getNodesNum());
			System.out.println("cost:"+ansDFID.getF());
			String Path ="";
			while(ansDFID!=null) {
				Path=ansDFID.getOperation()+" "+Path;
				ansDFID=ansDFID.getFatherPointer();
			}
			System.out.println(Path.substring(3));
		}
		else {
			System.out.println("DFID :"+DFID.getNodesNum());
			System.out.println("No path at DFID");
		}

		A_Star A_Star = new A_Star(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansAStar = A_Star.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		if(ansAStar!=null) {
			System.out.println("A_Star :"+A_Star.getNodesNum());
			System.out.println("cost:"+ansAStar.getF());
			String Path ="";
			while(ansAStar!=null) {
				Path=ansAStar.getOperation()+" "+Path;
				ansAStar=ansAStar.getFatherPointer();
			}
			System.out.println(Path.substring(3));
		}
		else {
			System.out.println("A_Star :"+DFID.getNodesNum());
			System.out.println("No path at A_Star");
		}
		
//		IDA_Star IDA_Star = new IDA_Star(startState, goal);
//		System.out.println();
//		startTime = System.currentTimeMillis();
//		State_Node ansIDA_Star = IDA_Star.Search_Goal_Algorithm();
//		endTime = System.currentTimeMillis();
//		finalTime = (endTime-startTime)/1000.0;
//		System.out.println(finalTime+" sec ");
//		if(ansIDA_Star!=null) {
//			System.out.println("IDA_Star :"+IDA_Star.getNodesNum());
//			System.out.println("cost:"+ansIDA_Star.getF());
//			String Path ="";
//			while(ansAStar!=null) {
//				Path=ansIDA_Star.getOperation()+" "+Path;
//				ansIDA_Star=ansIDA_Star.getFatherPointer();
//			}
//			System.out.println(Path);
//		}
//		else {
//			System.out.println("IDA_Star :"+DFID.getNodesNum());
//			System.out.println("No path at IDA_Star");
//		}

		DFBnB DFBnB = new DFBnB(startState, goal);
		System.out.println();
		startTime = System.currentTimeMillis();
		State_Node ansDFBnB = DFBnB.Search_Goal_Algorithm();
		endTime = System.currentTimeMillis();
		finalTime = (endTime-startTime)/1000.0;
		System.out.println(finalTime+" sec ");
		if(ansDFBnB!=null) {
			System.out.println("DFBnB :"+DFBnB.getNodesNum());
			System.out.println("cost:"+ansDFBnB.getF());
			String Path ="";
			while(ansAStar!=null) {
				Path=ansDFBnB.getOperation()+" "+Path;
				ansDFBnB=ansDFBnB.getFatherPointer();
			}
			System.out.println(Path.substring(3));
		}
		else {
			System.out.println("DFBnB :"+DFBnB.getNodesNum());
			System.out.println("No path at DFBnB");
		}
	}

}
