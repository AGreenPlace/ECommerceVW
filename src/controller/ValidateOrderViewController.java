package controller;

import model.Ordine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 11/06/2015.
 */
@ManagedBean
public class ValidateOrderViewController {
    @EJB
    private MainController mainController;
    @ManagedProperty(value ="#{param.id}")
    private Long orderId;

    public List<Ordine> getInvalidateOrders(){
        List<Ordine> orders = new LinkedList<>(this.mainController.displayNotValidatedOrders().values());
        return orders;
    }

    public String shipOrder(){

        Ordine ordine= this.mainController.validateOrder(this.orderId);
        if (ordine ==null)
            return "ValidateOrderView.xhtml";
        return "AdministrationView.xhtml";

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
