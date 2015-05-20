package controller;

import model.Catalogo;
import model.Prodotto;

import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
public class DatabaseController {
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
