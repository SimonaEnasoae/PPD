package lab3a;

import lab3a.V1.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ListV1 implements IListPol {
    Node head; // head of list

    synchronized public  void insert(int exp,int coef)
    {
        // Create a new node with given data
        Node new_node = new Node(coef,exp);
        new_node.next = null;

        // then make the new node as head
        if (this.head == null) {
            this.head = new_node;
        }
        else {
            if(this.head.exp<exp){
                new_node.next=head;
                head=new_node;
            }else {
                Node nodeA = this.head;
                while (nodeA.next != null && nodeA.next.exp > exp) {
                    nodeA = nodeA.next;
                }
                if (nodeA.next == null) {
                    //insert node at the end
                    nodeA.next = new_node;
                } else {
                    if(nodeA.exp ==exp){
                        nodeA.coef += coef;
                    }
                    else if (nodeA.next.exp == exp) {
                        nodeA.next.coef += coef;
                    } else {
                        new_node.next = nodeA.next;
                        nodeA.next = new_node;
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

    @Override
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
