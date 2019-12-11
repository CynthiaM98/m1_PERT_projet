/**
 * The class Node is used to be add into a graph like a task for a PERT chart
 * A Node have 2 attributs :
 * <ul>
 * <li>an integer for id</li>
 * <li>a string for a name</li>
 * </ul>
 * @author Cynthia Maillard et Jérémy Thiébaud
 * @version version 1.0
 */

class Node implements Comparable<Node> {
    private int number;
    private String name;
    private int timeExec;
    private int earliestTime;
    private int latestTime;


    /**
     * <b>Builder Node</b>
     *
     * Create Node with the number and the name in parameter
     *
     * @param number
     *		Is the id of the node
     *
     * @param name
     *		Is the name of the node
     */
    public Node(int number, String name){
        this.number = number;
        this.name = name;
    }

    /**
     * <b>Builder Node with only the number</b>
     *
     * Create Node with the number in parameter
     * The name is an empty string
     *
     * @param number
     *		Is the id of the node
     */
    public Node(int number){
        this(number, "");
    }

    /**
     * <b>Function getNumber</b>
     *
     * Get the node's number
     *
     * @return the node's number (int)
     */
    public int getNumber(){
        return number;
    }

    /**
     * <b>Function setNumber</b>
     *
     * Update the node's number with the number in parameter
     *
     * @param nb
     *		Is the new number of the node
     */
    public void setNumber(int nb){
        this.number = nb;
    }

    /**
     * <b>Function getName</b>
     *
     * Get the node's name
     *
     * @return the node's name (String)
     */
    public String getName(){
        return name;
    }

    /**
     * <b>Function setName</b>
     *
     * Update the node's name with the string in parameter
     *
     * @param name
     *		Is the new name of the node
     */
    public void setName(String name){
        this.name = name;
    }

    public void setTimeExec(int time){ this.timeExec = time; }

    public int getTimeExec() {
        return timeExec;
    }

    public int getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(int earliestTime) {
        this.earliestTime = earliestTime;
    }

    public int getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(int latestTime) {
        this.latestTime = latestTime;
    }

    /**
     * <b>Function toString</b>
     *
     * Used to print the node
     *
     * @return the number and the name of the node in a String
     */
    public String toString() {
        String ret = "";
        if(number == -99){
            ret = "[start]";
        }else if (number == 999){
            ret = "[end]";
        }else {
            ret = "[" + number + "]";
        }
        if (name != null) {
            ret += " " + name;
        }
        return ret;
    }


    /**
     * <b>Function compareTo</b>
     *
     * Used to compare the current node with the node in parameter
     *
     * @param node
     *		Is the node to compare with the current node
     *
     * @return 0 if nodes are similar, 1 or -1 if they are different
     */
    @Override
    public int compareTo(Node node) {
        if (node.getNumber() < this.getNumber()) {
            return -1;
        }

        if (node.getNumber() == this.getNumber()) {
            return 0;
        }

        return 1;
    }


    /**
     * <b>Function equals</b>
     *
     * Compare two nodes
     *
     * @param o
     *		Object which is a node in this case
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;

        return node.getNumber() == this.getNumber() &&
                node.getName().compareTo(this.getName()) == 0;
    }


    /**
     * <b>Function hashCode</b>
     *
     * Hash the node
     *
     * @return hash code which represent the node
     */
    @Override
    public int hashCode() {
        return number;
    }


}