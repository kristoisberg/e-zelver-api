package ee.cs.ut.esi.bpb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_invoice")
public class DeliveryInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id")
    private int id;
    @Column(name = "price")
    private int price;
    @Column(name = "date")
    private Date date;

    public DeliveryInvoice(int price, Date date) {
        this.price = price;
        this.date = date;
    }

    public DeliveryInvoice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
