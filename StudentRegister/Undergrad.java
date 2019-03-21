import java.util.*;

public class Undergrad extends Students {
    
    private String Honors;
    private double lateFee = .5;
	private double lateWeeks = 0;
	private double healthFee = 350.0;
	private double reducedFee = .5;
	
	public void setHealthFee (double healthFee){
		this.healthFee = healthFee;
	}
	
	public double getHealthFee(){
		return healthFee;
	}
		 
	public void setLateFee(double lateFee){
	 	this.lateFee = lateFee;
	 }
	 	 
	public double getLateFee(){
	 	return lateFee;	 
	 }

	public static void main(String[] args)
	{ 
		System.out.println("This is not the main method, Please run RegistrationSystem.java");
	}
}