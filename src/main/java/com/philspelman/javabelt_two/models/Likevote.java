package com.philspelman.javabelt_two.models;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//this is the table that bridges the one to many relationship between the ideas and the users likevotes
// need to add the implements Persistable<ObjectIdentity> - in this case, it's the LikevoteIdentity as the key
@Entity
@Table(name = "likevotes")
public class Likevote implements Persistable<LikevoteIdentity> {

    //the EmbeddedId is the information about the IDENTITY of this instance (an association between a idea and a user
    // which, in this case, is a likevote (the type is LikevoteIdentity)
    @EmbeddedId
    private LikevoteIdentity id;

    @Column
    private boolean likevote;

    //add the manyToOne relationship between the userId and one for the ideaId
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ideaId")
    private Idea idea;

    @Transient
    private boolean update;

    public Likevote() {
    }

    public Likevote(boolean likevote, User user, Idea idea) {
        this.id = new LikevoteIdentity(user.getId(), idea.getId());
        this.likevote = likevote;
        this.user = user;
        this.idea = idea;
    }

    //need to override the methods from Persistable (isNew() and getid())
    @Override
    public boolean isNew() {
        return !this.update;
    }

    @Override
    public LikevoteIdentity getId() {
        return id;
    }

    public void setId(LikevoteIdentity id) {
        this.id = id;
    }

    public boolean getLikevote() {
        return likevote;
    }

    public void setLikevote(boolean likevote) {
        this.likevote = likevote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return String.format("%s: %s, by: %s like: " + this.likevote, this.id, this.idea.getTitle(), this.user.getUsername());
    }
}

