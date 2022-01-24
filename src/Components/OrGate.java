package Components;

import Project.WireMap;

public class OrGate extends ComponentsClass {

	public OrGate(int r, int c, WireMap wMap) {
		super(r, c, wMap);
		Draw();
	}

	@Override
	void Draw() {
		int i =0;
		for(; i < 7; i++)
		{
			wireMap.overWriteCellToWire(row-1, column+i);
			wireMap.overWriteCellToWire(row+1, column+i);	
		}
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row+2, column+i);
		i++;
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row+2, column+i);
		wireMap.overWriteCellToWire(row, column+i);
		i++;
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		wireMap.overWriteCellToWire(row, column+i);
		int t = i+7;
		for(; i < t; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
	}

}
