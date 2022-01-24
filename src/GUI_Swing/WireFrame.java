package GUI_Swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Project.*;

@SuppressWarnings("serial")
public class WireFrame extends JFrame implements ActionListener{

	private CardLayout cardLayout = new CardLayout();
	private JPanel MAINPanel = new JPanel();//Tárolja az egyes paneleket a Cardlayout számára.
	private String actualPanel;
	private static int JButtonSize;
	private static int Wait;
	private static Point middleClickedPoint;
	private Dimension screenDimension;
	
	static
	{
		JButtonSize = 20;
		Wait = 300;		//ms
		middleClickedPoint = new Point();
	}
	
	//Menü:...
	private MyMenu menuPanel;
	//Beállítások panel:...
	private OptionsPanel optionsPanel;
	
	//Játék:...
	private JPanel wireWorldPanel;
	private JPanel cellButtonsPanel;
	private JPanel upperPanel;
	private WireMap wMap;
	public CellGui[][] CellArray;
	private int Width;//oszlopok száma
	private int Height;//sorok száma 
	private JButton üresButton;
	private JButton vezetékButton;
	private JButton elektronFejButton;
	private JButton elektronFarokButton;
	private String choosenButton;
	private JTextField cbTextField;
	private JButton clearButton;
	private JButton pauseButton;
	private boolean paused;
	private JButton mButton;
	private JButton importButton;

