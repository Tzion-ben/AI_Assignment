package Colored_Matrix_Management;
/**
 * This class represent every cell at the Puzzle matrix
 * @author Tzion
 *
 */
public class Matrix_Variable {

	public int getNum() {
		return this.num;
	}

	public Color getColor() {
		return this.color;
	}
	
	public Direction getDirection() {
		return this.direction;
	}

	//****************** Private Methods and Data *****************
	private int num;
	private Color color;
	private Direction direction;

	//****************** Constructors *****************
	public Matrix_Variable(int num , Color color , Direction direction) {
		this.num=num;
		this.color=color;
		this.direction=direction;
	}
}
