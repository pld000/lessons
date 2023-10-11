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

        Node nA = new Node(15);
        Node nB = new Node(25);
        Node nC = new Node(35);

        LinkedList2 list = new LinkedList2();

        list.addInTail(n1);
        list.addInTail(n2);
        list.addInTail(n3);
        list.addInTail(n4);
        list.addInTail(n5);
        list.addInTail(n6);
        list.addInTail(n7);

        System.out.println("-------------------");
        list.insertAfter(null, nA);
        list.insertAfter(n4, nB);
        list.insertAfter(n7, nC);
        printList(list);
        System.out.println("-------------------");
//        list.removeAll(2);
//        printList(list);
//        System.out.println("-------------------");
        System.out.println(list.head + "--" + list.tail);


        // System.out.println(list.find(11));
        //  ListTest.printNode(list.find(90));
//        var some = list.findAll(2);
//        for (int i = 0; i < some.size(); i++) {
//            System.out.println(some.get(i) + " " + i);
//        }

//        printList(list);
//        System.out.println(list.remove(5));
//        printList(list);
//        System.out.println(list.remove(3));
//        printList(list);
//        System.out.println(list.remove(21));
//        printList(list);
//        System.out.println(list.remove(210));


    }

    public static void printNode(Node node) {
        if (node != null) {
            System.out.println(node.value + " " + node + " " + node.prev + "/" + node.next);
        } else {
            System.out.println(node);
        }
    }

    public static void printList(LinkedList2 list) {
        if (list.head == null) {
            System.out.println(list.head + " " + list.tail + " - result");
        }

        Node node = list.head;

        while (node != null) {
            ListTest.printNode(node);
            node = node.next;
        }
    }
}
