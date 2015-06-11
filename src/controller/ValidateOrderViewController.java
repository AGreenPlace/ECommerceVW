package controller;

import model.Ordine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 11/06/2015.
 */
@ManagedBean
public class ValidateOrderViewController {
    @EJB
    private MainController mainController;

    public List<Ordine> getInvalidateOrders(){
        List<Ordine> orders = new LinkedList<>(this.mainController.displayOrders().values());
        return orders;
    }

    public String validateOrder(Long id){
        this.mainController.validateOrder(id);
        return "AdministrationView.html";

    }
}
