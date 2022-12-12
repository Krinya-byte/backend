package com.novin.accountmanager.repositories;

import com.novin.accountmanager.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
//get user data
//post user data - registration
@Repository
public interface IUserRepository {
    public List<User> findAll();
    public User findById(Long id);
    public User findByUserName(String userName);
}
