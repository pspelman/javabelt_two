package com.philspelman.javabelt_two.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 3, max = 50, message = "Shows must be more than 2 characters")
    private String title;

    @Size(min = 2, max = 20)
    private String network;

//
//    @ManyToMany(mappedBy = "users")
//    private List<User> users;


    public Show() {
    }

    public Show(@Size(min = 3, max = 50, message = "Shows must be more than 2 characters") String title, @Size(min = 2, max = 20) String network) {
        this.title = title;
        this.network = network;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
