/**
 * 
 */
package com.magneto.dnamutant;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.magneto.dnamutant.model.DnaLogModel;
import com.magneto.dnamutant.repository.DnaLogRepository;
import com.magneto.dnamutant.service.HorizontalDetection;
import com.magneto.dnamutant.service.NorthEastDetection;
import com.magneto.dnamutant.service.SouthEastDetection;
import com.magneto.dnamutant.service.VerticalDetection;
import com.magneto.dnamutant.serviceimpl.DnaMutantServiceImpl;
import com.magneto.dnamutant.serviceimpl.HorizontalDetectionImpl;
import com.magneto.dnamutant.serviceimpl.NorthEastDetectionImpl;
import com.magneto.dnamutant.serviceimpl.SouthEastDetectionImpl;
import com.magneto.dnamutant.serviceimpl.VerticalDetectionImpl;

/**
 * @author soldash
 *
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
//@SpringBootTest
class DnaMutantServiceTest {

	private String[] dnaMutant = { "ATCgAg", "CaGCAa", "TTcTtg", "ACAaGC", "CttCcA", "TagTcg" };
	private String[] dnaHuman = { "ATGcga", "cAGTGC", "TTATtT", "AGAcGG", "GcGTcA", "TcACTg" };
	private String[] dnaErrorNxN = { "ATGcga", "cAGTG", "TTATtT", "AGAcGG", "GcGTcA", "TcACTg" };
	private String[] dnaErrorCharacter = { "ATGcga", "cAGTGC", "TTdTtT", "AGAcGG", "GcGTcA", "TcACTg" };

	@Mock
	private HorizontalDetection horizontal;
	@Mock
	private VerticalDetection vertical;
	@Mock
	private SouthEastDetection southEast;
	@Mock
	private NorthEastDetection northEast;
	@Mock
	private DnaLogRepository dnaLogRepositoryMock;

	@InjectMocks
	private DnaMutantServiceImpl dnaMutantService;

	/**
	 * Test method for
	 * {@link com.magneto.dnamutant.serviceimpl.DnaMutantServiceImpl#isMutant(java.lang.String[])}.
	 * 
	 * @throws Exception
	 */
	@Test
	final void testIsMutant_GivenMutantDna_ExpectedTrue() throws Exception {
		when(horizontal.searchSecuence(Mockito.any(char[][].class))).thenReturn(true);
		when(dnaLogRepositoryMock.save(Mockito.any(DnaLogModel.class))).thenReturn(new DnaLogModel());

		boolean isMutant = dnaMutantService.isMutant(dnaMutant);
		assertTrue(isMutant);
	}

	@Test
	final void testIsMutant_GivenHumanDna_ExpectedFalse() throws Exception {
		when(horizontal.searchSecuence(Mockito.any(char[][].class))).thenReturn(false);
		when(vertical.searchSecuence(Mockito.any(char[][].class))).thenReturn(false);
		when(southEast.searchSecuence(Mockito.any(char[][].class))).thenReturn(false);
		when(northEast.searchSecuence(Mockito.any(char[][].class))).thenReturn(false);
		when(dnaLogRepositoryMock.save(Mockito.any(DnaLogModel.class))).thenReturn(new DnaLogModel());

		boolean isMutant = dnaMutantService.isMutant(dnaHuman);
		assertFalse(isMutant);
	}

	@Test
	final void testIsMutant_GivenErrorDnaNxN_ExpectedException() throws Exception {
		assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
			dnaMutantService.isMutant(dnaErrorNxN);
		}).withMessage("las secuencias de adn ingresadas deben ser de la misma longitud");
	}

	@Test
	final void testIsMutant_GivenErrorDnaCharacter_ExpectedException() throws Exception {
		assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
			dnaMutantService.isMutant(dnaErrorCharacter);
		}).withMessage("las letras de la secuencia solo pueden ser A, T, C e G.");
	}

}
