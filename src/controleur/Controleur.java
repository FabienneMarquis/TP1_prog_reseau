package controleur;

import modele.Gestion_Thread;
import modele.Gestion_traitement;
import vue.Observateur_vue;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Fabienne_Marquis
 *
 */

public class Controleur extends Application implements Observateur_vue {
	private Affichage_vue vue;
	private Gestion_Thread g_thread;
	private Gestion_traitement traitement;
	
	
	/**
			 * Methode qui démarre le programme
			 * 
			 * @param args
			 */
	public static void main(String[] args) {
		// launch(args);
	}

	/**
	 * Méthode qui va gérer la vue (l'interface graphique du programme)
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		vue = new Affichage_vue(primaryStage);
		vue.ajouteObservateur(this);
		primaryStage.show();
	}

	/**
	 * Methode qui réinitie la recherche ? à voir si c'est utile dans le
	 * contexte du TP1
	 */

	public void init() {
		traitement = new Gestion_traitement(); 
	}

	@Override
	public String obtenir_information_nom_complet(String Adresse) {
		String nom_machine= "";
		
		return nom_machine;
	}
	
	public String obtenir_information_nom_de_domaine(String nom_Domaine){
		String information_domaine = "";
		
		
		
		return information_domaine;
	}

}
