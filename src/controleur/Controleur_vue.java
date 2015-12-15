package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import modele.Gestion_Thread;
import modele.Gestion_traitement;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class Controleur_vue implements Initializable {
	@FXML
	private Scene scene;

	@FXML
	private GridPane root;

	@FXML
	private ListView<String> Liste_machines_reseau_locale;

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

	private Gestion_Thread g_thread;

	private Gestion_traitement traitement;
	
	private ObservableList<String> infoAdresse = FXCollections
			.synchronizedObservableList(FXCollections.observableArrayList());

	
	@FXML
	void quitApplication(ActionEvent event) {
		Platform.exit();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Liste_machines_reseau_locale.setItems(infoAdresse);
		//créer liste de address réseau (côté droit)
		

	}

}
