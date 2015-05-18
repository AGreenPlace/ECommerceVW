package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrea on 07/05/15.
 */
public class Catalog {
    private Map<String, Product> products;

    public Catalog() {
        this.products = new HashMap<String, Product>();
        Product temp1 = new Product();
        temp1.setCode("1");
        temp1.setName("1");
        temp1.setQuantity(1);
        Product temp2 = new Product();
        temp2.setCode("2");
        temp2.setName("2");
        Product temp3 = new Product();
        temp3.setCode("1");
        temp3.setName("1");
        temp3.setQuantity(1);
        this.setProduct(temp1);
        this.setProduct(temp2);
        this.setProduct(temp3);
    }

    //METHODS

    public String getCatalogDescription(){
        String outputString = "";
        for(Product current : products.values()){
            outputString += "<p>"+current.toString()+"</p>";
        }
        return outputString;
    }

    public void setProduct(Product product) {
        if (!this.products.containsKey(product.getCode())) {
            this.products.put(product.getCode(), product);
        }
        else {
            Product existingProduct = this.products.get(product.getCode());
            existingProduct.setQuantity(existingProduct.getQuantity()+product.getQuantity());
        }
    }

    //UTILITY METHODS
    private Product getProduct(String code){
        return this.products.get(code);
    }
}
