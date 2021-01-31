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
 **/
/**
 * @author : Dhanusha Perera
 * @since : 31/01/2021
 **/
package lk.ijse.dep.dbcp.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    public static void init() {

        Properties properties = new Properties();
        try {
            properties.load(LoggerUtil.class.getResourceAsStream("/application.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        String logFilePath;
        if (properties.getProperty("app.logger.log_dir") != null) {
            System.out.println("logger file path:");
            logFilePath = properties.getProperty("app.logger.log_dir") + File.separator + "logs" + File.separator + "dbcp-log.log";
            File file = new File(logFilePath);

            try {
                if (file.createNewFile()) {
                    System.out.println("Created dbcp-log.log");
                } else {
                    System.out.println("File already exists !, filename: dbcp-log.log");
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            logFilePath = System.getProperty("catalina.home") + File.separator + "logs" + File.separator + "dbcp-log.log";
        }
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(logFilePath, true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.INFO);
        Logger.getLogger("").addHandler(fileHandler);
    }


}
