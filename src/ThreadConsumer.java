
public class ThreadConsumer extends Thread {

    private final Queue queue;
    private final LinkedList linkedList;


    public ThreadConsumer(Queue queue, LinkedList linkedList) {
        this.queue = queue;
        this.linkedList = linkedList;
    }

    public void run() {

        Node node = queue.pop();
        while (node != null) {
            linkedList.setLock();
            try {
                linkedList.addNode(node.getCoefficient(), node.getExponent());
            } finally {
                linkedList.setUnlock();
            }
            node = queue.pop();
            if (node == null) {
                return;
            }
        }
    }
}
