package domains;

import base.domains.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class User extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true , nullable = false)
    private String username;
    @Column(name = "national_code" , nullable = false , columnDefinition = "char(10)" )
    private String nationalCode;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(nullable = false)
    private String password;
    //private Set<Article> articleSet;
    @ManyToMany
    @JoinColumn(nullable = false)
    private List<Role> userRoles;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

}
