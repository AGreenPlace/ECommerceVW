package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * Created by Andrea on 27/05/15.
 */
@ManagedBean
public class LoginViewController {
    @EJB
    MainController mainController;
    String username;
    String password;
    String previousPagePath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreviousPagePath() {
        return previousPagePath;
    }

    public String setPreviousPagePath(String previousPagePath) {
        this.previousPagePath = previousPagePath;
        return "LoginView.xhtml";
    }

    public String autheneticate(){
        Boolean loggedIn = mainController.login(this.username,this.password);
        if (loggedIn)
            return this.previousPagePath;
        else return "LoginView.xhtml";
    }
}
