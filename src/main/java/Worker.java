/**
 * The class Worker is used to assign task to workers
 * A Worker have 2 attributs :
 * <ul>
 * <li>a String for the name</li>
 * <li>an Integer for the id</li>
 * <li>a Node for the current Task</li>
 * <li>a booleanto know if the worker is in work or not</li>
 * </ul>
 * @author Cynthia Maillard et Jérémy Thiébaud
 * @version version 1.0
 */

public class Worker {

    protected String name;
    protected int id;
    protected Node currentTask;
    protected boolean inWork;


    /**
     * <b> Constuctor Worker</b>
     *
     * Creates and edge between the two nodes in parameters
     *
     * @param id id of the worker
     */
    public Worker(int id) {
        this.name = "Worker_"+id;
        this.id = id;
        this.inWork = false;  
    }

    /**
     * <b>Function isInWork </b>
     *
     * return the work state of the worker
     *
     * @return the work state of the worker
     */
    public boolean isInWork() {
        return inWork;
    }


    /**
     * <b>Function setInWork </b>
     *
     * set the work state of the worker
     *
     */
    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    /**
     * <b>Function setCurrentTask </b>
     *
     * set the current task of the worker
     *
     */
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
