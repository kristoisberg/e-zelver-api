package ee.cs.ut.esi.ezelver.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue("customer")
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    @NotNull
    @Column(name = "age")
    private int age;

    public Customer(String email, String password, String name, int age) {
        super(email, password, name);
        this.age = age;
    }

    public Customer() {
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public List<Role> getRoles() {
        return Collections.singletonList(Role.CUSTOMER);
    }

}