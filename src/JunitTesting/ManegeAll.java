package JunitTesting;

import Algorithms.Algorithems;
import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;
import DataStructure.Direction;
import DataStructure.Oprerator;
import DataStructure.State_Node;
import Puzzle_initFromFile.initMatrix;
import Puzzle_initFromFile.initTXT;
import Puzzle_initFromFile.outputTXT;

public class ManegeAll {

	public static void main(String[] args) {
		initTXT tt =new initTXT("input.txt");
		initMatrix start=new initMatrix(tt.getMatSIZE(), tt.getGame_Matrix(), tt.getBlackBlocks(), tt.getRedBloacks());

		Matrix_Variable [][] vv=start.getThePuzzle();
		for(int i=0;i<vv.length;i++) {
			for(int j=0;j<vv[0].length;j++) {
				System.out.print(vv[i][j].getNum()+","+vv[i][j].getColor()+"|");
			}
			System.out.println();
		}

		Oprerator op = new Oprerator(-1, Direction.N, Color.E);
		State_Node startState=new State_Node(start.getThePuzzle(), op, 0, null);
		int k=1;
		Matrix_Variable [][] goalState = new Matrix_Variable[start.getThePuzzle().length][start.getThePuzzle()[0].length];
		for(int i=0 ; i<goalState.length ; i++) {
			for(int j=0 ; j<goalState[0].length ; j++) {
				goalState[i][j]=new Matrix_Variable(k, Color.E);
				k++;
			}
		}
		goalState[goalState.length-1][goalState[0].length-1].setNum(-1);

		State_Node goal = new State_Node(goalState, op, 0, null);

		Algorithems algoRun = new Algorithems(startState, goal);
		//State_Node ans = algoRun.A_Star();
		//System.out.println("A* :"+algoRun.countingNodes());

		algoRun.zeroCountingNodes();

		System.out.println();
		State_Node ansBFS = algoRun.BFS();
		System.out.println("BFS :"+algoRun.raiseNumberOfNodes());
		System.out.println("cost:"+ansBFS.getF());
		State_Node pos = ansBFS;
		String ansPrint  = "";
		while(pos!= null) {
			ansPrint = pos.getOperation().toString()+ansPrint;
			pos = pos.getFatherPointer();
		}
		System.out.println(ansPrint.substring(4));
		System.out.println();

		algoRun.zeroCountingNodes();

		System.out.println();
		State_Node ansDFID = algoRun.DFID();
		System.out.println("DFID :"+algoRun.raiseNumberOfNodes());
		System.out.println("cost :"+ansDFID.getF());
		pos = ansDFID;
		ansPrint="";
		while(pos!= null) {
			ansPrint = pos.getOperation().toString()+ansPrint;
			pos = pos.getFatherPointer();
		}
		System.out.println(ansPrint.substring(4));
		System.out.println();
		
		
		
		//outputTXT ooo=new outputTXT("afa", 2, 3, 234, false);
	}

}
