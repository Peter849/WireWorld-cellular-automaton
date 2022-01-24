package Components;

import Project.WireMap;

public class FlipFlop extends ComponentsClass{

	public FlipFlop(int r, int c, WireMap wMap) {
		super(r, c, wMap);
		Draw();
	}

	@Override
	void Draw() {
		int i =0;
		for(; i < 7; i++)
		{
			if(i <=5) wireMap.overWriteCellToWire(row+4, column+i);
			if(i <=6) wireMap.overWriteCellToWire(row-4, column+i);
			if(i==5)
			{
				wireMap.overWriteCellToWire(row+2, column+i);
			}
			if(i==6)
			{
				wireMap.overWriteCellToWire(row-2, column+i);
				wireMap.overWriteCellToWire(row+1, column+i);
				wireMap.overWriteCellToWire(row+2, column+i);
				wireMap.overWriteCellToWire(row+3, column+i);
				wireMap.overWriteCellToWire(row, column+i);
			}
		}
		wireMap.overWriteCellToWire(row-3, column+i);
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row-1, column+i);
		wireMap.overWriteCellToWire(row+2, column+i);
		i++;
		wireMap.overWriteCellToWire(row-2, column+i);
		wireMap.overWriteCellToWire(row+1, column+i);
		wireMap.overWriteCellToWire(row, column+i);
		i++;
		for(; i < 16; i++)
		{
			wireMap.overWriteCellToWire(row, column+i);
		}
	}

}
