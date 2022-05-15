package ee.cs.ut.esi.ezelver.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.List;

@Entity
@DiscriminatorValue("employee")
public class Employee extends User {

    @Column(name = "position")
    private String position;

    public Employee(String email, String password, String name, String position) {
        super(email, password, name);
        this.position = position;
    }

    public Employee() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public List<Role> getRoles() {
        return Collections.singletonList(Role.EMPLOYEE);
    }

}