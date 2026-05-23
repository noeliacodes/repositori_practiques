package ud8.users.domain.entity;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
//    private String passwordHash;

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }

//    public User(int id, String name, String email, String passwordHash) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.passwordHash = passwordHash;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}