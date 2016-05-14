import java.util.ArrayList;

/**
 * Created by msimpson on 5/14/16.
 */
public class ExtendibleHash {
  private ArrayList<Bucket> table;
  private int numBuckets;
  private int globDepth;

  public ExtendibleHash(int initialDepth) {
    this.globDepth = initialDepth;
    this.table = new ArrayList<>();
    this.numBuckets = initialDepth << 1;
    for(int i = 0; i < this.numBuckets; ++i) {
      this.table.add(new Bucket(initialDepth));
    }
  }

  public void add(int key, Page value) {
    Bucket slot = this.table.get(hash(key));
    if(slot.hasRoom()) {
      slot.add(value);
    } else {
      if(slot.getLocDepth() == this.globDepth) {
        this.doubleDirectory();
      }
      this.splitBucket(slot);
    }
  }

  public boolean remove(int key) {
    Bucket slot = this.table.get(hash(key));
    return slot.remove(key);
  }

  public Page get(int key) {
    return this.table.get(hash(key)).find(key);
  }

  private void splitBucket(Bucket toSplit) {

  }

  private void doubleDirectory() {

  }

  private int hash(int depth) {

  }
}
