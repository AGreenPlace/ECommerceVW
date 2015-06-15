package controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
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
    private Utente currentUser;
    private Prodotto currentProduct;
    @PersistenceContext(unitName = "wissel")
    public EntityManager em;


    public Ordine addProductToOrder(Prodotto prodotto, Integer quantity) {
        return ((Cliente) currentUser).addProductToOrder(prodotto, quantity);
    }

    public String consultCatalog() {
        return databaseController.describeCatalog();
    }

    public List<Prodotto> getProductsInCatalog() {
        /*openEntityManager();
        return databaseController.getProductsInCatalog(this.em);*/
        CriteriaQuery<Prodotto> cq = em.getCriteriaBuilder().createQuery(Prodotto.class);
        cq.select(cq.from(Prodotto.class));
        List<Prodotto> products = em.createQuery(cq).getResultList();
        return products;
    }

    public Prodotto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Prodotto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public Prodotto getProductFromCatalog(String id) {
        this.currentProduct = databaseController.getProductFromCatalog(id);
        return currentProduct;

    }

    public String login(String email, String password) {
        Utente currentUser = databaseController.checkUser(email);
        if (currentUser == null) {
            return "";
        }
        Boolean passIsCorrect;
        try {
            passIsCorrect = currentUser.verifyPassword(password);
        } catch (InvalidPasswordException e) {
            System.out.println(e.toString());
            passIsCorrect = false;
        }
        if (passIsCorrect) {
            this.currentUser = currentUser;
            if (currentUser.getClass().equals(Cliente.class)) {
                return (String) getSession().getAttribute("previousPagePath");
            }
            if (currentUser.getClass().equals(Amministratore.class)) {
                return "AdministrationView.xhtml";
            }
            return "";
        } else return "";
    }


    public Boolean signUp(String nome, String cognome, String email, String password, String username, String nation, String city, String cap, String location) {
        Utente user = databaseController.checkUser(email);
        if (user != null)
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


    public Boolean closeOrder() {
        Ordine closedOrder = ((Cliente) this.currentUser).closeOrder();
        if (closedOrder != null)
            this.databaseController.addOrderToHandle(closedOrder);
        return closedOrder != null;
    }

    public Map<Long, Ordine> displayOrders() {
        if (this.checkType(this.currentUser) == 0)
            return ((Cliente) this.currentUser).getOrderHistory();
        if (this.checkType(this.currentUser) == 1)
            return databaseController.getAllOrders();
        return null;
    }

    public Map<Long, Ordine> displayNotValidatedOrders() {
        return databaseController.getOrders();
    }

    public Utente getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utente currentUser) {
        this.currentUser = currentUser;
    }


    public Ordine getCurrentOrderFromHistory(Long id) {
        return this.displayOrders().get(id);
    }

    public HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        return session;
    }

    public Prodotto addProductToCatalog(String name, int price, int quantity, String img, String description) {
        Prodotto productCreated = databaseController.addProductToCatalog(name, price, quantity, img, description);
        return productCreated;
    }

    public Integer checkCurrentUser() {
        return this.checkType(this.currentUser);
    }

    private Integer checkType(Utente currentUser) {
        if (currentUser.getClass().equals(Cliente.class))
            return 0;
        if (currentUser.getClass().equals(Amministratore.class))
            return 1;
        return -1;
    }

    public Cliente getClientFromId(String email) {
        return (Cliente) this.databaseController.checkUser(email);
    }

    public Ordine validateOrder(Long id) {
        return this.databaseController.evadiOrdine(id);
    }

    public void openEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        this.em = emf.createEntityManager();

    }
}