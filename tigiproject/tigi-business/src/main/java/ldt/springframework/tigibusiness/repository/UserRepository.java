package ldt.springframework.tigibusiness.repository;

import ldt.springframework.tigibusiness.domain.Course;
import ldt.springframework.tigibusiness.domain.LearnTracking;
import ldt.springframework.tigibusiness.domain.User;
import ldt.springframework.tigibusiness.domain.security.Role;

import java.util.List;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 7/25/18
 */


public interface UserRepository extends CRUDRepository<User>{
    User checkUsernamePassword(String usermame, String password);

    User findByUserName(String username);

    List<User> findAllByCustomerFirstNameOrCustomerLastName(String firstName, String lastName);

    List<User> findAllByCustomerFirstNameOrCustomerLastNameAndRolesContaining(String firstName, String lastname, Role role);

    List<User> findAllByRolesContaining(Role role);
}
