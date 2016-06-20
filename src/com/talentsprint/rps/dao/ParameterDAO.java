package com.talentsprint.rps.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.talentsprint.rps.dbutil.DBUtil;
import com.talentsprint.rps.dto.Parameter;
import com.talentsprint.rps.exception.RPSException;

public class ParameterDAO {
	public Parameter insert(Parameter parameter) throws RPSException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("insert into parameter(parameter_name) values(?)");
			preparedStatement.setString(1, parameter.getName());
			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement.close();
				preparedStatement = connection.prepareStatement("select last_insert_id()");
				resultSet = preparedStatement.executeQuery();
				parameter.setParameterId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return parameter;
	}

	public Parameter get(int parameterId) throws RPSException {
		Parameter parameter = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from parameter where parameter_id=?");
			preparedStatement.setInt(1, parameterId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parameter = new Parameter();
				parameter.setParameterId(parameterId);
				parameter.setName(resultSet.getString(2));
			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}
		return parameter;

	}

	public List<Parameter> list() throws RPSException {

		List<Parameter> parameterList = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Parameter parameter = null;

		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from parameter");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				parameterList = new ArrayList<>();
				resultSet.beforeFirst();
			}
			while (resultSet.next()) {
				parameter = new Parameter();
				parameter.setParameterId(resultSet.getInt(1));
				parameter.setName(resultSet.getString(2));
				parameterList.add(parameter);

			}

		} catch (SQLException e) {
			throw new RPSException(e.toString());
		} finally {
			DBUtil.close(resultSet, preparedStatement, connection);
		}

		return parameterList;
	}

}
