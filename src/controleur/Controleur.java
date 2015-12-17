package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * @author Fabienne_Marquis_Gabriel_Pariat
 *
 */

public class Controleur extends Application implements Initializable {

	@FXML
	private GridPane root;

	@FXML
	private Scene scene;


	/**
	 * Methode qui démarre le programme
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Méthode qui va générer la vue (l'interface graphique du programme)
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception{
		//System.out.println(getClass().getResource("../tp1_FM_interface.fxml"));

			
			root = FXMLLoader.load(getClass().getResource(
				"../tp1_FM_interface.fxml"));
			scene = new Scene(root);
	

		primaryStage.setTitle("Informations Réseaux");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Methode qui initialise le FXML/ControleurVue
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}
