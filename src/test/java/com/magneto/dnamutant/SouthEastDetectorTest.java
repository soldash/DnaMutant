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
import com.magneto.dnamutant.service.SouthEastDetection;
import com.magneto.dnamutant.service.VerticalDetection;
import com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl;
import com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl;
import com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl;
import com.magneto.dnamutant.util.DnaMutantUtil;

/**
 * @author soldash
 *
 */
class SouthEastDetectorTest {
	
	private String[] dnaExampleHuman = {"ATCgAg","CaGCAa","TTcTtg","ACAaGC","CttCcA","TagTcg"};
	private String[] dnaExampleMutant = {"ATGcga","cAGTGC","TTaTgT","AGAAGG","CcCcTA","TCACTg"};
	private char [] [] humanDnaToChar;
	private char [] [] mutantDnaToChar;
	private SouthEastDetection southEastDetection = new SouthEastDetectionImpl();
	private int row = 0;
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
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl#movePoint(com.magneto.dnamutant.model.Point)}.
	 */
	@Test
	void testMovePoint_GivenPoint_ExpectedNoErrorAndMovePoint() {
		assertThatNoException().isThrownBy(() -> southEastDetection.movePoint(point));
		assertEquals(row + 1, point.row);
		assertEquals(column + 1, point.column);
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenInitialCoordinate_ExpectedTrue() {
		assertTrue(southEastDetection.CanContinue(point, secuenceChars));
	}
	
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl#CanContinue(com.magneto.dnamutant.model.Point, int)}.
	 */
	@Test
	void testCanContinue_GivenLastCoordinate_ExpectedFalse() {
		point.setCoordinates(5, 5);
		assertFalse(southEastDetection.CanContinue(point, secuenceChars));
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedFalse() {
		assertFalse(southEastDetection.searchSecuence(humanDnaToChar));
	}
	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl#searchSecuence(char[][])}.
	 */
	@Test
	void testSearchSecuence_GivenHumanDna_ExpectedTrue() {
		assertTrue(southEastDetection.searchSecuence(mutantDnaToChar));
	}

}
