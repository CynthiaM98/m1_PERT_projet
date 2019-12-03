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
            return nodeFrom.getIdent() + " -> " + nodeTo.getIdent();
        }

        return nodeFrom.getIdent() + " -> " + nodeTo.getIdent() + "[label=\"" + weight + "\"]";
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


        if (edge.getNodeFrom().getIdent() == this.getNodeFrom().getIdent()) {

            if (edge.getNodeTo().getIdent() == this.getNodeTo().getIdent()) {
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