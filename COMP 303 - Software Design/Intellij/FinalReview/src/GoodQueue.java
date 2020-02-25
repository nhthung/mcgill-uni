import java.util.concurrent.locks.ReentrantLock;

public class GoodQueue<E> {
    private Object[] elements;
    private int head, tail, size;
    private ReentrantLock lock;

    public GoodQueue(int capacity) {
        elements = new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
        lock = new ReentrantLock();
    }

    public void add(E value) {
        lock.lock();

        try {
            while (size == elements.length);
            elements[tail] = value;
            tail++;
            size++;

            if (tail == elements.length)
                tail = 0;
        }

        finally { lock.unlock(); }
    }
}