import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Map<String, ArrayList<String>> listConstruGraph = readFile();
        Graf g = construGraf(listConstruGraph);
        //System.out.println(g.toDotString());
        g.printNodeTime();

        ArrayList<Node> critList = g.getCriticalPathList();
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
        System.out.println("-----------------MENU----------------");
        System.out.println("1. Read a construction description file");
        System.out.println("2. Draw the corresponding PERT chart");
        System.out.println("3. Save the graph as a file");
        System.out.println("4. Compute and display the total duration of the construction");
        System.out.println("5. Compute and display the earliest and latest start times of each task");
        System.out.println("6. Compute and display a critical path");
        System.out.println("7. Compute the assignment strategy n°1 : Random assignment");
        System.out.println("8. Compute the assignment strategy n°2 :"); //TODO add the name of the strategy
        System.out.println("9. Compute the assignment strategy n°3 :"); //TODO add the name of the strategy
        System.out.println("10. Compute the assignment strategy n°4 :");//TODO add the name of the strategy
        System.out.println("11. Display one more time this menu");
        System.out.println("0. Quit this application");
        System.out.println("----------------------------------");

        // handle user commands
        boolean quit = false;
        boolean init = false;
        int menuItem;
        Scanner menuScan = new Scanner(System.in);
        Graf myGraph = new Graf();
        do {
            System.out.print("Choose menu item:\n");
            menuItem = menuChoiceScan.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println("You've chosen option #1 : Read a construction description file\n");
                    System.out.println("Please enter the desired location of the description file \n");
                    try {
                        Map<String, ArrayList<String>> listConstruGraph = readFile();
                        myGraph = construGraf(listConstruGraph);
                    }
                    catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                    myGraph = new Graf();
                    init = true;
                    System.out.println("Done \n ----------------------------------\n");
                    break;

                case 2:
                    if (init) {
                        System.out.println("You've chosen option #2 : Draw the corresponding PERT chart\n");
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 3:
                    if (init) {
                        System.out.println("You've chosen option #3 : Save the graph as a file\n");
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 4:
                    if (init) {
                        System.out.println("You've chosen option #4 : Compute and display the total duration of the construction\n");
                        myGraph.setEarliestTimeNode();
                        myGraph.setLatestTimeNode();
                        System.out.println("The total duration of the construction has a value of "+ myGraph.getEndNode().getLatestTime());
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 5:
                    if (init) {
                        System.out.println("You've chosen option #5 : Compute and display the earliest and latest start times of each task\n");
                        myGraph.printNodeTime();
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 6:
                    if (init) {
                        System.out.println("You've chosen option #6 : Compute and display a critical path\n");
                        ArrayList<Node> critList = myGraph.getCriticalPathList();
                        for (Node node : critList) {
                            System.out.println(node.toString());
                        } //TODO Change display skin ?
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 7:
                    if (init) {
                        System.out.println("You've chosen option #7 : Compute the assignment strategy n°1 : Random assignment\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        int amountOfWorkers = menuScan.nextInt();
                        myGraph.randomStrategy(amountOfWorkers);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 8:
                    if (init) {
                        System.out.println("You've chosen option #8 : Compute the assignment strategy n°2 :\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        int amountOfWorkers = menuScan.nextInt();
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;


                case 9:
                    if (init) {
                        System.out.println("You've chosen option #9 : Compute the assignment strategy n°3 :\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        int amountOfWorkers = menuScan.nextInt();
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 10:
                    if (init) {
                        System.out.println("You've chosen option #10 : GCompute the assignment strategy n°4 :\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        int amountOfWorkers = menuScan.nextInt();
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 11:
                    System.out.println("-----------------MENU----------------");
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
                    System.out.println("----------------------------------");
                    break;

                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        while (!quit);
        System.out.println("Bye-bye!");
    }
}
