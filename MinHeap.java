/*
 * Lilia Skumatova
 * UCID: 30187339
 * assignment 1 CPSC 331
 */
package ca.ucalgary.cpsc331.a1;


public class MinHeap implements PriorityQueue {
	//variable assignment
    private int[] heap;
    //the max capacity of the heap
    private int capacity;
    //the current heap size
    private int size;

    /**
     * Constructor used to create a heap, initialize size as zero, and save the max capacity.
     * @param N is used to initialize the max capacity of the heap
     */
    public MinHeap(int N) {
        this.capacity = N;
        this.heap = new int[N + 1]; // 1-based indexing
        //nothing in the heap yet
        this.size = 0;
    }

    /**
     * empty method used to check if heap is empty
     * @return true of false if the heap is empty
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * full method used to check if the heap is full
     * @return true or false if the heap is full
     */
    @Override
    public boolean full() {
        return size == capacity;
    }

    /**
     * insert method used to add a new key to the heap
     * @param key is the new element that needs to be inserted
     */
    @Override
    public void insert(int key) {
        if (full()) {
            throw new RuntimeException("Heap overflow");
        }
        int index = size;
        //make size bigger
        heap[size++] = key;
        //while loop used to check if elements need to be swaped to maintain min heap
        while(index >0) {
        	int parent = parent(index);
        	if(heap[parent] <= heap[index]) {
        		break;
        	}
        	//swap elements when they do not follow the property of the min heap
        	swap(index, parent);
        	index = parent;
        }
        //calls minHeapify method
        minHeapify((index-1)/2);
    }
    /**
     * swap method used to swap to elements
     * @param i is the first element that will be swapped with the second
     * @param j is the first element that will be swapped with the first
     */
    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * extraxtMin is used to take out the min value of the heap
     * and check if the heap still follows min heap properties
     * @return min the smallest element in array
     */
    @Override
    public int extractMin() {
    	//check if empty
        if (empty()) {
            throw new RuntimeException("Heap underflow");
        }
        //min is saved in the first index of array
        int min = heap[0];
        //saves the last element in where the min element was saved
        heap[0] = heap[size -1];
        //minus the size of the array
        size--;
        //need to heapify using the index from where the element was removed
        minHeapify(0);
        return min;
    }

    /**
     * min method is used to peek and see which element is the smallest in the heap
     */
    @Override
    public int min() {
    	//checks if empty
        if (empty()) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }

    /**
     * minHeapify maintains the min heap condition after a value is removed or add to the heap
     * compares the root node value to its children
     * @param i is the index of the node being looked at maintaining the property
     */
    private void minHeapify(int i) {
    	int smallest = i;
    	int left = leftChild(i);
        int right =rightChild(i);
        //checks left child
        if(left < size && heap[left] < heap[smallest]) {
        	smallest = left;
        }
        //check right child
        if(right < size && heap[right] < heap[smallest]) {
        	smallest = right;
        }
        //if smallest is different from the original value passed as parameter
        //calls the swap method
        //then recursively heapifies
        if(smallest != i) {
        	swap(i, smallest);
        	minHeapify(smallest);
        }
       
    }
    /**
     * @param i is node which parent is returned
     * @return the parent of the node i
     */
    private int parent(int i) {
        return(i-1)/2;
    }
    /**
     * @param i is node which method finds left child of
     * @return the left child of node i
     */
    private int leftChild(int i) {
        return 2* i + 1;
    }
    /**
     * @param i is node which method finds the right child of
     * @return the right child of node i
     */
    private int rightChild(int i) {
        return 2 * i +2;
    }

    /**
     * The toString method converts the heap into a string to allow easy print
     * @return a string composed of the elements in the heap in their order in the heap
     */
    @Override
    public String toString() {
    	//the string that will hold the heap elements
        String sb = "";
        // Saves the size of the heap in the string
        sb += ("size = " + size + "\n");
        int indexEnd = 2; //Used to know how many elements are on each level of the string
        //used to iterate through the heap
        for(int i = 0; i < size; i++) {
        	//adds element
        	sb += heap[i];
        	//checks if the string needs to go to the next line
        	if(i == indexEnd -2 || size-1 == i) {
        		sb += "\n";
        		//times two do to each row having 2 children per node of last row
        		indexEnd = indexEnd * 2;
        	}else {
        		sb += " ";
        	}
        }
        return sb;
    }
}

	