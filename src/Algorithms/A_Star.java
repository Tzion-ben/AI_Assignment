package Algorithms;
/**
 * This class represent the A* Algorithm
 * @author Tzion
 */
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import DataStructure.Color;
import DataStructure.Heuristic_Comperator;
import DataStructure.State_Node;

public class A_Star implements Algorithm{

	/**
	 * This Algorithm is represent A*.
	 */
	@Override
	public State_Node Search_Goal_Algorithm() {
		//a comparator for the PQ , to create a priority
		Heuristic_Comperator F_comperator = new Heuristic_Comperator();

		//PQ based on the F of every state, the state with the minimum F will go out first.
		PriorityQueue<State_Node> openListPQ = new PriorityQueue<State_Node>(F_comperator);

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<String,State_Node> openList = new Hashtable<String,State_Node>();

		//this hash table will contain all the states that was generated and was expected 
		Hashtable<String,State_Node> closeList = new Hashtable<String,State_Node>();

		openListPQ.add(this.start);
		openList.put(this.start.key(), this.start);

		while(!openListPQ.isEmpty()) {
			iteration_Counter++;

			State_Node n = openListPQ.poll();
			if(n.key().equals(this.goal.key())) {return n;}

			closeList.put(n.key(),n);
			openList.remove(n.key());

			Hashtable<Integer, String> allowedOprerators= n.getOprerators();
			//returns null if there is no path
			if(allowedOprerators.isEmpty()) {return null;}

			//if have to print the openList 
			if(!openList.isEmpty() && withOpen) {printOpenList(openList);}

			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(), n,
							n.getMinouOne_i(),n.getMinouOne_j(),NodesNum);
					NodesNum++;

					//if it is a new state that wasn't even generated so put it at the open list 
					//and at the PQ
					if(!closeList.containsKey(child.key()) && !openList.containsKey(child.key())) {
						openListPQ.add(child);
						openList.put(child.key(),child);
					}

					else if(openList.containsKey(child.key())) {
						if(openList.get(child.key()).getF() > child.getF()) {


							System.out.println(child.toString());

							openList.remove(child.key());
							State_Node tempChile = openList.get(child.key());
							openListPQ.remove(tempChile);

							openListPQ.add(child);
							openList.put(child.key(), child);
						}
					}

					/**
					 *else: if the state is generated and we will check if it's on the 
					 *branch that we work so we will check if it is a better path 
					 */
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
	public A_Star (State_Node start, State_Node goal, boolean withOpen) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;//because the Start State is already created out of the Algorithm

		iteration_Counter = -1;
		this.withOpen=withOpen;
	}

}