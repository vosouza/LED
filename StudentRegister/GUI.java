import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import java.io.*;

public class GUI extends JFrame
{
	public static final Dimension FRAME_SIZE = new Dimension(600,425);
	public static final Dimension MIN_FRAME_SIZE = new Dimension(375,300);
	public static final Point FRAME_ORIGIN = new Point(450,250);

	public static final String NEW = "New";
	public static final String OPEN = "Open";
	public static final String SAVE = "Save";
	public static final String SAVE_AS = "Save As...";
	public static final String QUIT = "Quit";
	public static final String ADD_STUDENT = "Add Student";
	public static final String DELETE_STUDENT = "Delete Student";
	public static final String COMPUTE_STATS = "Compute Stats";
	public static final String SORT_NAME = "Sort by Name";
	public static final String SORT_ID = "Sort by ID";
	public static final String SORT_AMOUNT = "Sort by Amount";
	public static final int NO_STUDENT_DATA_MODE = 0;
	public static final int ARRAY_UNSORTED_MODE = 1;
	public static final int ARRAY_SORTED_MODE = 2;

	private JTextField[] statsFields;
	private JTextArea displayArea;
	private JMenuItem[][] items;
	private int mode;

	public GUI(EventListener listener)
	{
		Container contentPane;
		JMenuBar menuBar;
		JMenu[] menus;
		JScrollPane scrollPane;
		JPanel displayPanel, statsPanel;
		
		setSize(FRAME_SIZE);
		setMinimumSize(MIN_FRAME_SIZE);
		setResizable(false);
                setTitle("CSCE Registration System");
		setLocation(FRAME_ORIGIN);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) listener);
		
		contentPane = getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
		
		String[] menuNames = {"File", "Edit", "Process"};
		String[][] itemNames = {
			{"New", "Open", "Save", "Save As...", "Quit"},
			{"Add Student", "Delete Student"},
			{"Compute Stats", "Sort by Name", "Sort by ID", "Sort by Amout"} };
		String[][] commands = {
			{NEW, OPEN, SAVE, SAVE_AS, QUIT},
			{ADD_STUDENT, DELETE_STUDENT},
			{COMPUTE_STATS, SORT_NAME, SORT_ID, SORT_AMOUNT} };
		
		assert menuNames.length == itemNames.length :
			"menuNames and itemNames do not have matching lengths.";
		assert itemNames.length == commands.length :
			"itemNames and commands do not have matching lengths.";
		for (int i = 0; i < itemNames.length; i++)
			assert itemNames[i].length == commands[i].length :
				"itemNames[" + i + "] and commands[" + i + "] do not have"
				+ " matching lengths.";
			
		menuBar = new JMenuBar();
		menus = new JMenu[menuNames.length];
		items = new JMenuItem[menus.length][];
		for (int i = 0; i < menus.length; i++)
		{
			menus[i] = new JMenu(menuNames[i]);
			items[i] = new JMenuItem[itemNames[i].length];
			for (int j = 0; j < items[i].length; j++)
			{
				items[i][j] = new JMenuItem(itemNames[i][j]);
				menus[i].add(items[i][j]);
				items[i][j].addActionListener((ActionListener) listener);
				items[i][j].setActionCommand(commands[i][j]);
				items[i][j].setVisible(true);
			}
			menus[i].setVisible(true);
			menuBar.add(menus[i]);
		}
		setJMenuBar(menuBar);
		
		displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.LINE_AXIS));
		displayArea = new JTextArea();
		displayArea.setEditable(false);
		displayArea.setFont(new Font("Courier", Font.PLAIN, 12));
		displayArea.setMinimumSize(new Dimension(400,50));
		scrollPane = new JScrollPane(displayArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		displayPanel.add(scrollPane);
		
		statsPanel = new JPanel();
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.LINE_AXIS));
		String[] statsNames =
			{"Number", "Mean", "Median", "Standard Dev."};
		statsFields = new JTextField[statsNames.length];
		for(int i = 0; i < statsNames.length; i++)
		{
			Box tempBox = new Box(BoxLayout.PAGE_AXIS);
			statsFields[i] = new JTextField();
			statsFields[i].setEditable(false);
			statsFields[i].setMaximumSize(new Dimension(300, 20));
			tempBox.add(new JLabel(statsNames[i]));
			tempBox.add(statsFields[i]);
			statsPanel.add(tempBox);
		}
		
		contentPane.add(displayPanel);
		displayPanel.setVisible(true);
		contentPane.add(statsPanel);
		statsPanel.setVisible(true);
		
		changeMode(NO_STUDENT_DATA_MODE);
		setVisible(true);
	} 

	public int getMode()
	{
		return mode;
	}

	public void changeMode(int newMode)
	{
		setFocusHere();
		this.mode = newMode;
		if (mode == NO_STUDENT_DATA_MODE)
		{
			items[0][2].setEnabled(false);
			items[0][3].setEnabled(false);
			items[1][0].setEnabled(false);
			items[1][1].setEnabled(false);
			items[2][0].setEnabled(false);
			items[2][1].setEnabled(false);
            items[2][2].setEnabled(false);
            items[2][3].setEnabled(false);
            displayArea.setText("");
		} else if (mode == ARRAY_UNSORTED_MODE) {
			items[0][2].setEnabled(true);
			items[0][3].setEnabled(true);
			items[1][0].setEnabled(true);
			items[1][1].setEnabled(true);
			items[2][0].setEnabled(true);
			items[2][1].setEnabled(true);
            items[2][2].setEnabled(true);
            items[2][3].setEnabled(true);
		} else if (mode == ARRAY_SORTED_MODE) {
			items[0][2].setEnabled(true);
			items[0][3].setEnabled(true);
			items[1][0].setEnabled(true);
			items[1][1].setEnabled(true);
			items[2][0].setEnabled(true);
			items[2][1].setEnabled(true);
            items[2][2].setEnabled(true);
            items[2][3].setEnabled(true);
		}
	}
    public void changeSortName() {
        items[2][0].setEnabled(true);
        items[2][1].setEnabled(false);
        items[2][2].setEnabled(true);
        items[2][3].setEnabled(true);
    }
        
    public void changeSortID() {
        items[2][0].setEnabled(true);
        items[2][1].setEnabled(true);
        items[2][2].setEnabled(false);
        items[2][3].setEnabled(true);
    }
               
               
    public void changeSortAmount() {
        items[2][0].setEnabled(true);
        items[2][1].setEnabled(true);
        items[2][2].setEnabled(true);
        items[2][3].setEnabled(false);
    }
        
    public void changeComputeStats() {
        items[2][0].setEnabled(false);
        items[2][1].setEnabled(true);
        items[2][2].setEnabled(true);
        items[2][3].setEnabled(true);
    }
	
	public void setFocusHere()
	{
		setEnabled(true);
		toFront();
	} 

	public File selectOpenFile()
	{
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle("Select Input File");
		chooser.showOpenDialog(null);
		File f = new File(chooser.getSelectedFile(), "");
		
		return f;
	}

	public File selectSaveFile()
	{
		JFileChooser chooser = new JFileChooser();
		
		chooser.setDialogTitle("Select Save File");
		chooser.showSaveDialog(null);
		File f = new File(chooser.getSelectedFile(), "");

		return f;
	} 

	public void clearStats()
	{
		for (int i = 0; i < 4; i++)
			statsFields[0].setText("");
	} 

	public void displayStats(int number, double mean, double median, double deviation)
	{
		statsFields[0].setText("" + number);
		statsFields[1].setText("" + mean);
		statsFields[2].setText("" + median);
		statsFields[3].setText("" + deviation);
	} 

	public void displayText(String[][] s)
	{
		displayArea.setText("");
		String temp;
		temp = String.format("%18s |%9s |%30s |%11s",
			"Name", "ID", "Asso. Course #", "Amount Due");
		displayArea.append(temp + "\n");
		temp = ("-------------------|----------|------------------" +
				  "-------------|-----------");
		displayArea.append(temp + "\n");
		for (int i = 0; i < s.length; i++)
		{
			temp = String.format("%18s |%9s |%30s |%11s",
				s[i][0], s[i][1], s[i][2], s[i][3]);
			displayArea.append(temp);
			if (i+1 != s.length)
				displayArea.append("\n");
		}
	}

	public static void main(String[] args)
	{ 
		System.out.println("This is not the main method, Please run RegistrationSystem.java");
	}
}