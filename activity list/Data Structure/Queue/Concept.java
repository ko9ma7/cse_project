import java.util.Scanner;

class Queue {
	final int MAX_QUEUE_SIZE = 100;
	private int front;
	private int rear;
	private int[] data = new int[MAX_QUEUE_SIZE];

	// create constructor
	public Queue() { front = rear = 0; }
	// check queue is empty
	public boolean isEmpty() { return front == rear; }
	// check queue is full
	public boolean isFull() { return front == (rear + 1) % MAX_QUEUE_SIZE; }
	// queue status error
	public void error(String str) {
		System.out.printf("%s", str);
		System.exit(1);
	}
	// push the value in queue
	public void enqueue(int value) {
		if (isFull()) error("Queue is Full");
		rear = (rear + 1) % MAX_QUEUE_SIZE;
		data[rear] = value;
	}
	// pop the value from queue
	public int dequeue() {
		if (isEmpty()) error("Queue is Empty");
		front = (front + 1) % MAX_QUEUE_SIZE;
		return data[front];
	}
	// check the top of queue
	public int queue_top() {
		if (isEmpty()) error("Queue is Empty!");
		return data[(front + 1) % MAX_QUEUE_SIZE];
	}
	// print data in queue
	public void display() {
		System.out.println("Queue display");
		int maxi = (front < rear) ? rear : (rear + MAX_QUEUE_SIZE);
		for (int i = front + 1; i <= maxi; i++) {
			System.out.println(data[i]);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Queue queue = new Queue();

		for (int n = 1; n <= 20; n++) { if (n % 2 == 0) queue.enqueue(n); }
		queue.display();
	}
}
