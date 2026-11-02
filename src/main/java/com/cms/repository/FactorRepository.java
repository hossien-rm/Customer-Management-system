package com.cms.repository;

import com.cms.entity.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FactorRepository extends JpaRepository<Factor, Long> {

    @Query("select f from Factor f where f.customer.user.username=:username ")
    List<Factor> findFactorByUsername(@Param("username") String username);

}
