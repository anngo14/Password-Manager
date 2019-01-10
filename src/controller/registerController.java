package controller;

import java.io.IOException;

import dao.UserDAO;
import dao.UserDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;

public class registerController {
	
	@FXML
	AnchorPane content;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	
	public registerController()
	{
		super();
	}
	public void submitForm(ActionEvent event) throws IOException
	{
		String userInput = username.getText();
		String passInput = password.getText();
		User newUser = new User();
		UserDAO dao = new UserDAOImpl();
		newUser.setUser(userInput);
		newUser.setPassword(passInput);
		
		dao.registerUser(newUser);
		if(dao.checkUser(newUser))
		{
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
			content.getChildren().setAll(pane);
		}

	}
	public void backToLogin(ActionEvent event) throws IOException
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
		content.getChildren().setAll(pane);
	}
}
