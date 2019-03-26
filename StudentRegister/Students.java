import java.util.*;
import java.io.Serializable;

public class Students implements Serializable {

    public static final int NAME = 0;
    public static final int ID = 1;
    private static final int LESS = -1;
    private static final int EQUAL = 0;
    private static final int MORE  = 1;
    private static int compareAttribute;
    private String	studentName;
    private String	studentID;
    private Date	registrationDate;
    private Courses	course1;
    private String 	phoneNumber;
	private String 	currentCity;
	private String 	currentState;
	private String 	currentZipCode;
        
    static {
        compareAttribute = NAME;
    }
	
    public Students(){
        this ("", "", new Date(), "", "", "", "");
    }

    public Students(String studentName, String studentID) {
		this (studentName, studentID, new Date(), "", "", "", "");
	}

    public Students(String studentName, String studentID, 
			Date registrationDate, String phoneNumber, 
			String currentCity, String currentState, 
			String currentZipCode) 
    {
        this.studentName 		= studentName;
		this.studentID 			= studentID;
		this.registrationDate 	= registrationDate;
		this.course1 			= new Courses();
		this.phoneNumber 		= phoneNumber;
		this.currentCity 		= currentCity;
		this.currentState 		= currentState;
		this.currentZipCode 	= currentZipCode;             
    }

	public Courses getCourse() 
    {
		return course1;
	}

	public void setCourse(Courses course) 
    {
		this.course1 = course;
	}

	public String getCurrentCity() 
    {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) 
    {
		if (currentCity.equals(""))
			this.currentCity = "N/A";
		else
			this.currentCity = currentCity;
	}

	public String getCurrentState() 
    {
		return currentState;
	}

	public void setCurrentState(String currentState) 
    {
		if (currentState.equals(""))
			this.currentState = "N/A";
		else
			this.currentState = currentState;
	}

	public String getCurrentZipCode() {
		return currentZipCode;
	}

	public void setCurrentZipCode(String currentZipCode) 
    {
		if (currentZipCode.equals(""))
			this.currentZipCode = "N/A";
		else
			this.currentZipCode = currentZipCode;
	}

	public String getPhoneNumber() 
    {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) 
    {
		if (phoneNumber.equals(""))
			this.phoneNumber = "N/A";
		else
			this.phoneNumber = phoneNumber;
	}

	public Date getRegistrationDate()
    {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) 
    {
		this.registrationDate = registrationDate;
	}

	public String getStudentID() 
    {
		return studentID;
	}

	public void setStudentID(String id) 
    {
		if (id.equals(""))
			this.studentID = "N/A";
		else
			this.studentID = id;
	}

	public String getStudentName() 
    {
		return studentName;
	}

	public void setStudentName(String name) 
    {
		if (name.equals(""))
			this.studentName = "N/A";
		else
			this.studentName = name;
	}

    public static void setCompareAttribute(int attribute) 
    {
        compareAttribute = attribute;
    }

    public int compareTo(Students student, int attribute )
    {
        if ( attribute == ID ) {
            return (this.studentID.compareTo(student.getStudentID()));
        } else {
            return (this.studentName.compareTo(student.getStudentName()));
        }
    }

    public int compareTo(Students student) 
    {
        return compareTo(student, compareAttribute);
    }

    public void enterData() 
    {
    	String temp1 = "";
    	
    	System.out.print("Please enter the student ID: ");
    	temp1 = readString();
    	while (!hasWords(temp1, 1)) {
    		System.out.println("Input is invalid.");
    		System.out.print("Please enter the ID without spaces: ");
    		temp1 = readString();
    	}
    	setStudentID(temp1);
    	
    	System.out.print("Please enter the student's first and last names: ");
    	temp1 = readString();
    	while (!hasWords(temp1, 2))	{
    		System.out.println("Name input is invalid.");
    		System.out.print("Please enter ONLY the student's first and" + " last names: ");
    		temp1 = readString();
    	}
        setStudentName(temp1);
        
        System.out.print("Please enter date of registration (DD/MM/YYYY): ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Date input is invalid.  Please follow the " + "format indicated.");
        	System.out.print("Please enter date of registration (MM/DD/YYYY)" + ": ");
        	temp1 = readString();
        }
        setRegistrationDate(new Date(temp1));
         
        System.out.print("Please enter the student's phone number: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Number is invalid.  Please do not include " + "spaces in the input.");
        	System.out.print("Please enter the student's phone number: ");
        	temp1 = readString();
        }
        setPhoneNumber(temp1);
        
        System.out.print("Please enter the student's current city: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please type the city's " + "name as one word.");
        	temp1 = readString();
        }
        setCurrentCity(temp1);
        
        System.out.print("Please enter the student's current state: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please ensure that there" + " are no spaces.");
        	temp1 = readString();
        }
        setCurrentState(temp1);

        System.out.print("Please enter the student's zip code: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please ensure that " + "there are no spaces.");
        	temp1 = readString();
        }
        setCurrentZipCode(temp1);
    }

    private boolean hasWords(String string, int words) 
    {
    	if (words != 0 && string.equals(""))
    		return false;
    	return (string.split(" ").length == words);
    }

    public boolean exists() 
    {
    	return (this.getStudentID() != "");
    }

    private String readString() 
    {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        return userInput;
    }
}
