package Components;

import Project.WireMap;

public class NotConductorDiode extends ComponentsClass {
	
	public NotConductorDiode(int r, int c, WireMap wMap) {
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
		i++;
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		
		for(; i < 15 ; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
	}
}
