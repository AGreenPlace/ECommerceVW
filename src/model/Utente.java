package model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * Created by Andrea on 27/05/15.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="utente_tipo")
@Table(name="utente")
public abstract class Utente {
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String cognome;
    @Column(nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    @Id
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?",
            message = "{invalid.email}")
    private String email;

    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Utente() {
    }


    public Boolean verifyPassword(String password) throws InvalidPasswordException{
        if(!password.equals(this.password)){
            throw new InvalidPasswordException("Password is not valid");
        }
        else{
            return true;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utente utente = (Utente) o;

        return email.equals(utente.email);

    }



    @Override
    public int hashCode() {
        return email.hashCode();
    }

}
