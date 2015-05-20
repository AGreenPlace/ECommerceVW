package controller;

import model.Prodotto;

import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
public class MainController {
    private DatabaseController databaseController;

    public MainController() {
        this.databaseController = new DatabaseController();
    }

    public String consultCatalog(){
        return databaseController.describeCatalog();
    }

    public List<Prodotto> getProductsInCatalog(){
        return databaseController.getProductsInCatalog();
    }
}
