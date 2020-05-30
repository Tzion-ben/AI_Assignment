package JunitTesting;

import Algorithms.BFS;
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
		
		System.out.println(tt.getMatSIZE());
		System.out.println(tt.getBlackBlocks());		
		System.out.println(tt.getRedBloacks());
		System.out.println(tt.getGame_Matrix()+"\n");
		
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
		
		BFS bfs = new BFS(startState, goal);
		State_Node ans = bfs.BFS();
		
		outputTXT ooo=new outputTXT("afa", 2, 3, 234, false);
	}

}
