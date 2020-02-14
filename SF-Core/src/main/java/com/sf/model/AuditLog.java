package com.sf.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "affected_product_id")
    private Long productId;

    @Column(name = "action_by_user_id")
    private Long userId;

    @Column(name = "action_id")
    private Long actionId;

    @Column(name = "date")
    private String date;

}
