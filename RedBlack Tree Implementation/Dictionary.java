public interface Dictionary {
	// empty(): returns true iff the dictionary
	// is empty.
	boolean empty();
	// insert(k): inserts k and returns true if k is
	// not already in the dictionary; otherwise
	// returns false without inserting k.
	boolean insert(int k);
	// delete(k): deletes k and returns true if k is
	// a member of the dictionary; otherwise returns
	// false without deleting k.
	boolean delete(int k);
	// member(k): returns true iff k is a member.
	boolean member(int k);
}
