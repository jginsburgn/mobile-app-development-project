package mx.itesm.csf.proyectofinal.Model;
import mx.itesm.csf.proyectofinal.Fragments.Historial;

public class HistorialModel {

    private String type;
    private String destination;
    private String description;
    private String price;
    private String product_price;

    /* Constructors */
    public HistorialModel( String type, String destination, String description, String price)
    {
        this.type = type;
        this.description = description;
        this.destination = destination;
        this.price = price;
    }

    public HistorialModel()
    {

    }

    /* Getters and Setters */

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

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
