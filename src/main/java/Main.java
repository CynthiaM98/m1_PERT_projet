import java.io.*;
import java.util.*;

public class Main {

    private static int strat = 0;
    private static int amountOfWorkers = 0;
    private static ArrayList<Worker> listOfWorker = new ArrayList<Worker>();
    private static Map<Node, Worker> assigmentTaskWorker = new HashMap<>();
    private static int totalTime = 0;
    private static Graf g = new Graf();
    private static Map<Node, Integer> currentAndFinishTask = new HashMap<>();
    private static Set<Node> availableTask = new HashSet<>();

    public static void main(String[] args) throws IOException {

        /*Map<String, ArrayList<String>> listConstruGraph = readFile("./src/main/java/testCours.txt");
        Graf g = construGraf(listConstruGraph);
        System.out.println(g.toDotString());
        g.printNodeTime();
        ArrayList<Node> critList = g.getCriticalPathList();
        for (Node node : critList) {
            System.out.println(node.toString());
        }
*/
        SwitchOperatedTextMenu();


    }

    public static Map<String, ArrayList<String>> readFile(String path) throws IOException {
        Map<String, ArrayList<String>> mapInfoGraph = new HashMap<String, ArrayList<String>>();
        ArrayList<String> listInstr = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(path));
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

        for (String s : listInstr.keySet()) {
            Node n = new Node(findIdOfNode(listInstr, s), listInstr.get(s).get(1)); //create the node

            n.setTimeExec(Integer.parseInt(listInstr.get(s).get(2)));
            g.addNode(n);

        }

        for (String s : listInstr.keySet()) {

            if (listInstr.get(s).get(3).equals("-")) {
                g.addEdge(g.startNode, g.getNode(findIdOfNode(listInstr, s)), 0);

            } else {
                for (int i = 3; i < listInstr.get(s).size(); i++) {
                    g.addEdge(g.getNode(findIdOfNode(listInstr, listInstr.get(s).get(i))), g.getNode(findIdOfNode(listInstr, s)), g.getNode(findIdOfNode(listInstr, listInstr.get(s).get(i))).getTimeExec());
                }
            }
        }

