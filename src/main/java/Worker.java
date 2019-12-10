import java.util.ArrayList;
import java.util.List;

public class Worker {

    protected String name;
    protected int id;
    protected Node currentTask;
    protected boolean inWork;

   

    public Worker(int id) {
        this.name = "Worker_"+id;
        this.id = id;
        this.inWork = false;  
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public void setCurrentTask(Node n){
        this.currentTask = n;
    }

    public Node getCurrentTask(){
        return currentTask;
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
                '}';
    }
}
