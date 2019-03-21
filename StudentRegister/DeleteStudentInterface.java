import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DeleteStudentInterface extends JFrame
{
	public static final Dimension FRAME_SIZE = new Dimension(350,325);
	public static final Dimension MIN_FRAME_SIZE = new Dimension(350,200);
	public static final Point FRAME_ORIGIN = new Point(450,250);
	public static final String CONFIRM_DELETION = "Confirm Deletion";
	
	private JCheckBox[] checkBoxes;
	
	public DeleteStudentInterface(boolean[] delete, String[][] displayInfo,
			EventListener listener)
	{
		int n = delete.length;
		assert n == displayInfo[0].length && n == displayInfo[1].length :
			new Exception("The lengths of the boolean array delete and " +
					"the String arrays displayInfo[0] and displayInfo[1] " +
					"do not match.");
		
		Container contentPane;
		SpringLayout contentLayout;
		JButton confirmBtn;
		JScrollPane scrollPane;
		JPanel confirmPanel, deletePanel;
		
		checkBoxes = new JCheckBox[n];
		JLabel[][] studentInfo = new JLabel[2][n];
		setResizable(false);
		setSize(FRAME_SIZE);
		setMinimumSize(MIN_FRAME_SIZE);
		setTitle("Delete Students");
		setLocation(FRAME_ORIGIN);
		addWindowListener((WindowListener) listener);
		
		contentPane = getContentPane();
		contentPane.setLayout(contentLayout = new SpringLayout());
		
		confirmPanel = new JPanel();
		confirmBtn = new JButton("Confirm Deletion");
		confirmBtn.addActionListener((ActionListener) listener);
		confirmBtn.setActionCommand(CONFIRM_DELETION);
		confirmPanel.add(confirmBtn);
		
		deletePanel = new JPanel(new GridLayout(n, 3));
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				studentInfo[j][i] = new JLabel(displayInfo[j][i]);
				deletePanel.add(studentInfo[j][i]);
			}
			checkBoxes[i] = new JCheckBox();
			deletePanel.add(checkBoxes[i]);
		}
		scrollPane = new JScrollPane(deletePanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contentPane.add(confirmPanel);
		contentPane.add(scrollPane);
		contentLayout.putConstraint(SpringLayout.NORTH, confirmPanel, 0,
				SpringLayout.NORTH, contentPane);
		sideConstraint(confirmPanel, contentPane, contentLayout);
		contentLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0,
				SpringLayout.SOUTH, confirmPanel);
		contentLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0,
				SpringLayout.SOUTH, contentPane);
		sideConstraint(scrollPane, contentPane, contentLayout);
		setVisible(true);
	}
	
	private void sideConstraint(Component inner, Component outer, SpringLayout layout)
	{
		layout.putConstraint
			(SpringLayout.WEST, inner, 0, SpringLayout.WEST, outer);
		layout.putConstraint
			(SpringLayout.EAST, inner, 0, SpringLayout.EAST, outer);
	}
	
	public boolean[] getDeleteOrders()
	{
		boolean[] delete = new boolean[checkBoxes.length];
		for(int i = 0; i < delete.length; i++)
			delete[i] = checkBoxes[i].isSelected();
		return delete;
	}
}