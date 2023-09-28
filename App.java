import java.util.*;


public class App {
	public static void main(String[] args) {
		var list1 = new LinkedList();
        var list2 = new LinkedList();

        var n1 = new Node(5);
        var n2 = new Node(6);
        var n3 = new Node(10);
        var n4 = new Node(5);

        var n5 = new Node(12);
        var n6 = new Node(5);
        var n7 = new Node(12);
        var n8 = new Node(12);

        list1.addInTail(n1);
        list1.addInTail(n2);
        list1.addInTail(n3);
        list1.addInTail(n4);

        list2.addInTail(n5);
        list2.addInTail(n6);
        list2.addInTail(n7);
        list2.addInTail(n7);

        LinkedList list = summarizeLists(list1, list2);

       // list.insertAfter(n6, n7);

		//print(list);
	}

    public static LinkedList summarizeLists(LinkedList list1, LinkedList list2) {
        System.out.println(list2.count() + " " + list1.count());

        return list1;
        // if (list1.count() == list2.count()) {
        //     LinkedList sumList = new LinkedList();

        //     Node nodeList1 = list1.head;
        //     Node nodeList2 = list2.head;

        //     while(nodeList1 != null) {
        //         int sum = nodeList1.value + nodeList2.value;
        //         Node sumNode = new Node(sum);
        //         sumList.addInTail(sumNode);
        //         nodeList1 = nodeList1.next;
        //         nodeList2 = nodeList2.next;
        //     }

        //     return sumList;
        // }

        // return null;
    }

    public static void print(LinkedList list) {
        if (list == null) {
            System.out.println("Empty list");
        }

        Node node = list.head;
        while(node != null) {
            System.out.println(node.value + " - " + node + " - " + node.next);
            node = node.next;
        }
    }
}



class LinkedList
{     
    public Node head;
    public Node tail;

    public LinkedList() {
       head = null;
       tail = null;
     }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;

        while(node != null) {
            if(node.value == _value) {
                nodes.add(node);
            }

            node = node.next;
        }

        return nodes;
     }

     public boolean remove(int _value) {
        Node node = this.head;

        if(this.head != null && this.head.value == _value) {
            this.head = this.head.next;
            return true;
        }

        while (node != null) {
            if (node.next != null && node.next.value == _value) {
                node.next = node.next.next;
                return true;
            }     
            node = node.next;           
        }

        return false;
     }

     public void removeAll(int _value)
     {
        Node node = this.head;

        while(node != null) {
            if(this.head != null && this.head.value == _value) {
                this.head = this.head.next;
            } else if (node.next != null && node.next.value == _value) {
                node.next = node.next.next;
            }     

            node = node.next;
        }
     }

     public void clear()
     {
        while(this.head != null) {
            this.head = this.head.next;
        }

        this.tail = null;
     }

     public int count()
     {
        var result = 0;
        Node node = this.head;

        while (node != null) {
            result += 1;
            node = node.next;
        }

        return result;
     }

     public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
     {
        if(_nodeAfter == null) {
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
        } else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
        }
    
     }
}



class Node
{
     public int value;
     public Node next;
     public Node(int _value) 
     {  
       value = _value;
       next = null;
     }
}