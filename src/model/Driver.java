package model;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try
		{
			Parent root=FXMLLoader.load(getClass().getResource("/view/Login Page.fxml"));
			Scene scene = new Scene(root, 1000, 800);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Password Manager");
			primaryStage.show();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
