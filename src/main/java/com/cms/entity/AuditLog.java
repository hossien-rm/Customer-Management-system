package com.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "SEQ_AUDIT_LOG", sequenceName = "SEQ_AUDIT_LOG", allocationSize = 1)
public class AuditLog {

    @Id
    @GeneratedValue(generator = "SEQ_AUDIT_LOG", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ENTITY_TYPE", nullable = false, length = 150)
    private String entityType;

    @Column(name = "ENTITY_ID", nullable = false)
    private Long entityId;

    @Column(name = "ACTION", nullable = false, length = 20)
    private String action;

    @Column(name = "ACTOR", nullable = false, length = 150)
    private String actor;

    @Column(name = "EVENT_AT", nullable = false)
    private LocalDateTime eventAt;
}
