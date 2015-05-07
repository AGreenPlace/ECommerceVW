package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrea on 07/05/15.
 */
@WebServlet("/consultCatalog")
public class ConsultCatalog extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("mainController") == null){
            req.getSession().setAttribute("mainController",new MainController());
        }
        MainController mainController= (MainController)req.getSession().getAttribute("mainController");
        String catalogDescription = mainController.consultCatalog();
        req.getSession().setAttribute("currentBodyContent",this.generateHtml(catalogDescription));
        req.getServletContext().getRequestDispatcher(resp.encodeURL("/catalogView.jsp")).forward(req,resp);
    }

    private String generateHtml(String inputData){
        String output = "<h1>Catalogo</h1>" +"\n"+
                inputData;
        return output;
    }
}
