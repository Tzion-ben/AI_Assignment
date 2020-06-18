package Algorithms;

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;
import DataStructure.Color;
import DataStructure.Heuristic_Comperator;
import DataStructure.State_Node;
import Tools.ColorValidator;
import Tools.OpertionHelper;

public class DFBnB implements Algorithm {

	/************************************************************************************************
	 * This function represent a DFBnB Algorithm.
	 ************************************************************************************************/
	@Override
	public State_Node Search_Goal_Algorithm() {
		/**
		 * this hash table is for loop Avoidance, because if we work on a branch and expected some
		 * state but at the future we can maybe generate or expected this state agsin because it can be
		 * a child of other future state so it will make a infinite loop
		 */
		Hashtable<String, State_Node> loopAvoidance = new Hashtable<String, State_Node>();

		Stack<State_Node> howNext = new Stack<State_Node>();

		// the thrashHold will be sets depend on the calculate at the setTreshHold method 
		int treshHold= setTreshHold();

		//for the result to return it, if there is no path, will return's null
		State_Node result = null;

		this.start.setTag(0);
		howNext.add(this.start);
		loopAvoidance.put(this.start.key(), this.start);

		while(!howNext.isEmpty()) { 

			State_Node n = howNext.pop();

			if(n.getTag() == 1)//if the node marked as "out"
				loopAvoidance.remove(n.key());
			else {
				n.setTag(1);//marking the node as "out"
				howNext.push(n);

				//apply all the allowed Operators on n in increasing order
				PriorityQueue<State_Node> N = sortNodes(n);

				while(!N.isEmpty()) {
					State_Node N_Node = N.peek();

					/**
					 * if state from N is bigger then the trashHold so then remove it and all
					 * the states after it because it in increasing oreder so thy eith more bigger F
					 */
					if(N_Node.getF()>= treshHold)
					{N.clear();}

					//if the node marked as "out" so we work on this branch and it will create al loop so to avoid loops
					//we will remove this State Node
					else if(loopAvoidance.containsKey(N_Node.key()) && (loopAvoidance.get(N_Node.key()).getTag() == 1))
					{N.poll();}

					//if the node marked as "not out" 
					else if(loopAvoidance.containsKey(N_Node.key()) && (loopAvoidance.get(N_Node.key()).getTag() == 0)) {
						if(loopAvoidance.get(N_Node.key()).getF() <= N_Node.getF()) {
							N.poll();
						}else {
							loopAvoidance.remove(N_Node.key());
							removeFromStack(howNext, N_Node);
						}
					}

					//if we came to the goal we set the treshHold to it's F
					else if(N_Node.key().equals(this.goal.key())) {
						treshHold = N_Node.getF();
						result = N_Node;
						N.clear();//clear all the PQ
					}

					//if all this not worked before so insert all the nodes that left at th N to 
					//howNext Stack and to the hashTable
					else {
						howNext.add(N_Node);
						loopAvoidance.put(N_Node.key(), N_Node);
					}
				}
			}
		}

		return result;
	}

	/************************************************************************************************
	 * This function calculate the treshHold depend on the size of the matrix, if it less then
	 * 12 without the black block numbers so the treshHold will be n factorial
	 * If it more than 12 so the trashHold will be infinity(Integer.MaxValue) 
	 * @return
	 ************************************************************************************************/
	private int setTreshHold () {
		int treshHold = Integer.MAX_VALUE;

		int SIZE = this.start.getStateMatrix().length * this.start.getStateMatrix()[0].length;
		if(colorValidator.getNumWithOutBlackBlocks() <12) {treshHold = opertionHelper.setMatrixFactorial(SIZE);}

		return treshHold;
	}

	/************************************************************************************************
	 * This function expected all the children of a given State_Node and sort's them based on 
	 * there F, in increasing order in PriorityQueue
	 * @return a PriorityQueue of the children in increasing order
	 ************************************************************************************************/
	private PriorityQueue<State_Node> sortNodes (State_Node n){
		Hashtable<Integer, String> allowedOprerators= n.getOprerators();

		//a comparator for the MAX-PQ , to create a MAX-priority
		Heuristic_Comperator F_comperator = new Heuristic_Comperator();
		PriorityQueue<State_Node> sortN = new PriorityQueue<State_Node>(F_comperator.reversed());

		if(allowedOprerators != null) {
			for(int i=0; i<4 ;i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(), n,
							n.getMinouOne_i(),n.getMinouOne_j(),NodesNum);
					NodesNum++;

					sortN.add(child);
				}
			}
		}

		return sortN;
	}

	/************************************************************************************************
	 * This function removes a State_Node from the stack
	 ************************************************************************************************/
	private Stack<State_Node> removeFromStack (Stack<State_Node> original , State_Node toRemove) {
		Stack<State_Node> tempSt = new Stack<State_Node>();
		while(!original.isEmpty() && (original.peek().key() != toRemove.key())) {tempSt.push(original.pop());}

		original.pop();
		while(tempSt.isEmpty()) {original.push(tempSt.pop());}

		return original;
	}

	/************************************ Private Methods *******************************************/
	/************************************************************************************************
	 * This function sets a classes that will help as to make some operations on the 
	 * DFBnB Algorithm
	 ************************************************************************************************/
	private void setHelpClass() {
		opertionHelper = new OpertionHelper(this.numbersColors);
		colorValidator = new ColorValidator(this.numbersColors);
	}

	//********simple getters
	public int getNodesNum() {return NodesNum;}

	//****************** Private Data *****************
	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;

	private ColorValidator colorValidator;
	private OpertionHelper opertionHelper;

	//****************** Constructors *****************
	public DFBnB(State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=0;

		setHelpClass();
	}

}
