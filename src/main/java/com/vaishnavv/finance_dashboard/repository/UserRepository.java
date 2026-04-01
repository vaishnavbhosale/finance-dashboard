package com.vaishnavv.finance_dashboard.repository;

import com.vaishnavv.finance_dashboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
