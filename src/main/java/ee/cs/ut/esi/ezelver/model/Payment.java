package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ezelver_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "amount")
    private float amount;

    public Payment(Date paymentDate, float amount) {
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}