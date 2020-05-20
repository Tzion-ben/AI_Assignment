package Puzzle_initFromFile;
import java.io.BufferedReader;
/**
 * This class is ........ 
 * @author Tzion
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Colored_Matrix_Management.Color;
import Colored_Matrix_Management.Direction;

public class Game_init {
	public static void main(String[] args) {
		try {
			File newPuzzle = new File("input.txt");
			BufferedReader newPuzzleReader = new BufferedReader(new FileReader(newPuzzle)); 

			int runCount=0;
			String line=" ";

			while((line=newPuzzleReader.readLine()) !=null) {
				//running on the first 6 lines of the file with help of runCount
				if(runCount<6) {
					runCount++;
					System.out.println(line);
				}
				//pulling the board matrix with help of runCount
				if(runCount>=6) {
					runCount++;
					System.out.println(line);
				}
			}
			newPuzzleReader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not FOUND");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//****************** Private Methods and Data *****************
	private String algo;
	private String time;
	private String path;
	

	//****************** Constructors *****************

	public Game_init() {
		
	}
}