package com.putnin.db;

import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;

/**
 * Data base connection pool.
 *
 * @author putnin.v@gmail.com
 */
public class DataSourceConnectionPool {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_NAME = "zadb;create=true";
    private static EmbeddedConnectionPoolDataSource instance;

    private DataSourceConnectionPool(){
    }

    /**
     * Get instance of db connection pool data source.
     * @return connection pool data source
     */
    public static EmbeddedConnectionPoolDataSource getDCInstance() {
        if(instance == null){
            synchronized (DataSourceConnectionPool.class) {
                try {
                    Class.forName(DRIVER);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                instance = new EmbeddedConnectionPoolDataSource();
                instance.setDatabaseName(DB_NAME);
            }
        }
        return instance;
    }

}
