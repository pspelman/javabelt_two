package com.philspelman.javabelt_two.repositories;

import com.philspelman.javabelt_two.models.Idea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
    List<Idea> findAll();

    Idea findOneById(Long id);

    //comment out or change for identifying string
    Idea findOneByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Idea d SET d.title=?1 WHERE d.id=?2")
    int updateIdea(String title, Long id);



    // this needs to have @Transactional and @Modifying annotations in order to change existing DB contents
    // comment out or change title and network to fields for the model being used
//    @Transactional
//    @Modifying
//    @Query("UPDATE Show s SET s.title=?1, s.network=?2 WHERE s.id=?3")
//    void updateShow(String title, String network, Long id);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Idea s SET s.title=?1 WHERE s.id=?2")
//    void updateIdea(String title, Long id);

}
