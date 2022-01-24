package Project;

import java.io.IOException;


import GUI_Swing.WireFrame;

public class Main {

	private static WireFrame wFrame;
	private static WireMap wMap;
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException, IOException {
		
		wFrame = new WireFrame();
		wFrame.pack();
		wFrame.setVisible(true);
		wFrame.setResizable(false);
		wMap = new WireMap(wFrame.getNumOfRowsOfGrid(), wFrame.getNumOfColumnOfGrid());
		wFrame.SetWmap(wMap);
		
		while(true) {
			Thread.sleep(wFrame.getSpeed());
			wMap.DiscreetStep(wFrame.GetPaused());
			wFrame.UpdateColor(wMap.getGrid(), wMap.changed());
			wMap.gridChanged = false;
		}
	}

}
