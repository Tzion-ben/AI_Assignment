package Puzzle_initFromFile;
/**
 * This class is represent a File Writer , it's write the output details of the result of the 
 * Algorithm that worked on the Puzzle.  
 * @author Tzion
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class outputTXT {

	/**
	 * This function create a new File and write in to it the flowing data:
	 * a. The path to the goal state if it's exists, if not so it will write null. 
	 * b. Will write the number of nodes that was created by the algorithm (OpenList and Close List).
	 * c. Will write the cost if there is a path.
	 * d. Will print the time taken by the algorithm to finish the work.
	 */
	private void setToFIle() {
		try {
			FileWriter goalEND= new FileWriter("output.txt");
			BufferedWriter newFile=new BufferedWriter(goalEND);

			newFile.write(this.path);
			newFile.newLine();
			newFile.write("Num: "+this.numNodes);
			newFile.newLine();
			newFile.write("Cost: "+this.cost);
			newFile.newLine();
			if(printTime) {
				newFile.write(String.valueOf(this.time)+" sec");
				newFile.newLine();
			}

			newFile.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	//****************************** Private Methods and Data ******************************
	private String path;
	private int numNodes;
	private int cost;
	private double time;
	private boolean printTime;

	//************************************ Constructors ************************************
	public outputTXT(String path, int numNodes, int cost, double time, boolean printTime) {
		this.path=path;
		this.numNodes=numNodes;
		this.cost=cost;
		this.time=time;
		this.printTime=printTime;

		setToFIle();
	}

}
