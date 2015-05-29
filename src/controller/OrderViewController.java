package controller;

import model.Ordine;
import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 29/05/15.
 */
@ManagedBean
public class OrderViewController {
    @EJB
    MainController mainController;
    Ordine currentOrder;

    public String closeOrder(){
        String output;
        Boolean orderIsClosed = this.mainController.closeOrder();
        if(orderIsClosed)
            output = "L'ordine è stato completato";
        else
            output = "Si è verificato un errore nella chiusura dell'ordine";
        return output;
    }

    public Ordine getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Ordine currentOrder) {
        this.currentOrder = currentOrder;
    }

    public String addProductToOrder(Prodotto currentProd, Integer quantity){
        this.currentOrder = this.mainController.addProductToOrder(currentProd,quantity);
        String output;
        if (this.currentOrder != null){
            output = "OrderView.xhtml";
        }
        else {
            output = null;
        }
        return output;
    }
}
