package com.tujuhsembilan.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Lokomotif {
    @Id
    private String kodeLoko;

    @Column
    private String namaLoko;

    @Column
    private String dimensiLoko;

    @Column
    private String status;

    @Column
    private String createdDate;
    
}
