package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {
    private String mobile;
    private String password;

    public LoginUser(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }


}
