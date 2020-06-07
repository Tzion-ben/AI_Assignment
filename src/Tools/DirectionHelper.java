package Tools;

import java.util.Hashtable;

import DataStructure.Direction;

public class DirectionHelper {

	public DirectionHelper() {;}
	//*******************************************************************************************//

	/**
	 * This function returns the reversed operation of a given operation
	 * @return
	 */
	public Direction getReversedOp (Direction d) {
		Direction rev = null;
		switch (d) {
		case R: 
			rev = Direction.L;
			break;
		case U:
			rev = Direction.D;
			break;
		case L: 
			rev = Direction.R;
			break;
		case D:
			rev = Direction.U;
			break;
		}
		return rev;
	}

	/**
	 * This function removes from the allow operator of this state the operation
	 * that is reversed to the operation that create this state  
	 */
	public void RemoveReversedOp (Direction d,  Hashtable<Integer, String> allowOP) {
		Direction opsitOp = getReversedOp(d);
		for(int i=0; i <4 ; i++) {
			if(allowOP.containsKey(i)) {
				String toCut = allowOP.get(i);
				String [] opertion = toCut.split("-");
				if (Direction.valueOf(opertion[1]).compareTo(opsitOp) == 0 ) 
					allowOP.remove(i);
			}
		}
	}
}
