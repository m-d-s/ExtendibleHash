package extendibleHash;
import extendibleHash.ExtendibleHash;

public class Main {
	
  public static void main(String[] args) {
	ExtendibleHash myHash = new ExtendibleHash(2);	
	testAdd(myHash);
	testDouble(myHash);
	testSplit(myHash);
	testRemove(myHash);
	myHash.display();
  }
  
  
  public static void testAdd (ExtendibleHash myHash) {
	  myHash.add(2, new Page(2)); 
	  assert(myHash.get(2).key == new Page(2).key);  
  }
  
  public static void testDouble (ExtendibleHash myHash) {
	  myHash.add(4, new Page(4));
	  myHash.add(8, new Page(8));
	  myHash.add(16, new Page(16));
	  myHash.add(20, new Page(20));	 
	  myHash.add(24, new Page(24));	 
	  assert(myHash.getNumBuckets() == 8);  
	  assert(myHash.getGlobDepth() == 3); 
	  assert(myHash.getTable().size() == 8); 
  }
  
  public static void testRemove (ExtendibleHash myHash) {
	  assert (myHash.remove(2) == true);
	  assert(myHash.get(2) == null);
  }
  
  public static void testSplit (ExtendibleHash myHash) {
	  myHash.add(3, new Page(3));
	  myHash.add(7, new Page(7));
	  myHash.add(11, new Page(11));
	  myHash.add(15, new Page(15));	 
	  myHash.add(19, new Page(19));	 

  }
  
}
