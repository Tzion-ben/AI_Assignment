package DataStructure;
/**
 * This class represent a object that contains number of the block that will
 * move and the direction that it can moves (L,R,U,D)
 * @author Tzion
 */

import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Matrix_Variable;

public class Oprerator {

	//a simple getters and setters
	public int getNum() {
		return this.num;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setNum(int num) {
		this.num=num;
	}

	public void setDirection(Direction direction) {
		this.direction=direction;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[" + num + direction +"]";
	}

	/**
	 * This function checks if a given operation is equal to the this.operation 
	 * or not
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj!=null&&(obj instanceof Oprerator)){
			Oprerator op =(Oprerator) obj;

			if(this.num != op.getNum()) 
				return false;
			if(this.color != op.getColor()) 
				return false;
			if(this.direction != op.getDirection()) 
				return false;
		}
		return true;
	}

	//****************** Private Data *****************
	private int num;
	private Direction direction;
	private Color color;

	//****************** Constructors *****************
	public Oprerator(int num , Direction direction, Color color) {
		this.num=num;
		this.direction=direction;
		this.color=color;
	}

}