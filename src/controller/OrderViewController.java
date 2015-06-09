package controller;

import model.Ordine;
import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by Andrea on 29/05/15.
 */
@ManagedBean
public class OrderViewController {
    @EJB
    MainController mainController;
    Ordine currentOrder;
    @ManagedProperty(value = "#{param.codiceProdottoDaAggiungere}")
    String codiceProdottoDaAggiungere;
    String quantity;

    public String closeOrder(){
        String output;
        Boolean orderIsClosed = this.mainController.closeOrder();
        if(orderIsClosed)
            output = "L'ordine è stato completato";
        else
            output = "Si è verificato un errore nella chiusura dell'ordine";
        return output;
    }

    public String addProductToOrder(){
        Prodotto prodottoDaAggiungere = this.mainController.getProductFromCatalog(this.codiceProdottoDaAggiungere);
        this.currentOrder = this.mainController.addProductToOrder(prodottoDaAggiungere,new Integer(this.quantity));
        String output;
        if (this.currentOrder != null){
            output = "OrderView.xhtml";
        }
        else {
            output = null;
        }
        return output;
    }


    public Ordine getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Ordine currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String getCodiceProdottoDaAggiungere() {
        return codiceProdottoDaAggiungere;
    }

    public void setCodiceProdottoDaAggiungere(String codiceProdottoDaAggiungere) {
        this.codiceProdottoDaAggiungere = codiceProdottoDaAggiungere;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
