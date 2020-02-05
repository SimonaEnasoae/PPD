package lab3a;

import lab3a.V1.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.Lock;

public class ListV2 implements IListPol {
    Node head; // head of list
    Lock lock;
    public ListV2(Lock lock){
        this.lock=lock;
    }
     public  void insert(int exp,int coef)
    {
        // Create a new node with given data
        Node new_node = new Node(coef,exp);
        new_node.next = null;

        // then make the new node as head
        lock.lock();
        if (this.head == null) {
            this.head = new_node;
            lock.unlock();
        } else {
                if (this.head.exp < exp) {
                    new_node.next = head;
                    head = new_node;
                    lock.unlock();
                }
                else {
                    lock.unlock();
                    Node nodeA = this.head;
                    Node nodeB = nodeA.next;
                    while (nodeB != null && nodeB.exp > exp) {
                        synchronized (nodeA) {
                            synchronized (nodeB) {
                                nodeA = nodeB;
                                nodeB = nodeB.next;
                            }
                        }
                    }
                    synchronized (nodeA){
                        if (nodeA.exp == exp) {
                            nodeA.coef += coef;
                        } else {
                            if (nodeB == null) {
                                //insert node at the end
                                nodeA.next = new_node;
                            } else{
                                synchronized (nodeB) {
                                    if (nodeB.exp == exp) {
                                        nodeB.coef += coef;
                                    } else {
                                        new_node.next = nodeB;
                                        nodeA.next = new_node;
                                    }
                                }
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
