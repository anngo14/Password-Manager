package controller;



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class generateController {

	@FXML
	Button okButton;
	@FXML
	ComboBox<Integer> passLength;
	
	public generateController()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for(int i = 32; i >= 1; i--)
		{
			lengths.add(i);
		}
		passLength.getItems().addAll(lengths);
		passLength.setValue(32);
	}
	public void handle(ActionEvent event)
	{
		try {	
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main User View.fxml"));
			Parent root = (Parent) loader.load();
			Stage stage = (Stage) okButton.getScene().getWindow();

			userController uControl = loader.getController();
			uControl.lastData(passLength.getValue());
			stage.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
