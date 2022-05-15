package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;

@Entity
@Table(name = "ezelver_store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int id;

    @Column(name = "address")
    private String address;

    public Store(String address) {
        this.address = address;
    }

    public Store() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
