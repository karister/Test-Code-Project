import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author karister
 * @create 2022-03-09 23:53
 */
public class Task {
    public static final String MARK = "*#MARK#*";
    public static final String UNMARK = "*#UNMARK#*";
    private String content;
    private Integer number;
    private String status;

    public Task() {
        TasksFile.taskNum++;
        this.number = TasksFile.taskNum;
        this.status = UNMARK;
    }

    public void taskHandle(String[] args) {
        ArrayList<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(0);
        this.setContent(String.join(" ",argsList));
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Task{" +
                "content='" + content + '\'' +
                ", number=" + number +
                ", status='" + status + '\'' +
                '}';
    }
}
