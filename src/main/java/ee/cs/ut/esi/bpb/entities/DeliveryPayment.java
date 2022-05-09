package ee.cs.ut.esi.bpb.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery_payment")
public class DeliveryPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private Date date;
    @Column(name = "amount")
    private int amount;
    @Column(name = "accountName")
    private String accountName;
    @Column(name = "accountNumber")
    private String accountNumber;

    public DeliveryPayment() {
    }

    public DeliveryPayment(String type, Date date, int amount, String accountName, String accountNumber) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}