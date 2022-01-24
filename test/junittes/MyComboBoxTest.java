package junittes;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import GUI_Swing.MyComboBox;
import GUI_Swing.WireFrame;
import Project.WireMap;

public class MyComboBoxTest {
	
	static WireFrame wireFrame;
	static MyComboBox myComboBox;
	static WireMap wiremap;
	
		@BeforeClass
		public static void init()
		{
			wireFrame = new WireFrame();
			myComboBox = new MyComboBox(wireFrame);
			wiremap = new WireMap(wireFrame.getHeight(),wireFrame.getWidth());
			wireFrame.SetWmap(wiremap);
		}
		
		@Test
		public void ArrayEquality()
		{
			String[] temparray = {"Import Component", "Import Project"};
			Assert.assertArrayEquals(temparray, myComboBox.getMainStrings());
		}
		
		@Test
		public void CheckNullExeption() throws Exception
		{
			Assert.assertNull(myComboBox.GetWmap()); 
		}
		
		@Test
		public void CheckNull()
		{
			myComboBox.AddProject("FileName", wireFrame.getwMap());
			Assert.assertNotNull(myComboBox.getProjectName("FileName"));
		}
}
