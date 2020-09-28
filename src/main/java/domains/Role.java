package domains;

import base.domains.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends BaseEntity<Integer> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
    @ManyToMany(mappedBy = "userRoles")
    private Set<User> roleUsers = new HashSet<>();
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(Set<User> roleUsers) {
        this.roleUsers = roleUsers;
    }
}
