package com.magneto.dnamutant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class DnaLogModel implements Serializable {
	
	private static final long serialVersionUID = 8286944054143497181L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotNull
	private String dna;
	
	@Column
	@NotNull
	private boolean mutant;

	/**
	 * @return the dna
	 */
	public String getDna() {
		return dna;
	}

	/**
	 * @param dna the dna to set
	 */
	public void setDna(String dna) {
		this.dna = dna;
	}

	/**
	 * @return the mutant
	 */
	public boolean isMutant() {
		return mutant;
	}

	/**
	 * @param mutant the mutant to set
	 */
	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}

}
