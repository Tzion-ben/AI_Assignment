package Algorithms;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;
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
		//using a Queue based on the List  
		ConcurrentLinkedQueue<State_Node> howNext = new ConcurrentLinkedQueue<State_Node>();

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<Oprerator, State_Node> openList = new Hashtable<Oprerator, State_Node>();

		//this hash table will contain all the states that was generated and was expected 
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
				raiseNumberOfNodes();

				//if it is a new state that wasn't even generated so put it at the open list 
				if(!closeList.contains(child) && !openList.contains(child)) {

					if(child.equals(this.goal)) {return child;}
					howNext.add(child);
					openList.put(tempOp, child);
				}
			}
		}

		return null;
	}


	/**
	 * 
	 * @return
	 */
	public State_Node DFID() {
		for(int depth=1 ; depth<Integer.MAX_VALUE ; depth++) {

			/**
			 * this hash table is for loop Avoidance, because if we work on a branch and expected some
			 * state but at the future we can maybe generate or expected this state agsin because it can be
			 * a child of other future state so it will make a infinite loop
			 */
			Hashtable<Oprerator, State_Node> loopAvoidance = new Hashtable<Oprerator, State_Node>();

			//Initialize a cutOff state that will use the number of the operator of it to true==1 or false==0
			State_Node result = Limited_DFS(this.start, depth , loopAvoidance);

			/**
			 * if the result.GetOperation.GetNum is not -1 that is mean a cutoff (i am decided thath -1 is mark a cutOff)
			 * that means the algo stooped because the limit came to zero but still not found a path than return the goal
			 * with a pointers to the path at it or null if there is no path at all 
			 * */
			if(result.getOperation().getNum()!=-1) {return result;}//like: if(result != cutOff)
		}

		return null;
	}

	/**
	 *....
	 * @param depth
	 * @param loopAvoidance Hashtable
	 * @return
	 */
	private State_Node Limited_DFS(State_Node n, int limit ,Hashtable<Oprerator, State_Node> loopAvoidance) {
		State_Node cutOff =  new State_Node(null, new Oprerator(-1, Direction.N, Color.E),-1, null);
		if(n.equals(this.goal)) {return n;}//if came to the goal at the recursion

		/**
		 * if we came here and not found the goal yet and the limit us equal to ZERO ' it might be because
		 * the limit so we will return cutOff and not null
		 */
		else if(limit == 0) {return cutOff;}

		else {
			//put the state to the hash table to know that it work'w on
			//some brunch so if at the future this state will expected again it;s a loop
			loopAvoidance.put(n.getOperation(), n);

			//cutOff==False by sets the number of the operator of ths state to 0
			cutOff.getOperation().setNum(0);

			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			//returns null if there is no allow operators any more so no have path
			if(allowedOprerators.isEmpty()) {return null;}

			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				Matrix_Variable [][] tempNmatrix = n.deepCopy();
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);
				raiseNumberOfNodes(); //counting the number of generated nodes

				if(!loopAvoidance.contains(child)) {
					State_Node result = Limited_DFS(child, (limit-1), loopAvoidance); //running recursively

					//it means that we came to the limit and no path yet, and not found the goal yet
					if(result.getOperation().getNum() == 0) {cutOff.getOperation().setNum(1);}  // cutOff->true

					//if it's not fail and not a cutOff that we found the result
					else if(result.getOperation().getNum() != -1 ) {return result;}	
				}
				/**
				 * else --> it's goe's to the next operator it ths state is at the loop Avoidance hash Table
				 * to not create a loop
				 */
			}//end for

			//ends work on this brunch so can remove the state from that hash table
			loopAvoidance.remove(n.getOperation());

			if(cutOff.getOperation().getNum() == 1) {return cutOff;}  //+1 means that --> (cutOff==true)
			else{//()if (cutOff==false)
				cutOff.getOperation().setNum(-1);//-1 mean's fail
				return cutOff;
			}
		}
	}

	/*****************************************************************************************************
	 *****************************************Informed Algorithms*****************************************
	 *****************************************************************************************************/

	/**
	 * This algorithm is represent A*.
	 * @return
	 */
	public State_Node A_Star() {
		//a comparator for the PQ , to create a priority
		Heuristic_Comperator F_comperator = new Heuristic_Comperator();

		//PQ based on the F of every state, the state with the minimum F will go out first.
		PriorityQueue<State_Node> howNext = new PriorityQueue<State_Node>(F_comperator);

		//this hash table will contain all the states that was generated but still wasn't expected 
		Hashtable<Matrix_Variable [][], State_Node> openList = new Hashtable<Matrix_Variable [][], State_Node>();


		//this hash table will contain all the states that was generated and was expected 
		Hashtable<Matrix_Variable [][], State_Node> closeList = new Hashtable<Matrix_Variable [][], State_Node>();

		howNext.add(this.start);
		openList.put(this.start.getStateMatrix(), this.start);

		while(!openList.isEmpty()) {
			State_Node n = howNext.poll();
			if(n.equals(this.goal)) {return n;}

			closeList.put(n.getStateMatrix(), n);
			ArrayList<Oprerator> allowedOprerators= n.getOprerators();
			for(int i=0 ; i<allowedOprerators.size() ; i++) {
				Oprerator tempOp = allowedOprerators.get(i);
				Matrix_Variable [][] tempNmatrix = n.deepCopy();
				State_Node child = new State_Node(tempNmatrix, tempOp, n.getG(), n);

				int temp_F_Child = child.getF();

				raiseNumberOfNodes();

				//if it is a new state that wasn't even generated so put it at the open list 
				//and at the PQ
				if(!closeList.contains(child) && !openList.contains(child)) {
					howNext.add(child);
					openList.put(tempNmatrix, child);
				}

				//else: if the state is generated and we will check if it's on the 
				//branch that we work so we will check if it is a better path
				else if(openList.contains(child)) {
					boolean sssss=openList.containsKey(child.getStateMatrix());
					int c=10;
					//					if(temp.getF()>child.getF()) {
					//						openList.remove(allowedOprerators.get(i));
					//						openList.put(allowedOprerators.get(i), child);
					//					}
				}
			}
		}

		return null;
	}


	//****************** Public methods *****************

	/**
	 * This function raising the number of Nodes that created be the Algorithm
	 * @return
	 */
	public int raiseNumberOfNodes () {
		this.NodesNum++;
		return this.NodesNum;
	}

	/**
	 * This function reducing the number of Nodes that created be the Algorithm
	 * @return
	 */
	public int reduceNumberOfNodes () {
		this.NodesNum--;
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
