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
import java.util.HashMap;
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
/*        Prodotto temp1 = new Prodotto();
        temp1.setCode("236231023");
        temp1.setName("ParaFulmini");
        temp1.setDescription("un parafulmini molto csotoso");
        temp1.setQuantity(62);
        temp1.setPrice(103);
        this.em.persist(temp1);*/
        CriteriaQuery<Prodotto> cq = em.getCriteriaBuilder().createQuery(model.Prodotto.class);
        cq.select(cq.from(model.Prodotto.class));
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
        this.currentProduct = this.em.find(Prodotto.class, id);
        return currentProduct;
    }

    public String login(String email, String password) {
        Utente currentUser = this.em.find(Utente.class, email);
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
        Utente user = this.em.find(Utente.class, email);
        if (user != null)
            return false;
        else {
            Boolean userWasCreated = false;
            try {
                Cliente cliente = new Cliente(email, password);
                cliente.setCognome(cognome);
                cliente.setNome(nome);
                cliente.setUsername(username);
                cliente.setCity(city);
                cliente.setCap(cap);
                cliente.setLocation(location);
                cliente.setNation(nation);
                em.persist(cliente);
                return true;
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return userWasCreated;
        }
    }


    public Boolean closeOrder() {
        Ordine closedOrder = ((Cliente) this.currentUser).closeOrder();
//        if (closedOrder != null) {
//            this.databaseController.addOrderToHandle(closedOrder);
//        }
        this.updateOrder(closedOrder);
        return closedOrder != null;
    }

    public Map<Long, Ordine> displayOrders() {
        if (this.checkType(this.currentUser) == 0)
            return ((Cliente) this.currentUser).getOrderHistory();
        if (this.checkType(this.currentUser) == 1) {
            Map<Long,Ordine> output = new HashMap<>();
            CriteriaQuery<Ordine> query = em.getCriteriaBuilder().createQuery(Ordine.class);
            query.select(query.from(Ordine.class));
            List<Ordine> allOrders = this.em.createQuery(query).getResultList();
            for (Ordine current : allOrders)
                output.put(current.getId(),current);
        }
        return null;
    }

    public Map<Long, Ordine> displayNotValidatedOrders() {
        Map<Long,Ordine> first = this.displayOrders();
        Map<Long,Ordine> output = new HashMap<>();
        for (Ordine current : first.values()){
            if(current.getState() == 1)
                output.put(current.getId(), current);
        }
        return output;
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

    public Prodotto addProductToCatalog(String name, int price, int quantity,String code, String img, String description) {
       Prodotto productCreated = databaseController.addProductToCatalog(name, price, quantity,code, img, description, this.em);
       /* Prodotto productCreated = new Prodotto();
        productCreated.setName(name);
        productCreated.setDescription(description);
        productCreated.setPrice(price);
        productCreated.setQuantity(quantity);
        productCreated.setImg(img);
        productCreated.setCode(code);
        this.em.persist(productCreated);
        return productCreated;*/
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
//        return (Cliente) this.databaseController.checkUser(email);
        Cliente output = this.em.find(Cliente.class,email);
        return output;
    }

    public Ordine validateOrder(Long id) {
//        return this.databaseController.evadiOrdine(id);
        Ordine toValidate = this.em.find(Ordine.class, id);
        toValidate.validate();
        return toValidate;

    }

    public void openEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("");
        this.em = emf.createEntityManager();

    }

    private void updateOrder(Ordine o){
        em.merge(o);
    }

    private void updateClient(Cliente c){
        em.merge(c);
    }

    private void updateProduct(Prodotto p){
        em.merge(p);
    }

    private void removeOrder(Ordine o){
        em.remove(o);
    }

    private void removeClient(Cliente c){
        em.remove(c);
    }

    private void removeProduct(Prodotto p){
        em.remove(p);
    }

}