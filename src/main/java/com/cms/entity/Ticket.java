package com.cms.entity;

import com.cms.constant.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TICKET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_BASE_EINTITY", sequenceName = "SEQ_TICKET", allocationSize = 1)
public class Ticket extends BaseEntity {

    @CreatedDate
    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "UPDATE_AT")
    private LocalDateTime updateAt;

    @Column(name = "TITLE")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private TicketStatus status;

    @Column(name = "DESCRIPTIONS")
    private String descriptions;

    @Column(name = "REPLY")
    private String reply;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    private Users user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

}
