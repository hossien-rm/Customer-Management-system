package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_BASE_EINTITY", sequenceName = "SEQ_CUSTOMER", allocationSize = 1)
public class Customer extends BaseEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NATIONAL_CODE")
    private String nationalCode;

    @OneToMany(mappedBy = "customer"
            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Server> serves;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer"
            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Factor> factors;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "USER_ID", unique = true)
    private Users user;
}
