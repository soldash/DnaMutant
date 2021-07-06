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
	HorizontalDetection horizontal;

	@Autowired
	VerticalDetection vertical;

	@Autowired
	SouthEastDetection southEast;

	@Autowired
	NorthEastDetection northEast;

	@Autowired
	DnaLogRepository dnaLogRepository;

	@Override
	public boolean isMutant(String[] dna) throws Exception {

		char[][] dnaStructure = DnaMutantUtil.structureDNAchain(dna);

		int datalengh = dnaStructure.length;

		DnaLogModel dnaLog = new DnaLogModel();
		dnaLog.setDna(convertToString(dna));

		if (horizontal.searchSecuence(dnaStructure)) {
			return returnOperation(true, dnaLog);
		} else if (vertical.searchSecuence(dnaStructure)) {
			return returnOperation(true, dnaLog);
		} else if (southEast.searchSecuence(dnaStructure)) {
			return returnOperation(true, dnaLog);
		} else if (northEast.searchSecuence(dnaStructure)) {
			return returnOperation(true, dnaLog);
		}
		return returnOperation(false, dnaLog);
	}

	private boolean returnOperation(boolean returnOperation, DnaLogModel dnaLog) {
		dnaLog.setMutant(returnOperation);
		dnaLogRepository.save(dnaLog);
		return returnOperation;
	}

	private String convertToString(String[] arrayString) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			sb.append(arrayString[i].toUpperCase());
			sb.append(" , ");
		}
		return sb.toString();
	}

}
