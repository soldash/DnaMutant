package com.magneto.dnamutant.service;

import org.springframework.stereotype.Service;

@Service
public interface DnaStatsService {

	public Long getCountMutantDna();

	public Long getCountHumanDna();

	public Float getRatioDna();

}
