package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class registerController {
	
	@FXML
	AnchorPane content;
	@FXML
	TextField username;
	@FXML
	TextField password;
	
	public registerController()
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
	public void backToLogin(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
		content.getChildren().setAll(pane);
	}
}
