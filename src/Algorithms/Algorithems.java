package Algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;
import DataStructure.Direction;
import DataStructure.Oprerator;
import DataStructure.State_Node;

public class Algorithems {

	/*****************************************************************************************************
	 ****************************************Uninformed Algorithms****************************************
	 *****************************************************************************************************/

	/**
	 * 
	 * @return
	 */
	public State_Node  BFS() {
		//PQ based on the F of every state, the state with the minimum F will go out first
		ConcurrentLinkedQueue<State_Node> howNext = new ConcurrentLinkedQueue<State_Node>();

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<Oprerator, State_Node> openList = new Hashtable<Oprerator, State_Node>();

		/**
		 * this hash table will contain all the states that was generated and was expected 
		 */
		Hashtable<Oprerator, State_Node> closeList = new Hashtable<Oprerator, State_Node>();

		howNext.add(this.start);
		openList.put(this.start.getOperation(), this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();

			closeList.put(n.getOperation(), n);
			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			
			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}
			
			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				Matrix_Variable [][] tempNmatrix = n.deepCopy();
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);
				countingNodes();

				//if it is a new state that wasn't even generated so put it at the open list 
				if(!closeList.contains(child) && !openList.contains(child)) {
					
					if(child.equals(this.goal)) {return n;}
					howNext.add(child);
					openList.put(tempOp, child);
				}
			}
		}

		return null;
	}

	/*****************************************************************************************************
	 *****************************************Informed Algorithms*****************************************
	 *****************************************************************************************************/

	/**
	 * This algorithm is represent A*.
	 * @return
	 */
	public State_Node A_Star() {
		Heuristic_Comperator F_comperator = new Heuristic_Comperator();
		//PQ based on the F of every state, the state with the minimum F will go out first.
		PriorityQueue<State_Node> howNext = new PriorityQueue<State_Node>(F_comperator);
		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<Oprerator, State_Node> openList = new Hashtable<Oprerator, State_Node>();

		/**
		 * this hash table will contain all the states that was generated and was expected 
		 */
		Hashtable<Oprerator, State_Node> closeList = new Hashtable<Oprerator, State_Node>();

		howNext.add(this.start);
		openList.put(this.start.getOperation(), this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();
			if(n.equals(this.goal)) {return n;}

			closeList.put(n.getOperation(), n);
			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				Matrix_Variable [][] tempNmatrix = n.deepCopy();
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);
				countingNodes();

				//if it is a new state that wasn't even generated so put it at the open list 
				//and at the PQ
				if(!closeList.contains(child) && !openList.contains(child)) {
					howNext.add(child);
					openList.put(tempOp, child);
				}

				//else: if the state is generated and we will check if it's on the 
				//branch that we work so we will check if it is a better path
				else if(openList.contains(child) && openList.get(child.getOperation()).getF()>child.getF()) {
					openList.remove(child.getOperation());
					openList.put(tempOp, child);
				}
			}
		}

		return null;
	}


	//****************** Public methods *****************

	/**
	 * This function counting the number of Nodes that created be the Algorithm
	 * @return
	 */
	public int countingNodes () {
		this.NodesNum++;
		return this.NodesNum;
	}
	 
	/**
	 * This functions  make the counter to zero for the new algorithm
	 */
	public void zeroCountingNodes () {
		this.NodesNum=0;
	}

	//****************** Private methods *****************

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;
	private int NodesNum;

	//****************** Constructors *****************

	public Algorithems (State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.NodesNum=0;
	}

}
