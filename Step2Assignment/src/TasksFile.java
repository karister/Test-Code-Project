import java.io.*;
import java.util.*;

/**
 * @author karister
 * @create 2022-03-10 0:05
 */
public class TasksFile {

    public static Map<Integer, String > taskMap = new HashMap<>();
    public static File tasksFile;
    public static Integer taskNum = 0;
    public static Integer lineNum = 0;

    public static void tasksFileInit() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(TasksFile.tasksFile));
        String line;
        while ((line = br.readLine()) != null) {
            lineNum ++;
            taskMap.put(lineNum, line);
            taskNum++;
        }
        br.close();
    }

    public static void taskAdd(Task task) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(tasksFile,true));
        if(lineNum == 0)
            bw = new BufferedWriter(new FileWriter(tasksFile));
        String taskContent = String.format("%d %s %s",task.getNumber(),task.getContent(),task.getStatus());
        bw.write(taskContent);
        bw.newLine();
        bw.close();
    }

    public static void taskRemove(List<String> argsList) throws IOException {
        for (int i = 0; i < argsList.size(); i++) {
            Integer key = Integer.valueOf(argsList.get(i));
            if (taskMap.containsKey(key)) {
                taskMap.put(key,"");
            }
        }
        taskWrite();
    }

    public static void taskMark(List<String> argsList, String COMMAND) throws IOException {
        for (int i = 0; i < argsList.size(); i++) {
            Integer key = Integer.valueOf(argsList.get(i));
            if (taskMap.containsKey(key)) {
                String taskContent = taskMap.get(key);
                if (COMMAND.equals("mark"))
                    taskMap.put(key,taskContent.replace(Task.UNMARK,Task.MARK));
                else if (COMMAND.equals("unmark"))
                    taskMap.put(key,taskContent.replace(Task.MARK,Task.UNMARK));
            }
        }
        taskWrite();
    }

    public static void taskEdit(Integer num, String taskContent) throws IOException {
        String status = taskContent.contains(Task.MARK) ? Task.MARK : Task.UNMARK;
        taskMap.put(num,String.format("%d %s %s",num,taskContent,status));
        taskWrite();
    }

    public static void taskList() throws IOException {
        System.out.println("# To be done");
        boolean hasUnmark = false;
        for (Map.Entry<Integer, String> entry : taskMap.entrySet()) {
            if (entry.getValue().contains(Task.UNMARK)) {
                hasUnmark = true;
                System.out.println(entry.getValue().replace(Task.UNMARK,""));
            }
        }
        if (!hasUnmark)
            System.out.println("Empty");
        System.out.println("# Completed");
        boolean hasMark = false;
        for (Map.Entry<Integer, String> entry : taskMap.entrySet()) {
            if (entry.getValue().contains(Task.MARK)) {
                hasMark = true;
                System.out.println(entry.getValue().replace(Task.MARK,""));
            }
        }
        if (!hasMark)
            System.out.println("Empty");
    }

    public static void taskReset() throws IOException {
        taskMap = new HashMap<>();
        taskWrite();
    }

    public static void taskWrite() throws IOException {
        List<String> valuesList = new ArrayList<>(taskMap.values());
        BufferedWriter bw = new BufferedWriter(new FileWriter(tasksFile));
        for (int i = 0; i < valuesList.size(); i++) {
            if(i == 1) {
                bw.close();
                bw = new BufferedWriter(new FileWriter(tasksFile,true));
            }
            bw.write(valuesList.get(i));
            bw.newLine();
        }
        bw.close();
    }
}
