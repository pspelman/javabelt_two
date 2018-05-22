package com.philspelman.javabelt_two.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    //user id will be automatically generated in the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //next is a username field (must be called "username" ) - it cannot be null
    @NotNull
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    //next is an email field. It is required
    @NotNull @Email
    @Size(min = 3, message = "Email must be at least 3 characters long")
    private String email;

    @OneToMany
    @JoinColumn(name = "user")
    private Set<Idea> ideas = new HashSet<Idea>();


    //Need a password, at least 5 characters
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;

    //set a confirmation field for the password that won't be stored in the db
    @Transient
    private String passwordConfirmation;

    //add created and updated fields
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }



    // Add the DB associations
    // relationship between ROLES and USERS - used for authentication-related things (i.e., permissions)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    // relationship between a USER and RATINGS
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Rating> ratings;



//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<Idea> ideas;


    //Add constructor
    public User() {
    }

    public User(@NotNull @Size(min = 3, message = "Username must be at least 3 characters long") String username, @NotNull @Size(min = 3, message = "Email must be at least 3 characters long") String email, @NotNull @Size(min = 1, message = "Name cannot be blank") String name, @Size(min = 5, message = "Password must be at least 5 characters") String password, String passwordConfirmation) {
        this.username = username;
        this.email = email;
//        this.name = name;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

//    public Set<Idea> getIdeas() {
//        return ideas;
//    }
//
//    public void setIdeas(Set<Idea> ideas) {
//        this.ideas = ideas;
//    }

    //Add getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }



}