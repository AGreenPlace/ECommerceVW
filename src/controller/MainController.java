package controller;

import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
@ManagedBean
public class MainController {
    @EJB
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