	//JComboBox:...
	private MyComboBox MCB;
			               
	
	public WireFrame()
	{
		super("WireWorld Project");
		screenDimension =  Toolkit.getDefaultToolkit().getScreenSize();
		Width = (int) (screenDimension.getWidth()/JButtonSize);
		Height = (int) (screenDimension.getHeight()/JButtonSize);
		choosenButton = "üres";
		MCB = new MyComboBox(this);

		MyKeyListener mkObj = new MyKeyListener();
		MAINPanel.setLayout(cardLayout);
		MAINPanel.addKeyListener(mkObj);
		MAINPanel.setFocusable(true);
		
		//Menü:
		/*---------------------------------------------------------------------------------------*/
		menuPanel = new MyMenu(this, MCB);
		MAINPanel.add(menuPanel, "menü");
		ChangeCard("menü");
		/*---------------------------------------------------------------------------------------*/
		//Beállítások:
		optionsPanel = new OptionsPanel(this);
		MAINPanel.add(optionsPanel, "options");
		/*---------------------------------------------------------------------------------------*/
		
		//Játék:
		/*---------------------------------------------------------------------------------------*/
		wireWorldPanel = new JPanel();
		wireWorldPanel.setLayout(new BorderLayout());
		
		CellArray = new CellGui
				[Height+2] 	   //elsõ paraméter: sorok száma(magasság)
				[Width+2]; 	   //második paraméter: oszlopok száma(szélesség)
		
		
		cellButtonsPanel = new JPanel();
		wireWorldPanel.add(cellButtonsPanel, BorderLayout.CENTER);
		cellButtonsPanel.setLayout(new GridLayout(Height, Width));
			
		Dimension du = new Dimension(40,40);//több helyen is használva van.	
	
		Image img = new ImageIcon(this.getClass().getResource("/ps.png")).getImage();
		Image resizedImg = img.getScaledInstance((int)du.getWidth()+10, (int) du.getHeight(), java.awt.Image.SCALE_SMOOTH);
		
	    pauseButton = new JButton();
	    pauseButton.setIcon(new ImageIcon(resizedImg));
	    pauseButton.setBackground(Color.WHITE);
	    pauseButton.setBorder(new LineBorder(new Color(1,173,132)));
	    pauseButton.addActionListener(this);
	    pauseButton.setActionCommand("pause");
	    pauseButton.setPreferredSize(new Dimension((int)du.getWidth()+10,(int) du.getHeight()));
	    paused = false;
	    
	    importButton = new JButton("Import");
	    importButton.addActionListener(this);
	    importButton.setPreferredSize(new Dimension(80,(int) du.getHeight()));  
	    importButton.setBackground(Color.BLACK);
	    importButton.setForeground(Color.cyan);
	    Font buttonFont =new Font(importButton.getFont().getName(),Font.BOLD,importButton.getFont().getSize());
	    importButton.setFont(buttonFont);
	    importButton.setEnabled(false);
				
		üresButton = new JButton();
		vezetékButton = new JButton();
		elektronFejButton = new JButton();
		elektronFarokButton = new JButton();
		
		üresButton.setActionCommand("üres");
		üresButton.addActionListener(this);
		vezetékButton.setActionCommand("vezeték");
		vezetékButton.addActionListener(this);
		elektronFejButton.setActionCommand("elektronfej");
		elektronFejButton.addActionListener(this);
		elektronFarokButton.setActionCommand("elektron farok");
		elektronFarokButton.addActionListener(this);
				
		üresButton.setBackground(Color.BLACK);
		vezetékButton.setBackground(new Color(239,185,73));			//citromsárga
		elektronFejButton.setBackground(new Color(73,208,239)); 	//világos kék
		elektronFarokButton.setBackground(new Color(239, 73, 73));  //piros
		
		
		üresButton.setPreferredSize(du);
		vezetékButton.setPreferredSize(du);
		elektronFejButton.setPreferredSize(du);
		elektronFarokButton.setPreferredSize(du);

		 
		üresButton.setFocusable(false);
		vezetékButton.setFocusable(false);
		elektronFejButton.setFocusable(false);
		elektronFarokButton.setFocusable(false); 
		
		üresButton.setBorderPainted(false);
		vezetékButton.setBorderPainted(false);
		elektronFejButton.setBorderPainted(false);
		elektronFarokButton.setBorderPainted(false); 
		
		
		cbTextField = new JTextField(choosenButton);
		cbTextField.setPreferredSize(new Dimension(200,(int) du.getHeight()));
		cbTextField.setForeground(new Color(0x00FF00));
		cbTextField.setBackground(Color.BLACK);
		cbTextField.setHorizontalAlignment(JTextField.CENTER);
		cbTextField.setBorder(null);
		cbTextField.setEditable(false);
		
		clearButton = new JButton("Clear Screen");
		clearButton.setPreferredSize(new Dimension(150,(int) du.getHeight()));
		clearButton.addActionListener(this);
		
		mButton = new JButton("MENÜ");
		mButton.setPreferredSize(new Dimension(70,(int) du.getHeight()));
		mButton.addActionListener(this);
		
		upperPanel = new JPanel(new FlowLayout()); 
		upperPanel.add(pauseButton);
		upperPanel.add(MCB);
		upperPanel.add(importButton);
		upperPanel.add(üresButton);
		upperPanel.add(vezetékButton);
		upperPanel.add(elektronFejButton);
		upperPanel.add(elektronFarokButton);
		upperPanel.add(cbTextField);
		upperPanel.add(clearButton);
		upperPanel.add(mButton);

		//upperPanel.setLayout(new GridLayout(1, upperPanel.getComponentCount(), 0, 20));
		wireWorldPanel.add(upperPanel,BorderLayout.NORTH);
		/*---------------------------------------------------------------------------------------*/
		
		
		MAINPanel.add(wireWorldPanel, "WW");
		this.add(MAINPanel);
		
		//JFrame-el kapcsolatos beállítások:
		
		screenDimension.height/=1.4;
		screenDimension.width/=1.4;
		
		this.setPreferredSize(screenDimension);
		this.setLocation(screenDimension.width/2,screenDimension.height/2);
		this.pack();
		this.setLocationByPlatform(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void SetWmap(WireMap wm)
	{
		wMap = wm;
		for (int row = 1; row < CellArray.length-1; row++) { //sorok
            for ( int column = 1 ;column < CellArray[row].length-1; column++) {//oszlopok
            	CellArray[row][column] =
            			new CellGui(row,column,cellButtonsPanel,wMap,this,MCB);
            }
        }
		menuPanel.SetWmap(wm);
	}	
	
	//Getter függvények:
	public int getNumOfRowsOfGrid() { return Height+2;}
	public int getNumOfColumnOfGrid() { return Width+2;}
	public int getSpeed() { return Wait; }
	public String getChoosenButton() { return choosenButton; }
	public boolean GetPaused() { return paused; }	
	public WireMap getwMap() {return wMap;}		
	
	//Setter függvények:
	public void SetMiddleClicked(int r, int c) { middleClickedPoint.setLocation(r, c);}
	
	public void setSpeed(String str)
	{
		if(str.equals("Lassú"))
		{
			Wait = 600;
		}else if(str.equals("Közepes"))
		{
			Wait = 300;
		}else if(str.equals("Gyors"))
		{
			Wait = 100;
		}
	}
	
	public void ChangeSize(String str)
	{
		this.setResizable(true);
		if(str.equals("fullscreen"))
		{
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else if(str.equals("windowed")){
			this.setSize(screenDimension);
		}
		this.setResizable(false);
	}
	
	public void ChangeCard(String str)
	{
		if(str.equals("menü"))
		{ 
			cardLayout.show(MAINPanel, str);
			actualPanel = "menü";
			paused = true;
		}else if(str.equals("WW"))
		{
			cardLayout.show(MAINPanel, str);
			actualPanel = "WW";
			paused = false;
		}else if(str.equals("options"))
		{
			cardLayout.show(MAINPanel, str);
			actualPanel = "options";
			paused = true;
		}
	}
	
	public void SetImportButton(boolean b, Color fg)
	{
		importButton.setEnabled(b);
		importButton.setForeground(fg);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		LinkedList<String> validCells = new LinkedList<String>();
		validCells.add("üres");
		validCells.add("vezeték");
		validCells.add("elektronfej");
		validCells.add("elektron farok");
		
		if(validCells.contains(event.getActionCommand()))
		{
			choosenButton = event.getActionCommand();
			cbTextField.setText(choosenButton);		
			for (int row = 1; row < CellArray.length-1; row++) { //sorok
	            for ( int column = 1 ;column < CellArray[row].length-1; column++) {//oszlopok
	            		CellArray[row][column].SetChoosenButton(choosenButton);
	            }
			}
		}
		if(event.getSource() == clearButton)
		{
			int answer = JOptionPane.showConfirmDialog(this, "Biztosan letörli a képernyõ jelenlegi tartalmát?");
			if(answer == JOptionPane.YES_OPTION)
			{
				wMap.InitGrids();
			} 	
		}
		if(event.getSource() == pauseButton)
		{
			if(paused)
			{
				paused = false;
			}else
			{
				paused = true;
			}
		}
		if(event.getSource() == mButton)
		{
			ChangeCard("menü");
		}
		if(event.getSource() == importButton)
		{			
			if (JOptionPane.showConfirmDialog(null, "A jelenlegi project felül fog íródni!", "Importálás?",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				WireMap temp = MCB.GetWmap();
				wMap.copy(temp);
			}
		}
	}
	
	class MyKeyListener extends JPanel implements KeyListener{
		@Override
		public void keyTyped(KeyEvent event) {}
		@Override
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == KeyEvent.VK_ESCAPE )
			{
				if(actualPanel.equals("WW"))
				{
					ChangeCard("menü");
				}
				if(actualPanel.equals("options"))
				{
					ChangeCard("menü");
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if(actualPanel.equals("menü"))
				{
					ChangeCard("WW");
				}
			}
			if(event.getKeyCode() == KeyEvent.VK_SPACE)
			{
				if(paused)
				{
					paused = false;
				}else {
					paused = true;
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent event) {}
	}

	
	public void UpdateColor(Cell[][] grid, boolean b)
	{
		for (int row = 1; row < grid.length-1; row++) { //sorok
            for ( int column = 1 ;column < grid[row].length-1; column++) {//oszlopok
            	switch (grid[row][column].GetName())
            	{
				case "vezeték": CellArray[row][column].setBackground(new Color(239,185,73)); break;
				case "elektronfej": CellArray[row][column].setBackground(new Color(73,208,239)); break;
				case "elektron farok": CellArray[row][column].setBackground(new Color(239, 73, 73)); break;
				default: CellArray[row][column].setBackground(Color.BLACK); break;
				}
            }
        }
	}
}
