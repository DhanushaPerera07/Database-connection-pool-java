/**
 * MIT License
 * <p>
 * Copyright (c) 2020 Dhanusha Perera
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @author : Dhanusha Perera
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 * @since : 31/01/2021
 **/
/**
 * @author : Dhanusha Perera
 * @since : 31/01/2021
 **/
package lk.ijse.dep.dbcp.api.impl;

import lk.ijse.dep.dbcp.api.ConnectionPool;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class BasicConnectionPool implements ConnectionPool {

    private static Logger logger = LoggerFactory.getLogger(BasicConnectionPool.class);

    private static BasicConnectionPool basicConnectionPool;

    private static int INITIAL_POOL_SIZE = 10;
    private String url;
    private String user;
    private String password;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Connection> connectionPool;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Connection> usedConnections = new ArrayList<>();

    /* constructors */
    private BasicConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }


    private static BasicConnectionPool getInstance(String url, String user, String password, List<Connection> pool) {
        return (basicConnectionPool == null) ? basicConnectionPool = new BasicConnectionPool(url, user, password, pool) : basicConnectionPool;
    }


    /** This method will create the connection pool with number of connections.
     * Default number of connection = 10*/
    public static BasicConnectionPool create(String url, String user, String password) {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }

        return getInstance(url, user, password, pool);
    }

    /** This method will create a connection and return it
     * @return Connection */
    private static Connection createConnection(String url, String user, String password) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            logger.error("Error occurred when database connection is being created", exception);
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            logger.error("Error occurred when MySQL database driver is being loaded", exception);
            exception.printStackTrace();
        }
        return connection;
    }

    /** This method will get a connection from the pool and
     * add it to the usedPool,
     * then return the connection which was removed from the pool
     * @return Connection*/
    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    /** This method will get the connection, add to the pool, and remove from the usedPool.
     * returns true if connection is successfully restored to pool,
     * otherwise, returns false.
     * @return boolean*/
    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    /** this method will return how many connections are in the pool
     * @return int*/
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

}
