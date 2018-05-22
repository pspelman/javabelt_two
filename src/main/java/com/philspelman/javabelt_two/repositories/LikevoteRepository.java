package com.philspelman.javabelt_two.repositories;

import com.philspelman.javabelt_two.models.Likevote;
import com.philspelman.javabelt_two.models.LikevoteIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikevoteRepository extends CrudRepository<Likevote, LikevoteIdentity> {
    List<Likevote> findLikesByIdeaId(Long id);
}
