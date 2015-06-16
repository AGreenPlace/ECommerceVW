package controller;

import model.Prodotto;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 25/05/15.
 */
@ManagedBean
public class ProductDetailViewController {
    @EJB
    private MainController mainController;
    private Prodotto currentProd;
    private String desc;
    private Integer quantity;

    public String initWithId(Long id){
        this.currentProd = this.mainController.getProductFromCatalog(id);
        return "ProductDetailView.xhtml";
    }

    public String getDesc() {
        this.desc = "L'Id è: "+ this.currentProd.getCode();
        return desc;
    }
    public Prodotto getCurrentProd(){
        return this.currentProd;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean currentUserExists(){
        if(this.mainController.getCurrentUser()!=null)
            return true;
        return false;
    }
}
