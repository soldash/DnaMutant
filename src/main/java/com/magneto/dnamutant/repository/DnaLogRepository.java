package com.magneto.dnamutant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magneto.dnamutant.model.DnaLogModel;

/**
 * 
 * @author soldash
 *
 */
@Repository
public interface DnaLogRepository extends JpaRepository<DnaLogModel, Long> { 

}
