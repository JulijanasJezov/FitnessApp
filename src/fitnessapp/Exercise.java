package fitnessapp;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Exercise class for storing exercise details
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class Exercise {

	private String date;
	private String exercise;
	private Integer kj;
	private Integer speed;
	private Integer hr;
	private Integer duration;
	private String achieved;

	/**
	 * Exercise constructor
	 * @param date the date of exercise
	 * @param exercise the name of exercise
	 * @param kj burnt amount of kilojoules
	 * @param speed at what speed exercise is done in mph
	 * @param hr the heart rate while exercising
	 * @param duration time of how long user been exercising
	 * @param achieved shows if target was achieved
	 */
	public Exercise(Date date, String exercise, int kj, int speed, int hr, int duration, String achieved) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.date = formatter.format(date);
		this.exercise = exercise;
		this.kj = kj;
		this.speed = speed;
		this.hr = hr;
		this.duration = duration;
		this.achieved = achieved;
	}
	
	/**
	 * 
	 * @return date in format dd/mm/yyyy
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 
	 * @return exercise name
	 */
	public String getExercise() {
		return exercise;
	}
	
	/**
	 * 
	 * @return kj burnt
	 */
	public int getKj() {
		return kj;
	}
	
	/**
	 * 
	 * @return speed of exercise in mph
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * 
	 * @return heart rate of user while exercising
	 */
	public int getHr() {
		return hr;
	}
	
	/**
	 * 
	 * @return exercise's duration time in minutes
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * 
	 * @return "Yes/no" depending on target
	 */
	public String getAchieved() {
		return achieved;
	}
}
