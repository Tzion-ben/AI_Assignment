package Algorithms;
/**
 * This class represent the IDA* Algorithm
 * @author Tzion
 */
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;
import DataStructure.Color;
import DataStructure.State_Node;

public class IDA_Star implements Algorithm {

	/**
	 * This Algorithm is represent a IDA* Algorithm
	 */
	@Override
	public State_Node Search_Goal_Algorithm() {

		/**
		 * this hash table is for loop Avoidance, because if we work on a branch and expected some
		 * state but at the future we can maybe generate or expected this state again because it can be
		 * a child of other future state so it will make a infinite loop
		 */
		Hashtable<String, State_Node> loopAvoidance = new Hashtable<String, State_Node>();

		//this stack will contain all the states that was generated but still wasn't expected 	
		Stack<State_Node> openListSTACK = new Stack<State_Node>();

		//the start of the TrashHold is the heuristic function of the start Node
		int treshHold = this.start.getH();
		this.start.setTag(0);
		while(treshHold != Integer.MAX_VALUE) {
			int minF = Integer.MAX_VALUE; //the minimum F now is infinity at the start
			this.start.setTag(0);//starting again

			openListSTACK.push(this.start); 
			loopAvoidance.put(this.start.key(), this.start);

			while(!openListSTACK.isEmpty()) {
				iteration_Counter++;

				State_Node n = openListSTACK.pop();
				if(n.getTag() == 1)//if the node marked as "out"
					loopAvoidance.remove(n.key());
				else {
					n.setTag(1);//marking the node as "out"
					openListSTACK.push(n);

					Hashtable<Integer, String> allowedOprerators= n.getOprerators();
					//returns null if there is no path
					if(allowedOprerators.isEmpty()) {return null;}

					//if have to print the openList 
					if(!openListSTACK.isEmpty() && withOpen) {printOpenList(openListSTACK);}

					for(int i=0 ; i<4 ; i++) {
						if(allowedOprerators.containsKey(i)) {
							String tempOp = allowedOprerators.get(i);
							int [][] tempNmatrix = n.deepCopy();
							State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(), n,
									n.getMinouOne_i(),n.getMinouOne_j(),NodesNum);
							NodesNum++;

							//if the f(n) is bigger then the treshHold then move to the next Operator (cut and move on...) 
							if(child.getF() > treshHold) {
								minF = Math.min(minF, child.getF());
								continue;
							}

							//if the child is in the hash and marked as "out" (1) so we catch a loop so move to next
							//operator (cut and move on...)
							if(loopAvoidance.containsKey(child.key())) {
								if(loopAvoidance.get(child.key()).getTag() == 1) {continue;}

								//if the child is in the hash and marked as "not out" (-1) so we NOT catch a loop so move to next
								//and the F of this state is less good so remove it
								if(loopAvoidance.get(child.key()).getTag() == -1) {
									if(loopAvoidance.get(child.key()).getF() > child.getF()) {
										loopAvoidance.remove(child.key());
										removeFromStack(openListSTACK, child);
									}

									//else --> move to the next Operator (cut and move on...)
									else {continue;}
								}
							}

							if(child.key().equals(this.goal.key())) {return child;}//if we found the goal so return is and done

							openListSTACK.push(child);
							loopAvoidance.put(child.key(), child);
						}
					}
				}
			}
			treshHold = minF;
		}

		return null;
	}

	/**
	 * This function removes a given State_Node from the stack
	 * @return the given stack without the given State_Node
	 */
	private Stack<State_Node> removeFromStack (Stack<State_Node> original , State_Node toRemove) {
		Stack<State_Node> tempSt = new Stack<State_Node>();
		while(!original.isEmpty() && (original.peek().key() != toRemove.key())) {tempSt.push(original.pop());}

		original.pop();
		while(tempSt.isEmpty()) {original.push(tempSt.pop());}

		return original;
	}

	/**
	 * This function will print the OpenList if needed
	 */
	private void printOpenList (Stack<State_Node> openList) {
		if(!openList.isEmpty() && withOpen) {
			Iterator<State_Node> iter = openList.iterator();
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

	//********************************** Private Data **********************************
	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;

	private int iteration_Counter;
	private boolean withOpen;

	//********************************** Constructors **********************************
	public IDA_Star (State_Node start, State_Node goal, boolean withOpen) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;//because the Start State is already created out of the Algorithm

		iteration_Counter = -1;
		this.withOpen=withOpen;
	}

}
