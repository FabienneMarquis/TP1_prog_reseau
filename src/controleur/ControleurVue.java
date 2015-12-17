package controleur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

import javafx.scene.control.ListCell;
import javafx.util.Callback;
import modele.GestionThread;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modele.LocalNetworkComputer;
import modele.Localhost;

/**
 * 
 * @author Fabienne Marquis Gabriel Pariat
 *
 */

public class ControleurVue implements Initializable, Observer {
	@FXML
	private Scene scene;

	@FXML
	private GridPane root;

	@FXML
	private ListView<LocalNetworkComputer> Liste_machines_reseau_locale;

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

	@FXML
	private TextField networkPCFullname;

	private GestionThread gThread;

	private ObservableList<LocalNetworkComputer> infoAdresse = FXCollections
			.synchronizedObservableList(FXCollections.observableArrayList());
	private LocalNetworkComputer localNetworkComputer;
	private Object lock = new Object();

	/**
	 * Lorsque le bouton quitter est cliqué l'évènement est envoyer à cette
	 * observateur qui effectue la fermeture du programme
	 * 
	 * @param event
	 *            appuyer sur le bouton bt_quitter
	 */
	@FXML
	void quitApplication(ActionEvent event) {
		Platform.exit();
	}

	/**
	 * Lorsque le bouton obtenir nom complet est cliqué l'évènement est envoyer
	 * à cette observateur qui va afficher la valeur récupérer par la méthode
	 * 
	 * @param event
	 *            appuyer sur le bouton bt_obetnir_nom_complet
	 */
	@FXML
	void getFullNameOfSelectedItem(ActionEvent event) {
		networkPCFullname.setText(Liste_machines_reseau_locale
				.getSelectionModel().getSelectedItems().get(0).getFullName());
	}

	/**
	 * Lorsque le bouton Obtenir information est cliqué un évènement est envoyer
	 * à cet observateur qui va ensuite enclancher cette méthode
	 * 
	 * @param event
	 *            appuyer sur le bouton bt_obtenir_info
	 */
	@FXML
	void obtenirInfo(ActionEvent event) {
		String name = "https://" + info_nom_de_domaine.getText();
		try {
			URL url = new URL(name);
			InetAddress address = InetAddress.getByName(url.getHost());
			Platform.runLater(() -> textfiels_information_domaine
					.setText("ip: " + address.getHostAddress() + " port: "
							+ url.getDefaultPort()));

		} catch (MalformedURLException | UnknownHostException er) {
			Platform.runLater(() -> textfiels_information_domaine
					.setText("domaine invalid"));
			// er.printStackTrace();
		}
	}

	/**
	 * la méthode initialize qui va remplir les éléments de la scène créer dans
	 * le controleur dans la méthode start
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gThread = new GestionThread();
		gThread.startGetAllLocalNetworkComputersThread();
		gThread.addObserver(this);
		nom_complet_machine_locale.setText(Localhost.getInstance()
				.getFullName());
		nom_machine_locale.setText(Localhost.getInstance().getName());
		ip_machine_locale.setText(Localhost.getInstance().getAddress());
		ip_boucle_machine_locale.setText(Localhost.getInstance()
				.getLoopbackAddress());

		Liste_machines_reseau_locale.setItems(infoAdresse);

		Liste_machines_reseau_locale
				.setCellFactory(new Callback<ListView<LocalNetworkComputer>, ListCell<LocalNetworkComputer>>() {
					@Override
					public ListCell<LocalNetworkComputer> call(
							ListView<LocalNetworkComputer> param) {
						return new ListCell<LocalNetworkComputer>() {
							@Override
							protected void updateItem(LocalNetworkComputer t,
									boolean bln) {
								super.updateItem(t, bln);
								if (t != null) {
									Platform.runLater(() -> setText(t
											.getAddress()));
								}
							}
						};
					}
				});

	}

	/**
	 * Observateur qui regarder tout changement effectuer dans la classe
	 * GestionThread. Lorsqu'un changement est effectuer, il ajoute le nouvel
	 * objet à la liste observable
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass() == GestionThread.class) {
			infoAdresse.add((LocalNetworkComputer) arg);
		}
	}
}
