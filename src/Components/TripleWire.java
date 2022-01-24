package Components;

import Project.WireMap;

public class TripleWire extends ComponentsClass{

	public TripleWire(int r, int c, WireMap wMap) {
		super(r, c, wMap);
		Draw();
	}

	@Override
	void Draw() {
		int i = 0;
		for(; i < 14; i++)
		{
			wireMap.overWriteCellToWire(row-2, column+i);
			wireMap.overWriteCellToWire(row, column+i);
			wireMap.overWriteCellToWire(row+2, column+i);
		}
	}

}
