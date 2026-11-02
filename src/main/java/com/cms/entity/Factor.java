package com.cms.entity;


import com.cms.constant.FactorPaymentType;
import com.cms.constant.FactorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "FACTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_BASE_EINTITY", sequenceName = "SEQ_FACTOR", allocationSize = 1)
public class Factor extends BaseEntity {


    @Column(name = "FACTOR_DATE")
    private LocalDate currentDate = LocalDate.now();

    @Column(name = "AMOUNT")
    private Double amount;


    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private FactorType type;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private FactorPaymentType paymentType;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "server_id")
    private Server server;


}
