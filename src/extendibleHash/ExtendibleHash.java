package extendibleHash;
import java.util.ArrayList;

public class ExtendibleHash {
  private ArrayList<Bucket> table;
  private final int ancestorSpacing;
  private int numBuckets;
  private int globDepth;

  public ExtendibleHash(int initialDepth) {
    this.ancestorSpacing = (int)Math.pow((double)2,(double)initialDepth);
    this.globDepth = initialDepth;
    this.table = new ArrayList<>();
    this.numBuckets = initialDepth * 2;
    for(int i = 0; i < this.numBuckets; ++i) {
      this.table.add(new Bucket(initialDepth));
    }
  }

  public ArrayList<Bucket> getTable() {
    return table;
  }

  public void setTable(ArrayList<Bucket> table) {
    this.table = table;
  }

  public int getNumBuckets() {
    return numBuckets;
  }

  public void setNumBuckets(int numBuckets) {
    this.numBuckets = numBuckets;
  }

  public int getGlobDepth() {
    return globDepth;
  }

  public void setGlobDepth(int globDepth) {
    this.globDepth = globDepth;
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
    Bucket slot = this.table.get(hash(key, this.globDepth));
    return slot.remove(key);
  }

  public Page get(int key) {
    return this.table.get(hash(key, this.globDepth)).find(key);
  }

  private void splitBucket(int toSplit, Bucket splitee) {
    Page[] toDistribute = splitee.getData();
    while(toSplit < numBuckets) {
      if(this.table.get(toSplit) == splitee) {
        this.table.set(toSplit, new Bucket(this.globDepth));
      }
      toSplit += ancestorSpacing;
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
	  int hash = key & ((1 << depth) - 1);
	  return hash;
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
