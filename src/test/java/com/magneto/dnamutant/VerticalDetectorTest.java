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
import com.magneto.dnamutant.service.VerticalDetection;
import com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl;
import com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl;
import com.magneto.dnamutant.util.DnaMutantUtil;

/**
 * @author soldash
 *
 */
class VerticalDetectorTest {
	
	private String[] dnaExampleHuman = {"ATCgAg","CaGCAa","TTcTtg","ACAaGC","CttCcA","TagTcg"};
	private String[] dnaExampleMutant = {"ATGcga","cAGTGC","TTaTgT","AGAAGG","CcCcTA","TCACTg"};
	private char [] [] humanDnaToChar;
	private char [] [] mutantDnaToChar;
	private VerticalDetection verticalDetection = new VerticalDetectionImpl();
	private int row = 0;
	private Point point;
	private int secuenceChars;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		humanDnaToChar = DnaMutantUtil.structureDNAchain(dnaExampleHuman);
		secuenceChars = 4;
		point = new Point(humanDnaToChar, row, 0);
		mutantDnaToChar = DnaMutantUtil.structureDNAchain(dnaExampleMutant);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl#movePoint(com.magneto.dnamutant.model.Point)}.
	 */
	@Test
	void testMovePoint_GivenPoint_ExpectedNoErrorAndMovePoint() {
		assertThatNoException().isThrownBy(() -> verticalDetection.movePoint(point));
		assertEquals(row + 1, point.row);
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenInitialCoordinate_ExpectedTrue() {
		assertTrue(verticalDetection.CanContinue(point, secuenceChars));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenLastCoordinate_ExpectedFalse() {
		point.setCoordinates(5, 5);
		assertFalse(verticalDetection.CanContinue(point, secuenceChars));
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedFalse() {
		assertFalse(verticalDetection.searchSecuence(humanDnaToChar));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedTrue() {
		assertTrue(verticalDetection.searchSecuence(mutantDnaToChar));
	}

}
