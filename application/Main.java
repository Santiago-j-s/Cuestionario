package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Crear Cuestionario");
			String stringUrl = "form-preguntas.fxml";
			
			Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
			
			URL fxmlUrl = this.getClass().getResource(stringUrl);
			
			AnchorPane formPreguntas = FXMLLoader.<AnchorPane>load(fxmlUrl);
			
			Scene scene = new Scene(formPreguntas);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
