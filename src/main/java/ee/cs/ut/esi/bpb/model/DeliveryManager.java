package ee.cs.ut.esi.bpb.model;

import javax.persistence.*;

@Entity
@Table(name = "delivery_manager")
public class DeliveryManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manager_id")
    private int id;
    @Column(name = "name")
    private String name;

    public DeliveryManager() {
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

    public DeliveryManager(String name) {
        this.name = name;
    }
}