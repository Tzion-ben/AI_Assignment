package DataStructure;

import java.util.ArrayList;

public class State_Node implements State_Data {

	@Override
	public int getStateId() {
		return this.getStateId();
	}

	@Override
	public int[][] getStateMatrix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int HeuristicFunctions() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public State_Data fatherPointer() {
		// TODO Auto-generated method stub
		return this.fatherPointer;
	}

	@Override
	public double getWeightUntilNow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Integer> getRedBlocks() {
		int SIZE_1D=this.StateMatrix.length;
		if(SIZE_1D>0) {
			int SIZE_2D=this.StateMatrix[0].length;
			for(int i=0 ; i<SIZE_1D ; i++) {
				for(int j=0 ; j<SIZE_2D ; j++) {
					
				}
			}
		}
		return this.RedBlocks;
	}

	@Override
	public ArrayList<Integer> getGreenBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getBlacBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	//****************** Private Methods and Data *****************

	private int stateId;
	private int [][] StateMatrix;
	private int HeuristicFunctions;
	private State_Node fatherPointer;
	private double WeightUntilNow;
	private String info;

	private ArrayList<Integer> RedBlocks;
	private ArrayList<Integer> GreenBlocks;
	private ArrayList<Integer> BlackBlocks;

	//****************** Constructors *****************

	public State_Node(int stateId, int [][] StateMatrix, int HeuristicFunctions, State_Node fatherPointer,
			double WeightUntilNow, String info) {
		this.stateId=stateId;
		this.StateMatrix=StateMatrix;
		this.HeuristicFunctions=HeuristicFunctions;
		this.fatherPointer=fatherPointer;
		this.WeightUntilNow=WeightUntilNow;
		this.info=info;
	}

}
