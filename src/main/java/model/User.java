package model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_login")
    private String login;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_role")
    private String role = "user";

    public User() {

    }

    public User(String name, String login, String password, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(long id, String name, String login, String password, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}