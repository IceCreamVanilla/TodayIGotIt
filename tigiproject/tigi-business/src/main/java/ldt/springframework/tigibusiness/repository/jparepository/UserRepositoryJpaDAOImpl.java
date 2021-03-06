package ldt.springframework.tigibusiness.repository.jparepository;

import ldt.springframework.tigibusiness.domain.User;
import ldt.springframework.tigibusiness.domain.security.Role;
import ldt.springframework.tigibusiness.repository.UserRepository;
import ldt.springframework.tigibusiness.security.encrypt.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/25/18
 */

@Repository
@Profile("jpadao-pure")
public class UserRepositoryJpaDAOImpl extends AbstractJpaDAORepository
        implements UserRepository {

    // =======================================
    // =           Injection Point           =
    // =======================================

    @Autowired
    private EncryptionService encryptionService;



    // =======================================
    // =            CRUD Methods             =
    // =======================================

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        List<User> result = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();

        return result;
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        User result = em.find(User.class, id);
        em.close();

        return result;
    }

    @Override
    public User saveOrUpdate(User user) {
        EntityManager em = emf.createEntityManager();

        User savedUser = null;
        try {
            savedUser = this.doInTransaction(em, () -> {
                if(user.getPassword() != null && !user.getPassword().isEmpty()){
                    user.setEncryptedPassowrd(encryptionService.encryptString(user.getPassword()));
                }

                return em.merge(user);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        try {
            this.doInTransaction(em, () -> {
                em.remove(em.find(User.class, id));
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
    }

    @Override
    public User checkUsernamePassword(String username, String password) {
        // get User with username
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
        query.setParameter("username", username);
        User getUser = null;
        try{
            getUser = (User) query.getSingleResult();
        }catch (Exception ex){
            return null;
        }

        // check password
        if(encryptionService.checkPassword(password, getUser.getEncryptedPassowrd())){
            return getUser;
        }

        return null;
    }

    @Override
    public User findByUserName(String username) {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("FROM User WHERE username = :username", User.class).getSingleResult();
    }

    @Override
    public List<User> findAllByCustomerFirstNameOrCustomerLastName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<User> findAllByCustomerFirstNameOrCustomerLastNameAndRolesContaining(String firstName, String lastname, Role role) {
        return null;
    }

    @Override
    public List<User> findAllByRolesContaining(Role role) {
        return null;
    }
}
