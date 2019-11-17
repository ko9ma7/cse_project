import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;

import org.junit.jupiter.api.Test;

class LinkedListTest {
	@Test
	void pushFront_popFrontTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		String output = "";
		while(!list.isEmpty()) {
			output += list.popFront()+",";
		}
		assertEquals(output,"3,5,10,");
	}
	@Test
	void removeTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		list.remove(3);
		assertEquals(Integer.valueOf(5),list.peekFront());
		list.remove(10);
		assertEquals(1,list.size());
	}
	@Test
	void iteratorTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"3,5,10,");
	}
	@Test
	void listIterator_setTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.set(4);
		assertEquals(Integer.valueOf(5),it.next());
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(3,list.size());
		assertEquals(output,"4,5,10,");
	}
	@Test
	void listIterator_addTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.add(4);
		assertEquals(Integer.valueOf(5),it.next());
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(4,list.size());
		assertEquals(output,"3,4,5,10,");
	}
	@Test
	void listIterator_removeTest() {
		LinkedList<Integer> list = new LinkedList<>();
		list.pushFront(10);
		list.pushFront(5);
		list.pushFront(3);
		ListIterator<Integer> it = list.listIterator();
		it.next();
		it.remove();
		assertEquals(Integer.valueOf(5),it.next());
		it.next();
		it.remove();
		String output = "";
		for(var n: list)
			output += n+",";
		assertEquals(1,list.size());
		assertEquals(output,"5,");
	}	
}
