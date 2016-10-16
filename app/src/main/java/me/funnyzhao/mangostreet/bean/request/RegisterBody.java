package me.funnyzhao.mangostreet.bean.request;

/**
 * Created by funnyzhao .
 * 注册信息
 */

public class RegisterBody {
    private String username; //用户名
    private String password; //密码
    private String email;    //邮箱
    public RegisterBody(){}

    public RegisterBody(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
