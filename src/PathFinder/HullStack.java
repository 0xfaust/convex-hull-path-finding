
package pathFinder;

public class HullStack<T> { //stack for hull points
	
	private Node<T> head;  //head of stack
	private int size = 0;
	
	private static class Node<T> {
		T item;
		Node<T> next;
	}
	
	public void push(T item) { //push item onto stack
		Node<T> newNode = new Node<T>();
		newNode.item = item;
		newNode.next = head;
		head = newNode;
		size++;
	}
	
	public T pop() { //pop item off of stack
		if (head == null) return null; //stack is empty
		T item = head.item;
		head = head.next;
		size--;
		return item;
	}

	public int size() {	return size; }
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	@Override
	public String toString() {
		String s = "";
		Node<T> tmp = head;
		while (tmp != null) {
			s += tmp.item.toString() + " -> ";
			tmp = tmp.next;
		}
		return s;
	}

	public T peek() { //look at top item in stack
		if (head == null) return null; // stack is empty
		T item = head.item;
		return item;
	}
	
	public T sneeky_peek() { //look at item 2 places in
		if (head.next == null) return null; // stack is empty
		T item = head.next.item;
		return item;
	}
	
	
	// TEST CLIENT //
	public static void main(String args[]) {
	}

}
