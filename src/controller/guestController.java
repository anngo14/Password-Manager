package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Entropy;
import model.Password;

public class guestController {

	@FXML
	AnchorPane content;
	@FXML
	TextField randomPassword;
	@FXML
	TextField passwordCheck;
	
	public guestController()
	{
		super();
	}
	public void backToLogin(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
		content.getChildren().setAll(pane);
	}
	public void register(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Register Page.fxml"));
		content.getChildren().setAll(pane);
	}
	public void generatePassword(ActionEvent event)
	{
		Password generator = new Password();
		String random = generator.getRandomPassword();
		
		randomPassword.setText(random);
	}
	public void submitCheck(ActionEvent event)
	{
		String passwordToCheck = passwordCheck.getText();
		Entropy check = new Entropy(passwordToCheck);
		passwordCheck.setText("" + check.getBits());
	}

	
}
