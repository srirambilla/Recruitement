package com.talentsprint.rps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.rps.dbutil.DBUtil;
import com.talentsprint.rps.exception.RPSException;

public class RoundParameterDAO {

	public void insert(int roundId, int parameterId) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("insert into round_parameter values(?,?)");
			preparedStatement.setInt(1, roundId);
			preparedStatement.setInt(2, parameterId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
	}

	public List<Integer> getParameterIds(int roundId) throws RPSException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Integer> parameterIds = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from round_parameter where round_id=?");
			preparedStatement.setInt(1, roundId);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parameterIds = new ArrayList<>();
				resultSet.beforeFirst();
			}
			while (resultSet.next()) {
				parameterIds.add(resultSet.getInt(2));
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		}

		return parameterIds;
	}
}
