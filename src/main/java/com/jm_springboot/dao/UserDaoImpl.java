package com.jm_springboot.dao;

import com.jm_springboot.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Repository
//public interface UserDaoImpl extends JpaRepository<Role, Long> {
//}

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }
    @Override
    public void addUser(User user) { entityManager.persist(user); }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User editUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        return (User) entityManager
                .createQuery("from User u  inner JOIN FETCH u.roles as roles WHERE u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
}
