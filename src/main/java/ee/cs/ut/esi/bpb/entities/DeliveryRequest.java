package ee.cs.ut.esi.bpb.entities;

import javax.persistence.*;

@Entity
@Table(name = "delivery_request")
public class DeliveryRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deliveryrequest_id")
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "weight")
    private float weight;
    @Column(name = "size")
    private float size;

    public DeliveryRequest() {
    }

    public DeliveryRequest(String description, String location, float weight, float size) {
        this.description = description;
        this.location = location;
        this.weight = weight;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}

