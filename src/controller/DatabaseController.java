package controller;

import model.Catalogo;

/**
 * Created by Andrea on 07/05/15.
 */
public class DatabaseController {
    private Catalogo catalog;

    public DatabaseController() {
        this.catalog = new Catalogo();
    }

    public String describeCatalog(){
        return catalog.getCatalogDescription();
    }
}
