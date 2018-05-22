package com.philspelman.javabelt_two.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class LikevoteIdentity implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long ideaId;

    public LikevoteIdentity() {
    }

    public LikevoteIdentity(@NotNull Long userId, @NotNull Long ideaId) {
        this.userId = userId;
        this.ideaId = ideaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Long ideaId) {
        this.ideaId = ideaId;
    }


    //need to override an equals() method AND a hashCode method
    // so that a new LikevoteIdentity can be compared to others (so that a user can't DOUBLE-LIKE a idea)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikevoteIdentity that = (LikevoteIdentity) o;

        if (!userId.equals(that.userId)) return false;
        return ideaId.equals(that.ideaId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + ideaId.hashCode();
        return result;
    }

}

