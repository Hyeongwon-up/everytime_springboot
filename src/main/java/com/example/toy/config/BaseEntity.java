package com.example.toy.config;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false, columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updated_at;

}
