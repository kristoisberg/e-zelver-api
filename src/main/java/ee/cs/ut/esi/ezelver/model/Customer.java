package ee.cs.ut.esi.ezelver.model;
import javax.persistence.*;

@Entity
@Table(name = "ezelver_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}