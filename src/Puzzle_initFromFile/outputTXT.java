package Puzzle_initFromFile;
/**
 * 
 * @author Tzion
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class outputTXT {
	
	/**
	 * 
	 */
	private void setToFIle() {
		try {
			FileWriter goalEND= new FileWriter("output.txt");
			BufferedWriter newFIle=new BufferedWriter(goalEND);
			newFIle.write(this.operations);
			newFIle.newLine();
			newFIle.write("Num: "+this.num);
			newFIle.newLine();
			newFIle.write("Cost: "+this.cost);
			newFIle.newLine();
//			if(printTime) {
//				newFIle.write((double)this.time/1000);
//				newFIle.newLine();
//			}
			newFIle.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	//****************** Private Methods and Data *****************
	private String operations;
	private int num;
	private int cost;
	private long time;
	private boolean printTime;

	//****************** Constructors *****************
	public outputTXT(String operations, int num, int cost, long time, boolean printTime) {
		this.operations=operations;
		this.num=num;
		this.cost=cost;
		this.time=time;
		this.printTime=printTime;
		setToFIle();
	}
}
