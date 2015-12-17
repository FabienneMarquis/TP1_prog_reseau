package modele;


import java.util.List;
import java.util.Observable;

/**
 * @author Gabriel Pariat Fabienne Marquis on 2015-12-16.
 */
public class LocalNetworkComputer extends Observable{
    final String address;
    final String fullName;
    final static Object lock = new Object();
    static List<LocalNetworkComputer> list;
/**
 * Cette méthode permets la construction d'un nouvel object LocalNetworkComputer, avec l'adresse reçu et le nom complet reçu
 * @param address adresse IP reçu par le constructeur
 * @param fullName nom complet de l'ordinateur lié à l'adresse IP reçu par le constructeur
 */
    public LocalNetworkComputer(String address, String fullName) {
        this.address = address;
        this.fullName = fullName;
    }
/**
 * retourne l'information demander
 * @return retourne l'adresse IP de l'objet
 */
    public String getAddress() {
        return address;
    }
/**
 * retourne l'information demander
 * @return retourne le nom complet de l'objet
 */
    public String getFullName() {
        return fullName;
    }

}
