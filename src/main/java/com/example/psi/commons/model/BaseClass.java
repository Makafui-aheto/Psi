package com.example.psi.commons.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseClass implements Serializable {

    @Column(columnDefinition = "TIMESTAMP", name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime createdAt;


    @Column(columnDefinition = "TIMESTAMP", name = "modified_at", nullable = false)
    @LastModifiedDate
    protected LocalDateTime modifiedAt;

}
