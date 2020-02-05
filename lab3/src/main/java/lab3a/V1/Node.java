package lab3a.V1;

public class Node {

    public int coef;
    public int exp;
    public Node next;

    // Constructor
    public Node(int coef, int exp)
    {
        this.coef = coef;
        this.exp=exp;
        next = null;
    }
}
