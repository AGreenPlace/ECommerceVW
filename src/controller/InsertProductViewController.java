package controller;

import model.Prodotto;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Luca on 10/06/2015.
 */

@ManagedBean
public class InsertProductViewController {
    @EJB
    private MainController mainController;
    private String name;
    private int price;
    private int quantity;
    private String img;
    private String description;


    public String addProduct(){
        mainController.addProductToCatalog(name, price, quantity, img, description);
        return"AdministrationView";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


