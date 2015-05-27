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
        Boolean loggedIn = mainController.login(this.username,this.password);
        if (loggedIn) {
            return (String) getSession().getAttribute("previousPagePath");
        }
        else return "LoginView.xhtml";
    }

    private HttpSession getSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
        return session;
    }
}
