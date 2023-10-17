package kma.atweb.vn.project.model;

public class AccountInfo {
    private String userName;
    private String email;
    private String address;
    private String fullName;
    private boolean active;
    private String encryptedPassword;
    private String userRole;

    private int age;

    public AccountInfo() {
    }

    public AccountInfo(String userName, String email, String address, String fullName, boolean active, String encryptedPassword, String userRole) {
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.fullName = fullName;
        this.active = active;
        this.encryptedPassword = encryptedPassword;
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
