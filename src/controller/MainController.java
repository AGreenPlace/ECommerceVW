package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.InvalidPasswordException;
import model.Ordine;
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
    private Prodotto currentProduct;


    public Ordine addProductToOrder(Prodotto prodotto, Integer quantity){
        return currentUser.addProductToOrder(prodotto, quantity);
    }

    public String consultCatalog(){
        return databaseController.describeCatalog();
    }

    public List<Prodotto> getProductsInCatalog(){
        return databaseController.getProductsInCatalog();
    }

    public Prodotto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Prodotto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public Prodotto getProductFromCatalog(String id){
        this.currentProduct=databaseController.getProductFromCatalog(id);
        return currentProduct;

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


    public Boolean signUp(String nome,String cognome,String email,String password,String username,String nation,String city,String cap,String location){
        Utente user = databaseController.checkUser(email);
        if(user!=null)
            return false;
        else {
            Boolean userWasCreated = false;
            try {
                userWasCreated = databaseController.createUser(nome,cognome,email,password,username,nation,city,cap,location);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return userWasCreated;
        }
    }


    public Boolean closeOrder(){
        Ordine closedOrder = this.currentUser.closeOrder();
        if (closedOrder != null)
            this.databaseController.addOrderToHandle(closedOrder);
        return closedOrder!=null;
    }


    public Utente getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utente currentUser) {
        this.currentUser = currentUser;
    }






    }

