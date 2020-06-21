package Algorithms;
import java.util.Collection;
/**
 * This class represent the BFS Algorithm
 * @author Tzion
 */
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import DataStructure.*;

public class BFS implements Algorithm  {

	/**
	 * This Algorithm represent the BFS Algorithm. 
	 */
	public State_Node Search_Goal_Algorithm() {
		//using a Queue based on the List  
		ConcurrentLinkedQueue<State_Node> openListQ = new ConcurrentLinkedQueue<State_Node>();

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<String,State_Node> openList = new Hashtable<String,State_Node>();

		//this hash table will contain all the states that was generated and was expected 
		Hashtable<String,State_Node> closeList = new Hashtable<String,State_Node >();

		openListQ.add(this.start);
		openList.put(this.start.key(),this.start);

		while(!openListQ.isEmpty()) {
			iteration_Counter++;

			State_Node n = openListQ.poll();
			closeList.put(n.key(),n);

			Hashtable<Integer, String> allowedOprerators= n.getOprerators();

			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}
			
			//if have to print the openList 
			if(!openList.isEmpty() && withOpen) {printOpenList(openList);}

			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(),
							n,n.getMinouOne_i(),n.getMinouOne_j(),NodesNum);
					NodesNum++;

					//if it is a new state that wasn't even generated so put it at the open list 
					if(!closeList.containsKey(child.key()) && !openList.containsKey(child.key())) {
						if((child.key().equals(this.goal.key())) ){return child;}
						openListQ.add(child);
						openList.put(child.key(),child);
					}
				}
			}
		}

		return null;
	}

	/**
	 * This function will print the OpenList if needed
	 */
	private void printOpenList (Hashtable<String, State_Node> openList) {
		if(!openList.isEmpty() && withOpen) {
			Collection<State_Node> collec = openList.values();
			Iterator<State_Node> iter = collec.iterator();
			while(iter.hasNext()) {
				State_Node toPrint = iter.next(); 
				if(!toPrint.getOperation().equals("0-N")) {
					System.out.println("Iteration number "+iteration_Counter +" : ");
					toPrint.printState();
				}
			}
		}
	}

	//********************************** simple getters **********************************
	public int getNodesNum() {return NodesNum;}

	//*********************************** Private Data ***********************************
	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;

	private int iteration_Counter;
	private boolean withOpen;

	//************************************ Constructor ***********************************
	public BFS(State_Node start, State_Node goal, boolean withOpen) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;//because the Start State is already created out of the Algorithm

		iteration_Counter = -1;
		this.withOpen=withOpen;
	}

}