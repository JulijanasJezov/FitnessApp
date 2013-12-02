package fitnessapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.MainController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;

/**
 * Profile class is used to communicate with controllers and storing/loading
 * data into/from files
 * @author jxj02u
 * @version 1.0.0
 * 
 */
public class Profile {
	MainController mc;
	private User currentUser;
	private ObservableList<Exercise> exercise = FXCollections
			.observableArrayList();
	private ObservableList<Meal> meal = FXCollections.observableArrayList();
	private ObservableList<Weight> weightOvertime = FXCollections
			.observableArrayList();
	private ObservableList<MealDayTarget> mealDayTarget = FXCollections
			.observableArrayList();
	private ObservableList<String> recentList = FXCollections
			.observableArrayList();
	private ObservableList<Hyperlink> link = FXCollections
			.observableArrayList();
	private String recentActivity;
	List<String> lines = new ArrayList<String>();
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	String sDate = sdf.format(date);
	Scanner scan;
	List<String> imagePaths = new ArrayList<String>();

	public void addUser(String uName, String psw, String fName, String lName,
			String weight, String height, String kj, String dob, String duration)
			throws FileNotFoundException, UnsupportedEncodingException,
			ParseException {

		new File("users/" + uName).mkdir();
		PrintWriter toFile = new PrintWriter("users/" + uName
				+ "/UserDetails.csv", "UTF-8");
		toFile.println(uName);
		toFile.println(psw);
		toFile.println(fName);
		toFile.println(lName);
		toFile.println(weight);
		toFile.println(height);
		toFile.println(kj);
		toFile.println(duration);
		toFile.println(dob);
		toFile.close();

		toFile = new PrintWriter("users/" + uName + "/Weight.csv", "UTF-8");
		toFile.println(weight);
		toFile.println(sDate);
		toFile.close();
	}
	
