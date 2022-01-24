package Components;

import Project.WireMap;

abstract public class ComponentsClass {
   protected int row;
   protected int column;
   protected WireMap wireMap;
   
 //Az egyes komponenseket az al�bbi webhelyr�l n�ztem ki:
		//https://www.quinapalus.com/wi-index.html
   public ComponentsClass (int r, int c, WireMap wMap) {
      
      		
      		//Az adott sor �s oszlop �rt�ke, ahol kezd�dik a rajzol�s
      		row = r; 
      		column = c;
      		//az a WireMap, amelyen �ppen rajzolunk
      		wireMap = wMap;
      	}
   
   abstract void Draw();
   }
