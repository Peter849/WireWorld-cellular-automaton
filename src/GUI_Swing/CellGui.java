package GUI_Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Components.ConductorDiode;
import Components.FlipFlop;
import Components.NotConductorDiode;
import Components.OrGate;
import Components.TripleWire;
import Components.XorGate;
import Project.*;

@SuppressWarnings("serial")
public class CellGui extends JButton implements ActionListener, MouseListener{
	
	private String choosenButton;
	private Cell myCell;
	private int row;
	private int column;
	private WireMap wMap;
	private WireFrame wFrame;
	private MyComboBox myComboBox;
	//CTOR:
	public CellGui(int r, int c, JPanel cellButtonsPanel, WireMap wM,  WireFrame wf, MyComboBox mc)
	{		
		row = r;
		column = c;
		/*-------------*/
		wMap = wM;
		wFrame = wf;
		myComboBox = mc;
		/*-------------*/
		myCell = new Empty(row, column);
		wMap.addCell(myCell);
		choosenButton = wFrame.getChoosenButton();
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(100,100));
		this.addActionListener(this);
		this.addMouseListener(this);
		this.setFocusable(false);
		Border border = new LineBorder(Color.DARK_GRAY, 1);
		this.setBorder(border);
	
		cellButtonsPanel.add(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (choosenButton) {
			case "vezeték": {
				this.setBackground(new Color(239,185,73));
				myCell = new Wire(row, column);
				wMap.overWriteCell(myCell);
			}break;
			case "elektronfej":{
				if(wMap.getCell(row, column).GetName().equals("vezeték"))
				{
					this.setBackground(new Color(73,208,239));
					myCell = new ElectronHead(row, column);
					wMap.overWriteCell(myCell);
				}
			}break;
			//mivel elektronfarok nem rakható le, ezért ilyenkor lényegében egybõl egy vezeték lesz
			case "elektron farok":{ 
				this.setBackground(new Color(239, 73, 73));//csak a látvány miatt, egyébként egybõl Wire-ként kezeli a program
				myCell = new Wire(row, column);
				wMap.overWriteCell(myCell);
			}break;
		default:
			this.setBackground(Color.BLACK);
			myCell = new Empty(row, column);
			wMap.overWriteCell(myCell);
		}
		
	}
	
	public void SetChoosenButton(String cb) { choosenButton = cb; }
	public void SetMyCell(Cell c) { myCell = c; }
	public Cell GetMyCell() { return myCell; }
	
	@SuppressWarnings("unused")
	@Override
	public void mouseClicked(MouseEvent event) {
		
		if(event.getButton() == MouseEvent.BUTTON2)
		{
			//csak akkor lehet importálni, ha üres cellából indul ki
			if(myCell.GetName().equals("üres"))
			{
				wFrame.SetMiddleClicked(row, column);
				if(myComboBox.getSubBox().getSelectedItem().equals("Hármas vezeték"))
				{
					TripleWire tripleWire = new TripleWire(row, column, wMap);
				}
				if(myComboBox.getSubBox().getSelectedItem().equals("Vezetõ dióda"))
				{
					ConductorDiode cDiode1 = new ConductorDiode(row, column, wMap);
				}
				if(myComboBox.getSubBox().getSelectedItem().equals("Nem vezetõ dióda"))
				{
					NotConductorDiode cDiode2 =new NotConductorDiode(row, column, wMap);
				}
				if(myComboBox.getSubBox().getSelectedItem().equals("OR kapu"))
				{
					OrGate orGate = new OrGate(row, column, wMap);
				}
				if(myComboBox.getSubBox().getSelectedItem().equals("XOR kapu"))
				{
					XorGate xorGate = new XorGate(row, column, wMap);
				}
				if(myComboBox.getSubBox().getSelectedItem().equals("Flip-Flop"))
				{
					FlipFlop flipFlop = new FlipFlop(row, column, wMap);
				}
	
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
