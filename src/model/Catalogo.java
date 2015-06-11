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
        temp1.setName("Ombrello");
        temp1.setDescription("Un fottuto Ombrello");
        temp1.setQuantity(10);
        Prodotto temp2 = new Prodotto();
        temp2.setCode("2");
        temp2.setName("Cestello");
        temp2.setPrice(100000000);
        temp2.setQuantity(12);
        Prodotto temp3 = new Prodotto();
        temp3.setCode("1");
        temp3.setName("Ombrello");
        temp3.setDescription("Un altro fottuto ombrello");
        temp3.setQuantity(26);
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
    public Prodotto getProduct(String code){
        return this.products.get(code);
    }

    public Prodotto addProduct(String name, int price, int quantity, String img, String description) {
        Prodotto toBeAdded = new Prodotto();
        toBeAdded.setName(name);
        toBeAdded.setPrice(price);
        toBeAdded.setQuantity(quantity);
        toBeAdded.setImg(img);
        toBeAdded.setDescription(description);
        toBeAdded.setCode(this.generateProductCode());
        this.products.put(toBeAdded.getCode(), toBeAdded);
        return toBeAdded;
    }

    private String generateProductCode(){
        String output="";
        Boolean isAccettable = false;
        while (!isAccettable) {
            output = "" + Math.random() * 100 + 1;
            isAccettable = !this.products.containsKey(output);
        }
        return output;
    }
}
