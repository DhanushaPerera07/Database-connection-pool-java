/**
 * MIT License
 * <p>
 * Copyright (c) 2021 Dhanusha Perera
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
package lk.ijse.dep.dbcp;

import lk.ijse.dep.dbcp.api.impl.BasicConnectionPool;
import lk.ijse.dep.dbcp.util.DatabasePropertyUtil;
import lk.ijse.dep.dbcp.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

public class AppInitializer {
    private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

    public static void main(String[] args) {

        /* Initialize the Logger */
        LoggerUtil.init();

        /* Load database properties using resource bundle */
        Properties databaseProperties = DatabasePropertyUtil.getDatabaseProperties();

        /* create BasicConnectionPool */
        BasicConnectionPool basicConnectionPool = BasicConnectionPool.create(databaseProperties.getProperty("dbcp.jdbc.database.url"),
                databaseProperties.getProperty("dbcp.jdbc.database.user"),
                databaseProperties.getProperty("dbcp.jdbc.database.password"));

        /* get connection from the created BasicConnectionPool */
        Connection connection = basicConnectionPool.getConnection();

        /* set loggers and see the output */
        logger.info(connection.toString());
        logger.info(Integer.toString(basicConnectionPool.getSize()));
        logger.info(Boolean.toString(basicConnectionPool.releaseConnection(connection)));

    }
}
