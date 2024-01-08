//Lilia Skumatova
//UCID: 30187339
package ca.ucalgary.cpsc331.a3;

public class HashTable implements Dictionary {

	//set up some constant variables as the capacity does not change and
	//when a value is deleted it is replaced by "deleted"
    private static final int CAPACITY = 17;
    private static final String DELETED = "deleted";
    private String[] table;

    public HashTable() {
    	//create a new hashTable with the max capacity it can have
        table = new String[CAPACITY];
    }

    /**
     * return true iff Dictionary is full
     * @return true if Dictionary is full, false otherwise
     */
    @Override
    public boolean full() {
        for (String item : table) {
            if (item == null || item.equals(DELETED)) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns true iff key k is a member of the Dictionary.
     * @param k is the key that were are checking is in the dictionary
     * @return true if key is a member otherwise false
     */
    @Override
    public boolean member(String k) {
        int index = findIndex(k);
        int initialIndex = index;

        // Check if the key is found and is not marked as DELETED
        if (index != -1 && table[index] != null && !table[index].equals(DELETED) && table[index].equals(k)) {
            return true;
        }

        // Check for DELETED markers in the probe sequence
        boolean flag = false;

        while (table[index] != null && (!table[index].equals(k) || table[index].equals(DELETED))) {
            index = (index + 1) % CAPACITY;

            // Check if we've revisited the initial index
            if (index == initialIndex) {
                if (flag) {
                    break;  //leaves the loop when the index is back to the original
                }
                flag = true;
            }
        }

        return table[index] != null && table[index].equals(k);
    }

    
    /**
     * if k is not a member of the Dictionary and the latter is not
     * yet full then the method inserts k and returns true
     * If dictionary is full returns a RuntimeException
     * @param k the the key we want to insert into dictionary
     * @return true if k is inserted otherwise false with runtime exception
     */
    @Override
    public boolean insert(String k) {
        if (full()) {
            throw new RuntimeException("Hash table is full");
        }

        int index = findIndex(k);

        while (table[index] != null) {
            if (table[index].equals(k)) {
                return false; // Key already exists
            }
            if (table[index].equals(DELETED)) {
                break; // Found a slot with a DELETED marker
            }
            index = (index + 1) % CAPACITY;
        }

        table[index] = k;
        return true;
    }

   
    /**
     * if k is a member the Dictionary then deletes k and returns true, if
     * not returns false
     * @param k key we want to delete
     * @return returns true if k deleted false if not member
     */
    @Override
    public boolean delete(String k) {
        int index = findIndex(k);

        //checks if there is a value at the index
        while (table[index] != null) {
        	//checks if the value at the index is equal to k
            if (table[index].equals(k)) {
            	//deletes k
                table[index] = DELETED;
                return true;
            }
            index = (index + 1) % CAPACITY;
        }

        return false;
    }


    /**
     * Used to find the index of k in the Dictionary
     * @param k
     * @return
     */
    private int findIndex(String k) {
    	//get's the hashCode value of the key
        int hash = k.hashCode();
        //takes the hash value and module 17
        int index = hash % CAPACITY;

        // Increment index until an empty slot or a slot marked as DELETED is found
        while (table[index] != null && !table[index].equals(k) && !table[index].equals(DELETED)) {
            index = (index + 1) % CAPACITY;
        }
        return index;
    }

    /**
     * Converts the hashtable toString.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < CAPACITY; i++) {
            if (table[i] != null) {
                result.append(i).append(":");
                if (table[i].equals(DELETED)) { //checks if the value is DELETED
                    result.append("deleted");
                } else {
                    result.append("\"").append(table[i]).append("\"");
                }
                result.append("\n");
            }
        }
        return result.toString();
    }

 
}