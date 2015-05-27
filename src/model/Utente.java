package model;

/**
 * Created by Andrea on 27/05/15.
 */
public class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;

    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
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


    public Boolean verifyPassword(String password) throws InvalidPasswordException{
        if(!password.equals(this.password)){
            throw new InvalidPasswordException("Password is not valid");
        }
        else{
            return true;
        }

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
