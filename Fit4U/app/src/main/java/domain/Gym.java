package domain;

import java.util.HashSet;
import java.util.Iterator;

public class Gym {

    private static HashSet<Client> clients;
    private static HashSet<Trainer> trainers;
    private String name;
    private String address;

    public Gym (String name, String address){
        this.name=name;
        this.address=address;
    }

    public static boolean usernameExists(String username) {
        for (Iterator<Client> it = clients.iterator(); it.hasNext(); ) {
            Client c = it.next();
            if (c.getUsername().equals(username)) return true;
        }

        for (Iterator<Trainer> it = trainers.iterator(); it.hasNext(); ) {
            Trainer t = it.next();
            if (t.getUsername().equals(username)) return true;
        }
        return false;
    }
}
