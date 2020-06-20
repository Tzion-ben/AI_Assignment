package DataStructure;
import java.util.Arrays;
/**
 * This class is represent a State_Node, it means that it is the state of every move at the 
 * search Algorithm 
 * @author Tzion
 */
import java.util.Hashtable;
import Tools.*;

public class State_Node {

	/**
	 * This function is manage all the operations in the state that
	 * have to be automatically step be step
	 */
	private void manageAllOperations() {
		if(this.StateMatrix==null) {return;}
		setOperation();
		setStateMatrix();
		setOprerators();
	}

	/**
	 * This function is reorganize the State matrix Board, depend on the operator.
	 * Searching for the Label 0 that represent the empty block and replace it with the new 
	 * number that came from the operator
	 */
	public void setStateMatrix() {

		if(this.direction == Direction.N) {
			//Initialize for the start state ti i and j of the "-" marekd as 0
			setFirstMinus_I_J();
			setH();
		}

		//moves the number of the operator to the location of 0
		this.StateMatrix[this.minouOne_i][this.minouOne_j]=this.numDirection;

		//now need to put the -1 at the location of the operator
		switch (this.direction) {
		case R:
			setMinus_Loctions(this.minouOne_i , this.minouOne_j-1);
			this.minouOne_j-=1;
			break;
		case D:
			setMinus_Loctions(this.minouOne_i-1 , this.minouOne_j);
			this.minouOne_i-=1;
			break;
		case L:
			setMinus_Loctions(this.minouOne_i , this.minouOne_j+1);
			this.minouOne_j+=1;
			break;
		case U:
			setMinus_Loctions(this.minouOne_i+1 , this.minouOne_j);
			this.minouOne_i+=1;
			break;

		}

		setH();
	}

	/**
	 * This function calculate the heuristic function of this state based on the "Manhattan Distance" 
	 */
	public void setH() {
		this.h=operatorHelper.setH(this.StateMatrix, this.direction);
		setG();
	}

	/**
	 * This functions sets the g function of this state, the g is the real cost so far.
	 * the function add the given g from the state before to the cost to create this state
	 */
	public void setG() {
		int realCost=getCost(getColor(this.numDirection));
		this.g+=realCost;

		setF();
	}

	/**
	 * Sets the f(n) of the state to be h(n)+g(n), send the h and g that calculated so far
	 */
	public void setF() {this.f=this.h+this.g;}

	/**
	 * This function sets all the operators that allows on this state
	 */
	public void setOprerators() {
		this.allowOP = new Hashtable<Integer, String>();

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
				boolean r= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,j_ofSpace+1);
				if (r) {setAllowOperator(i_ofSpace, j_ofSpace+1, Direction.L);}

