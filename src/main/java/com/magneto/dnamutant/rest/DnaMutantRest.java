package com.magneto.dnamutant.rest;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.magneto.dnamutant.service.DnaMutantService;
import com.magneto.dnamutant.service.DnaStatsService;
import com.magneto.dnamutant.util.DnaMutantUtil;

/**
 * 
 * @author soldash
 * Rest controller to manage the principal endpoints
 */
@RestController
public class DnaMutantRest extends Exception {
	
	private static final long serialVersionUID = -8491919418492785594L;

	@Autowired
	DnaMutantService dnaMutantService;
	
	@Autowired
	DnaStatsService dnaStatsService;
	
	
	/**
	 * 
	 * @param body: dnaStructure
	 * @param response: 403: Human, 200 Mutant
	 * @return
	 * @throws Exception wrong caracters, no NxN matrix
	 */
	@PostMapping("/mutant")
	@ResponseBody
	public ResponseEntity<Object> mutant(@RequestBody String body, HttpServletResponse response) throws Exception {
		try {
			JSONObject input = new JSONObject(body);
			String[] dna = DnaMutantUtil.toStringArray(input.getJSONArray("dna"));
			if(dnaMutantService.isMutant(dna)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN );
			}
			
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @return stadistics about dna secuences
	 */
	@GetMapping(path = {"/stats","/"})
	@ResponseBody
	public ResponseEntity<Object> stats() {
		JSONObject body = new JSONObject();
		body.put("count_mutant_dna", dnaStatsService.getCountMutantDna());
		body.put("count_human_dna", dnaStatsService.getCountHumanDna());
		body.put("ratio", dnaStatsService.getRatioDna());
		return new ResponseEntity<>(body.toString(), HttpStatus.OK);
		
		
	}
}
