package com.novin.accountmanager.repositories;

import com.novin.accountmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//get user data
//post user data - registration
@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
    public User findByUserName(String userName);

    //public User save(User user);
}
