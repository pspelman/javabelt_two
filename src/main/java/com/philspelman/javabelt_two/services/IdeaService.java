package com.philspelman.javabelt_two.services;

import com.philspelman.javabelt_two.models.Idea;
import com.philspelman.javabelt_two.models.Likevote;
import com.philspelman.javabelt_two.models.LikevoteIdentity;
import com.philspelman.javabelt_two.repositories.IdeaRepository;
import com.philspelman.javabelt_two.repositories.LikevoteRepository;
import com.philspelman.javabelt_two.repositories.UserRepository;
import org.hibernate.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

@Service
public class IdeaService {

    //initiate the repositories
    private UserRepository userRepository;
    private IdeaRepository ideaRepository;

    // make sure to address this, delete if NOT including likevotes
    private LikevoteRepository likevoteRepository;

    //import the UserService to access user info
    private UserService userService;


    public IdeaService(UserRepository userRepository, IdeaRepository ideaRepository, LikevoteRepository likevoteRepository, UserService userService) {
        this.userRepository = userRepository;
        this.ideaRepository = ideaRepository;
        this.likevoteRepository = likevoteRepository;
        this.userService = userService;

        if (this.userRepository.findAll().size() < 1 && this.ideaRepository.findAll().size() < 1) {
            addSampleIdeas();
            addNewUsers();
        }

    }


    //add sample idea
    private void addSampleIdeas() {
        this.ideaRepository.save(new Idea("idea one", (long) 0));
        this.ideaRepository.save(new Idea("idea two", (long) 0));
        this.ideaRepository.save(new Idea("idea three", (long) 0));
    }

    private void addNewUsers() {
        System.out.println("pretending to add users");
//        this.userRepository.save(new User("john"));
    }

    //create new Idea
    public boolean addIdea(Idea idea) {
        if (isUnique(idea, true)) {
            ideaRepository.save(idea);
            return true;
        }
        return false;
    }

    //show all Ideas
    public List<Idea> allIdeas() {
        return ideaRepository.findAll();
    }

    //find by id
    public Idea findIdeaById(Long id) {
        return ideaRepository.findOneById(id);
    }

    public Idea getIdeaById(Long id) {
        return this.ideaRepository.findOneById(id);
    }

    //update
    public int updateIdea(Idea idea) {
        System.out.println("Need to try to update idea!");
        System.out.println("received idea id: " + idea.getId());
        System.out.println("and description: " + idea.getTitle());

//        try {
//            //insert actual update fields
//            int affected = ideaRepository.updateIdea(idea.getTitle(), idea.getId());
//
//        } catch (Exception err) {
//            System.out.println("error!");
//
//        }
////        if (isUnique(idea, false)) {
////            ideaRepository.updateIdea(idea.getTitle(), idea.getId()); //insert actual update fields
//////
////            return true;
////        }
////        return false;
        return ideaRepository.updateIdea(idea.getTitle(), idea.getId());
    }

    //delete
    public void deleteIdeaById(Long id) {
        ideaRepository.deleteById(id);
    }

    // add a likevote by a user
    // must catch DataIntegrityViolationException because this is using the Likevote class, which implements persistable
    public boolean toggleLikevoteByUsername(Long ideaId, String username) {

        List<Likevote> existingVote = likevoteRepository.findAllByIdeaAndUser(getIdeaById(ideaId), userRepository.findByUsername(username));
        System.out.println("found existing vote: " + existingVote.size());


        try {
            Likevote possibleNewLikevote = new Likevote(true, userRepository.findByUsername(username), getIdeaById(ideaId));
            likevoteRepository.save(possibleNewLikevote);
            return true;
        } catch (DataIntegrityViolationException e) {

            System.out.println("Exception caught deleting like...");
            Likevote upVote = existingVote.get(0);
            likevoteRepository.delete(existingVote.get(0));
//            likevoteRepository.save((Likevote) (existingVote.get(0))).setLikevote(false);

            return true;

        }
    }
//
//            if (existingVote.size() == 1) {
//                System.out.println("size was 1, getting the record to delete");
//            } else {
//                Likevote possibleNewLikevote = new Likevote(true, userRepository.findByUsername(username), getIdeaById(ideaId));
//                likevoteRepository.save(possibleNewLikevote);
//            }
//            return false;
//        }

