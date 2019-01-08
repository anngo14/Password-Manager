package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Entropy;
import model.Password;

public class userController {

	@FXML
	TextField username;
	@FXML
	AnchorPane content;
	@FXML
	TableView table;
	@FXML
	TextField label;
	@FXML
	TextField randomPassword;
	@FXML
	TextField checkPassword;
	
	public userController()
	{
		super();
	}
	public void backToLogin(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
		content.getChildren().setAll(pane);
	}
	public void generatePassword(ActionEvent event)
	{
		Password generator = new Password();
		String random = generator.getRandomPassword();
		
		randomPassword.setText(random);
	}
	public void savePassword(ActionEvent event)
	{
		
	}
	public void checkPassword(ActionEvent event)
	{
		String passwordToCheck = checkPassword.getText();
		Entropy check = new Entropy(passwordToCheck);
		checkPassword.setText("" + check.getBits());
	}
	
}
