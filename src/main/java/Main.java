//./src/main/java/test.txt

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {


    private static int strat = 0;
    private static int amountOfWorkers = 0;
    private static ArrayList<Worker> listOfWorker = new ArrayList<Worker>();
    private static Map<Node, Worker> assigmentTaskWorker = new HashMap<>();
    private static int totalDuration = 0;
    private static Graf g = new Graf();
    private static Map<Node, Integer> currentAndFinishTask = new HashMap<>();
    private static Set<Node> availableTask = new HashSet<>();


    /**
     * <b>main</b>
     *
     * Start the application and execute the function to print the menu
     *
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        SwitchOperatedTextMenu();

    }


    /**
     * <b>Function readFile</b>
     *
     * Read the file and fill the map with informations
     *
     * @param path
     *      The location of the file
     *
     * @return a Map which represent all informations into the file we read
     *
     */
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

    /**
     * <b>Function readLine</b>
     *
     * Parse a line with "," and fill the arrayList with each elements
     *
     * @param line
     *      The line to parse
     *
     * @return ArrayList with each element from a line
     *
     */
    public static ArrayList<String> readLine(String line) {
        String[] values = line.split(",");
        return new ArrayList(Arrays.asList(values));
    }

    /**
     * <b>Function supprSpace</b>
     *
     * Delete space into string in an ArrayList of String
     *
     * @param listString
     *      an ArrayList of string
     *
     * @return the ArrayList without space
     *
     */
    public static ArrayList<String> supprSpace(ArrayList<String> listString) {
        ArrayList<String> listWithoutSpace = new ArrayList<String>();
        for (String s : listString) {
            listWithoutSpace.add(s.replaceAll("\\s", ""));
        }
        return listWithoutSpace;
    }

    /**
     * <b>Function construGraf</b>
     *
     * Create a graf from the map
     *
     * @param listInstr
     *      the map which constain all information to create the graf
     *
     * @return the graf
     *
     */
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

    /**
     * <b>Function addEndEdge</b>
     *
     * Add the end node into the graf and add edges that we need
     *
     * @param g
     *      The graf where we add the end node
     *
     * @return the graf with the end node
     *
     */
    public static Graf addEndEdge(Graf g) {
        for (Node n : g.getAllNodes()) {
            List<Edge> listE = g.getOutEdges(n);
            if (listE.isEmpty() && n != g.endNode) {
                g.addEdge(n, g.endNode, n.getTimeExec());
            }
        }
        return g;
    }

    /**
     * <b>Function findIdOfNode</b>
     *
     * Find the id of node from the name
     *
     * @param map
     *      the map with all the node informations
     *
     * @param nameNode
     *      The name of the node that we find
     *
     * @return the id of the node finded
     *
     */
    public static int findIdOfNode(Map<String, ArrayList<String>> map, String nameNode) {
        List<String> indexes = new ArrayList<String>(map.keySet());
        return indexes.indexOf(nameNode);
    }


    /**
     * <b>Function SwitchOperatedTextMenu</b>
     *
     * The menu print into the console to interact between the application and the user
     *
     */
    public static void SwitchOperatedTextMenu() throws InterruptedException, IOException {
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
        System.out.println("8. Compute the assignment strategy n°2 : Minimum time execution first");
        System.out.println("9. Compute the assignment strategy n°3 : Maximum time execution first");
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
                    myGraph.setEarliestTimeNode();
                    myGraph.setLatestTimeNode();
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
                        Scanner pathReader = new Scanner(System.in);
                        String dotFileLocation = pathReader.nextLine();
                        myGraph.toDotFile(dotFileLocation);
                        pathReader.reset();
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
                            }
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
                        System.out.println("Total time of the execution : " + totalDuration);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;

                case 8:
                    if (init) {
                        strat = 2;
                        System.out.println("You've chosen option #8 : Compute the assignment strategy n°2 : Minimum time execution first\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalDuration);
                        System.out.println("Done \n ----------------------------------\n");
                    } else {
                        System.out.println("Error: Please ask for the creation of an empty graph first (option #1 in the menu)");
                    }
                    break;


                case 9:
                    if (init) {
                        strat = 3;
                        System.out.println("You've chosen option #9 : Compute the assignment strategy n°3 : Maximum time execution first\n");
                        System.out.print("Please enter the first number of workers you want to assign\n");
                        amountOfWorkers = menuScan.nextInt();
                        createListWorker(amountOfWorkers);
                        execStrategie();
                        System.out.println("Total time of the execution : " + totalDuration);
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
                        System.out.println("Total time of the execution : " + totalDuration);
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
                    System.out.println("8. Compute the assignment strategy n°2 : Minimum time execution first");
                    System.out.println("9. Compute the assignment strategy n°3 : Maximum time execution first");
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

    /**
     * <b>Function createListWorker</b>
     *
     * Get the number of worker and create an arrayList of workers
     *
     * @param amountOfWorkers
     *      The number of workers choose by the user
     *
     * @return the arrayList with all workers
     */

    public static void createListWorker(int amountOfWorkers){
        for (int i = 0; i < amountOfWorkers; i++){
            listOfWorker.add(new Worker(i+1));
        }
    }

    /**
     * <b>Function execStrategie</b>
     *
     * In this function we manage 2 list of task : available task and current and finished tasks
     *
     * We have a loop, each iteration represent a time in the execution of the pert chart since all task are finished
     * If a task finish during the loop, we free the worker who works this task and we assign it a new task
     *
     */
    public static void execStrategie() throws InterruptedException {
        currentAndFinishTask.clear();
        totalDuration = 0;
        for (Node node : g.getSuccessors(g.getStartNode())) {
            availableTask.add(node);
        }
        currentAndFinishTask.put(g.getEndNode(), 0);
        currentAndFinishTask.put(g.getStartNode(), 0);
        int workersWaiting = amountOfWorkers;

        while (!allTaskDone()) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("-----------------------");
            System.out.println("Time : " + totalDuration);

            for (Node n : currentAndFinishTask.keySet()) {
                if (currentAndFinishTask.get(n) == 0) {
                    continue;
                }

                currentAndFinishTask.put(n, currentAndFinishTask.get(n) - 1);

                if (currentAndFinishTask.get(n) == 0) {
                    workersWaiting++;
                    taskWorkerFinish(assigmentTaskWorker.get(n), n);
                    assigmentTaskWorker.remove(n);
                    availableTask.addAll(addSuccessor(n));
                }
            }

            while(workersWaiting > 0 && !availableTask.isEmpty() && workerAvailable()){
                affectWorker(getFreeWorker());
                workersWaiting--;
            }
            if(workersWaiting < amountOfWorkers){
                totalDuration++;
            }

        }

    }
    

    /**
     * <b>Function workerAvailable</b>
     *
     * Used to know is there are workers available to work
     *
     */
    public static boolean workerAvailable(){
        for (Worker w : listOfWorker){
            if (!w.isInWork()){
                return true;
            }
        }
        return false;
    }

    /**
     * <b>Function taskWorkerFinish</b>
     *
     * Used to finish a task and free the worker
     *
     * @param w
     *      The worker who work for this task
     *
     * @param n
     *      The node which represent the task
     *
     */

    public static void taskWorkerFinish(Worker w, Node n){
        w.setInWork(false);
        System.out.println(w.getName() + " finish "+ n.getName());
    }


    /**
     * <b>Function getFreeWorker</b>
     *
     * Used to find a free worker to begin a new task
     *
     * @return a free worker if exists
     */
    public static Worker getFreeWorker(){
        for (Worker w : listOfWorker) {
            if(!w.inWork){
                return w;
            }
        }
        return null;
    }


    /**
     * <b>Function allTaskDone</b>
     *
     * Used to know if all task are done
     *
     * @return a boolean
     */
    public static boolean allTaskDone() {
        for (Node n : currentAndFinishTask.keySet()) {
            if (currentAndFinishTask.get(n) != 0) {
                return false;
            }
        }

        return true && availableTask.isEmpty();
    }


    /**
     * <b>Function affectWorker</b>
     *
     * Assign a worker to a task depends of the strategy choose by the user
     *
     */
    public static void affectWorker(Worker w){
        Node task = null;
        w.setInWork(true);
        switch (strat) {
            case 1 : //critical path
                ArrayList<Node> critiPath = g.getCriticalPathList();
                for(Node n : availableTask){
                    if (critiPath.contains(n)) {
                        task = n;
                        break;
                    }
                }
                if (task != null) {
                    break;
                }
            case 2 : //choose node with minimal time exec first
                task = availableTask.iterator().next();
                for (Node n : availableTask) {
                    if (n.getTimeExec() < task.getTimeExec()) {
                        task = n;
                    }
                }
                break;

            case 3 :  //choose node with maximal time exec first
                task = availableTask.iterator().next();
                for (Node n : availableTask) {
                    if (n.getTimeExec() > task.getTimeExec()) {
                        task = n;
                    }
                }
                break;
            case 4 : //Random
                int rand = (int) Math.random() * availableTask.size();
                int cmp = 0;
                for (Node n : availableTask) {
                    if (cmp == rand) {
                        task = n;
                    }
                    cmp++;
                }
                break;
        }
        assigmentTaskWorker.put(task, w);
        System.out.println(w.getName() + " begin "+ task.getName());

        currentAndFinishTask.put(task, task.getTimeExec());
        availableTask.remove(task);

    }
    
    /**
     * <b>Function addSuccessor</b>
     *
     * Used to find new task to add in the list of available tasks which are succesor of a current node
     *
     * @param n
     *      Is the node which is finish and from it we search successor node to return
     *
     * @return a Set of Node which represent available task
     */
    public static Set<Node> addSuccessor(Node n) {
        Set<Node> toAddSuccessor = new HashSet<>();

        for (Node nextNode : g.getSuccessors(n)) {
            if (currentAndFinishTask.containsKey(nextNode)) {
                continue;
            }
            boolean ready = true;
            for (Edge inEdge : g.getInEdges(nextNode)) {
                if (!currentAndFinishTask.containsKey(inEdge.getNodeFrom()) || currentAndFinishTask.get(inEdge.getNodeFrom()) != 0) {
                    ready = false;
                    break;
                }
            }
            if (ready) {
                toAddSuccessor.add(nextNode);
            }
        }
        return toAddSuccessor;
    }
}
