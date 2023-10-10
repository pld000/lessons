package linked_list_2;

public class ListTest {
    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(5);
        Node n3 = new Node(7);
        Node n4 = new Node(2);
        Node n5 = new Node(2);
        Node n6 = new Node(11);
        Node n7 = new Node(2);

        LinkedList2 list = new LinkedList2();

        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);
        list.addInTail(n5);
        list.addInTail(n6);
        list.addInTail(n7);

        // System.out.println(list.find(11));
        //  ListTest.printNode(list.find(90));
        var some = list.findAll(2);
        for (int i = 0; i < some.size(); i++) {
            System.out.println(some.get(i) + " " + i);
        }


    }

    public static void printNode(Node node) {
        if (node != null) {
            System.out.println(node.value + " " + node + " " + node.prev + "/" + node.next);
        } else {
            System.out.println(node);
        }
    }

    public static void printList(LinkedList2 list) {
        Node node = list.head;

        while (node != null) {
            ListTest.printNode(node);
            node = node.next;
        }
    }
}
