package DataStructure;
/**
 * This class represent a object that contains number of the block that will
 * move and the direction that it can moves (L,R,U,D)
 * F is:
 * H is:
 * G is: 
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

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
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
	private int f;
	private int h;
	private int g;

	//****************** Constructors *****************
	public Oprerator(int num , Direction direction, Color color, int f, int h, int g) {
		this.num=num;
		this.direction=direction;
		this.color=color;
		this.f=f;
		this.h=h;
		this.g=g;
	}

}