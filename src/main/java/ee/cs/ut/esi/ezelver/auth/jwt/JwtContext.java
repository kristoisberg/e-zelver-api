package ee.cs.ut.esi.ezelver.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class JwtContext {
    private Integer userId;
    private String name;
    private List<String> roles;
    private Date expiryTime;
}
