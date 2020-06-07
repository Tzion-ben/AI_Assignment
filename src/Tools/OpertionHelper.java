package Tools;

public class OpertionHelper {

	public OpertionHelper() {;}
	//*******************************************************************************************//

	/**
	 * This function create a String from a int matrix
	 * @param mat
	 * @return String result
	 */
	public String hash (int [][] mat) {
		String result="";
		for(int i=0 ; i<mat.length ; i++) 
			for(int j=0 ; j<mat[0].length ; j++) {
				result=result+String.valueOf(mat[i][j]);
			}
		return result;

	}

	/**
	 * 
	 * @return
	 */
	public String GetPathSoFar (String op) {
		String PathSoFar ="";
		if(op.compareTo("0-N") == 0) {
			return PathSoFar;
		}
		else {
			String [] opertionSoFar = op.split("-");
			PathSoFar=PathSoFar+opertionSoFar[0]+opertionSoFar[1]+"-";
		}
		return PathSoFar;
	}
}
