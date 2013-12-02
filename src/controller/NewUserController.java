package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import fitnessapp.Profile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * NewUserController class for communicating with the model and new user window
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class NewUserController {
	@FXML
	private TextField usernamefield;
	@FXML
	private PasswordField passwordfield;
	@FXML
	private TextField firstnamefield;
	@FXML
	private TextField lastnamefield;
	@FXML
	private TextField weightfield;
	@FXML
	private TextField heightfield;
	@FXML
	private TextField kilojoulesfield;
	@FXML
	private Label fail;
	@FXML
	private TextField dobfield;
	@FXML
	private TextField adduserdurfield;

	Profile profile;

	@FXML
	protected void addNewUser(ActionEvent event) throws FileNotFoundException,
			UnsupportedEncodingException, ParseException {
		File folder = new File("users/");
		File[] listOfFiles = folder.listFiles();
		Boolean write = false;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				if (listOfFiles[i].getName().equals(usernamefield.getText())) {
					write = true;
				}
			}
		}

		if (!write) {
			if (!usernamefield.getText().isEmpty()
					&& !passwordfield.getText().isEmpty()
					&& !firstnamefield.getText().isEmpty()
					&& !lastnamefield.getText().isEmpty()
					&& !weightfield.getText().isEmpty()
					&& !heightfield.getText().isEmpty()
					&& !kilojoulesfield.getText().isEmpty()
					&& !dobfield.getText().isEmpty()
					&& !adduserdurfield.getText().isEmpty()
					&& isValidDate(dobfield.getText())
					&& isNumeric(weightfield.getText())
					&& isNumeric(heightfield.getText())
					&& isNumeric(kilojoulesfield.getText())
					&& isNumeric(adduserdurfield.getText())) {
				profile.addUser(usernamefield.getText(),
						passwordfield.getText(), firstnamefield.getText(),
						lastnamefield.getText(), weightfield.getText(),
						heightfield.getText(), kilojoulesfield.getText(),
						dobfield.getText(), adduserdurfield.getText());
				Stage stage = (Stage) usernamefield.getScene().getWindow();
				stage.close();
			} else {
				fail.setVisible(true);
			}
		} else {
			fail.setVisible(true);
		}
	}

	public void setModel(Profile profile) {
		this.profile = profile;
	}
	
	public boolean isValidDate(String dateString) {
	    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	        df.parse(dateString);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	
	public boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
