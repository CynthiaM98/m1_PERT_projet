import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The class Graf is used to represent a directed graph used to represent a PERT chart
 * A Graf have 2 attributs :
 * <ul>
 * <li>adjList is a Map< Node, SortedSet< Edge >> which represent de adjacency list of the graph</li>
 * <li>name of the graph (String)</li>
 * </ul>
 *
 * @author Cynthia Maillard et Jérémy Thiébaud
 * @version version 1.0
 */

class Graf {

    protected Map<Node, SortedSet<Edge>> adjList;
    protected String name;
    protected Node startNode;
    protected Node endNode;
    protected int totalTime;

    /**
     * <b> Getter the name of the graf</b>
     * <p>
     * Return the name of the graf
     *
     * @return the name of the graf
     */
    public String getName() {
        return name;
    }

    /**
     * <b> Getter the first node of the graf</b>
     * <p>
     * Return the first node of the graf
     *
     * @return the first node of the graf
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * <b> Getter the last node of the graf</b>
     * <p>
     * Return the last node of the graf
     *
     * @return the last node of the graf
     */
    public Node getEndNode() {
        return endNode;
    }

    /**
     * <b> Getter the total construction time of the graf</b>
     * <p>
     * Return the total construction time of the graf
     *
     * @return the total construction time of the graf
     */
    public int getTotalTime() {
        return totalTime;
    }

    /**
     * <b>Builder empty graf</b>
     * <p>
     * Create an empty graf with name "Graf"
     */

    public Graf() {
        this("Graf");
    }

    /**
     * <b>Builder empty graf with name</b>
     * <p>
     * Create an empty graf with the name chosen
     *
     * @param name name of the graf
     */
    public Graf(String name) {
        this.name = name;
        this.adjList = new TreeMap<Node, SortedSet<Edge>>();
        this.startNode = new Node(-1);
        addNode(startNode);
        this.totalTime = 0;
    }

    public Map<Node, SortedSet<Edge>> getAdjList() {
        return adjList;
    }

    /**
     * <b>Function setName</b>
     * <p>
     * Update the name of the graf
     *
     * @param name The new name of the graf
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>Function addNode with Node in parameter</b>
     * <p>
     * add the node in parameter into the graf
     *
     * @param n Is the Node to add into the graf
     */
    public void addNode(Node n) {
        adjList.put(n, new TreeSet<Edge>());
    }


    public void addEndNode() {
        this.endNode = new Node(getAllNodes().size() - 1);
        addNode(endNode);
    }


    /**
     * <b>Function addNode with the nnode's number in parameter</b>
     * <p>
     * create a node with the ident in parameter and add it into the graf
     *
     * @param idNode Is the node's ident to add into the graf
     */
    public void addNode(int idNode) {
        Node n = new Node(idNode);
        addNode(n);
    }

    /**
     * <b>Function removeNode with the Node in parameter</b>
     * <p>
     * remove the Node in parameter from the graf
     *
     * @param n Is the Node to remove from the graf
     */
    public void removeNode(Node n) {
        if (adjList.containsKey(n)) {
            List<Edge> edges = getIncidentEdges(n);
            for (Edge e : edges) {
                removeEdge(e);
            }
            adjList.remove(n);
        }

    }

    /**
     * <b>Function removeNode with the node's number in parameter</b>
     * <p>
     * remove the node with the number in parameter from the graf
     *
     * @param idNode Is the node's number to remove from the graf
     */
    public void removeNode(int idNode) {
        Node n = new Node(idNode);
        removeNode(n);
    }

    public Node getNode(int idNode) {
        for (Node n : adjList.keySet()) {
            if (n.getNumber() == idNode) {
                return n;
            }
        }
        return new Node(idNode);
    }

    /**
     * <b>Function addEdge with Nodes in parameter</b>
     * <p>
     * create an edge between 2 nodes in the graph
     *
     * @param nFrom Is the Node where the edge begin
     * @param nTo   Is the Node where the edge end
     */
    public void addEdge(Node nFrom, Node nTo) {


        if (!adjList.containsKey(nTo)) {
            adjList.put(nTo, new TreeSet<Edge>());
        }
        if (!adjList.containsKey(nFrom)) {
            adjList.put(nFrom, new TreeSet<Edge>());
        }
        adjList.get(nFrom).add(new Edge(nFrom, nTo));

    }

