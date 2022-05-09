package ee.cs.ut.esi.bpb.model;

import javax.persistence.*;

@Entity
@Table(name = "delivery_history")
public class DeliveryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "deliveryhistory_id")
    private int id;
    @Column(name = "details")
    private String details;
    @Column(name = "deliveryStatus")
    private String deliveryStatus;
    @Column(name = "trackingNumber")
    private String trackingNumber; //I also think this shouldn't be an int :))))

    public DeliveryHistory(String details, String deliveryStatus, String trackingNumber) {
        this.details = details;
        this.deliveryStatus = deliveryStatus;
        this.trackingNumber = trackingNumber;
    }

    public DeliveryHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}

