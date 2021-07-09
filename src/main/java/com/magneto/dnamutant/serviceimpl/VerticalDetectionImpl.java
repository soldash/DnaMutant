package com.magneto.dnamutant.serviceimpl;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.VerticalDetection;

/**
 * 
 * @author soldash
 *
 */
@Service
public class VerticalDetectionImpl extends DetectorImpl implements VerticalDetection {

	public boolean searchSecuence(char[][] dnaStructure) {

		int datalengh = dnaStructure.length;

		Point point = new Point(dnaStructure, 0, 0);

		for (int c = 0; c < datalengh; c++) {
			// reviso sobre las fila sus columnas para encontrar la secuencia
			point.setCoordinates(0, c);
			boolean isMutant = findSequence(point);
			if (isMutant) {
				return true;
			}

		}

		return false;
	}

	@Override
	public boolean CanContinue(Point point, int secuenceChars) {
		if ((point.row + 1 <= point.lastIndex) && (4 <= (secuenceChars + (point.lastIndex - point.row)))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void movePoint(Point point) {
		point.row++;
		point.currentChar = point.dnaStructure[point.row][point.column];
	}

}
