package dad.adivinapp;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application{

	Random rand = new Random();
	private int num = rand.nextInt(100)+1, intentos = 0;
	private TextField numText;
	private Label textoLabel;
	private Button comprobarButton;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		numText = new TextField();	
		
		textoLabel = new Label();
		textoLabel.setText("Introduce un número de 1 al 100");
		
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(this::onComprobarAction);
		
		VBox rootPanel = new VBox();
		rootPanel.setSpacing(15);
		rootPanel.setAlignment(Pos.CENTER);
		rootPanel.setFillWidth(false);
		rootPanel.getChildren().addAll(textoLabel, numText, comprobarButton);
		
		Scene scene = new Scene(rootPanel, 320, 200);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void onComprobarAction(ActionEvent e) {
		try {
			if(Integer.parseInt(numText.getText()) > 0 && Integer.parseInt(numText.getText()) <= 100) {
				intentos++;
				if(num == Integer.parseInt(numText.getText())) {
					num = rand.nextInt(100)+1; 
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has ganado!");
					alert.setContentText("Sólo has necesitado " + intentos + " intentos.\n\nVuelve a jugar y hazlo mejor.");
					alert.showAndWait();
					
					intentos = 0;
					
				} else if(num > Integer.parseInt(numText.getText())) {
					
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has fallado!");
					alert.setContentText("El número a adivinar es mayor que "+numText.getText()+"\n\nVuelve a intentarlo.");
					alert.showAndWait();
				} else if(num < Integer.parseInt(numText.getText())) {
					
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has fallado!");
					alert.setContentText("El número a adivinar es menor que "+numText.getText()+"\n\nVuelve a intentarlo.");
					alert.showAndWait();
				}			
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Error");
				alert.setContentText("El número introducido no es válido.");
				alert.showAndWait();
			}
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido.");
			alert.showAndWait();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
