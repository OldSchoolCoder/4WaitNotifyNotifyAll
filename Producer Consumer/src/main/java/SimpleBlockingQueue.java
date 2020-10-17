import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    @GuardedBy("this")
    private final int size;
    private final Object monitor = this;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
        if (queue.size() == size) {
            wait();
        }
        queue.add(value);
        monitor.notify();
    }

    public synchronized T poll() throws InterruptedException {
        if (queue.size() < size) {
            wait();
        }
        notify();
        return queue.poll();
    }
}

