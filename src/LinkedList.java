import java.util.StringJoiner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkedList{
    final Lock lock = new ReentrantLock();

    private Node head;

    public LinkedList() {
        head = null;
    }

    public void addNode(Integer coefficient, Integer exponent){

        Node node = new Node(coefficient,exponent,null);

        if (head==null) {
            head = node;
            return;
        }
        Node find = searchNode(node);
        if (find != null) {
            find.setCoefficient(find.getCoefficient() + node.getCoefficient());
            return;
        }

        Node toFind = head;
        if (toFind.getExponent() < node.getExponent()) {
            node.setNext(head);
            head = node;
            return;
        }

        while (toFind.getNext() != null && toFind.getNext().getExponent() > node.getExponent()) {
            toFind = toFind.getNext();
        }
        node.setNext(toFind.getNext());
        toFind.setNext(node);

    }

    public Node searchNode(Node node){

        Node toFind = head;

        while(toFind != null) {
            if (toFind.getExponent().equals(node.getExponent())) {
                return toFind;
            }
            toFind = toFind.getNext();
        }
        return null;
    }

    public Node getHead() {
        return head;
    }

    public void setLock() {
        this.lock.lock();
    }

    public void setUnlock() {
        this.lock.unlock();
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(" + ");
        if (head!=null) {
            Node node = head;
            while (node != null) {
                stringJoiner.add(node.toString());
                node=node.getNext();
            }
        }
        return stringJoiner.toString();
    }
}
