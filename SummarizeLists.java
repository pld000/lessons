public class SummarizeLists {
    public static LinkedList summarizeLists(LinkedList list1, LinkedList list2) {
        if (list1.count() == list2.count()) {
            LinkedList sumList = new LinkedList();

            Node nodeList1 = list1.head;
            Node nodeList2 = list2.head;

            while (nodeList1 != null) {
                int sum = nodeList1.value + nodeList2.value;
                Node sumNode = new Node(sum);
                sumList.addInTail(sumNode);
                nodeList1 = nodeList1.next;
                nodeList2 = nodeList2.next;
            }

            return sumList;
        }

        return null;
    }
}
