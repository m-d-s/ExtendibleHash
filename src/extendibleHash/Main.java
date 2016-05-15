package extendibleHash;

public class Main {
	
  public static void main(String[] args) {
	ExtendibleHash myHash = new ExtendibleHash(3);
	 myHash.display();
	testAdd(myHash);
	testDouble(myHash);
	testSplit(myHash);
	testRemove(myHash);
	myHash.display();
  }
  
  
  public static void testAdd (ExtendibleHash myHash) {
	  myHash.add(2, new Page(2)); 
	  myHash.display();
	  assert(myHash.get(2).key == new Page(2).key);  
  }
  
  public static void testDouble (ExtendibleHash myHash) {
	  myHash.add(4, new Page(4));
	  myHash.display();
	  myHash.add(8, new Page(8));
	  myHash.display();
	  myHash.add(16, new Page(16));
	  myHash.display();
	  myHash.add(20, new Page(20));	
	  myHash.display();
	  myHash.add(24, new Page(24));	
	  myHash.display();
	  assert(myHash.getNumBuckets() == 8);  
	  assert(myHash.getGlobDepth() == 3); 
	  assert(myHash.getTable().size() == 8); 
  }
  
  public static void testRemove (ExtendibleHash myHash) {
	  assert (myHash.remove(2) == true);
	  myHash.display();
	  assert(myHash.get(2) == null);
  }
  
  public static void testSplit (ExtendibleHash myHash) {
	  myHash.add(3, new Page(3));
	  myHash.display();
	  myHash.add(7, new Page(7));
	  myHash.display();
	  myHash.add(11, new Page(11));
	  myHash.display();
	  myHash.add(15, new Page(15));	
	  myHash.display();
	  myHash.add(19, new Page(19));
	  myHash.display();
	  // 7 and 15 belong in the 7 bucket
	  assert(myHash.getTable().get(7).find(7) != null);
	  assert(myHash.getTable().get(7).find(15) != null);
	  
	  // 3, 11, and 19 belong in the 3 bucket
	  assert(myHash.getTable().get(3).find(3) != null);
	  assert(myHash.getTable().get(3).find(11) != null);
	  assert(myHash.getTable().get(3).find(19) != null);
  }
  
}
