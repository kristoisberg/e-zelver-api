package ee.cs.ut.esi.ezelver.model;
import javax.persistence.*;

@Entity
@Table(name = "ezelver_product_entry")
public class ProductEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "dimensions")
    private String dimensions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ProductEntry() {
    }

    public ProductEntry(int quantity, String name, String type, Float weight, String dimensions) {
        this.quantity = quantity;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.dimensions = dimensions;
    }

}
