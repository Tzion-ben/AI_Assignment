package Algorithms;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;
import DataStructure.Color;
import DataStructure.State_Node;

public class BFS {

	public State_Node BFS() {
		//using a Queue based on the List  
		ConcurrentLinkedQueue<State_Node> howNext = new ConcurrentLinkedQueue<State_Node>();

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<Integer, State_Node> openList = new Hashtable<Integer, State_Node>();

		//this hash table will contain all the states that was generated and was expected 
		Hashtable<String, State_Node> closeList = new Hashtable<String, State_Node>();
		int j=0;
		howNext.add(this.start);
		openList.put(j, this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();
			closeList.put(n.getOperation(), n);
			Hashtable<Integer, String> allowedOprerators= n.getOprerators();

			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}

			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(), n
							,n.getMinouOne_i(),n.getMinouOne_j(),n.getPathSoFar());
					NodesNum++;

					//if it is a new state that wasn't even generated so put it at the open list 
					if(!closeList.contains(child) && !openList.contains(child)) {

						if(child.equals(this.goal)) {return child;}
						howNext.add(child);
						openList.put(j, child);
						j++;
					}
				}  
			}
			j++;
		}

		return null;
	}

	//****************** Private Methods***************
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	private int countCode(String str) {
		int result = 0;
		String [] tempStr = str.split("-");
		for(int i=0 ; i<tempStr.length ; i+=2) {
			result+=Integer.valueOf(tempStr[i]);
		}
		
		return result;
	}

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;

	//****************** Constructors *****************

	public int getNodesNum() {
		return NodesNum;
	}



	public BFS(State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;
	}


}