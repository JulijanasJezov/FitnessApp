package fitnessapp;

import java.util.Calendar;
import java.util.Date;
/**
 * User class for storing user's details
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class User {

	private String uName;
	private String fName;
	private String lName;
	private Date DOB;
	private int weight;
	private int height;
	private int kj;
	private int duration;

	/**
	 * User constructor
	 * @param uName user's username
	 * @param fName user's first name
	 * @param lName user's last name
	 * @param weight user's mass
	 * @param height user's height
	 * @param kj user's target consumption of kilojoules per day
	 * @param DOB user's date of birth
	 * @param duration user's target duration of an exercise
	 */
	public User(String uName, String fName, String lName, int weight, int height, int kj, Date DOB, int duration) {
		this.uName = uName;
		this.fName = fName;
		this.lName = lName;
		this.weight = weight;
		this.height = height;
		this.kj = kj;
		this.DOB = DOB;
		this.duration = duration;
	}
	
	/**
	 * 
	 * @return user's username
	 */
	public String getUName() {
		return uName;
	}
	
	/**
	 * 
	 * @return user's first name
	 */
	public String getFName() {
		return fName;
	}
	
	/**
	 * 
	 * @return user's last name
	 */
	public String getLName() {
		return lName;
	}
	
	/**
	 * 
	 * @return user's date of birth
	 */
	public Date getDOB() {
		return DOB;
	}
	
	/**
	 * 
	 * @return user's current mass
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * 
	 * @return user's current height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * 
	 * @return user's current target of kilojoules consumption per day
	 */
	public int getKj() {
		return kj;
	}
	
	/**
	 * 
	 * @return user's current target of exercise duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * 
	 * @return calculated age from date of birth
	 */
	public int getAge() {
		Calendar dob = Calendar.getInstance();  
		dob.setTime(DOB);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}
	
	/**
	 * 
	 * @return calculated BMI from weight and height
	 */
	public float getBmi(int weight) {
		float bmi = (float)weight / (float)(height*height) * 10000;
		return bmi;
	}
	
	public void updateWeight(int w) {
		weight = w;
	}
	
	public void updateHeight(int h) {
		height = h;
	}
	
	public void updateExTarget(int target) {
		duration = target;
	}
	
	public void updateMealTarget(int kj) {
		this.kj = kj;
	}
}
