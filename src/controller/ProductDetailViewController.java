package controller;

import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 25/05/15.
 */
@ManagedBean
public class ProductDetailViewController {
    private Long id;
    private String desc;

    public String initWithId(Long id){
        this.id = id;
        return "ProductDetailView.xhtml";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        this.desc = "L'Id è: "+ this.id;
        return desc;
    }
}
