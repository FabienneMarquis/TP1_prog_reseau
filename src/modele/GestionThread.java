package modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

/**
 * 
 * @author Fabienne Marquis Gabriel Pariat
 *
 */
public class GestionThread extends Observable {

	/**
	 * Ce threat permet d'aller chercher les chercher les informations des
	 * ordinateurs qui sont connnecter sur le réseaux. Les adresses doivent être
	 * atteingnable pour faire la liste. Chaque nouvel object ainsi créer est
	 * ensuite envoyer à l'observateur pour être ajouter à la liste observable
	 * JavaFX et être vu
	 */
	public void startGetAllLocalNetworkComputersThread() {

		try {
			byte[] ip = InetAddress.getLocalHost().getAddress();

			for (int i = -128; i <= 127; i++) {
				byte[] ipToReach = { ip[0], ip[1], ip[2], (byte) i };
				new Thread(() -> {
					try {
						if (InetAddress.getByAddress(ipToReach).isReachable(
								3000)) {
							synchronized (this) {
								System.out.println(InetAddress.getByAddress(
										ipToReach).getHostAddress());
								setChanged();
								notifyObservers(new LocalNetworkComputer(
										InetAddress.getByAddress(ipToReach)
												.getHostAddress(), InetAddress
												.getByAddress(ipToReach)
												.getCanonicalHostName()));
							}

						}
					} catch (IOException er) {
						er.printStackTrace();
					}
				}).start();
			}
		} catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
	}
}
