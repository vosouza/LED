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
								+ courseArray[i].getInstructorName() + "\n");
						else if (numberOfMatches == MAX_RESULTS_DISPLAYED)
							results = results.concat("\nThe system found too many results to fully display.\n");
					}
				}

				if (numberOfMatches > 1)
				{
					inputString = (JOptionPane.showInputDialog(null,
							"The system found " + numberOfMatches + " results that matched \""
							+ inputString + "\".\n\n"
							+ results
							+ "\nPlease enter at least one more character from the course name,"
							+ " course number, or instructor name to narrow your results:", inputString));
				}// end if
				else if (numberOfMatches < 1)
				{
					inputString = JOptionPane.showInputDialog(null,
							"The system could not find any matches for \""
							+ inputString + "\".\n\nPlease enter a new statement:", inputString);
				}// end if
			}while (numberOfMatches != 1);

		} catch (NullPointerException e){
			return null;
		}
		
		return foundCourse;
		
    }
    
	public boolean matchesInput(Courses courseValue, String inputString){
        
	    	boolean matches = false;
		
			inputString = inputString.toLowerCase();
		
			if (courseValue.getCourseName().toLowerCase().contains(inputString)
					|| courseValue.getCourseNumber().toLowerCase().contains(inputString)
					|| courseValue.getInstructorName().toLowerCase().contains(inputString))
				matches = true;

			return matches;

	}

	public void loadData()  {
		courseArray[0].setCourseName("Design Studio I");
		courseArray[0].setCourseNumber("301");
		courseArray[0].setCreditHours(3);
		courseArray[0].setCollegeFee(0);
		courseArray[0].setSpecialFee(0);
		courseArray[0].setScheduledTime("2:00 - 3:15 pm");
		courseArray[0].setScheduledDays("TR");
		courseArray[0].setScheduledRoom("Kauffman 122");
		courseArray[0].setInstructorName("Jeremy R. Suing");
		
		courseArray[1].setCourseName("Computer Science I");
		courseArray[1].setCourseNumber("155");
		courseArray[1].setCreditHours(4);
		courseArray[1].setCollegeFee(5);
		courseArray[1].setSpecialFee(35);
		courseArray[1].setScheduledTime("1:30 - 2:20 pm");
		courseArray[1].setScheduledDays("MWF");
		courseArray[1].setScheduledRoom("Avery 106");
		courseArray[1].setInstructorName("Jeremy Suing");
		
		courseArray[2].setCourseName("Communication Networks");
		courseArray[2].setCourseNumber("462");
		courseArray[2].setCreditHours(3);
		courseArray[2].setCollegeFee(0);
		courseArray[2].setSpecialFee(20);
		courseArray[2].setScheduledTime("1:30 - 2:20 pm");
		courseArray[2].setScheduledDays("MWF");
		courseArray[2].setScheduledRoom("Avery 110");
		courseArray[2].setInstructorName("Lisong Xu");
		
		courseArray[3].setCourseName("Fortran Programming");
		courseArray[3].setCourseNumber("252D");
		courseArray[3].setCreditHours(1);
		courseArray[3].setCollegeFee(5);
		courseArray[3].setSpecialFee(25);
		courseArray[3].setScheduledTime("2:00 - 3:15 pm");
		courseArray[3].setScheduledDays("T");
		courseArray[3].setScheduledRoom("Nebraska Hall W183");
		courseArray[3].setInstructorName("Staff");
		
		courseArray[4].setCourseName("Special Topics");
		courseArray[4].setCourseNumber("496");
		courseArray[4].setCreditHours(3);
		courseArray[4].setCollegeFee(10);
		courseArray[4].setSpecialFee(40);
		courseArray[4].setScheduledTime("1:30 - 2:20 pm");
		courseArray[4].setScheduledDays("MWF");
		courseArray[4].setScheduledRoom("Arranged");
		courseArray[4].setInstructorName("Ying Lu");
		
		courseArray[5].setCourseName("Design Project");
		courseArray[5].setCourseNumber("487");
		courseArray[5].setCreditHours(3);
		courseArray[5].setCollegeFee(10);
		courseArray[5].setSpecialFee(10);
		courseArray[5].setScheduledTime("2:00 - 3:15 pm");
		courseArray[5].setScheduledDays("TR");
		courseArray[5].setScheduledRoom("Avery 109");
		courseArray[5].setInstructorName("Gregg Rothermel");
		
		courseArray[6].setCourseName("Artificial Intelligence");
		courseArray[6].setCourseNumber("476");
		courseArray[6].setCreditHours(3);
		courseArray[6].setCollegeFee(5);
		courseArray[6].setSpecialFee(40);
		courseArray[6].setScheduledTime("2:30 - 3:20 pm");
		courseArray[6].setScheduledDays("MWF");
		courseArray[6].setScheduledRoom("Avery 109");
		courseArray[6].setInstructorName("Berthe Choueiry");
		
		courseArray[7].setCourseName("Algorithms");
		courseArray[7].setCourseNumber("310");
		courseArray[7].setCreditHours(3);
		courseArray[7].setCollegeFee(15);
		courseArray[7].setSpecialFee(20);
		courseArray[7].setScheduledTime("11:30 - 12:20 pm");
		courseArray[7].setScheduledDays("MWF");
		courseArray[7].setScheduledRoom("Avery 110");
		courseArray[7].setInstructorName("Byrav Ramamurthy");
		
		courseArray[8].setCourseName("Numerical Analysis I");
		courseArray[8].setCourseNumber("340");
		courseArray[8].setCreditHours(3);
		courseArray[8].setCollegeFee(0);
		courseArray[8].setSpecialFee(20);
		courseArray[8].setScheduledTime("11:00 - 12:15 pm");
		courseArray[8].setScheduledDays("TR");
		courseArray[8].setScheduledRoom("Avery 119");
		courseArray[8].setInstructorName("Don Costello");
		
		courseArray[9].setCourseName("Software Engineering");
		courseArray[9].setCourseNumber("361");
		courseArray[9].setCreditHours(3);
		courseArray[9].setCollegeFee(15);
		courseArray[9].setSpecialFee(20);
		courseArray[9].setScheduledTime("9:30 - 10:45 am");
		courseArray[9].setScheduledDays("TR");
		courseArray[9].setScheduledRoom("Avery 111");
		courseArray[9].setInstructorName("Scott Henninger");
		
		courseArray[10].setCourseName("Basics of Computing");
		courseArray[10].setCourseNumber("101");
		courseArray[10].setCreditHours(3);
		courseArray[10].setCollegeFee(0);
		courseArray[10].setSpecialFee(35);
		courseArray[10].setScheduledTime("12:30 - 1:45 pm");
		courseArray[10].setScheduledDays("TR");
		courseArray[10].setScheduledRoom("Avery 115");
		courseArray[10].setInstructorName("Chuck Riedesel");
		
		courseArray[11].setCourseName("Unix Programming");
		courseArray[11].setCourseNumber("251");
		courseArray[11].setCreditHours(1);
		courseArray[11].setCollegeFee(5);
		courseArray[11].setSpecialFee(25);
		courseArray[11].setScheduledTime("4:30 - 6:20 pm");
		courseArray[11].setScheduledDays("M");
		courseArray[11].setScheduledRoom("Avery 21");
		courseArray[11].setInstructorName("Negede Yossef");
		
	}
	
}
