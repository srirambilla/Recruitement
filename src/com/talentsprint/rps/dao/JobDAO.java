package com.talentsprint.rps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.rps.dbutil.DBUtil;
import com.talentsprint.rps.dto.Job;
import com.talentsprint.rps.exception.RPSException;

public class JobDAO {

	public Job insert(Job job) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Job job2 = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into job(job_title,job_description,last_date) values(?,?,?)");
			preparedStatement.setString(1, job.getJobTitle());
			preparedStatement.setString(2, job.getJobDescription());
			preparedStatement.setString(3, job.getLastDate());

			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement("select last_insert_id()");

				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					job.setJobId(resultSet.getInt(1));
				}

			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return job;
	}

	public Job get(int jobId) throws RPSException {
		Job job = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from job where job_id=?");
			preparedStatement.setInt(1, jobId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				job = new Job();
				job.setJobId(jobId);
				job.setJobTitle(resultSet.getString(2));
				job.setJobDescription(resultSet.getString(3));
				job.setLastDate(resultSet.getString(4));
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return job;

	}

	public List<Job> list() throws RPSException {

		List<Job> jobList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Job job = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from job");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				jobList = new ArrayList<>();
				resultSet.beforeFirst();
			}
			while (resultSet.next()) {
				job = new Job();
				job.setJobId(resultSet.getInt(1));
				job.setJobTitle(resultSet.getString(2));
				job.setJobDescription(resultSet.getString(3));
				job.setLastDate(resultSet.getString(4));
				jobList.add(job);

			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return jobList;
	}

}
