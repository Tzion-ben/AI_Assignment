package DataStructure;

import java.util.Hashtable;
import Colored_Matrix_Management.Matrix_Variable;

public class State_Node implements State_Data {

	@Override
	public int getStateId() {
		return this.stateId;
	}

	@Override
	public Matrix_Variable [][] getStateMatrix() {
		return this.StateMatrix;
	}

	@Override
	public int getHeuristicFunctions() {
		return this.HeuristicFunctions;
	}

	@Override
	public State_Data getFatherPointer() {
		return this.fatherPointer;
	}

	@Override
	public double getWeightUntilNow() {
		return this.WeightUntilNow;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public Hashtable<Integer, Integer> getRedBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<Integer, Integer> getBlacBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<Integer, Integer> getGreenBlocks() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//****************** Private Methods and Data *****************

	private int stateId;
	private Matrix_Variable [][] StateMatrix;
	private int HeuristicFunctions;
	private State_Node fatherPointer;
	private double WeightUntilNow;
	private String info;

	private Hashtable<Integer,Integer> RedBlocks;
	private Hashtable<Integer,Integer> GreenBlocks;
	private Hashtable<Integer,Integer> BlackBlocks;

	//****************** Constructors *****************

	public State_Node(int stateId, Matrix_Variable [][] StateMatrix, int HeuristicFunctions, State_Node fatherPointer,
			double WeightUntilNow, String info) {
		this.stateId=stateId;
		this.StateMatrix=StateMatrix;
		this.HeuristicFunctions=HeuristicFunctions;
		this.fatherPointer=fatherPointer;
		this.WeightUntilNow=WeightUntilNow;
		this.info=info;
	}
}