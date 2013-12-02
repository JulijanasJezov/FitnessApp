package fitnessapp;
/**
 * Weight class for storing user's mass on different dates
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class Weight {
	private Integer weight;
	private String date;
	
	/**
	 * Weight constructor
	 * @param weight user's mass on particular date
	 * @param date the date of user's mass was changed
	 */
	public Weight(int weight, String date) {
		this.date = date;
		this.weight = weight;
	}

	/**
	 * 
	 * @return date of when weight was updated in format dd/mm/yyyy
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 
	 * @return user's mass on particular date
	 */
	public int getWeight() {
		return weight;
	}
}
