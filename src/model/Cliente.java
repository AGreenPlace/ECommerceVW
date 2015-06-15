package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luca on 10/06/2015.
 */


@Entity
@DiscriminatorValue("C")
public class Cliente extends Utente {
    @Column(nullable=false)
    private String nation;
    @Column(nullable=false)
    private String city;
    @Column(nullable=false)
    private String cap;
    @Column(nullable=false)
    private String location;
    @OneToOne
    private Ordine currentOrder;
    @OneToMany(mappedBy = "ordersClient")
    private Map<Long,Ordine> orderHistory;

    


    public Cliente(String email, String password) {
        super(email, password);
    }

    public Cliente(){}


    public Ordine addProductToOrder(Prodotto prodotto, Integer quantity){
        if(this.currentOrder== null) {
            Boolean isAccetable = false;
            while (!isAccetable) {
                Long idOrder = (long) (Math.random() * 100 + 1);
                if(this.orderHistory == null||!this.orderHistory.containsKey(idOrder)) {
                    this.currentOrder = new Ordine(idOrder);
                    isAccetable = true;
                }
            }
        }
        Boolean productAdded = this.currentOrder.addProduct(prodotto, quantity);
        if (productAdded)
            return this.currentOrder;
        return null;

    }

    public Ordine closeOrder() {
        if (this.orderHistory == null)
            this.orderHistory = new HashMap<>();
        this.orderHistory.put(this.currentOrder.getId(),this.currentOrder);
        Ordine output= this.orderHistory.get(this.currentOrder.getId());
        output.setOrdersClient(this);
        output.close();
        this.currentOrder= null;
        return output;
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

    public Map<Long,Ordine> getOrderHistory() {
        return this.orderHistory;
    }

    public void setOrderHistory(Map<Long, Ordine> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
