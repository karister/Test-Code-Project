import tw.cn.cap.gtb.todo.domain.ServiceConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionProvider {

    private DatabaseConnectionProvider() {
    }

    public static Connection createConnection(ServiceConfiguration configuration) throws Exception {
        Class.forName(configuration.getDriver());
        return DriverManager.getConnection(
            configuration.getUri(), configuration.getUsername(), configuration.getPassword());
    }
}
