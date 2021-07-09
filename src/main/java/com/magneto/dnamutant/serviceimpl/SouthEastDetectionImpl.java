package com.magneto.dnamutant.serviceimpl;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.SouthEastDetection;

/**
 * 
 * @author soldash
 *
 */
@Service
public class SouthEastDetectionImpl extends DetectorImpl implements SouthEastDetection {

	public boolean searchSecuence(char[][] dnaStructure) {
		int datalengh = dnaStructure.length;

		Point point = new Point(dnaStructure, 0, 0);

		for (int c = 0; c < datalengh - 3; c++) {
			point.setCoordinates(0, c);
			boolean isMutant = findSequence(point);
			if (isMutant) {
				return true;
			}

		}
		for (int r = 1; r < datalengh - 3; r++) {
			point.setCoordinates(r, 0);
			boolean isMutant = findSequence(point);
			if (isMutant) {
				return true;
			}

		}

		return false;
	}

	@Override
	public boolean CanContinue(Point point, int secuenceChars) {
		if ((point.column + 1 <= point.lastIndex) && (point.row + 1 <= point.lastIndex)
				&& (4 <= (secuenceChars + (point.lastIndex - point.column)))
				&& (4 <= (secuenceChars + (point.lastIndex - point.row)))) {
			return true;
		}
		return false;
	}

	@Override
	public void movePoint(Point point) {
		point.row++;
		point.column++;
		point.currentChar = point.dnaStructure[point.row][point.column];
	}

}
