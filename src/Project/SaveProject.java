package Project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import GUI_Swing.MyComboBox;

public class SaveProject {
	public Cell[][] grid;
	private String filename;
	public SaveProject(WireMap wm, MyComboBox mcb) {
		filename = JOptionPane.showInputDialog("Adja meg a fájlnevet.");
		if(filename != null)
		{
			File file = new File("SavedFiles\\"  + filename);
			try {
				FileOutputStream f = new FileOutputStream(file);
				ObjectOutputStream objOut = new ObjectOutputStream(f);
				grid = wm.getGrid();
				objOut.writeObject(grid);
				objOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getFileName() { 
		if(filename!=null) return filename;
		else return "";
		}
	
}
