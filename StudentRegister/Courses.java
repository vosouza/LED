import java.io.Serializable;
import java.util.*;


public class Courses implements Serializable{
	
	private String 	courseNumber;
	private String 	courseName;
	private int 	creditHours;
	private String	scheduledTime;
	private String	scheduledDays;
	private String	scheduledRoom;
	private String	instructorName;
	 	 
	public Courses() {
		this.courseNumber 		= "";
		this.courseName 		= "";
		this.creditHours 		= 0;
		this.scheduledTime 		= "";
		this.scheduledDays 		= "";
		this.scheduledRoom 		= "";
		this.instructorName 	= "";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String name) {
		this.courseName = name;
	}
	
	public String getCourseNumber() {
		return courseNumber;
	}
	
	public void setCourseNumber(String number) {
		this.courseNumber = number;
	}

	public int getCreditHours() {
		return creditHours;
	}
	
	public void setCreditHours(int hours) {
		this.creditHours = hours;
	}

	public String getScheduledTime() {
		return scheduledTime;
	}
	
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public String getScheduledDays() {
		return scheduledDays;
	}
	
	public void setScheduledDays(String scheduledDays) {
		this.scheduledDays = scheduledDays;
	}

	public String getScheduledRoom() {
		return scheduledRoom;
	}
	
	public void setScheduledRoom(String scheduledRoom) {
		this.scheduledRoom = scheduledRoom;
	}

	public String getInstructorName() {
		return instructorName;
	}
	
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	
	public void display(){
		System.out.println("Displaying Course Information: \n");
		System.out.println("     Course Number: " + getCourseNumber());
		System.out.println("     Course Name: " + getCourseName());
		System.out.println("     Credit Hours: " + getCreditHours());
		System.out.println("     Scheduled Time: " + getScheduledTime());
		System.out.println("     Scheduled Days: " + getScheduledDays());
		System.out.println("     Scheduled Room: " + getScheduledRoom());
		System.out.println("     Instructor Name: " + getInstructorName());
		System.out.println();
	}

	public void enterData(){
		System.out.print("Please enter the course number: ");
		setCourseNumber(readString());
		System.out.print("Please enter the course name: ");
		setCourseName(readString());
		System.out.print("Please enter the course credit hours: ");
		setCreditHours(readInteger());
	}

	public void partialDisplay(){
		System.out.println("     " + getCourseName() + " (" + getCourseNumber() + "), taught by " + getInstructorName());
	}

	private int readInteger()  {	
		int temp = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			temp = scanner.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println(ex);
		}
		return temp;
	}

    private String readString() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        return userInput;
    }
	
}
