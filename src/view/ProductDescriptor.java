package view;


/**
 * Created by Andrea on 20/05/15.
 */
public class ProductDescriptor {
    private String header;
    private String image;
    private String body;

    public ProductDescriptor() {
    }

    public void setParameters(String header, String image, String body){
        this.header = header;
        this.image = image;
        this.body = body;
    }

    public String generateHtmlForCatalogView(){
        String output = "<div id=\"container\">\n" +
                        "  <div class=\"containerImg\">  <img src=\""+this.image+"\"></div>\n" +
                        "<div class=\"containerText\">\n"+
                        "    <p>"+this.header+"</p>\n" +
                        "    <p>"+this.body+"</p>\n" +
                        "</div>\n" +
                        "</div>";

        return output;
    }
}
