import java.util.Scanner;

class Stack {
	// create variable
	final int MAX_STACK_SIZE = 100;
	private int top;
	private int[] data = new int[MAX_STACK_SIZE];

	// create constructor
	public Stack() { top = -1; }
	// check stack is empty
	public boolean isEmpty() { return top == -1; }
	// check stack is full
	public boolean isFull() { return top == MAX_STACK_SIZE - 1; }
	// stack status error
	public void error(String str) {
		System.out.printf("%s", str);
		System.exit(1);
	}
	// push the value in stack
	public void push(int val) {
		if (isFull()) error("Stack is Full!");
		data[++top] = val;
	}
	// pop the value from stack
	public int pop() {
		if (isEmpty()) error("Stack is Empty!");
		return data[top--];
	}
	// get size of stack
	public int size() { return top + 1; }
	// check the top of stack
	public int stack_top() {
		if (isEmpty()) error("Stack is Empty!");
		return data[top];
	}
	// print data in stack
	public void display() {
		System.out.println("Stack display");
		for (int i = size() - 1; i >= 0; i--) {
			System.out.printf("-> [%d]\n", data[i]);
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Stack stack = new Stack();

		for (int n = 1; n <= 5; n++) stack.push(n);
		System.out.println("size: " + stack.size());
		stack.display();
	}
}

