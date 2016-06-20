package com.talentsprint.rps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.rps.dbutil.DBUtil;
import com.talentsprint.rps.dto.Round;
import com.talentsprint.rps.exception.RPSException;

public class RoundDAO {

	public Round insert(Round round) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("insert into round(round_name) values(?)");
			preparedStatement.setString(1, round.getName());
			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement("select last_insert_id()");
				resultSet = preparedStatement.executeQuery();
				round.setRoundId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new RPSException(e.toString());
		}

		return round;
	}

	public Round get(int roundId) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Round round = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from round where round_id=?");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				round = new Round();
				round.setRoundId(resultSet.getInt(1));
				round.setName(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return round;
	}

	public List<Round> list() throws RPSException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Round> roundsList = null;
		Round round = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from round");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				roundsList = new ArrayList<>();
				resultSet.beforeFirst();
			}
			while (resultSet.next()) {
				round = new Round();
				round.setRoundId(resultSet.getInt(1));
				round.setName(resultSet.getString(2));
				roundsList.add(round);
			}
			

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return roundsList;
	}

}
