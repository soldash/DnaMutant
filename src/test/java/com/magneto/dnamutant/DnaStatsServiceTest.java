/**
 * 
 */
package com.magneto.dnamutant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.magneto.dnamutant.repository.DnaLogRepository;
import com.magneto.dnamutant.service.DnaStatsService;
import com.magneto.dnamutant.serviceimpl.DnaStatsServiceImpl;


/**
 * @author soldash
 *
 */
@ExtendWith(MockitoExtension.class)
class DnaStatsServiceTest {
	
	@Mock
	private DnaLogRepository dnaLogRepositoryMock;
	
	@InjectMocks
	private DnaStatsService dnaStatsService = new DnaStatsServiceImpl();
	
	private Long numberOfRecords = 15L;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		when(dnaLogRepositoryMock.count(Mockito.any(Example.class)))
			.thenReturn(numberOfRecords);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.DnaStatsServiceImpl#getCountMutantDna()}.
	 */
	@Test
	void testGetCountMutantDna_ExpectedMutantCount() {
		assertEquals(numberOfRecords, dnaStatsService.getCountMutantDna());
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.DnaStatsServiceImpl#getCountHumanDna()}.
	 */
	@Test
	void testGetCountHumanDna_ExpectedHumanCount() {
		assertEquals(numberOfRecords, dnaStatsService.getCountHumanDna());
	}

	/**
	 * Test method for {@link com.magneto.dnamutant.serviceimpl.DnaStatsServiceImpl#getRatioDna()}.
	 */
	@Test
	void testGetRatioDna() {
		dnaStatsService.getCountMutantDna();
		dnaStatsService.getCountHumanDna();
		assertEquals(1F, dnaStatsService.getRatioDna());
	}

}
