package com.mjf.repository;

import com.mjf.entity.Pant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PantRepository extends JpaRepository<Pant, Long>{

}