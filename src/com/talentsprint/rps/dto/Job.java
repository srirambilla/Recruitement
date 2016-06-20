package com.talentsprint.rps.dto;

import java.util.Set;

public class Job {

	private int jobId;
	private String jobTitle;
	private String jobDescription;
	private String lastDate;
	private Set<Round> roundSets;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String date) {
		this.lastDate = date;
	}

	public Set<Round> getRoundSets() {
		return roundSets;
	}

	public void setRoundSets(Set<Round> roundSets) {
		this.roundSets = roundSets;
	}

}
