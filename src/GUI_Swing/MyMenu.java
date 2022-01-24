package GUI_Swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Project.SaveProject;
import Project.WireMap;


@SuppressWarnings("serial")
public class MyMenu extends JPanel implements ActionListener{
	
	private WireFrame wFrame;
	private WireMap wMap;
	private JPanel menuButtonPanel;
	private JButton gameButton;
	private JButton optionsButton;
	private JButton saveButton;
	private JButton exitButton;
	private MyComboBox myComboBox;
	
	private static ImageIcon imageIcon;
	private static Image image; 
	static {
		imageIcon = new ImageIcon("Images\\bg.png");
		image = imageIcon.getImage();	
	}
	
	public MyMenu(WireFrame f, MyComboBox mcb) 
	{
		wFrame = f;
		myComboBox = mcb;
		
		gameButton = new JButton("J�T�K");
		gameButton.setActionCommand("J�T�K");
		gameButton.addActionListener(lambda ->
		{
			wFrame.ChangeCard("WW");
		});
		gameButton.setBackground(new Color(73,208,239));
		
		optionsButton = new JButton("BE�LL�T�SOK");
		optionsButton.setActionCommand("BE�LL�T�SOK");
		optionsButton.addActionListener(lambda ->
		{
			wFrame.ChangeCard("options");
		});
		optionsButton.setBackground(new Color(73,208,239));
		
		saveButton = new JButton("MENT�S");
		saveButton.setActionCommand("MENT�S");
		saveButton.addActionListener(lambda ->
		{
			new SaveProject(wMap, myComboBox);
			mcb.ReadBack();
		});
		saveButton.setBackground(new Color(73,208,239));
		
		exitButton = new JButton("KIL�P�S");
		exitButton.setActionCommand("KIL�P�S");
		exitButton.addActionListener(this);
		exitButton.setBackground(new Color(73,208,239));
		
		
		menuButtonPanel = new JPanel();
		menuButtonPanel.add(gameButton);
		menuButtonPanel.add(optionsButton);
		menuButtonPanel.add(saveButton);
		menuButtonPanel.add(exitButton);
		
		menuButtonPanel.setOpaque(false);
		menuButtonPanel.setLayout(new GridLayout(menuButtonPanel.getComponentCount(), 1, 0, 10));
		
		this.setLayout(new GridBagLayout());
		this.add(menuButtonPanel);
	}
	
	public void SetWmap(WireMap wm) { wMap = wm; } 
	
	@Override
	public void actionPerformed(ActionEvent event) {	
		if(event.getActionCommand().equals(exitButton.getText()))
		{ 
			String[] options = { "IGEN", "IGEN �S MENTEK IS", "M�GSEM" };
			int answer = JOptionPane.showOptionDialog(null, "Biztosan kil�p a programb�l?","asd",
					JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			if(answer == JOptionPane.YES_OPTION)
			{
				wFrame.dispose();
			}else if(answer == JOptionPane.NO_OPTION)
			{
				new SaveProject(wMap, myComboBox);
			}
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{	
	    super.paintComponent(g);
	    if (image != null)
	    {
	    	Graphics2D g2d = (Graphics2D) g;
		    int x = (this.getWidth() - image.getWidth(null)) / 2;
		    int y = (this.getHeight() - image.getHeight(null)) / 2;
		    g2d.drawImage(image, x, y, null);
	    }
	}
	
}
