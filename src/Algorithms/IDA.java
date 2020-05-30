package Algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;

import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;
import DataStructure.Direction;
import DataStructure.Oprerator;
import DataStructure.State_Node;

public class IDA {

	/**
	 * 
	 * @return
	 */
	public State_Node IDA() {
		Heuristic_Comperator H_comperator = new Heuristic_Comperator();
		PriorityQueue<State_Node> howNext = new PriorityQueue<State_Node>(H_comperator);
		Hashtable<Oprerator, State_Node> openList = new Hashtable<Oprerator, State_Node>();
		//Hashtable<Oprerator, State_Node> closeList = new Hashtable<Oprerator, State_Node>();

		howNext.add(this.start);
		openList.put(this.start.getOperation(), this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();
			if(n.equals(this.goal)) {return n;}

			//closeList.put(n.getOperation(), n);
			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				Matrix_Variable [][] tempNmatrix = n.deepCopy();
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);
				countingNodes();
				if(!openList.contains(child)) {
					howNext.add(child);
					openList.put(tempOp, child);
				}
				else if(openList.contains(child)) {
					openList.remove(child.getOperation());
					openList.put(tempOp, child);
				}
			}
		}

		return null;
	}

	//****************** Public methods *****************

	/**
	 * This function counting the number of nudes that created be the Algorithm
	 * @return
	 */
	public int countingNodes () {
		this.NodesNum++;
		return this.NodesNum;
	}

	//****************** Private methods *****************

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;
	private int NodesNum;

	//****************** Constructors *****************

	public IDA (State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.NodesNum=0;
	}

}
