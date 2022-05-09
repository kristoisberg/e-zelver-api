package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;

@Entity
@Table(name = "ezelver_shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartItem_id")
    private int id;
    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}