            //delete it

//            Long userId = upVote.getUser().getId();
//
//            int result;
//
//            System.out.println("existing vote: " + upVote.toString());
//            Likevote modifyLikevote = new Likevote(false, upVote.getUser(), upVote.getIdea());

//            try {
//
////            Likevote possibleNewLikevote = new Likevote(true, userRepository.findByUsername(username), getIdeaById(ideaId));
////            likevoteRepository.save(possibleNewLikevote);
//                return true;
//            } catch (DataIntegrityViolationException e) {
//                System.out.println("Exception caught deleting like...");
////                System.out.println("Exception caught saving like...it must already exist...so TOGGLE!");
//
//
////                if (upVote.getLikevote()) {
////                    System.out.println("the likevote WAS true (meaning liked)...setting to false");
////
////                    upVote.setLikevote(false);
////                    return updateLikevote(upVote);
////
//////                    likevoteRepository.updateLikevote(false, ideaRepository.findOneById(ideaId), userRepository.findById(userId).get() );
////                } else {
////                    upVote.setLikevote(true);
////                    return updateLikevote(upVote);
//////                    likevoteRepository.updateLikevote(true, ideaRepository.findOneById(ideaId), userRepository.findById(userId).get() );
//////                    likevoteRepository.updateLikevote(true, ideaId, userId );
////                }
//
////                int result = likevoteRepository.updateLikevote(upVote.getLikevote(), upVote.getUser().getId(), upVote.getIdea().getId());
////                return ideaRepository.updateIdea(idea.getTitle(), idea.getId());
//                return false;
//
//            }
//        }
//    }

//        }
//        //if there is NO record, create one
//        if (likevoteRepository.findLikevoteByIdeaIdAndUserId()) {
//            System.out.println("found something: " + likevoteRepository.findLikevoteByIdeaIdAndUserId());
//        } else {
//            System.out.println("found nothing");
//
//        }
//        return false;
            //if there IS a record, then toggle it

//
//        try {
//            System.out.println("TRYING TO CREATE NEW LIKEVOTE!!!");
//            //FIXME: Make a new likevote to save
////            Likevote attemptToAddLikevote = new Likevote(value, userRepository.findByUsername(username), getIdea(ideaId));
////            likevoteRepository.save(attemptToAddLikevote);
//            return true;
//        } catch (DataIntegrityViolationException err) {
//            System.out.println("error adding likevote: " + err.getMessage());
//            return false;
//        }

    private boolean updateLikevote(Likevote upVote) {
        try {
            likevoteRepository.save(upVote);
            return true;
        } catch (Exception err) {
            System.out.println("Error trying to save / update");
            return false;
        }
    }


    //test for uniqueness (e.g., a tv show)
    boolean isUnique(Idea idea, boolean isNew) {
        Idea foundIdea = ideaRepository.findOneByTitle(idea.getTitle());
        if (foundIdea != null) {
            //means an idea with this title was found
            if (isNew) return false;
            //if it's the SAME idea (so updating that idea)
            else if (!idea.getId().equals(foundIdea.getId())) return false;
        }
        // Test the database to see if a idea with this name/title has already been added with the same column value
        // e.g., idea title and network should be unique
        return true;
    }


    public List<Likevote> getLikeVotesForSelectedIdea(Long id) {
        System.out.println("getting likes list");
        return likevoteRepository.findLikesByIdeaId(id);
    }
}



//
//        EntityManagerFactory factory = new EntityManagerFactory() {
//@Override
//public EntityManager createEntityManager() {
//        return null;
//        }
//
//@Override
//public EntityManager createEntityManager(Map map) {
//        return null;
//        }
//
//@Override
//public EntityManager createEntityManager(SynchronizationType synchronizationType) {
//        return null;
//        }
//
//@Override
//public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
//        return null;
//        }
//
//@Override
//public CriteriaBuilder getCriteriaBuilder() {
//        return null;
//        }
//
//@Override
//public Metamodel getMetamodel() {
//        return null;
//        }
//
//@Override
//public boolean isOpen() {
//        return false;
//        }
//
//@Override
//public void close() {
//
//        }
//
//@Override
//public Map<String, Object> getProperties() {
//        return null;
//        }
//
//@Override
//public Cache getCache() {
//        return null;
//        }
//
//@Override
//public PersistenceUnitUtil getPersistenceUnitUtil() {
//        return null;
//        }
//
//@Override
//public void addNamedQuery(String name, Query query) {
//
//        }
//
//@Override
//public <T> T unwrap(Class<T> cls) {
//        return null;
//        }
//
//@Override
//public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
//
//        }
//        };
//        EntityManager entityManager = factory.createEntityManager();
//        Session session = entityManager.unwrap( Session.class );
//        int updatedEntities = entityManager.createQuery(
//        "update Likevote l set p.likevote = :newVote where l.idea_id = :ideaId and l.user_id = :userId" )
//        .setParameter( "newVote", false )
//        .setParameter( "ideaId", 1 )
//        .setParameter( "userId", 2 )
//        .executeUpdate();
//
////                Session session = new Session();
////                int updatedEntities = session.createQuery(
////                        "update Person " +
////                                "set name = :newName " +
////                                "where name = :oldName" )
////                        .setParameter( "oldName", oldName )
////                        .setParameter( "newName", newName )
////                        .executeUpdate();
