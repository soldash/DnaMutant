package com.magneto.dnamutant.serviceimpl;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;

@Service
public class HorizontalDetectionImpl extends DetectorImpl implements HorizontalDetection {

	public boolean searchSecuence(char[][] dnaStructure) {
		// busca la secuencia a traves de las filas

		int datalengh = dnaStructure.length;

		Point point = new Point(dnaStructure, 0, 0);

		for (int r = 0; r < datalengh; r++) {
			// reviso sobre las fila sus columnas para encontrar la secuencia
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
		if ((point.column + 1 <= point.lastIndex) && (4 <= (secuenceChars + (point.lastIndex - point.column)))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void movePoint(Point point) {
		point.column++;
		point.currentChar = point.dnaStructure[point.row][point.column];
	}

}
