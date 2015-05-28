package controller;

import model.Carrello;
import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Luca on 28/05/2015.
 */
@ManagedBean
public class CartViewController {
    @EJB
   private MainController mainController;
    private Integer quantity;
    private Prodotto currentProduct;


    public String insertQuantity(){

        this.currentProduct = mainController.getCurrentProduct();
        return "ProductAddToCart.xhtml";

    }
    public String addToCart(){
        Boolean quantityIsRight = mainController.selectQuantity(quantity);
        if(quantityIsRight)
            return "index.xhtml";
        else return "ProductAddToCart.xhtml";
    }

    public Prodotto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Prodotto currentProduct) {
        this.currentProduct = currentProduct;
    }


}


