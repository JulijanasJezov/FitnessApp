package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import fitnessapp.Exercise;
import fitnessapp.Meal;
import fitnessapp.MealDayTarget;
import fitnessapp.Profile;
import fitnessapp.User;
import view.FitnessAppMain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import jfxtras.labs.scene.control.CalendarTextField;
/**
 * MainController class is used to communicate with the view
 * and model
 * It loads all the data into the view
 * @author jxj02u
 * @version 1.0.0
 *
 */
public class MainController implements Initializable {
	@FXML
	private ComboBox<String> userscombobox;
	@FXML
	private Label unamelabel;
	@FXML
	private Label fnamelabel;
	@FXML
	private Label lnamelabel;
	@FXML
	private Label agelabel;
	@FXML
	private Label weightlabel;
	@FXML
	private Label heightlabel;
	@FXML
	private Label bmilabel;
	@FXML
	private TableColumn<Exercise, String> exercisetablefield;
	@FXML
	private TableColumn<Exercise, Integer> kjtablefield;
	@FXML
	private TableColumn<Exercise, Integer> hrtablefield;
	@FXML
	private TableColumn<Exercise, Integer> speedtablefield;
	@FXML
	private TableColumn<Exercise, Integer> durationtablefield;
	@FXML
	private TableColumn<Exercise, String> datetablefield;
	@FXML
	private TableColumn<Exercise, String> targettablefield;
	@FXML
	private TableView<Exercise> exercisetable;
	@FXML
	private TableView<Meal> mealtable;
	@FXML
	private TableColumn<Meal, String> datemealtablefield;
	@FXML
	private TableColumn<Meal, String> mealtablefield;
	@FXML
	private TableColumn<Meal, Integer> kjmealtablefield;
	@FXML
	private TableColumn<Meal, Integer> proteintablefield;
	@FXML
	private TableColumn<Meal, Integer> carbstablefield;
	@FXML
	private TableColumn<Meal, Integer> fattablefield;
	@FXML
	private TableColumn<Meal, String> timetablefield;
	@FXML
	private TableView<MealDayTarget> mealtartable;
	@FXML
	private TableColumn<MealDayTarget, String> mealtardatefield;
	@FXML
	private TableColumn<MealDayTarget, String> mealtartotalfield;
	@FXML
	private TableColumn<MealDayTarget, String> mealtarfield;
	@FXML
	private TableColumn<MealDayTarget, String> mealtarachievfield;
	@FXML
	private LineChart<String, Number> mealchart;
	@FXML
	private LineChart<String, Number> burntkjchart;
	@FXML
	private LineChart<String, Number> bmichart;
	@FXML
	private TextField utabweightfield;
	@FXML
	private TextField utabheightfield;
	@FXML
	private TextField exercisefield;
	@FXML
	private TextField exercisekjfield;
	@FXML
	private TextField exercisehrfield;
	@FXML
	private TextField exercisespdfield;
	@FXML
	private TextField exercisedurfield;
	@FXML
	private TextField exercisedatefield;
	@FXML
	private TextField mealfield;
	@FXML
	private TextField mealkjfield;
	@FXML
	private TextField mealprotfield;
	@FXML
	private TextField mealcarbsfield;
	@FXML
	private TextField mealfatfield;
	@FXML
	private TextField mealtimefield;
	@FXML
	private TextField mealdatefield;
	@FXML
	private CalendarTextField calendartextfield;
	@FXML
	private Label kjtargetlabel;
	@FXML
	private Label targetdurlabel;
	@FXML
	private TextField extargetfield;
	@FXML
	private TextField mealtargetfield;
	@FXML
	private TabPane tabpane;
	@FXML
	private ListView<String> recentlist;
	@FXML
	private Label recentactlabel;
	@FXML
	private Tab userprofiletab;
	@FXML
	private Tab exercisetab;
	@FXML
	private Tab mealtab;
	@FXML
	private Tab graphtab;
	@FXML
	private Tab phototab;
	@FXML
	private GridPane imagegridpane;
	@FXML
	private TextField imageindexfield;
	@FXML
	private TextField urltextfield;
	@FXML
	private ListView<Hyperlink> linkslist;

