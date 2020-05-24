package JunitTesting;

import Colored_Matrix_Management.Matrix_Variable;
import Puzzle_initFromFile.initMatrix;
import Puzzle_initFromFile.initTXT;

public class run {

	public static void main(String[] args) {
		initTXT tt =new initTXT("input2.txt");
		initMatrix mm=new initMatrix(tt.getMatSIZE(), tt.getGame_Matrix(), tt.getBlackBlocks(), tt.getRedBloacks());
		
		System.out.println(tt.getMatSIZE());
		System.out.println(tt.getBlackBlocks());		
		System.out.println(tt.getRedBloacks());
		System.out.println(tt.getGame_Matrix()+"\n");
		
		Matrix_Variable [][] vv=mm.getThePuzzle();
		for(int i=0;i<vv.length;i++) {
			for(int j=0;j<vv[0].length;j++) {
				System.out.print(vv[i][j].getNum()+","+vv[i][j].getColor()+"|");
			}
			System.out.println();
		}
	}

}
