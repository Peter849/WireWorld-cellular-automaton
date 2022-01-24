package Project;
	
import java.io.Serializable;


@SuppressWarnings("serial")
public class WireMap implements Serializable{
	private Cell[][] grid;
	private Cell[][] tempGrid;
	static boolean gridChanged;
	
	public WireMap(int r, int c)
	{
		gridChanged = false;
		grid = new Cell[r][c];
		tempGrid = new Cell[r][c];
		InitGrids();
	}
	
	public WireMap(Cell[][] ingrid)
	{
		gridChanged = false;
		grid = ingrid;
		tempGrid = ingrid;
	}
	
	public Cell[][] getGrid(){ return grid; }
	public Cell[][] getTempGrid() { return tempGrid; }
	public Cell getCell(int r, int c) { return grid[r][c]; }
	public boolean changed() { return gridChanged; }
	
	//inicializáció: az összes cella alapból üres
	public void InitGrids()
	{
		for (int row = 0; row < grid.length; row++) { //sorok
            for ( int column = 0 ;column < grid[row].length; column++) {//oszlopok
            	grid[row][column] = new Empty(row, column);
            	tempGrid[row][column] = new Empty(row, column);
            }
        }
	}
	
	public void addCell(Cell cObj)
	{
		if(cObj == null) return;
		grid[cObj.GetRow()][cObj.GetColumn()] = cObj;
	}
	
	public void overWriteCell(Cell cObj)
	{
		if(cObj == null) return;
		grid[cObj.GetRow()][cObj.GetColumn()] = cObj;
	}
	public void overWriteCellToWire(int r, int c)
	{
		if(!(r < 1 || r >= grid.length-1) && !(c < 1 || c >= grid[0].length-1)) 
			grid[r][c] = new Wire(r, c);	
	}
	
	synchronized public void DiscreetStep(boolean p) {

		for (int row = 1; row < grid.length-1; row++) { //sorok
			
            for ( int column = 1 ;column < grid[row].length-1; column++) {//oszlopok

            	if(grid[row][column].GetName().equals("vezeték") && !p)
            	{
            		//vezetékbõl HA 1 vagy 2 szomszédos ef van, akkor elektronfej lesz
            		int nc = NeighbourCheck(grid[row][column]);
            	
            		if(nc == 1 || nc == 2)
            		{
            			tempGrid[row][column] = new ElectronHead(column, nc);
            		}else {
            			//egyébként: vezeték marad
            			tempGrid[row][column] = new Wire(row, column);
					}	
            	}
            	if(grid[row][column].GetName().equals("elektronfej") && !p)
            	{
            		//elektronfejbõl elektronfarok lesz
            		tempGrid[row][column] = new ElectronTail(row, column);
            	}
            	if(grid[row][column].GetName().equals("elektron farok") && !p)
            	{
            		//elektron farokból vezeték lesz
            		tempGrid[row][column] = new Wire(row, column);
            	}
            }
        }
		
		//Iyenkor felülírja a Grid-et a tempGrid-beli értékekkel.
		for (int row = 0; row < tempGrid.length; row++) { //sorok
            for ( int column = 0 ;column < tempGrid[row].length; column++) {//oszlopok
            	grid[row][column] = tempGrid[row][column];
            }
		}
		
		
	}
	
	//visszaadja az adott cella melletti elektronfejek számát:
	public int NeighbourCheck(Cell cObj)
	{
		if(cObj == null) return 0;
		int num = 0;
		for(int i = cObj.GetRow()-1; i <= cObj.GetRow()+1; i++)
		{
			for(int j = cObj.GetColumn()-1; j <= cObj.GetColumn()+1; j++)
			{
				//Ha nem az adott celláról van szó:
				if(!(i == cObj.GetRow() && j == cObj.GetColumn()))
				{
					if(grid[i][j].GetName().equals("elektronfej"))
					{
						num++;
					}
				}
			}
		}
		return num;
	}

	public void copy(WireMap temp) {
		
		for (int row = 0; row < grid.length; row++) { //sorok
            for ( int column = 0 ;column < grid[row].length; column++) {//oszlopok
            	grid[row][column] = temp.getGrid()[row][column];
            }
		}
	}
	
	

}

