package modele;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Gabriel Pariat Fabienne Marquis on 2015-12-16.
 */
public class Localhost {

	InetAddress address;

	private static Localhost localhost;

	/**
	 * cette méthode permets d'aller chercher une adresse
	 */
	private Localhost() {
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException ex) {
			System.err.println("Error");
		}
	}

	/**
	 * Instanciation d'un nouvel objet
	 * 
	 * @return retourne un nouvel objet localhost, si celui actuel est déjà
	 *         utilisé
	 */
	public static Localhost getInstance() {
		return localhost == null ? new Localhost() : localhost;
	}

	/**
	 * Cette méthode retourne le nom du serveur
	 * 
	 * @return
	 */
	public String getName() {
		return address.getHostName();
	}

	/**
	 * Cette méthode retourne le nom complet de l'ordinateur lié èa l'adresse IP
	 * 
	 * @return
	 */
	public String getFullName() {
		return address.getCanonicalHostName();
	}

	/**
	 * Cette méthode retourne l'adresse internet sous la forme d'une chaîne de
	 * caractères
	 * 
	 * @return
	 */
	public String getAddress() {
		return address.getHostAddress();
	}

	/**
	 * Cette méthode retourne l'adresse de bouclage lié à l'ordinateur
	 * 
	 * @return
	 */
	public String getLoopbackAddress() {
		return address.getLoopbackAddress().toString();
	}

}
