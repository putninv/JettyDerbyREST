package com.putnin.repository;

import com.putnin.db.DataSourceConnectionPool;
import com.putnin.model.User;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Repository for money transfer between accounts.
 *
 * @author putnin.v@gmail.com
 */
public class MoneyRepository {
    private EmbeddedConnectionPoolDataSource dataSource;

    public MoneyRepository() {
        dataSource = DataSourceConnectionPool.getDCInstance();
    }

    /**
     * Get count of money on account.
     * @param accountId accountId
     * @return count of money
     */
    public BigDecimal getAccountSum(Long accountId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        BigDecimal sum = null;
        try {
            connection = dataSource.getPooledConnection().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT accountSum FROM users_accounts0 " +
                    "WHERE users_accounts0.account_id = " + accountId);

            while (resultSet.next()) {
                sum = resultSet.getBigDecimal("accountSum");
            }
        } catch (SQLException e) {
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
            return sum;
        }
    }


    /**
     * Transfer money from sender account to recipient account.
     *
     * @param senderId  id of person who send money
     * @param recipient person who receive money
     * @param sum       money sum
     * @return true - money transfer success complete, false - money transfer not success complete
     */
    public boolean transferMoneyToPerson(Long senderId, User recipient, BigDecimal sum) throws SQLException {
        Connection connection = dataSource.getPooledConnection().getConnection();

        boolean result = false;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            connection.setAutoCommit(false);

            int takeOffResult = statement.executeUpdate("UPDATE users_accounts0 SET users_accounts0.accountSum =("
                    + "(SELECT accountSum FROM users_accounts0 WHERE users_accounts0.user_id = " + senderId
                    + ") - " + sum + ") WHERE users_accounts0.user_id = " + senderId);
            int putMoneyResult = statement.executeUpdate("UPDATE users_accounts0 SET users_accounts0.accountSum =("
                    + "(SELECT accountSum FROM users_accounts0 WHERE users_accounts0.user_id = " + recipient.getId()
                    + ") + " + sum + ") WHERE users_accounts0.user_id = " + recipient.getId());
            connection.commit();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
