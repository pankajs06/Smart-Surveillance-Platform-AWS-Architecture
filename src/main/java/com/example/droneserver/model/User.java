package com.example.droneserver.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String name;
    private String email;
    private String roles; // comma separated
    private Instant createdAt = Instant.now();

    public User() {}
    // getters/setters
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getUsername(){return username;} public void setUsername(String u){this.username=u;}
    public String getName(){return name;} public void setName(String n){this.name=n;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getRoles(){return roles;} public void setRoles(String r){this.roles=r;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant i){this.createdAt=i;}
}
