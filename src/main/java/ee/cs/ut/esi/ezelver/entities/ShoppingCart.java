package ee.cs.ut.esi.ezelver.entities;

import javax.persistence.*;

@Entity
@Table(name = "ezelver_shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private int id;
    @Column(name = "amount")
    private int amount;

    public ShoppingCart(int amount) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}