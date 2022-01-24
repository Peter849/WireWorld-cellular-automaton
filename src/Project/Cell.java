package Project;

import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class Cell implements Serializable{
	private String name;	//érték
	private int Row;		//y koordináta
	private int Column;		//x koordináta
	
	public Cell(int r, int c, String n) {
		Row = r;
		Column = c;
		name = n;
	}
	
	public int GetRow() { return Row; }
	public int GetColumn() { return Column; }
	public String GetName() { return name; }
}
