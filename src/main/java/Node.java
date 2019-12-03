/**
 * The class Node is used to be add into a graph
 * A Node have 2 attributs :
 * <ul>
 * <li>an integer for id</li>
 * <li>a string for a name</li>
 * </ul>
 * @author Cynthia Maillard et Jérémy Thiébaud
 * @version version 1.0
 */

class Node implements Comparable<Node> {
    private String ident;
    private String name;
    private int timeExec;
    private int earliestTime;
    private int latestTime;


    /**
     * <b>Builder Node</b>
     *
     * Create Node with the number and the name in parameter
     *
     * @param ident
     *		Is the id of the node
     *
     * @param name
     *		Is the name of the node
     */
    public Node(String ident, String name){
        this.ident = ident;
        this.name = name;
    }

    /**
     * <b>Builder Node with only the number</b>
     *
     * Create Node with the number in parameter
     * The name is an empty string
     *
     * @param ident
     *		Is the id of the node
     */
    public Node(String ident){
        this(ident, "");
    }

    /**
     * <b>Function getIdent</b>
     *
     * Get the node's ident
     *
     * @return the node's ident (int)
     */
    public String getIdent(){
        return ident;
    }

    /**
     * <b>Function setIdent</b>
     *
     * Update the node's ident with the ident in parameter
     *
     * @param id
     *		Is the new ident of the node
     */
    public void setIdent(String id){
        this.ident = id;
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

    /**
     * <b>Function toString</b>
     *
     * Used to print the node
     *
     * @return the number and the name of the node in a String
     */
    public String toString() {
        String ret = "[" + ident + "]";
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

        if (node.getIdent() == this.getIdent()) {
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

        return node.getIdent() == this.getIdent() &&
                node.getName().compareTo(this.getName()) == 0;
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
}
