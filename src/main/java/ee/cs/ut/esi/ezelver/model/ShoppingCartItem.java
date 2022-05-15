package ee.cs.ut.esi.ezelver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ezelver_shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonIgnore
    private ShoppingCart shoppingCart;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_entry_id", nullable = false)
    private ProductEntry productEntry;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(ShoppingCart shoppingCart, ProductEntry productEntry, int quantity) {
        this.shoppingCart = shoppingCart;
        this.productEntry = productEntry;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ProductEntry getProductEntry() {
        return productEntry;
    }

    public void setProductEntry(ProductEntry productEntry) {
        this.productEntry = productEntry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
