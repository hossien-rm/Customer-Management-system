package com.cms.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@Setter
@Getter
@SequenceGenerator(name = "SEQ_BASE_EINTITY", sequenceName = "SEQ_USERS", allocationSize = 1)
public class Users extends BaseEntity {

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

}
