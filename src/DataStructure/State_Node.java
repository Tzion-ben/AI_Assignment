package DataStructure;

import java.util.Hashtable;

public class State_Node {
	/**
	 * this function is manage all the operations in the state that
	 * have to be automatically step be step
	 */
	private void manageAllOperations() {
		if(this.StateMatrix==null) {return;}
		setOperation();
		setStateMatrix();
		setOprerators();
	}

	/**
	 * This function is reorganize the State matrix, depend on the operator.
	 * Searching for the Label 0 that represent the empty block and replace it with the new 
	 * number that came from the operator
	 */

	public void setStateMatrix() {

		//it's means it's the start state
		if(this.direction == Direction.N) {
			setH(0,0,0,0);//start 
			//Initialize for the start state ti i and j of the "-" marekd as 0
			setFirstMinus_I_J();
		}

		//else --> all the state after the start state
		else {
			int i = 0 , j = 0;

			//moves the number of the operator to the location of 0
			this.StateMatrix[this.minouOne_i][this.minouOne_j]=this.numDirection;

			//now need to put the -1 at the location of the operator
			switch (this.direction) {
			case R:
				i=this.minouOne_i ; j=this.minouOne_j;
				setMinus_Loctions(this.minouOne_i , this.minouOne_j-1);
				this.minouOne_j-=1;
				setH(i, j-1, i, j);
				break;
			case D:
				i=this.minouOne_i ; j=this.minouOne_j;
				setMinus_Loctions(this.minouOne_i-1 , this.minouOne_j);
				this.minouOne_i-=1;
				setH(i-1, j, i, j);
				break;
			case L:
				i=this.minouOne_i ; j=this.minouOne_j;
				setMinus_Loctions(this.minouOne_i , this.minouOne_j+1);
				this.minouOne_j+=1;
				setH(i, j+1, i, j);
				break;
			case U:
				i=this.minouOne_i ; j=this.minouOne_j;
				setMinus_Loctions(this.minouOne_i+1 , this.minouOne_j);
				this.minouOne_i+=1;
				setH(i+1, j, i, j);
				break;
			}
		}
	}


	/**
	 * this function calculate the heuristic function of this state based on the "Manhattan Distance" 
	 */

