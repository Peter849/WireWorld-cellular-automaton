package Components;

import Project.WireMap;

public class ConductorDiode extends ComponentsClass {

	
	public ConductorDiode(int r, int c, WireMap wMap) {
		super(r,c,wMap);
		Draw();
 	}
	
	void Draw()
	{
		int i = 0;
		for(; i < 7; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		wireMap.overWriteCellToWire(row, column+i	);
		i++;
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		i++;
		System.out.println("not");
		for(; i <= 16 ; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
	}
}
