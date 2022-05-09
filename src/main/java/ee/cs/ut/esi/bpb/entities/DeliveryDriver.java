package ee.cs.ut.esi.bpb.entities;

import javax.persistence.*;

@Entity
@Table(name = "delivery_driver")
public class DeliveryDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_id")
    private int id;
    @Column(name = "name")
    private String name;

    public DeliveryDriver(String name) {
        this.name = name;
    }

    public DeliveryDriver() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}