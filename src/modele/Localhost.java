package modele;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by 0940135 on 2015-12-16.
 */
public class Localhost{
    InetAddress address;
    private static Localhost localhost;
    private Localhost() {
        try {
            address = InetAddress.getLocalHost();
        }catch (UnknownHostException ex) {
            System.err.println("Error");
        }
    }
    public static Localhost getInstance(){
        return localhost == null ? new Localhost() : localhost;
    }

    public String getName(){
        return address.getHostName();
    }
    public String getFullName(){
        return address.getCanonicalHostName();
    }
    public String getAddress(){
        return address.getHostAddress();
    }
    public String getLoopbackAddress(){
        return address.getLoopbackAddress().toString();
    }

}