    /**
     * <b>Function addEdge with weight and with Nodes in parameter</b>
     * <p>
     * create an edge weighted between 2 nodes in the graph
     *
     * @param nFrom  Is the Node where the edge begin
     * @param nTo    Is the Node where the edge end
     * @param weight Is the weight of the edge
     */
    public void addEdge(Node nFrom, Node nTo, int weight) {

        if (!adjList.containsKey(nTo)) {
            adjList.put(nTo, new TreeSet<Edge>());
        }
        if (!adjList.containsKey(nFrom)) {
            adjList.put(nFrom, new TreeSet<Edge>());
        }
        adjList.get(nFrom).add(new Edge(nFrom, nTo, weight));

    }

    /**
     * <b>Function removeEdge with Nodes in parameter</b>
     * <p>
     * delete the edge between 2 nodes from the graph
     *
     * @param nFrom Is the Node where the edge begin
     * @param nTo   Is the Node where the edge end
     */
    public void removeEdge(Node nFrom, Node nTo) {
        if (adjList.containsKey(nFrom) && adjList.get(nFrom).contains(new Edge(nFrom, nTo))) {
            adjList.get(nFrom).remove(new Edge(nFrom, nTo));
        }
    }

    /**
     * <b>Function removeEdge with nodes numbers in parameter</b>
     * <p>
     * delete the edge between 2 nodes from the graph
     *
     * @param idNodeFrom Is the node's number where the edge begin
     * @param idNodeTo   Is the node's number of the node where the edge end
     */
    public void removeEdge(int idNodeFrom, int idNodeTo) {
        Node nFrom = new Node(idNodeFrom);
        Node nTo = new Node(idNodeTo);
        removeEdge(nFrom, nTo);
    }

    /**
     * <b>Function removeEdge with the edge in parameter</b>
     * <p>
     * delete the edge in parameter
     *
     * @param e Is the Edge which will be delete from the graph
     */
    public void removeEdge(Edge e) {
        removeEdge(e.getNodeFrom(), e.getNodeTo());
    }

    /**
     * <b>Function getSuccessors with Nodes in parameter</b>
     * <p>
     * Get the list of nodes which success to the node in parameter
     *
     * @param n Is the Node that we want all successors
     * @return list of successors
     */
    public List<Node> getSuccessors(Node n) {
        List<Node> listSuccessors = new ArrayList<Node>();
        for (Edge e : adjList.get(n)) {
            listSuccessors.add(e.getNodeTo());
        }
        return listSuccessors;
    }

    /**
     * <b>Function getSuccessors with the number of node in parameter</b>
     * <p>
     * Get the list of nodes which success to the node with the number in parameter
     *
     * @param idNode Is the node's number that we want all successors
     * @return list of successors
     */
    public List<Node> getSuccessors(int idNode) {
        Node n = new Node(idNode);
        return getSuccessors(n);
    }

    /**
     * <b>Function getOutEdges with the Node in parameter</b>
     * <p>
     * Get the list of all edges which begin from the node in parameter
     *
     * @param n Is the Node from which we find the list of edges
     * @return list of out edges
     */
    public List<Edge> getOutEdges(Node n) {
        List<Edge> listE = new ArrayList<>();
        for (Edge e : adjList.get(n)) {
            listE.add(e);
        }
        return listE;
    }

    /**
     * <b>Function getOutEdges with the number of the node in parameter</b>
     * <p>
     * Get the list of all edges which begin from the node with the number in parameter
     *
     * @param idNode Is the node's number from which we find the list of edges
     * @return list of out edges
     */
    public List<Edge> getOutEdges(int idNode) {
        Node n = new Node(idNode);
        return getOutEdges(n);
    }

