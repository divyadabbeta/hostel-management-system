package com.hms.repository.dao;

import com.hms.repository.dmo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
}
