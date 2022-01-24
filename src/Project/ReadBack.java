package Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import GUI_Swing.MyComboBox;
 

public class ReadBack  {
	private Cell[][] grid;
	private File folder;
	
	public ReadBack(MyComboBox mcb) {
		reedFiles(mcb);
	} 
		
	public void reedFiles(MyComboBox mcb) {
		folder = new File("SavedFiles\\");
	    File[] directoryListing = folder.listFiles();
		  if (directoryListing != null) {
		    for (File file : directoryListing) {
		    	try {
		    		FileInputStream f = new FileInputStream("SavedFiles\\" +file.getName());
		    		ObjectInputStream in =new ObjectInputStream(f);
		    		grid = (Cell[][])in.readObject();
		    		WireMap readWireMap = new WireMap(grid);
		    		if(!mcb.IsContains(file.getName())) mcb.AddProject(file.getName(), readWireMap);
		    		in.close();
		    		} catch(IOException ex) {
		    		
		    		}catch (ClassNotFoundException ex) {
					}
		    }
		  } 
	}
}
