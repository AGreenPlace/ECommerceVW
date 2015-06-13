package model;

import javax.persistence.Entity;
import java.util.Map;

/**
 * Created by Luca on 10/06/2015.
 */
@Entity
public class Amministratore extends Utente {

    public Amministratore(String email, String password) {
        super(email, password);
    }

}
