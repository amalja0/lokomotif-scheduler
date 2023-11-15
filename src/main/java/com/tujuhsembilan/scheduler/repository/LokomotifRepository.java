package com.tujuhsembilan.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.scheduler.model.Lokomotif;

@Repository
public interface LokomotifRepository extends JpaRepository<Lokomotif, Long> {
    
}