    /**
     * <b>Function getInEdges with the Node in parameter</b>
     * <p>
     * Get the list of all edges which come into the node in parameter
     *
     * @param n Is the Node where which we find the list of edges
     * @return list of in edges
     */
    public List<Edge> getInEdges(Node n) {
        List<Edge> listE = new ArrayList<>();
        for (Node node : adjList.keySet()) {
            for (Edge e : adjList.get(node)) {
                if (e.getNodeTo().equals(n)) {
                    listE.add(e);
                }
            }
        }
        return listE;
    }

    /**
     * <b>Function getInEdges with the number of the node in parameter</b>
     * <p>
     * Get the list of all edges which come into the node with the number in parameter
     *
     * @param idNode Is the node's number where which we find the list of edges
     * @return list of in edges
     */
    public List<Edge> getInEdges(int idNode) {
        Node n = new Node(idNode);
        return getInEdges(n);
    }

    /**
     * <b>Function getIncidentEdges with the Node in parameter</b>
     * <p>
     * Get the list of all edges which come into and come from the node in parameter
     *
     * @param n Is the Node where which we find the list of edges
     * @return list of edges from and to the node
     */
    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> listOutEdge = getOutEdges(n);
        List<Edge> listInEdge = getInEdges(n);
        ListIterator<Edge> iter = listInEdge.listIterator();
        while (iter.hasNext()) {
            if (n.equals(iter.next().getNodeFrom())) {
                iter.remove();
            }
        }
        listOutEdge.addAll(listInEdge);

