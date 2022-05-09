package ee.cs.ut.esi.bpb.model;

import javax.persistence.*;

@Entity
@Table(name = "delivery_refund_request")
public class DeliveryRefund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "refundrequest_id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "cancelled")
    private boolean cancelled = false;

    public DeliveryRefund(String description, int orderId) {
        this.description = description;
        this.orderId = orderId;
    }

    public DeliveryRefund() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderId() {
        return orderId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public DeliveryRefund setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }
}