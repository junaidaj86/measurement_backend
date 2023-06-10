package com.mjf.measurement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mjf.measurement.model.Pant;
@Repository
public interface PantRepository extends JpaRepository<Pant, Long>{
   
}
