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
import com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl;
import com.magneto.dnamutant.util.DnaMutantUtil;

/**
 * @author soldash
 *
 */
class HorizontalDetectorTest {
	
	private String[] dnaExampleHuman = {"ATCgAg","CaGCAa","TTcTtg","ACAaGC","CttCcA","TagTcg"};
	private String[] dnaExampleMutant = {"ATGcga","cAGTGC","TTaTgT","AGAAGG","CcCcTA","TCACTg"};
	private char [] [] humanDnaToChar;
	private char [] [] mutantDnaToChar;
	private HorizontalDetection horizontalDetection = new HorizontalDetectionImpl();
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
		point = new Point(humanDnaToChar, 0, column);
		
		mutantDnaToChar = DnaMutantUtil.structureDNAchain(dnaExampleMutant);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl#movePoint(com.magneto.dnamutant.model.Point)}.
	 */
	@Test
	void testMovePoint_GivenPoint_ExpectedNoErrorAndMovePoint() {
		assertThatNoException().isThrownBy(() -> horizontalDetection.movePoint(point));
		assertEquals(column + 1, point.column);
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenInitialCoordinate_ExpectedTrue() {
		assertTrue(horizontalDetection.CanContinue(point, secuenceChars));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenLastCoordinate_ExpectedFalse() {
		point.setCoordinates(0, 5);
		assertFalse(horizontalDetection.CanContinue(point, secuenceChars));
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedFalse() {
		assertFalse(horizontalDetection.searchSecuence(humanDnaToChar));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedTrue() {
		assertTrue(horizontalDetection.searchSecuence(mutantDnaToChar));
	}

}
