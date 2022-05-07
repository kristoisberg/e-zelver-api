package ee.cs.ut.esi.ezelver.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int id;
    @Column(name = "status")
    private String status;
    @Column(name = "deliveryLocation")
    private String deliveryLocation;
    @Column(name = "deliveryPrice")
    private int deliveryPrice;
    @Column(name = "deliveryTime")
    private Date deliveryTime;
    @Column(name = "feedback")
    private String feedback;

    public Order(String status, String deliveryLocation, int deliveryPrice, Date deliveryTime, String feedback) {
        this.status = status;
        this.deliveryLocation = deliveryLocation;
        this.deliveryPrice = deliveryPrice;
        this.deliveryTime = deliveryTime;
        this.feedback = feedback;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}