package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ezelver_product_entry")
public class ProductEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_entry_id")
    private int id;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @NotNull
    @Column(name = "price")
    private float price;

    @NotNull
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "weight")
    private float weight;

    @NotNull
    @Column(name = "dimensions")
    private String dimensions;

    public ProductEntry(int quantity, String name, String image, String type, float price, float weight,
                        String dimensions) {
        this.quantity = quantity;
        this.name = name;
        this.image = image;
        this.type = type;
        this.price = price;
        this.weight = weight;
        this.dimensions = dimensions;
    }

    public ProductEntry() {
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
}
