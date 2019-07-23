package com.longquanxiao.sql;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *  对连接工具的单元测试
 * @author longquanxiao
 */
public class ConnectionDBTest extends TestCase {
    private static final Logger logger = Logger.getLogger(ConnectionDBTest.class);
    public ConnectionDBTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ConnectionDBTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * 测试连接类
     */
    public void testConnection(){
        Connection connection = ConnectionDB.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            String selectSql = "SELECT * FROM t_user LIMIT 1,5";
            resultSet = statement.executeQuery(selectSql);
            while (resultSet.next()) {
                for(int i=1; i<7; i++){
                    logger.info(resultSet.getString(i));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }finally {
            ConnectionDB.release(connection,statement,resultSet);
        }
    }

    public void testHello() {
        logger.info("hello test");
    }
}
