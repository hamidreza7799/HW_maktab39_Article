package domains.app;

import base.domains.BaseEntity;
import embededClasses.Address;

import javax.persistence.*;
import java.util.*;

@Entity
public class User extends BaseEntity<Integer> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true , nullable = false)
    private String username;
    @Column(name = "national_code" , nullable = false , columnDefinition = "char(10)" )
    private String nationalCode;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "creator" , cascade = CascadeType.ALL)
    private Set<Article> articleSet = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL) @JoinColumn(nullable = false)
    private Set<Role> userRoles = new HashSet<>();
    @Embedded
    private Address userAddress;

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

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", birthday=" + birthday +
                ", userAddress=" + userAddress +
                '}';
    }
}
