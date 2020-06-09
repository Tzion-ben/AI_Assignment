package Algorithms;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;
import DataStructure.*;

public class BFS implements Algorithm  {

	public State_Node Search_Goal_Algorithm() {
		//using a Queue based on the List  
		ConcurrentLinkedQueue<State_Node> howNext = new ConcurrentLinkedQueue<State_Node>();

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<String,State_Node> openList = new Hashtable<String,State_Node>();

		//this hash table will contain all the states that was generated and was expected 
		Hashtable<String,State_Node> closeList = new Hashtable<String,State_Node >();

		howNext.add(this.start);
		openList.put(this.start.key(),this.start);

		while(!howNext.isEmpty()) {
			State_Node n = howNext.poll();

			//openList.remove(n.hash());
			closeList.put(n.key(),n);

			Hashtable<Integer, String> allowedOprerators= n.getOprerators();

			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}
			iteration++;
			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(),
							n,n.getMinouOne_i(),n.getMinouOne_j(),n.getPathSoFar(),iteration);
					NodesNum++;

					//if it is a new state that wasn't even generated so put it at the open list 
					if(!closeList.containsKey(child.key()) && !openList.containsKey(child.key())) {
						if((child.key().equals(this.goal.key())) ){return child;}
						howNext.add(child);
						openList.put(child.key(),child);
					}
				}
			}
		}
		return null;
	}

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;
	private int iteration;


	//****************** Constructors *****************

	public int getNodesNum() {return NodesNum;}

	public int getIteration() {return iteration;}

	public BFS(State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;
		this.iteration=0;
	}
}