package ee.cs.ut.esi.ezelver.entities;

import javax.persistence.*;

@Entity
@Table(name = "ezelver_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public Employee() {

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}