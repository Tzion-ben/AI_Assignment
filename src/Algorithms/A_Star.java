package Algorithms;

import java.util.Hashtable;
import java.util.PriorityQueue;
import DataStructure.Color;
import DataStructure.Heuristic_Comperator;
import DataStructure.State_Node;

public class A_Star implements Algorithm{

	/************************************************************************************************
	 * This algorithm is represent A*.
	 * @return
	 ************************************************************************************************/
	@Override
	public State_Node Search_Goal_Algorithm() {
		//a comparator for the PQ , to create a priority
		Heuristic_Comperator F_comperator = new Heuristic_Comperator();

		//PQ based on the F of every state, the state with the minimum F will go out first.
		PriorityQueue<State_Node> howNext = new PriorityQueue<State_Node>(F_comperator);

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<String,State_Node> openList = new Hashtable<String,State_Node>();

		//this hash table will contain all the states that was generated and was expected 
		Hashtable<String,State_Node> closeList = new Hashtable<String,State_Node>();

		howNext.add(this.start);
		openList.put(this.start.key(), this.start);

		while(!howNext.isEmpty()) {
			State_Node n = howNext.poll();
			if(n.key().equals(this.goal.key())) {return n;}

			closeList.put(n.key(),n);
			openList.remove(n.key());

			Hashtable<Integer, String> allowedOprerators= n.getOprerators();
			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}

			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(), n,
							n.getMinouOne_i(),n.getMinouOne_j(),n.getPathSoFar(),NodesNum);
					NodesNum++;

					//if it is a new state that wasn't even generated so put it at the open list 
					//and at the PQ
					if(!closeList.containsKey(child.key()) && !openList.containsKey(child.key())) {
						howNext.add(child);
						openList.put(child.key(),child);
					}

					else if(openList.containsKey(child.key())) {
						if(openList.get(child.key()).getF() > child.getF()) {
							openList.remove(child.key());
							State_Node tempChile = openList.get(child.key());
							howNext.remove(tempChile);
							
							howNext.add(child);
							openList.put(child.key(), child);
						}
					}
					//else: if the state is generated and we will check if it's on the 
					//branch that we work so we will check if it is a better path

				}
			}
		}

		return null;
	}

	//********************************************************************************

	//********simple getters
	public int getNodesNum() {return NodesNum;}

	public int getIteration() {return iteration;}

	//****************** Private Data *****************

	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;
	private int iteration;

	//****************** Constructors *****************
	public A_Star (State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;
		this.iteration=0;
	}

}