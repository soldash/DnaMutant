package com.magneto.dnamutant.service;

import org.springframework.stereotype.Service;

import com.magneto.dnamutant.model.Point;

@Service
public interface NorthEastDetection {
	
	public boolean searchSecuence(char[][] dnaStructure);
	
	public boolean CanContinue(Point point, int secuenceChars);
	
	public void movePoint(Point point);

}
