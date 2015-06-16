package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

/**
 * Created by Luca on 10/06/2015.
 */
@Entity
@DiscriminatorValue("A")
@Table(name = "Amministratori")
public class Amministratore extends Utente {

    public Amministratore(String email, String password) {
        super(email, password);
    }


    public Amministratore() {
    }
}
