package com.philspelman.javabelt_two.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class UserIdeaIdentity implements Serializable {

    @NotNull
    @Size(max = 20)
    private Long userId;

    @NotNull
    @Size(max = 20)
    private Long ideaId;

    public UserIdeaIdentity(@NotNull @Size(max = 20) Long userId, @NotNull @Size(max = 20) Long ideaId) {
        this.userId = userId;
        this.ideaId = ideaId;
    }

    @Override
    public boolean equals(Object o ) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserIdeaIdentity that = (UserIdeaIdentity) o;

        if(!userId.equals(that.userId)) return false;
        return ideaId.equals(that.ideaId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + ideaId.hashCode();
        return result;
    }


}
