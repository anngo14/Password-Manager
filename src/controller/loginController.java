package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {

	@FXML
	private AnchorPane content;
	@FXML 
	private TextField username;
	@FXML 
	private TextField password;
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
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Main User View.fxml"));
		content.getChildren().setAll(pane);
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
