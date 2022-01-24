package junittes;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import GUI_Swing.WireFrame;
import Project.WireMap;

public class WireFrameTest {
	
	WireFrame wireFrame;
	
	
	@Before
	public void setUp() 
	{
		wireFrame = new WireFrame();
	}
	
	@Test
	public void NullTest() {
		WireMap wireMap = new WireMap(wireFrame.getHeight(),wireFrame.getWidth());
		Assert.assertNull(wireFrame.getwMap());
		wireFrame.SetWmap(wireMap);
		Assert.assertNotNull(wireFrame.getwMap());
	}
	
	@Test
	public void TestTitle()
	{
		Assert.assertEquals("WireWorld Project", wireFrame.getTitle());
	}
	
	@Test
	public void TestSize()
	{
		Dimension dim =  Toolkit.getDefaultToolkit().getScreenSize();
		double delta =  1e-6;
		
		dim.width/=1.4;
		dim.height/=1.4;
		Assert.assertEquals(wireFrame.getSize().getWidth(), dim.getWidth(),delta);
		Assert.assertEquals(wireFrame.getSize().getHeight(), dim.getHeight(), delta);	
	}
	
	@Test
	public void SpeedTest()
	{
		wireFrame.setSpeed("Gyors");
		Assert.assertEquals(600/2, wireFrame.getSpeed(),0);
	}
}
