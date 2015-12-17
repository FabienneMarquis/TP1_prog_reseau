package modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by 0940135 on 2015-12-16.
 */
public class LocalNetworkComputer extends Observable{
    final String address;
    final String fullName;
    final static Object lock = new Object();
    static List<LocalNetworkComputer> list;

    public LocalNetworkComputer(String address, String fullName) {
        this.address = address;
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        return fullName;
    }

}
