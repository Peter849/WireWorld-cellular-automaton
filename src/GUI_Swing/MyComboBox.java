package GUI_Swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Project.ReadBack;
import Project.WireMap;


@SuppressWarnings("serial")
public class MyComboBox extends JPanel implements ActionListener {
	
	private WireFrame wFrame;
	private JComboBox<String> MainCB;
	private JComboBox<String> SubCB;
    private Hashtable<String, String[]> subItems = new Hashtable<String, String[]>();
    
    //fájlnév(Key), illetve a hozzá tartozó WMAP(Value) objektumokat tartalmazza
    private HashMap<String, WireMap> savedProjects;
    private String[] projectsStrings = new String[1000];
    private int quantity;
    private String[] jcbStringsMain = {"Import Component", "Import Project"};
    
    ReadBack rb;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyComboBox(WireFrame wf) {
		wFrame = wf;
		
		MainCB = new JComboBox<String>( jcbStringsMain );
		MainCB.addActionListener(this);
		MainCB.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
		
		SubCB = new JComboBox<String>();
		SubCB.setPrototypeDisplayValue("XXXXXXXXXX"); // JDK1.4

	    String[] comps = {"Hármas vezeték", "Vezetõ dióda", "Nem vezetõ dióda", "OR kapu", "XOR kapu", "Flip-Flop"};
	    subItems.put(jcbStringsMain[0], comps); 
	    SubCB.setModel(new DefaultComboBoxModel( (String[])comps ));    
	    
	    savedProjects = new HashMap<String, WireMap>();
	    quantity = 0;
	    subItems.put(jcbStringsMain[1], projectsStrings);

		this.add(MainCB);
		this.add(SubCB);
		
		rb = new ReadBack(this);
	}
	
	
	public void AddProject(String str, WireMap wmin) {
			projectsStrings[quantity] = str;
			savedProjects.put(str, wmin);
			quantity++;
	}
	
	public JComboBox<String> getSubBox() { return SubCB; }
	public String[] getMainStrings() { return jcbStringsMain; }
	public boolean IsContains(String str) {
		if(savedProjects.containsKey(str))return true;
		else return false;
	}
	
	public WireMap getProjectName(String str) {
		if(savedProjects.containsKey(str)) {return savedProjects.get(str);}
		else return null;
	}
	
	public WireMap GetWmap()
	{
		if(savedProjects.containsKey(SubCB.getSelectedItem()))
		{
			return savedProjects.get(SubCB.getSelectedItem());
		}
		return null;
	}
	
	public void ReadBack()
	{
		rb.reedFiles(this);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	  public void actionPerformed(ActionEvent event)
    {
        String item = (String)MainCB.getSelectedItem();
        Object o = subItems.get( item );

        if (o == null)
        {   	
            SubCB.setModel( new DefaultComboBoxModel() );
        }
        else
        {
        	
        	SubCB.setModel( new DefaultComboBoxModel( (String[])o ) );
        }
        if(MainCB.getSelectedItem().equals("Import Component"))
        {
        	wFrame.SetImportButton(false, Color.RED);
        }
        if(MainCB.getSelectedItem().equals("Import Project"))
        {
        	wFrame.SetImportButton(true, Color.GREEN);
        }
    }

}
