package extendibleHash;
import java.util.ArrayList;

public class ExtendibleHash {

  private int globDepth;
  private int numBuckets;
  private ArrayList<Bucket> table;
  private final int ancestorSpacing;

  public ExtendibleHash(int initialDepth) {
    this.ancestorSpacing = this.numBuckets = 1 << initialDepth;
    this.globDepth = initialDepth;
    this.table = new ArrayList<>();
    for(int i = 0; i < this.numBuckets; ++i) {
      this.table.add(new Bucket(initialDepth));
    }
  }

  public ArrayList<Bucket> getTable() {
    return table;
  }

  public int getNumBuckets() {
    return numBuckets;
  }

  public int getGlobDepth() {
    return globDepth;
  }

  public void add(int key, Page value) {
    Bucket slot = this.table.get(hash(key, this.globDepth));
    if(slot.hasRoom()) {
      slot.add(value);
    } else {
      if(slot.getLocDepth() == this.globDepth) {
        this.doubleDirectory();
      }
      this.splitBucket(hash(key, this.globDepth), slot);
      this.add(key, value);
    }
  }

  public boolean remove(int key) {
    return this.table.get(hash(key, this.globDepth)).remove(key);
  }

  public Page get(int key) {
    return this.table.get(hash(key, this.globDepth)).find(key);
  }

  private void splitBucket(int toSplit, Bucket splitee) {
    Page[] toDistribute = splitee.getData();
    while(toSplit < this.numBuckets) {
      this.table.set(toSplit, new Bucket(this.globDepth));
      toSplit += this.ancestorSpacing;
    }
    for(Page p : toDistribute) {
      this.add(p.key, p);
    }
  }

  private void doubleDirectory() {
	  int oldNumBuckets = this.numBuckets;
	  for (int i=0;i<oldNumBuckets;++i) {
		  this.table.add(this.table.get(i));
	  }
	  this.numBuckets = this.numBuckets * 2;
	  this.globDepth++;
  }

  private int hash(int key, int depth) {
	  return key & ((1 << depth) - 1);
  }

  void display() {
    int length = this.table.size();
    for(int i = 0; i < length; ++i) {
      System.out.print("Bucket: " + i + ", ");
      this.table.get(i).display();
    }
    System.out.println();
  }
}
