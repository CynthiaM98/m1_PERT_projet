import java.util.List;

public class Worker {

    protected String name;
    protected int id;

    public Worker(String name, int id, List<Node> tasksList) {
        this.name = name;
        this.id = id;

    }

    public Worker(int id) {
        this.name = "Worker "+id;
        this.id = id;
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
