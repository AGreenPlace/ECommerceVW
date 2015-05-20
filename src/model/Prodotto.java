package model;

import javax.persistence.*;

/**
 * Created by Andrea on 07/05/15.
 */

@Entity
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String description;
    private int price;
    private int quantity;

    //METHODS


    @Override
    public String toString() {
        return "VWProdotto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    //GENERATED EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prodotto that = (Prodotto) o;

        if (price != that.price) return false;
        if (quantity != that.quantity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!code.equals(that.code)) return false;
        return !(description != null ? !description.equals(that.description) : that.description != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + code.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + quantity;
        return result;
    }
}
