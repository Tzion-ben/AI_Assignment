package Puzzle_initFromFile;
/**
 * This class representing a simple class of reading a txt file and create a Strings
 * with every ingredient , to create a Puzzle Game matrix.  
 * @author Tzion
 */
import java.io.BufferedReader;
/**
 * This class is is about reading the Puzzle details from TXT file 
 * @author Tzion
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class initTXT {

	//********************************** simple getters	********************************** 
	public String getAlgo() {return algo;}

	public boolean getShowTimeORnot() {return Time;}

	public boolean getShowOpenListORnot() {return OpenList;}

	public String getMatSIZE() {return matSIZE;}

	public String getBlackBlocks() {return BlackBlocks;}

	public String getRedBloacks() {return RedBloacks;}

	public String getGame_Matrix() {return Game_Matrix;}

	/**
	 * This private function reading the game details from TXT file.
	 * a. The first line is the Algorithm that will work on the Puzzle
	 * b. 2nd line says to print the game time or not 
	 * c. 3rd line says if show the open list or not
	 * d. 4th line it's the size of the matrix
	 * e. 5th line it's the the numbers at the blocks that represents the black color
	 * f. 5th line it's the the numbers at the blocks that represents the red color
	 * g. the lines from 6th line it's the matrix it self
	 */
	private void setFromFile(String Puzzle) {
		try {
			File newPuzzle = new File(Puzzle);
			BufferedReader newPuzzleReader = new BufferedReader(new FileReader(newPuzzle)); 

			String line="";
			while((line=newPuzzleReader.readLine()) !=null) {
				//running on the first 6 lines of the file 
				this.algo=line;
				
				this.showTimeORnot=newPuzzleReader.readLine();
				if(this.showTimeORnot.equals("with time")) {this.Time = true;}

				this.showOpenListORnot=newPuzzleReader.readLine();
				if(this.showOpenListORnot.equals("with open")) {this.OpenList = true;}

				this.matSIZE=newPuzzleReader.readLine();

				//want to cut the colon from the line, to work only with the numbers after it
				String temp_BlackBlocks=newPuzzleReader.readLine();
				String [] withOUTaColon_Black=temp_BlackBlocks.split(": ");
				if(withOUTaColon_Black.length>1) {this.BlackBlocks=withOUTaColon_Black[1];}

				//else: if no have a numbers for BLACK Blocks
				else{this.BlackBlocks=null;}
				//**end the cutting for the BLACK Blocks

				//want to cut the colon from the line, to work only with the numbers after it
				String temp_RedBlocks=newPuzzleReader.readLine();
				String [] withOUTaColon_Red=temp_RedBlocks.split(": ");
				if(withOUTaColon_Red.length>1) {this.RedBloacks=withOUTaColon_Red[1];}

				//else: if no have a numbers for RED Blocks
				else {this.RedBloacks=null;}
				//**end the cutting for the RED Blocks

				//pulling the board matrix
				while((line=newPuzzleReader.readLine()) !=null) {
					this.Game_Matrix+=line+",";
					continue;
				}
			}
			
			newPuzzleReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not FOUND");
			e.printStackTrace();
		}
		catch (IOException e) {e.printStackTrace();}
	}

	//****************************** Private Methods and Data ******************************
	private String algo ;
	
	private String showTimeORnot;
	private boolean Time = false;
	
	private String showOpenListORnot;
	private boolean OpenList = false;

	private String matSIZE;
	private String BlackBlocks;
	private String RedBloacks;
	private String Game_Matrix="";

	//************************************ Constructors ************************************
	public initTXT(String newPuzzle) {setFromFile(newPuzzle);}

}