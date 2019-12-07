import java.util.ArrayList;
import java.util.List;

public class Worker {

    protected String name;
    protected int id;
    protected List<Node> tasksList;

    public Worker(String name, int id, List<Node> tasksList) {
        this.name = name;
        this.id = id;
        this.tasksList=tasksList;

    }

    public Worker(int id) {
        this.name = "Worker_"+id;
        this.id = id;
        this.tasksList=new ArrayList<>();
    }

    public List<Node> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Node> tasksList) {
        this.tasksList = tasksList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", tasksList=" + tasksList +
                '}';
    }
}
