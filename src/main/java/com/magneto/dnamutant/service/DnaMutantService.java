package com.magneto.dnamutant.service;

import javax.ws.rs.BadRequestException;

import org.springframework.stereotype.Service;

@Service
public interface DnaMutantService {

	public boolean isMutant(String[] dna) throws Exception;
	
	
}
