package controller;

import model.Cliente;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Created by Luca on 11/06/2015.
 */
@ManagedBean
public class UserViewController {
    @EJB
    private MainController mainController;
    private Cliente cliente;
    @ManagedProperty(value="#{param.email}")
    private String email;



    public String initWithEmail(){
        this.cliente=this.mainController.getClientFromId(email);
        return "UserView.xhtml";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
