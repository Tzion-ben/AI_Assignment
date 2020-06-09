package Algorithms;

import java.util.Hashtable;
import DataStructure.Color;
import DataStructure.State_Node;

public class DFID implements Algorithm {

	@Override
	public State_Node Search_Goal_Algorithm() {
		for(int depth=1 ; depth<Integer.MAX_VALUE; depth++) {

			/**
			 * this hash table is for loop Avoidance, because if we work on a branch and expected some
			 * state but at the future we can maybe generate or expected this state agsin because it can be
			 * a child of other future state so it will make a infinite loop
			 */
			Hashtable<Integer, State_Node> loopAvoidance = new Hashtable<Integer, State_Node>();

			//Initialize a cutOff state that will use the number of the operator of it to true==1 or false==0
			State_Node result = Limited_DFS(this.start, depth , loopAvoidance);

			/**
			 * if the result.GetOperation.GetNum is not -1 that is mean a cutoff (i am decided thath -1 is mark a cutOff)
			 * that means the algo stooped because the limit came to zero but still not found a path than return the goal
			 * with a pointers to the path at it or null if there is no path at all 
			 * */
			if(result.getNumDirection()!=0) {return result;}//like: if(result != cutOff)
		}

		return null;
	}

	/**
	 *....
	 * @param depth
	 * @param loopAvoidance Hashtable
	 * @return
	 */
	private State_Node Limited_DFS(State_Node n, int limit ,Hashtable<Integer, State_Node> loopAvoidance) {
		int [][] toStart = this.start.deepCopy();
		State_Node cutOff =  new State_Node(toStart, this.numbersColors,"0-N",-1,-1, null, -1, -1, null,0);
		if(n.key() == (this.goal.key())) {return n;}//if came to the goal at the recursion

		/**
		 * if we came here and not found the goal yet and the limit us equal to ZERO ' it might be because
		 * the limit so we will return cutOff and not null
		 */
		else if(limit == 0) {return cutOff;}

		else {
			//put the state to the hash table to know that it work'w on
			//some brunch so if at the future this state will expected again it;s a loop
			loopAvoidance.put(n.key(), n);

			//cutOff--> False by sets the number of the operator of the state to 2
			cutOff.setNumDirection(2);

			Hashtable<Integer, String> allowedOprerators= n.getOprerators();
			//returns null if there is no allow operators any more so no have path
			if(allowedOprerators.isEmpty()) {return null;}
			for(int i=0 ; i<4 ; i++) {
				if(allowedOprerators.containsKey(i)) {
					String tempOp = allowedOprerators.get(i);
					int [][] tempNmatrix = n.deepCopy();
					State_Node child = new State_Node(tempNmatrix,this.numbersColors, tempOp, n.getG(),n.getH(),
							n,n.getMinouOne_i(),n.getMinouOne_j(),
							n.getPathSoFar(),NodesNum);
					NodesNum++; //counting the number of generated nodes

					if(!loopAvoidance.containsKey(child.key())) {
						State_Node result = Limited_DFS(child, (limit-1), loopAvoidance); //running recursively

						//it means that we came to the limit and no path yet, and not found the goal yet
						if(result.getNumDirection() == 1) {cutOff.setNumDirection(1);}  // cutOff->true

						//if it's not fail and not a cutOff that we found the result
						else if(result.getNumDirection() != 0 ) {return result;}	
					}
					/**
					 * else --> it's goe's to the next operator it ths state is at the loop Avoidance hash Table
					 * to not create a loop
					 */
				}
			}//end for

			//ends work on this brunch so can remove the state from that hash table
			loopAvoidance.remove(n.key());

			if(cutOff.getNumDirection() == 1) {return cutOff;}  //+1 means that --> (cutOff==true)
			else{//()if (cutOff==false)
				cutOff.setNumDirection(0);//0 mean's fail
				return cutOff;
			}
		}
	}

	//*********************************************************************************
	//********simple getters
	public int getNodesNum() {return NodesNum;}
	
	//****************** Private Data *****************

	private State_Node start;
	private State_Node goal;
	private Hashtable<Integer, Color> numbersColors;
	private int NodesNum;


	//****************** Constructors *****************
	public DFID (State_Node start, State_Node goal) {
		this.start=start;
		this.goal=goal;
		this.numbersColors=this.goal.getNumbersColors();
		this.NodesNum=1;
	}
}