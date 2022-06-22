import tw.cn.cap.gtb.todo.domain.TodoItem;
import tw.cn.cap.gtb.todo.domain.TodoItemRepository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


public class TodoApp {
    public static final String JDBC_URL = "jdbc:mysql://localhost:13306/todoapp";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "p@ssword";
    public static final String TABLE_CREATE_SQL = "CREATE TABLE `tasks` (\n" +
            "`id`  int NOT NULL AUTO_INCREMENT ,\n" +
            "`name`  varchar(255) NOT NULL ,\n" +
            "`checked`  tinyint NOT NULL DEFAULT false ,\n" +
            "PRIMARY KEY (`id`)\n" +
            ")";
    public static String COMMAND;

    public enum Command {
        init,add,remove,list,mark,edit,reset;
    }

    public static void main(String[] args) throws Exception {
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(JDBC_URL, JDBC_USER, JDBC_PASSWORD, "com.mysql.jdbc.Driver");
        TodoItemRepository repository = new TodoItemRepository(databaseConfiguration);

        if (args.length == 0) {
            showMessageNoargs();
            return ;
        }
        COMMAND = args[0];
        if (COMMAND.equals(Command.init.toString())) {
            try(Connection connection = DatabaseConnectionProvider.createConnection(databaseConfiguration)) {
                if (!tableExists(connection, "tasks")) {
                    connection.prepareStatement(TABLE_CREATE_SQL).execute();
                    System.out.println("Initialized successfully.");
                    return ;
                }
                return ;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try(Connection connection = DatabaseConnectionProvider.createConnection(databaseConfiguration)) {
            if (!tableExists(connection, "tasks")) {
                System.out.println(String.format("Please run 'todo init' before running '%s' command.", COMMAND));
                return ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(0);

        if (COMMAND.equals(Command.add.toString())) {
            repository.create(String.join(" ",argsList));
        }
        else if (COMMAND.equals(Command.remove.toString())) {
            for (String s : argsList) {
                repository.delete(Integer.parseInt(s));
            }
        }
        else if (COMMAND.contains(Command.mark.toString())) {
            for (String s : argsList) {
                Optional<TodoItem> taskOptional = repository.findById(Integer.parseInt(s));
                if (taskOptional.isPresent()) {
                    if (COMMAND.equals("mark")) {
                        repository.changeCheckedStatus(Integer.parseInt(s),true);
                    }
                    else if (COMMAND.equals("unmark")) {
                        repository.changeCheckedStatus(Integer.parseInt(s),false);
                    }
                }
            }
        }
        else if (COMMAND.equals(Command.edit.toString())) {
            repository.editName(Integer.parseInt(argsList.get(0)), argsList.get(1));
        }
        else if (COMMAND.equals(Command.list.toString())) {
            System.out.println(repository.printList());
        }
        else if (COMMAND.equals(Command.reset.toString())) {
            repository.clear();
        }
        else
            System.out.println(String.format("Unknown command: '%s'!",COMMAND));
    }
    static void showMessageNoargs() {
        System.out.println("usage: todo <init|list|add|edit|remove|reset|mark|unmark> [args]");
    }


    static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});

        return resultSet.next();
    }
}