	ObservableList<ImageView> imageview = FXCollections.observableArrayList();
	FitnessAppMain mainApp;
	Profile profile;
	int rowCount = 0;
	int columnCount = 0;

	@FXML
	protected void insertUsersComboBox() {
		File folder = new File("users/");
		File[] listOfFiles = folder.listFiles();
		userscombobox.getItems().clear();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				userscombobox.getItems().add(listOfFiles[i].getName());
			}
		}
		userscombobox.setPromptText(listOfFiles[0].getName());
	}

	@FXML
	protected void openNewUserWindow(ActionEvent event) throws Exception {
		mainApp.addUserWindow();
	}

	@FXML
	protected void openAboutWindow(ActionEvent event) throws Exception {
		mainApp.aboutWindow();
	}
	
	@FXML
	protected void openLoginWindow(ActionEvent event) throws Exception {
		mainApp.loginWindow();
	}

	@FXML
	protected void openUserProfile(ActionEvent event) throws Exception {
		tabpane.getSelectionModel().select(userprofiletab);
	}

	@FXML
	protected void openExerciseTab(ActionEvent event) throws Exception {
		tabpane.getSelectionModel().select(exercisetab);
	}

	@FXML
	protected void openMealTab(ActionEvent event) throws Exception {
		tabpane.getSelectionModel().select(mealtab);
	}

	@FXML
	protected void openGraphTab(ActionEvent event) throws Exception {
		tabpane.getSelectionModel().select(graphtab);
	}

	@FXML
	protected void openPhotoTab(ActionEvent event) throws Exception {
		tabpane.getSelectionModel().select(phototab);
	}

	@FXML
	protected void closeApplication(ActionEvent event) throws Exception {
		System.exit(0);
	}

	public void displayCurrentUser(User curUser) throws FileNotFoundException,
			ParseException {
		unamelabel.setText(curUser.getUName());
		fnamelabel.setText(curUser.getFName());
		lnamelabel.setText(curUser.getLName());
		weightlabel.setText(Integer.toString(curUser.getWeight()));
		heightlabel.setText(Integer.toString(curUser.getHeight()));
		agelabel.setText(Integer.toString(curUser.getAge()));
		bmilabel.setText(Float.toString(curUser.getBmi(profile.getUser()
				.getWeight())));
		kjtargetlabel.setText(Integer.toString(curUser.getKj()));
		targetdurlabel.setText(Integer.toString(curUser.getDuration()));
	}

	public void displayExercise() {
		exercisetablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, String>(
						"exercise"));
		kjtablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>(
						"kj"));
		hrtablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>(
						"hr"));
		speedtablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>(
						"speed"));
		durationtablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, Integer>(
						"duration"));
		datetablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, String>(
						"date"));
		targettablefield
				.setCellValueFactory(new PropertyValueFactory<Exercise, String>(
						"achieved"));
		exercisetable.setItems(profile.getExercise());

	}

	public void displayExerciseOnDate(ObservableList<Exercise> tempEx) {
		exercisetable.setItems(tempEx);

	}

	public void displayMealOnDate(ObservableList<Meal> tempMeal) {
		mealtable.setItems(tempMeal);
	}

	public void displayMeal() {
		mealtablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, String>(
						"meal"));
		kjmealtablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, Integer>(
						"kj"));
		proteintablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, Integer>(
						"protein"));
		carbstablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, Integer>(
						"carbs"));
		fattablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, Integer>(
						"fat"));
		datemealtablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, String>(
						"date"));
		timetablefield
				.setCellValueFactory(new PropertyValueFactory<Meal, String>(
						"time"));
		mealtable.setItems(profile.getMeal());
	}

	public void displayMealTarget() {
		mealtardatefield
				.setCellValueFactory(new PropertyValueFactory<MealDayTarget, String>(
						"date"));
		mealtartotalfield
				.setCellValueFactory(new PropertyValueFactory<MealDayTarget, String>(
						"total"));
		mealtarfield
				.setCellValueFactory(new PropertyValueFactory<MealDayTarget, String>(
						"target"));
		mealtarachievfield
				.setCellValueFactory(new PropertyValueFactory<MealDayTarget, String>(
						"achieved"));
		mealtartable.setItems(profile.getMealDayTarget());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void displayMealGraph() {
		XYChart.Series series = new XYChart.Series();
		series.setName(profile.getUser().getUName());
		for (int i = 0; i < profile.getMealDayTarget().size(); i++) {
			series.getData().add(
					new XYChart.Data(profile.getMealDayTarget().get(i)
							.getDate(), Integer.parseInt(profile
							.getMealDayTarget().get(i).getTotal())));
		}
		mealchart.getData().add(series);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void displayBMIGraph() {
		XYChart.Series series = new XYChart.Series();
		series.setName(profile.getUser().getUName());
		for (int i = 0; i < profile.getWeights().size(); i++) {
			series.getData().add(
					new XYChart.Data(profile.getWeights().get(i).getDate(),
							profile.getUser().getBmi(
									profile.getWeights().get(i).getWeight())));
		}
		bmichart.getData().add(series);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void displayBurntKjGraph() {
		XYChart.Series series = new XYChart.Series();
		series.setName(profile.getUser().getUName());
		for (int i = 0; i < profile.getExercise().size(); i++) {
			series.getData().add(
					new XYChart.Data(profile.getExercise().get(i).getDate(),
							profile.getExercise().get(i).getKj()));
		}
		burntkjchart.getData().add(series);
	}

	@FXML
	protected void updateWeight(ActionEvent event) throws Exception {
		if (isNumeric(utabweightfield.getText())
				&& !utabweightfield.getText().isEmpty()) {
			weightlabel.setText(utabweightfield.getText());
			bmichart.getData().clear();
			bmilabel.setText(profile.updateWeight(utabweightfield.getText()));
			loadRecentList();
			displayBMIGraph();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void updateHeight(ActionEvent event) throws Exception {
		if (isNumeric(utabheightfield.getText())
				&& !utabheightfield.getText().isEmpty()) {
			heightlabel.setText(utabheightfield.getText());
			bmilabel.setText(profile.updateHeight(utabheightfield.getText()));
			loadRecentList();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void updateExTarget(ActionEvent event) throws Exception {
		if (isNumeric(extargetfield.getText())
				&& !extargetfield.getText().isEmpty()) {
			targetdurlabel.setText(extargetfield.getText());
			profile.updateExTarget(extargetfield.getText());
			loadRecentList();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void updateMealTarget(ActionEvent event) throws Exception {
		if (isNumeric(mealtargetfield.getText())
				&& !mealtargetfield.getText().isEmpty()) {
			kjtargetlabel.setText(mealtargetfield.getText());
			profile.updateKjTarget(mealtargetfield.getText());
			loadRecentList();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void addExercise(ActionEvent event) throws IOException,
			ParseException {
		if (!exercisefield.getText().isEmpty()
				&& !exercisekjfield.getText().isEmpty()
				&& !exercisehrfield.getText().isEmpty()
				&& !exercisespdfield.getText().isEmpty()
				&& !exercisedurfield.getText().isEmpty()
				&& !exercisedatefield.getText().isEmpty()
				&& isValidDate(exercisedatefield.getText())
				&& isNumeric(exercisekjfield.getText())
				&& isNumeric(exercisehrfield.getText())
				&& isNumeric(exercisespdfield.getText())
				&& isNumeric(exercisedurfield.getText())) {
			burntkjchart.getData().clear();
			profile.addExercise(exercisefield.getText(),
					exercisekjfield.getText(), exercisehrfield.getText(),
					exercisespdfield.getText(), exercisedurfield.getText(),
					exercisedatefield.getText());
			loadRecentList();
			displayBurntKjGraph();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void addMeal(ActionEvent event) throws IOException,
			ParseException {
		if (!mealdatefield.getText().isEmpty()
				&& !mealfield.getText().isEmpty()
				&& !mealkjfield.getText().isEmpty()
				&& !mealprotfield.getText().isEmpty()
				&& !mealcarbsfield.getText().isEmpty()
				&& !mealfatfield.getText().isEmpty()
				&& !mealtimefield.getText().isEmpty()
				&& isValidDate(mealdatefield.getText())
				&& isNumeric(mealkjfield.getText())
				&& isNumeric(mealprotfield.getText())
				&& isNumeric(mealcarbsfield.getText())
				&& isNumeric(mealfatfield.getText())
				&& isValidTime(mealtimefield.getText())) {
			mealchart.getData().clear();
			profile.addMeal(mealdatefield.getText(), mealfield.getText(),
					mealkjfield.getText(), mealprotfield.getText(),
					mealcarbsfield.getText(), mealfatfield.getText(),
					mealtimefield.getText());
			loadRecentList();
			displayMealGraph();
		} else {
			mainApp.displayError();
		}
	}

	@FXML
	protected void addLink(ActionEvent event) throws IOException {
		profile.addLink(urltextfield.getText());
	}

	public void loadLinks() {
		linkslist.setItems(profile.getLinks());
	}
	
	@FXML
	protected void deleteLink(ActionEvent event) throws IOException {
		profile.deleteLink(urltextfield.getText());
	}

	@FXML
	protected void uploadImage(ActionEvent event) throws Exception {
		FileChooser fileChooser = new FileChooser();
		imageview.add(new ImageView());
		if (imageview.size() / 3.0 > 1.1 + rowCount) {
			rowCount++;
		}
		int mod = imageview.size() % 3;
		if (mod == 1 || mod == 2) {
			columnCount = mod - 1;
		} else {
			columnCount = 3;
		}
		imagegridpane.add(imageview.get(imageview.size() - 1), columnCount,
				rowCount);

		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter(
				"JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
				"PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

		File file = fileChooser.showOpenDialog(null);
		Image image = new Image(file.toURI().toString(), 200, 200, true, true);
		imageview.get(imageview.size() - 1).setImage(image);
		profile.addImage(file.toURI().toString());
	}

	@FXML
	protected void deleteImage(ActionEvent event) throws NumberFormatException,
			IOException {
		imagegridpane.getChildren().remove(
				Integer.parseInt(imageindexfield.getText()));
		imageview.remove(Integer.parseInt(imageindexfield.getText()));
		profile.deleteImage(Integer.parseInt(imageindexfield.getText()));
	}

	@FXML
	protected void getDate(ActionEvent event) {
		Date ddate = calendartextfield.getValue().getTime();
		String sdate = new SimpleDateFormat("dd/MM/yyyy").format(ddate);
		profile.dateChosen(sdate);
	}

	@FXML
	protected void displayAll(ActionEvent event) {
		displayExercise();
		displayMeal();
	}

	public void loadImages() {
		for (int i = 0; i < profile.getImages().size(); i++) {
			imageview.add(new ImageView());
			if (imageview.size() / 3.0 > 1.1 + rowCount) {
				rowCount++;
			}
			int mod = imageview.size() % 3;
			if (mod == 1 || mod == 2) {
				columnCount = mod - 1;
			} else {
				columnCount = 3;
			}
			imagegridpane.add(imageview.get(imageview.size() - 1), columnCount,
					rowCount);
			Image image = new Image(profile.getImages().get(i), 200, 200, true,
					true);
			imageview.get(imageview.size() - 1).setImage(image);
		}
	}

	public void loadRecentList() {
		recentlist.setItems(profile.getRecentList());
	}

	public void loadRecentActivity() {
		recentactlabel
				.setText("Recent Activity " + profile.getRecentActivity());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		insertUsersComboBox();
		tabpane.setDisable(true);
	}

	public void setModel(FitnessAppMain mainApp, Profile profile) {
		this.mainApp = mainApp;
		this.profile = profile;
	}

	public void enableTab() {
		tabpane.setDisable(false);
	}

	//Resets all the data in the program 
	//when new user logs in
	public void resetData() {
		profile.resetData();
		mealchart.getData().clear();
		bmichart.getData().clear();
		burntkjchart.getData().clear();
		exercisetable.getItems().clear();
		mealtable.getItems().clear();
		mealtartable.getItems().clear();
		recentlist.getItems().clear();
	}

	//Check if string is a number
	public boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	//Check if date string is valid
	public boolean isValidDate(String dateString) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			df.parse(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	//Check if time string is valid
	public boolean isValidTime(String timeString) {
		SimpleDateFormat df = new SimpleDateFormat("hh:mm");
		try {
			df.parse(timeString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