	public void setH(int oldLoc_i, int oldLoc_j,int newLoc_i , int newLoc_j) {
		int h=0;
		int goal_i=0, goal_j=0, now_i=0, now_j=0;
		int stepsForI=0, stepsForJ=0;
		int SIZE = this.StateMatrix.length*this.StateMatrix[0].length;

		/**
		 * it's mean's that it's that it's the start state, so it have to calculate all the matrix
		 * for the H function
		 */
		if(this.direction == Direction.N) {
			int colum = this.StateMatrix[0].length;
			for(int k=1 ; k<=SIZE ; k++ ) {
				goal_j = now_j;
				goal_i = now_i;
				now_j++;//the columns runs

				//if k divided be now_i that it's the number of the row so it mean's it's
				//the last location of the row 
				if(k/(now_i+1) == colum) {  //a new line at the goal matrix so i++ and j=0
					now_i++;
					now_j=0;
				}


				for(int i=0;i<this.StateMatrix.length;i++) {
					for(int j=0; j<this.StateMatrix[0].length;j++) {

						//if it's not the -1 Label that represent "_" : a empty block
						if(this.StateMatrix[i][j]!=0) {
							if(this.StateMatrix[i][j] == k) {

								//how many steps have to do to put the number at his position 
								//for the h function
								stepsForI=goal_i-i;
								stepsForJ=goal_j-j;

								//returns the cost of the block to move
								Color colorToCosr = getColor(this.StateMatrix[i][j]);
								int costColor= getCost(colorToCosr);

								//calculate steps for rows+columns
								int cost=(Math.abs(stepsForI)+Math.abs(stepsForJ))*costColor;

								//added it to the h function until now
								h+=cost;

								j=this.StateMatrix[0].length;
								i=this.StateMatrix.length;
							}
						}

						//else --> found the place of 0, want to save it 
						else if(this.StateMatrix[i][j]==0) {
							this.minouOne_i=i;
							this.minouOne_j=j;
						}

						//returns the moveCounters to start position
						stepsForI=0;
						stepsForJ=0;
					}
				}
			}
		}
		//else --> it's state that isn't start and i can work with the privies H and just make a 
		//change depend on the opertion
		else {
			h=this.h;
			int numToOp=this.numDirection;
			//find's the real i and j of the number that need to move
			for(int k=0 ; k<=numToOp ; k++) {
				int colum = this.StateMatrix[0].length;
				goal_j = now_j;
				goal_i = now_i;
				now_j++;//the columns runs

				//if k divided be now_i that it's the number of the row so it mean's it's
				//the last location of the row 
				if(k/(now_i+1) == colum) {  //a new line at the goal matrix so i++ and j=0
					now_i++;
					now_j=0;
				}
			}

			//returns the cost of the block to move (for the new and old is't the same price
			Color colorToCosr = getColor(this.StateMatrix[newLoc_i][newLoc_j]);
			int costColor= getCost(colorToCosr);

			int oldStemps_H_i=goal_i-oldLoc_i;
			int oldStemps_H_j=goal_j-oldLoc_j;
			//calculate the "old" steps for rows+columns
			int oldCost=(Math.abs(oldStemps_H_i)+Math.abs(oldStemps_H_j))*costColor;

			int newStemps_H_i=goal_i-newLoc_i;
			int newStemps_H_j=goal_j-newLoc_j;
			//calculate the "new" steps for rows+columns
			int newCost=(Math.abs(newStemps_H_i)+Math.abs(newStemps_H_j))*costColor;

			h-=oldCost;
			h+=newCost;

		}
		this.h=h;
		setG();
	}

	/**
	 * this functions sets the g function of this state, the g is the real cost so far.
	 * the function add the given g from the state before to the cost to create this state
	 */
	public void setG() {
		int realCost=getCost(getColor(this.numDirection));
		this.g+=realCost;

		setF();
	}

	/**
	 * Sets the f of the state to be h+g, send the h and g that calculated so far
	 */
	public void setF() {
		this.f=this.h+this.g;
	}

	/**
	 * this function sets all the operators that allows on this state
	 */

