package controller;

import model.Prodotto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
@Stateless
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

    public Prodotto getProductFromCatalog(String id){
        return databaseController.getProductFromCatalog(id);
    }
}