        g.addEndNode();
        return addEndEdge(g);
    }

    public static Graf addEndEdge(Graf g) {
        for (Node n : g.getAllNodes()) {
            List<Edge> listE = g.getOutEdges(n);
            if (listE.isEmpty() && n != g.endNode) {
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
        System.out.println("1. Read a construction description file / Reset the current graf");
        System.out.println("2. Draw the corresponding PERT chart");
        System.out.println("3. Save the graph as a file");
        System.out.println("4. Compute and display the total duration of the construction");
        System.out.println("5. Compute and display the earliest and latest start times of each task");
        System.out.println("6. Compute and display a critical path");
        System.out.println("7. Compute the assignment strategy n°1 : Critical path");
        System.out.println("8. Compute the assignment strategy n°2 : ");
        System.out.println("9. Compute the assignment strategy n°3 :");
        System.out.println("10. Compute the assignment strategy n°4 : Random assignment");
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
                    myGraph = new Graf();
                    System.out.println("You've chosen option #1 : Read a construction description file / Reset the current graf\n");
                    System.out.println("Please enter the desired location of the description file \n");
                    try {
                        String sourceFilePath = menuScan.nextLine();
                        Map<String, ArrayList<String>> listConstruGraph = readFile(sourceFilePath);
                        myGraph = construGraf(listConstruGraph);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    init = true;
                    System.out.println("Done \n ----------------------------------\n");
                    break;

                case 2:
                    if (init) {
                        System.out.println("You've chosen option #2 : Draw the corresponding PERT chart\n");
                        System.out.println(myGraph.toDotString());
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 3:
                    if (init) {
                        System.out.println("You've chosen option #3 : Save the graph as a file\n");
                        System.out.println("Please enter the desired location of the DOT file \n");
                        String dotFileLocation = menuChoiceScan.nextLine();
                        myGraph.toDotFile(dotFileLocation);
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
                        System.out.println("The total duration of the construction has a value of " + myGraph.getEndNode().getLatestTime());
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
                        if(critList.size()<=1){
                            System.out.println("There is no critical path in the current graf");
                        }
                        else {
                            for (Node node : critList) {
                                System.out.println(node.toString());
                            } //TODO Change display skin ?
                        }
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 7:
                    if (init) {
                        strat = 1;
                        System.out.println("You've chosen option #7 : Compute the assignment strategy n°1 : Critical path\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalTime);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 8:
                    if (init) {
                        strat = 2;
                        System.out.println("You've chosen option #8 : Compute the assignment strategy n°2 : \"\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalTime);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;


                case 9:
                    if (init) {
                        strat = 3;
                        System.out.println("You've chosen option #9 : Compute the assignment strategy n°3 :\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalTime);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 10:
                    if (init) {
                        strat = 4;
                        System.out.println("You've chosen option #10 : GCompute the assignment strategy n°4 : Random assignment\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalTime);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 11:
                    System.out.println("-----------------MENU----------------");
                    System.out.println("1. Read a construction description file / Reset the current graf");
                    System.out.println("2. Draw the corresponding PERT chart");
                    System.out.println("3. Save the graph as a file");
                    System.out.println("4. Compute and display the total duration of the construction");
                    System.out.println("5. Compute and display the earliest and latest start times of each task");
                    System.out.println("6. Compute and display a critical path");
                    System.out.println("7. Compute the assignment strategy n°1 : Critical path");
                    System.out.println("8. Compute the assignment strategy n°2 : ici");
                    System.out.println("9. Compute the assignment strategy n°3 : ");
                    System.out.println("10. Compute the assignment strategy n°4 : Random assignment");
                    System.out.println("11. Display one more time this menu");
                    System.out.println("0. Quit this application");
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


    public static void createListWorker(int amountOfWorkers){
        for (int i = 0; i < amountOfWorkers; i++){
            listOfWorker.add(new Worker(i+1));
        }
    }

    public static void execStrategie() {
        currentAndFinishTask.clear();
        totalTime = 0;


        for (Edge e : g.getOutEdges(g.getStartNode())) {
            availableTask.add(e.getNodeTo());
        }
        currentAndFinishTask.put(g.getEndNode(), 0);
        currentAndFinishTask.put(g.getStartNode(), 0);

        int workersWaiting = amountOfWorkers;

        while (!allTaskDone()) {

            for (Node n : currentAndFinishTask.keySet()) {
                if (currentAndFinishTask.get(n) == 0) {
                    continue;
                }

                currentAndFinishTask.put(n, currentAndFinishTask.get(n) - 1);

                if (currentAndFinishTask.get(n) == 0) {
                    workersWaiting++;
                    taskWorkerFinish(assigmentTaskWorker.get(n));
                    assigmentTaskWorker.remove(n);
                    availableTask.addAll(addSuccessor(n));
                }
            }

            while(workersWaiting > 0 && !availableTask.isEmpty() && workerAvailable()){
                affectWorker(getFreeWorker());
                workersWaiting--;
            }
            if(workersWaiting < amountOfWorkers){
                totalTime++;
            }

        }

    }

    public static boolean workerAvailable(){
        for (Worker w : listOfWorker){
            if (!w.isInWork()){
                return true;
            }
        }
        return false;
    }

    public static void taskWorkerFinish(Worker w){
        w.setInWork(false);
    }

    public static Worker getFreeWorker(){
        for (Worker w : listOfWorker) {
            if(!w.inWork){
                return w;
            }
        }
        return null;
    }

    public static boolean allTaskDone() {
        for (Node n : currentAndFinishTask.keySet()) {
            if (currentAndFinishTask.get(n) != 0) {
                return false;
            }
        }

        return true && availableTask.isEmpty();
    }

    public static void affectWorker(Worker w){
        Node removeNode = null;
        w.setInWork(true);
        switch (strat) {
            case 1 : //critical path
                ArrayList<Node> critiPath = g.getCriticalPathList();

                for(Node n : availableTask){
                    if (critiPath.contains(n)) {
                        removeNode = n;
                        break;
                    }
                }

                if (removeNode != null) {
                    break;
                }
            case 2 : //choose node with minimal time exec first
                removeNode = availableTask.iterator().next();

                for (Node n : availableTask) {
                    if (n.getTimeExec() < removeNode.getTimeExec()) {
                        removeNode = n;
                    }
                }
                break;

            case 3 :  //choose node with maximal time exec first
                removeNode = availableTask.iterator().next();

                for (Node n : availableTask) {
                    if (n.getTimeExec() > removeNode.getTimeExec()) {
                        removeNode = n;
                    }
                }
                break;
            case 4 : //Random

                int rand = (int) Math.random() * availableTask.size();
                int cmp = 0;
                for (Node n : availableTask) {
                    if (cmp == rand) {
                        removeNode = n;
                    }
                    cmp++;
                }
                break;


        }

        assigmentTaskWorker.put(removeNode, w);
        System.out.println("worker " + w.getName() + " begin "+ removeNode.getName());

        currentAndFinishTask.put(removeNode, removeNode.getTimeExec());
        availableTask.remove(removeNode);

    }


    public static Set<Node> addSuccessor(Node n) {
        Set<Node> childrensToAdd = new HashSet<>();

        for (Edge oe : g.getOutEdges(n)) {
            if (currentAndFinishTask.containsKey(oe.getNodeTo())) {
                continue;
            }

            boolean ready = true;

            for (Edge ie : g.getInEdges(oe.getNodeTo())) {
                if (!currentAndFinishTask.containsKey(ie.getNodeFrom()) || currentAndFinishTask.get(ie.getNodeFrom()) != 0) {
                    ready = false;
                    break;
                }
            }

            if (ready) {
                childrensToAdd.add(oe.getNodeTo());
            }

        }

        return childrensToAdd;
    }

}
