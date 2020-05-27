package DataStructure;
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
				case N:
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

					//if it's not the -1 Lable that represent "_" : a empty bloack
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
					//else: ==-1 so put k--, so it can run again on this k
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
	 * 
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

	@Override
	public Oprerator[] setOprerators() {
		// TODO Auto-generated method stub
		return null;
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

	public Oprerator [] getOprerators() {
		return this.oprerators;
	}


	//****************** Private Methods and Data *****************

	private Matrix_Variable [][] StateMatrix;
	private Oprerator op;
	private State_Node fatherPointer;
	private int f;
	private int h;
	private int g;

	private Oprerator [] oprerators;


	//****************** Constructors *****************

	public State_Node(Matrix_Variable [][] StateMatrix, Oprerator op, int g, State_Node fatherPointer) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.g=g;

		manageAllOperations();
	}

}