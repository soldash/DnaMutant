/**
 * 
 */
package com.magneto.dnamutant;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.magneto.dnamutant.model.Point;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.NorthEastDetection;
import com.magneto.dnamutant.service.SouthEastDetection;
import com.magneto.dnamutant.service.VerticalDetection;
import com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl;
import com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl;
import com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl;
import com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl;
import com.magneto.dnamutant.util.DnaMutantUtil;

/**
 * @author soldash
 *
 */
class NorthEastDetectorTest {
	
	private String[] dnaExampleHuman = {"ATGcga","cAGTGC","TTATtT","AGAcGG","GcGTcA","TcACTg"};
	private String[] dnaExampleMutant = {"ATGcga","cAGTGC","TTATtT","AGActG","GcGTcA","TctCTg"};
	private char [] [] humanDnaToChar;
	private char [] [] mutantDnaToChar;
	private NorthEastDetection northEastDetection = new NorthEastDetectionImpl();
	private int row = 5;
	private int column = 0;
	private Point point;
	private int secuenceChars;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		humanDnaToChar = DnaMutantUtil.structureDNAchain(dnaExampleHuman);
		secuenceChars = 4;
		point = new Point(humanDnaToChar, row, column);
		
		mutantDnaToChar = DnaMutantUtil.structureDNAchain(dnaExampleMutant);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl#movePoint(com.magneto.dnamutant.model.Point)}.
	 */
	@Test
	void testMovePoint_GivenPoint_ExpectedNoErrorAndMovePoint() {
		assertThatNoException().isThrownBy(() -> northEastDetection.movePoint(point));
		assertEquals(row - 1, point.row);
		assertEquals(column + 1, point.column);
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenInitialCoordinate_ExpectedTrue() {
		assertTrue(northEastDetection.CanContinue(point, secuenceChars));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenLastCoordinate_ExpectedFalse() {
		point.setCoordinates(0, 5);
		assertFalse(northEastDetection.CanContinue(point, secuenceChars));
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedFalse() {
		assertFalse(northEastDetection.searchSecuence(humanDnaToChar));
	}
	
	@Test
	void testSearchSecuence_GivenMutantDna_ExpectedTrue() throws Exception {

		assertTrue(northEastDetection.searchSecuence(mutantDnaToChar));
	}

}
