package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.LoginController;
import controller.MainController;
import controller.NewUserController;
import fitnessapp.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FitnessAppMain class for opening GUI windows
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class FitnessAppMain extends Application {
	Profile profile = new Profile();
	MainController mc;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader main = new FXMLLoader();
		main.setLocation(getClass().getResource("Wireframe.fxml"));
		main.load();
		Parent root = main.getRoot();
		mc = (MainController) main.getController();
		mc.setModel(this, profile);
		profile.setController(mc);
		primaryStage.setTitle("WireFrame");
		Scene scene = new Scene(root, 1200, 1000);
		primaryStage.setScene(scene);
		scene.getStylesheets().add("view/mainapp.css");
		primaryStage.setMinWidth(1200);
		primaryStage.setMinHeight(800);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void addUserWindow() throws Exception {
		FXMLLoader main = new FXMLLoader();
		main.setLocation(getClass().getResource("Newuser.fxml"));
		main.load();
		Parent root = main.getRoot();
		NewUserController nc = (NewUserController) main.getController();
		nc.setModel(profile);
		Stage stage = new Stage();
		stage.setTitle("Add New User");
		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		scene.getStylesheets().add("view/popups.css");
		stage.show();
	}
	
	public void loginWindow() throws Exception {
		FXMLLoader main = new FXMLLoader();
		main.setLocation(getClass().getResource("Login.fxml"));
		main.load();
		Parent root = main.getRoot();
		LoginController lc = (LoginController) main.getController();
		lc.setModel(mc, profile);
		Stage stage = new Stage();
		stage.setTitle("Login");
		Scene scene = new Scene(root, 330, 150);
		stage.setScene(scene);
		scene.getStylesheets().add("view/popups.css");
		stage.show();
	}
	
	public void aboutWindow() throws Exception {
		FXMLLoader main = new FXMLLoader();
		main.setLocation(getClass().getResource("About.fxml"));
		main.load();
		Parent root = main.getRoot();
		Stage stage = new Stage();
		stage.setTitle("About");
		Scene scene = new Scene(root, 200, 120);
		stage.setScene(scene);
		scene.getStylesheets().add("view/popups.css");
		stage.show();
	}
	
	 public void displayError() {
		   JPanel panel = new JPanel();

		   JOptionPane.showMessageDialog(panel, "Invalid data entered", "Error", JOptionPane.ERROR_MESSAGE);

		  }
	
}
