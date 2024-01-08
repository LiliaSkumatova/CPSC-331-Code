public interface PriorityQueue {
	boolean empty();
	boolean full();
	void insert(int key);
	int extractMin();
	int min();

}
