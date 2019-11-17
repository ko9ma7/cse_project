import java.util.Scanner;

class Deque extends Queue {
	public Deque() { }
	// enqueue from front
	public void addRear(int val) { this.enqueue(val); }
	// dequeue from front
	public int deleteFront() { return this.dequeue(); }
	// peek from front
	public int getFront() { return this.peek(); }
	// push from front
	public void addFront(int val) {
		if (this.isFull()) { this.error("Deque is Full\n"); }
		else {
			this.data[front] = val;
			this.front = ((this.front - 1) + this.MAX_QUEUE_SIZE) % this.MAX_QUEUE_SIZE;
		}
	}
	// pop from rear
	public int deleteRear() {
		int ret = 0;

		if (isEmpty()) { error("Deque is Empty\n"); }
		else {
			ret = data[rear];
			rear = ((rear - 1) + MAX_QUEUE_SIZE) % MAX_QUEUE_SIZE;
		}

		return ret;
	}
	// peek from rear
	public int getRear() {
		if (isEmpty()) { error("Deque is Empty\n"); }

		return data[rear];
	}

	public void display() {
		System.out.print("value : ");
		int maxi = (front < rear) ? rear : rear + MAX_QUEUE_SIZE;

		for (int i = front + 1; i <= maxi; i++) System.out.printf("[%2d] ", data[i % MAX_QUEUE_SIZE]);
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		Deque deq = new Deque();

		for (int i = 1; i < 10; i++) {
			if (i % 2 != 0) deq.addFront(i); // put odd value in front
			else deq.addRear(i);             // put even value in front
		}

		deq.display();
		deq.deleteFront();
		deq.deleteRear();
		deq.deleteFront();
		deq.display();
	}
}
