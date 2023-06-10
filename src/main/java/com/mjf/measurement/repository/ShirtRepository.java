package com.mjf.measurement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mjf.measurement.model.Shirt;

@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Long>{
    
}
