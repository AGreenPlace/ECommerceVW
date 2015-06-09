package controller;

import model.Ordine;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.Order;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luca on 09/06/2015.
 */
@ManagedBean
public class HistoryOrdersViewController {
    @EJB
    private MainController mainController;
    private Ordine currentOrder;


    public List<Ordine> getHistoryOrder(){
        return new LinkedList<Ordine>(mainController.displayOrders().values());
    }

    public String getOrderById(Long id){
        this.currentOrder=this.mainController.getCurrentOrderFromHistory(id);
        return "OrderView.xhtml";
    }


}
