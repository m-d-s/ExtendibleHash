package extendibleHash;
import extendibleHash.ExtendibleHash;

public class Main {
  public static void main(String[] args) {
	  ExtendibleHash myHash = new ExtendibleHash(2);
	  myHash.add(2, new Page(2));
	  myHash.add(4, new Page(4));
	  myHash.display();
  }
}
