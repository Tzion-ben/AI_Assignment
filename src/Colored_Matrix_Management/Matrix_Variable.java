package Colored_Matrix_Management;
/**
 * This class represent every cell at the Puzzle matrix
 * @author Tzion
 *
 */
public class Matrix_Variable {

	//a simple getters and setters
	public int getNum() {
		return this.num;
	}

	public Color getColor() {
		return this.color;
	}

	public void setNum(int num) {
		this.num=num;
	}

	public void setColor(Color color) {
		this.color=color;
	}

	//****************** Private Data *****************
	private int num;
	private Color color;

	//****************** Constructors *****************
	public Matrix_Variable(int num , Color color ) {
		this.num=num;
		this.color=color;
	}
}