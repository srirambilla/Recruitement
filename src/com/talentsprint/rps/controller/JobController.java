package com.talentsprint.rps.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.talentsprint.rps.dao.JobDAO;
import com.talentsprint.rps.dao.ParameterDAO;
import com.talentsprint.rps.dao.RoundDAO;
import com.talentsprint.rps.dao.RoundParameterDAO;
import com.talentsprint.rps.dao.WeightageDAO;
import com.talentsprint.rps.dto.Job;
import com.talentsprint.rps.dto.Parameter;
import com.talentsprint.rps.dto.Round;
import com.talentsprint.rps.dto.Weightage;
import com.talentsprint.rps.exception.RPSException;

/**
 * Servlet implementation class JobController
 */
@WebServlet("/JobController")
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Round> roundsList = new RoundDAO().list();
			for (Round round : roundsList) {

				List<Integer> parameterIds = new RoundParameterDAO().getParameterIds(round.getRoundId());
				if (null != parameterIds && parameterIds.size() > 0) {

					Set<Parameter> parameterSet = new HashSet<>();
					for (Integer parameterId : parameterIds) {

						Parameter parameter = new ParameterDAO().get(parameterId);
						parameterSet.add(parameter);

					}
					round.setParameterSet(parameterSet);
				}

			}

			request.setAttribute("roundsList", roundsList);
			request.getRequestDispatcher("new.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String jobTitle = request.getParameter("jobTitle");
		String jobDescription = request.getParameter("jobDescription");
		String lastDate = request.getParameter("lastDate");
		String[] name = request.getParameterValues("rounds");

		Job job = new Job();
		job.setJobTitle(jobTitle);
		job.setJobDescription(jobDescription);
		job.setLastDate(lastDate);

		try {
			job = (new JobDAO()).insert(job);
		} catch (RPSException e) {
			e.printStackTrace();
		}

		if (null != name && name.length > 0) {
			for (String round : name) {
				int roundId = Integer.parseInt(round);

				String[] params = request.getParameterValues(roundId + "-names");
				if (null != params && params.length > 0) {
					for (String string2 : params) {
						int parameterId = Integer.parseInt(string2);
						String[] weightage = request.getParameterValues(parameterId + "-names");
						if (null != weightage && weightage.length > 0) {
							for (String string : weightage) {
								int weightage2 = Integer.parseInt(string);
								
								Weightage weightage3 = new Weightage();
								weightage3.setJobId(job.getJobId());
								weightage3.setRoundId(roundId);
								weightage3.setParameterId(parameterId);
								weightage3.setWeightage(weightage2);

								try {
									new WeightageDAO().insert(weightage3);
								} catch (RPSException e) {
									e.printStackTrace();
								}

							}

						}

					}
				}
			}
		}

	}

}
