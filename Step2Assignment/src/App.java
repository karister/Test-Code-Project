import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class App {
    public static final String FOLDER_PATH = System.getProperty("user.home") + "/.todo";
    public static final String FILE_PATH = FOLDER_PATH + "/" + "tasks";
    public static String COMMAND;

    public enum Command {
        init,add,remove,list,mark,edit,reset;
    }

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        TasksFile.tasksFile = file;
        if (args.length == 0) {
            showMessageNoargs();
            return ;
        }
        COMMAND = args[0];
        if (COMMAND.equals(Command.init.toString())) {
            if (file.exists()) {
                return ;
            }
            new File(FOLDER_PATH).mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Initialized successfully.");
        }

        if (!file.exists()) {
            System.out.println(String.format("Please run 'todo init' before running '%s' command.", COMMAND));
            return ;
        }
        try {
            TasksFile.tasksFileInit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(0);

        if (COMMAND.equals(Command.add.toString())) {
            Task task = new Task();
            task.setContent(String.join(" ",argsList));
            try {
                TasksFile.taskAdd(task);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (COMMAND.equals(Command.remove.toString())) {
            try {
                TasksFile.taskRemove(argsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (COMMAND.contains(Command.mark.toString())) {
            try {
                TasksFile.taskMark(argsList,COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (COMMAND.equals(Command.edit.toString())) {
            try {
                TasksFile.taskEdit(Integer.valueOf(argsList.get(0)),argsList.get(1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (COMMAND.equals(Command.list.toString())) {
            try {
                TasksFile.taskList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (COMMAND.equals(Command.reset.toString())) {
            try {
                TasksFile.taskReset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println(String.format("Unknown command: '%s'",COMMAND));
    }

    static void showMessageNoargs() {
        System.out.println("usage: todo <init|list|add|edit|remove|reset|mark|unmark> [args]");
    }
}
