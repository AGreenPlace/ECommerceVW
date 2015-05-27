package controller;

import model.Prodotto;
import view.ProductDescriptor;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Andrea on 07/05/15.
 */
@ManagedBean
public class CatalogViewController{
    @EJB
    private MainController mainController;
    private List<Prodotto> products;
    private String catalogHTMLDescription;

    public List<Prodotto> displayCatalog(){
        List<Prodotto> products = mainController.getProductsInCatalog();

        this.generateHtmlFromProducts(products);
        return products;
    }

    private String generateHtml(String inputData){
        String output = "<h1>Catalogo</h1>" +"\n"+
                inputData;
        return output;
    }

    private void generateHtmlFromProducts(List<Prodotto> products){
        String output = "";
        ProductDescriptor productDescriptor = new ProductDescriptor();
        for (Prodotto current : products){
            productDescriptor.setParameters(current.getName(),current.getImg(),current.getDescription());
            output += productDescriptor.generateHtmlForCatalogView()+ "\n";
        }
        this.catalogHTMLDescription = output;
    }

    public String getCatalogHTMLDescription() {
        return catalogHTMLDescription;
    }

    public MainController getMainController() {
        return mainController;
    }
}
