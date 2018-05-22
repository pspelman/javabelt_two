package com.philspelman.javabelt_two.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


//I need a rating identity as an embeddable because it is used by the logic to understand how to identify
// the individual User - ShowRating pairs on the third table (holding the many-to-many relationship between
// users and the shows they rated
// this ONLY represents the IDENTITY of those rows, NOT the actual rating, which is stored in the ratings table
@Embeddable
public class RatingIdentity implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long showId;

    public RatingIdentity() {
    }

    public RatingIdentity(@NotNull Long userId, @NotNull Long showId) {
        this.userId = userId;
        this.showId = showId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }


    //need to override an equals() method AND a hashCode method
    // so that a new RatingIdentity can be compared to others (so that a user can't DOUBLE-RATE a show)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatingIdentity that = (RatingIdentity) o;

        if (!userId.equals(that.userId)) return false;
        return showId.equals(that.showId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + showId.hashCode();
        return result;
    }

}
