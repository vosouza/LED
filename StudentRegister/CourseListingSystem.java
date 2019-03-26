import javax.swing.*;

public class CourseListingSystem  {
	
	public static final int MAX_RESULTS_DISPLAYED = 10;
   
	private Courses courseArray[];
	
	public CourseListingSystem()  {
		courseArray = new Courses[12];
		for (int i = 0; i <= 11; i++) {
			courseArray[i] = new Courses();
		}
		loadData();
	}
	
	public Courses findCourse(){
		
		String inputString, results;
		int numberOfMatches = 0;
		Courses foundCourse = new Courses();

		try {
			inputString = JOptionPane.showInputDialog(null,
					"Please enter all or part of the course name, course number,"
					+ " or instructor name to select a course.", null);
		
			do {
				numberOfMatches = 0;
				results = "";
				for (int i = 0; i <= 11; i++) {
					if (matchesInput(courseArray[i], inputString)) {
						numberOfMatches++;
						foundCourse = courseArray[i];
						if (numberOfMatches < MAX_RESULTS_DISPLAYED)
								results = results.concat(
									courseArray[i].getCourseName() + ", " +
									courseArray[i].getCourseNumber() + " taught by "
									+ courseArray[i].getInstructorName() + "\n"
								);
						else if (numberOfMatches == MAX_RESULTS_DISPLAYED)
							results = results.concat("\nThe system found too many results to fully display.\n");
					}
				}

				if (numberOfMatches > 1) {
					inputString = (JOptionPane.showInputDialog(null,
							"The system found " + numberOfMatches + " results that matched \""
							+ inputString + "\".\n\n"
							+ results
							+ "\nPlease enter at least one more character from the course name,"
							+ " course number, or instructor name to narrow your results:", inputString));
				}
				else if (numberOfMatches < 1) {
					inputString = JOptionPane.showInputDialog(null,
							"The system could not find any matches for \""
							+ inputString + "\".\n\nPlease enter a new statement:", inputString);
				}
			} while (numberOfMatches != 1);

		} catch (NullPointerException e){
			return null;
		}
		return foundCourse;	
    }
    
	public boolean matchesInput(Courses courseValue, String inputString){
	    boolean matches = false;
		
		inputString = inputString.toLowerCase();
		
		if (courseValue.getCourseName().toLowerCase().contains(inputString) || 
			courseValue.getCourseNumber().toLowerCase().contains(inputString) || 
			courseValue.getInstructorName().toLowerCase().contains(inputString))
			matches = true;
		
		return matches;
	}

	public void loadData() {
		courseArray[0].setCourseName("Teoria dos Números");
		courseArray[0].setCourseNumber("028185");
		courseArray[0].setCreditHours(3);
		courseArray[0].setScheduledTime("9:05 - 10:45");
		courseArray[0].setScheduledDays("Monday");
		courseArray[0].setScheduledRoom("310");
		courseArray[0].setInstructorName("Cristiana");
		
		courseArray[1].setCourseName("Laboratório de ED.");
		courseArray[1].setCourseNumber("028188");
		courseArray[1].setCreditHours(3);
		courseArray[1].setScheduledTime("7:15 - 08:55");
		courseArray[1].setScheduledDays("Tuesday");
		courseArray[1].setScheduledRoom("110");
		courseArray[1].setInstructorName("Júlio");
		
		courseArray[2].setCourseName("Física para SE.");
		courseArray[2].setCourseNumber("028185");
		courseArray[2].setCreditHours(3);
		courseArray[2].setScheduledTime("7:15 - 8:55");
		courseArray[2].setScheduledDays("Friday");
		courseArray[2].setScheduledRoom("Lab. 100");
		courseArray[2].setInstructorName("Marcos");
		
		courseArray[3].setCourseName("POO");
		courseArray[3].setCourseNumber("028186");
		courseArray[3].setCreditHours(3);
		courseArray[3].setScheduledTime("10:55 - 12:35");
		courseArray[3].setScheduledDays("Wednesday");
		courseArray[3].setScheduledRoom("321");
		courseArray[3].setInstructorName("Ítalo");
	}
}
