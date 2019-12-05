import java.io.*;
import java.util.*;

public class Main {
    public static void main (String[] args) throws IOException {

        Map<String, ArrayList<String>> listConstruGraph = readFile();

        Graf g = construGraf(listConstruGraph);

        //System.out.println(g.toDotString());
        g.setEarliestTimeNode();
        g.setLatestTimeNode();
        g.printNodeTime();




    }

    public static Map<String, ArrayList<String>> readFile() throws IOException {
        Map<String, ArrayList<String>> mapInfoGraph = new HashMap<String, ArrayList<String>>();
        ArrayList<String> listInstr = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("./src/main/java/test.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            listInstr = supprSpace(readLine(line));
            mapInfoGraph.put(listInstr.get(0), listInstr);

        }
        br.close();

        return mapInfoGraph;
    }

    public static ArrayList<String> readLine(String line) {
        String[] values = line.split(",");
        return new ArrayList(Arrays.asList(values));
    }

    public static ArrayList<String> supprSpace(ArrayList<String> listString){
        ArrayList<String> listWithoutSpace = new ArrayList<String>();
        for (String s : listString) {
            listWithoutSpace.add(s.replaceAll("\\s", ""));
        }
        return listWithoutSpace;
    }

    public static Graf construGraf(Map<String, ArrayList<String>> listInstr){

        Graf g = new Graf();
        for (String s :listInstr.keySet()) {
            Node n = new Node(findIdOfNode(listInstr,s), listInstr.get(s).get(1)); //create the node

            n.setTimeExec(Integer.parseInt(listInstr.get(s).get(2)));
            //System.out.println("node : " + n.toString() + " time : " + n.getTimeExec());
            g.addNode(n);

        }

        for (String s : listInstr.keySet()){

            if(listInstr.get(s).get(3).equals("-")){
                //System.out.println(g.getNode(findIdOfNode(listInstr,s)).toString());
                g.addEdge(g.startNode, g.getNode(findIdOfNode(listInstr,s)), 0);

            }else{
                for (int i = 3; i< listInstr.get(s).size(); i++){
                    //System.out.println(g.getNode(findIdOfNode(listInstr,listInstr.get(s).get(i))).toString() + " - > " + g.getNode(findIdOfNode(listInstr,s)).toString());
                    g.addEdge(g.getNode(findIdOfNode(listInstr,listInstr.get(s).get(i))), g.getNode(findIdOfNode(listInstr,s)), g.getNode(findIdOfNode(listInstr,listInstr.get(s).get(i))).getTimeExec());
                }
            }
        }

        g.addEndNode();
        return addEndEdge(g);
    }

    public static Graf addEndEdge(Graf g){
        for (Node n: g.getAllNodes()) {
            //System.out.println(n.toString());
            List<Edge> listE = g.getOutEdges(n);
            if(listE.isEmpty() && n!=g.endNode){
                //System.out.println("end : " + n.toString());
                g.addEdge(n, g.endNode, n.getTimeExec());
            }
        }
        return g;
    }

    public static int findIdOfNode(Map<String, ArrayList<String>> map, String nameNode){
        List<String> indexes = new ArrayList<String>(map.keySet());
        return indexes.indexOf(nameNode);
    }


}
