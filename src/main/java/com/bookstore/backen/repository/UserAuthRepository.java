package com.bookstore.backen.repository;

import com.bookstore.backen.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuth,Long>{

    @Query(value = "from UserAuth where userId = :userId")
    UserAuth getUserAuthByUserId(int userId);

    @Query(value = "from UserAuth where username = :username")
    UserAuth getUserAuthByUsername(String username);
}
