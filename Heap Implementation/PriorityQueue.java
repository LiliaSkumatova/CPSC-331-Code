package ca.ucalgary.cpsc331.a1;

public interface PriorityQueue {
	boolean empty();
	boolean full();
	void insert(int key);
	int extractMin();
	int min();

}
