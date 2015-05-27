package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 27/05/15.
 */
@ManagedBean
public class SignUpViewController {
    @EJB
    MainController mainController;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String username;
    private String nation;
    private String city;
    private String cap;
    private String location;

    public String signUp(){
        Boolean signUpSucceded = mainController.signUp(nome, cognome, email, password, username,nation,city,cap,location);
        if (signUpSucceded)
            return "index.xhtml";
        else return "SignUpView.xhtml";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
