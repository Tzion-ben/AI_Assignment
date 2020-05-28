package DataStructure;
import java.util.ArrayList;

import Colored_Matrix_Management.Color;
/**
 * 
 * @author Tzion
 */
import Colored_Matrix_Management.Matrix_Variable;

public class State_Node implements State_Data {

	/**
	 * this function is manage all the operations in the state that
	 * have to be automatically step be step
	 */
	private void manageAllOperations() {
		setStateMatrix();
		setH();
	}

	/**
	 * This function is reorganize the State matrix, depend on the operator.
	 * Searching for the Label -1 that represent the empty block and replace it with the new 
	 * number that came from the operator
	 */
	@Override
	public void setStateMatrix() {
		for(int i=0; i<this.StateMatrix.length;i++) {
			for(int j=0;j<this.StateMatrix[0].length;j++) {
				Color opColor=null;

				if(this.StateMatrix[i][j].getNum()==this.op.getNum()) {
					this.StateMatrix[i][j].setNum(-1);
					opColor=this.StateMatrix[i][j].getColor();
					this.StateMatrix[i][j].setColor(Color.E);	
				}

				switch (this.op.getDirection()) {
				case R:
					this.StateMatrix[i][j+1].setNum(op.getNum());
					this.StateMatrix[i][j+1].setColor(opColor);
					break;
				case D:
					this.StateMatrix[i+1][j].setNum(op.getNum());
					this.StateMatrix[i+1][j].setColor(opColor);
					break;
				case L:
					this.StateMatrix[i][j-1].setNum(op.getNum());
					this.StateMatrix[i][j-1].setColor(opColor);
					break;
				case U:
					this.StateMatrix[i-1][j].setNum(op.getNum());
					this.StateMatrix[i-1][j].setColor(opColor);
					break;
				case N://only for the start state
					break;
				}
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void setH() {
		int h=0;
		int originalI=0, originalJ=0, new_i=0, new_j=0;
		int stepsForI=0, stepsForJ=0;
		for(int k=1;k>this.StateMatrix.length*this.StateMatrix[0].length;k++) {
			for(int i=0;i<this.StateMatrix.length;i++) {
				for(int j=0; j<this.StateMatrix[0].length;j++) {

					//the goal position of K
					originalI=i;
					originalJ=j;

					//if it's not the -1 Label that represent "_" : a empty bloack
					if(this.StateMatrix[i][j].getNum()!=-1) {
						if(this.StateMatrix[i][j].getNum() == k) {
							new_i=i;
							new_j=j;


							//how many steps have to do to pot the numbet at his posiotion 
							//for the h function
							stepsForI=originalI-new_i;
							stepsForJ=originalJ-new_j;

							//returns the cost of the block to move
							int costColor= getCost(this.StateMatrix[i][j].getColor());
							//calculate steps for rows+columns
							int cost=(stepsForI+stepsForJ)*costColor;

							//added it to the h function until now
							h+=cost;
						}
					}
					//else: ==-1 so put k=k-1, so it can run again on this k
					else
						k--;

					//returns the moveCounters to start position
					stepsForI=0;
					stepsForJ=0;
				}
			}
		}
		this.h=h;
		setG();
	}

	/**
	 * this functions sets the g function of this state, the g is the real cost so far.
	 * the function add the given g from the state before to the cost to create this state
	 */
	@Override
	public void setG() {
		int realCost=getCost(this.op.getColor());
		this.g+=realCost;

		setF();
	}

	/**
	 * Sets the f of the state to be h+g, send the h and g that calculate so far
	 */
	@Override
	public void setF() {
		this.f=h+g;
	}

	/**
	 * this function sets all the operators that allows on this state
	 */
	@Override
	public void setOprerators() {
		this.allowOP = new ArrayList<Oprerator>();

		//will hold the position of the Label -1 that represent the empty block :"_" 
		int [] pos = searchSpace();
		int i_ofSpace=pos[0];
		int j_ofSpace=pos[1];

		//will help to work with the rows and the columns more easy
		//n=rows ; m=columns
		int n=this.StateMatrix.length;
		int m=this.StateMatrix[0].length;


		/**
		 * if the -1 Label is at the mat[0][0....m-1](first row) or mat[n-1][0....m-1](last row), so will always check the right
		 * and left blocks. Than down if the row is 0 and up if the row n-1
		 */
		if(i_ofSpace==0 ||i_ofSpace==n-1) {
			if(j_ofSpace>0 && j_ofSpace<(m-1)) {
				boolean r=getAllowORnot(this.StateMatrix[i_ofSpace][j_ofSpace+1].getColor());
				if(r) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][j_ofSpace+1].getNum(),Direction.L, this.StateMatrix[i_ofSpace][j_ofSpace+1].getColor()));}
				boolean l=getAllowORnot(this.StateMatrix[i_ofSpace][j_ofSpace-1].getColor());
				if(l) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][j_ofSpace-1].getNum(),Direction.R, this.StateMatrix[i_ofSpace][j_ofSpace-1].getColor()));}
			}

			//the up or down operation
			if(i_ofSpace==0) {
				boolean d=getAllowORnot(this.StateMatrix[1][j_ofSpace].getColor());
				if(d) {this.allowOP.add(new Oprerator(this.StateMatrix[1][j_ofSpace].getNum(),Direction.U, this.StateMatrix[1][j_ofSpace].getColor()));}
			}
			else if(i_ofSpace==(n-1)) {
				boolean u=getAllowORnot(this.StateMatrix[1][j_ofSpace].getColor());
				if(u) {this.allowOP.add(new Oprerator(this.StateMatrix[n-2][j_ofSpace].getNum(),Direction.D, this.StateMatrix[n-2][j_ofSpace].getColor()));}
			}
		}


		/**
		 * if the -1 Label is at the mat[0...n-1][0](first column) or mat[0...n-1][m-1](last column), so will always check the up
		 * and down blocks. Than right if the column is 0 and up if the column m-1
		 */
		if(j_ofSpace==0 ||j_ofSpace==m-1) {
			if(i_ofSpace>0 && i_ofSpace<(n-1)) {
				boolean d=getAllowORnot(this.StateMatrix[i_ofSpace+1][j_ofSpace].getColor());
				if(d) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace+1][j_ofSpace].getNum(),Direction.U, this.StateMatrix[i_ofSpace+1][j_ofSpace].getColor()));}
				boolean u=getAllowORnot(this.StateMatrix[i_ofSpace-1][j_ofSpace].getColor());
				if(u) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace-1][j_ofSpace].getNum(),Direction.D, this.StateMatrix[i_ofSpace-1][j_ofSpace].getColor()));}
			}

			//the right or left operation
			if(j_ofSpace==0) {
				boolean r=getAllowORnot(this.StateMatrix[i_ofSpace][1].getColor());
				if(r) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][1].getNum(),Direction.L, this.StateMatrix[i_ofSpace][1].getColor()));}
			}
			else if(j_ofSpace==(m-1)) {
				boolean l=getAllowORnot(this.StateMatrix[i_ofSpace][m-2].getColor());
				if(l) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][m-2].getNum(),Direction.R, this.StateMatrix[i_ofSpace][m-2].getColor()));}
			}
		}

		/**
		 * if the -1 Label is not at the first row or column and not at the last row or column
		 */
		else {
			boolean r=getAllowORnot(this.StateMatrix[i_ofSpace][j_ofSpace+1].getColor());
			if(r) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][j_ofSpace+1].getNum(),Direction.L, this.StateMatrix[i_ofSpace][j_ofSpace+1].getColor()));}
			boolean l=getAllowORnot(this.StateMatrix[i_ofSpace][j_ofSpace-1].getColor());
			if(l) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace][j_ofSpace-1].getNum(),Direction.R, this.StateMatrix[i_ofSpace][j_ofSpace-1].getColor()));}
			boolean u=getAllowORnot(this.StateMatrix[i_ofSpace-1][j_ofSpace].getColor());
			if(u) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace-1][j_ofSpace].getNum(),Direction.D, this.StateMatrix[i_ofSpace-1][j_ofSpace].getColor()));}
			boolean d=getAllowORnot(this.StateMatrix[i_ofSpace+1][j_ofSpace].getColor());
			if(d) {this.allowOP.add(new Oprerator(this.StateMatrix[i_ofSpace+1][j_ofSpace].getNum(),Direction.U, this.StateMatrix[i_ofSpace+1][j_ofSpace].getColor()));}
		}
	}

	//*************************** Private Methods *********************************

	/**
	 * this function searching for the position of the Label -1 that represent the empty block :"_" 
	 * @return array wit the i and j of the -1
	 */
	private int [] searchSpace () {
		int [] pos = new int[2];
		for(int i=0 ; i<this.StateMatrix.length ; i++) {
			for(int j=0 ; j<this.StateMatrix[0].length ; j++) {
				if (this.StateMatrix[i][j].getNum() == -1) {
					pos[0]=i;
					pos[1]=j;
				}
			}
		}

		return pos;
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
			ans=false;;
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

	//********************** simple getters
	@Override
	public Matrix_Variable[][] getStateMatrix() {
		return this.StateMatrix;
	}

	@Override
	public State_Data getFatherPointer() {
		return this.fatherPointer;
	}

	@Override
	public int getF() {
		return this.f;
	}

	@Override
	public int getH() {
		return this.h;
	}

	@Override
	public int getG() {
		return this.g;
	}

	@Override
	public Oprerator getOperation() {
		return this.op;
	}

	public ArrayList<Oprerator>  getOprerators() {
		return this.allowOP;
	}


	//****************** Private Data *****************

	private Matrix_Variable [][] StateMatrix;
	private Oprerator op;
	private State_Node fatherPointer;
	private int f;
	private int h;
	private int g;

	private ArrayList<Oprerator> allowOP;

	//****************** Constructors *****************

	public State_Node(Matrix_Variable [][] StateMatrix, Oprerator op, int g, State_Node fatherPointer) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.g=g;

		manageAllOperations();
	}

}