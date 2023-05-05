package com.example.week7springmvc.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String username;
    private String email;
    private String password;

    private String salt;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }


    public User() {
    }

    public boolean checkpassword(String password, String hashPassword){
        return BCrypt.checkpw(password, hashPassword);
    }
}