	/**
	 * 
	 * @return "True/False" depending if password matched the user
	 */
	public boolean checkUser(String uName, String psw)
			throws FileNotFoundException, ParseException {
		File folder = new File("users/");
		File[] listOfFiles = folder.listFiles();
		Boolean userExists = false;

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				if (listOfFiles[i].getName().equals(uName)) {
					userExists = true;
				}
			}
		}
		if (userExists) {
			scan = new Scanner(new File("users/" + uName + "/UserDetails.csv"));
			lines.clear();
			while (scan.hasNextLine()) {
				lines.add(scan.nextLine());
			}
			if (lines.get(1).equals(psw)) {
				return true;
			} else {
				lines.clear();
				return false;
			}
		} else {
			return false;
		}
	}

	//Loads all the date stored in files
	public void loadUsersData(String uName) throws ParseException,
			FileNotFoundException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// load user
		scan = new Scanner(new File("users/" + uName + "/UserDetails.csv"));
		while (scan.hasNextLine()) {
			lines.add(scan.nextLine());
		}
		String fName = lines.get(2);
		String lName = lines.get(3);
		String sWeight = lines.get(4);
		int weight = Integer.parseInt(sWeight);
		String sHeight = lines.get(5);
		int height = Integer.parseInt(sHeight);
		String sKj = lines.get(6);
		int kj = Integer.parseInt(sKj);
		String sDob = lines.get(8);
		Date dob = formatter.parse(sDob);
		int dur = Integer.parseInt(lines.get(7));
		currentUser = new User(uName, fName, lName, weight, height, kj, dob,
				dur);

		// load exercises
		File file = new File("users/" + uName + "/Exercises.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName + "/Exercises.csv"));
			while (scan.hasNextLine()) {
				String sDate = scan.nextLine();
				Date date = formatter.parse(sDate);
				String ex = scan.nextLine();
				String seKj = scan.nextLine();
				String sSpeed = scan.nextLine();
				String sHr = scan.nextLine();
				String sDur = scan.nextLine();
				String achieved = scan.nextLine();
				int eKj = Integer.parseInt(seKj);
				int speed = Integer.parseInt(sSpeed);
				int hr = Integer.parseInt(sHr);
				int duration = Integer.parseInt(sDur);
				exercise.add(new Exercise(date, ex, eKj, speed, hr, duration,
						achieved));
			}
		}

		// load meals
		file = new File("users/" + uName + "/Meals.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName + "/Meals.csv"));
			while (scan.hasNextLine()) {
				String sDate = scan.nextLine();
				Date date = formatter.parse(sDate);
				String mealName = scan.nextLine();
				String smKj = scan.nextLine();
				String sProt = scan.nextLine();
				String sCarb = scan.nextLine();
				String sFat = scan.nextLine();
				String sTime = scan.nextLine();
				int mKj = Integer.parseInt(smKj);
				int prot = Integer.parseInt(sProt);
				int carbs = Integer.parseInt(sCarb);
				int fat = Integer.parseInt(sFat);
				meal.add(new Meal(date, mealName, mKj, prot, carbs, fat, sTime));
			}
		}

		// load recent activity
		file = new File("users/" + uName + "/Recentactivity.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName
					+ "/Recentactivity.csv"));
			recentActivity = scan.nextLine();
		}

		// load recent list
		file = new File("users/" + uName + "/Recentlist.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName + "/Recentlist.csv"));
			while (scan.hasNextLine()) {
				String recent = scan.nextLine();
				if (recentList.size() < 10) {
					recentList.add(0, recent);
				} else {
					recentList.add(0, recent);
					recentList.remove(10);
				}

			}
		}

		// load images
		file = new File("users/" + uName + "/Images.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName + "/Images.csv"));
			while (scan.hasNextLine()) {
				String image = scan.nextLine();
				imagePaths.add(image);

			}
		}

		// load links
		file = new File("users/" + uName + "/Links.csv");
		if (file.exists()) {
			scan = new Scanner(new File("users/" + uName + "/Links.csv"));
			while (scan.hasNextLine()) {
				String url = scan.nextLine();
				link.add(new Hyperlink(url));
			}
		}

		loadWeights();
		loadListMealDayTarget();
		sortLists();
	}
	
	/**
	 * 
	 * @return new calculated BMI from updated weight
	 */
	public String updateWeight(String weight) throws IOException,
			ParseException {
		int iWeight = Integer.parseInt(weight);
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Weight.csv", true)));
		toFile.println(weight);
		toFile.println(sDate);
		toFile.close();

		currentUser.updateWeight(iWeight);
		weightOvertime.clear();
		loadWeights();

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/UserDetails.csv", "UTF-8");
		toFile.println(currentUser.getUName());
		toFile.println(lines.get(1));
		toFile.println(lines.get(2));
		toFile.println(lines.get(3));
		toFile.println(weight);
		toFile.println(lines.get(5));
		toFile.println(lines.get(6));
		toFile.println(lines.get(7));
		toFile.println(lines.get(8));
		toFile.close();

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity("Weight Updated");
		return Float.toString(currentUser.getBmi(iWeight));

	}

	/**
	 * 
	 * @return new calculated BMI from updated height
	 */
	public String updateHeight(String height) throws IOException {
		int iHeight = Integer.parseInt(height);
		currentUser.updateHeight(iHeight);
		PrintWriter toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/UserDetails.csv", "UTF-8");
		toFile.println(currentUser.getUName());
		toFile.println(lines.get(1));
		toFile.println(lines.get(2));
		toFile.println(lines.get(3));
		toFile.println(lines.get(4));
		toFile.println(height);
		toFile.println(lines.get(6));
		toFile.println(lines.get(7));
		toFile.println(lines.get(8));
		toFile.close();

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity("Height Updated");
		return Float.toString(currentUser.getBmi(currentUser.getWeight()));
	}

	public void updateExTarget(String exTarget) throws IOException {
		int iExTarget = Integer.parseInt(exTarget);
		currentUser.updateExTarget(iExTarget);
		PrintWriter toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/UserDetails.csv", "UTF-8");
		toFile.println(currentUser.getUName());
		toFile.println(lines.get(1));
		toFile.println(lines.get(2));
		toFile.println(lines.get(3));
		toFile.println(lines.get(4));
		toFile.println(lines.get(5));
		toFile.println(lines.get(6));
		toFile.println(exTarget);
		toFile.println(lines.get(8));
		toFile.close();

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity("Exercise Target Updated");
	}

	public void updateKjTarget(String mealTarget) throws IOException {
		int iMealTarget = Integer.parseInt(mealTarget);
		currentUser.updateMealTarget(iMealTarget);
		PrintWriter toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/UserDetails.csv", "UTF-8");
		toFile.println(currentUser.getUName());
		toFile.println(lines.get(1));
		toFile.println(lines.get(2));
		toFile.println(lines.get(3));
		toFile.println(lines.get(4));
		toFile.println(lines.get(5));
		toFile.println(mealTarget);
		toFile.println(lines.get(7));
		toFile.println(lines.get(8));
		toFile.close();

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity("Kilojoules Target Updated");
	}

	public void addExercise(String ex, String kj, String hr, String speed,
			String dur, String date) throws IOException, ParseException {

		String achieved;
		int idur = Integer.parseInt(dur);
		if (idur >= currentUser.getDuration()) {
			achieved = "Yes";
		} else {
			achieved = "No";
		}
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Exercises.csv", true)));
		toFile.println(date);
		toFile.println(ex);
		toFile.println(kj);
		toFile.println(speed);
		toFile.println(hr);
		toFile.println(dur);
		toFile.println(achieved);
		toFile.close();
		int ikj = Integer.parseInt(kj);
		int ispd = Integer.parseInt(speed);
		int ihr = Integer.parseInt(hr);

		Date ddate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		exercise.add(new Exercise(ddate, ex, ikj, ispd, ihr, idur, achieved));

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity(ex + " Exercise Added");
		sortLists();
	}

	public void addMeal(String mealdate, String mealname, String kj,
			String protein, String carbs, String fat, String time)
			throws IOException, ParseException {
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Meals.csv", true)));
		toFile.println(mealdate);
		toFile.println(mealname);
		toFile.println(kj);
		toFile.println(protein);
		toFile.println(carbs);
		toFile.println(fat);
		toFile.println(time);
		toFile.close();
		int ikj = Integer.parseInt(kj);
		int iprot = Integer.parseInt(protein);
		int icarbs = Integer.parseInt(carbs);
		int ifat = Integer.parseInt(fat);
		Date ddate = new SimpleDateFormat("dd/MM/yyyy").parse(mealdate);
		meal.add(new Meal(ddate, mealname, ikj, iprot, icarbs, ifat, time));

		toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Recentactivity.csv", "UTF-8");
		toFile.println(sDate);
		toFile.close();
		recentActivity = sDate;
		setRecentActivity(mealname + " Meal Added");
		sortLists();
		loadListMealDayTarget();
	}

	public void loadWeights() throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File("users/" + currentUser.getUName()
				+ "/Weight.csv"));
		while (scan.hasNextLine()) {
			String sw = scan.nextLine();
			String wdate = scan.nextLine();
			int iw = Integer.parseInt(sw);
			weightOvertime.add(new Weight(iw, wdate));
			sortLists();
		}
	}

	//Creates temporary lists of meals and exercises 
	//when user chooses specific date on calendar
	public void dateChosen(String date) {
		ObservableList<Exercise> tempEx = FXCollections.observableArrayList();
		for (Exercise ex : exercise) {
			if (ex.getDate().equals(date)) {
				tempEx.add(ex);
			}
		}
		mc.displayExerciseOnDate(tempEx);

		ObservableList<Meal> tempMeal = FXCollections.observableArrayList();
		for (Meal ml : meal) {
			if (ml.getDate().equals(date)) {
				tempMeal.add(ml);
			}
		}
		mc.displayMealOnDate(tempMeal);
	}

	public void loadListMealDayTarget() {
		mealDayTarget.clear();
		String achieved;
		int total = 0;
		boolean add;
		for (Meal ml : meal) {
			add = true;
			for (Meal ml2 : meal) {
				if (ml.getDate().equals(ml2.getDate())) {
					total += ml2.getKj();
				}
			}
			if (currentUser.getKj() >= total) {
				achieved = "Yes";
			} else {
				achieved = "No";
			}
			if (mealDayTarget.isEmpty()) {
				mealDayTarget.add(new MealDayTarget(ml.getDate(), Integer
						.toString(total),
						Integer.toString(currentUser.getKj()), achieved));
			}
			for (MealDayTarget mdt : mealDayTarget) {
				if (ml.getDate().equals(mdt.getDate())) {
					add = false;
				}
			}
			if (add) {
				mealDayTarget.add(new MealDayTarget(ml.getDate(), Integer
						.toString(total),
						Integer.toString(currentUser.getKj()), achieved));
			}
			total = 0;
		}
	}

	public void addLink(String url) throws IOException {
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Links.csv", true)));
		toFile.println(url);
		toFile.close();
		link.add(new Hyperlink(url));
		setRecentActivity(url + " Link Deleted");
	}

	public void deleteLink(String url) throws IOException {
		int counter = 0;
		scan = new Scanner(new File("users/" + currentUser.getUName()
				+ "/Links.csv"));
		List<String> fileLines = new ArrayList<String>();
		while (scan.hasNextLine()) {
			fileLines.add(scan.nextLine());
			if (fileLines.get(counter).contains(url)) {
				link.remove(counter);
				fileLines.remove(counter);
			}
			counter++;
		}
		PrintWriter toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Links.csv", "UTF-8");
		for (String s : fileLines) {
			toFile.println(s);
		}
		toFile.close();
		setRecentActivity(url + " Link Deleted");
	}

	public void addImage(String path) throws IOException {
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Images.csv", true)));
		toFile.println(path);
		toFile.close();
		imagePaths.add(path);
		setRecentActivity("Image Added");
	}

	public void deleteImage(int imgIndex) throws IOException {
		imagePaths.remove(imgIndex);
		PrintWriter toFile = new PrintWriter("users/" + currentUser.getUName()
				+ "/Images.csv", "UTF-8");
		for (String s : imagePaths) {
			toFile.println(s);
		}
		toFile.close();
		setRecentActivity("Image Deleted");
	}

	public void sortLists() {
		FXCollections.sort(exercise, new Comparator<Exercise>() {
			public int compare(Exercise d1, Exercise d2) {
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(d1
							.getDate());
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(d2
							.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date1.compareTo(date2);
			}
		});

		FXCollections.sort(meal, new Comparator<Meal>() {
			public int compare(Meal m1, Meal m2) {
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(m1
							.getDate());
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(m2
							.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date1.compareTo(date2);
			}
		});

		FXCollections.sort(weightOvertime, new Comparator<Weight>() {
			public int compare(Weight w1, Weight w2) {
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(w1
							.getDate());
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(w2
							.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return date1.compareTo(date2);
			}
		});
	}

	//Add new user's activity
	public void setRecentActivity(String s) throws IOException {
		PrintWriter toFile = new PrintWriter(new BufferedWriter(new FileWriter(
				"users/" + currentUser.getUName() + "/Recentlist.csv", true)));
		toFile.println(s);
		toFile.close();

		if (recentList.size() < 10) {
			recentList.add(0, s);
		} else {
			recentList.add(0, s);
			recentList.remove(10);
		}
	}

	/**
	 * 
	 * @return list of the file paths of images uploaded
	 */
	public List<String> getImages() {
		return imagePaths;
	}

	/**
	 * 
	 * @return recent list of activities done by user
	 */
	public ObservableList<String> getRecentList() {
		return recentList;
	}

	/**
	 * 
	 * @return user's details that is logged in
	 */
	public User getUser() {
		return currentUser;
	}

	/**
	 * 
	 * @return list of exercises added by user
	 */
	public ObservableList<Exercise> getExercise() {
		return exercise;
	}

	/**
	 * 
	 * @return list of meals added by user
	 */
	public ObservableList<Meal> getMeal() {
		return meal;
	}

	/**
	 * 
	 * @return list of Weight objects
	 */
	public ObservableList<Weight> getWeights() {
		return weightOvertime;
	}

	/**
	 * 
	 * @return list of MealDayTarget objects
	 */
	public ObservableList<MealDayTarget> getMealDayTarget() {
		return mealDayTarget;
	}

	/**
	 * 
	 * @return date of when the last time user has updates something
	 */
	public String getRecentActivity() {
		return recentActivity;
	}

	/**
	 * 
	 * @return list of links stored by user
	 */
	public ObservableList<Hyperlink> getLinks() {
		return link;
	}

	public void setController(MainController mc) {
		this.mc = mc;
	}

	//Resets all the lists if new user logs in
	public void resetData() {
		recentList.clear();
		exercise.clear();
		meal.clear();
		weightOvertime.clear();
		mealDayTarget.clear();
	}
}
