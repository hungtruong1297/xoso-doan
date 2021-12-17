package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM [USER] U WHERE U.USER_ROLE = 'USER'",
        nativeQuery = true)
    Collection<User> findAllByRoleUser();

    @Query("SELECT U.userMail FROM User U")
    Collection<User> findAlByRoleUserJPQL();

}
