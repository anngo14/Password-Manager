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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
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
	@FXML
	Button generateButton;
	

	PasswordsDAO dao = new PasswordsDAOImpl();
	User sessionUser = new User();
	Password generator = new Password();
	int passwordLength = 32;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					Password password = (Password) table.getSelectionModel().getSelectedItem();
					handleClick(2, password);
				} else if (event.isSecondaryButtonDown() && event.getClickCount() == 2) {
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
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/view/PasswordDetailedView.fxml"));
					Parent root = (Parent) loader.load();
					Stage stageTemp = new Stage();
					stageTemp.setTitle("Password Detailed Information");
					stageTemp.setScene(new Scene(root, 800, 600));
					stageTemp.getIcons().add(new Image("Resources/icons8-grand-master-key-64.png"));
					detailedController detail = loader.getController();
					detail.initData(password, sessionUser);
					stageTemp.show();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}
	public void handleOptions()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/generateOptionView.fxml"));
			Parent root = (Parent) loader.load();
			Stage stageTemp = new Stage();
			stageTemp.setTitle("Password Generator Options");
			stageTemp.setScene(new Scene(root, 500, 300));
			stageTemp.getIcons().add(new Image("Resources/icons8-grand-master-key-64.png"));
			stageTemp.show();
			
		}
		catch(IOException e) {
			e.printStackTrace();
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
		generator.setRandomPassword(generator.generatePassword(passwordLength));
		generator.cleansePassword();
		randomPassword.setText(generator.getRandomPassword());
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
		System.out.println(generator);
		System.out.println("" + this.passwordLength);
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
	public void initData(int length)
	{
		this.passwordLength = length;
		Password generated = new Password();
		generated.setRandomPassword(generator.generatePassword(this.passwordLength));
		generated.cleansePassword();
		generator = generated;
		
		randomPassword.setText(generator.getRandomPassword());

	}
	
}
