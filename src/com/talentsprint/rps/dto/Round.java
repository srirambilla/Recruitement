package com.talentsprint.rps.dto;

import java.util.Set;

public class Round {

	private int roundId;
	private String name;
	private Set<Parameter> parameterSet;

	public int getRoundId() {
		return roundId;
	}

	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Parameter> getParameterSet() {
		return parameterSet;
	}

	public void setParameterSet(Set<Parameter> parameterSet) {
		this.parameterSet = parameterSet;
	}

}
