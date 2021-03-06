package model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Luca on 10/06/2015.
 */


@Entity
@DiscriminatorValue("C")
@Table(name="clienti")
public class Cliente extends Utente {
    @Column(nullable=false)
    private String nation;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String cap;
    @Column(nullable=false)
    private String location;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Ordine currentOrder;
    @OneToMany(mappedBy = "ordersClient", fetch =FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private List<Ordine> orderHistory;

    


    public Cliente(String email, String password) {

        super(email, password);
    }

    public Cliente(){}


    public Ordine addProductToOrder(Prodotto prodotto, Integer quantity){
        if(this.currentOrder== null) {
            this.currentOrder= new Ordine();
        }
        Boolean productAdded = this.currentOrder.addProduct(prodotto, quantity);
        if (productAdded) {
            this.currentOrder.setDataCreazione(Calendar.getInstance().getTime());
            this.currentOrder.setOrdersClient(this);
            this.currentOrder.setState(0);
            return this.currentOrder;
        }
        return null;

    }

    public Ordine closeOrder(EntityManager em) {
        if (this.orderHistory == null)
            this.orderHistory = new LinkedList<>();
        this.currentOrder= em.find(Ordine.class,this.currentOrder.getId() );
        this.orderHistory.add(this.currentOrder);
        Ordine output= this.currentOrder;
        output.setOrdersClient(this);
        output.close();
        this.currentOrder = null;
        return output;
    }


    private Boolean historyContainsId(Long id){
        for(Ordine current:this.orderHistory){
            if(current.getId().equals(id))
                return true;
        }
        return false;
    }


    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Ordine getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Ordine currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Ordine> getOrderHistory() {
        return this.orderHistory;
    }

    public void setOrderHistory(List<Ordine> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
