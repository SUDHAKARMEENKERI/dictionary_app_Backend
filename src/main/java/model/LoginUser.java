package model;

public class LoginUser {
    private String mobile;
    private String password;

    // Getters and setters
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public LoginUser(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }


}
