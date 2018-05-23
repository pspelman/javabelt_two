package com.philspelman.javabelt_two.repositories;

import com.philspelman.javabelt_two.models.Idea;
import com.philspelman.javabelt_two.models.Likevote;
import com.philspelman.javabelt_two.models.LikevoteIdentity;
import com.philspelman.javabelt_two.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LikevoteRepository extends CrudRepository<Likevote, LikevoteIdentity> {
    List<Likevote> findLikesByIdeaId(Long id);

//    Likevote findByUserAndIdeaId(User byUsername, Idea ideaById);

    List<Likevote> findAllByIdeaAndUser(Idea idea, User user);





    @Transactional
    @Modifying
    @Query(value = " UPDATE likevotes t SET t.likevote = ?1 WHERE t.idea_id = ?2 AND t.user_id = ?3", nativeQuery = true)
    int updateLikevote(boolean vote, Long ideaId, Long userId);



//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Likevote d SET d.likevote=?1 WHERE d.likevoteIdentity=?2")
//    int updateLikevote(boolean vote, LikevoteIdentity id);


//    boolean findLikevoteByIdeaIdAndUserId();

//    int findLikevoteByUsername(String username);
}
