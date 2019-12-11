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

    public Edge(Node nodeFrom, Node nodeTo){
        this(nodeFrom, nodeTo, 0);
        this.isWeighted = false;
    }

    public Edge(Node nodeFrom, Node nodeTo, int weight){
        this.nodeFrom = nodeFrom;
        this.nodeTo = nodeTo;
        this.weight = weight;
        this.isWeighted = true;
    }


    public String printEdge(){
        return toString();
    }

    public String toString(){
        if (!isWeighted) {
            return nodeFrom.getNumber() + " -> " + nodeTo.getNumber();
        }

        return nodeFrom.getNumber() + " -> " + nodeTo.getNumber() + "[label=\"" + weight + "\"]";
    }

    public Node getNodeFrom(){
        return nodeFrom;
    }

    public Node getNodeTo(){
        return nodeTo;
    }

    public int getWeight() {
        return weight;
    }


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

    @Override
    public int hashCode() {
        return Objects.hash(nodeFrom, nodeTo, weight);
    }
}