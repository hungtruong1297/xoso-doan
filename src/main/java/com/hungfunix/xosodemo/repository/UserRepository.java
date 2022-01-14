package com.hungfunix.xosodemo.repository;

import com.hungfunix.xosodemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findUserByStatus(int statusId);

    List<User> findAllByMailContains(String mail);

    List<User> findAllByPhoneContains(String phone);

    User findUserByMail(String mail);

    User findUserById(int id);

}
