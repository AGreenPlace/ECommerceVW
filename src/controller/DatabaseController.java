package controller;

import model.Catalogo;
import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
@ManagedBean
public class DatabaseController {
    @EJB
    private Catalogo catalog;

    public DatabaseController() {
        this.catalog = new Catalogo();
    }

    public List<Prodotto> getProductsInCatalog(){
        return this.catalog.getProducts();
    }

    public String describeCatalog(){
        return catalog.getCatalogDescription();
    }
}
