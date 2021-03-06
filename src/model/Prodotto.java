package model;

import javax.persistence.*;

/**
 * Created by Andrea on 07/05/15.
 */

@Entity
@Table(name = "prodotto")
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Prodotto p")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private String code;
    @Column(length = 200)
    private String description;
    @Column(nullable=false)
    private int price;
    @Column(nullable=false)
    private int quantity;
    private String img;

    //METHODS


    @Override
    public String toString() {
        return "VWProdotto{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", img="+ img +
                '}';
    }

    //GETTERS AND SETTERS
    public Long getId(){
        return id;
    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
