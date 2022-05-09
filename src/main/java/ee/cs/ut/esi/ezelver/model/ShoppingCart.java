package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ezelver_shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private int id;
    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customerId;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "shoppingCart")
    private List<ShoppingCartItem> items;
    @Column(name = "amount")
    private int amount;

    public ShoppingCart(Customer customer, int amount) {
        this.customer = customer;
        this.amount = amount;
    }

    public ShoppingCart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCartItem> items) {
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}