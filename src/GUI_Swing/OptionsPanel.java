package GUI_Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//k�s�bb b�v�thet� tov�bbi be�ll�t�sokkal
@SuppressWarnings("serial")
public class OptionsPanel extends JPanel implements ActionListener{
	private WireFrame wFrame;

	private JLabel label;
	
	private JPanel panel;
	
	private JLabel sebessegLabel;
	private JComboBox<String> comboBox;
	private JTextField textField;
	
	private JLabel meretLabel;
	private JButton fullscreenButton;
	private JButton windowedButton;
	private boolean size;
	private boolean clicked;
	
	private JButton menuButton;
	private JButton submitButton;

	private Color color = new Color(73,208,239);//vil�gos k�k
	
	private static ImageIcon imageIcon;
	private static Image image; 
	static {
		imageIcon = new ImageIcon("Images\\bgo.png");
		image = imageIcon.getImage();	
	}
	
	public OptionsPanel(WireFrame wf) {
		wFrame = wf;
		panel = new JPanel(new GridBagLayout());
		
		label = new JLabel("SUBMIT gomb megnyom�sa ut�n ment�dnek el a be�ll�t�sok.");
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setPreferredSize(new Dimension(1000, 200));
		label.setForeground(color);
		
		//Sebess�g:
		sebessegLabel = new JLabel("Sebess�g:",  SwingConstants.CENTER);
		sebessegLabel.setForeground(color);
		String[] strings = {"Lass�", "K�zepes", "Gyors" };
		comboBox = new JComboBox<String>(strings);
		comboBox.addActionListener(this);
		comboBox.setBackground(color);
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(150, textField.getHeight()));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.addActionListener(this);
		textField.setEditable(false);
		textField.setBackground(color);
		
		
		meretLabel = new JLabel("K�perny� m�ret�nek m�dos�t�sa:",  SwingConstants.CENTER);
		meretLabel.setForeground(color);
		fullscreenButton = new JButton("FULLSCREEN");
		fullscreenButton.setBackground(color);
		fullscreenButton.addActionListener(this); 
		
		windowedButton = new JButton("WINDOWED");
		windowedButton.setBackground(color);
		windowedButton.addActionListener(this); 
		
		menuButton = new JButton("MEN�");
		menuButton.addActionListener(lambda ->
		{
			wFrame.ChangeCard("men�");
		});
		menuButton.setBackground(color);
		submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(this);
		submitButton.setBackground(color);
		
		
		panel.add(new JLabel() );
		panel.add(label);
		panel.add(new JLabel());
		
		panel.add(sebessegLabel);
		panel.add(comboBox);
		panel.add(textField);
		
		panel.add(meretLabel);
		panel.add(fullscreenButton);
		panel.add(windowedButton);

		panel.add(new JLabel());
		panel.add(submitButton);
		panel.add(menuButton);
		

		panel.setOpaque(false);
		panel.setLayout(new GridLayout(0,3));
		
		this.setLayout(new GridBagLayout());
		this.add(panel);
 	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == comboBox)
		{
			textField.setText("Sebess�g: " + comboBox.getSelectedItem());
		}
		
		if(event.getSource() == submitButton)
		{
			if(!textField.getText().equals("")) 
			{
				String[] strArray = textField.getText().split(" ");
				wFrame.setSpeed(strArray[1]);
			}
			if(size && clicked)
			{
				wFrame.ChangeSize("windowed");
				clicked = false;
			}else if(clicked){
				wFrame.ChangeSize("fullscreen");
				clicked = false;
			}
			
		}
		if(event.getSource() == fullscreenButton)
		{
			size = false;
			clicked = true;
			fullscreenButton.setBackground(new Color(239, 73, 73));
			windowedButton.setBackground(color);
		}
		if(event.getSource() == windowedButton)
		{
			size = true;
			clicked = true;
			windowedButton.setBackground(new Color(239, 73, 73));
			fullscreenButton.setBackground(color);
		}
	}
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
