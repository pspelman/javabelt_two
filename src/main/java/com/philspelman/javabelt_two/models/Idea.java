package com.philspelman.javabelt_two.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ideas")
public class Idea {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=3, message="Ideas must be at least 3 characters")
    private String title;

    @Column
    @NotNull
    private Long addedByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User addedBy;

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    @OneToMany(mappedBy="idea", fetch=FetchType.LAZY)
    private List<Likevote> likevotes;


    public Idea(@NotNull @Size(min = 1, message = "Please specify a title") String title, @NotNull User addedBy) {
        this.title = title;
        this.addedBy = addedBy;
        this.addedByUserId = addedBy.getId();
    }

    public Idea(@NotNull @Size(min = 1, message = "Please specify a title") String title, Long addedByUserId) {
        this.title = title;
        this.addedByUserId = addedByUserId;
    }



    public Idea() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAddedByUserId() {
        return addedByUserId;
    }

    public void setAddedByUserId(Long addedByUserId) {
        this.addedByUserId = addedByUserId;
    }



    public List<Likevote> getLikevotes() {
        return likevotes;
    }

    public void setLikevotes(List<Likevote> likevotes) {
        this.likevotes = likevotes;
    }

    public List<Likevote> getlikevotes() {
        return likevotes;
    }

    public void setlikevotes(List<Likevote> likevotes) {
        this.likevotes = likevotes;
    }

    public int getTotalLikevotes() {
        if (likevotes.size() == 0) return 0;
        int sum = 0;
        for (Likevote vote : likevotes) {
            if (vote.getLikevote()) {
                sum += 1;
            }
        }
        return sum;
    }


    @Override
    public String toString() {
        return String.format("Username: %s %nTitle: %s%n", getAddedBy().getUsername(), getTitle());
    }


}
