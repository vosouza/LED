import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class AddStudentInterface extends JFrame
{
	public static final Dimension FRAME_SIZE = new Dimension(850,350);
	public static final Dimension MIN_FRAME_SIZE = new Dimension(350,300);
	public static final Point FRAME_ORIGIN = new Point(350,250);
	
	public static final String RETURN_DATA = "Return Student Data";
	public static final String FIND_COURSE = "Find Course";
	public static final String CLEAR_DATA = "Clear Student Data";

	private SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
	private JTextField[] courseFields;
	private JTextField[] studentFields;
	private Courses course;
	private Students students;

	public AddStudentInterface(EventListener listener)
	{
		Container contentPane;
		JButton doneBtn, clearBtn, cancelBtn;
		JPanel infoPanel, studentPanel, coursePanel;
		SpringLayout contentLayout, infoLayout;
		setResizable(false);
		setSize(FRAME_SIZE);
		setMinimumSize(MIN_FRAME_SIZE);
		setTitle("Add Student");
		setLocation(FRAME_ORIGIN);
		addWindowListener((WindowListener) listener);
		
		contentPane = getContentPane();
		contentPane.setLayout(contentLayout = new SpringLayout());

		infoPanel = new JPanel(infoLayout = new SpringLayout());
		infoPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));
		studentPanel = new JPanel();
		studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.PAGE_AXIS));
		coursePanel = new JPanel();
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.PAGE_AXIS));
		coursePanel.setBorder(BorderFactory.createTitledBorder("Associated Course Data"));
		
		String[] studentLabelArray = {
			"Student Name", "Student ID", "Date of Registration",
			"Phone Number", "City", "State", "Zip Code"
		};
		int stLabels = studentLabelArray.length;
		studentFields = new JTextField[stLabels];

		for(int i = 0; i < stLabels; i++)
			studentFields[i] = new JTextField();
		
		String[] courseLabelArray = {
			"Course Number", "Course Name", "Course Credit Hours",
			"Scheduled Time","Scheduled Days", "Scheduled Room", 
			"Instructor Name"
		};
		int coLabels = courseLabelArray.length;
		courseFields = new JTextField[coLabels];

		for(int i = 0; i < coLabels; i++) {
			courseFields[i] = new JTextField();
			courseFields[i].setEditable(false);
		}
		
		doneBtn = new JButton("Done");
		doneBtn.addActionListener((ActionListener) listener);
		doneBtn.setActionCommand(RETURN_DATA);
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener((ActionListener) listener);
		clearBtn.setActionCommand(CLEAR_DATA);
		cancelBtn = new JButton("Find Course");
		cancelBtn.addActionListener((ActionListener) listener);
		cancelBtn.setActionCommand(FIND_COURSE);
		JPanel[] studInfoLines = new JPanel[stLabels + 1];
		studentPanel.add(Box.createVerticalGlue());

		for(int i = 0; i < stLabels; i++) {
			studInfoLines[i] = new JPanel(new GridLayout(1,2,0,4));
			studInfoLines[i].add(new JLabel(studentLabelArray[i]));
			studInfoLines[i].add(studentFields[i]);
			studentPanel.add(studInfoLines[i]);
			studentPanel.add(Box.createVerticalGlue());
		}

		studInfoLines[stLabels] = new JPanel();
		studInfoLines[stLabels].setLayout(new BoxLayout(studInfoLines[stLabels], BoxLayout.LINE_AXIS));
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(doneBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(clearBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(cancelBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studentPanel.add(studInfoLines[stLabels]);
		
		JPanel[] courInfoLines = new JPanel[coLabels];
		coursePanel.add(Box.createVerticalGlue());

		for(int i = 0; i < coLabels; i++) {
			courInfoLines[i] = new JPanel(new GridLayout(1,2,0,4));
			courInfoLines[i].add(new JLabel(courseLabelArray[i]));
			courInfoLines[i].add(courseFields[i]);
			coursePanel.add(courInfoLines[i]);
			coursePanel.add(Box.createVerticalGlue());
		}

		infoPanel.add(studentPanel);
		infoPanel.add(coursePanel);

		infoLayout.putConstraint(SpringLayout.NORTH, studentPanel, 0, SpringLayout.NORTH, infoPanel);
		infoLayout.putConstraint(SpringLayout.NORTH, infoPanel, 0, SpringLayout.NORTH, coursePanel);
		infoLayout.putConstraint(SpringLayout.WEST, coursePanel, 0, SpringLayout.HORIZONTAL_CENTER, infoPanel);
		infoLayout.putConstraint(SpringLayout.EAST, studentPanel, 0, SpringLayout.HORIZONTAL_CENTER, infoPanel);
		infoLayout.putConstraint(SpringLayout.EAST, coursePanel, 0, SpringLayout.EAST, infoPanel);
		infoLayout.putConstraint(SpringLayout.WEST, studentPanel, 0, SpringLayout.WEST, infoPanel);
		infoLayout.putConstraint(SpringLayout.SOUTH, infoPanel, 0, SpringLayout.SOUTH, coursePanel);
		infoLayout.putConstraint(SpringLayout.SOUTH, studentPanel, 0, SpringLayout.SOUTH, infoPanel);

		contentPane.add(infoPanel);
		contentLayout.putConstraint(SpringLayout.NORTH, infoPanel, 0, SpringLayout.NORTH, contentPane);
		contentLayout.putConstraint(SpringLayout.SOUTH, contentPane, 0, SpringLayout.SOUTH, infoPanel);

		sideConstraint(infoPanel, contentPane, contentLayout);
		setVisible(true);
	}
	
	private void sideConstraint(Component inner, Component outer, SpringLayout layout)
	{
		layout.putConstraint(SpringLayout.WEST, inner, 0, SpringLayout.WEST, outer);
		layout.putConstraint(SpringLayout.EAST, inner, 0, SpringLayout.EAST, outer);
	}
	
	public void clear()
	{
		for (int i = 0; i < studentFields.length; i++)
			studentFields[i].setText("");
		for (int i = 0; i < courseFields.length; i++)
			courseFields[i].setText("");
		course = null;
	}
	
	public boolean inputIsValid()
	{
		boolean infoIsValid = true;
		String errorMessage = "";
		
		for (int i = 0; i < studentFields.length; i++) {
			String temp = studentFields[i].getText();
			if (temp.length() > 0) {
				while (temp.charAt(0) == ' ') {
					temp = temp.substring(1);
				}
				while (temp.endsWith(" ")) {
					temp = temp.substring(0, temp.length() - 2);
				}
				studentFields[i].setText(temp);
			}
		}
		
		int i = 0;
		do {
			if (studentFields[i].getText().length() == 0){
				infoIsValid = false;
				errorMessage = errorMessage.concat("Some of the fields are blank.  All fields are" + " required.\n");
			}
			i++;
		} while (i < studentFields.length && infoIsValid);
	
		if (!studentFields[1].getText().matches("[R]{1}[A]{1}[0-9]{8}")){
			infoIsValid = false;
			errorMessage = errorMessage.concat("Student RA must consist of 'RA' and 8 digits, in the format:" + " RA12345678\n");
		}
		try {
			Date date = sdf.parse(studentFields[2].getText());
			if (date.before(sdf.parse("1/1/1946")))
				errorMessage = errorMessage.concat("Year of registration must be later than 1946\n");
		} catch (ParseException e) {
			infoIsValid = false;
			errorMessage = errorMessage.concat("Registration date must be in the format DD/MM/YYYY\n");
		}
		if (!(studentFields[3].getText().matches("[0-9]{2}-[9]{1}[0-9]{4}-[0-9]{4}") || studentFields[3].getText().matches("[0-9]{2}-[0-9]{4}-[0-9]{4}"))){
			infoIsValid = false;
			errorMessage = errorMessage.concat("Phone number must be in the format: 11-91234-7890 or 11-1234-5678\n");
		}
		if (!studentFields[6].getText().matches("[0-9]{5}-[0-9]{3}")){
			infoIsValid = false;
			errorMessage = errorMessage.concat("Zip Code must be this formats: 12345-678\n");
		}
		if (course == null){
			infoIsValid = false;
			errorMessage = errorMessage.concat("A course must be associated with the student\n");
		}
		if (!infoIsValid){
			JOptionPane.showMessageDialog(null, "I'm sorry, but I was unable to save. Please correct the following errors:\n\n" + errorMessage);
			return false;
		} else
			return true;
	}

	public void setCourse(Courses c)
	{
		this.course = c;
		courseFields[0].setText(c.getCourseNumber());
		courseFields[1].setText(c.getCourseName());
		courseFields[2].setText("" + c.getCreditHours());
		courseFields[3].setText(c.getScheduledTime());
		courseFields[4].setText(c.getScheduledDays());
		courseFields[5].setText(c.getScheduledRoom());
		courseFields[6].setText(c.getInstructorName());
	}
	
	public Students retrieve()
	{
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date registrationDate;

		try { 
			registrationDate = sdf.parse(studentFields[2].getText());
		} catch (ParseException e) {
			registrationDate = new Date();
		}

		Students temp = new Students(
			studentFields[0].getText(), studentFields[1].getText(), 
			registrationDate, studentFields[3].getText(), 
			studentFields[4].getText(), studentFields[5].getText(), 
			studentFields[6].getText()
		);
		
        temp.setCourse(course);
        return temp;
	}       
}
