package controller;

import model.Catalogo;
import model.Prodotto;
import model.Utente;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrea on 07/05/15.
 */
@ManagedBean
public class DatabaseController {
    private Catalogo catalog;
    private Map<String,Utente> utenti;

    public DatabaseController() {
        this.catalog = new Catalogo();
        this.utenti = new HashMap<>();
        Utente u1 = new Utente("luca@luca.com","luca");
        Utente u2 = new Utente("andrea@andrea.com","andrea");
        this.utenti.put(u1.getEmail(),u1);
        this.utenti.put(u2.getEmail(),u2);
    }

    public List<Prodotto> getProductsInCatalog(){
        return this.catalog.getProducts();
    }

    public String describeCatalog(){
        return catalog.getCatalogDescription();
    }

    public Prodotto getProductFromCatalog(String id){
        return this.catalog.getProduct(id);
    }

    public Utente checkUser(String email){
        return utenti.get(email);
    }

    public Boolean createUser(String nome,String cognome,String email,String password,String username,String nation,String city,String cap,String location) throws Exception{
        if(nome.isEmpty())
            throw new Exception("Name is Empty");
        if(cognome.isEmpty())
            throw new Exception("Cognome is Empty");
        if(email.isEmpty())
            throw new Exception("Email is Empty");
        if(password.isEmpty())
            throw new Exception("Password is Empty");
        if(username.isEmpty())
            throw new Exception("Username is Empty");
        if(nation.isEmpty())
            throw new Exception("Nation is Empty");
        if(city.isEmpty())
            throw new Exception("City is Empty");
        if(cap.isEmpty())
            throw new Exception("Cap is Empty");
        if(location.isEmpty())
            throw new Exception("Location is Empty");
        Utente nuovoUtente = new Utente(email, password);
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setUsername(username);
        nuovoUtente.setNation(nation);
        nuovoUtente.setCity(city);
        nuovoUtente.setCap(cap);
        nuovoUtente.setLocation(location);
        this.utenti.put(nuovoUtente.getEmail(),nuovoUtente);
        return true;
    }

}
