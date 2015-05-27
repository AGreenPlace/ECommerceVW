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
    @EJB

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
}
