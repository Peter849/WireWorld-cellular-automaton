package Components;

import Project.WireMap;

abstract public class ComponentsClass {
   protected int row;
   protected int column;
   protected WireMap wireMap;
   
 //Az egyes komponenseket az alábbi webhelyrõl néztem ki:
		//https://www.quinapalus.com/wi-index.html
   public ComponentsClass (int r, int c, WireMap wMap) {
      
      		
      		//Az adott sor és oszlop értéke, ahol kezdõdik a rajzolás
      		row = r; 
      		column = c;
      		//az a WireMap, amelyen éppen rajzolunk
      		wireMap = wMap;
      	}
   
   abstract void Draw();
   }
