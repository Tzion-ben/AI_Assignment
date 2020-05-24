package DataStructure;
import Colored_Matrix_Management.Color;
/**
 * 
 * @author Tzion
 */
import Colored_Matrix_Management.Matrix_Variable;

public class State_Node implements State_Data {

	/**
	 * 
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
				}
			}
		}

	}






	@Override
	public void setF() {
		// TODO Auto-generated method stub

	}


	@Override
	public void setH() {
		// TODO Auto-generated method stub

	}


	@Override
	public void setG() {
		// TODO Auto-generated method stub

	}

	@Override
	public Oprerator getOperation() {
		return this.op;
	}


	@Override
	public Oprerator[] setOprerators() {
		// TODO Auto-generated method stub
		return null;
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


	//****************** Private Methods and Data *****************

	private Matrix_Variable [][] StateMatrix;
	private Oprerator op;
	private State_Node fatherPointer;
	private int f;
	private int h;
	private int g;


	//****************** Constructors *****************

	public State_Node(Matrix_Variable [][] StateMatrix, Oprerator op, int f,int h, int g, State_Node fatherPointer) {
		this.StateMatrix=StateMatrix;
		this.op=op;
		this.fatherPointer=fatherPointer;
		this.f=f;
		this.h=h;
		this.g=g;

	}
}