	public void setOprerators() {
		this.allowOP = new Hashtable<Integer, String>();
		Color colorToCheck=null;

		//will hold the position of the Label 0 that represent the empty block :"_" 
		int i_ofSpace=this.minouOne_i;
		int j_ofSpace=this.minouOne_j;

		//will help to work with the rows and the columns more easy
		//n=rows ; m=columns
		int n=this.StateMatrix.length;
		int m=this.StateMatrix[0].length;

		/**
		 * if the 0 Label is at the mat[0][0....m-1](first row) or mat[n-1][0....m-1](last row), so will always check the right
		 * and left blocks. Than down if the row is 0 and up if the row n-1
		 */
		if(i_ofSpace==0 ||i_ofSpace==n-1) {
			if(j_ofSpace>0 && j_ofSpace<(m-1)) {
				//for the right side of the space block
				colorToCheck = getColor(this.StateMatrix[i_ofSpace][j_ofSpace+1]);
				boolean r=getAllowORnot(colorToCheck);
				if(r) {this.allowOP.put(0,String.valueOf(this.StateMatrix[i_ofSpace][j_ofSpace+1])+"-"+Direction.L);}

				//for the left side of the space block
				colorToCheck = getColor(this.StateMatrix[i_ofSpace][j_ofSpace-1]);
				boolean l=getAllowORnot(colorToCheck);
				if(l) {this.allowOP.put(2,String.valueOf(this.StateMatrix[i_ofSpace][j_ofSpace-1])+"-"+Direction.R);}
			}

			//the up or down operation
			if(i_ofSpace==0) {
				//for the down side of the space block
				colorToCheck = getColor(this.StateMatrix[1][j_ofSpace]);
				boolean d=getAllowORnot(colorToCheck);
				if(d) {this.allowOP.put(1,String.valueOf(this.StateMatrix[1][j_ofSpace])+"-"+Direction.U);}
			}
			//for the up side of the space block
			else if(i_ofSpace==(n-1)) {
				colorToCheck = getColor(this.StateMatrix[n-2][j_ofSpace]);
				boolean u=getAllowORnot(colorToCheck);
				if(u) {this.allowOP.put(3,String.valueOf(this.StateMatrix[n-2][j_ofSpace])+"-"+Direction.D);}
			}
		}


		/**
		 * if the 0 Label is at the mat[0...n-1][0](first column) or mat[0...n-1][m-1](last column), so will always check the up
		 * and down blocks. Than right if the column is 0 and up if the column m-1
		 */
		if(j_ofSpace==0 ||j_ofSpace==m-1) {
			if(i_ofSpace>0 && i_ofSpace<(n-1)) {
				//for the down side of the space block
				colorToCheck = getColor(this.StateMatrix[i_ofSpace+1][j_ofSpace]);
				boolean d=getAllowORnot(colorToCheck);
				if(d) {this.allowOP.put(1,String.valueOf(this.StateMatrix[i_ofSpace+1][j_ofSpace])+"-"+Direction.U);}

				//for the up side of the space block
				colorToCheck = getColor(this.StateMatrix[i_ofSpace-1][j_ofSpace]);
				boolean u=getAllowORnot(colorToCheck);
				if(u) {this.allowOP.put(3,String.valueOf(this.StateMatrix[i_ofSpace-1][j_ofSpace])+"-"+Direction.D);}
			}

			//the right or left operation
			if(j_ofSpace==0) {
				//for the left side of the space block
				colorToCheck = getColor((this.StateMatrix[i_ofSpace][1]));
				boolean r=getAllowORnot(colorToCheck);
				if(r) {this.allowOP.put(0,String.valueOf(this.StateMatrix[i_ofSpace][1])+"-"+Direction.L);}
			}
			if(j_ofSpace==(m-1)) {
				//for the left side of the space block
				colorToCheck = getColor((this.StateMatrix[i_ofSpace][m-2]));
				boolean l=getAllowORnot(colorToCheck);
				if(l) {this.allowOP.put(2,String.valueOf(this.StateMatrix[i_ofSpace][m-2])+"-"+Direction.R);}
			}
		}

		/**
		 * if the 0 Label is not at the first row or column and not at the last row or column
		 */
		else if(i_ofSpace>0 && i_ofSpace<n-1 && j_ofSpace>0 && j_ofSpace<m-1) {
			//for the right side of the space block
			colorToCheck = getColor((this.StateMatrix[i_ofSpace][j_ofSpace+1]));
			boolean r=getAllowORnot(colorToCheck);
			if(r) {this.allowOP.put(0,String.valueOf(this.StateMatrix[i_ofSpace][j_ofSpace+1])+"-"+Direction.L);}	

			//for the down side of the space block
			colorToCheck = getColor((this.StateMatrix[i_ofSpace+1][j_ofSpace]));
			boolean d=getAllowORnot(colorToCheck);
			if(d) {this.allowOP.put(1,String.valueOf(this.StateMatrix[i_ofSpace+1][j_ofSpace])+"-"+Direction.U);}

			//for the left side of the space block
			colorToCheck = getColor((this.StateMatrix[i_ofSpace][j_ofSpace-1]));
			boolean l=getAllowORnot(colorToCheck);
			if(l) {this.allowOP.put(2,String.valueOf(this.StateMatrix[i_ofSpace][j_ofSpace-1])+"-"+Direction.R);}

			//for the left side of the space block
			colorToCheck = getColor((this.StateMatrix[i_ofSpace-1][j_ofSpace]));
			boolean u=getAllowORnot(colorToCheck);
			if(u) {this.allowOP.put(3,String.valueOf(this.StateMatrix[i_ofSpace-1][j_ofSpace])+"-"+Direction.D);}
		}

		if(this.direction != Direction.N)
			//removes the reversed operation
			removeReversedOp();
	}

