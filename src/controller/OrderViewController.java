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

    public String initWithId(Long id){
        this.currentOrder = this.mainController.getCurrentOrderFromHistory(id);
        return "OrderView.xhtml";
    }

    public String closeOrder(){
        String output;
        Boolean orderIsClosed = this.mainController.closeOrder();
        if(orderIsClosed)
            output = "index.xhtml";
        else
            output = "index.xhtml";
        return output;
    }

    public String addProductToOrder(){
        Prodotto prodottoDaAggiungere = this.mainController.getProductFromCatalog(this.codiceProdottoDaAggiungere);
        String output;
        Integer selectedQuantity=new Integer(this.quantity);

        if((prodottoDaAggiungere.getQuantity()>= selectedQuantity)&& prodottoDaAggiungere.getQuantity()>0){
            prodottoDaAggiungere.setQuantity(prodottoDaAggiungere.getQuantity()-selectedQuantity);
            this.currentOrder = this.mainController.addProductToOrder(prodottoDaAggiungere,selectedQuantity);
            if (this.currentOrder != null){
                output = "OrderView.xhtml";
            }
            else {
                output = null;
            }
        }
        else{
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

    public MainController getMainController() {
        return mainController;
    }
}
