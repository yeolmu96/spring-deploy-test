package com.playdata.backend.monoproj.account.repository;

import com.playdata.backend.monoproj.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