				//for the left side of the space block		
				boolean l= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,j_ofSpace-1);
				if (l) {setAllowOperator(i_ofSpace, j_ofSpace-1, Direction.R);}
			}

			//the up or down operation
			if(i_ofSpace==0) {
				//for the down side of the space block
				boolean d= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,1,j_ofSpace);
				if (d) {setAllowOperator(1, j_ofSpace, Direction.U);}
			}
			//for the up side of the space block
			else if(i_ofSpace==(n-1)) {
				boolean u= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,n-2,j_ofSpace);
				if (u) {setAllowOperator(n-2, j_ofSpace, Direction.D);}
			}
		}

		/**
		 * if the 0 Label is at the mat[0...n-1][0](first column) or mat[0...n-1][m-1](last column), so will always check the up
		 * and down blocks. Than right if the column is 0 and up if the column m-1
		 */
		if(j_ofSpace==0 ||j_ofSpace==m-1) {
			if(i_ofSpace>0 && i_ofSpace<(n-1)) {
				//for the down side of the space block
				boolean d= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace+1,j_ofSpace);
				if (d) {setAllowOperator(i_ofSpace+1, j_ofSpace, Direction.U);}

				//for the up side of the space block
				boolean u= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace-1,j_ofSpace);
				if (u) {setAllowOperator(i_ofSpace-1, j_ofSpace, Direction.D);}
			}

			//the right or left operation
			if(j_ofSpace==0) {
				//for the left side of the space block
				boolean r= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,1);
				if (r) {setAllowOperator(i_ofSpace, 1, Direction.L);}
			}
			if(j_ofSpace==(m-1)) {
				//for the left side of the space block
				boolean l= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,m-2);
				if (l) {setAllowOperator(i_ofSpace, m-2, Direction.R);}
			}
		}

		/**
		 * if the 0 Label is not at the first row or column and not at the last row or column
		 */
		else if(i_ofSpace>0 && i_ofSpace<n-1 && j_ofSpace>0 && j_ofSpace<m-1) {
			//for the right side of the space block
			boolean r= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,j_ofSpace+1);
			if (r) {setAllowOperator(i_ofSpace, j_ofSpace+1, Direction.L);}

			//for the down side of the space block
			boolean d= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace+1,j_ofSpace);
			if (d) {setAllowOperator(i_ofSpace+1, j_ofSpace, Direction.U);}

			//for the left side of the space block
			boolean l= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace,j_ofSpace-1);
			if (l) {setAllowOperator(i_ofSpace, j_ofSpace-1, Direction.R);}


			//for the left side of the space block
			boolean u= directionHelper.locationToOperetors(this.StateMatrix,this.numbersColors,i_ofSpace-1,j_ofSpace);
			if (u) {setAllowOperator(i_ofSpace-1, j_ofSpace, Direction.D);}
		}
	}

	//************************************ Public methods ************************************
	/**
	 * This function create a deep Copy of the given matrix state Board, so the new state won't
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
	 * This function prints the state Board, the operation that brings to this state anf the f(n)
	 * of the state
	 */
	public void printState() {
		System.out.println("Operation that bring to this state is: " + op + ", f(n) is: " + f);
		System.out.println( "State Board :");
		printBoard();
		System.out.println();
	}

	/**
	 * This function prints the Game Board
	 */
	public void printBoard (){
		for(int i=0 ; i<this.StateMatrix.length ; i++) { 
			for(int j=0 ; j<this.StateMatrix[0].length ; j++) {
				if(this.StateMatrix[i][j]!=0) {
					System.out.print(this.StateMatrix[i][j]+numbersColors.get(this.StateMatrix[i][j]).toString()+"|");
				}else {
					System.out.print("_____|");
				}
			}
			System.out.println();
		}
	}

	//************************************ Private Methods ************************************
	//********************************** Help to H Function ***********************************
	/**
	 * This function searching for the position of the Label 0 that represent the empty block :"_" 
	 * @return array with the i and j of the 0 Label
	 */
	private void setFirstMinus_I_J() {
		int [] spcace_pos = operatorHelper.setMinus_I_J(this.StateMatrix);
		this.minouOne_i=spcace_pos[0];
		this.minouOne_j=spcace_pos[1];
	}

	/**
	 * This function set the location of the "_", that marks as 0, to a given i and j
	 */
	private void setMinus_Loctions(int new_i, int new_j) {this.StateMatrix[new_i][new_j]=0;}

	//******************************* Operator Helper Functions *******************************
	/**
	 * This function returns a unique string assisted with this matrix state, for the key to the 
	 * hash Table of the Algorithms
	 * @return String represents a unique key to the state
	 */
	public String key () {return operatorHelper.key(this.StateMatrix);}

	/**
	 * This function add the operator to the HashTable of the operators if and only if that new 
	 * operator is NOT the reveres of this.direction
	 */
	private void setAllowOperator(int i, int j, Direction D) {
		int direction_ID = directionHelper.getDirection_Id(D);

		//if and only if the direction of the new operator is NOT reversed direction
		if(!directionHelper.getReversedOp(D).equals(this.direction))
			this.allowOP.put(direction_ID,directionHelper.nextOperation(this.StateMatrix, i, j, D));
	}

	//********************************* Color Helper Functions ********************************
	/**
	 * This method return the cost to move a specific block depend on it's color, RED->30
	 * GREEN->1, BLACK->can't move so 0 , E-> for the start state also 0
	 * @return cost depend on the color
	 */
	private int getCost (Color c){return colorHelper.getCost(c);}

	/**
	 * This function return the "color" of a given number from the matrix Game
	 */
	private Color getColor(int num) {return colorHelper.getColor(num);}

	//****************************** simple getters and setters ******************************
	public int[][] getStateMatrix() {return this.StateMatrix;}

	public State_Node getFatherPointer() {return this.fatherPointer;}

	public int getF() {return this.f;}

	public int getH() {return this.h;}

	public int getG() {return this.g;}

	public int getMinouOne_i() {return minouOne_i;}

	public int getMinouOne_j() {return minouOne_j;}

	public String getOperation() {return this.op;}

	public Hashtable<Integer, String> getOprerators() {return this.allowOP;}

	public Hashtable<Integer, Color> getNumbersColors() {return numbersColors;}

	public int getNumDirection() {return this.numDirection;}

	public Direction getDirection() {return this.direction;}	

	public void setNumDirection(int num) {this.numDirection=num;}

	public int getNodeID() {return NodeID;}

	public void setTag(int tagOut) {this.tagOut=tagOut;}

	public int getTag() {return this.tagOut;}

	public void setNodeId(int id) {this.NodeID=id;}

	//********************************* Private setters *********************************
	/**
	 * This function gets the operation string that represent for example: 5-U, and cut it 
	 * to number and Direction
	 */
	private void setOperation() {
		String [] opertion = this.op.split("-");
		this.numDirection = Integer.valueOf(opertion[0]);
		this.direction = Direction.valueOf(opertion[1]);
	}

	//*********************************** Help Classes ***********************************
	/**
	 * This function sets the classes that will help State_Node class to work.
	 */
	private void setTools () {
		operatorHelper = new OpertionHelper(this.numbersColors);
		colorHelper = new ColorValidator(this.numbersColors);
		directionHelper = new DirectionHelper(this.numbersColors);
	}

	//*********************************** Private Data ***********************************
	private int [][] StateMatrix;

	private String op;
	private int numDirection;
	private Direction direction;

	private int NodeID;
	private int tagOut;

	private State_Node fatherPointer;

	private int f;
	private int h;
	private int g;

	private int minouOne_i;
	private int minouOne_j;

	private Hashtable<Integer, Color> numbersColors;
	private Hashtable<Integer, String> allowOP;

	private static OpertionHelper operatorHelper;
	private static ColorValidator colorHelper;
	private static DirectionHelper directionHelper;

	//*********************************** Constructors ***********************************
	public State_Node(int [][] StateMatrix, Hashtable<Integer, Color> numbersColors , String op, int g, int h,
			State_Node fatherPointer, int minouOne_i, int minouOne_j, int NodeID) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.g=g;
		this.h=h;

		this.NodeID=NodeID;
		this.tagOut=0;


		this.minouOne_i=minouOne_i;
		this.minouOne_j=minouOne_j;
		this.numbersColors = new Hashtable<Integer, Color>();
		this.numbersColors=numbersColors;

		setTools();
		manageAllOperations();
	}

}