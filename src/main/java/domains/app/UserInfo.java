package domains.app;

import embededClasses.Address;

import java.util.Set;

public class UserInfo {
    private String username;
    private Address userAddress;
    private Set<Role> userRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", userAddress=" + userAddress +
                ", userRoles=" + userRoles +
                '}';
    }
}
