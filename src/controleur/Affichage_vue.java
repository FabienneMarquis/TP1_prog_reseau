package controleur;

import java.io.IOException;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Fabienne_Marquis
 *
 */

public class Affichage_vue {
	@FXML
	private Scene scene;

	@FXML
	private GridPane root;

	@FXML
	private ListView<?> Liste_machines_reseau_locale;

	@FXML
	private Button bt_obetnir_nom_complet;

	@FXML
	private TextField nom_machine_locale;

	@FXML
	private TextField nom_complet_machine_locale;

	@FXML
	private TextField ip_machine_locale;

	@FXML
	private TextField ip_boucle_machine_locale;

	@FXML
	private Button bt_obtenir_info;

	@FXML
	private TextField textfiels_information_domaine;

	@FXML
	private Button bt_quitter;

	@FXML
	private TextField info_nom_de_domaine;

	public Affichage_vue(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource(
					"tp1_FM_interface.fxml"));
			scene = new Scene(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.setTitle("Chercheur de nombre premier");

		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();
		
	}

	public void ajouteObservateur(Controleur controleur) {
		// TODO Auto-generated method stub

	}

}
