package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 29/05/15.
 */
@ManagedBean
public class OrderViewController {
    @EJB
    MainController mainController;

}
