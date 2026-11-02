package com.cms.repository;


import com.cms.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Query("select s FROM Server s Where s.customer.user.username=:username ")
    List<Server> findServerByUsername(@Param("username") String username);

}
