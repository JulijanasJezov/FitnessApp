package Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import fitnessapp.Exercise;
import fitnessapp.Meal;
import fitnessapp.MealDayTarget;
import fitnessapp.Profile;
import fitnessapp.User;
import fitnessapp.Weight;

public class ModelTest {
	String string = "19/08/1992";
	Date date;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void initialize() throws ParseException {
		date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
	}
	
	@Test
	public void testUserClass() {
		User user = new User("uname", "fname", "lname", 80, 180, 1000, date, 30);
		String var = user.getFName();
		assertEquals(var, "fname");
		
		int ivar = user.getDuration();
		assertEquals(ivar, 30);
	
		var = user.getLName();
		assertEquals(var, "lname");
		
		var = user.getUName();
		assertEquals(var, "uname");
		
		ivar = user.getWeight();
		assertEquals(ivar, 80);
		
		ivar = user.getHeight();
		assertEquals(ivar, 180);
		
		ivar = user.getKj();
		assertEquals(ivar, 1000);
		
		ivar = (int) user.getBmi(80);
		assertEquals(ivar, 24);
		
		user.updateWeight(85);
		ivar = user.getWeight();
		assertEquals(ivar, 85);
		
		user.updateHeight(182);
		ivar = user.getHeight();
		assertEquals(ivar, 182);
		
		user.updateExTarget(35);
		ivar = user.getDuration();
		assertEquals(ivar, 35);
		
		user.updateMealTarget(1200);
		ivar = user.getKj();
		assertEquals(ivar, 1200);
	}
	
	@Test
	public void testExerciseClass() {
		Exercise ex = new Exercise(new Date(05/12/2013), "running", 100, 7, 155, 30, "Yes");
		
		String var = ex.getExercise();
		assertEquals(var, "running");
		
		int ivar = ex.getDuration();
		assertEquals(ivar, 30);
		
		ivar = ex.getHr();
		assertEquals(ivar, 155);
		
		ivar = ex.getKj();
		assertEquals(ivar, 100);
		
		ivar = ex.getSpeed();
		assertEquals(ivar, 7);
		
		var = ex.getAchieved();
		assertEquals(var, "Yes");
	}
	
	@Test
	public void testMealClass() {
		Meal ml = new Meal(new Date(05/12/2013), "chicken", 60, 30, 0, 10, "14:00");
		
		String var = ml.getMeal();
		assertEquals(var, "chicken");
		
		var = ml.getTime();
		assertEquals(var, "14:00");
		
		int ivar = ml.getCarbs();
		assertEquals(ivar, 0);
		
		ivar = ml.getFat();
		assertEquals(ivar, 10);
		
		ivar = ml.getKj();
		assertEquals(ivar, 60);
		
		ivar = ml.getProtein();
		assertEquals(ivar, 30);
	}
	
	@Test
	public void testWeightClass() {
		Weight w = new Weight(80, "20/11/2013");
		
		String var = w.getDate();
		assertEquals(var, "20/11/2013");
		
		int ivar = w.getWeight();
		assertEquals(ivar, 80);
	}
	
	@Test
	public void testMealDayTargetClass() {
		MealDayTarget mld = new MealDayTarget("10/12/2013", "1250", "1200", "no");
		
		String var = mld.getAchieved();
		assertEquals(var, "no");
		
		var = mld.getDate();
		assertEquals(var, "10/12/2013");
		
		var = mld.getTarget();
		assertEquals(var, "1200");
		
		var = mld.getTotal();
		assertEquals(var, "1250");
	}
	
	@Test
	public void testProfileClass() throws ParseException, IOException {
		Profile prf = new Profile();
		
		prf.addUser("junit", "psw", "john", "smith", "80", "180", "1500", "20/05/1993", "30");
		prf.checkUser("junit", "psw");
		prf.loadUsersData("junit");
		prf.addExercise("running", "100", "7", "155", "30", "05/12/2013");
		prf.addMeal("05/12/2013", "chicken", "60", "30", "0", "10", "14:00");
		prf.addImage("file:/C:/Users/user/Pictures/pic.JPG");
		prf.addLink("www.google.com");
		
		String var = prf.getUser().getUName();
		assertEquals(var, "junit");
		
		var = prf.getExercise().get(0).getExercise();
		assertEquals(var, "running");
		
		var = prf.getMeal().get(0).getMeal();
		assertEquals(var, "chicken");
		
		var = prf.getImages().get(0);
		assertEquals(var, "file:/C:/Users/user/Pictures/pic.JPG");
		
		var = prf.getRecentActivity();
		assertEquals(var, sdf.format(new Date()));
		
		int ivar = prf.getWeights().get(0).getWeight();
		assertEquals(ivar, 80);
		
		prf.updateWeight("90");
		
		ivar = prf.getUser().getWeight();
		assertEquals(ivar, 90);
		
		prf.updateHeight("181");
		
		ivar = prf.getUser().getHeight();
		assertEquals(ivar, 181);
		
		var = prf.getRecentList().get(0);
		assertEquals(var, "Height Updated");
		
		prf.updateKjTarget("1600");
		
		ivar = prf.getUser().getKj();
		assertEquals(ivar, 1600);
		
		prf.updateExTarget("40");
		
		ivar = prf.getUser().getDuration();
		assertEquals(ivar, 40);
		
	}
}
