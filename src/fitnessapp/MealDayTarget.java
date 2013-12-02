package fitnessapp;

/**
 * MealDayTarget class for storing user's total consumed kilojoules on
 * particular day
 * @author jxj02u
 * @version 1.0.0
 * 
 */
public class MealDayTarget {

	private String date;
	private String total;
	private String target;
	private String achieved;
	
	/**
	 * MealDayTarget constructor
	 * @param date the date of particular day
	 * @param total the total amount of kilojoules consumed on that day
	 * @param target the user's target amount of kilojoules
	 * @param achieved shows if target was achieved
	 */
	public MealDayTarget(String date, String total, String target,
			String achieved) {
		this.date = date;
		this.total = total;
		this.target = target;
		this.achieved = achieved;
	}

	/**
	 * 
	 * @return date of the day meal's were eaten in format dd/mm/yyyy
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @return total amount of kilojoules consumed that day
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * 
	 * @return target amount of kilojoules 
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 
	 * @return "Yes/No" depending on the target achievement
	 */
	public String getAchieved() {
		return achieved;
	}

}
