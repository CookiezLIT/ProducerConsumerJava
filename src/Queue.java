
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Queue {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    private final Node[] arr;
    private int putptr;
    private int takeptr;
    public int count;
    public AtomicBoolean done = new AtomicBoolean(false);

    public Queue() {
        int capacity = 50;
        arr = new Node[capacity];
        takeptr = 0;
        putptr = 0;
        count = 0;
    }

    public Node pop() {
        lock.lock();
        try {
            while (count == 0 && !done.get()) {
                notEmpty.await();
            }
            Node x = arr[takeptr];
            arr[takeptr] = null;
            if (++takeptr == arr.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return x;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

    public void push(Node item) throws InterruptedException {
        lock.lock();
        try {

            while (count == arr.length) {
                notFull.await();
            }
            arr[putptr] = item;

            if (done.get()) {
                notEmpty.signalAll();
            }

            if (++putptr == arr.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

}
