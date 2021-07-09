package com.magneto.dnamutant.serviceimpl;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;

@Service
public class HorizontalDetectionImpl extends DetectorImpl implements HorizontalDetection {

	/*
	 * 
	 * @see com.magneto.dnamutant.service.HorizontalDetection#searchSecuence(char[][])
	 */
	public boolean searchSecuence(char[][] dnaStructure) {


		int datalengh = dnaStructure.length;

		Point point = new Point(dnaStructure, 0, 0);

		for (int r = 0; r < datalengh; r++) {
			point.setCoordinates(r, 0);
			boolean isMutant = findSequence(point);
			if (isMutant) {
				return true;
			}

		}

		return false;
	}
	 /**
	  * check the conditions to continue
	  */
	@Override
	public boolean CanContinue(Point point, int secuenceChars) {
		if ((point.column + 1 <= point.lastIndex) && (4 <= (secuenceChars + (point.lastIndex - point.column)))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * specify the way to move the point
	 */
	@Override
	public void movePoint(Point point) {
		point.column++;
		point.currentChar = point.dnaStructure[point.row][point.column];
	}

}
