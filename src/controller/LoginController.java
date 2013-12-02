package controller;

import java.io.FileNotFoundException;
import java.text.ParseException;

import fitnessapp.Profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * LoginController class for communicating with the model and login window
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class LoginController {
	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField passwordfield;
	@FXML
	private Label faillabel;
	Profile profile;
	MainController mc;

	@FXML
	protected void checkUser(ActionEvent event) throws FileNotFoundException, ParseException {
		if (profile.checkUser(usernamefield.getText(), passwordfield.getText())) {
			Stage stage = (Stage) usernamefield.getScene().getWindow();
			stage.close();
			mc.resetData();
			profile.loadUsersData(usernamefield.getText());
			mc.enableTab();
			mc.displayCurrentUser(profile.getUser());
			mc.displayExercise();
			mc.displayMeal();
			mc.displayMealTarget();
			mc.displayMealGraph();
			mc.displayBurntKjGraph();
			mc.displayBMIGraph();
			mc.loadRecentList();
			mc.loadRecentActivity();
			mc.loadImages();
			mc.loadLinks();
		} else {
			faillabel.setVisible(true);
		}
	}

	public void setModel(MainController mc, Profile profile) {
		this.profile = profile;
		this.mc = mc;
	}
	
}
