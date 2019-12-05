import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, ArrayList<String>> listConstruGraph = readFile();
        Graf g = construGraf(listConstruGraph);
        //System.out.println(g.toDotString());
        g.setEarliestTimeNode();
        g.setLatestTimeNode();
        g.printNodeTime();
        ArrayList<Node> critList=g.getCriticalPathList();
        for (Node node : critList) {
            System.out.println(node.toString());
        }

        //  SwitchOperatedTextMenu();


    }

    public static Map<String, ArrayList<String>> readFile() throws IOException {
        Map<String, ArrayList<String>> mapInfoGraph = new HashMap<String, ArrayList<String>>();
        ArrayList<String> listInstr = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("./src/main/java/testCours.txt"));
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

    public static ArrayList<String> supprSpace(ArrayList<String> listString) {
        ArrayList<String> listWithoutSpace = new ArrayList<String>();
        for (String s : listString) {
            listWithoutSpace.add(s.replaceAll("\\s", ""));
        }
        return listWithoutSpace;
    }

    public static Graf construGraf(Map<String, ArrayList<String>> listInstr) {

        Graf g = new Graf();
        for (String s : listInstr.keySet()) {
            Node n = new Node(findIdOfNode(listInstr, s), listInstr.get(s).get(1)); //create the node

            n.setTimeExec(Integer.parseInt(listInstr.get(s).get(2)));
            //System.out.println("node : " + n.toString() + " time : " + n.getTimeExec());
            g.addNode(n);

        }

        for (String s : listInstr.keySet()) {

            if (listInstr.get(s).get(3).equals("-")) {
                //System.out.println(g.getNode(findIdOfNode(listInstr,s)).toString());
                g.addEdge(g.startNode, g.getNode(findIdOfNode(listInstr, s)), 0);

            } else {
                for (int i = 3; i < listInstr.get(s).size(); i++) {
                    //System.out.println(g.getNode(findIdOfNode(listInstr,listInstr.get(s).get(i))).toString() + " - > " + g.getNode(findIdOfNode(listInstr,s)).toString());
                    g.addEdge(g.getNode(findIdOfNode(listInstr, listInstr.get(s).get(i))), g.getNode(findIdOfNode(listInstr, s)), g.getNode(findIdOfNode(listInstr, listInstr.get(s).get(i))).getTimeExec());
                }
            }
        }

        g.addEndNode();
        return addEndEdge(g);
    }

    public static Graf addEndEdge(Graf g) {
        for (Node n : g.getAllNodes()) {
            //System.out.println(n.toString());
            List<Edge> listE = g.getOutEdges(n);
            if (listE.isEmpty() && n != g.endNode) {
                //System.out.println("end : " + n.toString());
                g.addEdge(n, g.endNode, n.getTimeExec());
            }
        }
        return g;
    }

    public static int findIdOfNode(Map<String, ArrayList<String>> map, String nameNode) {
        List<String> indexes = new ArrayList<String>(map.keySet());
        return indexes.indexOf(nameNode);
    }


    public static void SwitchOperatedTextMenu() {
        System.out.print("Choose menu item:\n");
        Scanner menuChoiceScan = new Scanner(System.in);
        // print menu
        System.out.println("1. Read a construction description file\n");
        System.out.println("2. Draw the corresponding PERT chart\n");
        System.out.println("3. Save the graph as a file\n");
        System.out.println("4. Compute and display the total duration of the construction\n");
        System.out.println("5. Compute and display the earliest and latest start times of each task\n");
        System.out.println("6. Compute and display the critical path(s) \n");
        System.out.println("7. Enter the number of workers\n");
        System.out.println("8. Choose an assignment strategy\n");
        System.out.println("9. Compute and display the construction’s duration according the chosen strategy\n");
        System.out.println("10. Get the list of all edges entering a node\n");
        System.out.println("11. Display one more time this menu\n");
        System.out.println("0. Quit this application\n");

        // handle user commands
        boolean quit = false;
        int menuItem;
        Graf myGraph = new Graf();
        do {
            System.out.print("Choose menu item:\n");
            menuItem = menuChoiceScan.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println("Read a construction description file\n");
                    myGraph = new Graf();
                    System.out.println("Done \n");
                    break;

                case 2:
                    System.out.println("Draw the corresponding PERT chart\n");
                    System.out.println("Done \n");
                    break;

                case 3:
                    System.out.println("Save the graph as a file\n");
                    System.out.println("Done \n");
                    break;

                case 4:
                    System.out.println("Compute and display the total duration of the construction\n");
                    System.out.println("Done \n");
                    break;

                case 5:
                    System.out.println("Compute and display the earliest and latest start times of each task\n");
                    System.out.println("Done \n");
                    break;

                case 6:
                    System.out.println("Compute and display the critical path(s)\n");
                    System.out.println("Done \n");
                    break;

                case 7:
                    System.out.println("Enter the number of workers\n");
                    System.out.println("Done \n");
                    break;

                case 8:
                    System.out.println("Choose an assignment strategy\n");
                    System.out.println("Done \n");
                    break;


                case 9:
                    System.out.println("Compute and display the construction’s duration according the chosen strategy\n");
                    System.out.println("Done \n");
                    break;

                case 10:
                    System.out.println("Get the list of all edges entering a node\n");
                    System.out.println("Done \n");
                    break;

                case 11:
                    System.out.println("1. Read a construction description file\n");
                    System.out.println("2. Draw the corresponding PERT chart\n");
                    System.out.println("3. Save the graph as a file\n");
                    System.out.println("4. Compute and display the total duration of the construction\n");
                    System.out.println("5. Compute and display the earliest and latest start times of each task\n");
                    System.out.println("6. Compute and display the critical path(s) \n");
                    System.out.println("7. Enter the number of workers\n");
                    System.out.println("8. Choose an assignment strategy\n");
                    System.out.println("9. Compute and display the construction’s duration according the chosen strategy\n");
                    System.out.println("10. Get the list of all edges entering a node\n");
                    System.out.println("11. Display one more time this menu\n");
                    System.out.println("0. Quit this application\n");
                    break;

                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);
        System.out.println("Bye-bye!");
    }
}
