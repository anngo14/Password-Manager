package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import dao.PasswordsDAO;
import dao.PasswordsDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entropy;
import model.Password;
import model.User;

public class userController implements Initializable{

	@FXML
	Label username;
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
	@FXML
	TableColumn nColumn;
	@FXML
	TableColumn lColumn;
	@FXML
	TableColumn pColumn;
	@FXML
	TableColumn eColumn;
	

	PasswordsDAO dao = new PasswordsDAOImpl();
	User sessionUser = new User();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Password password = (Password) table.getSelectionModel().getSelectedItem();
					handleClick(2, password);
				} else if (event.isPrimaryButtonDown() && event.getClickCount() == 3) {
					Password password = (Password) table.getSelectionModel().getSelectedItem();
					handleClick(3, password);
				}
			}
		});
	}
	public void handleClick(int i, Password password)
	{
		switch(i)
		{
			case 2:
				StringSelection stringSelection = new StringSelection(password.getRandomPassword());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Password Manager");
				alert.setHeaderText("Clipboard");
				alert.setContentText("Password has been copied to the clipboard");
				Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("Resources/clipboard-outline-filled.png"));
				alert.showAndWait();
				break;
			case 3:
				break;
		}
	}
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
		String passwordToSave = randomPassword.getText();
		Entropy check = new Entropy(passwordToSave);
		String labelToSave = label.getText();
		User user = new User();
		Password save = new Password();
		
		user.setUser(username.getText());
		if(labelToSave.matches("\\s*"))
		{
			labelToSave = "UNKNOWN";
		}
		save.setLabel(labelToSave);
		save.setEntropy(check.getBits());
		save.setRandomPassword(passwordToSave);
		dao.savePassword(save, user);
	}
	public void checkPassword(ActionEvent event)
	{
		String passwordToCheck = checkPassword.getText();
		Entropy check = new Entropy(passwordToCheck);
		checkPassword.setText("" + check.getBits());
	}
	public void refresh(ActionEvent event)
	{
		ArrayList<Password> passList = dao.getAllPasswords(sessionUser);
		ObservableList<Password> savedPasswords = FXCollections.observableArrayList(passList);
		table.setItems(savedPasswords);
	}
	public void initData(User u)
	{
		sessionUser.setUser(u.getUser());
		username.setText(sessionUser.getUser());
		nColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		lColumn.setCellValueFactory(new PropertyValueFactory<>("Label"));
		pColumn.setCellValueFactory(new PropertyValueFactory<>("RandomPassword"));
		eColumn.setCellValueFactory(new PropertyValueFactory<>("Entropy"));
		
		ArrayList<Password> passList = dao.getAllPasswords(sessionUser);
		ObservableList<Password> savedPasswords = FXCollections.observableArrayList(passList);
		table.setItems(savedPasswords);
	}
	
}
