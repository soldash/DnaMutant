package com.magneto.dnamutant.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;

@Service
public abstract class DetectorImpl {

	private static final Logger log = LoggerFactory.getLogger(DetectorImpl.class);
	
	public boolean findSequence(Point point) {

		char currentCharacter = point.dnaStructure[point.row][point.column];

		int secuenceChars = 1;
		while (CanContinue(point, secuenceChars)) {
			movePoint(point); // voy al siguiente punto
			if (point.currentChar == currentCharacter) {
				secuenceChars++;
				if (secuenceChars == 4) {
					log.info(String.format("Mutant sequences found at: %s , %s", point.column , point.row));
					return true;
				}
			} else {
				secuenceChars = 1;
			}
			currentCharacter = point.currentChar;
		}

		return false;
	}

	protected abstract void movePoint(Point point);

	protected abstract boolean CanContinue(Point point, int secuenceChars);

}
