package modele;

import java.util.Observable;

/**
 * Created by 0940135 on 2015-12-16.
 */
public class LocalNetworkComputer extends Observable{
    final String address;
    final String fullName;

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
