package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrea on 07/05/15.
 */

@Entity
public class Catalogo {
    @OneToMany
    private Map<String, Prodotto> products;


    public Catalogo() {
        this.products = new HashMap<String, Prodotto>();
        Prodotto temp1 = new Prodotto();
        temp1.setCode("1");
        temp1.setName("1");
        temp1.setQuantity(1);
        Prodotto temp2 = new Prodotto();
        temp2.setCode("2");
        temp2.setName("2");
        Prodotto temp3 = new Prodotto();
        temp3.setCode("1");
        temp3.setName("1");
        temp3.setQuantity(1);
        this.setProduct(temp1);
        this.setProduct(temp2);
        this.setProduct(temp3);
    }

    //METHOD

    public List<Prodotto> getProducts(){
        return new LinkedList<>(this.products.values());
    }

    public String getCatalogDescription(){
        String outputString = "";
        for(Prodotto current : products.values()){
            outputString += "<p>"+current.toString()+"</p>";
        }
        return outputString;
    }

    public void setProduct(Prodotto product) {
        if (!this.products.containsKey(product.getCode())) {
            this.products.put(product.getCode(), product);
        }
        else {
            Prodotto existingProduct = this.products.get(product.getCode());
            existingProduct.setQuantity(existingProduct.getQuantity()+product.getQuantity());
        }
    }

    //UTILITY METHODS
    private Prodotto getProduct(String code){
        return this.products.get(code);
    }
}
