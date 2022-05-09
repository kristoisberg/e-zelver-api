package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;

@Entity
@Table(name = "ezelver_shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartItem_id")
    private int id;
    @Column(name = "cart_id", insertable = false, updatable = false)
    private int shoppingCartId;
    @ManyToOne
    @JoinColumn(name="cart_id", nullable = false)
    private ShoppingCart shoppingCart;
    @Column(name = "product_entry_id", insertable = false, updatable = false)
    private int productEntryId;
    @ManyToOne
    @JoinColumn(name = "product_entry_id", nullable = false)
    private ProductEntry productEntry;
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

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getProductEntryId() {
        return productEntryId;
    }

    public void setProductEntryId(int productEntryId) {
        this.productEntryId = productEntryId;
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