        return listOutEdge;
    }

    /**
     * <b>Function getIncidentEdges with the number of the node in parameter</b>
     * <p>
     * Get the list of all edges which come into and come from the node with the number in parameter
     *
     * @param idNode Is the node's number where which we find the list of edges
     * @return list of edges from and to the node
     */
    public List<Edge> getIncidentEdges(int idNode) {
        Node n = new Node(idNode);
        return getIncidentEdges(n);
    }

    /**
     * <b>Function getAllNodes</b>
     * <p>
     * Get the list of all nodes in the graph
     *
     * @return list of all nodes in the graph
     */
    public List<Node> getAllNodes() {
        Set<Node> keys = adjList.keySet();
        List<Node> list = new ArrayList<>(keys);
        return list;
    }

    /**
     * <b>Function getAllEdges</b>
     * <p>
     * Get the list of all edges in the graph
     *
     * @return list of all edges in the graph
     */
    public List<Edge> getAllEdges() {
        List<Edge> listE = new ArrayList<>();
        for (Node n : getAllNodes()) {
            listE.addAll(getOutEdges(n));
        }
        return listE;
    }


    /**
     * <b>Function getReverseGraph</b>
     * <p>
     * Get the reverse graph of the current graph
     *
     * @return Graf which is the reverse graph of the current graph
     */
    public Graf getReverseGraph() {
        Graf gT = new Graf();
        List<Node> listNode = new ArrayList<Node>();
        listNode = getAllNodes();
        for (Node n : listNode) {
            gT.addNode(n);
        }
        for (Node n : listNode) {
            for (Edge e : adjList.get(n)) {
                gT.addEdge(e.getNodeTo(), e.getNodeFrom(), e.getWeight());
            }
        }
        return gT;
    }

    /**
     * <b>Function getTransitiveClosure</b>
     * <p>
     * Add all edges needed to create all transitives closures
     *
     * @return Graf with all edge to have the transitive closure of the current graph
     */
    public Graf getTransitiveClosure() {
        Graf g = new Graf();
        for (Node n : getAllNodes()) {
            g.addNode(n);
        }
        for (Edge e : getAllEdges()) {
            g.addEdge(e.getNodeFrom(), e.getNodeTo());
        }
        for (Node n : g.getAllNodes()) {
            List<Node> nodesToGoTo = new ArrayList<>();
            getDFSrec(n, nodesToGoTo);
            nodesToGoTo.remove(n);
            for (Node nodeToGoTo : nodesToGoTo) {
                g.addEdge(n, nodeToGoTo);
            }
        }

        return g;
    }

    /**
     * <b>Function getDFS without parameter</b>
     * <p>
     * Call the DFS with the first node of the graph
     *
     * @return List of node sort in order of visit
     */
    public List<Node> getDFS() {
        return getDFS((Node) adjList.keySet().toArray()[adjList.keySet().size() - 1]);
    }

    /**
     * <b>Function getDFS start node in parameter</b>
     * <p>
     * Call the DFS recursive function to do the DFS
     *
     * @param n Node to begin the DFS
     * @return List of node sort in order of visit
     */
    public List<Node> getDFS(Node n) {

        List<Node> listDFS = new ArrayList<>();
        getDFSrec(n, listDFS);
        return listDFS;
    }

    /**
     * <b>Function getDFS rec</b>
     * <p>
     * Recursive function to visite all nodes in the graph.
     * Is the Depth First Search
     *
     * @param n       Current node visited
     * @param listDFS List used to add node in order of visit
     * @return List of node with the lastest node visited at the end
     */
    private void getDFSrec(Node n, List<Node> listDFS) {
        listDFS.add(n);

        for (Edge e : adjList.get(n)) {
            if (!listDFS.contains(e.getNodeTo())) {
                getDFSrec(e.getNodeTo(), listDFS);
            }
        }
    }


    /**
     * <b>Function getBFS without parameter</b>
     * <p>
     * Call the BFS with the first node of the graph
     *
     * @return List of node sort in order of visit
     */
    public List<Node> getBFS() {
        return getBFS((Node) adjList.keySet().toArray()[adjList.keySet().size() - 1]);
    }

    /**
     * <b>Function getBFS start node in parameter</b>
     * <p>
     * Call the BFS recursive function to do the BFS
     *
     * @param n Node to begin the BFS
     * @return List of node sort in order of visit
     */
    public List<Node> getBFS(Node n) {

        List<Node> listBFS = new ArrayList<>();
        listBFS.add(n);
        getBFSrec(n, listBFS);
        return listBFS;
    }

    /**
     * <b>Function getBFS rec</b>
     * <p>
     * Recursive function to visite all nodes in the graph.
     * Is the Breadth First Search
     *
     * @param n       Current node visited
     * @param listBFS List used to add node in order of visit
     * @return List of node with the lastest node visited at the end
     */
    private void getBFSrec(Node n, List<Node> listBFS) {
        List<Edge> childrens = new ArrayList<>();

        for (Edge e : adjList.get(n)) {
            childrens.add(e);
        }

        for (Edge e : adjList.get(n)) {
            if (listBFS.contains(e.getNodeTo())) {
                childrens.remove(e);
            } else {
                listBFS.add(e.getNodeTo());
            }
        }
        for (Edge e : childrens) {
            getBFSrec(e.getNodeTo(), listBFS);
        }
    }


    public int getWeightOfEdge(Node nFrom, Node nTo) {
        List<Edge> listE = getOutEdges(nFrom);
        for (Edge e : listE) {
            if (nTo.equals(e.getNodeTo())) {
                return e.getWeight();
            }
        }
        return 0;
    }


    public void setEarliestTimeNode() {
        List<Node> nodeTime = getAllNodes();
        //init
        for (Node node : nodeTime) {
            if (node.equals(this.startNode)) {
                node.setEarliestTime(0);
            } else {
                node.setEarliestTime(-0xfffffe);
            }
        }
        int nbIter = 1;
        boolean modified = true;
        while (nbIter < nodeTime.size() + 1 && modified) {
            modified = false;
            for (Edge e : getAllEdges()) {
                if (e.getNodeTo().getEarliestTime() < e.getNodeFrom().getEarliestTime() + getWeightOfEdge(e.getNodeFrom(), e.getNodeTo())) {
                    e.getNodeTo().setEarliestTime(e.getNodeFrom().getEarliestTime() + getWeightOfEdge(e.getNodeFrom(), e.getNodeTo()));
                    modified = true;
                }
                if (totalTime < e.getNodeTo().getEarliestTime()) {
                    totalTime = e.getNodeTo().getEarliestTime();
                }
            }
            nbIter++;
        }
    }

    public void setLatestTimeNode() {
        Graf reverseGraf = getReverseGraph();
        List<Node> nodeTime = reverseGraf.getAllNodes();
        //init
        for (Node node : nodeTime) {
            node.setLatestTime(totalTime);
        }
        int distance = 0;
        for (Node node : nodeTime) {
            for (Node succ : reverseGraf.getSuccessors(node)) {
                distance = reverseGraf.getWeightOfEdge(node, succ);
                if (succ.getLatestTime() > node.getLatestTime() - distance) {
                    succ.setLatestTime(node.getLatestTime() - distance);
                }
            }
        }
        Graf g = reverseGraf.getReverseGraph();
        this.adjList = g.getAdjList();
    }


    public void printNodeTime() {
        for (Node node : getAllNodes()) {
            if (node.equals(startNode)) {
                System.out.println(" [Start Node] " + "E : " + node.getEarliestTime() + " / L :" + node.getLatestTime() + "\n");

            } else if (node.equals(endNode)) {
                System.out.println(" [ Final Node ] " + "E : " + node.getEarliestTime() + " / L :" + node.getLatestTime() + "\n");

            } else {
                System.out.println(" [ " + node.getNumber() + "] " + "E : " + node.getEarliestTime() + " / L :" + node.getLatestTime() + "\n");

            }
        }
    }

    /**
     * <b>Function toDotString</b>
     * <p>
     * Create the dot string from the structure of the graph
     *
     * @return String which represent the content of the file
     */
    public String toDotString() {
        String grafToDot = "";
        grafToDot = "}\n" + grafToDot;

        for (Node n : getAllNodes()) {
            grafToDot = n.getNumber() + "[xlabel=\"[" + n.getEarliestTime() + "|" + n.getLatestTime() + "]\"" + "];\n" + grafToDot;
        }

        for (Edge e : getAllEdges()) {
            grafToDot = "	" + e.printEdge() + ";\n" + grafToDot;
        }

        grafToDot = "digraph " + name + " {\n" + grafToDot;
        return grafToDot;
    }

    /**
     * <b>Function toDotFile</b>
     * <p>
     * Create the dot file from the function toDotString
     */
    public void toDotFile(String name) throws IOException {

        String fileContent = toDotString();

        BufferedWriter writer = new BufferedWriter(new FileWriter(name));
        writer.write(fileContent);
        writer.close();
    }


    /**
     * <b>Function equals</b>
     * <p>
     * Compare two graph
     *
     * @param o Object which is a graf in this case
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
        Graf g = (Graf) o;

        for (Node n : g.getAllNodes()) {
            if (!getAllNodes().contains(n)) {
                return false;
            }
        }
        for (Node n : getAllNodes()) {
            if (!g.getAllNodes().contains(n)) {
                return false;
            }
        }
        for (Edge e : g.getAllEdges()) {
            if (!getAllEdges().contains(e)) {
                return false;
            }
        }
        for (Edge e : getAllEdges()) {
            if (!g.getAllEdges().contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>Function hashCode</b>
     * <p>
     * Hash the graph
     *
     * @return hash code which represent the graph
     */
    @Override
    public int hashCode() {
        return Objects.hash(adjList, name);
    }


    /**
     * <b>Function getCriticalPathList</b>
     * <p>
     * Give the critical path of the current graf
     *
     * @return the list of nodes which represents the critical path
     */
    public ArrayList<Node> getCriticalPathList() {
        ArrayList<Node> res = new ArrayList<>();
        res.add(startNode);
        return getCriticalRecursivity(startNode, res);
    }

    /**
     * <b>Function getCriticalRecursivity</b>
     * <p>
     * Give the critical path of the current graf recursively
     *
     * @return the list of nodes which represents the critical path
     */
    public ArrayList<Node> getCriticalRecursivity(Node n, ArrayList<Node> res) {
        for (Edge e : this.getOutEdges(n)) {
            if (e.getNodeTo().getEarliestTime() == e.getNodeTo().getLatestTime()) {
                res.add(e.getNodeTo());
                return getCriticalRecursivity(e.getNodeTo(), res);
            }
        }
        return res;
    }


}