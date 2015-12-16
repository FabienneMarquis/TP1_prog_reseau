package controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ListCell;
import javafx.util.Callback;
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
import modele.LocalNetworkComputer;
import modele.Localhost;

/**
 * 
 * @author Fabienne_Marquis/Gabriel_Pariat
 *
 */

public class Controleur_vue implements Initializable {
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

	private Gestion_Thread g_thread;

	private Gestion_traitement traitement;
	
	private ObservableList<LocalNetworkComputer> infoAdresse = FXCollections
			.synchronizedObservableList(FXCollections.observableArrayList());

	
	@FXML
	void quitApplication(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void getFullNameOfSelectedItem(ActionEvent event){
		networkPCFullname.setText(Liste_machines_reseau_locale.getSelectionModel().getSelectedItems().get(0).getFullName());
	}

	@FXML
	void obtenirInfo(ActionEvent event){
		String name = info_nom_de_domaine.getText();
		try{
			URL url = new URL(name);
			InetAddress address = InetAddress.getByName(url.getHost());
			Platform.runLater(() -> textfiels_information_domaine.setText("ip: " + address.getHostAddress() + " port: " + url.getDefaultPort()));

		}catch (MalformedURLException | UnknownHostException er){
			Platform.runLater(()->textfiels_information_domaine.setText("domaine invalid ou manque protocole(https://)"));
			//er.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		new Thread (()->{
			nom_complet_machine_locale.setText(Localhost.getInstance().getFullName());
			nom_machine_locale.setText(Localhost.getInstance().getName());
			ip_machine_locale.setText(Localhost.getInstance().getAddress());
			ip_boucle_machine_locale.setText(Localhost.getInstance().getLoopbackAddress());
		}).start();


		new Thread(()->{
			try {
				byte[] ip = InetAddress.getLocalHost().getAddress();

				for(int i = -128; i <= 127;i++){
					byte[] ipToReach = {ip[0],ip[1],ip[2],(byte)i};
					new Thread(()->{
						try{
							if(InetAddress.getByAddress(ipToReach).isReachable(3000)){
								infoAdresse.add(new LocalNetworkComputer(InetAddress.getByAddress(ipToReach).getHostAddress(), InetAddress.getByAddress(ipToReach).getCanonicalHostName()));
							}
						}catch (IOException er){
							er.printStackTrace();
						}
					}).start();
				}
			}catch (UnknownHostException ex){
				ex.printStackTrace();
			}

		}).start();

		Liste_machines_reseau_locale.setItems(infoAdresse);

		Liste_machines_reseau_locale.setCellFactory(new Callback<ListView<LocalNetworkComputer>, ListCell<LocalNetworkComputer>>() {
			@Override
			public ListCell<LocalNetworkComputer> call(ListView<LocalNetworkComputer> param) {
				return new ListCell<LocalNetworkComputer>() {
					@Override
					protected void updateItem(LocalNetworkComputer t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							Platform.runLater(()-> setText(t.getAddress()));
						}
					}
				};
			}
		});

	}


}
