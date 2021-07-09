/**
 * 
 */
package com.magneto.dnamutant.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.DnaLogModel;
import com.magneto.dnamutant.repository.DnaLogRepository;
import com.magneto.dnamutant.service.DnaStatsService;

/**
 * @author soldash
 *
 */
@Service
public class DnaStatsServiceImpl implements DnaStatsService {

	/* 
	 * @see com.magneto.dnamutant.service.DnaStatsService#getCountMutantDna()
	 */
	private Long countMutantDna;
	private Long countHumanDna;
	
	@Autowired
	private DnaLogRepository dnaLogRepository;
	
	@Override
	public Long getCountMutantDna() {
		countMutantDna = getCountByType(true);
		return countMutantDna;
	}

	/* 
	 * @see com.magneto.dnamutant.service.DnaStatsService#getCountHumanDna()
	 */
	@Override
	public Long getCountHumanDna() {
		countHumanDna = getCountByType(false);
		return countHumanDna;
	}

	/* 
	 * @see com.magneto.dnamutant.service.DnaStatsService#getRatioDna()
	 */
	@Override
	public Float getRatioDna() {
		return Float.valueOf((float) countMutantDna) / ((float) countHumanDna);
	}
	
	private Long getCountByType(boolean isMutant) {
		DnaLogModel dnaLogModelExample =new DnaLogModel();
		dnaLogModelExample.setMutant(isMutant);
		Example<DnaLogModel> exampleDnaLogModel = Example.of(dnaLogModelExample);
		return dnaLogRepository.count(exampleDnaLogModel);
	}

}
