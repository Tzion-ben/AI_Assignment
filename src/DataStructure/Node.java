package DataStructure;

import java.util.ArrayList;

public class Node implements State_Data {

	@Override
	public int getStateId() {
		// TODO Auto-generated method stub
		return 0;
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
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
	private State_Data fatherPointer;
	private double WeightUntilNow;
	private String info;
	
	private ArrayList<Integer> getRedBlocks;
	private ArrayList<Integer> getGreenBlocks;
	private ArrayList<Integer> getBlackBlocks;
	
	//****************** Constructors *****************

	public Node(int id) {
		this.id=id;//the id of vertex ,i put it to -1 because id can be i to infinity 
		this.weigth=0;
		this.info=null;//for meta data in the algorithms
		this.tag=0;//to make a sign in the algorithms 
	}

}
