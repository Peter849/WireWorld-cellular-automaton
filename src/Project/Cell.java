package Project;

import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class Cell implements Serializable{
	private String name;	//�rt�k
	private int Row;		//y koordin�ta
	private int Column;		//x koordin�ta
	
	public Cell(int r, int c, String n) {
		Row = r;
		Column = c;
		name = n;
	}
	
	public int GetRow() { return Row; }
	public int GetColumn() { return Column; }
	public String GetName() { return name; }
}
