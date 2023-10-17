package kma.atweb.vn.project.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EnableAutoConfiguration
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    @Id
    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "Active", length = 1, nullable = false)
    private boolean active;

    @Column(name = "User_Role", length = 20, nullable = false)
    private String userRole;

    @Column(name = "email", length = 256, nullable = false)
    private String email;

    @Column(name = "address", length = 256, nullable = false)
    private String address;

    @Column(name = "full_name", length = 256, nullable = false)
    private String fullName;

    @Column(name = "age", nullable = false)
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "[" + this.userName + "," + this.encrytedPassword + "," + this.userRole + "]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
