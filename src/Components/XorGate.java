package Components;

import Project.WireMap;

public class XorGate extends ComponentsClass{

	public XorGate(int r, int c, WireMap wMap) {
		super(r, c, wMap);
		Draw();
	}

	@Override
	void Draw() {
		int i =0;
		for(; i < 5; i++)
		{
			wireMap.overWriteCellToWire(row-1, column+i);
			wireMap.overWriteCellToWire(row+1, column+i);
		}
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row+2, column+i);
		i++;
		wireMap.overWriteCellToWire(row-3, column+i);
		wireMap.overWriteCellToWire(row+3, column+i);
		i++;
		wireMap.overWriteCellToWire(row-3, column+i);
		wireMap.overWriteCellToWire(row+3, column+i);
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		wireMap.overWriteCellToWire(row, column+i);
		i++;
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row+2, column+i);
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		i++;
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		wireMap.overWriteCellToWire(row, column+i);
		i++;
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		int t = i+7;
		for(; i < t; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
	}

}
