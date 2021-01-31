package lk.ijse.dep.dbcp.api.impl;

import junit.framework.TestCase;

import java.sql.Connection;

/**
 * @author : Dhanusha Perera
 * @since : 31/01/2021
 **/
public class BasicConnectionPoolTest extends TestCase {

    String url = "jdbc:mysql://localhost:3306";
    String user = "root";
    String password = "root";

//    @Test
//    public void testCreateConnection() {
//        /* createConnection method is a private method,
//        in order to test it, it should should be changed to public */
//        Connection connection = BasicConnectionPool.createConnection(url, user, password);
//        System.out.println(connection);
//        assertNotNull(connection);
//    }

    public void testGetConnection() {
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(url, user, password);
        Connection connection = basicConnectionPool.getConnection();
        System.out.println(connection);
        assertNotNull(connection);

    }
}
