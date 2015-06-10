package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrea on 07/05/15.
 */
@Stateless
public class MainController {
    private DatabaseController databaseController = new DatabaseController();
    private Cliente currentUser;
    private Amministratore currentAdministrator;
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

    public String login(String email, String password){
        Utente currentUser = databaseController.checkUser(email);
        if (currentUser == null){
            return "";
        }
        Boolean passIsCorrect;
        try {
            passIsCorrect = currentUser.verifyPassword(password);
        }catch (InvalidPasswordException e){
            System.out.println(e.toString());
            passIsCorrect = false;
        }
        if (passIsCorrect){
            if(currentUser.getClass().isInstance(Cliente.class)) {
                this.currentUser = (Cliente)currentUser;
                return (String)getSession().getAttribute("previousPagePath");
            }
            if(currentUser.getClass().isInstance(Amministratore.class)){
                this.currentAdministrator= (Amministratore)currentUser;
                return "AdministrationPage.xhtml";
            }
            if(currentUser.getClass().isInstance(Utente.class)){

            }
            return "";
        }
        else return "";
    }


    public Boolean signUp(String nome,String cognome,String email,String password,String username,String nation,String city,String cap,String location){
        Utente user = databaseController.checkUser(email);
        if(user!=null)
            return false;
        else {
            Boolean userWasCreated = false;
            try {
                userWasCreated = databaseController.createClient(nome, cognome, email, password, username, nation, city, cap, location);
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

    public Map<Long,Ordine> displayOrders(){
        return this.currentUser.getOrderHistory();
    }


    public Utente getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Cliente currentUser) {
        this.currentUser = currentUser;
    }


    public Ordine getCurrentOrderFromHistory(Long id) {
        return this.currentUser.getOrderHistory().get(id);
    }

    public HttpSession getSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        return session;
    }
}

