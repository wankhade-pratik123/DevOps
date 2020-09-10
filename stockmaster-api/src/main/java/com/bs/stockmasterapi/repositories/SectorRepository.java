package com.bs.stockmasterapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.stockmasterapi.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

}