	//****************** Public methods *****************

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public boolean equals(Object obj) {
		if(obj!=null&&(obj instanceof State_Node)){
			State_Node n=(State_Node) obj;
			int [][] n_Matrix=n.getStateMatrix();

			if(this.StateMatrix == null && n_Matrix == null) {return true;}
			if((this.StateMatrix == null && n_Matrix != null)||(this.StateMatrix != null && n_Matrix == null)) {return false;}

			for(int i=0; i<this.StateMatrix.length ; i++) {
				for(int j=0 ; j<this.StateMatrix[0].length ; j++) {
					if(this.StateMatrix[i][j] != n_Matrix[i][j]) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * This function create a deep Copy of the given matrix state, so the new state won't
	 * ruin the matrix of an existent father state of it   
	 * @return
	 */
	public int [][] deepCopy (){
		int [][] newStateMat = new int [this.StateMatrix.length][this.StateMatrix[0].length];
		for(int i=0 ; i<newStateMat.length ; i++) 
			for(int j=0 ; j<newStateMat[0].length ; j++) 
				newStateMat[i][j]=this.StateMatrix[i][j];
		return newStateMat;
	}

	/**
	 * this function is sets the operator of the state if the algorithem found a more better path
	 * to this state
	 */
	public void setOpretor (String op) {
		this.op=op;
		setOperation();
	}

	//*************************** Private Methods *********************************

	/**
	 * this function set the i and j of the "_", that merks as 0 to a given i and j
	 */
	private void setMinus_Loctions(int new_i, int new_j) {
		this.StateMatrix[new_i][new_j]=0;		
	}

	/**
	 * this function searching for the position of the Label -1 that represent the empty block :"_" 
	 * @return array wit the i and j of the 0
	 */
	private void setFirstMinus_I_J() {
		boolean stop=false;	
		for(int i=0 ; i<this.StateMatrix.length && !stop ; i++) {
			for(int j=0 ; j<this.StateMatrix[0].length ; j++) {
				if (this.StateMatrix[i][j] == 0) {
					this.minouOne_i=i;
					this.minouOne_j=j;
					stop=true;
				}
			}
		}
	}

	/**
	 * this function return the "color" of a given number from the matrix game of the state
	 */
	private Color getColor(int num) {
		if(num==0) {return Color.E;}
		while(!this.numbersColors.isEmpty()) 
			if(this.numbersColors.containsKey(num)) 
				return this.numbersColors.get(num);
		return null;
	}

	/**
	 * this method checks the color of the block and says if this block can move or 
	 * not: RED and GRENN - can move, BLACK - can't move 
	 * @param color c
	 * @return
	 */
	private boolean getAllowORnot(Color c) {
		boolean ans=false;
		switch (c) {
		case RED:
		case GREEN:
			ans=true;
			break;
		case BLACK:
			ans=false;
			break;
		}
		return ans;
	}

	/**
	 * this method return the cost to move a block depend on it's color, RED->30
	 * GREEN->1, BLACK->can't move so 0 , E-> for the start state
	 * @return cost depend on the color
	 */
	private int getCost (Color c){
		int cost=0;
		switch (c) {
		case RED:
			cost=30;
			break;
		case GREEN:
			cost=1;
			break;
		case BLACK:
			cost=0;
			break;
		case E:
			cost=0;
			break;
		}
		return cost;
	}

	/**
	 * This function removes from the allow operator of this state the operation
	 * that is reversed to the operation that create this state  
	 */
	private void removeReversedOp () {
		Direction opsitOp = getReversedOp(this.direction);
		for(int i=0; i <4 ; i++) {
			if(this.allowOP.containsKey(i)) {
				String toCut = this.allowOP.get(i);
				String [] opertion = toCut.split("-");
				if (Direction.valueOf(opertion[1]).compareTo(opsitOp) == 0 ) 
					this.allowOP.remove(i);
			}
		}
	}

	/**
	 * This function returns the reversed operation of a given operation
	 * @return
	 */
	private Direction getReversedOp (Direction d) {
		Direction rev = null;
		switch (d) {
		case R: 
			rev = Direction.L;
			break;
		case U:
			rev = Direction.D;
			break;
		case L: 
			rev = Direction.R;
			break;
		case D:
			rev = Direction.U;
			break;
		}
		return rev;
	}

	/**
	 * This function gets the opertion string and cut it to number and Direction
	 * @return
	 */
	private void setOperation() {
		String [] opertion = this.op.split("-");
		this.numDirection = Integer.valueOf(opertion[0]);
		this.direction = Direction.valueOf(opertion[1]);
	}

	/**
	 * 
	 * @return
	 */
	private void SetPathSoFar () {
		if(this.op.compareTo("0-N") == 0) {
			this.PathSoFar="";
		}
		else {
			String [] opertionSoFar = this.op.split("-");
			this.PathSoFar=this.PathSoFar+opertionSoFar[0]+opertionSoFar[1]+"-";
		}
	}


	//********************** simple getters and setters

	public int[][] getStateMatrix() {
		return this.StateMatrix;
	}

	public State_Node getFatherPointer() {
		return this.fatherPointer;
	}

	public int getF() {
		return this.f;
	}

	public int getH() {
		return this.h;
	}

	public int getG() {
		return this.g;
	}

	public int getMinouOne_i() {
		return minouOne_i;
	}

	public int getMinouOne_j() {
		return minouOne_j;
	}

	public String getOperation() {
		return this.op;
	}

	public Hashtable<Integer, String> getOprerators() {
		return this.allowOP;
	}

	public Hashtable<Integer, Color> getNumbersColors() {
		return numbersColors;
	}

	public int getNumDirection() {
		return this.numDirection;
	}

	public Direction getDirection() {
		return this.direction;
	}	

	public void setNumDirection(int num) {
		this.numDirection=num;
	}

	public String getPathSoFar() {
		return PathSoFar;
	}

	public int getSumPat() {
		return sumPat;
	}

	//****************** Private Data *****************

	private int [][] StateMatrix;

	private String op;
	private int numDirection;
	private Direction direction;

	private String PathSoFar;
	private int sumPat;
	private State_Node fatherPointer;

	private int f;
	private int h;
	private int g;

	private int minouOne_i;
	private int minouOne_j;

	private Hashtable<Integer, Color> numbersColors;
	private Hashtable<Integer, String> allowOP;

	//****************** Constructors *****************

	/**
	 * contractor oonly for the start and goal state 
	 * @param StateMatrix
	 * @param numbersColors
	 * @param op
	 * @param g
	 * @param h
	 * @param fatherPointer
	 * @param minouOne_i
	 * @param minouOne_j
	 */
	public State_Node(int [][] StateMatrix, Hashtable<Integer, Color> numbersColors , String op, int g, int h,
			State_Node fatherPointer, int minouOne_i, int minouOne_j, String PathSoFar) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.g=g;
		this.h=h;

		this.minouOne_i=minouOne_i;
		this.minouOne_j=minouOne_j;
		this.numbersColors = new Hashtable<Integer, Color>();
		this.numbersColors=numbersColors;

		this.PathSoFar = PathSoFar;
		SetPathSoFar();

		manageAllOperations();
	}

	/**
	 * contractor for all the states exept the start and goal state 
	 * @param StateMatrix
	 * @param op
	 * @param g
	 * @param h
	 * @param fatherPointer
	 * @param minouOne_i
	 * @param minouOne_j
	 */
	public State_Node(int [][] StateMatrix, String op, int g, int h,
			State_Node fatherPointer, int minouOne_i, int minouOne_j,String PathSoFar) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.g=g;
		this.h=h;

		this.minouOne_i=minouOne_i;
		this.minouOne_j=minouOne_j;

		manageAllOperations();
	}

}