package controller;

import java.io.IOException;

import dao.UserDAO;
import dao.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.User;

public class loginController {

	@FXML
	private AnchorPane content;
	@FXML 
	private TextField username;
	@FXML 
	private PasswordField password;
	@FXML
	private Button submit;
	
	private Stage newStage;
	
	public loginController()
	{
		super();
	}
	
	public void submitForm(ActionEvent event) throws IOException
	{
		String userInput = username.getText();
		String passInput = password.getText();
		User userCheck = new User();
		userCheck.setUser(userInput);
		userCheck.setPassword(passInput);
		
		UserDAO dao = new UserDAOImpl();
		if(dao.checkUser(userCheck))
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main User View.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			userController uControl = loader.getController();
			uControl.initData(userCheck);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
					
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setTitle("Login");
			alert.setHeaderText("Failed to Login");
			alert.setContentText("Incorrect Password or Username. Try Again!");
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("Resources/error-7-xxl.png"));
			alert.showAndWait();
		}
	}
	public void registerUser(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Register Page.fxml"));
		content.getChildren().setAll(pane);
	}
	public void resetPassword(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Forgot Password Page.fxml"));
		content.getChildren().setAll(pane);
	}
	public void setGuest(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Guest Page.fxml"));
		content.getChildren().setAll(pane);
	}
}
