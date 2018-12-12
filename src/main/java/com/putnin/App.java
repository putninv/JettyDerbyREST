package com.putnin;

import com.putnin.controller.MoneyController;
import com.putnin.db.DataSourceConnectionPool;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.sql.*;

/**
 * App main class. Here we start embedded server and create database
 *
 * @author putnin.v@gmail.com
 */
public class App {

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8085);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                MoneyController.class.getCanonicalName());

        initDB();
        jettyServer.start();
        jettyServer.join();
    }


    private static void initDB() throws SQLException {
        EmbeddedConnectionPoolDataSource dataSource = DataSourceConnectionPool.getDCInstance();
        Connection connection = dataSource.getPooledConnection().getConnection();
        Statement statement = connection.createStatement();
        initBDTables(connection);

        statement.execute("insert into app_users0 values (123123, '+79009082837')," +
                " (234234, '+79999082837')," +
                "(345345, '+79889082837'), (456456, '+79779082837')");
        statement.execute("insert into users_accounts0 values (981111, 1000,123123)," +
                " (971111, 2000, 234234),(961111, 3000, 345345), (951111, 4000, 456456)");

        statement.close();
        connection.close();
    }

    private static void initBDTables(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet rs = null;
        rs = databaseMetaData.getTables(null, null,
                "APP_USERS0", null);

        if (rs.next()) {
            statement.execute("DROP TABLE app_users0");
            statement.execute("CREATE TABLE  app_users0 (id INTEGER, phone VARCHAR(12))");
        } else {
            statement.execute("CREATE TABLE  app_users0 (id INTEGER, phone VARCHAR(12))");
        }

        rs = databaseMetaData.getTables(null, null,
                "USERS_ACCOUNTS0", null);

        if (rs.next()) {
            statement.execute("DROP TABLE users_accounts0");
            statement.execute("CREATE TABLE users_accounts0 (account_id INTEGER," +
                    " accountSum FLOAT, user_id INTEGER)");
        } else {
            statement.execute("CREATE TABLE users_accounts0 (account_id INTEGER," +
                    " accountSum FLOAT, user_id INTEGER)");
        }

        if (statement != null) {
            statement.close();
        }
    }

}
