package com.magneto.dnamutant.util;

import java.util.regex.Pattern;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;

public class DnaMutantUtil {
	private static final Pattern CHARACTERS_BASE_PARTTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

	private DnaMutantUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static char[][] structureDNAchain(String[] dna) throws Exception {
		// Convierte la estructura de ADN en una matriz de char
		int dnalength = dna.length;
		char[][] dnaStructure = new char[dnalength][dnalength];

		for (int i = 0; i < dnalength; i++) {
			String dnaRow = dna[i];
			validateDNA(dnalength, dnaRow);
			dnaStructure[i] = dnaRow.toUpperCase().toCharArray();

		}
		return dnaStructure;
	}

	private static void validateDNA(int dnalength, String dnaRow) throws Exception {
		// Valida que la cadena de entrada cumpla con las condiciones
		if (dnaRow.length() != dnalength) {
			// valida que sea una matriz de NxN
			throw new Exception("las secuencias de adn ingresadas deben ser de la misma longitud");

		} else if (!CHARACTERS_BASE_PARTTERN.matcher(dnaRow).matches()) {
			throw new Exception("las letras de la secuencia solo pueden ser A, T, C e G.");
		}
	}

	public static String[] toStringArray(JSONArray array) {
		if (array == null)
			return null;

		String[] arr = new String[array.length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = array.optString(i);
		}
		return arr;
	}
	
	public static String convertToString(String[] arrayString) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			sb.append(arrayString[i].toUpperCase());
			sb.append(" , ");
		}
		return sb.toString();
	}
}
