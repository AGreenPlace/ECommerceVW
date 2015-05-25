package controller;

import model.Prodotto;
import view.ProductDescriptor;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
//@WebServlet("/consultCatalog")
@ManagedBean
public class CatalogViewController{
    @EJB
    private MainController mainController;
    private List<Prodotto> products;
 /*   private String catalogHTMLDescription;*/
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getSession().getAttribute("mainController") == null){
//            req.getSession().setAttribute("mainController",new MainController());
//        }
//        MainController mainController= (MainController)req.getSession().getAttribute("mainController");
//        String catalogDescription = mainController.consultCatalog();
//        req.getSession().setAttribute("currentBodyContent",this.generateHtml(catalogDescription));
//        req.getServletContext().getRequestDispatcher(resp.encodeURL("/catalogView.jsp")).forward(req,resp);
//    }

    public List<Prodotto> displayCatalog(){
        if(this.mainController == null){
            this.mainController = new MainController();
        }
        List<Prodotto> products = this.mainController.getProductsInCatalog();
        return products;
        /*this.generateHtmlFromProducts(products);

        return "catalogView";*/
    }

/*    private String generateHtml(String inputData){
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
    }*/
}
