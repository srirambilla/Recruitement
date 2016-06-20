package com.talentsprint.rps.dao;

import java.util.List;

import com.talentsprint.rps.dto.Job;
import com.talentsprint.rps.exception.RPSException;

import sun.util.EmptyListResourceBundle;

public class New {

	public static void main(String[] args) throws RPSException {
		Job job=new Job();
		job.setJobTitle("fssdf");
		job.setJobDescription("sriram");
		job.setLastDate("22/2/2016");
		Job job2=new JobDAO().insert(job);
		System.out.println(job.getJobTitle());
		
		

	}

}
