package com.playdata.backend.monoproj.account.repository;

import com.playdata.backend.monoproj.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositoryImpl extends JpaRepository<Account,Long> {
}
