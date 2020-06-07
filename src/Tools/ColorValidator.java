package Tools;

import java.util.Hashtable;

import DataStructure.Color;
public class ColorValidator {

	//****************** Private Data *****************

	private Hashtable<Integer, Color> numbersColors = new Hashtable<Integer, Color>();

	public ColorValidator(Hashtable<Integer, Color> numbersColors) {this.numbersColors=numbersColors;}
	//*******************************************************************************************//

	/**
	 * This function return a number associated with a "color" at the Hach Table
	 * @param num
	 * @return
	 */
	public Color getColor(int num) {
		if(num==0) {return Color.E;}
		while(!this.numbersColors.isEmpty()) 
			if(this.numbersColors.containsKey(num)) 
				return this.numbersColors.get(num);
		return null;
	}

	/**
	 * this method return the cost to move a block depend on it's color, RED->30
	 * GREEN->1, BLACK->can't move so 0 , E-> for the start state
	 * @return cost depend on the color
	 */
	public int getCost (Color c){
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

	/**
	 * this method checks the color of the block and says if this block can move or 
	 * not: RED and GRENN - can move, BLACK - can't move 
	 * @param color c
	 * @return
	 */
	public boolean getAllowORnot(Color c) {
		boolean ans=false;
		switch (c) {
		case RED:
		case GREEN:
			ans=true;
			break;
		case BLACK:
			ans=false;
			break;
		}
		return ans;
	}
}
