package controller;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;

import dao.PasswordsDAO;
import dao.PasswordsDAOImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Entropy;
import model.Password;
import model.User;

public class detailedController {

	@FXML
	TextField label;
	@FXML
	TextField passwordText;
	@FXML
	TextField link;
	@FXML
	TextField entropy;
	@FXML
	Button saveButton;
	@FXML
	Button deleteButton;
	
	Password pass;
	User sessionUser;
	PasswordsDAO dao = new PasswordsDAOImpl();
	
	public detailedController()
	{
		
	}
	public void deletePassword()
	{
		dao.deletePassword(pass);
		Stage stage = (Stage) deleteButton.getScene().getWindow();
		stage.close();
	}
	public void copyPassword()
	{
		StringSelection stringSelection = new StringSelection(pass.getRandomPassword());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Password Manager");
		alert.setHeaderText("Clipboard");
		alert.setContentText("Password has been copied to the clipboard");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("Resources/clipboard-outline-filled.png"));
		alert.showAndWait();
	}
	public void goLink()
	{
		 try {
		        Desktop.getDesktop().browse(new URL(pass.getLink()).toURI());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}
	public void generatePassword()
	{
		Password generator = new Password();
		String random = generator.getRandomPassword();
		
		passwordText.setText(random);
	}
	public void savePassword()
	{
		String passwordToSave = passwordText.getText();
		Entropy check = new Entropy(passwordToSave);
		String labelToSave = label.getText();
		Password save = new Password();
		
		save.setId(pass.getId());
		save.setLabel(labelToSave);
		save.setEntropy(check.getBits());
		save.setRandomPassword(passwordToSave);
		save.setLink(link.getText());
		dao.updatePassword(save);
		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}
	public void initData(Password password, User u)
	{
		pass = password;
		sessionUser = u;
		label.setText(pass.getLabel());
		entropy.setText("" + pass.getEntropy());
		passwordText.setText(pass.getRandomPassword());
		link.setText(pass.getLink());
	}
}
