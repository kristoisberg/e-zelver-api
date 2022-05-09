package ee.cs.ut.esi.bpb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private int id;
    @Column(name = "pickupDate")
    private Date pickupDate;
    @Column(name = "pickupAddress")
    private String pickupAddress;
    @Column(name = "deliveryDate")
    private Date deliveryDate;
    @Column(name = "deliveryAddress")
    private String deliveryAddress;
    @Column(name = "deliveryDetails")
    private String deliveryDetails;

    public Delivery(Date pickupDate, String pickupAddress, Date deliveryDate, String deliveryAddress, String deliveryDetails) {
        this.pickupDate = pickupDate;
        this.pickupAddress = pickupAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDetails = deliveryDetails;
    }

    public Delivery() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public Delivery updateDateAndAddress(Date date, String address) {
        this.deliveryDate = date;
        this.deliveryAddress = address;
        return this;
    }

    public Delivery updatePickupDateAndAddress(Date date, String address) {
        this.pickupDate = date;
        this.pickupAddress = address;
        return this;
    }
}