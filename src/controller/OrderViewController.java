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
    @ManagedProperty(value = "#{param.idProdottoDaAggiungere}")
    Long idProdottoDaAggiungere;
    String quantity;
    @ManagedProperty(value = "#{param.sender}")
    String sender;

    public String initWithId(Long id){
        this.currentOrder = this.mainController.getCurrentOrderFromHistory(id);
        System.out.println("currentOrder" + this.currentOrder.getRigheordine());
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
        Prodotto prodottoDaAggiungere = this.mainController.getProductFromCatalog(this.idProdottoDaAggiungere);
        String output;
        if (this.quantity == null){
            return null;
        }
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

    public Long getIdProdottoDaAggiungere() {
        return idProdottoDaAggiungere;
    }

    public void setIdProdottoDaAggiungere(Long idProdottoDaAggiungere) {
        this.idProdottoDaAggiungere = idProdottoDaAggiungere;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer provaSender(){
        if (this.sender.equals("ValidateOrderView")) {
            return 1;
        }
        return 0;
    }

    public boolean orderPorcoDio() {
        return provaSender().equals(1) && mainController.checkCurrentUser()==1;
    }
}
