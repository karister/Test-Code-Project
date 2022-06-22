package domain;


import tw.cn.cap.gtb.todo.DatabaseConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoItemRepository {
    public static final String CREATE_SQL = "INSERT INTO `tasks` (`name`) VALUES (?)";
    public static final String DELETE_SQL = "DELETE FROM `tasks` WHERE (`id`) = (?)";
    public static final String QUERY_BY_ID_SQL = "SELECT `name`, `checked` FROM `tasks` WHERE `id` = ?";
    public static final String QUERY_BY_CHECKED_SQL = "SELECT `id`, `name` FROM `tasks` WHERE `checked` = ?";
    public static final String MODIFY_CHECKED_SQL = "UPDATE `tasks` SET `checked` = ? WHERE `id` = ?";
    public static final String MODIFY_NAME_SQL = "UPDATE `tasks` SET `name` = ? WHERE `id` = ?";
    public static final String CLEAR_SQL = "DELETE FROM `tasks`";

    private final ServiceConfiguration configuration;

    public TodoItemRepository(ServiceConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Create an item and save it into database. The `checked` status should default to `false`.
     *
     * @param name The name of the item.
     * @return The `id` of the item created.
     * @throws IllegalArgumentException The name is `null`.
     */
    public long create(String name) throws Exception {
        if (name == null) {
            throw new IllegalArgumentException("Name must be provided.");
        }
        try (
            Connection connection = createConnection();
            final PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, name);
            statement.executeUpdate();
            final ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    /**
     * Delete an item and update databse.
     *
     * @param id
     * @throws Exception
     */
    public void delete(long id) throws Exception {
        try (
            Connection connection = createConnection();
            final PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    /**
     * Change item checked status to specified state.
     *
     * @param id The item to update.
     * @param checked The new checked status.
     * @return If the item exist returns true, otherwise returns false.
     */
    public boolean changeCheckedStatus(long id, boolean checked) throws Exception {
        //   Please implement the method.
        try (
                Connection connection = createConnection();
                final PreparedStatement statement = connection.prepareStatement(MODIFY_CHECKED_SQL);
            ) {
            statement.setBoolean(1, checked);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Modify item name update in to database.
     *
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    public boolean editName(long id, String name) throws Exception {
        //   Please implement the method.
        try (
            Connection connection = createConnection();
            final PreparedStatement statement = connection.prepareStatement(MODIFY_NAME_SQL);
        ) {
            statement.setString(1, name);
            statement.setLong(2, id);
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * print todo item list message.
     * @return
     * @throws Exception
     */
    public String printList() throws Exception {
        final String NEWLINE = "\r\n";
        final String MARK_PREFIX = "# To be done" + NEWLINE;
        final String UNMARK_PREFIX = "# Completed" + NEWLINE;
        final String EMPTY = "Empty";
        StringBuilder markItem = new StringBuilder();
        StringBuilder unmarkItem = new StringBuilder();
        StringBuilder result = new StringBuilder();

        List<Optional<TodoItem>> checkedOptionalList = findByChecked(true);
        if (checkedOptionalList.size() == 0) {
            markItem.append(EMPTY);
            markItem.append(NEWLINE);
        }
        for (Optional<TodoItem> todoItemOptional : checkedOptionalList) {
            if (todoItemOptional.isPresent()) {
                TodoItem item = todoItemOptional.get();
                markItem.append(item);
                markItem.append(NEWLINE);
            }
        }

        List<Optional<TodoItem>> uncheckOptionalList = findByChecked(false);
        if (uncheckOptionalList.size() == 0) {
            unmarkItem.append(EMPTY);
            unmarkItem.append(NEWLINE);
        }
        for (Optional<TodoItem> todoItemOptional : uncheckOptionalList) {
            if (todoItemOptional.isPresent()) {
                TodoItem item = todoItemOptional.get();
                unmarkItem.append(item);
                unmarkItem.append(NEWLINE);
            }
        }

        result.append(MARK_PREFIX);
        result.append(markItem);
        result.append(UNMARK_PREFIX);
        result.append(unmarkItem);

        return result.toString();
    }

    /**
     * Clear all todo items.
     *
     * @throws Exception
     */
    public void clear() throws Exception {
        //   Please implement the method.
        try (
            Connection connection = createConnection();
            final PreparedStatement statement = connection.prepareStatement(CLEAR_SQL);
        ) {
            statement.executeUpdate();
        }
    }

    /**
     * get items by checked
     *
     * @param checked
     * @return
     * @throws Exception
     */
    public List<Optional<TodoItem>> findByChecked(boolean checked) throws Exception {
        List<Optional<TodoItem>> taskOptionalList = new ArrayList<>();
        //   Please implement the method.
        try (
                Connection connection = createConnection();
                final PreparedStatement statement = connection.prepareStatement(QUERY_BY_CHECKED_SQL);
        ) {
            statement.setBoolean(1, checked);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                final long id = resultSet.getLong("id");
                final String name = resultSet.getString("name");
                taskOptionalList.add(Optional.of(new TodoItem(id, name, checked)));
            }
            return taskOptionalList;
        }
    }

    /**
     * Get item by its id.
     *
     * @param id The id of the item.
     * @return The item entity. If the item does not exist, returns empty.
     */
    public Optional<TodoItem> findById(long id) throws Exception {
        //   Please implement the method.
        try (
            Connection connection = createConnection();
            final PreparedStatement statement = connection.prepareStatement(QUERY_BY_ID_SQL);
        ) {
            statement.setLong(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                final String name = resultSet.getString("name");
                final boolean checked = resultSet.getBoolean("checked");
                return Optional.of(new TodoItem(id, name, checked));
            }
            return Optional.empty();
        }
    }

    private Connection createConnection() throws Exception {
        return DatabaseConnectionProvider.createConnection(configuration);
    }
}
