package Puzzle_initFromFile;
import java.io.BufferedReader;
/**
 * This class is is about reading the Puzzle details from TXT file 
 * @author Tzion
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game_init {

	/**
	 * This functions represent regular getters and setters
	 * @return
	 */
	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public String getShowTimeORnot() {
		return showTimeORnot;
	}

	public void setShowTimeORnot(String showTimeORnot) {
		this.showTimeORnot = showTimeORnot;
	}

	public String getShowOpenListORnot() {
		return showOpenListORnot;
	}

	public void setShowOpenListORnot(String showOpenListORnot) {
		this.showOpenListORnot = showOpenListORnot;
	}

	public String getMatSIZE() {
		return matSIZE;
	}

	public void setMatSIZE(String matSIZE) {
		this.matSIZE = matSIZE;
	}

	public String getBlackBlocks() {
		return BlackBlocks;
	}

	public void setBlackBlocks(String blackBlocks) {
		BlackBlocks = blackBlocks;
	}

	public String getRedBloacks() {
		return RedBloacks;
	}

	public void setRedBloacks(String redBloacks) {
		RedBloacks = redBloacks;
	}

	public String getGreenBloacks() {
		return GreenBloacks;
	}

	public void setGreenBloacks(String greenBloacks) {
		GreenBloacks = greenBloacks;
	}

	public String getGame_Matrix() {
		return Game_Matrix;
	}

	public void setGame_Matrix(String game_Matrix) {
		Game_Matrix = game_Matrix;
	}

	/************************************************************************************************
	 * This private function reading the game details from TXT file.
	 ************************************************************************************************/
	private void setFromFile(File newPuzzle) {
		try {
			//File newPuzzle = new File("input.txt");
			BufferedReader newPuzzleReader = new BufferedReader(new FileReader(newPuzzle)); 

			int runCount=0;
			String line=" ";
			while((line=newPuzzleReader.readLine()) !=null) {
				//running on the first 6 lines of the file with help of runCount
				if(runCount<6) {
					runCount++;
					this.algo=line;
					this.showTimeORnot=newPuzzleReader.readLine();
					this.showTimeORnot=newPuzzleReader.readLine();
					this.showOpenListORnot=newPuzzleReader.readLine();
					this.matSIZE=newPuzzleReader.readLine();

					//want to cut the colon from the line, to work only with the numbers after it
					String temp_BlackBlocks=newPuzzleReader.readLine();
					String [] withOUTaColon_Black=temp_BlackBlocks.split(":");
					if(withOUTaColon_Black.length>1)
						this.BlackBlocks=withOUTaColon_Black[1];
					else
						//else: if no have a numbers for RED Blocks
						this.BlackBlocks=null;
					//**end the cutting for the BALCK Blocks
					
					//want to cut the colon from the line, to work only with the numbers after it
					String temp_RedBlocks=newPuzzleReader.readLine();
					String [] withOUTaColon_Red=temp_RedBlocks.split(":");
					if(withOUTaColon_Red.length>1)
						this.BlackBlocks=withOUTaColon_Red[1];
					else
						//else: if no have a numbers for BLACK Blocks
						this.RedBloacks=null;
					//**end the cutting for the RED Blocks

				}
				//pulling the board matrix with help of runCount
				if(runCount>=6) {
					while(runCount<8) {	
						runCount++;
						this.Game_Matrix+=newPuzzleReader.readLine()+",";
					}
					//added the last line without ","
					this.Game_Matrix+=newPuzzleReader.readLine();
				}
			}
			newPuzzleReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not FOUND");
			e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}

	//****************** Private Methods and Data *****************
	private String algo;
	private String showTimeORnot;
	private String showOpenListORnot;
	private String matSIZE;
	private String BlackBlocks;
	private String RedBloacks;
	private String GreenBloacks;
	private String Game_Matrix;


	//****************** Constructors *****************
	public Game_init(File newPuzzle) {
		setFromFile(newPuzzle);
	}
}