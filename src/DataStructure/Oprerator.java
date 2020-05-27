package DataStructure;
/**
 * This class represent a object that contains number of the block that will
 * move and the direction that it can moves (L,R,U,D)
 * F is: H + G
 * H is: is the heuristic function
 * G is: is the real cost from this specific state to the goal
 * @author Tzion
 */

import Colored_Matrix_Management.Color;

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