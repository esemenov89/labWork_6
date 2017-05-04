package code.services;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import java.util.Locale;

public class ConnectionPool {

    private static DataSource datasource = new DataSource();
    static {
        Locale.setDefault(Locale.ENGLISH);
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
        p.setDriverClassName("oracle.jdbc.OracleDriver");
        p.setUsername("admin");
        p.setPassword("1234");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1 FROM DUAL");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(1000000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

        datasource.setPoolProperties(p);
    }

    /**
     * Get an object DataSource for connection to DB
     * @return dataSource
     */
    public static DataSource getInstance() {
        return datasource;
    }

}
