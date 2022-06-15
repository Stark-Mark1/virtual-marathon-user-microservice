package com.virtualmarathon.user.repository;


import com.virtualmarathon.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    public long getTotalUsers(){
        return (long) entityManager.createQuery("select count(user) from User user").getResultList().get(0);
    }

    public User getUserByEmail(String email){
        return entityManager.find(User.class,email);
    }

    public User addUser(User user){
        entityManager.persist(user);
        return user;
    }

    public User modifyUser(User user){
        if (getUserByEmail(user.getEmail())!=null){
            entityManager.merge(user);
            return user;
        }
        return null;
    }

    public List<User> getAllUsersSorted(){
        return entityManager.createQuery("Select user from User user order by user.totalPoints",User.class).getResultList();
    }

    public Page<User> getAllUsersSortedAndPaginated(Pageable pageable){
        List<User> userList= getAllUsersSorted();
        List<User> UserPage= userList.subList((int)pageable.getOffset(),
                Math.min((int)pageable.getOffset()+pageable.getPageSize(), userList.size()));
        return new PageImpl<User>(UserPage, pageable, userList.size());
    }
}
