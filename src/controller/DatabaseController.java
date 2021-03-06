package controller;

import model.*;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Created by Andrea on 07/05/15.
 */
@ManagedBean
public class DatabaseController {
    private Catalogo catalog;
    private Map<String,Utente> utenti;
    private Map<Long,Ordine> orders;
    private Map<Long, Ordine> ordersValidated;



    public DatabaseController() {
        this.catalog = new Catalogo();
        this.utenti = new HashMap<>();
        this.orders = new HashMap<>();
        Utente u1 = new Cliente("luca@luca.com","luca");
        Utente u2 = new Amministratore("andrea@andrea.com","andrea");
        this.utenti.put(u1.getEmail(),u1);
        this.utenti.put(u2.getEmail(),u2);

    }

    public List<Prodotto> getProductsInCatalog(EntityManager em){
        CriteriaQuery<Prodotto> cq = em.getCriteriaBuilder().createQuery(Prodotto.class);
        cq.select(cq.from(Prodotto.class));
        List<Prodotto> result = em.createQuery(cq).getResultList();
        return result;
    }

    public String describeCatalog(){
        return catalog.getCatalogDescription();
    }

    public Prodotto getProductFromCatalog(String id){
        return this.catalog.getProduct(id);
    }

    public Utente checkUser(String email){
        return utenti.get(email);
    }

    public Boolean createClient(String nome,String cognome,String email,String password,String username,String nation,String city,String cap,String location) throws Exception{
        if(nome.isEmpty())
            throw new Exception("Name is Empty");
        if(cognome.isEmpty())
            throw new Exception("Cognome is Empty");
        if(email.isEmpty())
            throw new Exception("Email is Empty");
        if(password.isEmpty())
            throw new Exception("Password is Empty");
        if(username.isEmpty())
            throw new Exception("Username is Empty");
        if(nation.isEmpty())
            throw new Exception("Nation is Empty");
        if(city.isEmpty())
            throw new Exception("City is Empty");
        if(cap.isEmpty())
            throw new Exception("Cap is Empty");
        if(location.isEmpty())
            throw new Exception("Location is Empty");
       Cliente nuovoCliente = new Cliente(email, password);
        nuovoCliente.setNome(nome);
        nuovoCliente.setCognome(cognome);
        nuovoCliente.setUsername(username);
        nuovoCliente.setNation(nation);
        nuovoCliente.setCity(city);
        nuovoCliente.setCap(cap);
        nuovoCliente.setLocation(location);
        this.utenti.put(nuovoCliente.getEmail(),nuovoCliente);
        return true;
    }

    public Boolean addOrderToHandle(Ordine toBeHandled) {
                this.orders.put(toBeHandled.getId(), toBeHandled);
        if(this.orders.containsValue(toBeHandled))
            return true;
        else return false;
    }

    public Prodotto addProductToCatalog(String name, int price, int quantity,String code, String img, String description, EntityManager em) {
        /*Prodotto productAdded = this.catalog.addProduct(name, price, quantity, img, description);*/
        Prodotto productCreated = new Prodotto();
        productCreated.setName(name);
        productCreated.setDescription(description);
        productCreated.setPrice(price);
        productCreated.setQuantity(quantity);
        productCreated.setImg(img);
        productCreated.setCode(code);
        em.persist(productCreated);
        return productCreated;
    }

    public Map<Long, Ordine> getOrders(){
        return this.orders;
    }

    public Map<Long, Ordine> getAllOrders() {
        Map<Long,Ordine> output = new HashMap<>(this.orders);
        if (this.ordersValidated != null)
            output.putAll(this.ordersValidated);
        return output;
    }

    public void setOrders(Map<Long, Ordine> orders) {
        this.orders = orders;
    }

    public Ordine evadiOrdine(Long id) {
        Ordine validatedOrder = this.orders.get(id);
        validatedOrder.setDataSpedizione();
        if (this.ordersValidated == null)
            this.ordersValidated = new HashMap<>();
        this.ordersValidated.put(validatedOrder.getId(), validatedOrder);
        this.orders.remove(validatedOrder.getId());
        return validatedOrder;


    }
}
