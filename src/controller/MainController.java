package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.InvalidPasswordException;
import model.Prodotto;
import model.Utente;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
@Stateless
public class MainController {
    private DatabaseController databaseController = new DatabaseController();
    private Utente currentUser;

    public String consultCatalog(){
        return databaseController.describeCatalog();
    }

    public List<Prodotto> getProductsInCatalog(){
        return databaseController.getProductsInCatalog();
    }

    public Prodotto getProductFromCatalog(String id){
        return databaseController.getProductFromCatalog(id);
    }

    public Boolean login(String email, String password){
        Utente currentUser = databaseController.checkUser(email);
        if (currentUser == null){
            return false;
        }
        Boolean passIsCorrect;
        try {
            passIsCorrect = currentUser.verifyPassword(password);
        }catch (InvalidPasswordException e){
            System.out.println(e.toString());
            passIsCorrect = false;
        }
        if (passIsCorrect){
            this.currentUser = currentUser;
            return true;
        }
        else return false;
    }


    public Utente getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utente currentUser) {
        this.currentUser = currentUser;
    }
}
