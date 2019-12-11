/**
 * The class Edge is used to be add into a graf to join two node
 * A Node have 2 attributs :
 * <ul>
 * <li>a Node where begin the egde</li>
 * <li>a Node where finish the edge</li>
 * <li>a int for the weight</li>
 * <li>a boolean to know if the edge is weighted</li>
 * </ul>
 * @author Cynthia Maillard et Jérémy Thiébaud
 * @version version 1.0
 */

import java.util.Objects;

class Edge implements Comparable<Edge>{
    private Node nodeFrom;
    private Node nodeTo;
    private int weight;
    private boolean isWeighted;

    /**
     * <b> Constuctor Edge</b>
     *
     * Creates and edge between the two nodes in parameters
     *
     * @param nodeFrom node the edge comes from
     * @param nodeTo node the edge goes to
     */
    public Edge(Node nodeFrom, Node nodeTo){
        this(nodeFrom, nodeTo, 0);
        this.isWeighted = false;
    }

    /**
     * <b> Constuctor Edge with weight</b>
     *
     * Creates and edge between the two nodes in parameters, with the weight in parameters
     *
     * @param nodeFrom node the edge comes from
     * @param nodeTo node the edge goes to
     * @param weight weight of the edge
     */
    public Edge(Node nodeFrom, Node nodeTo, int weight){
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.weight = weight;
        this.isWeighted = true;
    }

    /**
     * <b> Function to print the edge</b>
     *
     * Return the string reprsenting the edge
     *
     * @return the string reprsenting the edge
     */
    public String printEdge(){
        return toString();
    }

    /**
     * <b> Function to print the edge</b>
     *
     * Return the string reprsenting the edge
     *
     * @return the string reprsenting the edge
     */
    public String toString(){
        if (!isWeighted) {
            return nodeFrom.getNumber() + " -> " + nodeTo.getNumber();
        }

        return nodeFrom.getNumber() + " -> " + nodeTo.getNumber() + "[label=\"" + weight + "\"]";
    }

    /**
     * <b> Getter the start node of the edge</b>
     *
     * Return the start node of the edge
     *
     * @return the start node of the edge
     */
    public Node getNodeFrom(){
        return nodeFrom;
    }


    /**
     * <b> Getter the end node of the edge</b>
     *
     * Return the end node of the edge
     *
     * @return the end node of the edge
     */
    public Node getNodeTo(){
        return nodeTo;
    }


    /**
     * <b> Getter the weight of the edge</b>
     *
     * Return the weight of the edge
     *
     * @return the the weight of the edge
     */
    public int getWeight() {
        return weight;
    }

    /**
     * <b>Function compareTo</b>
     *
     * Used to compare the current edge with the edge in parameter
     *
     * @param edge the edge to compare with the current edge
     *
     * @return 0 if edges are similar, 1 or -1 if they are different
     */
    @Override
    public int compareTo(Edge edge) {

        if (edge.getNodeFrom().getNumber() < this.getNodeFrom().getNumber()) {
            return -1;
        }

        if (edge.getNodeFrom().getNumber() == this.getNodeFrom().getNumber()) {
            if (edge.getNodeTo().getNumber() < this.getNodeTo().getNumber()) {
                return -1;
            }

            if (edge.getNodeTo().getNumber() == this.getNodeTo().getNumber()) {
                return 0;
            }

            return 1;
        }

        return 1;
    }

    /**
     * <b>Function equals</b>
     *
     * Compare two edges
     *
     * @param o Object which is an edge in this case
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

        Edge edge = (Edge) o;

        return edge.getNodeTo() == this.getNodeTo() &&
                edge.getNodeFrom() == this.getNodeFrom();
    }

    /**
     * <b>Function hashCode</b>
     *
     * Hash the edge
     *
     * @return hash code which represent the edge
     */
    @Override
    public int hashCode() {
        return Objects.hash(nodeFrom, nodeTo, weight);
    }
}