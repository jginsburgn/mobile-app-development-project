package mx.itesm.csf.proyectofinal.Model;

public class SearchModel {

    private String type;
    private String destination;
    private String description;
    private String price;
    private String product_price;

    /* Constructors */
    public SearchModel( String type, String destination, String description, String price)
    {
        this.type = type;
        this.description = description;
        this.destination = destination;
        this.price = price;
    }

    public SearchModel()
    {    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String setDescription() {
        return product_price;
    }

    public void setDescription(String product_price) {
        this.product_price = product_price;
    }

    public String getDescription() {

        return description;
    }

}