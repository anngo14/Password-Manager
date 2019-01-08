package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class resetController {

	@FXML
	AnchorPane content;
	@FXML
	TextField username;
	
	public resetController()
	{
		super();
	}
	public void submitForm(ActionEvent event)
	{
		String userInput = username.getText();
		System.out.println(userInput);
	}
	public void backToLogin(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
		content.getChildren().setAll(pane);
	}
}
