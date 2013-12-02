package fitnessapp;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Meal class for storing meal's details
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class Meal {

	private String date;
	private String meal;
	private Integer kj;
	private Integer protein;
	private Integer carbs;
	private Integer fat;
	private String time;
	/**
	 * Meal constructor
	 * @param date the date of meal eaten
	 * @param meal name of the meal
	 * @param kj amount of kilojoules in the meal
	 * @param protein amount of protein in the meal
	 * @param carbs amount of carbohydrates in the meal
	 * @param fat amount of fat in the meal
	 * @param time the time of when the meal was eaten
	 */
	public Meal(Date date, String meal, int kj, int protein, int carbs, int fat, String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		this.date = formatter.format(date);
		this.meal = meal;
		this.kj = kj;
		this.protein = protein;
		this.carbs = carbs;
		this.fat = fat;
		this.time = time;
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
	 * @return name of the meal
	 */
	public String getMeal(){
		return meal;
	}
	
	/**
	 * 
	 * @return amount of kilojoules in the meal
	 */
	public int getKj() {
		return kj;
	}
	
	/**
	 * 
	 * @return amount of protein in the meal
	 */
	public int getProtein() {
		return protein;
	}
	
	/**
	 * 
	 * @return amount of carbohydrates in the meal
	 */
	public int getCarbs() {
		return carbs;
	}
	
	/**
	 * 
	 * @return amount of fat in the meal
	 */
	public int getFat() {
		return fat;
	}
	
	/**
	 * 
	 * @return time of meal was eaten in format hh:mm
	 */
	public String getTime() { 
		return time;
	}
}
