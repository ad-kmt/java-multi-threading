package org.kmt.multithreading.problems.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

class Message {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    private int id;
    private String data;
    private String producer;

    public Message(String producer) {
        this.id = idGenerator.incrementAndGet();
        this.data = "Data-" + new Random().nextInt(100); // Generate random data
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}

class SharedQueue {
    private Queue<Message> queue;
    private int capacity;

    public SharedQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void produce(Message message) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // Wait if the queue is full
        }
        queue.add(message);
        System.out.println("Produced: " + message + " by " + Thread.currentThread().getName());
        notifyAll(); // Notify consumers that a new item is available
    }

    public synchronized Message consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Wait if the queue is empty
        }
        Message message = queue.remove();
        System.out.println("Consumed: " + message + " by " + Thread.currentThread().getName());
        notifyAll(); // Notify producers that space is available
        return message;
    }
}

class Producer implements Runnable {
    private SharedQueue sharedQueue;
    private String producerName;

    public Producer(SharedQueue sharedQueue, String producerName) {
        this.sharedQueue = sharedQueue;
        this.producerName = producerName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Message message = new Message(producerName);
                sharedQueue.produce(message);
                Thread.sleep(500); // Simulate time taken to produce the item
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private SharedQueue sharedQueue;

    public Consumer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                sharedQueue.consume();
                Thread.sleep(1000); // Simulate time taken to consume the item
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(5); // Create a shared queue with capacity 5

        // Create and start multiple producer and consumer threads
        Thread producerThread1 = new Thread(new Producer(sharedQueue, "Producer-1"));
        Thread producerThread2 = new Thread(new Producer(sharedQueue, "Producer-2"));
        Thread consumerThread1 = new Thread(new Consumer(sharedQueue));
        Thread consumerThread2 = new Thread(new Consumer(sharedQueue));

        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();

        // Wait for threads to finish
        try {
            producerThread1.join();
            producerThread2.join();
            consumerThread1.join();
            consumerThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


