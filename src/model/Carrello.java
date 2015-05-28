package model;

import java.util.List;

/**
 * Created by Luca on 27/05/2015.
 */
public class Carrello {

private List<Prodotto> productsInCart;


    public List<Prodotto> getProductsInCart() {
        return productsInCart;
    }

    public void setProductsInCart(List<Prodotto> productsInCart) {
        this.productsInCart = productsInCart;
    }
}
