package com.putnin.repository;

import com.putnin.db.DataSourceConnectionPool;
import com.putnin.model.User;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Repository for users.
 *
 * @author putnin.v@gmail.com
 */
public class UserRepository {
    private EmbeddedConnectionPoolDataSource dataSource;

    public UserRepository() {
        dataSource = DataSourceConnectionPool.getDCInstance();
    }

    /**
     * Get user by phone.
     *
     * @param phone user phone
     * @return founded user
     */
    public User getUserByPhone(String phone) throws SQLException {
        Connection connection = dataSource.getPooledConnection().getConnection();
        User user = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * from app_users0 WHERE app_users0.phone = '"
                    + phone+"'");

            user = extractUser(resultSet);
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }

        return user;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            Long userId = resultSet.getLong("id");
            String userPhone = resultSet.getString("phone");
            user = new User(userId, userPhone);
        }
        return user;
    }
}
