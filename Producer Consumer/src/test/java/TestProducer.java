import org.junit.Test;

public class TestProducer {
    @Test
    public void testProducer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                try {
                    queue.offer(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
