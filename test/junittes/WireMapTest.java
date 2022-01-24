package junittes;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import Project.Cell;
import Project.Wire;
import Project.WireMap;

public class WireMapTest {
	
	static WireMap wireMap;
	static Cell cell; 
	static Cell c;
	
	@BeforeClass
	public static void init()
	{
		wireMap = new WireMap(5,6);
		cell = new Wire(2,2);
		c= wireMap.getGrid()[cell.GetRow()][cell.GetColumn()];
	}
	
	@Test
	public void InitTest() {
		wireMap.overWriteCell(cell);
		wireMap.InitGrids();
		Assert.assertEquals(wireMap.getGrid()[cell.GetRow()][cell.GetColumn()].GetName(), c.GetName());
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void IsCellNull() throws Exception
	{
		wireMap.getCell(5, 7);
	}
	
	@Test
	public void IsCellChanged()
	{	
		wireMap.overWriteCell(cell);
		Assert.assertNotNull(wireMap.getGrid()[2][2]);
	}
	
	@Test
	public void NotSameGridObject()
	{
		Assert.assertNotSame(wireMap.getGrid(), wireMap.getTempGrid());
	}
}
