package ee.cs.ut.esi.bpb.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_order")
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deliveryorder_id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "type")
    private String type;

    @Column(name = "refunded")
    private boolean refunded = false;

    public DeliveryOrder() {
    }

    public DeliveryOrder(Date date, String type) {
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRefunded() {
        return refunded;
    }

    public DeliveryOrder setRefunded(boolean refunded) {
        this.refunded = refunded;
        return this;
    }
}