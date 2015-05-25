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

    public String initWithId(String id){
        this.currentProd = this.mainController.getProductFromCatalog(id);
        return "ProductDetailView.xhtml";
    }

    public String getDesc() {
        this.desc = "L'Id è: "+ this.currentProd.getCode();
        return desc;
    }
}
