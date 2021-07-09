package com.magneto.dnamutant.serviceimpl;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.NorthEastDetection;

/**
 * 
 * @author soldash
 *
 */
@Service
public class NorthEastDetectionImpl extends DetectorImpl implements NorthEastDetection {

	public boolean searchSecuence(char[][] dnaStructure) {
		int datalengh = dnaStructure.length;

		Point point = new Point(dnaStructure, 0, 0);

		for (int c = 0; c < datalengh; c++) {

			point.setCoordinates(datalengh - 1, c);
			boolean isMutant = findSequence(point);
			if (isMutant) {
				return true;
			}
		}
		for (int r = datalengh - 1; r > 0; r--) {
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
		if ((point.column + 1 <= point.lastIndex) && (point.row - 1 >= 0)
				&& (4 <= (secuenceChars + (point.lastIndex - point.column))) && (4 <= (secuenceChars + (point.row)))) {
			return true;
		}
		return false;
	}

	@Override
	public void movePoint(Point point) {
		point.row--;
		point.column++;
		point.currentChar = point.dnaStructure[point.row][point.column];
	}

}
