import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {

        ArrayList<String> listConstruGraph = readFile();
        Graf g = construGraf(listConstruGraph);

        System.out.println(g.toDotString());
        g.setEarliestTimeNode();
        g.setLatestTimeNode();
        g.printNodeTime();




    }

    public static ArrayList<String> readFile() throws IOException {
        ArrayList<String> listInstr = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("./src/main/java/testCours.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            listInstr.add(line);
        }
        br.close();

        return listInstr;
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

    public static Graf construGraf(ArrayList<String> listInstr){

        Graf g = new Graf();
        for (String s : listInstr) {
            ArrayList<String> instr = supprSpace(readLine(s));
            String identNode = instr.get(0);
            Node n = new Node(identNode, instr.get(1)); //create the node
            n.setTimeExec(Integer.parseInt(instr.get(2)));
            g.addNode(n);
            //System.out.println(g.getNode(identNode).toString());

        }

        for (String s : listInstr){
            ArrayList<String> instr = supprSpace(readLine(s));
            String identNode = instr.get(0);
            if(instr.get(3).equals("-")){
                System.out.println(identNode);
                g.addEdge(g.startNode, g.getNode(identNode), 0);

            }else{
                for (int i = 3; i< instr.size(); i++){
                    //System.out.println(identNode);
                    g.addEdge(g.getNode(instr.get(i)), g.getNode(identNode), g.getNode(instr.get(i)).getTimeExec());
                }
            }
        }

        System.out.println(g.toDotString());

        g.addEndNode();
        return addEndEdge(g);
    }

    public static Graf addEndEdge(Graf g){
        for (Node n: g.getAllNodes()) {
            System.out.println(n.toString());
            List<Edge> listE = g.getOutEdges(n);
            if(listE.isEmpty() && n!=g.endNode){
                System.out.println(n.toString());
                g.addEdge(n, g.endNode, n.getTimeExec());
            }
        }
        return g;
    }


}
