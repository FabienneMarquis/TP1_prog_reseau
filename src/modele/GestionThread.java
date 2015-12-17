package modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * 
 * @author Fabienne_Marquis_gabriel_pariat
 *
 */
public class GestionThread extends Observable{

	/**
	 * Information à aller chercher avec le thread d'ouverture
	 * Nom de la machine locale
	 * nom complet de la machine locale
	 * IP machine Locale
	 * IP boucle machine Locale
	 * 
	 */

	/**
	 * Thread qui va chercher les machines du réseaux locale (atteingnable) 
	 * une liste d'observable object
	 * byte[] address = {107, 23, (byte) 216, (byte) 196};
	 * InetAddress sansHotname =  InetAddress.getByAddress(address);
	 */

	/**
	 * mettre des syso pour vérifier si les threads fonctionnent
	 */
	public void startGetAllLocalNetworkComputersThread(){

		try {
			byte[] ip = InetAddress.getLocalHost().getAddress();

			for(int i = -128; i <= 127;i++){
				byte[] ipToReach = {ip[0],ip[1],ip[2],(byte)i};
				new Thread(()->{
					try{
						if(InetAddress.getByAddress(ipToReach).isReachable(3000)){
							synchronized (this){
								System.out.println(InetAddress.getByAddress(ipToReach).getHostAddress());
								setChanged();
								notifyObservers(new LocalNetworkComputer(InetAddress.getByAddress(ipToReach).getHostAddress(), InetAddress.getByAddress(ipToReach).getCanonicalHostName()));
							}

						}
					}catch (IOException er){
						er.printStackTrace();
					}
				}).start();
			}
		}catch (UnknownHostException ex){
			ex.printStackTrace();
		}
	}
}
