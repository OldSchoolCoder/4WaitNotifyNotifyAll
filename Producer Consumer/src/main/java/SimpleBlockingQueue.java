import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    @GuardedBy("this")
    boolean isFull;
    private final Object monitor = this;

    public synchronized void offer(T value) throws InterruptedException {
        if (isFull) {
            wait();
        }
        queue.add(value);
        isFull = true;
        monitor.notify();
    }

    public synchronized T poll() throws InterruptedException {
        if (!isFull) {
            wait();
        }
        isFull = false;
        notify();
        return queue.poll();
    }
}

