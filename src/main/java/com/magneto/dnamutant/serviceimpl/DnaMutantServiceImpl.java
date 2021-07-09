package com.magneto.dnamutant.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.DnaLogModel;
import com.magneto.dnamutant.repository.DnaLogRepository;
import com.magneto.dnamutant.service.DnaMutantService;
import com.magneto.dnamutant.util.DnaMutantUtil;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.NorthEastDetection;
import com.magneto.dnamutant.service.SouthEastDetection;
import com.magneto.dnamutant.service.VerticalDetection;

@Service
public class DnaMutantServiceImpl implements DnaMutantService {

	@Autowired
	private HorizontalDetection horizontal;
	@Autowired
	private VerticalDetection vertical;
	@Autowired
	private SouthEastDetection southEast;
	@Autowired
	private NorthEastDetection northEast;
	
	@Autowired
	DnaLogRepository dnaLogRepository;

	@Override
	public boolean isMutant(String[] dna) throws Exception {

		char[][] dnaStructure = DnaMutantUtil.structureDNAchain(dna);

		DnaLogModel dnaLog = new DnaLogModel();
		dnaLog.setDna(DnaMutantUtil.convertToString(dna));

		if (horizontal.searchSecuence(dnaStructure) || vertical.searchSecuence(dnaStructure)
				|| southEast.searchSecuence(dnaStructure) || northEast.searchSecuence(dnaStructure)) {
			return returnOperation(true, dnaLog);
		}
		return returnOperation(false, dnaLog);
	}

	private boolean returnOperation(boolean returnOperation, DnaLogModel dnaLog) {
		dnaLog.setMutant(returnOperation);
		dnaLogRepository.save(dnaLog);
		return returnOperation;
	}

}
