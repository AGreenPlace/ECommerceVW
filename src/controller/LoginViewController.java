package controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
        HttpSession session = getSession();
        session.setAttribute("previousPagePath",previousPagePath);
        return "LoginView.xhtml";
    }

    public String authenticate(){
        String destination = mainController.login(this.username,this.password);
        if (!destination.isEmpty()) {
            return destination;
        }
        return "LoginView.xhtml";
    }

    private HttpSession getSession(){
        return mainController.getSession();
    }
}
