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
        String output = "<div class=\"container\">\n" +
                        "    <img src=\""+this.image+"\">\n" +
                        "<div class=\"textContainer\">\n"+
                        "    <h2>"+this.header+"</h2>\n" +
                        "    <p>"+this.body+"</p>\n" +
                        "</div>";
        return output;
    }
}
