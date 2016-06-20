package com.talentsprint.rps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.rps.dbutil.DBUtil;
import com.talentsprint.rps.dto.Weightage;
import com.talentsprint.rps.exception.RPSException;


public class WeightageDAO {
	
	
	public Weightage insert(Weightage weightage) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("insert into weightage(job_id,round_id,parameter_id,weightage) values(?,?,?,?)");
			preparedStatement.setInt(1, weightage.getJobId());
			preparedStatement.setInt(2, weightage.getRoundId());
			preparedStatement.setInt(3, weightage.getParameterId());
			preparedStatement.setDouble(4, weightage.getWeightage());
			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement("select last_insert_id()");
				resultSet = preparedStatement.executeQuery();
				
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return weightage;
	}
/*
	public Weightage get(int jobId) throws RPSException {
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
				job.setJobDescription(resultSet.getString(2));
				job.setLastDate(resultSet.getDate(3));
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return job;

	}*/

	public List<Weightage> list() throws RPSException {

		List<Weightage> weightageList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Weightage weightage = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from weightage");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				weightageList = new ArrayList<>();
				resultSet.beforeFirst();
			}
			while (resultSet.next()) {
				weightage = new Weightage();
				weightage.setJobId(resultSet.getInt(1));
				weightage.setRoundId(resultSet.getInt(2));
				weightage.setParameterId(resultSet.getInt(3));
				weightage.setWeightage(resultSet.getInt(4));
				weightageList.add(weightage);

			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return weightageList;
	}
	
	

}
