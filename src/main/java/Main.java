public class Main {
    public static void main (String[] args){
        System.out.println("TEST");
        Graf g = new Graf();
        Node n1=new Node(2);
        Node n2=new Node(3);
        Node n3=new Node(4);
        Node n4=new Node(5);
        Node n5=new Node(6);
        Node n6=new Node(7);
        Node n7=new Node(8);
        Node n8=new Node(9);
        Node n9=new Node(10);
        Node n10=new Node(11);
        Node n11=new Node(12);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.addNode(n5);
        g.addNode(n6);
        g.addNode(n7);
        g.addNode(n8);
        g.addNode(n9);
        g.addNode(n10);
        g.addNode(n11);
        g.addEdge(n1,n2,7);
        g.addEdge(n1,n4,7);
        g.addEdge(n2,n3,3);
        g.addEdge(n3,n7,1);
        g.addEdge(n3,n5,1);
        g.addEdge(n3,n6,1);
        g.addEdge(n4,n7,8);
        g.addEdge(n4,n5,8);
        g.addEdge(n4,n6,8);
        g.addEdge(n5,n10,2);
        g.addEdge(n6,n8,1);
        g.addEdge(n7,n10,1);
        g.addEdge(n8,n9,3);
        g.addEdge(n9,n10,2);
        g.addEdge(n10,n11,1);
        g.setEarliestTimeNode();
        g.setLatestTimeNode();
        g.printNodeTime();
    }
}
