package lab3a.V1;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class ListV11 {
    public Node head; // head of list

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    static public void insert(ListV11 list, int exp, int coef)
    {
        synchronized (list) {
            // Create a new node with given data
            Node new_node = new Node(coef, exp);
            new_node.next = null;

            // then make the new node as head
            if (list.head == null) {
                list.head = new_node;
            } else {
                if (list.head.exp < exp) {
                    new_node.next = list.head;
                    list.head = new_node;
                } else {
                    Node nodeA = list.head;
                    while (nodeA.next != null && nodeA.next.exp > exp) {
                        nodeA = nodeA.next;
                    }
                    if (nodeA.next == null) {
                        //insert node at the end
                        nodeA.next = new_node;
                    } else {
                        if (nodeA.exp == exp) {
                            nodeA.coef += coef;
                        } else if (nodeA.next.exp == exp) {
                            nodeA.next.coef += coef;
                        } else {
                            new_node.next = nodeA.next;
                            nodeA.next = new_node;
                        }
                    }
                }
            }
        }

    }
    public  void printList( )
    {
        Node currNode = this.head;
        System.out.print("LinkedList: ");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.println(currNode.exp+" "+currNode.coef);

            // Go to next node
            currNode = currNode.next;
        }
    }

    public void printInFile(String file) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            Node node =this.head;
            while (node.next !=null) {
                writer.write(node.exp + " " + node.coef + "\n");
                node=node.next;
            }
        } catch (IOException ex) {
            System.out.println("error");
        }
    }

}
