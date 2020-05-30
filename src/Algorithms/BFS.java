package Algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;

import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;
import DataStructure.Direction;
import DataStructure.Oprerator;
import DataStructure.State_Node;

public class BFS {

	/**
	 * 
	 * @return
	 */
	public State_Node BFS() {
		Huristic_Comperator comperator = new Huristic_Comperator();
		PriorityQueue<State_Node> howNext = new PriorityQueue<State_Node>(comperator);
		Hashtable<Oprerator, State_Node> openList = new Hashtable<Oprerator, State_Node>();
		Hashtable<Oprerator, State_Node> closeList = new Hashtable<Oprerator, State_Node>();

		howNext.add(this.start);
		openList.put(this.start.getOperation(), this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();
			if(n.equals(this.goal)) {return n;}
			
			closeList.put(n.getOperation(), n);
			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			Matrix_Variable [][] tempNmatrix = n.getStateMatrix();
			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);
				if(closeList.contains(child) && openList.contains(child)) {
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

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;

	//****************** Constructors *****************

	public BFS (State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
	}
}
