package model;

/**
 * Created by Luca on 29/05/2015.
 */
public class RigaOrdine {
    private Prodotto currentProduct;
    private  Integer quantity;

    public RigaOrdine(){

    }

    public RigaOrdine(Prodotto prodotto, Integer quantity) {
        this();
        this.setCurrentProduct(prodotto);
        this.setQuantity(quantity);

    }


    public Prodotto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Prodotto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
