package com.philspelman.javabelt_two.models;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


//this is the table that bridges the one to many relationship between the shows and the users ratings
// need to add the implements Persistable<ObjectIdentity> - in this case, it's the RatingIdentity as the key
@Entity
@Table(name = "ratings")
public class Rating implements Persistable<RatingIdentity> {

    //the EmbeddedId is the information about the IDENTITY of this instance (an association between a show and a user
    // which, in this case, is a rating (the type is RatingIdentity)
    @EmbeddedId
    private RatingIdentity id;

    @Min(value = 1, message = "rating must be at least 1")
    @Max(value = 5, message = "rating cannot be greater than 5")
    private int rating;

    //add the manyToOne relationship between the userId and one for the showId
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("showId")
    private Show show;

    @Transient
    private boolean update;

    public Rating() {
    }

    public Rating(@Min(value = 1, message = "rating must be at least 1") @Max(value = 5, message = "rating cannot be greater than 5") int rating, User user, Show show) {
        this.id = new RatingIdentity(user.getId(), show.getId());
        this.rating = rating;
        this.user = user;
        this.show = show;
    }

    //need to override the methods from Persistable (isNew() and getid())
    @Override
    public boolean isNew() {
        return !this.update;
    }

    @Override
    public RatingIdentity getId() {
        return id;
    }

    public void setId(RatingIdentity id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}
