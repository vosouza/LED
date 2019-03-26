import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class RegistrationSystem implements ActionListener, WindowListener
{
	private File currentFile;
	private Students[] students;
	private GUI userInterface;
	private DeleteStudentInterface studentDeleter;
	private AddStudentInterface studentAdder;
	
	public RegistrationSystem()
	{
		userInterface = new GUI(this);
	}
	
	public int getNumberOfStudents()
	{
		int validStudents = 0;
		int i = 0;
		boolean done = false;
		
		do {
			if (students[i] != null && students[i].exists())
				validStudents++;
			else
				done = true;
			i++;
		} while(i < students.length && !done);
		
		return validStudents;
	}
	
	private double getStudentID(int s)
	{
        return Double.parseDouble(students[s].getStudentID());
	}

	private void setCurrentFile(File f)
	{
		currentFile = f;
	}
	
	private boolean fileIsReadable(File f)
	{
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));
			try {
				Students test = (Students) input.readObject();
				if (!test.exists())
					return false;
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "This file may not be in the " + "format used by this program");
				return false;
			} catch (EOFException e){
				JOptionPane.showMessageDialog(null, "This file is blank.");
				return false;
			} finally {
				input.close();
			}
		} catch (IOException e) {
			if (!f.toString().equals(""))
				JOptionPane.showMessageDialog(null, "This file could not be accessed.");
			return false;
		}
		return true;
	}
	
	private void addCourseToStudent()
	{
		CourseListingSystem cls = new CourseListingSystem();
		Courses temp = cls.findCourse();
		if (temp != null)
			studentAdder.setCourse(temp);
	}

	private int countStudentsInFile()
	{
		int studentsInFile = 0;
		boolean done;
		do{
			done = true;
			String temp = JOptionPane.showInputDialog("How many student records are in this file?");
			if (temp == null || temp.equals(""))
				return 0;
			try {
				studentsInFile = Integer.parseInt(temp);
				if (studentsInFile < 1){
					String message = "A positive, non-zero integer is required.";
					JOptionPane.showMessageDialog(null, message);
					done = false;
				}
			} catch (NumberFormatException e) {
				String message = "An integer is required.";
				JOptionPane.showMessageDialog(null, message);
				done = false;
			}
		} while (!done);
		return studentsInFile;
	}

	private void readFile() throws IOException
	{
		ObjectInputStream input =
			new ObjectInputStream(new FileInputStream(currentFile));
		int n = countStudentsInFile();
		if (n > 0) {
			students = new Students[n + 20];
			
			int i = 0;
			try {
				for (i = 0; i < n; i++)
					students[i] = (Students) input.readObject();
			} catch (ClassNotFoundException e) {
				String msg;
				msg = "This file may not be in the format used by this program.";
				JOptionPane.showMessageDialog(null, msg);
			} catch (EOFException e){
				String msg = "This file only contains " + i + " student records."
								+ "  Please check to ensure that this is the file you"
								+ " intended to load.";
				JOptionPane.showMessageDialog(null, msg);
			} finally {
				for (int j = i; j < students.length; j++)
					students[j] = new Students();
				input.close();
			} 
		}
	}

	private void updateGUI()
	{
		String[][] textDump = new String[getNumberOfStudents()][4];
		DecimalFormat money = new DecimalFormat("$0.00");

		for (int i = 0; i < getNumberOfStudents(); i++) {
			textDump[i][0] = students[i].getStudentName();
			textDump[i][1] = students[i].getStudentID();
			textDump[i][2] = students[i].getCourse().getCourseName();
		} 

		userInterface.displayText(textDump);
		
		if (getNumberOfStudents() == 0){
			userInterface.changeMode(GUI.NO_STUDENT_DATA_MODE);
			setCurrentFile(null);
		} else
			userInterface.changeMode(GUI.ARRAY_UNSORTED_MODE);
	} 

	private int wantToSave()
	{
		if (userInterface.getMode() == GUI.NO_STUDENT_DATA_MODE)
			return JOptionPane.NO_OPTION;
		else {
			String msg = "This action will write over all data in the system. " + "Would you like to save before proceeding?";
			int answer = JOptionPane.showConfirmDialog(null, msg);
			if (answer == JOptionPane.YES_OPTION)
				saveFile();
			return answer;
		}
	} 

	private void newFile()
	{
		if (wantToSave() != JOptionPane.CANCEL_OPTION){
			students = new Students[30];
			for (int i = 0; i < students.length; i++)
				students[i] = new Students();
			setCurrentFile(null);
			beginAddStudent();
		}
	} 

	private void openFile()
	{
		if (wantToSave() != JOptionPane.CANCEL_OPTION){
			File f = userInterface.selectOpenFile();
			if (fileIsReadable(f)){
				setCurrentFile(f);
				
				try {
					readFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getStackTrace());
				} 

				updateGUI();
			}
		}
	} 

	private void saveFile()
	{
		if (currentFile != null && currentFile.exists()){
			try {
				ObjectOutputStream output =
					new ObjectOutputStream(new FileOutputStream(currentFile));
				int i = 0, n = getNumberOfStudents();
				try {
					for (i = 0; i < n; i++)
						output.writeObject(students[i]);
					JOptionPane.showMessageDialog(null,
							i + " student records have been saved to the file " +
							currentFile);
				} finally {
					output.close();
				} 
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace());
			} 
		} else {
			saveAs();
		} 
	}
	
	private void saveAs()
	{
		File f = userInterface.selectSaveFile();
		if (!f.toString().equals("")){
			System.out.println(!f.exists());
			if (!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
				} 
			}
			setCurrentFile(f);
			saveFile();
		}
	}
	
	private void quit()
	{
		int answer = JOptionPane.NO_OPTION;
		
		if (userInterface.getMode() != GUI.NO_STUDENT_DATA_MODE){
			String msg = "Would you like to save before quitting?";
			answer = JOptionPane.showConfirmDialog(null, msg);
			if (answer == JOptionPane.YES_OPTION)
				saveFile();
		}
		if (answer != JOptionPane.CANCEL_OPTION)
			System.exit(0);
	}
	

	private void beginAddStudent()
	{
		boolean isOpenSpace = ( students[students.length-1] == null
									|| !students[students.length-1].exists() );
		
		if (isOpenSpace){
			userInterface.setEnabled(false);
			studentAdder = new AddStudentInterface(this);
		} else {
			String message = "There is not enough space for another record.";
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	private void finishAddStudent()
	{	
		if(studentAdder.inputIsValid()){
			Students temp = studentAdder.retrieve();
			int i = 0;
			boolean done = false;
			do{
				if (students[i] == null || !students[i].exists()){
					students[i] = temp;
					done = true;
				}
				i++;
			} while (i < students.length && !done);
			
			studentAdder.dispose();
			updateGUI();
		}
	} 
	
	private void beginDeleteStudents()
	{
		int n = getNumberOfStudents();
		boolean[] delete = new boolean[n];
		String[][] displayedInfo = new String[2][n];
		for (int i = 0; i < n; i++){
			displayedInfo[0][i] = students[i].getStudentName();
			displayedInfo[1][i] = students[i].getStudentID();
		} 
		
		userInterface.setEnabled(false);
		studentDeleter = new DeleteStudentInterface(delete, displayedInfo, this);
	} 
	
	private void finishDeleteStudents()
	{
		boolean[] delete = studentDeleter.getDeleteOrders();
		boolean anyDeletions = false;
		for (int i = 0; i < delete.length; i++)
			if (delete[i])
				anyDeletions = true;
		
		if (anyDeletions){
			int confirm = JOptionPane.showConfirmDialog(null,
					"Are you sure you would like to delete these students?",
					"Confirmation Needed", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION){
				int n = delete.length;
				Students tempStudent;
				boolean tempBool;
				
				for (int i = 0; i < n; i++){
					if (delete[i]){
						students[i] = new Students();
						delete[i] = false;
						for (int j = i; j < students.length - 1; j++){
							tempStudent = students[j];
							students[j] = students[j+1];
							students[j+1] = tempStudent;
							if (j+1 < n){
								tempBool = delete[j];
								delete[j] = delete[j+1];
								delete[j+1] = tempBool;
							}
						} 
						i--;
					}
				} 
				
				studentDeleter.dispose();
				updateGUI();
			}
		}
	} 
	
	private void sortName()
	{	 
		int i;
		int j;
		Students temp;
		int n = getNumberOfStudents();

		for(j = 0; j < n; j++){
			for (i = (n / 2) - 1; i >= 0; i--)
				siftNameDown(i, n);
			for (i = n - 1; i >= 1; i--){
				temp = students[0];
				students[0] = students[i];
				students[i] = temp;
				siftNameDown(0, i-1);
			} 
			updateGUI();
	        userInterface.changeSortName();
	    }
	} 
	
	private void sortID()
	{
		int i;
		int j;
		Students temp;
		int n = getNumberOfStudents();

		for(j = 0; j < n; j++){
			for (i = (n / 2) - 1; i >= 0; i--)
				siftIDDown(i, n);
			for (i = n - 1; i >= 1; i--){
				temp = students[0];
				students[0] = students[i];
				students[i] = temp;
				siftIDDown(0, i-1);
			} 
	    	updateGUI();
		}
	}	

	private void siftIDDown(int root, int bottom) {

        boolean done = false;
	    int maxChild;
	    Students temp;
		
		while (root * 2 <= bottom && !done) {
			boolean leftChildIsBottom = (root * 2 == bottom);
			boolean leftChildIsLarger = ( getStudentID(root * 2) >
												getStudentID((root * 2) + 1) );
			if (leftChildIsBottom || leftChildIsLarger)
				maxChild = root * 2;
			else
				maxChild = (root * 2) + 1;
			
			if (getStudentID(root) < getStudentID(maxChild)){
				temp = students[root];
				students[root] = students[maxChild];
				students[maxChild] = temp;
				root = maxChild;
			} else {
				done = true;
			}
		} 
	}

	private void siftNameDown(int root, int bottom)
	{
		boolean done = false;
		int maxChild;
		Students temp;
		
		for( temp = students[ root ]; (2 * root + 1) < bottom; root = maxChild ) { 
			maxChild = 2 * root + 1; 
			if( maxChild != bottom - 1 && students[ maxChild ].compareTo( students[ maxChild + 1 ] ) < 0 ) 
				maxChild++; 
			if( temp.compareTo( students[ maxChild ] ) < 0 ) 
				students[ root ] = students[maxChild ]; 
			else 
				break; 
		} 
		students[ root ] = temp; 
 	}  

	
	public static void main(String[] args)
	{
		RegistrationSystem system = new RegistrationSystem();
	}

	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action.equals(GUI.NEW))
			newFile();
		else if (action.equals(GUI.OPEN))
			openFile();
		else if (action.equals(GUI.SAVE))
			saveFile();
		else if (action.equals(GUI.SAVE_AS))
			saveAs();
		else if (action.equals(GUI.QUIT))
			quit();
		else if (action.equals(GUI.ADD_STUDENT))
			beginAddStudent();
		else if (action.equals(GUI.DELETE_STUDENT))
			beginDeleteStudents();
		else if (action.equals(GUI.SORT_NAME))
			sortName();
		else if (action.equals(AddStudentInterface.RETURN_DATA))
			finishAddStudent();
		else if (action.equals(AddStudentInterface.FIND_COURSE))
			addCourseToStudent();
		else if (action.equals(AddStudentInterface.CLEAR_DATA))
			studentAdder.clear();
		else if (action.equals(DeleteStudentInterface.CONFIRM_DELETION))
			finishDeleteStudents();
	}
	
	public void windowClosing(WindowEvent e)
	{
		Object source = e.getSource();
		if (source.equals(userInterface))
			quit();
		else if (source.equals(studentAdder) || source.equals(studentDeleter))
			userInterface.setFocusHere();
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
}