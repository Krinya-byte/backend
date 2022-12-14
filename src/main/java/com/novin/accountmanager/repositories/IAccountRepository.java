package com.novin.accountmanager.repositories;

import com.novin.accountmanager.domain.Account;
import com.novin.accountmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long> {

    public Account findByUserName(String ownerName);
}
