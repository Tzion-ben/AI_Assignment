package DataStructure;
/**
 * 
 * @author Tzion
 */
import Colored_Matrix_Management.Color;

public class Psiotion {

	//simple getters and setters
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	//****************** Private Data *****************
	private int num;
	private int i;
	private int j;

	//****************** Constructors *****************
	public Psiotion(int num , int i, int j) {
		this.num=num;
		this.i=i;
		this.j=j;
	}
	
}