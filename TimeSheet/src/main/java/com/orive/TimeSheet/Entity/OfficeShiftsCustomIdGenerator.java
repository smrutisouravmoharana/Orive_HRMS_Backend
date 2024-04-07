package com.orive.TimeSheet.Entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.engine.jdbc.connections.spi.JdbcConnectionAccess;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OfficeShiftsCustomIdGenerator implements IdentifierGenerator {

	private static final long serialVersionUID = 1L;

	private final String prefix = "ORITMSOFCSFTS";
	private long sequence;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		JdbcConnectionAccess jdbcConnectionAccess = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			jdbcConnectionAccess = session.getJdbcConnectionAccess();
			connection = jdbcConnectionAccess.obtainConnection();

			String query = "SELECT MAX(office_shifts_id), COUNT(office_shifts_id) FROM officeshift";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int rowCount = resultSet.getInt(2);
				if (rowCount == 0) {
					sequence = 0;
				} else {
					String dbId = resultSet.getString(1);
					sequence = (dbId == null) ? 0 : Integer.parseInt(dbId.substring(prefix.length()));
				}
				return prefix + String.format("%03d", ++sequence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
